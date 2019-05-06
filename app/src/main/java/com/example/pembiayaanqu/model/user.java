package com.example.pembiayaanqu.model;

public class user {
    public String username;
    public String phoneNumber;
    public String email;
    public String password;
    public String nama_lengkap;
    public String umur;
    public String status;
    public String jenis_kelamin;
    public String pendidikan_terakhir;
    public String pekerjaan_usaha;
    public String nomor_npwp;
    public String nomor_ktp;
    public String lama_bekerja_usaha;
    public String nama_perusahaan_saat_bekerja_usaha;
    public String jumlah_pengajuan_pembiayaan;
    public String tipe_tujuan_pembiayaan;
    public String lokasi_pengambilan;
    public String alamat_lokasi;
    public String urlphotoKTP;
    public String urlphotoKK;


    public user(String username, String phoneNumber, String email, String password) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public user(String username, String phoneNumber, String email, String password, String nama_lengkap, String umur, String status, String jenis_kelamin, String pendidikan_terakhir, String pekerjaan_usaha, String nomor_npwp, String nomor_ktp, String lama_bekerja_usaha, String nama_perusahaan_saat_bekerja_usaha, String jumlah_pengajuan_pembiayaan, String tipe_tujuan_pembiayaan, String lokasi_pengambilan, String alamat_lokasi, String urlphotoKTP, String urlphotoKK) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.nama_lengkap = nama_lengkap;
        this.umur = umur;
        this.status = status;
        this.jenis_kelamin = jenis_kelamin;
        this.pendidikan_terakhir = pendidikan_terakhir;
        this.pekerjaan_usaha = pekerjaan_usaha;
        this.nomor_npwp = nomor_npwp;
        this.nomor_ktp = nomor_ktp;
        this.lama_bekerja_usaha = lama_bekerja_usaha;
        this.nama_perusahaan_saat_bekerja_usaha = nama_perusahaan_saat_bekerja_usaha;
        this.jumlah_pengajuan_pembiayaan = jumlah_pengajuan_pembiayaan;
        this.tipe_tujuan_pembiayaan = tipe_tujuan_pembiayaan;
        this.lokasi_pengambilan = lokasi_pengambilan;
        this.alamat_lokasi = alamat_lokasi;
        this.urlphotoKTP = urlphotoKTP;
        this.urlphotoKK = urlphotoKK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getPendidikan_terakhir() {
        return pendidikan_terakhir;
    }

    public void setPendidikan_terakhir(String pendidikan_terakhir) {
        this.pendidikan_terakhir = pendidikan_terakhir;
    }

    public String getPekerjaan_usaha() {
        return pekerjaan_usaha;
    }

    public void setPekerjaan_usaha(String pekerjaan_usaha) {
        this.pekerjaan_usaha = pekerjaan_usaha;
    }

    public String getNomor_npwp() {
        return nomor_npwp;
    }

    public void setNomor_npwp(String nomor_npwp) {
        this.nomor_npwp = nomor_npwp;
    }

    public String getNomor_ktp() {
        return nomor_ktp;
    }

    public void setNomor_ktp(String nomor_ktp) {
        this.nomor_ktp = nomor_ktp;
    }

    public String getLama_bekerja_usaha() {
        return lama_bekerja_usaha;
    }

    public void setLama_bekerja_usaha(String lama_bekerja_usaha) {
        this.lama_bekerja_usaha = lama_bekerja_usaha;
    }

    public String getNama_perusahaan_saat_bekerja_usaha() {
        return nama_perusahaan_saat_bekerja_usaha;
    }

    public void setNama_perusahaan_saat_bekerja_usaha(String nama_perusahaan_saat_bekerja_usaha) {
        this.nama_perusahaan_saat_bekerja_usaha = nama_perusahaan_saat_bekerja_usaha;
    }

    public String getJumlah_pengajuan_pembiayaan() {
        return jumlah_pengajuan_pembiayaan;
    }

    public void setJumlah_pengajuan_pembiayaan(String jumlah_pengajuan_pembiayaan) {
        this.jumlah_pengajuan_pembiayaan = jumlah_pengajuan_pembiayaan;
    }

    public String getTipe_tujuan_pembiayaan() {
        return tipe_tujuan_pembiayaan;
    }

    public void setTipe_tujuan_pembiayaan(String tipe_tujuan_pembiayaan) {
        this.tipe_tujuan_pembiayaan = tipe_tujuan_pembiayaan;
    }

    public String getLokasi_pengambilan() {
        return lokasi_pengambilan;
    }

    public void setLokasi_pengambilan(String lokasi_pengambilan) {
        this.lokasi_pengambilan = lokasi_pengambilan;
    }

    public String getAlamat_lokasi() {
        return alamat_lokasi;
    }

    public void setAlamat_lokasi(String alamat_lokasi) {
        this.alamat_lokasi = alamat_lokasi;
    }

    public String getUrlphotoKTP() {
        return urlphotoKTP;
    }

    public void setUrlphotoKTP(String urlphotoKTP) {
        this.urlphotoKTP = urlphotoKTP;
    }

    public String getUrlphotoKK() {
        return urlphotoKK;
    }

    public void setUrlphotoKK(String urlphotoKK) {
        this.urlphotoKK = urlphotoKK;
    }
}
