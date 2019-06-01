package com.example.pembiayaanqu.view.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.adapter.RecyclerViewAdapterCSR;
import com.example.pembiayaanqu.model.menu;

import java.util.ArrayList;
import java.util.List;

public class program_csr extends AppCompatActivity {
    private RecyclerView myrecycleview;
    private List<menu> listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_csr);

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listMenu = new ArrayList<>();
        listMenu.add(new menu("Bukit Asam",R.mipmap.bukit_asam));
        listMenu.add(new menu("Wilmar",R.mipmap.wilmar));
        listMenu.add(new menu("Chandra Asri Peduli",R.mipmap.chandra_asri_peduli));
        myrecycleview = findViewById(R.id.recycleBank);
        RecyclerViewAdapterCSR recyclerAdapter = new RecyclerViewAdapterCSR(getBaseContext(), listMenu);
        myrecycleview.setLayoutManager(new GridLayoutManager(program_csr.this,4));
        myrecycleview.setAdapter(recyclerAdapter);
    }
}


