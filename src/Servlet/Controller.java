package Servlet;

import Objects.Book;
import Objects.Search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Myles on 4/15/17.
 */
@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = "/SearchResultsPage.jsp";
        Search search = new Search();
        String input = request.getParameter("search");
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        if (type.equals("keyword")) {
            ArrayList<Book> keywords = search.populateKeywordSearch(input);
            session.setAttribute("SearchType", "0");
            session.setAttribute("results", keywords);
        } else if (type.equals("course")) {
            ArrayList<Book> courses = search.populateCourseSearch(input);
            session.setAttribute("SearchType", "1");
            session.setAttribute("results", courses);
        } else if (type.equals("professor")) {
            ArrayList<Book> professors = search.populateProfessorSearch(input);
            session.setAttribute("SearchType", "2");
            session.setAttribute("results", professors);

        }


        getServletContext().getRequestDispatcher(page).forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
