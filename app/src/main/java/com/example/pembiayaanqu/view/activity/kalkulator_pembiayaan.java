package com.example.pembiayaanqu.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.pembiayaanqu.R;

public class kalkulator_pembiayaan extends AppCompatActivity {
    Button hasil;
    TextView money;
    EditText nilai_jangka_waktu;
    EditText nilai_margin;
    TextView perkiraan_angsuran;

    private static SeekBar seek_bar;
    private static TextView text_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kalkulator_pembiayaan);

        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        seekbar();

        hasil = findViewById(R.id.hitung);
        money = findViewById(R.id.number);
        nilai_jangka_waktu=findViewById(R.id.jangkaWaktu);
        nilai_margin = findViewById(R.id.marginPembiayaan);
        perkiraan_angsuran = findViewById(R.id.total_angsuran);

        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int plafond =  Integer.parseInt(String.valueOf(money.getText()));
                int waktu = Integer.parseInt(String.valueOf(nilai_jangka_waktu.getText()));
                int margin = Integer.parseInt(String.valueOf(nilai_margin.getText()));
                int total;
                int hasil = (plafond * margin)/100;
                total = (hasil +plafond)/waktu;
                perkiraan_angsuran.setText(Integer.toString(total));
            }
        });
    }

    public void seekbar(){
        seek_bar = findViewById(R.id.seekBar);
        text_view = findViewById(R.id.number);
        text_view.setText(Integer.toString((seek_bar.getProgress())*100000));


        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;
                    int progres_final;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        progress_value = i*100000;
                        text_view.setText(Integer.toString(progress_value));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        text_view.setText(Integer.toString(progress_value));
                    }
                }
        );
    }
}
