<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="The_Turing_Game.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var chatHub = $.connetion.chatHub;
            registerClientMethods(chatHub);
            $.connection.hub.
        });
      
    </script>
</head>
<body>
    <h1 id="a"><%=test %></h1>
</body>
</html>
