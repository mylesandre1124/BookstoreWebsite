<%--
    Document   : BookDetails
    Created on : Apr 19, 2017, 4:53:54 PM
    Author     : Nicolas Perez
--%>

<%@page import="Objects.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  String rnum;
    rnum = request.getParameter("get");//this variable is used to grab the correct book from search results.
    Book b = (Book)request.getSession().getAttribute("result"+rnum);
    session.setAttribute("book", b);//adds this book to the session when the page is loaded in case the user decides to buy it.
    session.setAttribute("rnum", rnum);//stores this variable so the controller can come back to this page.%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%out.println(b.getBookName());%></title>
    <style type ="text/css" media="screen"><!--
    #Description{
        margin-left: auto;
        margin-right: auto;
        font-size: 14px
    }
    p {
    margin-left: 80px;
    }
    --></style>
</head>
<body>
    <table width ="100%">
            <tr>
                <td width="75%"><a href="index.jsp"><img src="image/images/ksu (1).png" width="30%" height="100" alt="Kennesaw Logo"></a>
                </td>
                <td width="25%">
                    <a href="shoppingCart.jsp"><img src="image/images/cart.png" alt="cart" width="80" height="80"/></a>
                </td>
            </tr>
    </table>
<%
    String isbn1;
    String isbn2;
    String ISBN = String.valueOf(b.getIsbn());
    isbn1 = ISBN.substring(0, 3);
    isbn2 = ISBN.substring(3, ISBN.length());
%>

<table border="0px" cellspacing="3px" cellpadding="3px">
    <tr>
        <td>
            <img src="image/images/<%out.println(isbn1+"-"+isbn2);%>.jpg" margin-left="100px">
        </td>
        <td>
            <table border="0px" cellspacing="3px" cellpadding="3px">
                <tr>
                    <td>
                        Title: <strong> <% out.println(b.getBookName()); %> </strong>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    Author(s):
                                </td>
                                <td>
                                    <table>
                                        <% for(int j = 0; j < b.getAuthor().length; j++){
                                            out.println(" <tr><td>"+b.getAuthor()[j]+"</td></tr>"); }%>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        ISBN: <%out.println(isbn1+"-"+isbn2);%>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<br><br>
<table align="center" border="0px" cellspacing="3px" cellpadding="3px">
    <tr>
        <td>
            <%--BUY NEW BOOK TABLE --%>
            <table border="1px">
                <tr>
                    <td>
                        <form action='${pageContext.request.contextPath}/Bookstore' method="post">
                            <input type="submit" value="buy NEW" name="action" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="number" name="qty" min="0" max="<%out.println(b.getNewQuantity());%>">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        Price<br>
                        <%out.println("$"+ b.getNewPrice()); %>
                    </td>
                    <td>
                        Qty available: <% out.println(b.getNewQuantity()); %>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <%--BUY USED BOOK TABLE --%>
            <table border="1px">
                <tr>
                    <td>
                        <form action='${pageContext.request.contextPath}/Bookstore' method="post">
                            <input type="submit" value="buy USED" name="action" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="number" name="qty" min="0" max="<%out.println(b.getUsedQuantity());%>">
                        </form>
                    </td>

                </tr>
                <tr>
                    <td>
                        Price<br>
                        <%out.println("$"+ b.getUsedPrice()); %>
                    </td>
                     <td>
                        Qty available: <%out.println(b.getUsedQuantity()); %>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <%--BUY RENTAL BOOK TABLE --%>
            <table border="1px">
                <tr>
                    <td>
                        <form action='${pageContext.request.contextPath}/Bookstore' method="post">
                            <input type="submit" value="buy RENTAL" name="action" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="number" name="qty" min="0" max="<%out.println(b.getRentalQuantity());%>">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        Price<br>
                        <%out.println("$"+ b.getRentalPrice()); %>
                    </td>
                    <td>
                        Qty available: <%out.println(b.getRentalQuantity()); %>
                    </td>
                </tr>
            </table>
        </td>
        <td>
            <%--BUY EBOOK TABLE --%>
            <table border="1px">
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/Bookstore" method="post">
                            <input type="submit" value="buy Ebook" name="action" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="number" name="qty" >
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        Price<br>
                        <%out.println("$"+ b.getEbookPrice()); %>
                    </td>
                    <td>
                        Available: <%if(b.getEbookQuantity()>1)
                                        out.println("Yes");
                                    else
                                        out.println("No");%>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<div id="Description">
    <p style="font-size: 24px" margin-left="200px"><strong>Description: </strong></p>
    <table width="700px">
        <tr>
            <td>
                <p style="font-size: 18px"><%out.println(b.getDescription()); %></p>
            </td>
        </tr>
    </table>
</div>

<br><br>
<table align="center" cellspacing="50px">
    <tr>
        <td>
            <% if(b.getBuyDemand()== "Required"){ 
             out.println("<table border='1px'><tr><td>Required:</td></tr><tr><td>Course#:</td><td>Professor:</td></tr><tr><td>");
             out.println( b.getCourse()+"</td><td>"+b.getProfessor()+"</td></tr></table></td>");}                
             else {
             out.println("<table border='1px'><tr><td>Recommended:</td></tr><tr><td>Course#:</td><td>Professor:</td></tr><tr><td>"); 
             out.println( b.getCourse()+"</td><td>"+b.getProfessor()+"</td></tr></table></td>");} %>  
    </tr>
</table>
</body>
</html>
