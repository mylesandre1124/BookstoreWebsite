<%@ page import="Objects.Student" %>
<%@ page import="Objects.ShoppingCart" %>
<%@ page import="Objects.StudentsDatabase" %>
<%@ page import="java.util.TreeMap" %><%--
  Created by IntelliJ IDEA.
  User: Myles
  Date: 4/27/17
  Time: 5:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
    <table>
        <tr>
            <td width="75%"><a href ="http://localhost:8080/index.jsp"><img src="image/images/ksu (1).png" width = "350" height = "100" alt = "Kennesaw Logo"></a></td>
            <td width="25%"><a href="shoppingCart.jsp"><img src="image/images/cart.png" alt="cart" width="80" height ="80" align="right"/></a></td>
        </tr>
        <tr>
            <td><h1>Financial Aid Login</h1></td>
        </tr>

    </table>
    <body>
    <%
        boolean loggedIn;
        try {
            loggedIn = (boolean) session.getAttribute("loggedIn");
            if(loggedIn)
            {
                try{
                    boolean finaid = (boolean)session.getAttribute("finaid");
                    if(!finaid)
                    {
                        out.println("You do not have enough financial aid for this purchase. This page will automatically redirect in 10 seconds");
                        %>
                        <meta http-equiv="Refresh" content="10;url=paymentInfo.jsp">
                        <%
                    }
                    else if(finaid)
                    {
                        Student student = (Student) session.getAttribute("student");
                        StudentsDatabase students = new StudentsDatabase();
                        TreeMap<String, Student > db = students.getStudents();
                        Student newStudent = db.get(student.getUsername());
                        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                        boolean doubleCheck = newStudent.checkFinancialAid(Double.parseDouble(cart.getTotalPrice()));
                        if(doubleCheck)
                        {
                            response.sendRedirect("OrderConfirmation.jsp");
                        }
                        else if(!doubleCheck)
                        {
                            out.println("You do not have enough financial aid for this purchase. This page will automatically redirect in 10 seconds");
                            %>
                            <meta http-equiv="Refresh" content="10;url=paymentInfo.jsp">
                            <%
                        }
                    }
                }
                catch (NullPointerException ex)
                {}
            }
            else if(!loggedIn)
            {
                out.println("Incorrect user information");
            }
        }
        catch (NullPointerException ex)
        {}
    %>
    <form name="login" method="post" action="Login">
        <input name="username" align="center" type="text" placeholder="Username:">
        </br></br>
        <input name="password" align="center" type="password" placeholder="Password:">
        </br></br>
        <input name="submit" value="Submit" type="submit">
    </form>
    </body>

</html>
