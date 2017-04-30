<%@ page import="Objects.ShoppingCart" %>
<%@ page import="Objects.Book" %><%--
  Created by IntelliJ IDEA.
  User: Myles
  Date: 4/29/17
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ShoppingCart cart;
        try {
            cart = (ShoppingCart) session.getAttribute("cart");
            }
        catch (NullPointerException ex)
        {
            out.println("The shopping cart is empty");
            cart = new ShoppingCart();
        }
%>
<html>
<head>
    <title>Order Confirmation</title>
</head>
<body>
<a href ="http://localhost:8080/index.jsp"><img src="image/images/ksu (1).png" width = "350" height = "100" alt = "Kennesaw Logo"></a>
<table align="center">
    <%
        for (int i = 0; i < cart.size(); i++) {
            Book book = cart.getBook(i);
        %>
    <thead>Items Ordered:</thead>
    <tr>
        <td>
            <%out.println(book.getBookName());%>
        </td>
        <td>
            <%switch (cart.getBookType(i))
            {
                case 1:
                    out.println("New");
                    break;
                case 2:
                    out.println("Used");
                    break;
                case 3:
                    out.println("Rental");
                    break;
                case 4:
                    out.println("Ebook");
                    break;
            }%>
        </td>
        <td>
            <%
                switch (cart.getBookType(i))
                {
                    case 1:
                        out.println("$" + cart.getBookQuantity(i) * book.getNewPrice());
                        break;
                    case 2:
                        out.println("$" + cart.getBookQuantity(i) * book.getUsedPrice());
                        break;
                    case 3:
                        out.println("$" + cart.getBookQuantity(i) * book.getRentalPrice());
                        break;
                    case 4:
                        out.println("$" + cart.getBookQuantity(i) * book.getEbookPrice());
                        break;
                }
            %>
        </td>
        <td>
            <%out.println(cart.getBookQuantity(i));%>
        </td>
    </tr>

    <%
        }
    %>
    <style>
        table, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        td {
            padding: 5px;
            text-align: left;
        }
    </style>
</table>
<form name="updateInfo" action="OrderInformation.jsp" method="get">
    <input type="submit" name="updateInfo" value="Edit Information">
</form>
<form name="updateCart" action="shoppingCart.jsp" method="get">
    <input type="submit" name="update" value="Edit Cart">
</form>
</body>
</html>
