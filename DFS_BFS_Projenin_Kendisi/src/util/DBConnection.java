/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
private static String url = "jdbc:mysql://localhost:3307/dfs_bfs";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "root";
    private static Connection c;
    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                c = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("Veri tabanı bağlantısı başarısız."); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Sürücü bulunamadı.");
        }
        return c;
    }
    public static void closeConnection(Connection c) {
		try {
			c.close();
		} catch (Exception ex) {
		}
	}
}
