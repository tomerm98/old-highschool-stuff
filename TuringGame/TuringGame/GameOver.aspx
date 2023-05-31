<%@ Page Language="C#" AutoEventWireup="true" CodeFile="GameOver.aspx.cs" Inherits="GameOver" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title><%=message %></title>
     <link type="text/css" rel="stylesheet" href="Css/Forms.css" />
    <link type="text/css" rel="stylesheet" href="Css/General.css" />

    <!--Reference the jQuery library. -->
    <script src="/Scripts/jquery-1.10.2.min.js"></script>
</head>
<body>
    <a href="Default.aspx"> 
        <img src="Images/home.png" class="homeIcons" width="80"/>
    </a>
        <h1 class="Headlines"><%=message %> </h1>
     <div class="FormDivs" style="text-align:center;">
         <h1>
             Current Skill Rank: <%= skillRank %>
              <br />
             <br />
             Current Human Rank: <%= humanRank%>
             
         </h1>
         <br />
          <input class="Buttons" type="button" value="Play Again" onclick="window.location = 'ChatRoom.aspx'"
            style="display:block;margin-left:auto;margin-right:auto;"/>
     </div>

</body>
</html>
