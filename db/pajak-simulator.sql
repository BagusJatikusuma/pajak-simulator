CREATE TABLE wajib_pajak(
id_wp varchar(30) not null primary key,
nama_wp varchar(255) not null,
jenis_wp int not null,
alamat_jalan varchar(255) not null,
alamat_kecamatan varchar(100) not null,
alamat_desa varchar(100) not null, nama_pemilik varchar(100), telepon varchar(20), fax varchar(20), tahun_operasional varchar(5));
CREATE TABLE tim(
id_tim VARCHAR(30) not null primary key,
nama_tim VARCHAR(50) not null);
CREATE TABLE surat_perintah (id_sp VARCHAR(30) PRIMARY KEY NOT NULL, nomor_surat VARCHAR(100), nomor_urut VARCHAR(10), nomor_sk VARCHAR(100), tanggal_sk VARCHAR(20) NOT NULL, pemberi_sk VARCHAR(100), tahun_anggaran_sk INTEGER, tahun_anggaran_biaya INTEGER NOT NULL, nomor_surat_biaya VARCHAR(100), tanggal_biaya VARCHAR(20), pemberi_sp TEXT, masa_pajak_awal VARCHAR(20) NOT NULL, masa_pajak_akhir VARCHAR(20) NOT NULL, tahap int NOT NULL, lama_pelaksanaan int NOT NULL, tempat VARCHAR(30) NOT NULL, tanggal_surat VARCHAR(30));
CREATE TABLE pegawai (id_tim VARCHAR(30), nip_pegawai VARCHAR(30) PRIMARY KEY NOT NULL, nama_pegawai VARCHAR(255) NOT NULL, golongan VARCHAR(10) NOT NULL, jabatan_tim VARCHAR(255) NOT NULL, pangkat VARCHAR(20), jabatan_dinas varchar(255));
CREATE TABLE berkas_persiapan (id_wp VARCHAR(30) NOT NULL REFERENCES wajib_pajak (id_wp), id_sp VARCHAR(30) NOT NULL REFERENCES surat_perintah (id_sp), dokumen_pinjaman TEXT);
CREATE TABLE nomor_berkas (id_wp VARCHAR(30) NOT NULL REFERENCES wajib_pajak (id_wp), id_sp VARCHAR(30) NOT NULL REFERENCES surat_perintah (id_sp), nomor_hasil VARCHAR(10), tanggal_hasil VARCHAR(30), nomor_pemberitahuan VARCHAR(10), tanggal_pemberitahuan VARCHAR(30), nomor_peminjaman VARCHAR(10), tanggal_peminjaman VARCHAR(30));
CREATE TABLE rekapitulasi (id_sp VARCHAR(30) NOT NULL REFERENCES surat_perintah (id_sp) ON DELETE CASCADE ON UPDATE CASCADE, id_wp VARCHAR(30) NOT NULL REFERENCES wajib_pajak (id_wp), bulan VARCHAR(15) NOT NULL, omzet_periksa DOUBLE NOT NULL, pajak_periksa DOUBLE NOT NULL, omzet_laporan DOUBLE NOT NULL, pajak_disetor DOUBLE NOT NULL, omzet DOUBLE NOT NULL, pokok_pajak DOUBLE NOT NULL, denda DOUBLE NOT NULL, jumlah DOUBLE NOT NULL, persentase_denda INTEGER NOT NULL);
CREATE TABLE tim_perintah (id_sp VARCHAR(30) NOT NULL REFERENCES surat_perintah (id_sp) ON DELETE RESTRICT ON UPDATE RESTRICT, penanggung_jawab TEXT NOT NULL, supervisor TEXT NOT NULL, id_tim VARCHAR(30) REFERENCES tim (id_tim), nama_tim VARCHAR(10) NOT NULL, list_anggota TEXT NOT NULL, list_wp TEXT NOT NULL);
