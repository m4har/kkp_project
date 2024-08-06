/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mahar
 */
public class conn {
     private static final String URL = "jdbc:mysql://localhost:3306/krb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Dapatkan koneksi ke database
//            Connection connection = conn.getConnection();
// Membangun query
//            StringBuilder query = new StringBuilder("UPDATE barang SET stock = CASE ");
// Mempersiapkan statement
//            PreparedStatement statement = connection.prepareStatement(query.toString());
//
// Eksekusi query
//            int rowsUpdated = statement.executeUpdate();
//            System.out.println("Rows updated: " + rowsUpdated);
//
// Tutup koneksi
//            DBConnection.closeConnection();