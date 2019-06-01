package com.example.pembiayaanqu.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.adapter.RecyclerViewAdapter;
import com.example.pembiayaanqu.model.menu;

import java.util.ArrayList;
import java.util.List;

public class bank extends AppCompatActivity {

    private RecyclerView myrecycleview;
    private List<menu> listMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bank);

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listMenu = new ArrayList<>();
        listMenu.add(new menu("Mandiri Syariah",R.drawable.logo_bank_mandiri_syariah));
        listMenu.add(new menu("Bank Mandiri",R.drawable.mandiri));
        listMenu.add(new menu("Bank BRI",R.mipmap.bri));
        listMenu.add(new menu("Bank BCA",R.mipmap.bca));
        listMenu.add(new menu("Bank Danamond",R.mipmap.ddanamon));
        listMenu.add(new menu("Bank BNI",R.mipmap.bni));
        listMenu.add(new menu("Bank Mega",R.drawable.mega));

        myrecycleview = findViewById(R.id.recycleBank);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getBaseContext(), listMenu);
        myrecycleview.setLayoutManager(new GridLayoutManager(bank.this,4));
        myrecycleview.setAdapter(recyclerAdapter);
    }
}
