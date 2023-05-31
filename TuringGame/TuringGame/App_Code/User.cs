using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for User
/// </summary>
public class User
{
    public User(string ConnectionId, int SiteId)
    {
        connectionId = ConnectionId;
        siteId = SiteId;
       
    }


    private string connectionId;
    public string ConnectionId
    {
        get { return connectionId; }
        set { connectionId = value; }
    }

    private int siteId;

    public int SiteId
    {
        get { return siteId; }
        set { siteId = value; }
    }




}
