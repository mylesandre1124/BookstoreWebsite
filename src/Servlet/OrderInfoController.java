package Servlet;

import Objects.OrderInfoHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Bookstore on 4/23/2017.
 */
@WebServlet(name = "OrderInfoController", urlPatterns = {"/OrderInfoController"})
public class OrderInfoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String strAction = request.getParameter("orderInfoSubmit");
        OrderInfoHandler orderInfo = new OrderInfoHandler();

        if(strAction != null && !strAction.equals("")) {
            String shippingName = request.getParameter("shippingName");
            String shippingAddress1 = request.getParameter("shippingAddress");
            String shippingAddress2 = request.getParameter("shippingAddressLine2");
            String shippingCity = request.getParameter("shippingCity");
            String shippingState = request.getParameter("shippingState");
            String strShippingZip = request.getParameter("shippingZip");
            int intShippingZip = Integer.parseInt(strShippingZip);
            if (request.getParameter("shippingAsBilling").equals("yes")) {
                orderInfo.setShippingName(shippingName);
                orderInfo.setShippingAddress(shippingAddress1);
                orderInfo.setShippingAddressLine2(shippingAddress2);
                orderInfo.setShippingCity(shippingCity);
                orderInfo.setShippingState(shippingState);
                orderInfo.setShippingZip(intShippingZip);
                orderInfo.shippingAsBilling();
            } else {
                String billingName = request.getParameter("billingName");
                String billingAddress1 = request.getParameter("billingAddress");
                String billingAddress2 = request.getParameter("billingAddressLine2");
                String billingCity = request.getParameter("billingCity");
                String billingState = request.getParameter("billingState");
                String strBillingZip = request.getParameter("billingZip");
                int intBillingZip = Integer.parseInt(strBillingZip);
                orderInfo.setBillingName(billingName);
                orderInfo.setBillingAddress(billingAddress1);
                orderInfo.setBillingAddressLine2(billingAddress2);
                orderInfo.setBillingCity(billingCity);
                orderInfo.setBillingState(billingState);
                orderInfo.setBillingZip(intBillingZip);
            }
            session.setAttribute("orderInfo", orderInfo); //posts the new cart to the session
            response.sendRedirect("http://localhost:8080/paymentInfo.jsp"); //reloads the Shopping Cart page
            return;
        }
        session.setAttribute("orderInfo", orderInfo); //posts the new cart to the session
        response.sendRedirect("http://localhost:8080/paymentInfo.jsp"); //reloads the Shopping Cart page
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
