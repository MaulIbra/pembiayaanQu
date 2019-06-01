package com.example.pembiayaanqu.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.pembiayaanqu.contract.datacatatanKeuangan;
import com.example.pembiayaanqu.model.catatanKeuangan;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class catatanKeuanganPresenter implements datacatatanKeuangan.presenter {

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    datacatatanKeuangan.view view;
    Integer totalPemasukkan;
    ArrayList<catatanKeuangan> list;

    public catatanKeuanganPresenter(datacatatanKeuangan.view view) {
        this.view = view;
    }

    @Override
    public void saveData(String namaCatatan, String jumlahCatatan, String jenisCatatan) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        final Map<String, Object> catatanKeuangan = new HashMap<>();
        catatanKeuangan.put("namaCatatan",namaCatatan);
        catatanKeuangan.put("jumlah",jumlahCatatan);
        catatanKeuangan.put("jenisCatatan",jenisCatatan);

        firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid()).collection("catatanKeuangan").document()
                .set(catatanKeuangan)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        view.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onFailure();
            }
        });

    }

    @Override
    public void totalPemasukkan() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            firebaseFirestore.collection("users").document(user.getUid()).collection("catatanKeuangan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    int total_pemasukkan = 0;
                    for (DocumentSnapshot querySnapshot : task.getResult()){
                        if (querySnapshot.exists()){
                            if (String.valueOf(querySnapshot.getString("jenisCatatan")).equals("Pemasukan")){
                                total_pemasukkan = total_pemasukkan + Integer.parseInt(String.valueOf(querySnapshot.getString("jumlah")));
                            }
                        }
                    }
                    view.showTotalPemasukkan(total_pemasukkan);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    view.onFailure();
                }
            });
        }
        else{
            view.showTotalPemasukkan(0);
        }
    }

    @Override
    public void totalPengeluaran() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user!=null){
            firebaseFirestore.collection("users").document(user.getUid()).collection("catatanKeuangan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    int total_pengeluaran = 0;
                    for (DocumentSnapshot querySnapshot : task.getResult()){
                        if (querySnapshot.exists()){
                            if (String.valueOf(querySnapshot.getString("jenisCatatan")).equals("Pengeluaran")){
                                total_pengeluaran = total_pengeluaran + Integer.parseInt(String.valueOf(querySnapshot.getString("jumlah")));
                            }
                        }
                    }
                    view.showTotalPengeluaran(total_pengeluaran);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    view.onFailure();
                }
            });
        }else{
            view.showTotalPengeluaran(0);
        }

    }

    @Override
    public void totalSaldo(Integer pemasukkan, Integer pengeluaran) {
        Integer totalSaldo = pemasukkan - pengeluaran;
        view.showTotalSaldo(totalSaldo);
    }

    @Override
    public void loadData() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user!=null){
            firebaseFirestore.collection("users").document(user.getUid()).collection("catatanKeuangan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    list = new ArrayList<>();
                    for (DocumentSnapshot querySnapshot : task.getResult()){
                        if (querySnapshot.exists()){
                            catatanKeuangan c = new catatanKeuangan();
                            c.setNamaCatatan(String.valueOf(querySnapshot.getString("namaCatatan")));
                            c.setJumlahKeuangan(String.valueOf(querySnapshot.getString("jumlah")));
                            c.setJenisCatatan(String.valueOf(querySnapshot.getString("jenisCatatan")));
                            list.add(c);

                        }
                    }
                    view.addAdapter(list);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    view.onFailure();
                }
            });
        }else{

        }

    }
}
