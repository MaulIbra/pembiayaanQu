package com.example.pembiayaanqu.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pembiayaanqu.MainActivity;
import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.contract.registerMember;
import com.example.pembiayaanqu.model.user;
import com.example.pembiayaanqu.presenter.registerPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class register extends AppCompatActivity implements registerMember.view{

    private ImageView back;
    private EditText username;
    private EditText phoenNumber;
    private EditText email;
    private EditText password;
    private Button submit;
    private RelativeLayout frameProgressbar;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username=findViewById(R.id.username);
        phoenNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        frameProgressbar = findViewById(R.id.frameProgressbar);
        progressBar = findViewById(R.id.progress_bar);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backActivity();
            }
        });


        final registerPresenter presenter = new registerPresenter(this);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Un = username.getText().toString();
                String Pn = phoenNumber.getText().toString();
                String Em = email.getText().toString();
                String Pass = password.getText().toString();
                String Collection = "users";

                if (TextUtils.isEmpty(Un) || TextUtils.isEmpty(Pn) || TextUtils.isEmpty(Em) || TextUtils.isEmpty(Pass)) {
                    displayToastIsEmpty();
                }else{
                    presenter.createAuth(Un,Pn,Em,Pass,Collection);
                }
            }
        });
    }

    @Override
    public void showProgressBar() {
        frameProgressbar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        frameProgressbar.setClickable(true);
    }

    @Override
    public void hideProgressBar() {
        frameProgressbar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        frameProgressbar.setClickable(true);
    }

    @Override
    public void changeActivity() {
        Intent login = new Intent(register.this, MainActivity.class);
        startActivity(login);
    }

    @Override
    public void displayToastSuccess() {
        Toast.makeText(register.this,"Registration success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayToastFailure() {
        Toast.makeText(register.this,"Registration failure",Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayToastIsEmpty() {
        Toast.makeText(register.this,"please enter all the filed",Toast.LENGTH_LONG).show();
    }

    @Override
    public void backActivity() {
        Intent login = new Intent(register.this, MainActivity.class);
        startActivity(login);
    }

}