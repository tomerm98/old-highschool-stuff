<%@ Page Language="C#" AutoEventWireup="true" CodeFile="SignUp.aspx.cs" Inherits="SignUp" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Sign Up</title>
    <link type="text/css" rel="stylesheet" href="Css/Forms.css" />
    <link type="text/css" rel="stylesheet" href="Css/General.css" />

    <!--Reference the jQuery library. -->
    <script src="/Scripts/jquery-1.10.2.min.js"></script>
</head>
<body>
    <a href="Default.aspx">
        <img src="Images/home.png" class="homeIcons" width="80" />
    </a>
    <!------SIGN UP------>
    <h1 class="SmallHeadLines">Sign Up</h1>
    <br />
    <h5 id="errors" class="Errors"><%=errors %></h5>
   
    <div class="FormDivs">
        <form method="post" action="SignUp.aspx" onsubmit="return Valid()">


            <h3 style="margin-top: 0;">Username:
                <br />
                <input type="text" name="uname" id="uname" class="Inputs" maxlength="20" value="<%=defaultUname %>" />

                Email: 
                <br />
                <input type="text" name="email" id="email" class="Inputs" maxlength="30" value="<%=defaultEmail %>" />

                Password: 
                <br />
                <input type="password" name="password" id="password" class="Inputs" maxlength="30" />

                Confirm Password: 
                <br />
                <input type="password" id="cpassword" class="Inputs" maxlength="30" />

            </h3>


            <input type="submit" name="submit" value="Submit" class="Buttons FormButtons" />

        </form>
    </div>
    <!------SIGN UP------>
    <script type="text/javascript">
        function Valid() {
            $('#errors').text('');
            var password = $('#password').val();
            var cpassword = $('#cpassword').val();
            var email = $('#email').val();
            var username = $('#uname').val();

            if (password == '' || cpassword == '' || email == '' || username == '') {
                $('#errors').text('Please fill in all fields.');

                return false;
            }
            if (password != cpassword) {
                $('#errors').text("Passwords don't match");
                return false;
            }
            return true;
        }
    </script>


</body>
</html>
