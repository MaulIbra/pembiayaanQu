package com.example.pembiayaanqu.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pembiayaanqu.adapter.PencarianAdapter;
import com.example.pembiayaanqu.contract.dataLoadPengajuan;
import com.example.pembiayaanqu.model.pencarian;
import com.example.pembiayaanqu.model.pengajuan;
import com.example.pembiayaanqu.view.activity.hasil_pencarian;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class loadDataPengajuanPresenter implements dataLoadPengajuan.presenter {
    ArrayList<pengajuan> list;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    dataLoadPengajuan.view view;


    public loadDataPengajuanPresenter(dataLoadPengajuan.view view) {
        this.view = view;
    }

    @Override
    public void loadDataPengajuan(String idPage) {
        if (idPage.equals("pengajuanSemua")){
            loadData("0");
        }else if (idPage.equals("pengajuanDiterima")){
            loadData("5");
        }else if (idPage.equals("pengajuanDitolak")){
            loadData("6");
        }
    }

    @Override
    public void loadData(final String statusPengajuan) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        firebaseFirestore.collection("pengajuanPembiayaan").document(user.getUid()).collection("pengajuan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                list = new ArrayList<>();
                for (DocumentSnapshot querySnapshot : task.getResult()){
                    if (querySnapshot.exists()){
                        if (statusPengajuan.equals("0")){
                            if (!(String.valueOf(querySnapshot.getString("Status Pengajuan")).equals("5")) && !(String.valueOf(querySnapshot.getString("Status Pengajuan")).equals("6")) ){
                                pengajuan p = new pengajuan();
                                p.setKategoriPengajuan(String.valueOf(querySnapshot.getString("Kategori Pengajuan")));
                                p.setTujuanPengajuan(String.valueOf(querySnapshot.getString("Tujuan Pengajuan")));
                                p.setBranchPengajuan(String.valueOf(querySnapshot.getString("Cabang Pengajuan")));
                                p.setTanggalPengajuan(String.valueOf(querySnapshot.getString("Tanggal Pengajuan")));
                                String status = status(String.valueOf(querySnapshot.getString("Status Pengajuan")));
                                p.setStatusPengajuan(status);
                                p.setPhoneNumber(String.valueOf(querySnapshot.getString("Nomor Handphone")));
                                p.setNama_lengkap(String.valueOf(querySnapshot.getString("Nama Lengkap")));
                                p.setUmur(String.valueOf(querySnapshot.getString("Umur")));
                                p.setEmail(String.valueOf(querySnapshot.getString("Email")));
                                p.setStatus(String.valueOf(querySnapshot.getString("Status")));
                                p.setJenis_kelamin(String.valueOf(querySnapshot.getString("Jenis Kelamin")));
                                p.setPendidikan_terakhir(String.valueOf(querySnapshot.getString("Pendidikan Terakhir")));
                                p.setPekerjaan_usaha(String.valueOf(querySnapshot.getString("Pekerjaan atau Usaha")));
                                p.setNomor_npwp(String.valueOf(querySnapshot.getString("Nomor NPWP")));
                                p.setNomor_ktp(String.valueOf(querySnapshot.getString("Nomor KTP")));
                                p.setLama_bekerja_usaha(String.valueOf(querySnapshot.getString("Lama bekerja atau Usaha")));
                                p.setNama_perusahaan_saat_bekerja_usaha(String.valueOf(querySnapshot.getString("Nama Perusahaan saat Bekerja")));
                                p.setJumlah_pengajuan_pembiayaan(String.valueOf(querySnapshot.getString("Jumlah Pengajuan Pembiayaan")));
                                p.setTipe_tujuan_pembiayaan(String.valueOf(querySnapshot.getString("Tipe Tujuan Pembiayaan")));
                                p.setAlamatLatitude(String.valueOf(querySnapshot.getString("Alamat Latitude")));
                                p.setAlamatLongitude(String.valueOf(querySnapshot.getString("Alamat Longitude")));
                                p.setProvinsi(String.valueOf(querySnapshot.getString("Provinsi")));
                                p.setKota_kabupaten(String.valueOf(querySnapshot.getString("KotaKabupaten")));
                                p.setKecamatan(String.valueOf(querySnapshot.getString("Kecamatan")));
                                p.setKodePos(String.valueOf(querySnapshot.getString("Kode Pos")));
                                p.setAlamatLengkap(String.valueOf(querySnapshot.getString("Alamat Lengkap")));
                                p.setUrlphotoKTP(String.valueOf(querySnapshot.getString("uriPhotoKtp")));
                                p.setUrlphotoKK(String.valueOf(querySnapshot.getString("uriPhotoKk")));
                                p.setUrlphotoJaminan(String.valueOf(querySnapshot.getString("uriPhotoJaminan")));
                                list.add(p);
                            }
                        }else if (statusPengajuan.equals("5") || statusPengajuan.equals("6")){
                            if (String.valueOf(querySnapshot.getString("Status Pengajuan")).equals(statusPengajuan)){
                                pengajuan p = new pengajuan();
                                p.setKategoriPengajuan(String.valueOf(querySnapshot.getString("Kategori Pengajuan")));
                                p.setTujuanPengajuan(String.valueOf(querySnapshot.getString("Tujuan Pengajuan")));
                                p.setBranchPengajuan(String.valueOf(querySnapshot.getString("Cabang Pengajuan")));
                                p.setTanggalPengajuan(String.valueOf(querySnapshot.getString("Tanggal Pengajuan")));
                                String status = status(String.valueOf(querySnapshot.getString("Status Pengajuan")));
                                p.setStatusPengajuan(status);
                                p.setPhoneNumber(String.valueOf(querySnapshot.getString("Nomor Handphone")));
                                p.setNama_lengkap(String.valueOf(querySnapshot.getString("Nama Lengkap")));
                                p.setUmur(String.valueOf(querySnapshot.getString("Umur")));
                                p.setEmail(String.valueOf(querySnapshot.getString("Email")));
                                p.setStatus(String.valueOf(querySnapshot.getString("Status")));
                                p.setJenis_kelamin(String.valueOf(querySnapshot.getString("Jenis Kelamin")));
                                p.setPendidikan_terakhir(String.valueOf(querySnapshot.getString("Pendidikan Terakhir")));
                                p.setPekerjaan_usaha(String.valueOf(querySnapshot.getString("Pekerjaan atau Usaha")));
                                p.setNomor_npwp(String.valueOf(querySnapshot.getString("Nomor NPWP")));
                                p.setNomor_ktp(String.valueOf(querySnapshot.getString("Nomor KTP")));
                                p.setLama_bekerja_usaha(String.valueOf(querySnapshot.getString("Lama bekerja atau Usaha")));
                                p.setNama_perusahaan_saat_bekerja_usaha(String.valueOf(querySnapshot.getString("Nama Perusahaan saat Bekerja")));
                                p.setJumlah_pengajuan_pembiayaan(String.valueOf(querySnapshot.getString("Jumlah Pengajuan Pembiayaan")));
                                p.setTipe_tujuan_pembiayaan(String.valueOf(querySnapshot.getString("Tipe Tujuan Pembiayaan")));
                                p.setAlamatLatitude(String.valueOf(querySnapshot.getString("Alamat Latitude")));
                                p.setAlamatLongitude(String.valueOf(querySnapshot.getString("Alamat Longitude")));
                                p.setProvinsi(String.valueOf(querySnapshot.getString("Provinsi")));
                                p.setKota_kabupaten(String.valueOf(querySnapshot.getString("KotaKabupaten")));
                                p.setKecamatan(String.valueOf(querySnapshot.getString("Kecamatan")));
                                p.setKodePos(String.valueOf(querySnapshot.getString("Kode Pos")));
                                p.setAlamatLengkap(String.valueOf(querySnapshot.getString("Alamat Lengkap")));
                                p.setUrlphotoKTP(String.valueOf(querySnapshot.getString("uriPhotoKtp")));
                                p.setUrlphotoKK(String.valueOf(querySnapshot.getString("uriPhotoKk")));
                                p.setUrlphotoJaminan(String.valueOf(querySnapshot.getString("uriPhotoJaminan")));
                                list.add(p);
                            }
                        }
                    }
                }
                view.addAdapter(list);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onfailure();
            }
        });
    }

    public String status(String statusPengajuan){
        String status = null;
        if (statusPengajuan.equals("0")){
            status = "Submitted";
        }else if (statusPengajuan.equals("1")){
            status ="Arrange To Sales";
        }else if (statusPengajuan.equals("2")){
            status = "confirmed sales";
        }else if (statusPengajuan.equals("3")){
            status = "sales check location";
        }else if (statusPengajuan.equals("4")){
            status = "collected document";
        }else if (statusPengajuan.equals("5")){
            status = "submission accepted";
        }else if (statusPengajuan.equals("6")){
            status = "submission rejected";
        }

        return status;
    }
}
