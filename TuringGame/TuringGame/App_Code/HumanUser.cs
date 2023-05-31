using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for HumanUser
/// </summary>
public class HumanUser : User //= user that chats with a human
{
    public HumanUser(string ConnectionId,int SiteId) :base(ConnectionId,SiteId)
    {
        partner = null;
    }

    private HumanUser partner;
    public HumanUser Partner
    {
        get { return partner; }
        set { partner = value; }
    }

}