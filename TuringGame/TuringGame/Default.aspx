<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>The Turing Game</title>
    <link type="text/css" rel="stylesheet" href="Css/Forms.css" />
    <link type="text/css" rel="stylesheet" href="Css/General.css" />

    <!--Reference the jQuery library. -->
    <script src="/Scripts/jquery-1.10.2.min.js"></script>

</head>
<body>
    <h1 class="Headlines">The Turing Game</h1>
    <br />
    <div style="text-align: center;">
        <%=topButtons %>
        <br />
        <br />
        <br />

        <img src="Images/alan_turing.jpg" />
        <br />
        <br />
        <br />

        <a href="Leaderboards.aspx">
            <input class="Buttons" type="button" value="Leaderboards" />
        </a>
        <br />
        <br />
       
        <form method="post" action="Default.aspx">
           <input class="Buttons" type="submit" name="submit" value="Sign Out" style="<%=signOutStyle%>"/>  
        </form>
    </div>


</body>
</html>
