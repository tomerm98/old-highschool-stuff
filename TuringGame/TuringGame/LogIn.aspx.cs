using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
public partial class LogIn : System.Web.UI.Page
{
    public string errors = "";
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Request.Form["submit"] != null)
        {
            string identification = Request.Form["identification"];
            string password = Request.Form["password"];
            string query = "SELECT Id FROM Users WHERE Email = @0 AND Password = @1 " +
                         "OR Name = @0 AND Password = @1";
            SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
            SqlDataReader reader = DatabaseHelper.ExecuteForResult(conn,query, identification, password);
            if (reader.HasRows)
            {
                reader.Read();
                int id = reader.GetInt32(0);
                conn.Close();
                Session["id"] = id;
                Response.Redirect("Default.aspx");
            }
            else
            {
                errors = "The email/username or password are not correct";
                conn.Close();
            }
        }
        


    }

}