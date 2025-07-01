/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Kharisma.S
 */
public class config {
 
    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                String url = "jdbc:mysql://localhost:3306/d'rapz_dimsum"; 
                String user = "root";
                String pass = ""; 
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi berhasil!");
            }
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
}   
}
