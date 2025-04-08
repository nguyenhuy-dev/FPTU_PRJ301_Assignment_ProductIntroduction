
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
import model.dto.AccountDTO;
import utils.ConnectDB;

public class AccountDAO implements Accessible<AccountDTO> {
    private Connection con;
    private ServletContext sc;

    public AccountDAO() {
        this.con = new ConnectDB().getConnection();
    }

    public AccountDAO(ServletContext sc) {
        this.sc = sc;
        this.con = new ConnectDB(sc).getConnection(); 
    }

    @Override
    public int insert(AccountDTO obj) {
        int count = 0;
        try {
            String sql = "INSERT INTO accounts (account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem)\n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, obj.getAccount());
            stm.setString(2, obj.getPass());
            stm.setString(3, obj.getLastName());
            stm.setString(4, obj.getFirstName()); 
            stm.setDate(5, obj.getBirthday());
            stm.setBoolean(6, obj.isGender());
            stm.setString(7, obj.getPhone());
            stm.setBoolean(8, obj.isIsUse());
            stm.setInt(9, obj.getRoleInSystem());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int delete(AccountDTO obj) {
        int count = 0;
        try {
            String sql = "DELETE FROM accounts\n" +
                            "WHERE account = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, obj.getAccount());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int update(AccountDTO obj) {
        int count = 0;
        try {
            String sql = "UPDATE accounts\n" +
                            "SET account = ?, pass = ?, lastName = ?, firstName = ?, birthday = ?, gender = ?, phone = ?, isUse = ?, roleInSystem = ?\n" +
                            "WHERE account = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, obj.getAccount());
            stm.setString(2, obj.getPass());
            stm.setString(3, obj.getLastName());
            stm.setString(4, obj.getFirstName()); 
            stm.setDate(5, obj.getBirthday());
            stm.setBoolean(6, obj.isGender());
            stm.setString(7, obj.getPhone());
            stm.setBoolean(8, obj.isIsUse());
            stm.setInt(9, obj.getRoleInSystem());
            stm.setString(10, obj.getAccount());
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public AccountDTO getObjectById(String id) {
        AccountDTO acc = null;
        try {
            String sql = "SELECT account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem FROM accounts\n" +
                            "WHERE account = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {        
                acc = new AccountDTO();
                acc.setAccount(rs.getString("account"));
                acc.setPass(rs.getString("pass"));
                acc.setLastName(rs.getString("lastName"));
                acc.setFirstName(rs.getString("firstName"));
                acc.setBirthday(rs.getDate("birthday"));
                acc.setGender(rs.getBoolean("gender"));
                acc.setPhone(rs.getString("phone"));
                acc.setIsUse(rs.getBoolean("isUse"));
                acc.setRoleInSystem(rs.getInt("roleInSystem"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    @Override
    public List<AccountDTO> listAll() {
        List<AccountDTO> list = new ArrayList<>();
        try {
            Statement stm = con.createStatement();
            String sql = "SELECT account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem FROM accounts \n" +
                            "ORDER BY roleInSystem, account";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {                
                AccountDTO acc = new AccountDTO();
                acc.setAccount(rs.getString("account"));
                acc.setPass(rs.getString("pass"));
                acc.setLastName(rs.getString("lastName"));
                acc.setFirstName(rs.getString("firstName"));
                acc.setBirthday(rs.getDate("birthday"));
                acc.setGender(rs.getBoolean("gender"));
                acc.setPhone(rs.getString("phone"));
                acc.setIsUse(rs.getBoolean("isUse"));
                acc.setRoleInSystem(rs.getInt("roleInSystem"));
                list.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int updateIsUsed(String account, boolean isUse) {
        int count = 0;
        try {
            String sql = "UPDATE accounts\n" +
                            "SET isUse = ?\n" +
                            "WHERE account = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setBoolean(1, isUse);
            stm.setString(2, account);
            count = stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    public AccountDTO getUserByLogin(String account, String pass) {
        AccountDTO acc = null;
        try {
            String sql = "SELECT account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem FROM accounts\n" +
                            "WHERE account = ? AND pass = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, account);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                acc = new AccountDTO();
                acc.setAccount(rs.getString("account"));
                acc.setPass(rs.getString("pass"));
                acc.setLastName(rs.getString("lastName"));
                acc.setFirstName(rs.getString("firstName"));
                acc.setBirthday(rs.getDate("birthday"));
                acc.setGender(rs.getBoolean("gender"));
                acc.setPhone(rs.getString("phone"));
                acc.setIsUse(rs.getBoolean("isUse"));
                acc.setRoleInSystem(rs.getInt("roleInSystem"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
}
