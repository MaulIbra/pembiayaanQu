package com.example.pembiayaanqu.view.fragment;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pembiayaanqu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class account extends Fragment {
    private FirebaseAuth mAuth;
    private String photoUrl;
    private String provider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.account,container,false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                ImageView photoview = (ImageView)v.findViewById(R.id.imageAccount);
                if (profile.getPhotoUrl()!=null){
                    provider = user.getProviders().get(0);
                    photoUrl = user.getPhotoUrl().toString();
                    String photo = checkResolutionPhoto(provider,photoUrl,user);
                    Glide.with(this).load(photo).fitCenter().into(photoview);
                }
            }
        }
        return v;
    }
    
    private String checkResolutionPhoto(String provider,String photoUrl,FirebaseUser user){
        if (provider.equals("facebook.com")) {
            photoUrl = photoUrl + "?height=500";
        }
        else if(provider.equals("google.com"))
        {
            photoUrl = photoUrl.substring(0, photoUrl.length() - 15) + "s400-c/photo.jpg";

        }
        else
        {
            photoUrl=photoUrl;
        }
        return photoUrl;
    }
}
