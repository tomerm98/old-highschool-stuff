using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
public partial class SignUp : System.Web.UI.Page
{

    public string defaultEmail = "";
    public string errors = "";
    public string defaultUname = "";
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Request.Form["submit"] != null)
        {

            string uname = Request.Form["uname"];
            string email = Request.Form["email"];
            string password = Request.Form["password"];
            defaultUname = uname;
            defaultEmail = email;
            bool emailExist = IsEmailExist(email);
            bool unameExist = IsUsernameExist(uname);
            SetErrors(emailExist, unameExist);
            if (errors == "")
            {
                CreateNewUser(uname, password, email);
                Response.Redirect("LogIn.aspx");
            }
        }
    }

    private void SetErrors(bool emailExist, bool unameExist)
    {
        if (emailExist && unameExist)
            errors = "The email and username already exists";
        else if (emailExist && !unameExist)
            errors = "The email already exists";
        else if (!emailExist && unameExist)
            errors = "The username already exists";

    }

    private void CreateNewUser(string uname, string password, string email)
    {
        string query = "INSERT INTO Users (Name, Password, Email) VALUES(@0, @1, @2);";
        SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
        DatabaseHelper.Execute(conn,query, uname, password, email);
        conn.Close();
    }

    private bool IsEmailExist(string email)
    {
        string query = "SELECT * FROM Users WHERE Email = @0;";
        SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
        SqlDataReader reader = DatabaseHelper.ExecuteForResult(conn, query,email);
        bool b = reader.HasRows;
        conn.Close();
        return b;
    }
    private bool IsUsernameExist(string uname)
    {
        string query = "SELECT * FROM Users WHERE Name = @0;";
        SqlConnection conn = new SqlConnection(Constants.DATABASE_CONNECTION);
        SqlDataReader reader = DatabaseHelper.ExecuteForResult(conn,query, uname);
        bool b =  reader.HasRows;
        conn.Close();
        return b;
    }
}