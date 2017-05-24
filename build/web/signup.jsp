<%-- 
    Document   : signup
    Created on : Feb 17, 2017, 5:20:27 PM
    Author     : MetalGearRay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign-up</title>
         <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    <body>
        <h1>Sign-up Form</h1>
        <form action="membership" method="post">
            <input type="hidden" name="action" value="signup">
            <label for="code" class="labels">First Name:</label>
                <input type="text" name="firstName"/> <br><br>
                
            <label for="desc" class="labels">Last Name: </label>
                <input type="text" name="lastName"/> <br><br>
                
            <label for="price" class="labels">Email:</label>
                <input type="text" name="email"/> <br><br>
                
            <label for="price" class="labels">Username:</label>
                <input type="text" name="username"/> <br><br>
            
            <label for="price" class="labels">Password:</label>
                <input type="text" name="pass"/> <br><br>
                
                <button type="submit" value="signup" class="button">Sign Up</button>
        </form>
    </body>
</html>
