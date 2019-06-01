package com.example.pembiayaanqu.contract;

import com.example.pembiayaanqu.model.pengajuan;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public interface dataLoadPengajuan {
    interface view{
        void onfailure();
        void onSuccess();
        void addAdapter(ArrayList<pengajuan> list);
    }
    interface presenter{
        void loadDataPengajuan(String idPage);
        void loadData(String statusPengajuan);
    }
}
