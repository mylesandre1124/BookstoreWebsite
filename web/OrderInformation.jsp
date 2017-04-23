<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ShoppingCart.ShoppingCart" %>
<html>
<% if (session.getAttribute("cart") != null) {
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
}%>
<head>
    <title>Order Information - Checkout</title>
</head>

<body>
    <table width = "100%">
        <tr>
            <td width= "75%"><a href="http://localhost:8080/BookstoreWebsite_war_exploded/index.jsp"><img src="image/images/ksu (1).png" width="350" height="100" alt="Kennesaw Logo"></a></td>
            <td width="25%">
                <a href="shoppingCart.jsp"><img src="image/images/cart.png" alt="cart" width="80" height ="80"/></a>
            </td>
        </tr>

    </table>
</body>
</html>
