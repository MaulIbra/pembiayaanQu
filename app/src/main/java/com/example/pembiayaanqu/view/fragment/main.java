package com.example.pembiayaanqu.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.view.activity.bank;
import com.example.pembiayaanqu.view.activity.catatan_keuangan;
import com.example.pembiayaanqu.view.activity.gadai;
import com.example.pembiayaanqu.view.activity.koperasi;
import com.example.pembiayaanqu.view.activity.multifinance;
import com.example.pembiayaanqu.view.activity.notLogin;
import com.example.pembiayaanqu.view.activity.pengajuan_pembiayaan;
import com.example.pembiayaanqu.view.activity.program_csr;
import com.example.pembiayaanqu.view.activity.riwayat_pembiayaan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class main extends Fragment implements View.OnClickListener {

    ImageView bank;
    ImageView multifinance;
    ImageView koperasi;
    ImageView programCSR;
    ImageView gadai;
    ImageView riwayatpembiayaan;
    Button pengajuanPembiayaan;
    ImageView catatanKeuangan;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        mAuth = FirebaseAuth.getInstance();
        user  = mAuth.getCurrentUser();

        bank  = view.findViewById(R.id.bank);
        multifinance = view.findViewById(R.id.multifinance);
        koperasi = view.findViewById(R.id.koperasi);
        programCSR = view.findViewById(R.id.csr);
        gadai = view.findViewById(R.id.gadai);
        riwayatpembiayaan = view.findViewById(R.id.history_pembiayaan);
        pengajuanPembiayaan = view.findViewById(R.id.button_pengajuan);
        catatanKeuangan = view.findViewById(R.id.catatan_keuangan);

        bank.setOnClickListener(this);
        multifinance.setOnClickListener(this);
        koperasi.setOnClickListener(this);
        programCSR.setOnClickListener(this);
        gadai.setOnClickListener(this);
        riwayatpembiayaan.setOnClickListener(this);
        pengajuanPembiayaan.setOnClickListener(this);
        catatanKeuangan.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == bank ){
            Intent bank = new Intent(getActivity(),bank.class);
            startActivity(bank);
        }
        if (v == multifinance){
            Intent multifinance = new Intent(getActivity(),multifinance.class);
            startActivity(multifinance);
        }
        if (v == koperasi){
            Intent koperasi = new Intent(getActivity(),koperasi.class);
            startActivity(koperasi);
        }
        if (v == programCSR){
            Intent programCSR = new Intent(getActivity(), program_csr.class);
            startActivity(programCSR);
        }
        if (v == gadai){
            Intent gadai = new Intent(getActivity(),gadai.class);
            startActivity(gadai);
        }
        if (v == riwayatpembiayaan){
            if (user != null){
                Intent history_pembiayaan = new Intent(getActivity(), riwayat_pembiayaan.class);
                startActivity(history_pembiayaan);
            }else{
                startActivity(new Intent(getActivity(), notLogin.class));
            }
        }
        if (v == pengajuanPembiayaan){
            Intent pengajuanbiaya = new Intent(getActivity(), pengajuan_pembiayaan.class);
            pengajuanbiaya.putExtra("tujuan_pengajuan","");
            startActivity(pengajuanbiaya);
        }
        if (v == catatanKeuangan){
            if (user != null){
                Intent catatan = new Intent(getActivity(), catatan_keuangan.class);
                startActivity(catatan);
            }else{
                startActivity(new Intent(getActivity(), notLogin.class));
            }
        }
    }
}
