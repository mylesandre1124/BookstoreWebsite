package Servlet;

import Objects.Book;
import Objects.Search;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
        String type = request.getParameter("type");
        if (type.equals("keyword")) {
            ArrayList<Book> keywords = search.populateKeywordSearch(input);
            printResults(keywords, out);
            request.setAttribute("results", keywords);
        } else if (type.equals("course")) {
            ArrayList<Book> courses = search.populateCourseSearch(input);
            printResults(courses, out);
            request.setAttribute("results", courses);
        } else if (type.equals("professor")) {
            ArrayList<Book> professors = search.populateProfessorSearch(input);
            printResults(professors, out);
            request.setAttribute("results", professors);

        }


        //getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    public void printResults(ArrayList<Book> books, PrintWriter out) {
        for (int i = 0; i < books.size(); i++) {
            out.println(books.get(i).getBookName());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
