/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.Book;
import sample.cart.CartObj;

/**
 *
 * @author Acer
 */
@WebServlet(name = "UpdateItemsFromCartServlet", urlPatterns = {"/UpdateItemsFromCartServlet"})
public class UpdateItemsFromCartServlet extends HttpServlet {

    private static final String VIEW_CART = "viewCart.jsp";

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
        PrintWriter out = response.getWriter();
        String url = VIEW_CART;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {

                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    try {
                        String bookId = request.getParameter("txtBookId");
                        int amount = Integer.parseInt(request.getParameter("txtAmount"));
                        if(amount == 0) {
                            cart.removeItemFromCart(bookId);
                        } 
                        if(amount < 0) throw new Exception();
                        Map<String, Book> item = cart.getItems();
                        if (item != null) {
                            if (item.containsKey(bookId)) {
                                Book b = item.get(bookId);
                                b.setAmount(amount);
                            }
                        }

                        session.setAttribute("CART", cart);
                    } catch (Exception e) {
                        request.setAttribute("INVALIDAMOUNT", "Invalid Value Of Amount");
                    }

                }
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
