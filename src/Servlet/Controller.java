package Servlet;

import Objects.Book;
import Objects.BooksDatabase;
import Objects.ImportSpreadsheet;
import Objects.Search;
import com.oracle.tools.packager.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeMap;

/**
 * Created by Myles on 4/15/17.
 */
@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/SearchResultsJSP.jsp";
        Search search = new Search();
        String input = request.getParameter("search");
        PrintWriter out = response.getWriter();
        out.println(input);
        String type = request.getParameter("type");
        if(type.equals("keyword")) {
            try {
                ArrayList<Book> books = search.populateKeywordSearch(input);
                for (int i = 0; i < books.size(); i++) {
                    out.println(books.get(i).getBookName());
                }
            }
            catch (NullPointerException ex)
            {
                out.println(ex.getMessage());
            }
        }
        else if(type.equals("course"))
        {
            ArrayList<Book> courses = search.populateCourseSearch(input);
        }
        else if(type.equals("professor"))
        {

        }


        //getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
