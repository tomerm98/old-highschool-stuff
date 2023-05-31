<%@ Page Language="C#" AutoEventWireup="true" CodeFile="LogIn.aspx.cs" Inherits="LogIn" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Log In</title>
    <link type="text/css" rel="stylesheet" href="Css/Forms.css" />
    <link type="text/css" rel="stylesheet" href="Css/General.css" />

    <!--Reference the jQuery library. -->
    <script src="/Scripts/jquery-1.10.2.min.js"></script>
</head>
<body>
      <a href="Default.aspx">
        <img src="Images/home.png" class="homeIcons" width="80" />
    </a>
   
<!------LOG IN FORM------>
        <h1 class="SmallHeadLines">Log In</h1><br />
        <h5 id = "errors" class = "Errors"><%=errors %></h5> <br />
        <div class="FormDivs ">
            <form method="post" action="LogIn.aspx" onsubmit= "return Valid()">
                <h3>
                Email or Username: <br /> <input type="text" name="identification" class="Inputs" id="identification"  maxlength="30"/> 
                Password: <br /> <input type="password" name="password" class="Inputs" id ="password" maxlength="30"/> 
                
               
                </h3>

                  <input type ="submit" name="submit" value="Log in" id="submit" class="Buttons FormButtons "/>
               
            </form>
        </div>

    <!------LOG IN FORM------>
    
    
    <!------FUNCTIONS------>
     <script type="text/javascript">


         //checks if the form inputs are valid 
         function Valid() {
             var password = $('#password').val();
             var identification = $('#identification').val();
             if (identification == "" || password == "") {
                $('#errors').text("Please fill in all fields");
                 return false;
             }
             return true;
       
         }
 </script>
</body>
</html>
