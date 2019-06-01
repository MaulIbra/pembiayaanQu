package com.example.pembiayaanqu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.model.pencarian;

import java.util.ArrayList;

public class PencarianAdapter extends RecyclerView.Adapter<PencarianAdapter.MyViewHolder> {

    Context context;
    ArrayList<pencarian> pencarian;

    public PencarianAdapter(Context context, ArrayList<com.example.pembiayaanqu.model.pencarian> pencarian) {
        this.context = context;
        this.pencarian = pencarian;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pencarian,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.produkPembiayaan.setText(pencarian.get(i).getProdukPembiayaan());
        myViewHolder.plafondMin.setText(pencarian.get(i).getPlafondMin());
        myViewHolder.plafondMax.setText(pencarian.get(i).getPlafondMax());
    }

    @Override
    public int getItemCount() {
        return pencarian.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView produkPembiayaan,plafondMin,plafondMax;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            produkPembiayaan = itemView.findViewById(R.id.produkPembiayaan);
            plafondMin = itemView.findViewById(R.id.plafondMin);
            plafondMax = itemView.findViewById(R.id.plafondMax);
        }
    }
}
