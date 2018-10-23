package com.bekasidev.app.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connect {

    public static Connection con = connect();

    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:dbtestSqlite.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
