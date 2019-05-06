package com.example.pembiayaanqu.view.activity;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pembiayaanqu.MainActivity;
import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.view.fragment.account;
import com.example.pembiayaanqu.view.fragment.information;
import com.example.pembiayaanqu.view.fragment.main;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button logout;
    private BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    ImageView logo;
    ImageView hamburger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mAuth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        logo = findViewById(R.id.logo);
        hamburger = findViewById(R.id.hamburger);
        toolbar = findViewById(R.id.toolbar);

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
                        hamburger.setVisibility(View.VISIBLE);
                        break;
                    case R.id.account:
                        changeFragment(new account());
                        menuItem.setChecked(true);
                        toolbar.setVisibility(View.GONE);
                        logo.setVisibility(View.GONE);
                        hamburger.setVisibility(View.GONE);
                        break;
                    case R.id.information:
                        changeFragment(new information());
                        menuItem.setChecked(true);
                        toolbar.setVisibility(View.GONE);
                        logo.setVisibility(View.GONE);
                        hamburger.setVisibility(View.GONE);
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
