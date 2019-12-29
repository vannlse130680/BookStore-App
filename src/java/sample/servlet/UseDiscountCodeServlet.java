/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_discount.Tbl_DiscountDAO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "UseDiscountCodeServlet", urlPatterns = {"/UseDiscountCodeServlet"})
public class UseDiscountCodeServlet extends HttpServlet {
private static final String VIEW_CART ="viewCart.jsp";
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
        String code = request.getParameter("txtDCode");
        String url = VIEW_CART;
        try {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("USERID");
            Tbl_DiscountDAO dao = new Tbl_DiscountDAO();
            boolean isValidCode = dao.isDiscountCode(code, userId);
            
            
            if(isValidCode) {
                int percent = dao.getPercentDiscount(code);
                session.setAttribute("VALIDCODE", code);
                
                session.setAttribute("PERCENT", percent);
            } else {
                session.removeAttribute("VALIDCODE");
                session.removeAttribute("PERCENT");
                request.setAttribute("INVALIDCODE", code);
            }
        } catch (SQLException ex) {
        log("UseDiscountCodeServlet _ SQLException:" + ex.getMessage());
    } catch (NamingException ex) {
        log("UseDiscountCodeServlet _ NamingException:" + ex.getMessage());
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
