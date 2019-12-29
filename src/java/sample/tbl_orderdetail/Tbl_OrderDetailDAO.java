/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import utilies.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_OrderDetailDAO implements Serializable{
    public boolean insertOrderDetail(int orderId,String bookId, int amount, float total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = DBUtilies.makeConnection();
            if (con != null) {
                String sql = "insert tbl_orderdetail(orderId, bookid,amount, total) values(?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, orderId);
                pst.setString(2, bookId);
                pst.setInt(3, amount);
                pst.setFloat(4, total);
                
                
                
                
                
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
