/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.tbl_book.Tbl_BookDAO;
import sample.tbl_book.Tbl_BookDTO;
import sample.tbl_category.Tbl_CategoryDAO;
import sample.tbl_category.Tbl_CategoryDTO;

/**
 *
 * @author Acer
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private static final String SEARCH_PAGE = "search.jsp";
    private static final String AD_SEARCH_PAGE = "adSearch.jsp";
    private static final String USER_SEARCH_PAGE = "userSearch.jsp";

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
        String url = SEARCH_PAGE;
        String option = request.getParameter("option");
        String title = request.getParameter("txtTitle");
        String categoryName = request.getParameter("categoryName");
        String min = request.getParameter("txtMin");
        String max = request.getParameter("txtMax");
        try {
            if (option != null) {

                Tbl_BookDAO dao = null;
                List<Tbl_BookDTO> result = null;
                switch (option) {
                    case "title":
                        dao = new Tbl_BookDAO();
                        dao.searchByTitle(title);
                        result = dao.getListBook();
                        request.setAttribute("SEARCHRESULT", result);
                        request.setAttribute("SEARCHBY", option);
                        break;
                    case "category":
                        dao = new Tbl_BookDAO();
                        if (categoryName.isEmpty() || categoryName.equals("Category")) {
                            dao.searchAllBook();
                        } else {
                            dao.searchByCategory(categoryName);
                        }

                        result = dao.getListBook();
                        request.setAttribute("SEARCHRESULT", result);
                        request.setAttribute("SEARCHBY", option);
                        break;
                    case "price":
                        if (min.isEmpty() && max.isEmpty()) {
                            dao = new Tbl_BookDAO();
                            dao.searchAllBook();
                            result = dao.getListBook();
                            request.setAttribute("SEARCHBY", option);
                            request.setAttribute("SEARCHRESULT", result);
                        } else {
                            dao = new Tbl_BookDAO();
                            request.setAttribute("SEARCHBY", option);
                            try {
                                float minPrice = Float.parseFloat(min);
                                float maxPrice = Float.parseFloat(max);

                                if (minPrice > maxPrice) {
                                    throw new Exception();
                                }
                                dao.searchByPrice(minPrice, maxPrice);
                                result = dao.getListBook();
                                request.setAttribute("SEARCHRESULT", result);

                            } catch (Exception e) {
                            }
                        }

                        break;
                    case "":
                        dao = new Tbl_BookDAO();
                        dao.searchAllBook();
                        result = dao.getListBook();
                        request.setAttribute("SEARCHRESULT", result);
                        break;
                }
            } else {
                Tbl_BookDAO dao = new Tbl_BookDAO();
                dao.searchAllBook();
                List<Tbl_BookDTO> result = dao.getListBook();
                request.setAttribute("SEARCHRESULT", result);

            }

            HttpSession session = request.getSession();
            String isAd = (String) session.getAttribute("ADMIN");
            String isUser = (String) session.getAttribute("USER");
            if (isAd != null) {
                url = AD_SEARCH_PAGE;
            }
            if (isUser != null) {
                url = USER_SEARCH_PAGE;
            }
            Tbl_CategoryDAO dao = new Tbl_CategoryDAO();
            dao.loadListCategory();
            List<Tbl_CategoryDTO> listCategory = dao.getListCategory();

            session.setAttribute("LISTCATEGORY", listCategory);
        } catch (SQLException ex) {
            log("SearchServlet _ SQLException:" + ex.getMessage());
        } catch (NamingException ex) {
            log("SearchServlet _ NamingException:" + ex.getMessage());
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
