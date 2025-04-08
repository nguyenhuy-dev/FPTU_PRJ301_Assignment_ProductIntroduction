
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
import model.dto.ProductDTO;
import utils.ConnectDB;


public class ProductDAO implements Accessible<ProductDTO> {
    private Connection con;
    private ServletContext sc;

    public ProductDAO() {
        this.con = new ConnectDB().getConnection();
    }

    public ProductDAO(ServletContext sc) {
        this.sc = sc;
        this.con = new ConnectDB(sc).getConnection();
    }

    @Override
    public int insert(ProductDTO obj) {
        int count = 0;
        try {
            String sql = "INSERT INTO products(productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, obj.getProductId());
            stm.setString(2, obj.getProductName());
            stm.setString(3, obj.getProductImage());
            stm.setString(4, obj.getBrief());
            stm.setDate(5, obj.getPostedDate());
            stm.setInt(6, obj.getType().getTypeId());
            stm.setString(7, obj.getAccount().getAccount());
            stm.setString(8, obj.getUnit());
            stm.setInt(9, obj.getPrice());
            stm.setInt(10, obj.getDiscount());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int delete(ProductDTO obj) {
        int count = 0;
        try {
            String sql = "DELETE FROM products WHERE productId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, obj.getProductId());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int update(ProductDTO obj) {
        int count = 0;
        try {
            String sql = "UPDATE products\n" +
                            "SET productId = ?, productName = ?, productImage = ?, brief = ?, postedDate = ?, typeId = ?, account = ?, unit = ?, price = ?, discount = ?\n" +
                            "WHERE productId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, obj.getProductId());
            stm.setString(2, obj.getProductName());
            stm.setString(3, obj.getProductImage());
            stm.setString(4, obj.getBrief());
            stm.setDate(5, obj.getPostedDate());
            stm.setInt(6, obj.getType().getTypeId());
            stm.setString(7, obj.getAccount().getAccount());
            stm.setString(8, obj.getUnit());
            stm.setInt(9, obj.getPrice());
            stm.setInt(10, obj.getDiscount());
            stm.setString(11, obj.getProductId());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public ProductDTO getObjectById(String id) {
        ProductDTO pro = null;
        try {
            String sql = "SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount FROM products\n" +
                            "WHERE productId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                pro = new ProductDTO();
                pro.setProductId(rs.getString("productId"));
                pro.setProductName(rs.getString("productName"));
                pro.setProductImage(rs.getString("productImage"));
                pro.setBrief(rs.getString("brief"));
                pro.setPostedDate(rs.getDate("postedDate"));
                pro.setType(new CategoryDAO(this.sc).getObjectById(rs.getString("typeId")));
                pro.setAccount(new AccountDAO(this.sc).getObjectById(rs.getString("account")));
                pro.setUnit(rs.getString("unit"));
                pro.setPrice(rs.getInt("price"));
                pro.setDiscount(rs.getInt("discount"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }
    
    public List<ProductDTO> listProductsByCategory(String id) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount \n" +
                            "FROM products WHERE typeId = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                ProductDTO pro = new ProductDTO();
                pro.setProductId(rs.getString("productId"));
                pro.setProductName(rs.getString("productName"));
                pro.setProductImage(rs.getString("productImage"));
                pro.setBrief(rs.getString("brief"));
                pro.setPostedDate(rs.getDate("postedDate"));
                pro.setType(new CategoryDAO(this.sc).getObjectById(rs.getString("typeId")));
                pro.setAccount(new AccountDAO(this.sc).getObjectById(rs.getString("account")));
                pro.setUnit(rs.getString("unit"));
                pro.setPrice(rs.getInt("price"));
                pro.setDiscount(rs.getInt("discount"));
                list.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<ProductDTO> listProductsByBox(String id) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            String cusId = "%" + id + "%";
            String sql = "SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount \n" +
                            "FROM products WHERE productName COLLATE Latin1_General_CI_AI LIKE ? ";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, cusId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                ProductDTO pro = new ProductDTO();
                pro.setProductId(rs.getString("productId"));
                pro.setProductName(rs.getString("productName"));
                pro.setProductImage(rs.getString("productImage"));
                pro.setBrief(rs.getString("brief"));
                pro.setPostedDate(rs.getDate("postedDate"));
                pro.setType(new CategoryDAO(this.sc).getObjectById(rs.getString("typeId")));
                pro.setAccount(new AccountDAO(this.sc).getObjectById(rs.getString("account")));
                pro.setUnit(rs.getString("unit"));
                pro.setPrice(rs.getInt("price"));
                pro.setDiscount(rs.getInt("discount"));
                list.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<ProductDTO> listAll() {
        List<ProductDTO> list = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            String sql = "SELECT productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount FROM products "
                            + "ORDER BY postedDate DESC";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                ProductDTO pro = new ProductDTO();
                pro.setProductId(rs.getString("productId"));
                pro.setProductName(rs.getString("productName"));
                pro.setProductImage(rs.getString("productImage"));
                pro.setBrief(rs.getString("brief"));
                pro.setPostedDate(rs.getDate("postedDate"));
                pro.setType(new CategoryDAO(this.sc).getObjectById(rs.getString("typeId")));
                pro.setAccount(new AccountDAO(this.sc).getObjectById(rs.getString("account")));
                pro.setUnit(rs.getString("unit"));
                pro.setPrice(rs.getInt("price"));
                pro.setDiscount(rs.getInt("discount"));
                list.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
