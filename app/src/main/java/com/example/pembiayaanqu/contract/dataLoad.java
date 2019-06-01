package com.example.pembiayaanqu.contract;

import android.content.Intent;
import android.net.Uri;

import java.util.HashMap;

public interface dataLoad {

    interface view{
        void showProgressBar();
        void hideProgressBar();
        void changeActivity();
        void displayData(HashMap<String,String> data);
        void getdataUri(HashMap<String,String> data);
        void displayToastSuccess();
        void displayToastFailure();
        void displayToastUploadSuccess();
        void displayToastIsEmpty();
        void backActivity();
        void resultActivity(int requestCode, Intent intent);
        void toUpdateData();
        void spinnerData(String[] data, HashMap<Integer,String> value);
        void spinnerDataKota(String[] data, HashMap<Integer,String> value);
        void spinerDataKategoriPengajuan(String[] data, HashMap<Integer,String> value);
        void spinerDataDetailKategoriPengajuan(String[] data, HashMap<Integer,String> value);
        void spinerDataCabangPengajuan(String[] data, HashMap<Integer,String> value);
    }

    interface presenter{
        void readData();
        void readProvince();
        void readKotaKab(String id);
        void readKategoriPengajuan();
        void readDetailKategoriPengajuan(String id);
        void readCabangPengajuan(String idDetail,String idBranch);
        void saveDataPengajuan(HashMap<String,String> pengajuanData);
        void savedata(HashMap<String,String> userData);
        void uploadImage(HashMap<String,Uri> data);
        void getImage(int requestCode);
    }

}
