<%-- 
    Document   : login
    Created on : Feb 9, 2017, 1:54:20 PM
    Author     : MetalGearRay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    <body>
        <h1>Login</h1>
        <form action="membership?action=view_products" method="post">
            <input type="hidden" name="action" value="login">
            <label for="price" class="labels">Username:</label>
                <input type="text" name="username"/> <br><br>
                
            <label for="price" class="labels">Password:</label>
                <input type="text" name="pass"/> <br><br>
                
            <button type="submit" value='login' class="button">Login</button>
        </form><br>
        <a href="signup.jsp">New user? Click here to register</a>
    </body>
</html>
