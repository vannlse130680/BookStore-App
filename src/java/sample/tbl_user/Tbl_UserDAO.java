/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.naming.NamingException;
import utilies.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_UserDAO implements Serializable {

    public int checkLogin(String userId, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select isAdmin from tbl_User where userId = ? and password = ? and status = 'Active'";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
                pst.setString(2, password);
                rs = pst.executeQuery();

                if (rs.next()) {
                    boolean isAd = rs.getBoolean("isAdmin");
                    if (isAd) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
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
    public String getFullName(String userId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select fullname from tbl_user where userId = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
                rs = pst.executeQuery();
                if (rs.next()) {
                    return rs.getString("fullname");
                }
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
        return "";
    }
    public boolean isDuplicateUserId(String userId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select userId from tbl_user where userid = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, userId);
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
    public boolean insertUser(String userId,String password, String fullName, String address, String phone) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_user(userId, password,fullname, address, phone, isAdmin, status) values(?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, userId);
                pst.setString(2, password);
                pst.setString(3, fullName);
                pst.setString(4, address);
                pst.setString(5, phone);
                pst.setInt(6, 0);
                
                pst.setString(7, "Active");
                
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
    public Vector<String> getListUserId() throws NamingException, SQLException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        Vector v = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                v = new Vector();
                String sql = "select userId from tbl_user where isAdmin = 'false'";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    v.add(userId);
                }
                
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
        return v;
    }
}
