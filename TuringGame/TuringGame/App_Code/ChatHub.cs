using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Microsoft.AspNet.SignalR;
using System.Threading.Tasks;
using System.Threading;
using Cleverbot.Net;
using System.Data.SqlClient;

public class ChatHub : Hub
{
    
   

    static List<User> ConnectedUsers = new List<User>();



    public void Connect(int siteId)
    {
        string connId = Context.ConnectionId;

        if (!ConnectedUsers.Exists
            (x => x.ConnectionId == connId || x.SiteId == siteId))
        {
            User newUser = CreateNewUser(connId, siteId);
            ConnectedUsers.Add(newUser);
            Clients.Caller.onConnected();
        }
        else Clients.Caller.onConnectionFailed();

    }

    public void FindPartner()
    {
        User user = GetCurrentUser();
        if (user != null)
        {
            if (user is BotUser)
                FindBotPartner((BotUser)user);

            else if (user is HumanUser)
                FindHumanPartner((HumanUser)user);
        }
    }

    public void SendMessage(string message)
    {
        message = message.ToLower();
        User user = GetCurrentUser();
        if (user != null)
        {
            if (user is BotUser)
                sendBotMessage((BotUser)user, message);
            else if (user is HumanUser)
                sendHumanMessage((HumanUser)user, message);
        }
    }

    public override Task OnDisconnected(bool stopCalled)
    {
        User user = GetCurrentUser();
        if (user != null)
        {
            if (user is HumanUser)
            {
                HumanUser partner = ((HumanUser)user).Partner;
                if (partner != null)
                    Clients.Client(partner.ConnectionId).onPartnerLeft();
            }
            ConnectedUsers.Remove(user);
        }

        return base.OnDisconnected(stopCalled);
    }

    public void GuessPartnerIsHuman()
    {
        User user = GetCurrentUser();
        if (user != null)
        {
            if (user is HumanUser)
            {
                HumanUser humanUser = (HumanUser)user;
                ChangeHumanRank(humanUser.Partner, Constants.HUMAN_RANK_CHANGE);
                Win(user);
            }
            else if (user is BotUser)
                Lose(user);
        }
    }

    public void GuessPartnerIsBot()
    {
        User user = GetCurrentUser();
        if (user != null)
        {
            if (user is HumanUser)
            {
                HumanUser humanUser = (HumanUser)user;
                ChangeHumanRank(humanUser.Partner, -Constants.HUMAN_RANK_CHANGE);
                Lose(user);
            }
            else if (user is BotUser)
                Win(user);
        }
    }



    private void ChangeSkillRank(User user, int amount)
    {
        if (user != null)
        {
            string siteId = user.SiteId.ToString();
            string amountString = amount.ToString();
            if (amount >= 0)
                amountString = "+" + amountString;
            string query = 
                "UPDATE Users SET SkillRank = SkillRank "+amountString+" WHERE Id = @0;";

            SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
            DatabaseHelper.Execute(conn,query,siteId);
            conn.Close();
        }
    }

    private void ChangeHumanRank(User user, int amount)
    {
        if (user != null)
        {
            string siteId = user.SiteId.ToString();
            string amountString = amount.ToString();
            if (amount >= 0)
                amountString = "+" + amountString;
            string query = 
                "UPDATE Users SET HumanRank = HumanRank "+amountString+" WHERE Id = @0;";

            SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
            DatabaseHelper.Execute(conn,query,siteId);
            conn.Close();
        }
    }

    private void Lose(User user)
    {
        ChangeSkillRank(user, -Constants.SKILL_RANK_CHANGE);
        
        string url = "GameOver.aspx?w=f";
        Clients.Caller.onGameOver(url);
    }

    private void Win(User user)
    {
        ChangeSkillRank(user, Constants.SKILL_RANK_CHANGE);
     
        string url = "GameOver.aspx?w=t";
        Clients.Caller.onGameOver(url);
    }

    private void sendBotMessage(BotUser user, string message)
    {
        CleverbotSession botSession = user.BotSession;
        if (botSession != null)
        {
            Clients.Caller.onMessageReceived(message, true);
            string response = botSession.GetResponse(message).Response;
            string processedResponse = ProcessBotResponse(response);
            Clients.Caller.onMessageReceived(processedResponse, false);
        }
    }

    private void sendHumanMessage(HumanUser user, string message)
    {
        HumanUser partner = user.Partner;
        if (partner != null)
        {
            Clients.Caller.onMessageReceived(message, true);
            Clients.Client(partner.ConnectionId).onMessageReceived(message, false);
        }
    }

    private User CreateNewUser(string connId, int siteId)
    {
        User newUser;
        if (FlipCoin())
            newUser = new BotUser(connId, siteId);
        else
            newUser = new HumanUser(connId, siteId);

        return newUser;

    }

    private User GetCurrentUser()
    {
        string connId = Context.ConnectionId;
        return ConnectedUsers.Find(x => x.ConnectionId == connId);

    }

    private void FindBotPartner(BotUser user){
        user.BotSession = new CleverbotSession(Constants.CLEVERBOT_KEY);
        WaitRandomTime(5000);
        Clients.Caller.onPartnerJoined();

        //cleverbot starts the conversation
        if (FlipCoin())
        {
            string conversationStarter = GetRandomConversationStarter();
            CleverbotResponse cbr = user.BotSession.GetResponse(conversationStarter);
            string message = ProcessBotResponse(cbr.Response);
            Clients.Caller.onMessageReceived(message, false);
        }
    }

    private void WaitRandomTime(int amountMS)
    {
        Random rng = new Random();
        int randomAmount = rng.Next(amountMS);
        Thread.Sleep(randomAmount);
    }

    private void FindHumanPartner(HumanUser user)
    {
        HumanUser partner = (HumanUser)ConnectedUsers
              .Find(x => x is HumanUser && x != user &&((HumanUser)x).Partner == null);
        if (partner != null)
        {
            user.Partner = partner;
            partner.Partner = user;
            Clients.Caller.onPartnerJoined();
            Clients.Client(partner.ConnectionId).onPartnerJoined();
        }
    }

    private bool FlipCoin()
    {
        Random rng = new Random();
        return rng.Next(2) == 0;
    }
    private string ProcessBotResponse(string response)
    {
        string newResponse = "";
        string forbidden = ".,'?";
        foreach (char c in response.ToLower())
        {
            if (!forbidden.Contains(c))
                newResponse += c;
            else if (FlipCoin())
                newResponse += c;
            Thread.Sleep(400);
        }
        return newResponse;
    }
    private string GetRandomConversationStarter()
    {
        string[] starters =
            {
            "hello",
            "hi",
            "lets talk",
        };
        Random rng = new Random();
        int randomIndex = rng.Next(starters.Length);
        return starters[randomIndex];
    }
}
