package com.bekasidev.app.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connect {

//    private static Connection conn = null;

    public static Connection connect() throws ClassNotFoundException  {
        Connection conn = null;
        // SQLite connection string
//        if(conn == null){
            String url = "jdbc:sqlite:db/pajak-simulator.db";
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
//        }
        return conn;
    }
}
