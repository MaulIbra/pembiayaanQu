package com.example.pembiayaanqu.contract;

import com.example.pembiayaanqu.model.catatanKeuangan;

import java.util.ArrayList;

public interface datacatatanKeuangan {
    interface view{
        void showPopup();
        void onSuccess();
        void onFailure();
        void showTotalPemasukkan(int pemasukkan);
        void showTotalPengeluaran(int pengeluaran);
        void showTotalSaldo(Integer sisaSaldo);
        void addAdapter(ArrayList<catatanKeuangan> catatanKeuangans);
    }
    interface presenter{
        void saveData(String namaCatatan,String jumlahCatatan, String jenisCatatan);
        void totalPemasukkan();
        void totalPengeluaran();
        void totalSaldo(Integer pemasukkan, Integer pengeluaran);
        void loadData();
    }
}
