using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    public string topButtons;
    public string signOutStyle = "";
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Request.Form["submit"] != null)
            Session["id"] = null;

        if (Session["id"] == null)
        {
            topButtons = "<a href=\"LogIn.aspx\">" +
                   "<input class=\"Buttons\" type=\"button\" value=\"Sign In\" style=\"margin-right: 70px;\" /></a>" +
                   "<a href = \"SignUp.aspx\" >" +
                   "<input class=\"Buttons\" type=\"button\" value=\"Sign Up\" /> </a>";
            signOutStyle = "display:none;";
        }
        else topButtons = " <a href=\"ChatRoom.aspx\">" +
                     "<input class=\"Buttons\" type=\"button\" value=\"Play\" /></a>";
    }
}