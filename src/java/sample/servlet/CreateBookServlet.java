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
import sample.error.CreateBookError;
import sample.tbl_book.Tbl_BookDAO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "CreateBookServlet", urlPatterns = {"/CreateBookServlet"})
public class CreateBookServlet extends HttpServlet {

    private static final String ERROR_PAGE = "createBook.jsp";
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
        String bookId = request.getParameter("txtBookId");
        String title = request.getParameter("txtTitle");
        String author = request.getParameter("txtAuthor");
        String categoryId = request.getParameter("categoryName");
        String quantity = request.getParameter("txtQuantity");
        String description = request.getParameter("txtDescription");
        String image = request.getParameter("source");
        String price = request.getParameter("txtPrice");
        String action = request.getParameter("btAction");
        String url = ERROR_PAGE;
        try {
            if (action.equals("Create")) {
                float priceNum = -1;
                int quantityNum = -1;
                boolean findError = false;
                CreateBookError error = new CreateBookError();
                Tbl_BookDAO dao = new Tbl_BookDAO();
                if (bookId.trim().isEmpty()) {
                    findError = true;
                    error.setBookId("Please enter book ID!");
                } else {
                    if (dao.isDuplicateBookId(bookId)) {
                        findError = true;
                        error.setBookId("This bookid is duplicated!");
                    }
                }
                if (title.trim().isEmpty()) {
                    findError = true;
                    error.setTitle("Please enter book title!");
                }
                if (author.trim().isEmpty()) {
                    findError = true;
                    error.setAuhtor("Please enter book author!");
                }
                try {
                    quantityNum = Integer.parseInt(quantity);
                    if (quantityNum <= 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    findError = true;
                    error.setQuantity("Please enter book quantity!");
                }
                try {
                    priceNum = Float.parseFloat(price);
                    if (priceNum <= 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    findError = true;
                    error.setPrice("Invalid value of price !");

                }
                if (image.trim().isEmpty()) {

                    findError = true;
                    error.setImage("Pleaes choose an image !");
                } else if (!image.substring(image.lastIndexOf("\\")).contains(".jpg") && !image.substring(image.lastIndexOf("\\")).contains(".png") && !image.substring(image.lastIndexOf("\\")).contains(".gif")) {
                    findError = true;
                    error.setImage("This file is not support");
                }
                if (description.trim().isEmpty()) {

                    findError = true;
                    error.setDescription("Pleaes enter description!");
                }
                if (findError) {
                    request.setAttribute("CREATEERROR", error);
                } else {
                    long millis = System.currentTimeMillis();
                    java.sql.Date date = new java.sql.Date(millis);
                    boolean result = dao.insertBook(bookId, title, image, priceNum, author, categoryId, quantityNum, description, date.toString());
                    if (result) {
                        url = SEARCH_SERVLET;
                    }
                }
            } else {
                url = SEARCH_SERVLET;
            }
        } catch (SQLException ex) {
            log("CreateBookServlet _ SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("CreateBookServlet _ NamingException:" + ex.getMessage());
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
