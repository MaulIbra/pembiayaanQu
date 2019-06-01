package com.example.pembiayaanqu.view.activity;

import android.os.Bundle;
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

public class multifinance extends AppCompatActivity {

    private RecyclerView myrecycleview;
    private List<menu> listMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multifinance);

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listMenu = new ArrayList<>();
        listMenu.add(new menu("BFI Finance Indonesia",R.mipmap.bfi));
        listMenu.add(new menu("FIF",R.mipmap.fif));
        listMenu.add(new menu("Adira Dinamika Multifinance",R.mipmap.adira));

        myrecycleview = findViewById(R.id.recycleBank);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getBaseContext(), listMenu);
        myrecycleview.setLayoutManager(new GridLayoutManager(multifinance.this,4));
        myrecycleview.setAdapter(recyclerAdapter);
    }
}
