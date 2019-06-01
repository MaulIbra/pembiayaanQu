package com.example.pembiayaanqu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.model.menu;
import com.example.pembiayaanqu.view.activity.detail_menu_csr;

import java.util.List;

public class RecyclerViewAdapterCSR extends RecyclerView.Adapter<RecyclerViewAdapterCSR.MyViewHolder> {
    Context mContext;
    List<menu> mMenu;

    public RecyclerViewAdapterCSR(Context mContext, List<menu> mMenu) {
        this.mContext = mContext;
        this.mMenu = mMenu;
    }

    @Override
    public RecyclerViewAdapterCSR.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_bank, viewGroup,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterCSR.MyViewHolder myViewHolder, final int i) {
        myViewHolder.labelMenu.setText(mMenu.get(i).getNama_bank());
        myViewHolder.image_Menu.setImageResource(mMenu.get(i).getLogo_bank());
        myViewHolder.cardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(mContext, detail_menu_csr.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("label_menu",mMenu.get(i).getNama_bank());
                mContext.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenu.size();
    }

    public  static  class  MyViewHolder extends RecyclerView.ViewHolder{
        private TextView labelMenu;
        private ImageView image_Menu;
        private TextView kategori;
        ConstraintLayout cardMenu;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            labelMenu= itemView.findViewById(R.id.labelBank);
            image_Menu = itemView.findViewById(R.id.logo_bank);
            cardMenu = itemView.findViewById(R.id.cardMenu);

        }
    }
}