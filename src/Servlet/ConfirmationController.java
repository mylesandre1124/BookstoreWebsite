package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Myles on 4/29/17.
 */
@WebServlet(name = "ConfirmationController", urlPatterns = "/ConfirmationController")
public class ConfirmationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int buttonType = 0;
        try{
            buttonType = (int)request.getAttribute("buttonType");
            switch (buttonType)
            {
                case 1:
                    response.sendRedirect("shoppingCart.jsp");
                    break;
            }
        }
        catch (NullPointerException ex)
        {
            response.getWriter().println(buttonType);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
