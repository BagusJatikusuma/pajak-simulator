package com.bekasidev.app.service.backend;

import com.bekasidev.app.model.SuratPerintah;
import com.bekasidev.app.model.TimSP;
import com.bekasidev.app.wrapper.ExportDokumenWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ExportImportService {

    void exportDataAdmin(SuratPerintah suratPerintah) throws IOException;
    void exportDataTim(TimSP timSP);
    void importData(File file) throws IOException;
    void setExportable(String idSP);
    void importWajibPajak(File file);
}
