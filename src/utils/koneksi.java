package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
 
public class koneksi {
  public Connection con;
  public Statement stm;
    
    public void config(){
        try {
            if(con == null){
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "UTF-8");
            properties.setProperty("user", "root");
            properties.setProperty("password", "");

            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krb", properties);
            stm = con.createStatement();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
    }
}