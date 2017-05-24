<%-- 
    Document   : products
    Created on : Feb 9, 2017, 1:46:02 PM
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
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <body>
        
        User <a href="membership?action=logout">Logout</a>
        <h1>Products</h1>
        <form action="productManagement" method="post">
            <table>
                <tr>
                    <th>Code</th>
                    <th class="desc">Description</th>
                    <th class="price">Price</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <c:forEach items="${productData}" var="current">
                    <tr>
                        <td><c:out value="${current.code}" /></td>  
                        <td><c:out value="${current.description}" /></td>
                        <td><c:out value="${current.price}" /></td>
                        <td>&nbsp;</td>
                        <td><a href="productManagement?action=delete&code=${current.code}&desc=${current.description}&price=${current.price}">Delete</td>
                        <td><a href="productManagement?action=update&code=${current.code}&desc=${current.description}&price=${current.price}">Edit</td>
                    </tr>
                </c:forEach>
            </table>
        <br>
            <button type="submit" name="action" value="add_product">Add Product</button>
        </form>
    </body>
</html>
