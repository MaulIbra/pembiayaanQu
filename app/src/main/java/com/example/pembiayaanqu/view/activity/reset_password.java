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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class reset_password extends AppCompatActivity {

    private EditText email;
    private Button submit;
    private ImageView back;
    private RelativeLayout frameProgressbar;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(reset_password.this, MainActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

        frameProgressbar = findViewById(R.id.frameProgressbar);
        progressBar = findViewById(R.id.progress_bar);
        email = findViewById(R.id.email);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                if (TextUtils.isEmpty(userEmail)){
                    Toast.makeText(reset_password.this,"Please enter the user email",Toast.LENGTH_LONG).show();
                }else{
                    show_progressbar();
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                hide_progressbar();
                                Toast.makeText(reset_password.this,"Please check your email",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(reset_password.this,MainActivity.class));
                            }
                            else{
                                String message = task.getException().getMessage();
                                Toast.makeText(reset_password.this,"erorr reset password"+message,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
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