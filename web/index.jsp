<%@ page import="Objects.Book" %><%--Created by IntelliJ IDEA.
User: Myles
Date: 4/15/17
Time: 7:44 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookstore Homepage</title>
</head>
<body>
<tr>
    <td width="75%">
        <img src="image/images/ksu (1).png" width="300" height="100">
    </td>
    <td width="25%">
        <a href="shoppingCart.jsp"><img src="image/images/cart.png" alt="cart" width="80" height="80" align="right" /></a>
    </td>
</tr>
<% for (int i = 0; i < 10; i++) {
    out.println("</br>");
}
//session.setAttribute("loggedIn", new Boolean(null));
%>

<div class="SearchForms">
    <form name="searchForm" action="/Controller" method="post">
        <input name="searchBox" type="text" value="" width="100" height="400" size="175" align="center"/>

        <select name="type">
            <option value="keyword">Keyword</option>
            <option value="course">Course</option>
            <option value="professor">Professor</option>
        </select>
        <% for (int i = 0; i < 3; i++) {
            out.println("</br>");
        } %>
        <input type="submit" name="Click" value="Submit">


    </form>
    <% for (int i = 0; i < 18; i++) {
        out.println("</br>");
    } %>
    <a href="aboutUs.jsp">About Us</a>
</div>
<style>
    .SearchForms {
        text-align: center;
        box-shadow: #2c4557;
    }

</style>
</br>


</body>
</html>
