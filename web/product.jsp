<%-- 
    Document   : product
    Created on : Feb 9, 2017, 2:15:04 PM
    Author     : MetalGearRay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Product Management</title>
       <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    <body>
        User <a href="membership?action=logout">Logout</a>
        <h1>Product</h1>
        <form action="membership" method="post">  
            <label for="code" class="labels">Code:</label>
            <input type="text" name="code" value="${productData.code}"/><br><br>
                
            <label for="desc" class="labels">Description: </label>
                <textarea name="desc" class="desc">${productData.description}</textarea> <br><br>
                
            <label for="price" class="labels">Price:</label>
                <input type="text" name="price" value="${productData.price}"/><br><br>
            
            <button type="submit" name="action" value="update_product" class="button">Update Product</button>
            <button type="submit" name="action" value='view_products' class="button">View Product</button>
        </form>
    </body>
</html>
