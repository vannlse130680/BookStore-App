/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.cart.Book;
import sample.cart.CartObj;
import sample.tbl_book.Tbl_BookDAO;
import sample.tbl_discount.Tbl_DiscountDAO;
import sample.tbl_order.Tbl_OrderDAO;
import sample.tbl_orderdetail.Tbl_OrderDetailDAO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "ConfirmServlet", urlPatterns = {"/ConfirmServlet"})
public class ConfirmServlet extends HttpServlet {

    private static final String SEARCH_SERVLET = "SearchServlet";
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
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String url = SEARCH_SERVLET;
        try {
            Tbl_OrderDAO orderDao = new Tbl_OrderDAO();
            HttpSession session = request.getSession(false);
            if (session != null) {
                String userId = (String) session.getAttribute("USERID");
                String code = (String) session.getAttribute("VALIDCODE");
                if (code == null) {
                    code = "";
                }
                float total = Float.parseFloat(request.getParameter("txtTotal"));

                int row = orderDao.getRowTable();
                int orderId = ++row;

                CartObj cart = (CartObj) session.getAttribute("CART");

                if (cart != null) {

                    Map<String, Book> items = cart.getItems();
                    if (items != null) {
                        String outOfStock = "";
                        for (Map.Entry item : items.entrySet()) {

                            Tbl_BookDAO bookDao = new Tbl_BookDAO();
                            int quantity = bookDao.getQuantity((String) item.getKey());
                            int amount = ((Book) (item.getValue())).getAmount();
                            if (amount > quantity) {
                                outOfStock = outOfStock + ((Book) (item.getValue())).getTitle() + " available: " + quantity + "\\n";

                            }

                        }
                        if (outOfStock.isEmpty()) {
                            orderDao.insertOrder(orderId, userId, date.toString(), total, code);
                            for (Map.Entry item : items.entrySet()) {
                                Tbl_BookDAO bookDao = new Tbl_BookDAO();
                                Tbl_OrderDetailDAO detailDao = new Tbl_OrderDetailDAO();
                                int quantity = bookDao.getQuantity((String) item.getKey());
                                int amount = ((Book) (item.getValue())).getAmount();
                                detailDao.insertOrderDetail(orderId, (String) item.getKey(), ((Book) (item.getValue())).getAmount(), ((Book) (item.getValue())).getTotal());
                                bookDao.updateQuantity((String) item.getKey(), quantity - amount);
                            }
                            Tbl_DiscountDAO discountDao = new Tbl_DiscountDAO();
                            discountDao.updateCode(code, userId);
                            session.removeAttribute("VALIDCODE");
                            session.removeAttribute("PERCENT");
                            session.removeAttribute("CART");
                        } else {
                            request.setAttribute("OUTOFSTOCK", "Out of stock: \\n" + outOfStock);
                            url = VIEW_CART;
                        }

                    }

                }
            }

        } catch (NamingException ex) {
            log("ConfirmServlet _ NamingException:" + ex.getMessage());
        } catch (SQLException ex) {
            log("ConfirmServlet _ SQLException:" + ex.getMessage());
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
