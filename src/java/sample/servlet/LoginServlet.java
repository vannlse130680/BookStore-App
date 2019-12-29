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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_user.Tbl_UserDAO;

/**
 *
 * @author Acer
 */
public class LoginServlet extends HttpServlet {
private static final String SEARCH_SERVLET = "search";
private static final String INVALID_PAGE = "invalid.html";

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
        String userId = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");
        String url = INVALID_PAGE;
        String fullName;
        try  {
            HttpSession session = request.getSession();
            Tbl_UserDAO dao = new Tbl_UserDAO();
            int result = dao.checkLogin(userId, password);
            
            switch(result) {
                case 1 : 
                    
                    url = SEARCH_SERVLET;                    
                    
                    fullName = dao.getFullName(userId);
                    session.setAttribute("ADMIN", fullName);
                    break;
                case 0 :
                    url = SEARCH_SERVLET;
                    fullName = dao.getFullName(userId);
                    session.setAttribute("USER", fullName);
                    session.setAttribute("USERID", userId);
                    break;
                case -1 : 
                    break;
                    
            }
        } catch (SQLException ex) {
        log("LoginServlet _ SQLException:" + ex.getMessage());
    } catch (NamingException ex) {
        log("LoginServlet _ NamingException:" + ex.getMessage());
    } finally {
            response.sendRedirect(url);
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
