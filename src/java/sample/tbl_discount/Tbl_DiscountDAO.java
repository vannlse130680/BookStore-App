/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_discount;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utilies.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_DiscountDAO implements Serializable{
    public boolean isDiscountCode(String code, String userId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select discountCode from tbl_discount where discountCode = ? and isUsed = ? and userId = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
                pst.setString(2, "false");
                pst.setString(3, userId);
                rs = pst.executeQuery();

                if (rs.next()) {
                    return true;
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
        return false;
    }
    public boolean updateCode(String code, String userId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
               String sql = "update tbl_discount set isUsed =? where discountCode = ? and userId = ?";
                pst = con.prepareStatement(sql);
                
                pst.setString(1, "true");
                pst.setString(2, code);
                pst.setString(3, userId);
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
    public int getPercentDiscount(String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select percentDiscount from tbl_discount where discountCode = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
               
                rs = pst.executeQuery();

                if (rs.next()) {
                    return rs.getInt("percentDiscount");
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
    public boolean isDuplicatedCode(String code) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "select discountCode from tbl_discount where discountCode = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
                
                rs = pst.executeQuery();

                if (rs.next()) {
                    return true;
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
        return false;
    }
    public boolean insertCode(String code, String userId, int percent, String date) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_discount(discountCode, userId, percentDiscount,isUsed, importDate) values(?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, code);
                pst.setString(2, userId);
                pst.setInt(3, percent);
                pst.setString(4, "false");
                pst.setString(5, date);
                
                
                
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
