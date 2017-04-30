<%--
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

    <h1>Financial Aid Login</h1>
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
                        <meta http-equiv="Refresh" content="10;url=OrderInformation.jsp">
                        <%
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
    <form name="login" method="post" action="/Login">
        <input name="username" align="center" type="text">
        <input name="password" align="center" type="password">
        <input name="submit" value="Submit" type="submit">
    </form>
    </body>

</html>
