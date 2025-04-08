package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

public class ConnectDB {
    private String hostName;
    private String instance;
    private String port;
    private String dbName;
    private String user;
    private String pass;

    public ConnectDB() {
        this.hostName = "LAPTOP-UALL54OF";
        this.instance = "HUYNGUYEN";
        this.port = "1433";
        this.dbName = "ProductIntro";
        this.user = "testkn";
        this.pass = "12345";
    }
    
    public ConnectDB(ServletContext sc) {
        this.hostName = sc.getInitParameter("hostName");
        this.instance = sc.getInitParameter("instance");
        this.port = sc.getInitParameter("port");
        this.dbName = sc.getInitParameter("dbName");
        this.user = sc.getInitParameter("user");
        this.pass = sc.getInitParameter("pass");
    }
    
    public String getURLString() {
        String url = "jdbc:sqlserver://%s\\%s:%s;databaseName=%s";
        return String.format(url, 
                            this.hostName, this.instance, this.port, this.dbName);
    }
    
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(getURLString(), this.user, this.pass);
            System.out.println("Ket noi thanh cong!!");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
