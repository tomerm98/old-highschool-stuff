using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Cleverbot.Net;
/// <summary>
/// Summary description for BotUser
/// </summary>
public class BotUser : User //= user that chats with a bot
{
    public BotUser(string ConnectionId, int SiteId) :base(ConnectionId,SiteId)
    {
        botSession = null;
    }

    private CleverbotSession botSession;

    public CleverbotSession BotSession
    {
        get { return botSession; }
        set { botSession = value; }
    }



}