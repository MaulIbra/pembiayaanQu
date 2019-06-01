package com.example.pembiayaanqu.presenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.pembiayaanqu.contract.dataLoad;
import com.example.pembiayaanqu.model.pencarian;
import com.example.pembiayaanqu.model.province;
import com.example.pembiayaanqu.view.activity.edit_profile;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class loadDataPresenter implements dataLoad.presenter {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    ArrayList<province> list;

    dataLoad.view view;

    public loadDataPresenter(dataLoad.view view) {
        this.view = view;
    }



    @Override
    public void readData() {
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            firebaseFirestore.collection("users").document(user.getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                HashMap<String,String> data = new HashMap<>();
                                data.put("Tujuan Pengajuan",documentSnapshot.getString("Tujuan Pengajuan"));
                                data.put("Nama Lengkap",documentSnapshot.getString("Nama Lengkap"));
                                data.put("Nomor Handphone",documentSnapshot.getString("Nomor Handphone"));
                                data.put("Email",documentSnapshot.getString("Email"));
                                data.put("Umur",documentSnapshot.getString("Umur"));
                                data.put("Status",documentSnapshot.getString("Status"));
                                data.put("Jenis Kelamin",documentSnapshot.getString("Jenis Kelamin"));
                                data.put("Pendidikan Terakhir",documentSnapshot.getString("Pendidikan Terakhir"));
                                data.put("Pekerjaan atau Usaha",documentSnapshot.getString("Pekerjaan atau Usaha"));
                                data.put("Nomor NPWP",documentSnapshot.getString("Nomor NPWP"));
                                data.put("Nomor KTP",documentSnapshot.getString("Nomor KTP"));
                                data.put("Lama bekerja atau Usaha",documentSnapshot.getString("Lama bekerja atau Usaha"));
                                data.put("Nama Perusahaan saat Bekerja",documentSnapshot.getString("Nama Perusahaan saat Bekerja"));
                                data.put("Jumlah Pengajuan Pembiayaan",documentSnapshot.getString("Jumlah Pengajuan Pembiayaan"));
                                data.put("Tipe Tujuan Pembiayaan",documentSnapshot.getString("Tipe Tujuan Pembiayaan"));
                                data.put("Alamat Latitude",documentSnapshot.getString("Alamat Latitude"));
                                data.put("Alamat Longitude",documentSnapshot.getString("Alamat Longitude"));
                                data.put("Provinsi",documentSnapshot.getString("Provinsi"));
                                data.put("KotaKabupaten",documentSnapshot.getString("KotaKabupaten"));
                                data.put("Kecamatan",documentSnapshot.getString("Kecamatan"));
                                data.put("Kode Pos",documentSnapshot.getString("Kode Pos"));
                                data.put("Alamat Lengkap",documentSnapshot.getString("Alamat Lengkap"));
                                data.put("uriPhotoKk",documentSnapshot.getString("uriPhotoKk"));
                                data.put("uriPhotoKtp",documentSnapshot.getString("uriPhotoKtp"));
                                data.put("uriPhotoJaminan",documentSnapshot.getString("uriPhotoJaminan"));
                                view.displayData(data);
                            }else{
                                HashMap<String,String> data = new HashMap<>();
                                data.put("Nama Lengkap",user.getDisplayName());
                                data.put("Nomor Handphone",user.getPhoneNumber());
                                data.put("Email",user.getEmail());
                                view.displayData(data);
                            }
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

    @Override
    public void saveDataPengajuan(HashMap<String, String> pengajuanData) {
        view.showProgressBar();
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseFirestore.collection("pengajuanPembiayaan").document(mAuth.getUid()).collection("pengajuan").document().set(pengajuanData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                view.hideProgressBar();
                view.displayToastSuccess();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.hideProgressBar();
                        view.displayToastFailure();
                    }
                });
    }

    @Override
    public void uploadImage(HashMap<String,Uri> data) {
        String STORAGE_PATH = "user/";
        view.showProgressBar();
        for (Map.Entry me : data.entrySet()) {
            final StorageReference ref = storageReference.child(STORAGE_PATH + mAuth.getUid() + "/" + mAuth.getUid() + me.getKey().toString() + ".JPG");
            final String a = me.getKey().toString();
            ref.putFile((Uri) me.getValue()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap<String, String> uridata = new HashMap<>();
                            uridata.put(a, uri.toString());
                            view.getdataUri(uridata);
                            view.hideProgressBar();
                            view.displayToastUploadSuccess();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void savedata(HashMap<String, String> userData) {
        view.showProgressBar();
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseFirestore.collection("users").document(mAuth.getUid()).set(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                view.hideProgressBar();
                view.displayToastSuccess();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.hideProgressBar();
                        view.displayToastFailure();
                    }
                });
    }

    @Override
    public void getImage(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        view.resultActivity(requestCode,intent);
    }

    @Override
    public void readProvince() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("provinsi").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                String[] spinnerArray = new String[task.getResult().size()];
                HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();
                int i = 0;
                for (DocumentSnapshot querySnapshot: task.getResult()){
                    spinnerMap.put(i,querySnapshot.getId());
                    spinnerArray[i] = querySnapshot.get("namaProvinsi").toString();
                    i= i+1;
                }
               view.spinnerData(spinnerArray,spinnerMap);
            }
        });
    }

    @Override
    public void readKotaKab(String id) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("provinsi").document(id).collection("kabupatenkota").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String[] spinnerArray = new String[task.getResult().size()];
                HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();
                int i = 0;
                for (DocumentSnapshot querySnapshot: task.getResult()){
                    spinnerMap.put(i,querySnapshot.getId());
                    spinnerArray[i] = querySnapshot.get("namaKabupatenKota").toString();
                    i= i+1;
                }
                view.spinnerDataKota(spinnerArray,spinnerMap);
            }
        });
    }

    @Override
    public void readKategoriPengajuan() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("kategoriPengajuan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                String[] spinnerArray = new String[task.getResult().size()];
                HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();
                int i = 0;
                for (DocumentSnapshot querySnapshot: task.getResult()){
                    spinnerMap.put(i,querySnapshot.getId());
                    spinnerArray[i] = querySnapshot.get("kategori").toString();
                    i= i+1;
                }
                view.spinerDataKategoriPengajuan(spinnerArray,spinnerMap);
            }
        });
    }

    @Override
    public void readDetailKategoriPengajuan(String id) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("kategoriPengajuan").document(id).collection("detail").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String[] spinnerArray = new String[task.getResult().size()];
                HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();
                int i = 0;
                for (DocumentSnapshot querySnapshot: task.getResult()){
                    spinnerMap.put(i,querySnapshot.getId());
                    spinnerArray[i] = querySnapshot.get("name").toString();
                    i= i+1;
                }
                view.spinerDataDetailKategoriPengajuan(spinnerArray,spinnerMap);
            }
        });
    }

    @Override
    public void readCabangPengajuan(String idDetail, String idBranch) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("kategoriPengajuan").document(idDetail).collection("detail").document(idBranch).collection("branch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String[] spinnerArray = new String[task.getResult().size()];
                HashMap<Integer,String> spinnerMap = new HashMap<Integer, String>();
                int i = 0;
                for (DocumentSnapshot querySnapshot: task.getResult()){
                    spinnerMap.put(i,querySnapshot.getId());
                    spinnerArray[i] = querySnapshot.get("namaBranch").toString();
                    i= i+1;
                }
                view.spinerDataCabangPengajuan(spinnerArray,spinnerMap);
            }
        });
    }
}
