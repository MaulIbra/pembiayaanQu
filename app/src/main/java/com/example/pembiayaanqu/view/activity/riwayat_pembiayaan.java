package com.example.pembiayaanqu.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.adapter.ViewPagerAdapter;
import com.example.pembiayaanqu.view.fragment.pengajuan_diterima;
import com.example.pembiayaanqu.view.fragment.pengajuan_ditolak;
import com.example.pembiayaanqu.view.fragment.pengajuan_semua;


public class riwayat_pembiayaan extends AppCompatActivity {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_pembiayaan);
        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.Addfragment(new pengajuan_semua(),"Semua Pengajuan");
        adapter.Addfragment(new pengajuan_diterima(),"Pengajuan Diterima");
        adapter.Addfragment(new pengajuan_ditolak(),"Pengajuan Ditolak");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
