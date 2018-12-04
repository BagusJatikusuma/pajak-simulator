package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.wrapper.ExportDokumenWrapper;

import java.io.IOException;
import java.util.List;

public interface ExportImportService {

    void exportData(List<SuratPerintah> listSuratPerintah) throws IOException;
    void importData() throws IOException;
}
