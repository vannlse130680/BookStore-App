/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utilies.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_BookDAO implements Serializable {

    List<Tbl_BookDTO> listBook;

    public List<Tbl_BookDTO> getListBook() {
        return listBook;
    }

    public void searchAllBook() throws SQLException, NamingException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select bookid, title, image, price, author, c.categoryid, c.categoryname, quantity, description, importDate, status from tbl_Book b, tbl_Category c where  status = 'Active' and quantity > 0 and c.categoryid = b.categoryid";
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                String bookId = rs.getString("bookid");
                String title = rs.getString("title");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                String author = rs.getString("author");

                String category = rs.getString("categoryname");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String importDate = rs.getString("importDate");
                String status = rs.getString("status");
                Tbl_BookDTO dto = new Tbl_BookDTO(bookId, title, image, price, author, category, quantity, description, importDate, status);
                if (listBook == null) {
                    listBook = new ArrayList<>();
                }
                listBook.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public void searchByTitle(String name) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select bookid, title, image, price, author, c.categoryid, c.categoryname, quantity, description, importDate, status from tbl_Book b, tbl_Category c "
                    + "where  status = 'Active' and quantity > 0 and c.categoryid = b.categoryid and title like ?";
            pst = con.prepareCall(sql);
            pst.setString(1, "%" + name + "%");
            rs = pst.executeQuery();

            while (rs.next()) {

                String bookId = rs.getString("bookid");
                String title = rs.getString("title");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                String author = rs.getString("author");

                String category = rs.getString("categoryname");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String importDate = rs.getString("importDate");
                String status = rs.getString("status");
                Tbl_BookDTO dto = new Tbl_BookDTO(bookId, title, image, price, author, category, quantity, description, importDate, status);
                if (listBook == null) {
                    listBook = new ArrayList<>();
                }
                listBook.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchByCategory(String categoryName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select bookid, title, image, price, author, c.categoryid, c.categoryname, quantity, description, importDate, status from tbl_Book b, tbl_Category c "
                    + "where  status = 'Active' and quantity > 0 and c.categoryid = b.categoryid and c.categoryName = ?";
            pst = con.prepareCall(sql);
            pst.setString(1, categoryName);
            rs = pst.executeQuery();

            while (rs.next()) {

                String bookId = rs.getString("bookid");
                String title = rs.getString("title");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                String author = rs.getString("author");

                String category = rs.getString("categoryname");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String importDate = rs.getString("importDate");
                String status = rs.getString("status");
                Tbl_BookDTO dto = new Tbl_BookDTO(bookId, title, image, price, author, category, quantity, description, importDate, status);
                if (listBook == null) {
                    listBook = new ArrayList<>();
                }
                listBook.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void searchByPrice(float min, float max) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select bookid, title, image, price, author, c.categoryid, c.categoryname, quantity, description, importDate, status from tbl_Book b, tbl_Category c "
                    + "where  status = 'Active' and quantity > 0 and c.categoryid = b.categoryid and price >= ? and price <= ?";
            pst = con.prepareCall(sql);
            pst.setFloat(1, min);
            pst.setFloat(2, max);
            rs = pst.executeQuery();

            while (rs.next()) {

                String bookId = rs.getString("bookid");
                String title = rs.getString("title");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                String author = rs.getString("author");

                String category = rs.getString("categoryname");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String importDate = rs.getString("importDate");
                String status = rs.getString("status");
                Tbl_BookDTO dto = new Tbl_BookDTO(bookId, title, image, price, author, category, quantity, description, importDate, status);
                if (listBook == null) {
                    listBook = new ArrayList<>();
                }
                listBook.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteBook(String bookId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "update tbl_book set status = ? where bookid = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, "Inactive");
                pst.setString(2, bookId);

                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }

        } finally {

            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateBook(String bookId, String title, float price, String author, String categoryId, String importDate, int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "update tbl_Book set title = ?, price = ?, author = ?, categoryId = ?, importDate = ?, quantity =? where bookid = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, title);
                pst.setFloat(2, price);
                pst.setString(3, author);
                pst.setString(4, categoryId);
                pst.setString(5, importDate);
                pst.setInt(6, quantity);
                pst.setString(7, bookId);
                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }

        } finally {

            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean isDuplicateBookId(String bookId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select bookId from tbl_book where bookid = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, bookId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertBook(String bookId, String title, String image, float price, String author, String categoryId, int quantity, String description, String importDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_book(bookid, title,image, price, author, categoryId, quantity, description, importdate, status) values(?,?,?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, bookId);
                pst.setString(2, title);
                pst.setString(3, image);
                pst.setFloat(4, price);
                pst.setString(5, author);
                pst.setString(6, categoryId);
                pst.setInt(7, quantity);
                pst.setString(8, description);
                pst.setString(9, importDate);
                pst.setString(10, "Active");
                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }

        } finally {

            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getQuantity(String bookId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select quantity from tbl_book where bookid = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, bookId);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }
    
    public boolean updateQuantity(String bookId,  int quantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "update tbl_Book set quantity =? where bookid = ?";
                pst = con.prepareStatement(sql);
                
                pst.setInt(1, quantity);
                pst.setString(2, bookId);
                int row = pst.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }

        } finally {

            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
