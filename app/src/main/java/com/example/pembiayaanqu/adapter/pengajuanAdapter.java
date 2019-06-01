package com.example.pembiayaanqu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.model.pencarian;
import com.example.pembiayaanqu.model.pengajuan;
import com.example.pembiayaanqu.view.activity.detailPengajuan;
import com.example.pembiayaanqu.view.activity.detail_menu;

import java.util.ArrayList;

public class pengajuanAdapter extends RecyclerView.Adapter<pengajuanAdapter.MyViewHolder> {

    Context context;
    ArrayList<pengajuan> pengajuan;

    public pengajuanAdapter(Context context, ArrayList<com.example.pembiayaanqu.model.pengajuan> pengajuan) {
        this.context = context;
        this.pengajuan = pengajuan;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pengajuan,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.kategoriPengajuan.setText(pengajuan.get(i).getKategoriPengajuan());
        myViewHolder.tujuanPengajuan.setText(pengajuan.get(i).getTujuanPengajuan());
        myViewHolder.branchPengajuan.setText(pengajuan.get(i).getBranchPengajuan());
        myViewHolder.tanggalPengajuan.setText(pengajuan.get(i).getTanggalPengajuan());
        myViewHolder.statusPengajuan.setText(pengajuan.get(i).getStatusPengajuan());
        myViewHolder.detailPengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, detailPengajuan.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("kategoriPengajuan",pengajuan.get(i).getKategoriPengajuan());
                detail.putExtra("tujuanPengajuan",pengajuan.get(i).getTujuanPengajuan());
                detail.putExtra("cabangPengajuan",pengajuan.get(i).getBranchPengajuan());
                detail.putExtra("tanggalPengajuan",pengajuan.get(i).getTanggalPengajuan());
                detail.putExtra("namaLengkap",pengajuan.get(i).getNama_lengkap());
                detail.putExtra("nomorHP",pengajuan.get(i).getPhoneNumber());
                detail.putExtra("umur",pengajuan.get(i).getUmur());
                detail.putExtra("status",pengajuan.get(i).getStatus());
                detail.putExtra("jenis_kelamin",pengajuan.get(i).getJenis_kelamin());
                detail.putExtra("pendidikanTerakhir",pengajuan.get(i).getPendidikan_terakhir());
                detail.putExtra("pekerjaanUsaha",pengajuan.get(i).getPekerjaan_usaha());
                detail.putExtra("email",pengajuan.get(i).getEmail());
                detail.putExtra("noNPWP",pengajuan.get(i).getNomor_npwp());
                detail.putExtra("noKTP",pengajuan.get(i).getNomor_ktp());
                detail.putExtra("lamaBekerjaUsaha",pengajuan.get(i).getLama_bekerja_usaha());
                detail.putExtra("namaPerusahaanSaatBekerjaUsaha",pengajuan.get(i).getNama_perusahaan_saat_bekerja_usaha());
                detail.putExtra("jumlahPengajuanPembiayaan",pengajuan.get(i).getJumlah_pengajuan_pembiayaan());
                detail.putExtra("tipeTujuanPembiayaan",pengajuan.get(i).getTipe_tujuan_pembiayaan());
                detail.putExtra("latitude",pengajuan.get(i).getAlamatLatitude());
                detail.putExtra("longitude",pengajuan.get(i).getAlamatLongitude());
                detail.putExtra("provinsi",pengajuan.get(i).getProvinsi());
                detail.putExtra("kota_kabupaten",pengajuan.get(i).getKota_kabupaten());
                detail.putExtra("kecamatan",pengajuan.get(i).getKecamatan());
                detail.putExtra("kodePos",pengajuan.get(i).getKodePos());
                detail.putExtra("alamatLengkap",pengajuan.get(i).getAlamatLengkap());
                detail.putExtra("imageKTP",pengajuan.get(i).getUrlphotoKTP());
                detail.putExtra("imageKK",pengajuan.get(i).getUrlphotoKK());
                detail.putExtra("imageJaminan",pengajuan.get(i).getUrlphotoJaminan());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pengajuan.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView kategoriPengajuan,tujuanPengajuan,branchPengajuan,tanggalPengajuan,statusPengajuan;
        ConstraintLayout detailPengajuan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            kategoriPengajuan = itemView.findViewById(R.id.kategoriPengajuan);
            tujuanPengajuan = itemView.findViewById(R.id.tujuanPengajuan);
            branchPengajuan = itemView.findViewById(R.id.branchPengajuan);
            tanggalPengajuan = itemView.findViewById(R.id.tanggalPengajuan);
            statusPengajuan = itemView.findViewById(R.id.statusPengajuan);
            detailPengajuan = itemView.findViewById(R.id.detailPengajuan);
        }
    }
}
