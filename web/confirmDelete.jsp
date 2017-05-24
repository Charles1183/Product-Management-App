<%-- 
    Document   : confirmDelete
    Created on : Feb 9, 2017, 3:14:34 PM
    Author     : MetalGearRay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Delete Page</title>
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    <body>
        User <a href="membership?action=logout">Logout</a>
        <h1>Are you sure you want to delete this product?</h1>
        <form action="membership" method="post">
            <label for="code" class="labels">Code:</label> ${productData.code} <br><br>
            <input type="hidden" name="code" value="${productData.code}">
                
            <label for="desc" class="labels">Description: </label>  ${productData.description} <br><br>
            <input type="hidden" name="desc" value="${productData.description}">
                
            <label for="price" class="labels">Price:</label> ${productData.price} <br><br>
            <input type="hidden" name="price" value="${productData.price}">
            
            <button type="submit" name="action" value='yesDelete'>Yes</button>
            <button type="submit" name="action" value='noDelete'>No</button>
        </form>
    </body>
</html>
