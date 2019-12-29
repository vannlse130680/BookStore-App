/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_order.History;
import sample.tbl_order.Tbl_OrderDAO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {

    private static final String VIEW_HISTORY = "viewHistory.jsp";

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
        String action = request.getParameter("btAction");
        String title = request.getParameter("txtTitle");
        String importDate = request.getParameter("txtDate");
        String userId = request.getParameter("txtUserId");
        String url = VIEW_HISTORY;
        try {
            if (action.equals("Search By Title")) {

                Tbl_OrderDAO dao = new Tbl_OrderDAO();
                dao.searchHistoryByTitle(userId, title);
                List<History> result = dao.getListHistory();
                request.setAttribute("HISTORY", result);

            } else if (action.equals("Search By Date")) {
                boolean findError = false;
                Tbl_OrderDAO dao = new Tbl_OrderDAO();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                try {
                    Date date = (Date) sdf.parse(importDate);
                } catch (ParseException ex) {
                    findError = true;

                }
                if (findError && !importDate.trim().isEmpty()) {

                    request.setAttribute("INVALIDDATE", "Invalid Date !");
                    dao.loadHistory(userId);
                    List<History> result = dao.getListHistory();
                    request.setAttribute("HISTORY", result);
                } else {
                    if (importDate.isEmpty()) {
                        dao.loadHistory(userId);
                        List<History> result = dao.getListHistory();
                        request.setAttribute("HISTORY", result);
                    } else {
                        dao.searchHistoryByDate(userId, importDate);
                        List<History> result = dao.getListHistory();
                        request.setAttribute("HISTORY", result);
                    }

                }

            }
        } catch (SQLException ex) {
            log("SearchHistoryServlet _ SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchHistoryServlet _ NamingException:" + ex.getMessage());
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
