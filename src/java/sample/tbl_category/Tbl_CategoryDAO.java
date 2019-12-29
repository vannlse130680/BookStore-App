/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_category;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.tbl_book.Tbl_BookDTO;
import utilies.DBUtilies;

/**
 *
 * @author Acer
 */
public class Tbl_CategoryDAO implements Serializable{
    List<Tbl_CategoryDTO> listCategory;

    public List<Tbl_CategoryDTO> getListCategory() {
        return listCategory;
    }
    public void loadListCategory() throws SQLException, NamingException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select categoryId, categoryName from tbl_category";
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

               String categoryId = rs.getString("categoryid");
               String categoryName = rs.getString("categoryName");
                
                Tbl_CategoryDTO dto = new Tbl_CategoryDTO(categoryId, categoryName);
                if (listCategory == null) {
                    listCategory = new ArrayList<>();
                }
                listCategory.add(dto);
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
    public String getCategoryId(String categoryName) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBUtilies.makeConnection();
            String sql = "select categoryId from tbl_category "
                    + "where  categoryName = ?";
            pst = con.prepareCall(sql);
            pst.setString(1, categoryName);
            rs = pst.executeQuery();

            if(rs.next()) {
                return rs.getString("categoryId");
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
}
