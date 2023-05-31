using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class ChatRoom : System.Web.UI.Page
{
    public int siteId;
    public const int MAX_MESSAGE_COUNT = Constants.MAX_MESSAGE_COUNT;
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["id"] != null)
            siteId = (int)Session["id"];
        else Response.Redirect("LogIn.aspx?r=ChatRoom.aspx");
    }
}