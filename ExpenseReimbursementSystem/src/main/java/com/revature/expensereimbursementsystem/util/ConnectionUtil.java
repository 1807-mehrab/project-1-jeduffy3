/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.revature.expensereimbursementsystem.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author James
 */
public class ConnectionUtil {
    
    public static Connection getConnection() throws SQLException, IOException {
//        Properties prop = new Properties();
//        InputStream in = new FileInputStream("connection.properties");
//        prop.load(in);
//        
//        String url = prop.getProperty("url");
//        String user = prop.getProperty("user");
//        String password = prop.getProperty("password");
//        
//        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
//        return DriverManager.getConnection(url, user, password);
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ersdb", "password");
    }
}
