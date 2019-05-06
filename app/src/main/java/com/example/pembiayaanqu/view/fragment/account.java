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

                String providerId = profile.getProviderId();
                String uid = profile.getUid();

                String name = profile.getDisplayName();
                TextView namaLengkap = (TextView)v.findViewById(R.id.namaLengkap);
                namaLengkap.setText(name);
                String email = profile.getEmail();
                TextView Email = (TextView)v.findViewById(R.id.email);
                Email.setText(email);
                provider = user.getProviders().get(0);
                String photo = checkResolutionPhoto(provider,photoUrl,user);
                
                ImageView photoview = (ImageView)v.findViewById(R.id.imageAccount);
                if (photo.isEmpty()){
                    Glide.with(this).load(R.mipmap.user).into(photoview);
                }else{
                    Glide.with(this).load(photo).fitCenter().into(photoview);
                }
            }
        }
        return v;
    }
    
    private String checkResolutionPhoto(String provider,String photoUrl,FirebaseUser user){
        if (provider.equals("facebook.com")) {
            photoUrl = user.getPhotoUrl() + "?height=500";
        }
        else if(provider.equals("google.com"))
        {
            photoUrl = user.getPhotoUrl().toString();

            //Remove thumbnail url and replace the original part of the Url with the new part
            photoUrl = photoUrl.substring(0, photoUrl.length() - 15) + "s400-c/photo.jpg";

        }
        else
        {
            photoUrl = user.getPhotoUrl().toString();
        }
        return photoUrl;
    }
}
