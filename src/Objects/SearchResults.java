package Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bookstore
 */
import static java.lang.System.out;
import java.util.*;
//import static org.apache.taglibs.standard.tag.common.core.OutSupport.out;

public class SearchResults {
    ArrayList<Book> Results;
    Book Result;          //When searching by ISBN there should only be one result.
    
    public SearchResults(){
    }
    
    public SearchResults(ArrayList<Book> results){
        Results = results;
    }
    
    public SearchResults(Book result){
        Result = result;
    }
    
    public ArrayList<Book> getResults(){
        return Results;
    }
    
    public Book getResult(){
        return Result;
    }
    
    public void sortByTitle(){
    Collections.sort(Results, (Book b1, Book b2) -> b1.getBookName().compareTo(b2.getBookName()));
    }
    
    public int getSize(){
        return this.getResults().size();
    }
    
    //notworking
    public void buildABookTable(Book b){
        out.println("<table border=\"5px\">\n" +
"            <td width=\"133px\">\n" +
"                                     \n" +
"                <%-- IMAGE --%>\n" +
"                <a href=\"BookDetails.jsp?get=<%out.println(i);%>\">\n" +
"                <img style=\"display: block\" src=\"images/<%out.println(isbn1+\"-\"+isbn2);%>.jpg\" height=\"200\" width=\"133\" />\n" +
"                </a>\n" +
"                \n" +
"                \n" +
"            </td>\n" +
"            <td style=\"width: 501px\">\n" +
"                <table border=\"1px\">\n" +
"                    <tr>\n" +
"                        <td style=\"width: 500px\">\n" +
"                            <%-- TITLE GOES HERE. --%>\n" +
"                            <a href=\"BookDetails.jsp?get=<%out.println(i);%>\">\n" +
"                            <% out.println(sr.getResults().get(i).getBookName()); %>\n" +
"                            </a>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <%-- AUTHOR GOES HERE. --%>\n" +
"                            <% for(int j = 0; j < sr.getResults().get(i).getAuthor().length; j++){\n" +
"                                out.println(sr.getResults().get(i).getAuthor()[j]); %> <br> <% }%>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <%-- CLASS COURSE# --%>\n" +
"                            <% out.println(sr.getResults().get(i).getCourse()+\"<br>\"); %>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <%out.println(isbn1+\"-\"+isbn2);%>\n" +
"                        </td>                                \n" +
"                    </tr> \n" +
"                </table>\n" +
"            </td>\n" +
"            <td style=\"width: 500px\">\n" +
"                <%-- DESCRIPTION --%>\n" +
"                <% out.println(sr.getResults().get(i).getDescription()); %>\n" +
"            </td>\n" +
"            <td>\n" +
"                <table border=\"1px\">\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            NEW\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            USED\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            RENTAL\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            EBOOK\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <%-- NEW PRICE $$ --%>\n" +
"                            <% out.println(\"$\"+sr.getResults().get(i).getNewPrice());%>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <%-- USED PRICE $$ --%>\n" +
"                            <% out.println(\"$\"+sr.getResults().get(i).getUsedPrice());%>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <%-- RENTAL PRICE $$ --%>\n" +
"                            <% out.println(\"$\"+sr.getResults().get(i).getRentalPrice());%>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <%-- EBOOK PRICE $$ --%>\n" +
"                            <% out.println(\"$\"+sr.getResults().get(i).getEbookPrice());%>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <%-- NEW Quantity --%>\n" +
"                            <% out.println(sr.getResults().get(i).getNewQuantity());%>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                             <%-- USED Quantity --%>\n" +
"                             <% out.println(sr.getResults().get(i).getUsedQuantity());%>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                             <%-- RENTAL Quantity --%>\n" +
"                             <% out.println(sr.getResults().get(i).getRentalQuantity());%>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                             <%-- Ebook availability --%>\n" +
"                             <% if(sr.getResults().get(i).getEbookPrice()>1)\n" +
"                                 out.println(\"Yes\");\n" +
"                                else\n" +
"                                 out.println(\"No\");%>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </td>\n" +
"        </table>");
    }
}
