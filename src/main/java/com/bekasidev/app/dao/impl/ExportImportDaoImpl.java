package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.ExportImportDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExportImportDaoImpl implements ExportImportDao {
    @Override
    public void importData(String sql) {
        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
