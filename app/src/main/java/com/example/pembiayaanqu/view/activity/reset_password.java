package com.example.pembiayaanqu.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.pembiayaanqu.MainActivity;
import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.contract.resetPass;
import com.example.pembiayaanqu.presenter.resetPassPresenter;


public class reset_password extends AppCompatActivity implements resetPass.view{

    private EditText email;
    private Button submit;
    private ImageView back;
    private RelativeLayout frameProgressbar;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);

        back = findViewById(R.id.back);
        frameProgressbar = findViewById(R.id.frameProgressbar);
        progressBar = findViewById(R.id.progress_bar);
        email = findViewById(R.id.email);
        submit = findViewById(R.id.submit);
        final resetPassPresenter presenter = new resetPassPresenter(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backActivity();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                presenter.resetPassword(userEmail);
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
        startActivity(new Intent(reset_password.this, MainActivity.class));
    }

    @Override
    public void displayToastSuccess() {
        Toast.makeText(reset_password.this,"Please check your email",Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayToastFailure() {
        Toast.makeText(reset_password.this,"error reset password",Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayToastIsEmpty() {
        Toast.makeText(reset_password.this,"Please enter the user email",Toast.LENGTH_LONG).show();
    }

    @Override
    public void backActivity() {
        startActivity(new Intent(reset_password.this, MainActivity.class));
    }

}