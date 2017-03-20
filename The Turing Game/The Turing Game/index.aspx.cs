using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Cleverbot.Net;

namespace The_Turing_Game
{
    public partial class index : System.Web.UI.Page
    {
        public string test;
        protected void Page_Load(object sender, EventArgs e)
        {
            string message;
            var session = CleverbotSession.NewSession("GQLewj2sFuLmrlk1", "qcB96yku1mVAeI1u2NMLyvpLuGF4aCWk");
            test = session.Send("what do you think about antartica?");


        }
    }
}