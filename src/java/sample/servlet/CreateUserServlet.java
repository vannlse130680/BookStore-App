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
import sample.error.CreateUserError;
import sample.tbl_user.Tbl_UserDAO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "CreateUserServlet", urlPatterns = {"/CreateUserServlet"})
public class CreateUserServlet extends HttpServlet {

    private static final String ERROR_PAGE = "createUser.jsp";
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
        String userId = request.getParameter("txtUserId");
        String password = request.getParameter("txtPassword");
        String conPassword = request.getParameter("txtConPassword");
        String fullName = request.getParameter("txtFullName");
        String address = request.getParameter("txtAddress");
        String phone = request.getParameter("txtPhone");
        String url = ERROR_PAGE;
        String action = request.getParameter("btAction");
        String phoneFormat = "\\d{10,15}";
        try {
            if (action.equals("Create")) {
                boolean findError = false;
                CreateUserError errors = new CreateUserError();
                Tbl_UserDAO dao = new Tbl_UserDAO();
                if (userId.trim().isEmpty()) {
                    findError = true;
                    errors.setUserId("Please enter userID !");
                } else {

                    if (dao.isDuplicateUserId(userId)) {
                        findError = true;
                        errors.setUserId("This userID is duplicated !");
                    }
                }
                if (password.trim().isEmpty()) {
                    findError = true;
                    errors.setPassword("Please enter password !");
                } else if (!password.equals(conPassword)) {
                    findError = true;
                    errors.setConPassword("Cofirm must match password !");
                }
                if (fullName.trim().isEmpty()) {
                    findError = true;
                    errors.setFullName("Please enter full name !");
                }
                if (address.trim().isEmpty()) {
                    findError = true;
                    errors.setAddress("Please enter address !");
                }
                if (!phone.trim().matches(phoneFormat)) {
                    findError = true;
                    errors.setPhone("Incorrect phone number format (10-15)");
                }

                if (findError) {
                    // catching errors
                    request.setAttribute("CREATEERROR", errors);
                } else {

                    boolean result = dao.insertUser(userId, password, fullName, address, phone);

                    if (result) {

                        url = SEARCH_SERVLET;
                    }
                }
            } else {
                url = SEARCH_SERVLET;
            }

        } catch (SQLException ex) {
            log("CreateUserServlet _ SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateUserServlet _ NamingException:" + ex.getMessage());
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
