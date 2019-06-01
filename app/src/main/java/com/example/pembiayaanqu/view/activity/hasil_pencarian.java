package com.example.pembiayaanqu.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.adapter.PencarianAdapter;
import com.example.pembiayaanqu.model.pencarian;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class hasil_pencarian extends AppCompatActivity {

    ArrayList<pencarian> list;
    private FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    PencarianAdapter adapter;
    private RelativeLayout frameProgressbar;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil_pencarian);

        frameProgressbar = findViewById(R.id.frameProgressbar);
        progressBar = findViewById(R.id.progress_bar);

        Intent i = getIntent();
        String kategoriid = i.getStringExtra("kategori");
        String plafondMinid = i.getStringExtra("plafondMin");
        String plafondMaxid = i.getStringExtra("plafondMax");



        String kategori = extractPosition(kategoriid);
        Integer plafondMin = Integer.parseInt(plafondMinid);
        Integer plafondMax = Integer.parseInt(plafondMaxid);
        recyclerView = findViewById(R.id.recyclePencarian);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        loadDataFromFirebase(kategori,plafondMin,plafondMax);


    }

    public String extractPosition(String position){
        String value = null;
        if (position.equals("0")){
            value = "semua";
        }else if(position.equals("1")){
            value = "layout_bank";
        }else if (position.equals("2")){
            value = "multifinance";
        }else if(position.equals("3")){
            value = "koperasi";
        }else if (position.equals("4")){
            value = "programCSR";
        }else if (position.equals("5") ){
            value = "gadai";
        }
        return value;
    }

    public Integer extractPostionplafound(String position){
        Integer value = null;
        if (position.equals("0")){
            value = 100000000;
        }else if(position.equals("1")){
            value = 200000000;
        }else if (position.equals("2")){
            value = 300000000;
        }else if(position.equals("3")){
            value = 400000000;
        }else if (position.equals("4")){
            value = 500000000;
        }else if (position.equals("5") ){
            value = 600000000;
        }else if (position.equals("6")){
            value = 700000000;
        }else if(position.equals("7")){
            value = 800000000;
        }else if (position.equals("8")){
            value = 900000000;
        }else if (position.equals("9") ){
            value = 1000000000;
        }
        return value;
    }

    private void loadDataFromFirebase(final String kategori, final Integer plafondmin, final Integer plafondmax){

        ArrayList<String> kategoritemp = new ArrayList<String>();

        kategoritemp.add("layout_bank");
        kategoritemp.add("multifinance");
        kategoritemp.add("koperasi");
        kategoritemp.add("programCSR");
        kategoritemp.add("gadai");


        if (kategori.equals("semua")){
                for (int i = 0; i<kategoritemp.size();i++){
                    firebaseFirestore.collection(kategoritemp.get(i)).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            for (DocumentSnapshot querySnapshot : task.getResult()){
                                if (querySnapshot.get("plafondMin") !=null && querySnapshot.get("plafondMax") != null){
                                    if ((((long)querySnapshot.get("plafondMin") >= plafondmin) && ((long)querySnapshot.get("plafondMin")<= plafondmax)) || (((long)querySnapshot.get("plafondMax")>= plafondmin)&& ((long)querySnapshot.get("plafondMax")<= plafondmax)) ){
                                        pencarian p = new pencarian();
                                        p.setProdukPembiayaan(querySnapshot.getString("namaProduk"));
                                        p.setPlafondMin(String.valueOf(querySnapshot.get("plafondMin")));
                                        p.setPlafondMax(String.valueOf(querySnapshot.get("plafondMax")));
                                        list.add(p);
                                    }
                                }
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(hasil_pencarian.this,"data kosong",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            adapter = new PencarianAdapter(hasil_pencarian.this,list);
            recyclerView.setAdapter(adapter);
        }else{
            showProgressbar();
            firebaseFirestore.collection(kategori).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot querySnapshot : task.getResult()){
                        if (querySnapshot.get("plafondMin") !=null && querySnapshot.get("plafondMax") != null){
                            if (plafondmax == 0 && plafondmin !=0){
                                if ((long) querySnapshot.get("plafondMin") >= plafondmin){
                                    pencarian p = new pencarian();
                                    p.setProdukPembiayaan(querySnapshot.getString("namaProduk"));
                                    p.setPlafondMin(String.valueOf(querySnapshot.get("plafondMin")));
                                    p.setPlafondMax(String.valueOf(querySnapshot.get("plafondMax")));
                                    list.add(p);
                                }
                            }else if (plafondmax != 0 && plafondmin ==0){
                                if ((long) querySnapshot.get("plafondMax") <= plafondmax){
                                    pencarian p = new pencarian();
                                    p.setProdukPembiayaan(querySnapshot.getString("namaProduk"));
                                    p.setPlafondMin(String.valueOf(querySnapshot.get("plafondMin")));
                                    p.setPlafondMax(String.valueOf(querySnapshot.get("plafondMax")));
                                    list.add(p);
                                }
                            }
                            else if (plafondmax != 0 && plafondmin != 0) {
                                if ((((long) querySnapshot.get("plafondMin") >= plafondmin) && ((long) querySnapshot.get("plafondMin") <= plafondmax)) || (((long) querySnapshot.get("plafondMax") >= plafondmin) && ((long) querySnapshot.get("plafondMax") <= plafondmax))) {
                                    pencarian p = new pencarian();
                                    p.setProdukPembiayaan(querySnapshot.getString("namaProduk"));
                                    p.setPlafondMin(String.valueOf(querySnapshot.get("plafondMin")));
                                    p.setPlafondMax(String.valueOf(querySnapshot.get("plafondMax")));
                                    list.add(p);
                                }
                            }
                        }
                    }
                    hideProgressbar();
                    adapter = new PencarianAdapter(hasil_pencarian.this,list);
                    recyclerView.setAdapter(adapter);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
           //         hideProgressbar();
                    Toast.makeText(hasil_pencarian.this,"data kosong",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void showProgressbar(){
        frameProgressbar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        frameProgressbar.setClickable(true);
    }

    public void hideProgressbar(){
        frameProgressbar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        frameProgressbar.setClickable(true);
    }
}
