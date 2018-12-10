package com.bekasidev.app.dao;

public interface ExportImportDao {
    void importData(String sql);

    void setExported();
}
