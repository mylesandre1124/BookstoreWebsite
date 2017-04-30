package Servlet;

import Objects.ShoppingCart;
import Objects.Student;
import Objects.StudentsDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Myles on 4/27/17.
 */
@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        StudentsDatabase students = new StudentsDatabase();
        Student student = students.login(username, password);
        HttpSession session = request.getSession();
        //ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        PrintWriter out = response.getWriter();
        if(student != null)
        {
            session.setAttribute("student", student);
            try {
                session.setAttribute("loggedIn", true);
                //ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                //double price = Double.parseDouble(cart.getTotalPrice());
                double price = 600.0;
                if (student.getAidAmount() >= price)
                {
                    out.println(student.getFirstName());
                    out.println(student.getAidAmount());
                    out.println("Enough");
                }
                else {
                    session.setAttribute("finaid", false);
                    response.sendRedirect("http://localhost:8080/Login.jsp");
                }
            }
            catch (NullPointerException ex)
            {}
        }
        else {
            session.setAttribute("loggedIn", false);
            response.sendRedirect("http://localhost:8080/Login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
