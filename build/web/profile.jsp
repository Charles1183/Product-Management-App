<%-- 
    Document   : profile
    Created on : Mar 17, 2017, 6:52:37 PM
    Author     : DarkHado
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test Output!</h1>
        <h3>User Data:</h3>
        <table style="width:50%">
            <tr>
                <th>First Name:</th>
                <th>Last Name:</th>
                <th>Email:</th>
                <th>Password:</th>     
            </tr>
            <tr>
                <td>${userData.firstName}</td>
                <td>${userData.lastName}</td>
                <td>${userData.email}</td>
                <td>${userData.password}</td>
            </tr>
            
        </table>
        
    </body>
</html>
