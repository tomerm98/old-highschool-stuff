using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Leaderboards : System.Web.UI.Page
{
    
    public string myStats ="";
    public string table;
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["id"] != null)
        {
            string id = Session["id"].ToString();
            string query = "SELECT SkillRank, HumanRank FROM Users WHERE Id = @0";
            SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
            SqlDataReader reader = DatabaseHelper.ExecuteForResult(conn, query, id);
            reader.Read();
            string skillRank = reader.GetInt32(0).ToString();
            string humanRank = reader.GetInt32(1).ToString();
            conn.Close();

            SetMyStats(skillRank, humanRank);

        }
        if (Request.Form["skill"] != null)
            SetTable("SkillRank");
        else if (Request.Form["human"] != null)
            SetTable("HumanRank");
        

      
    }
    private void SetTable(string orderBy)
    {
        table = "<table> <tr>" +
                 "<th>User</th>" +
                 "<th>Skill Rank</th>" +
                 "<th>Human Rank</th></tr>";
        string query = "SELECT Name, SkillRank, HumanRank FROM Users ORDER BY " + orderBy + " DESC";
        SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
        SqlDataReader reader = DatabaseHelper.ExecuteForResult(conn, query);
        while (reader.Read())
        {
            table += "<tr>" +
                    "<td>" + reader.GetString(0) + "</td>" +
                     "<td>" + reader.GetInt32(1) + "</td>" +
                      "<td>" + reader.GetInt32(2) + "</td>" +
                      "</tr>";
        }
        table += "</table>";


        table += "</table>";
    }
    private void SetMyStats(string skillRank, string humanRank)
    {
        myStats = "<h1> My Skill Rank: " + skillRank +
            "<br />" +
              "My Human Rank: " + humanRank +
              "</h1 ><br />";


    }
}