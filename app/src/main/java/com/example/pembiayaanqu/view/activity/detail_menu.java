package com.example.pembiayaanqu.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pembiayaanqu.R;

public class detail_menu extends AppCompatActivity {

    ImageView image;
    private TextView labelPerusahaan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_menu);

        labelPerusahaan = findViewById(R.id.nama_perusahaan);
        Intent in = getIntent();
        final String label_Perusahaan = in.getExtras().getString("label_menu");

        labelPerusahaan.setText(label_Perusahaan);
        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView pengajuan = findViewById(R.id.pengajuan_pembiayaan);
        pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pengajuan = new Intent(detail_menu.this,pengajuan_pembiayaan.class);
                pengajuan.putExtra("tujuan_pengajuan",label_Perusahaan);
                startActivity(pengajuan);
            }
        });

        ImageView kalkulator = findViewById(R.id.kalkulator_pembiayaan);
        kalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kalkulator = new Intent(detail_menu.this,kalkulator_pembiayaan.class);
                startActivity(kalkulator);
            }
        });

    }
}
