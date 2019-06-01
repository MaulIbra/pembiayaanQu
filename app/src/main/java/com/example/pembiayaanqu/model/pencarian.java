package com.example.pembiayaanqu.model;

public class pencarian {
    public String produkPembiayaan;
    public String plafondMin;
    public String plafondMax;

    public pencarian() {
        this.produkPembiayaan = produkPembiayaan;
        this.plafondMin = plafondMin;
        this.plafondMax = plafondMax;
    }

    public String getProdukPembiayaan() {
        return produkPembiayaan;
    }

    public void setProdukPembiayaan(String produkPembiayaan) {
        this.produkPembiayaan = produkPembiayaan;
    }

    public String getPlafondMin() {
        return plafondMin;
    }

    public void setPlafondMin(String plafondMin) {
        this.plafondMin = plafondMin;
    }

    public String getPlafondMax() {
        return plafondMax;
    }

    public void setPlafondMax(String plafondMax) {
        this.plafondMax = plafondMax;
    }
}
