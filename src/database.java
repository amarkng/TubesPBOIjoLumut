/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ImNotplying
 */
public class database {
    static final String url = "jdbc:mysql://dbpbo.2233322.xyz:3307/pbo";
    static final String user = "root";
    static final String pass = "ibad12345";
    static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    
     public void connect(){
        try{
            conn = DriverManager.getConnection(url,user,pass);
            stmt = conn.createStatement();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void query(String sql){
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ResultSet view(String sql){
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return rs;
    }
    
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
