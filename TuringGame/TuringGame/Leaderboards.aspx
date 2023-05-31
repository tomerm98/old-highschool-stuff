<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Leaderboards.aspx.cs" Inherits="Leaderboards" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Leaderboards</title>
    <link type="text/css" rel="stylesheet" href="Css/Forms.css" />
    <link type="text/css" rel="stylesheet" href="Css/General.css" />

    <!--Reference the jQuery library. -->
    <script src="/Scripts/jquery-1.10.2.min.js"></script>
</head>
<body>
     <a href="Default.aspx">
        <img src="Images/home.png" class="homeIcons" width="80" />
    </a>
     <h1 class="SmallHeadLines">Leaderboards</h1>
     <div class="FormDivs" style="text-align:center;">
         <br />
         <%=myStats %>
         <br />
         <form method="post" action="Leaderboards.aspx">
         <input class="Buttons" type="submit" name="skill" value="Most Skillful Players" style="margin-right: 70px; width:250px; height:60px;" />
         <input class="Buttons" type="submit" name="human" value="Most Human Humans"style="width:250px; height:60px;" />
         </form>
         <br />
         <br />
         <br />
         <%=table %>
     </div>
</body>
</html>
