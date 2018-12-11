package com.bekasidev.app.dao.impl;

import com.bekasidev.app.config.Connect;
import com.bekasidev.app.dao.ExportImportDao;
import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.util.LogException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExportImportDaoImpl implements ExportImportDao {
    @Override
    public void importData(String sql) {
        try(Connection conn = Connect.connect();
            Statement stm = conn.createStatement();) {
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }

    @Override
    public void setExported() {
        String sql = "UPDATE surat_perintah SET exported=? WHERE exported=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setShort(1, (short) 1);
            pstm.setShort(2, (short) 0);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }

    @Override
    public void setExportable(String idSP) {
        String sql = "UPDATE surat_perintah SET exported=? WHERE id_sp=?";

        try(Connection conn = Connect.connect();
            PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setShort(1, (short) 0);
            pstm.setString(2, idSP);

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            new LogException(e);
        }
    }
}
