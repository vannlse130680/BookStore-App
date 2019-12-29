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
import sample.tbl_discount.Tbl_DiscountDAO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "AddCodeServlet", urlPatterns = {"/AddCodeServlet"})
public class AddCodeServlet extends HttpServlet {

    private static final String ERROR_PAGE = "PreAddCodeServlet";
    private static final String SEARCH_SERVLET = "SearchServlet";

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
        String userId = request.getParameter("cbxUserId");
        String code = request.getParameter("txtCode");
        int percent = Integer.parseInt(request.getParameter("cbxPercent"));
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String action = request.getParameter("btAction");
        String url = ERROR_PAGE;
        try {
            if (action.equals("Cancel")) {
                url = SEARCH_SERVLET;
            } else {
                boolean findError = false;
                Tbl_DiscountDAO dao = new Tbl_DiscountDAO();
                if (code.trim().isEmpty()) {
                    findError = true;
                    request.setAttribute("ERROR", "Please enter discount code !");

                } else {
                    if (dao.isDuplicatedCode(code))
                    {

                        findError = true;
                        request.setAttribute("ERROR", "This code is duplicated !");
                    }

                }
                if (!findError) {
                    url = SEARCH_SERVLET;
                    dao.insertCode(code, userId, percent, date.toString());
                }
            }

        } catch (SQLException ex) {
            log("AddCodeServlet _ SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("AddCodeServlet _ NamingException:" + ex.getMessage());
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
