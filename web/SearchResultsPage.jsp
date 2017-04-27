<%-- 
    Document   : SearchResults.get(i)Page
    Created on : Apr 17, 2017, 6:32:38 PM
    Author     : Bookstore
--%>

<%@page import="Objects.SearchResults"%>
<%@page import="Objects.Book"%>
<%@page import="Objects.Search"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
        <style type ="text/css" media="screen"><!--
                        #footer
            {
                font-size: 11px;
                font-family: Verdana, Geneva, Arial, sans-serif;
                text-align: center;
                position: absolute;
                bottom: 0px;
                left: 0px;
                width: 100%;
                height: 20px;
                visibility: visible;
                display: block
            }
            p {
    margin-left: 80px;
    }
        --></style>
    </head>
    <body>        
        <%@page import = "java.util.*" %>
        <%@page import = "java.io.File" %>
        <table width ="100%">
            <tr>
                <td width="75%"><a href="index.jsp"><img src="image/images/ksu (1).png" width="30%" height="100" alt="Kennesaw Logo"></a>
                </td>
                <td width="25%">
                    <a href="shoppingCart.jsp"><img src="image/images/cart.png" alt="cart" width="80" height="80"/></a>
                </td>
            </tr>
        </table>
        <h3 align="center"><strong>
        Search Results
        </strong></h3> 
        <br><br>
        <%
            //TEST DATA
        long isbn = 9780073376356L;
        String authors[] = new String[2];
        authors[0] = "Jesse Pinkman";
        authors[1] = "Bugs Bunny";
        Book test1 = new Book();
        test1.setIsbn(isbn);
        test1.setBookName("	Intro to Java Programming, Comp Version Plus MyProgrammmingLab with Pearson eText- Access Card Package");
        test1.setAuthor(authors);
        test1.setSemester("Fall");
        test1.setCourse("SWE 3313");
        test1.setSection(61);
        test1.setProfessor("Lartigue");
        test1.setCrn(22456);
        test1.setNewQuantity(30);
        test1.setUsedQuantity(10);
        test1.setRentalQuantity(20);
        test1.setEbookQuantity(999999);
        test1.setNewPrice(123.12);
        test1.setUsedPrice(70.31);
        test1.setRentalPrice(55.31);
        test1.setEbookPrice(67.93);
        test1.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol Apparently also SWE 3313 lol whatever really loooooong history about how chemicals interact with each other in weird ways like wtf is up with fire??  the oxidization of materials really quickly usually involves fire leaving just only the carbon behind like some kind of object skeleton amirite?");
        test1.setBuyDemand("Required");
        Book test2 = new Book();
        test2.setIsbn(isbn);
        test2.setBookName("Chemistry");
        test2.setAuthor(authors);
        test2.setSemester("Fall");
        test2.setCourse("SWE 3313");
        test2.setSection(61);
        test2.setProfessor("Lartigue");
        test2.setCrn(22456);
        test2.setNewQuantity(30);
        test2.setUsedQuantity(10);
        test2.setRentalQuantity(20);
        test2.setEbookQuantity(999999);
        test2.setNewPrice(123.12);
        test2.setUsedPrice(70.31);
        test2.setRentalPrice(55.31);
        test2.setEbookPrice(67.93);
        test2.setDescription("This is a textbook for Chemistry!");
        test2.setBuyDemand("Recommended");
        Book test3 = new Book();
        test3.setIsbn(isbn);
        test3.setBookName("Chemistry");
        test3.setAuthor(authors);
        test3.setSemester("fall");
        test3.setCourse("SWE 3313");
        test3.setSection(61);
        test3.setProfessor("Lartigue");
        test3.setCrn(22456);
        test3.setNewQuantity(30);
        test3.setUsedQuantity(10);
        test3.setRentalQuantity(20);
        test3.setEbookQuantity(999999);
        test3.setNewPrice(123.12);
        test3.setUsedPrice(70.31);
        test3.setRentalPrice(55.31);
        test3.setEbookPrice(67.93);
        test3.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        
                Book test4 = new Book();
        test4.setIsbn(isbn);
        test4.setBookName("Chemistry");
        test4.setAuthor(authors);
        test4.setSemester("fall");
        test4.setCourse("SWE 3313");
        test4.setSection(61);
        test4.setProfessor("Lartigue");
        test4.setCrn(22456);
        test4.setNewQuantity(30);
        test4.setUsedQuantity(10);
        test4.setRentalQuantity(20);
        test4.setEbookQuantity(999999);
        test4.setNewPrice(123.12);
        test4.setUsedPrice(70.31);
        test4.setRentalPrice(55.31);
        test4.setEbookPrice(67.93);
        test4.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        Book test5 = new Book();
        test5.setIsbn(isbn);
        test5.setBookName("Chemistry");
        test5.setAuthor(authors);
        test5.setSemester("fall");
        test5.setCourse("SWE 3313");
        test5.setSection(61);
        test5.setProfessor("Lartigue");
        test5.setCrn(22456);
        test5.setNewQuantity(30);
        test5.setUsedQuantity(10);
        test5.setRentalQuantity(20);
        test5.setEbookQuantity(999999);
        test5.setNewPrice(123.12);
        test5.setUsedPrice(70.31);
        test5.setRentalPrice(55.31);
        test5.setEbookPrice(67.93);
        test5.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        Book test6 = new Book();
        test6.setIsbn(isbn);
        test6.setBookName("Chemistry");
        test6.setAuthor(authors);
        test6.setSemester("fall");
        test6.setCourse("SWE 3313");
        test6.setSection(61);
        test6.setProfessor("Lartigue");
        test6.setCrn(22456);
        test6.setNewQuantity(30);
        test6.setUsedQuantity(10);
        test6.setRentalQuantity(20);
        test6.setEbookQuantity(999999);
        test6.setNewPrice(123.12);
        test6.setUsedPrice(70.31);
        test6.setRentalPrice(55.31);
        test6.setEbookPrice(67.93);
        test6.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        Book test7 = new Book();
        test7.setIsbn(isbn);
        test7.setBookName("Chemistry");
        test7.setAuthor(authors);
        test7.setSemester("fall");
        test7.setCourse("SWE 3313");
        test7.setSection(61);
        test7.setProfessor("Lartigue");
        test7.setCrn(22456);
        test7.setNewQuantity(30);
        test7.setUsedQuantity(10);
        test7.setRentalQuantity(20);
        test7.setEbookQuantity(999999);
        test7.setNewPrice(123.12);
        test7.setUsedPrice(70.31);
        test7.setRentalPrice(55.31);
        test7.setEbookPrice(67.93);
        test7.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        Book test8 = new Book();
        test8.setIsbn(isbn);
        test8.setBookName("Chemistry");
        test8.setAuthor(authors);
        test8.setSemester("fall");
        test8.setCourse("SWE 3313");
        test8.setSection(61);
        test8.setProfessor("Lartigue");
        test8.setCrn(22456);
        test8.setNewQuantity(30);
        test8.setUsedQuantity(10);
        test8.setRentalQuantity(20);
        test8.setEbookQuantity(999999);
        test8.setNewPrice(123.12);
        test8.setUsedPrice(70.31);
        test8.setRentalPrice(55.31);
        test8.setEbookPrice(67.93);
        test8.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        Book test9 = new Book();
        test9.setIsbn(isbn);
        test9.setBookName("Chemistry");
        test9.setAuthor(authors);
        test9.setSemester("fall");
        test9.setCourse("SWE 3313");
        test9.setSection(61);
        test9.setProfessor("Lartigue");
        test9.setCrn(22456);
        test9.setNewQuantity(30);
        test9.setUsedQuantity(10);
        test9.setRentalQuantity(20);
        test9.setEbookQuantity(999999);
        test9.setNewPrice(123.12);
        test9.setUsedPrice(70.31);
        test9.setRentalPrice(55.31);
        test9.setEbookPrice(67.93);
        test9.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        Book test10 = new Book();
        test10.setIsbn(isbn);
        test10.setBookName("Chemistry");
        test10.setAuthor(authors);
        test10.setSemester("fall");
        test10.setCourse("SWE 3313");
        test10.setSection(61);
        test10.setProfessor("Lartigue");
        test10.setCrn(22456);
        test10.setNewQuantity(30);
        test10.setUsedQuantity(10);
        test10.setRentalQuantity(20);
        test10.setEbookQuantity(999999);
        test10.setNewPrice(123.12);
        test10.setUsedPrice(70.31);
        test10.setRentalPrice(55.31);
        test10.setEbookPrice(67.93);
        test10.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        Book test11 = new Book();
        test11.setIsbn(isbn);
        test11.setBookName("Science");
        test11.setAuthor(authors);
        test11.setSemester("fall");
        test11.setCourse("SWE 3313");
        test11.setSection(61);
        test11.setProfessor("Lartigue");
        test11.setCrn(22456);
        test11.setNewQuantity(30);
        test11.setUsedQuantity(10);
        test11.setRentalQuantity(20);
        test11.setEbookQuantity(999999);
        test11.setNewPrice(123.12);
        test11.setUsedPrice(70.31);
        test11.setRentalPrice(55.31);
        test11.setEbookPrice(67.93);
        test11.setDescription("This is a textbook for Chemistry! Apparently also SWE 3313 lol");
        
        ArrayList<Book> Searchlist = new ArrayList();
        Searchlist.add(test1);
        Searchlist.add(test2);
        Searchlist.add(test3);
        Searchlist.add(test4);
        Searchlist.add(test5);
        Searchlist.add(test6);
        Searchlist.add(test7);
        Searchlist.add(test8);
        Searchlist.add(test9);
        Searchlist.add(test10);
        Searchlist.add(test11);
        
        //grabbing the search results from the session
        ArrayList<Book> r = (ArrayList<Book>)request.getSession().getAttribute("results");
        SearchResults sr;
        if(r != null && r.size() > 0){//making sure it is not null
            sr= new SearchResults(r);
            String searchType = (String)request.getSession().getAttribute("SearchType");
            int st = Integer.parseInt(searchType);//checking to see how to SORT the results
            switch(st){
                case 0:
                    sr.sortByTitle();
                    break;
                case 1:
                    sr.sortByCourse();
                    break;
                case 2:
                    sr.sortByProfessor();
                    break;
                default:
                    sr.sortByTitle();
                    break;
            }
        }
        else{
            sr = new SearchResults();}//if there are no results initializing a null class.
        
        
        
        String index = request.getParameter("search");//stores what page# for browsing the search results
        int indexNum =0;
        int pages;
        int upperBound = 0;
        pages = sr.getSize()/10;
        if(sr.getSize() % 10 != 0)
            pages++;
        
        //Checks to see if this is the first page of search results or not.
        //Then makes sure the correct page is loaded before constructing the page.
        if(index != null && Integer.parseInt(index)>0){
        indexNum= Integer.parseInt(index)-1;
        if(indexNum*10+10>sr.getSize())
            upperBound = sr.getSize();
        else
            upperBound = indexNum*10+10;
        for(int i= (indexNum*10); i< upperBound;i++){
            String isbn1;
            String isbn2;
            String ISBN = String.valueOf(sr.getResults().get(i).getIsbn());
            isbn1 = ISBN.substring(0, 3);
            isbn2 = ISBN.substring(3, ISBN.length());
            //adds each book found to the session.
            request.getSession().setAttribute("result"+i, sr.getResults().get(i)); %>
            <table align="center" style="margin: 0px auto;" border="5px">
            <td width="133px">
                                     
                <%-- IMAGE --%>
                <a href="BookDetails.jsp?get=<%out.println(i);%>">
                <img style="display: block" src="image/images/<%out.println(isbn1+"-"+isbn2);%>.jpg" height="200" width="133" />
                </a>
                
                
            </td>
            <td style="width: 501px">
                <table border="1px">
                    <tr>
                        <td style="width: 500px">
                            <%-- TITLE GOES HERE. --%>
                            <a href="BookDetails.jsp?get=<%out.println(i);%>">
                            <% out.println(sr.getResults().get(i).getBookName()); %>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- AUTHOR GOES HERE. --%>
                            <% for(int j = 0; j < sr.getResults().get(i).getAuthor().length; j++){
                                out.println(sr.getResults().get(i).getAuthor()[j]); %> <br> <% }%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- CLASS COURSE# --%>
                            <% out.println(sr.getResults().get(i).getCourse()+"<br>"); %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%out.println(isbn1+"-"+isbn2);%>
                        </td>                                
                    </tr> 
                </table>
            </td>
            <td style="width: 500px">
                <%-- DESCRIPTION --%>
                <% out.println(sr.getResults().get(i).getDescription()); %>
            </td>
            <td>
                <table border="1px">
                    <tr>
                        <td>
                            NEW
                        </td>
                        <td>
                            USED
                        </td>
                        <td>
                            RENTAL
                        </td>
                        <td>
                            EBOOK
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- NEW PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getNewPrice());%>
                        </td>
                        <td>
                            <%-- USED PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getUsedPrice());%>
                        </td>
                        <td>
                            <%-- RENTAL PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getRentalPrice());%>
                        </td>
                        <td>
                            <%-- EBOOK PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getEbookPrice());%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- NEW Quantity --%>
                            <% out.println(sr.getResults().get(i).getNewQuantity());%>
                        </td>
                        <td>
                             <%-- USED Quantity --%>
                             <% out.println(sr.getResults().get(i).getUsedQuantity());%>
                        </td>
                        <td>
                             <%-- RENTAL Quantity --%>
                             <% out.println(sr.getResults().get(i).getRentalQuantity());%>
                        </td>
                        <td>
                             <%-- Ebook availability --%>
                             <% if(sr.getResults().get(i).getEbookPrice()>1)
                                 out.println("Yes");
                                else
                                 out.println("No");%>
                        </td>
                    </tr>
                </table>
            </td>
        </table>
            <%}}
        else if(sr.getSize()>0){
            indexNum =0;
                if(sr.getSize()<10)
                upperBound = sr.getSize();
                else
                upperBound = 10;
        for(int i=0; i<upperBound;i++){
            String isbn1;
            String isbn2;
            String ISBN = String.valueOf(sr.getResults().get(i).getIsbn());
            isbn1 = ISBN.substring(0, 3);
            isbn2 = ISBN.substring(3, ISBN.length());
        //adds each book found to the session.
            request.getSession().setAttribute("result"+i, sr.getResults().get(i));
        %>
        
        <%-- RESULT ENTRY TABLE --%>
        <table align="center" style="margin: 0px auto;" border="5px">
            <td width="133px">
                                     
                <%-- IMAGE --%>
                <a href="BookDetails.jsp?get=<%out.println(i);%>">
                <img style="display: block" src="image/images/<%out.println(isbn1+"-"+isbn2);%>.jpg" height="200" width="133" />
                </a>
                
                
            </td>
            <td style="width: 501px">
                <%-- BOOK INFO --%>
                <table border="1px">
                    <tr>
                        <td style="width: 500px">
                            <%-- TITLE GOES HERE. --%>
                            <a href="BookDetails.jsp?get=<%out.println(i);%>">
                            <% out.println(sr.getResults().get(i).getBookName()); %>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- AUTHOR GOES HERE. --%>
                            <% for(int j = 0; j < sr.getResults().get(i).getAuthor().length; j++){
                                out.println(sr.getResults().get(i).getAuthor()[j]); %> <br> <% }%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- CLASS COURSE# --%>
                            <% out.println(sr.getResults().get(i).getCourse()+"<br>"); %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%out.println(isbn1+"-"+isbn2);%>
                        </td>                                
                    </tr> 
                </table>
            </td>
            <td style="width: 500px">
                <%-- DESCRIPTION CELL --%>
                <% out.println(sr.getResults().get(i).getDescription()); %>
            </td>
            <td>
                <%-- PRICING TABLE --%>
                <table border="1px">
                    <tr>
                        <td>
                            NEW
                        </td>
                        <td>
                            USED
                        </td>
                        <td>
                            RENTAL
                        </td>
                        <td>
                            EBOOK
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- NEW PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getNewPrice());%>
                        </td>
                        <td>
                            <%-- USED PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getUsedPrice());%>
                        </td>
                        <td>
                            <%-- RENTAL PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getRentalPrice());%>
                        </td>
                        <td>
                            <%-- EBOOK PRICE $$ --%>
                            <% out.println("$"+sr.getResults().get(i).getEbookPrice());%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <%-- NEW Quantity --%>
                            <% out.println(sr.getResults().get(i).getNewQuantity());%>
                        </td>
                        <td>
                             <%-- USED Quantity --%>
                             <% out.println(sr.getResults().get(i).getUsedQuantity());%>
                        </td>
                        <td>
                             <%-- RENTAL Quantity --%>
                             <% out.println(sr.getResults().get(i).getRentalQuantity());%>
                        </td>
                        <td>
                             <%-- Ebook availability --%>
                             <% if(sr.getResults().get(i).getEbookPrice()>1)
                                 out.println("Yes");
                                else
                                 out.println("No");%>
                        </td>
                    </tr>
                </table>
            </td>
        </table>
        <% }} %>
        
        
        <%--SEARCH PAGE NAVIGATION --%>
        <% if(sr.getSize()<1){
            out.println("No results found, please try again.");  
        }
        else if (sr.getSize()>10){
            out.println("Displaying results "+(indexNum*10+1));
            if(indexNum*10+10 > sr.getSize())
                out.println("-"+sr.getSize()+" of "+sr.getSize());
            else
                out.println("-"+(indexNum*10+10)+" of "+sr.getSize());
        }
        else{
            out.println("displaying results 1-"+sr.getSize()+" of"+sr.getSize() ); }%>
            <br>
            <% if (indexNum >=1)
            out.println("<a href="+"SearchResultsPage.jsp?search="+(indexNum-1)+">"+"PREVIOUS"+"</a>"); %>
            
            <% for(int i = 1; i <= pages; i++){ %>
                <a href="SearchResultsPage.jsp?search=<%out.println(i);%>">
                    <% if(i==indexNum+1)
                         out.println("["+i+"]");
                    else
                        out.println(i);
                    %>
                </a>
            <% }%>
        
        
    </body>
</html>
