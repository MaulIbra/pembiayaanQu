package com.example.pembiayaanqu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.model.catatanKeuangan;
import com.example.pembiayaanqu.model.pengajuan;

import java.util.ArrayList;

public class catatanAdapter extends RecyclerView.Adapter<catatanAdapter.MyViewHolder> {

    Context context;
    ArrayList<catatanKeuangan> catatanKeuangans;

    public catatanAdapter(Context context, ArrayList<catatanKeuangan> catatanKeuangans) {
        this.context = context;
        this.catatanKeuangans = catatanKeuangans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_catatan_keuangan,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.namaCatatan.setText(catatanKeuangans.get(i).getNamaCatatan());
        myViewHolder.jumlah.setText(catatanKeuangans.get(i).getJumlahKeuangan());
        myViewHolder.jenisCatatan.setText(catatanKeuangans.get(i).getJenisCatatan());
    }

    @Override
    public int getItemCount() {
        return catatanKeuangans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView jenisCatatan,jumlah,namaCatatan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jenisCatatan = itemView.findViewById(R.id.jenisCatatan);
            jumlah = itemView.findViewById(R.id.jumlah);
            namaCatatan = itemView.findViewById(R.id.namaCatatan);
        }
    }
}
