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
import com.example.pembiayaanqu.model.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class register extends AppCompatActivity {

    private ImageView back;
    private EditText username;
    private EditText phoenNumber;
    private EditText EMAIL;
    private EditText PASSWORD;
    private Button submit;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private RelativeLayout frameProgressbar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(register.this, MainActivity.class);
                startActivity(login);
                finish();
            }
        });

        username=findViewById(R.id.username);
        phoenNumber = findViewById(R.id.phoneNumber);
        EMAIL = findViewById(R.id.email);
        PASSWORD = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        frameProgressbar = findViewById(R.id.frameProgressbar);
        progressBar = findViewById(R.id.progress_bar);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_progressbar();
                final String UserName = username.getText().toString();
                final String PhoneNumber = phoenNumber.getText().toString();
                final String email = EMAIL.getText().toString();
                final String password = PASSWORD.getText().toString();

                if (TextUtils.isEmpty(UserName)){
                    Toast.makeText(register.this,"please Enter username",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(PhoneNumber)){
                    Toast.makeText(register.this,"please Enter phone number",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(email)){
                    Toast.makeText(register.this,"please Enter email",Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(password)){
                    Toast.makeText(register.this,"please Enter password",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(UserName) && TextUtils.isEmpty(PhoneNumber) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    Toast.makeText(register.this,"please enter the filed",Toast.LENGTH_LONG).show();
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    hide_progressbar();
                                    user informationUser = new user(UserName,PhoneNumber,email,password);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(informationUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(register.this,"Registration Completed",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        }
                                    });

                                } else {
                                    Toast.makeText(register.this,"Registration failure",Toast.LENGTH_LONG).show();
                                    hide_progressbar();
                                }
                            }
                        });

            }
        });
    }

    private void show_progressbar(){
        frameProgressbar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        frameProgressbar.setClickable(true);
    }

    private void hide_progressbar(){
        frameProgressbar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        frameProgressbar.setClickable(true);
    }
}