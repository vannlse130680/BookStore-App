/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_order;

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
public class Tbl_OrderDAO implements Serializable {
    List<History> listHistory;
    public List<History> getListHistory() {
        return  listHistory;
    }
    public int getRowTable() throws NamingException, SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int row = 0;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select orderId from tbl_order";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    ++row;
                }
                return row;

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
        return -1;

    }

    public boolean insertOrder(int orderId, String userId, String date, float total, String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_order(orderId, userId,date, total, discountCode) values(?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, orderId);
                pst.setString(2, userId);
                pst.setString(3, date);
                pst.setFloat(4, total);
                pst.setString(5, code);

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

    public void loadHistory(String userId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select o.orderid, o.date, o.total  as allTotal, b.title, d.amount, d.total , discountCode from tbl_Order o, tbl_OrderDetail d, tbl_Book b "
                    + "where o.orderID = d.orderID and d.bookId = b.bookId and o.userID =?";
            pst = con.prepareCall(sql);
            pst.setString(1, userId);
            rs = pst.executeQuery();

            while (rs.next()) {

                String orderId = rs.getString("orderid");
                String date = rs.getString("date");
                
                float allTotal = rs.getFloat("allTotal");
                String title = rs.getString("title");

               
                int amount = rs.getInt("amount");
                float total = rs.getFloat("total");
                String discountCode = rs.getString("discountCode");
                
                History h = new History(orderId, date, allTotal, title, amount, total, discountCode);
                if (listHistory == null) {
                    listHistory = new ArrayList<>();
                }
                listHistory.add(h);
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
    public void searchHistoryByTitle(String userId, String titleSearch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select o.orderid, o.date, o.total  as allTotal, b.title, d.amount, d.total , discountCode from tbl_Order o, tbl_OrderDetail d, tbl_Book b "
                    + "where o.orderID = d.orderID and d.bookId = b.bookId and o.userID =? and b.title like ?";
            pst = con.prepareCall(sql);
            pst.setString(1, userId );
            pst.setString(2,"%" + titleSearch + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
               
                String orderId = rs.getString("orderid");
                String date = rs.getString("date");
                
                float allTotal = rs.getFloat("allTotal");
                String title = rs.getString("title");

               
                int amount = rs.getInt("amount");
                float total = rs.getFloat("total");
                String discountCode = rs.getString("discountCode");
                
                History h = new History(orderId, date, allTotal, title, amount, total, discountCode);
                if (listHistory == null) {
                    listHistory = new ArrayList<>();
                }
                listHistory.add(h);
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
    public void searchHistoryByDate(String userId, String dateSearch) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select o.orderid, o.date, o.total  as allTotal, b.title, d.amount, d.total , discountCode from tbl_Order o, tbl_OrderDetail d, tbl_Book b "
                    + "where o.orderID = d.orderID and d.bookId = b.bookId and o.userID =? and o.date = ?";
            pst = con.prepareCall(sql);
            pst.setString(1, userId );
            pst.setString(2, dateSearch );
            rs = pst.executeQuery();

            while (rs.next()) {

                String orderId = rs.getString("orderid");
                String date = rs.getString("date");
                
                float allTotal = rs.getFloat("allTotal");
                String title = rs.getString("title");

               
                int amount = rs.getInt("amount");
                float total = rs.getFloat("total");
                String discountCode = rs.getString("discountCode");
                
                History h = new History(orderId, date, allTotal, title, amount, total, discountCode);
                if (listHistory == null) {
                    listHistory = new ArrayList<>();
                }
                listHistory.add(h);
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
}
