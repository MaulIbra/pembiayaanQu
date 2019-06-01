package com.example.pembiayaanqu.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.adapter.catatanAdapter;
import com.example.pembiayaanqu.contract.datacatatanKeuangan;
import com.example.pembiayaanqu.model.catatanKeuangan;
import com.example.pembiayaanqu.presenter.catatanKeuanganPresenter;

import java.util.ArrayList;

public class catatan_keuangan extends AppCompatActivity implements datacatatanKeuangan.view{
    Dialog dialog;
    Button submit;
    TextView totalPemasukkan;
    TextView totalPengeluaran;
    TextView totalSaldo;
    FloatingActionButton tambah;
    EditText namaCatatan;
    ImageView backActivity;
    EditText jumlahPenambahan;
    RadioGroup jenisCatatanGroup;
    RadioButton jenisCatatan;

    private ImageView closePopup;
    RecyclerView recyclerView;
    catatanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_catatan_keuangan);

        backActivity = findViewById(R.id.backArrow);
        backActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(catatan_keuangan.this,home.class));
            }
        });

        recyclerView = findViewById(R.id.recycleCatatanKeuangan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tambah = findViewById(R.id.tambah_data);
        totalSaldo = findViewById(R.id.total_saldo);
        totalPemasukkan = findViewById(R.id.total_pemasukkan);
        totalPengeluaran = findViewById(R.id.total_pengeluaran);
        dialog = new Dialog(this);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });
        catatanKeuanganPresenter catatanKeuanganPresenter = new catatanKeuanganPresenter(this);
        catatanKeuanganPresenter.totalPemasukkan();
        catatanKeuanganPresenter.totalPengeluaran();
        catatanKeuanganPresenter.loadData();

    }


    @Override
    public void showPopup() {
        dialog.setContentView(R.layout.popup_catatan_keuangan);
        closePopup = dialog.findViewById(R.id.closePopup);
        submit = dialog.findViewById(R.id.submit);
        namaCatatan = dialog.findViewById(R.id.parameter);
        jumlahPenambahan = dialog.findViewById(R.id.jumlahPenambahan);
        jenisCatatanGroup = dialog.findViewById(R.id.jenisCatatan);


        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = jenisCatatanGroup.getCheckedRadioButtonId();
                jenisCatatan = dialog.findViewById(selectedId);
                catatanKeuanganPresenter catatanKeuanganPresenter = new catatanKeuanganPresenter(catatan_keuangan.this);
                catatanKeuanganPresenter.saveData(namaCatatan.getText().toString(),jumlahPenambahan.getText().toString(),jenisCatatan.getText().toString());
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(catatan_keuangan.this,"data berhasil di tambahkan",Toast.LENGTH_SHORT).show();
        catatanKeuanganPresenter catatanKeuanganPresenter = new catatanKeuanganPresenter(this);
        catatanKeuanganPresenter.totalPemasukkan();
        catatanKeuanganPresenter.totalPengeluaran();
        dialog.dismiss();
    }

    @Override
    public void onFailure() {
        Toast.makeText(catatan_keuangan.this,"data gagal di tambahkan",Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public void showTotalPemasukkan(int pemasukan) {
        catatanKeuanganPresenter catatanKeuanganPresenter = new catatanKeuanganPresenter(this);
        totalPemasukkan.setText(String.valueOf(pemasukan));
        catatanKeuanganPresenter.totalSaldo(Integer.parseInt(totalPemasukkan.getText().toString()),Integer.parseInt(totalPengeluaran.getText().toString()));

    }

    @Override
    public void showTotalPengeluaran(int pengeluaran) {
        catatanKeuanganPresenter catatanKeuanganPresenter = new catatanKeuanganPresenter(this);
        totalPengeluaran.setText(String.valueOf(pengeluaran));
        catatanKeuanganPresenter.totalSaldo(Integer.parseInt(totalPemasukkan.getText().toString()),Integer.parseInt(totalPengeluaran.getText().toString()));

    }

    @Override
    public void showTotalSaldo(Integer sisaSaldo) {
        totalSaldo.setText(String.valueOf(sisaSaldo));
        catatanKeuanganPresenter catatanKeuanganPresenter = new catatanKeuanganPresenter(this);
        catatanKeuanganPresenter.loadData();
    }

    @Override
    public void addAdapter(ArrayList<catatanKeuangan> catatanKeuangans) {
        if (catatanKeuangans != null){
            adapter = new catatanAdapter(catatan_keuangan.this,catatanKeuangans);
            recyclerView.setAdapter(adapter);
        }
    }

}
