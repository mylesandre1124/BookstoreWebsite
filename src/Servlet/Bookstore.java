package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Objects.Book;
import Objects.ShoppingCart;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/Bookstore"})
public class Bookstore extends HttpServlet {

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Bookstore</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Bookstore at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ShoppingCart cart = new ShoppingCart();
        if(session.getAttribute("cart") != null){
            cart = (ShoppingCart)session.getAttribute("cart");
        }
        String strAction = request.getParameter("action");
        String strQty = request.getParameter("qty");
        int intQty;
        if(strQty !=null && !strQty.equals("")){
            intQty = Integer.parseInt(strQty);
        
            if (strAction != null && !strAction.equals("") && intQty > 0) {
                if (strAction.equals("buy NEW")) {
                    int intIndex = 1;
                    Book a;
                    a = (Book) session.getAttribute("book");
                    cart.addToCart(a, intQty, intIndex);
                    session.setAttribute("cart", cart);
                    response.sendRedirect("shoppingCart.jsp");
                } else if (strAction.equals("buy USED")) {
                    int intIndex = 2;
                    Book a;
                    a = (Book) session.getAttribute("book");
                    cart.addToCart(a, intQty, intIndex);
                    session.setAttribute("cart", cart);
                    response.sendRedirect("shoppingCart.jsp");
                } else if (strAction.equals("buy RENTAL")) {
                    int intIndex = 3;
                    Book a;
                    a = (Book) session.getAttribute("book");
                    cart.addToCart(a, intQty, intIndex);
                    session.setAttribute("cart", cart);
                    response.sendRedirect("shoppingCart.jsp");
                } else if (strAction.equals("buy Ebook")) {
                    int intIndex = 4;
                    Book a;
                    a = (Book) session.getAttribute("book");
                    cart.addToCart(a, intQty, intIndex);
                    session.setAttribute("cart", cart);
                    response.sendRedirect("shoppingCart.jsp");
                }
            }
        }
        else
            response.sendRedirect("http://localhost:8080/BookDetails.jsp?get="+(String)request.getSession().getAttribute("rnum"));
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
