package com.example.pembiayaanqu.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.adapter.PencarianAdapter;
import com.example.pembiayaanqu.adapter.pengajuanAdapter;
import com.example.pembiayaanqu.contract.dataLoadPengajuan;
import com.example.pembiayaanqu.model.pengajuan;
import com.example.pembiayaanqu.presenter.loadDataPengajuanPresenter;

import java.util.ArrayList;

public class pengajuan_semua extends Fragment implements dataLoadPengajuan.view {
    RecyclerView recyclerView;
    pengajuanAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pengajuan_semua,container,false);

        recyclerView = v.findViewById(R.id.recyclePengajuansemua);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadDataPengajuanPresenter loadDataPengajuanPresenter = new loadDataPengajuanPresenter(this);
        loadDataPengajuanPresenter.loadDataPengajuan("pengajuanSemua");
        return v;
    }

    @Override
    public void onfailure() {
        Toast.makeText(getContext(),"Data Kosong",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void addAdapter(ArrayList<pengajuan> list) {
        if (list != null){
            adapter = new pengajuanAdapter(getContext(),list);
            recyclerView.setAdapter(adapter);
        }
    }
}
