package com.example.pembiayaanqu.model;

public class catatanKeuangan {

    public String namaCatatan;
    public String jumlahKeuangan;
    public String jenisCatatan;

    public catatanKeuangan() {
        this.namaCatatan = namaCatatan;
        this.jumlahKeuangan = jumlahKeuangan;
        this.jenisCatatan = jenisCatatan;
    }

    public String getNamaCatatan() {
        return namaCatatan;
    }

    public void setNamaCatatan(String namaCatatan) {
        this.namaCatatan = namaCatatan;
    }

    public String getJumlahKeuangan() {
        return jumlahKeuangan;
    }

    public void setJumlahKeuangan(String jumlahKeuangan) {
        this.jumlahKeuangan = jumlahKeuangan;
    }

    public String getJenisCatatan() {
        return jenisCatatan;
    }

    public void setJenisCatatan(String jenisCatatan) {
        this.jenisCatatan = jenisCatatan;
    }
}
