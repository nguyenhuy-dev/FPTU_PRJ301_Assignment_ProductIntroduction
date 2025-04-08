
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import model.dto.CategoryDTO;
import utils.ConnectDB;

public class CategoryDAO implements Accessible<CategoryDTO>{
    private ServletContext sc;
    private Connection con;

    public CategoryDAO() {
        this.con = new ConnectDB().getConnection();
    }

    public CategoryDAO(ServletContext sc) {
        this.sc = sc;
        this.con = new ConnectDB(this.sc).getConnection();
    }
    
    @Override
    public int insert(CategoryDTO obj) {
        int count = 0;
        try {
            String sql = "INSERT INTO categories(categoryName, memo) VALUES (?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, obj.getCategoryName());
            stm.setString(2, obj.getMemo());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int delete(CategoryDTO obj) {
        int count = 0;
        try {
            String sql = "DELETE FROM categories WHERE typeId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, obj.getTypeId());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int update(CategoryDTO obj) {
        int count = 0;
        //-- Cannot update identity column 'typeId'.
        try {
            String sql = "UPDATE categories\n" +
                            "SET categoryName = ?, memo = ?\n" +
                            "WHERE typeId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            
            stm.setString(1, obj.getCategoryName());
            stm.setString(2, obj.getMemo());
            stm.setInt(3, obj.getTypeId());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public CategoryDTO getObjectById(String id) {
        CategoryDTO cat = null;
        try {
            String sql = "SELECT typeId, categoryName, memo FROM categories \n" +
                        "WHERE typeId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {      
                cat = new CategoryDTO();
                cat.setTypeId(rs.getInt("typeId"));
                cat.setCategoryName(rs.getString("categoryName"));
                cat.setMemo(rs.getString("memo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
    }

    @Override
    public List<CategoryDTO> listAll() {
        List<CategoryDTO> list = new ArrayList<>();
        try {
            Statement stm = this.con.createStatement();
            String sql = "SELECT typeId, categoryName, memo FROM categories";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                CategoryDTO cat = new CategoryDTO();
                cat.setTypeId(rs.getInt("typeId"));
                cat.setCategoryName(rs.getString("categoryName"));
                cat.setMemo(rs.getString("memo"));
                list.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
