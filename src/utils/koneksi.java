package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
 
public class koneksi {
  public Connection con;
  public Statement stm;
    
    public void config(){
        try {
            if(con == null){
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/krb", "root", "");
            stm = con.createStatement();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
    }
}