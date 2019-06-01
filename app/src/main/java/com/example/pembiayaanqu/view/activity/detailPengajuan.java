package com.example.pembiayaanqu.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pembiayaanqu.R;

public class detailPengajuan extends AppCompatActivity {
    private EditText kategoriPengajuan;
    private EditText tujuanPengajuan;
    private EditText cabangPengajuan;
    private EditText namaLengkap;
    private EditText nomorHP ;
    private EditText umur;
    private EditText status;
    private EditText jenis_kelamin;
    private EditText pendidikanTerakhir;
    private EditText pekerjaanUsaha;
    private EditText email;
    private EditText noNPWP;
    private EditText noKTP;
    private EditText lamaBekerjaUsaha;
    private EditText namaPerusahaanSaatBekerjaUsaha;
    private EditText jumlahPengajuanPembiayaan;
    private EditText tipeTujuanPembiayaan;
    private EditText kodePos;
    private EditText alamatLengkap;
    private TextView latitude;
    private TextView longitude;
    private EditText provinsi;
    private EditText kota_kabupaten;
    private ImageView imageKTP;
    private ImageView imageKK;
    private ImageView imageJaminan;
    private EditText kecamatan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailpengajuan);
        kategoriPengajuan = findViewById(R.id.kategoriPengajuan);
        tujuanPengajuan = findViewById(R.id.tujuanPengajuan);
        cabangPengajuan = findViewById(R.id.cabangPengajuan);
        namaLengkap = findViewById(R.id.namaLengkap);
        nomorHP = findViewById(R.id.nomorHP);
        umur = findViewById(R.id.umur);
        status = findViewById(R.id.status);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        pendidikanTerakhir = findViewById(R.id.pendidikanTerakhir);
        pekerjaanUsaha = findViewById(R.id.pekerjaanUsaha);
        email = findViewById(R.id.email);
        noNPWP = findViewById(R.id.noNPWP);
        noKTP = findViewById(R.id.noNPWP);
        lamaBekerjaUsaha = findViewById(R.id.lamaBekerjaUsaha);
        namaPerusahaanSaatBekerjaUsaha = findViewById(R.id.namaPerusahaanSaatBekerjaUsaha);
        jumlahPengajuanPembiayaan = findViewById(R.id.jumlahPengajuanPembiayaan);
        tipeTujuanPembiayaan = findViewById(R.id.tipeTujuanPembiayaan);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        provinsi = findViewById(R.id.provinsi);
        kota_kabupaten = findViewById(R.id.kota_kabupaten);
        kecamatan = findViewById(R.id.kecamatan);
        kodePos = findViewById(R.id.kodePos);
        alamatLengkap = findViewById(R.id.alamatLengkap);
        imageKTP = findViewById(R.id.imageKTP);
        imageKK = findViewById(R.id.imageKK);
        imageJaminan = findViewById(R.id.imageJaminan);

        Intent i = getIntent();
        kategoriPengajuan.setText(i.getStringExtra("kategoriPengajuan"));
        tujuanPengajuan.setText(i.getStringExtra("tujuanPengajuan"));
        cabangPengajuan.setText(i.getStringExtra("cabangPengajuan"));
        namaLengkap.setText(i.getStringExtra("namaLengkap"));
        nomorHP.setText(i.getStringExtra("nomorHP"));
        umur.setText(i.getStringExtra("umur"));
        status.setText(i.getStringExtra("status"));
        jenis_kelamin.setText(i.getStringExtra("jenis_kelamin"));
        pendidikanTerakhir.setText(i.getStringExtra("pendidikanTerakhir"));
        pekerjaanUsaha.setText(i.getStringExtra("pekerjaanUsaha"));
        email.setText(i.getStringExtra("email"));
        noNPWP.setText(i.getStringExtra("noNPWP"));
        noKTP.setText(i.getStringExtra("noKTP"));
        lamaBekerjaUsaha.setText(i.getStringExtra("lamaBekerjaUsaha"));
        namaPerusahaanSaatBekerjaUsaha.setText(i.getStringExtra("namaPerusahaanSaatBekerjaUsaha"));
        jumlahPengajuanPembiayaan.setText(i.getStringExtra("jumlahPengajuanPembiayaan"));
        tipeTujuanPembiayaan.setText(i.getStringExtra("tipeTujuanPembiayaan"));
        provinsi.setText(i.getStringExtra("provinsi"));
        kota_kabupaten.setText(i.getStringExtra("kota_kabupaten"));
        kecamatan.setText(i.getStringExtra("kecamatan"));
        kodePos.setText(i.getStringExtra("kodePos"));
        alamatLengkap.setText(i.getStringExtra("alamatLengkap"));
        latitude.setText(i.getStringExtra("latitude"));
        longitude.setText(i.getStringExtra("longitude"));

        Glide.with(getBaseContext())
                .load(i.getStringExtra("imageKTP"))
                .fitCenter()
                .into(imageKTP);

        Glide.with(getBaseContext())
                .load(i.getStringExtra("imageKK"))
                .fitCenter()
                .into(imageKK);
        Glide.with(getBaseContext())
                .load(i.getStringExtra("imageJaminan"))
                .fitCenter()
                .into(imageJaminan);
    }
}
