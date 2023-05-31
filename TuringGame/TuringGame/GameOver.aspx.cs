using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
public partial class GameOver : System.Web.UI.Page
{
    public string message,humanRank,skillRank;
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["id"] != null)
        {
            string  id = Session["id"].ToString();
            SetMessage();
            string query = "SELECT SkillRank, HumanRank FROM Users WHERE Id = @0";
            SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
            SqlDataReader reader = DatabaseHelper.ExecuteForResult(conn,query, id);
            reader.Read();
            skillRank = reader.GetInt32(0).ToString();
            humanRank = reader.GetInt32(1).ToString();
            conn.Close();
            
        }
        else Response.Redirect("Default.aspx");
    }
    private void SetMessage()
    {
        string par = Request.QueryString["w"];
        switch (par)
        {
            case "t":
                message = "You Won!";
                break;
            case "f":
                message = "You Lost!";
                break;
            default:
                message = "?????";
                break;
        }
    }
}