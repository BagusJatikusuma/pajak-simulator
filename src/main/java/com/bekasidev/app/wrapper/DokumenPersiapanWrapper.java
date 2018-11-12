package com.bekasidev.app.wrapper;

import com.bekasidev.app.model.BerkasPersiapan;
import com.bekasidev.app.model.WajibPajak;

public class DokumenPersiapanWrapper extends BerkasPersiapan {
    WajibPajak wp;

    public WajibPajak getWp() {
        return wp;
    }

    public void setWp(WajibPajak wp) {
        this.wp = wp;
    }
}
