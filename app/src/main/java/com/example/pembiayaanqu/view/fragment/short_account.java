package com.example.pembiayaanqu.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pembiayaanqu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class short_account extends Fragment {

    private TextView nomorHP;
    private TextView email;
    private TextView umur;
    private TextView status;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_account_short, container, false);

        Button toFullAccount = view.findViewById(R.id.layout_id_to_fullAccount);
        toFullAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_account, new full_account()).commit();
            }
        });

        nomorHP = view.findViewById(R.id.nomorHP);
        email = view.findViewById(R.id.email);
        umur = view.findViewById(R.id.umur);
        status = view.findViewById(R.id.status);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();


        if (user != null) {
            firebaseFirestore.collection("users").document(user.getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                nomorHP.setText(documentSnapshot.getString("Nomor Handphone"));
                                email.setText(documentSnapshot.getString("Email"));
                                umur.setText(documentSnapshot.getString("Umur"));
                                status.setText(documentSnapshot.getString("Status"));
                            }else {
                                nomorHP.setText(user.getPhoneNumber());
                                email.setText(user.getEmail());
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
                }
            });

        }
        return view;
    }
}
