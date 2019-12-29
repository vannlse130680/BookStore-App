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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.error.UpdateError;
import sample.tbl_book.Tbl_BookDAO;
import sample.tbl_category.Tbl_CategoryDAO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "UpdateBookServlet", urlPatterns = {"/UpdateBookServlet"})
public class UpdateBookServlet extends HttpServlet {

    private static final String SEARCH_SERVLET = "SearchServlet";
    private static final String ERROR_PAGE = "updateBook.jsp";

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
        String title = request.getParameter("txtTitle");
        String price = request.getParameter("txtPrice");
        String author = request.getParameter("txtAuthor");
        String category = request.getParameter("categoryName");
        String importDate = request.getParameter("txtImportDate");
        String quantity = request.getParameter("txtQuantity");
        String bookId = request.getParameter("txtBookId");
        String url = ERROR_PAGE;
        String btAction = request.getParameter("btAction");
        try {
            if (btAction.equals("Update")) {
                float priceNum = -1;
                int quantityNum = -1;
                boolean findError = false;
                UpdateError error = new UpdateError();
                if (title.trim().isEmpty()) {
                    findError = true;
                    error.setTitle("Please enter book title!");
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
                if (author.trim().isEmpty()) {
                    findError = true;
                    error.setAuthor("Please enter author !");
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                try {
                    Date date = (Date) sdf.parse(importDate);
                } catch (ParseException ex) {
                    findError = true;
                    error.setImportDate("Invalid import date !");
                }
                try {
                    quantityNum = Integer.parseInt(quantity);
                    if(quantityNum <= 0) throw  new Exception();
                } catch (Exception e) {
                    findError = true;
                    error.setQuantity("Invalid value of quantity!");
                }

                if (findError) {
                    request.setAttribute("UPDATEERRORS", error);
                    
                } else {
                    url = SEARCH_SERVLET;
                    Tbl_CategoryDAO categoryDao = new Tbl_CategoryDAO();
                    Tbl_BookDAO bookDao = new Tbl_BookDAO();
                    String categoryId = categoryDao.getCategoryId(category);
                    bookDao.updateBook(bookId, title, priceNum, author, categoryId, importDate, quantityNum);

                }
            } else {
                url = SEARCH_SERVLET;
            }

        } catch (SQLException ex) {
            log("UpdateBookServlet _ SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateBookServlet _ NamingException:" + ex.getMessage());
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
