package com.example.pembiayaanqu.presenter;

import android.support.annotation.NonNull;

import com.example.pembiayaanqu.contract.registerMember;
import com.example.pembiayaanqu.model.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;


public class registerPresenter implements registerMember.presenter {

    FirebaseAuth firebaseAuth;
    private FirebaseFirestore Firestore;
    registerMember.view view;

    public registerPresenter(registerMember.view view) {
        this.view = view;
    }

    @Override
    public void createAuth(String userName, String phoneNumber, String email, String Password, final String collection) {
        firebaseAuth = FirebaseAuth.getInstance();
        Firestore = FirebaseFirestore.getInstance();

        final String em = email;
        final String pass = Password;
        user userDetail = new user(userName,phoneNumber,em,pass,null,null,null,null,null,null, null,null,null,null,null,null,null,null,null,null);

        final Map<String, Object> userFirestore = new HashMap<>();
        userFirestore.put("Nama Lengkap",userDetail.getUsername());
        userFirestore.put("Nomor Handphone",userDetail.getPhoneNumber());
        userFirestore.put("Email",userDetail.getEmail());
        userFirestore.put("Umur",userDetail.getUmur());
        userFirestore.put("Status",userDetail.getStatus());
        userFirestore.put("Jenis Kelamin",userDetail.getJenis_kelamin());
        userFirestore.put("Pendidikan Terakhir",userDetail.getPendidikan_terakhir());
        userFirestore.put("Pekerjaan atau Usaha",userDetail.getPekerjaan_usaha());
        userFirestore.put("Nomor NPWP",userDetail.getNomor_npwp());
        userFirestore.put("Nomor KTP",userDetail.getNomor_ktp());
        userFirestore.put("Lama bekerja atau Usaha",userDetail.getLama_bekerja_usaha());
        userFirestore.put("Nama Perusahaan saat Bekerja",userDetail.getNama_perusahaan_saat_bekerja_usaha());
        userFirestore.put("Jumlah Pengajuan Pembiayaan",userDetail.getJumlah_pengajuan_pembiayaan());
        userFirestore.put("Tipe Tujuan Pembiayaan",userDetail.getTipe_tujuan_pembiayaan());
        userFirestore.put("Lokasi Pengambilan",userDetail.getLokasi_pengambilan());
        userFirestore.put("Alamat Lokasi",userDetail.getAlamat_lokasi());
        userFirestore.put("uriPhotoKTP",userDetail.getUrlphotoKTP());
        userFirestore.put("uriPhotoKK",userDetail.getUrlphotoKK());

        view.showProgressBar();
        firebaseAuth.createUserWithEmailAndPassword(email,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            insertFirestore(collection,userFirestore);
                        }else{
                            view.displayToastFailure();
                        }
                    }
                });
    }


    public void insertFirestore(String collection, Map<String, Object> userFirestore) {
        Firestore.collection(collection).document(firebaseAuth.getCurrentUser().getUid())
                .set(userFirestore)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        view.hideProgressBar();
                        view.changeActivity();
                        view.displayToastSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.displayToastFailure();
                    }
                });
    }
}
