package com.example.pembiayaanqu.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


import com.example.pembiayaanqu.MainActivity;
import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.view.fragment.account;
import com.example.pembiayaanqu.view.fragment.information;
import com.example.pembiayaanqu.view.fragment.main;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private Button logout;
    private BottomNavigationView bottomNavigationView;
    Dialog dialog;
    Toolbar toolbar;
    ImageView logo;
    ImageView hamburger;
    private ImageView search;
    private ImageView closePopup;
    private Spinner kategori;
    private EditText plafondMin;
    private EditText plafondMax;
    private Button cari;
    public String pMin;
    public String pMax;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        mAuth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        logo = findViewById(R.id.logo);
        hamburger = findViewById(R.id.hamburger);
        toolbar = findViewById(R.id.toolbar);
        search = findViewById(R.id.search);

        dialog = new Dialog(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(layout_main.this,"search clickde",Toast.LENGTH_SHORT).show();
                showPopup();
            }
        });

        updateBottomNavigation();




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        changeFragment(new main());
                        menuItem.setChecked(true);
                        toolbar.setVisibility(View.VISIBLE);
                        logo.setVisibility(View.VISIBLE);
                        hamburger.setVisibility(View.GONE);
                        search.setVisibility(View.VISIBLE);
                        break;
                    case R.id.account:
                        if (mAuth.getUid() == null){
                            startActivity(new Intent(home.this,notLogin.class));
                        }else{
                            changeFragment(new account());
                            menuItem.setChecked(true);
                            toolbar.setVisibility(View.GONE);
                            logo.setVisibility(View.GONE);
                            hamburger.setVisibility(View.GONE);
                            search.setVisibility(View.GONE);
                            break;
                        }
                    case R.id.information:
                        changeFragment(new information());
                        menuItem.setChecked(true);
                        toolbar.setVisibility(View.GONE);
                        logo.setVisibility(View.GONE);
                        hamburger.setVisibility(View.GONE);
                        search.setVisibility(View.GONE);
                        break;
                    case R.id.login:
                        Intent login = new Intent(home.this, MainActivity.class);
                        startActivity(login);
                        break;
                    case R.id.logout:
                        logout();
                        break;
                }
                return false;
            }
        });



    }

    private void showPopup() {
        dialog.setContentView(R.layout.popup);
        closePopup = dialog.findViewById(R.id.closePopup);
        cari = dialog.findViewById(R.id.cari);
        kategori = dialog.findViewById(R.id.category);
        plafondMin = dialog.findViewById(R.id.plafondMin);
        plafondMax = dialog.findViewById(R.id.plafondMax);

        closePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dataParse = new Intent(home.this,hasil_pencarian.class);
                dataParse.putExtra("kategori",String.valueOf(kategori.getSelectedItemPosition()));
                if (plafondMin.getText().toString().equals("")){
                    dataParse.putExtra("plafondMin","0");
                }else{
                    dataParse.putExtra("plafondMin",plafondMin.getText().toString());
                }
                if (plafondMax.getText().toString().equals("")){
                    dataParse.putExtra("plafondMax","0");
                }else{
                    dataParse.putExtra("plafondMax",plafondMax.getText().toString());
                }
                startActivity(dataParse);
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void logout(){
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        updateUI();
    }

    private void updateUI() {
        Intent home = new Intent(home.this, home.class);
        startActivity(home);
        finish();
    }


    private void updateBottomNavigation(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            bottomNavigationView.getMenu().clear();
            bottomNavigationView.inflateMenu(R.menu.nav_item_login);
        }else{
            bottomNavigationView.getMenu().clear();
            bottomNavigationView.inflateMenu(R.menu.nav_item_logout);
        }
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

}
