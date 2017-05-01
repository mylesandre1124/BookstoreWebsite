package Servlet;

import Objects.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Myles on 4/29/17.
 */
@WebServlet(name = "ConfirmationController", urlPatterns = "/ConfirmationController")
public class ConfirmationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try{
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            Student student = (Student) session.getAttribute("student");
            OrderInfoHandler orderInfo = (OrderInfoHandler) session.getAttribute("orderInfo");
            OrderConfirmation orderConfirmation = new OrderConfirmation(cart, orderInfo, student);
            ReceiptHandler receipt = new ReceiptHandler();
            PrintWriter out = response.getWriter();
            if(student.isEmpty() || student == null)
            {
                orderConfirmation.checkout();
                out.println(receipt.toTextFile(orderConfirmation));
            }
            else if(!student.isEmpty())
            {
                orderConfirmation.studentCheckout();
                out.println(receipt.toTextFile(orderConfirmation));
            }
        }
        catch (NullPointerException ex)
        {
            response.getWriter().println();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
