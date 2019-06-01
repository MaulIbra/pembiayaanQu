package com.example.pembiayaanqu.model;

public class menu {
    private String nama_bank;
    private int logo_bank;

    public menu(String nama_bank, int logo_bank) {
        this.nama_bank = nama_bank;
        this.logo_bank = logo_bank;
    }

    public String getNama_bank() {
        return nama_bank;
    }

    public void setNama_bank(String nama_bank) {
        this.nama_bank = nama_bank;
    }

    public int getLogo_bank() {
        return logo_bank;
    }

    public void setLogo_bank(int logo_bank) {
        this.logo_bank = logo_bank;
    }
}
