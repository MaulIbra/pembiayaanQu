package com.example.pembiayaanqu.view.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pembiayaanqu.R;
import com.example.pembiayaanqu.contract.dataLoad;
import com.example.pembiayaanqu.presenter.loadDataPresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class edit_profile extends AppCompatActivity implements dataLoad.view{

    private Button save_data;
    private Button addcoordinatemaps;
    private ImageView takePicture;
    private ImageView imagePicture;
    private Button fotoKtp;
    private ImageView imageKtp;
    private Button fotoKk;
    private ImageView imageKk;
    private Button fotoJaminan;
    private ImageView imageJaminan;
    private TextView latitude;
    private TextView longitude;
    private Uri PhotoProfile;
    private Uri PhotoKTP;
    private Uri PhotoKK;
    private Uri PhotoJaminan;
    private EditText namaLengkap;
    private EditText nomorHP;
    private EditText umur;
    private EditText status;
    private EditText jenis_kelamin;
    private EditText pendidikan_terakhir;
    private EditText pekerjaan_usaha;
    private EditText email;
    private EditText noNPWP;
    private EditText noKTP;
    private EditText lama_bekerja_usaha;
    private EditText nama_perusahaan_saat_bekerja;
    private EditText jumlah_pengajuan_pembiayaan;
    private EditText tipe_tujuan_pembiayaan;
    private Spinner provinsi;
    private Spinner kota_kabupaten;
    private EditText kecamatan;
    private EditText kodePos;
    private EditText alamatLengkap;
    private Button uploadPhotoJaminan;
    private Button uploadPhotoKk;
    private Button uploadPhotoKtp;
    private Button uploadPhotoProfile;
    private TextView tempphotoprofile;
    private TextView tempphotokk;
    private TextView tempphotoktp;
    private TextView tempphotojaminan;
    private ImageView backactivitylay;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;

    public static final String STORAGE_PATH = "user/";

    public String photoJaminanUri;
    public String photoKkUri;
    public String photoKtpUri;
    public String photoProfileUri;
    private RelativeLayout frameProgressbar;
    private ProgressBar progressBar;
    HashMap<Integer, String> spinerProvince;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        backactivitylay = findViewById(R.id.back);
        backactivitylay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });

        save_data = findViewById(R.id.sumbitPengajuan);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        takePicture = findViewById(R.id.take_Picture);
        imagePicture = findViewById(R.id.imageAccount);
        addcoordinatemaps=findViewById(R.id.addcoordinatemaps);
        fotoKtp= findViewById(R.id.fotoKTP);
        imageKtp = findViewById(R.id.imageKTP);
        fotoKk = findViewById(R.id.fotoKK);
        imageKk = findViewById(R.id.imageKK);
        fotoJaminan = findViewById(R.id.fotoJaminan);
        imageJaminan = findViewById(R.id.imageJaminan);
        namaLengkap = findViewById(R.id.namaLengkap);
        nomorHP = findViewById(R.id.nomorHP);
        umur = findViewById(R.id.umur);
        status = findViewById(R.id.status);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        pendidikan_terakhir = findViewById(R.id.pendidikanTerakhir);
        pekerjaan_usaha = findViewById(R.id.pekerjaanUsaha);
        email = findViewById(R.id.email);
        noNPWP = findViewById(R.id.noNPWP);
        noKTP = findViewById(R.id.noKTP);
        lama_bekerja_usaha = findViewById(R.id.lamaBekerjaUsaha);
        nama_perusahaan_saat_bekerja = findViewById(R.id.namaPerusahaanSaatBekerjaUsaha);
        jumlah_pengajuan_pembiayaan = findViewById(R.id.jumlahPengajuanPembiayaan);
        tipe_tujuan_pembiayaan = findViewById(R.id.tipeTujuanPembiayaan);
        provinsi = findViewById(R.id.provinsi);
        kota_kabupaten = findViewById(R.id.kota_kabupaten);
        kecamatan = findViewById(R.id.kecamatan);
        kodePos = findViewById(R.id.kodePos);
        alamatLengkap = findViewById(R.id.alamatLengkap);
        frameProgressbar = findViewById(R.id.frameProgressbar);
        progressBar = findViewById(R.id.progress_bar);
        uploadPhotoProfile = findViewById(R.id.uploadProfile);
        uploadPhotoKtp = findViewById(R.id.uploadPhotoKtp);
        uploadPhotoKk = findViewById(R.id.uploadPhotoKk);
        uploadPhotoJaminan = findViewById(R.id.uploadPhotoJaminan);
        tempphotojaminan = findViewById(R.id.tempphotojaminan);
        tempphotokk = findViewById(R.id.tempphotokk);
        tempphotoktp = findViewById(R.id.tempphotoktp);
        tempphotoprofile = findViewById(R.id.tempphotoprofile);



        final loadDataPresenter loadData = new loadDataPresenter(this);
        loadData.readData();
        loadData.readProvince();

        provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadData.readKotaKab(spinerProvince.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addcoordinatemaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(edit_profile.this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent changeActivityintent = new Intent(edit_profile.this,mapActivity.class);
                                startActivityForResult(changeActivityintent,121);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(edit_profile.this);
                                    builder.setTitle("Permission Denied")
                                            .setMessage("Permission to access device is permanently denied. you need to go to setting to allow the permission")
                                            .setNegativeButton("Cancel",null)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package",getPackageName(),null));
                                                }
                                            })
                                            .show();
                                }else{
                                    Toast.makeText(edit_profile.this,"permission Denied",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        })
                        .check();
            }
        });


        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData.getImage(122);
            }
        });

        fotoKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData.getImage(123);
            }
        });

        fotoKk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData.getImage(124);
            }
        });

        fotoJaminan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData.getImage(125);
            }
        });

        uploadPhotoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Uri> imageData = new HashMap<>();
                imageData.put("uriPhotoProfile",PhotoProfile);
                loadData.uploadImage(imageData);
                uploadPhotoProfile.setVisibility(View.GONE);
            }
        });

        uploadPhotoKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Uri> imageData = new HashMap<>();
                imageData.put("uriPhotoKtp",PhotoKTP);
                loadData.uploadImage(imageData);
                uploadPhotoKtp.setVisibility(View.GONE);
            }
        });

        uploadPhotoKk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Uri> imageData = new HashMap<>();
                imageData.put("uriPhotoKk",PhotoKK);
                loadData.uploadImage(imageData);
                uploadPhotoKk.setVisibility(View.GONE);
            }
        });


        uploadPhotoJaminan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Uri> imageData = new HashMap<>();
                imageData.put("uriPhotoJaminan",PhotoJaminan);
                loadData.uploadImage(imageData);
                uploadPhotoJaminan.setVisibility(View.GONE);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();



        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    toUpdateData();
                }catch (Exception e){
                    Toast.makeText(edit_profile.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 121){
            if (resultCode == RESULT_OK){
                Double lat = data.getDoubleExtra("latitude",0);
                Double lng = data.getDoubleExtra("longitude",1);
                latitude.setText(""+lat);
                longitude.setText(""+lng);
            }
        }
        else if (requestCode==122 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            PhotoProfile= data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),PhotoProfile);
                imagePicture.setImageBitmap(bm);
                uploadPhotoProfile.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode==123 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            PhotoKTP = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),PhotoKTP);
                imageKtp.setVisibility(View.VISIBLE);
                imageKtp.setImageBitmap(bm);
                uploadPhotoKtp.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode==124 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            PhotoKK = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),PhotoKK);
                imageKk.setVisibility(View.VISIBLE);
                imageKk.setImageBitmap(bm);
                uploadPhotoKk.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode==125 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            PhotoJaminan = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),PhotoJaminan);
                imageJaminan.setVisibility(View.VISIBLE);
                imageJaminan.setImageBitmap(bm);
                uploadPhotoJaminan.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    }

    @Override
    public void displayData(HashMap<String, String> data) {
        namaLengkap.setText(data.get("Nama Lengkap"));
        nomorHP.setText(data.get("Nomor Handphone"));
        email.setText(data.get("Email"));
        umur.setText(data.get("Umur"));
        status.setText(data.get("Status"));
        jenis_kelamin.setText(data.get("Jenis Kelamin"));
        pendidikan_terakhir.setText(data.get("Pendidikan Terakhir"));
        pekerjaan_usaha.setText(data.get("Pekerjaan atau Usaha"));
        noNPWP.setText(data.get("Nomor NPWP"));
        noKTP.setText(data.get("Nomor KTP"));
        lama_bekerja_usaha.setText(data.get("Lama bekerja atau Usaha"));
        nama_perusahaan_saat_bekerja.setText(data.get("Nama Perusahaan saat Bekerja"));
        jumlah_pengajuan_pembiayaan.setText(data.get("Jumlah Pengajuan Pembiayaan"));
        tipe_tujuan_pembiayaan.setText(data.get("Tipe Tujuan Pembiayaan"));
        latitude.setText(data.get("Alamat Latitude"));
        longitude.setText(data.get("Alamat Longitude"));
//        provinsi.setText(data.get("Provinsi"));
//        kota_kabupaten.setText(data.get("KotaKabupaten"));
        kecamatan.setText(data.get("Kecamatan"));
        kodePos.setText(data.get("Kode Pos"));
        alamatLengkap.setText(data.get("Alamat Lengkap"));
        tempphotoprofile.setText(data.get("uriPhotoProfile"));
        tempphotojaminan.setText(data.get("uriPhotoJaminan"));
        tempphotoktp.setText(data.get("uriPhotoKtp"));
        tempphotokk.setText(data.get("uriPhotoKk"));

        if (data.get("uriPhotoProfile") !=null){
            imagePicture.setVisibility(View.VISIBLE);
            Glide.with(this).load(data.get("uriPhotoProfile")).fitCenter().into(imagePicture);
        }
        if (data.get("uriPhotoKk") != null){
            imageKk.setVisibility(View.VISIBLE);
            Glide.with(this).load(data.get("uriPhotoKk")).into(imageKk);
        }
        if (data.get("uriPhotoKtp") != null){
            imageKtp.setVisibility(View.VISIBLE);
            Glide.with(this).load(data.get("uriPhotoKtp")).into(imageKtp);
        }
        if (data.get("uriPhotoJaminan") != null){
            imageJaminan.setVisibility(View.VISIBLE);
            Glide.with(this).load(data.get("uriPhotoJaminan")).into(imageJaminan);
        }
    }

    @Override
    public void displayToastSuccess() {
        Toast.makeText(edit_profile.this,"Edit Profile Success",Toast.LENGTH_SHORT).show();
        backActivity();
    }

    @Override
    public void displayToastFailure() {
        Toast.makeText(edit_profile.this,"Failure",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayToastIsEmpty() {

    }

    @Override
    public void backActivity() {
        startActivity(new Intent(edit_profile.this,home.class));
    }

    @Override
    public void resultActivity(int requestCode,Intent intent) {
        startActivityForResult(Intent.createChooser(intent,"Select Image"),requestCode);
    }

    @Override
    public void getdataUri(HashMap<String, String> data) {
        for (Map.Entry me : data.entrySet()){
            if (me.getKey().toString().equals("uriPhotoJaminan")){
                photoJaminanUri = me.getValue().toString();
            }
            else if(me.getKey().toString().equals("uriPhotoKk")){
                photoKkUri = me.getValue().toString();
            }
            else if (me.getKey().toString().equals("uriPhotoKtp")){
                photoKtpUri = me.getValue().toString();
            }
            else if (me.getKey().toString().equals("uriPhotoProfile")){
                photoProfileUri = me.getValue().toString();
            }
        }

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void toUpdateData() {
        loadDataPresenter loadDataPresenter = new loadDataPresenter(this);
        HashMap<String, String> userFirestore = new HashMap<>();
        if (namaLengkap.getText().toString()== null){
            userFirestore.put("Nama Lengkap", null);
        }else if (!(namaLengkap.getText().toString().isEmpty())){
            userFirestore.put("Nama Lengkap", namaLengkap.getText().toString().trim());
        }
        if (nomorHP.getText().toString()== null){
            userFirestore.put("Nomor Handphone",null);
        }else if (!(nomorHP.getText().toString().isEmpty())){
            userFirestore.put("Nomor Handphone", nomorHP.getText().toString().trim());
        }
        if (email.getText().toString()== null){
            userFirestore.put("Email",null);
        }else if (!(email.getText().toString().isEmpty())){
            userFirestore.put("Email", email.getText().toString().trim());
        }
        if (umur.getText().toString()== null){
            userFirestore.put("Umur",null);
        }else if (!(umur.getText().toString().isEmpty())){
            userFirestore.put("Umur", umur.getText().toString().trim());
        }
        if (status.getText().toString()== null){
            userFirestore.put("Status",null);
        }else if (!(status.getText().toString().isEmpty())){
            userFirestore.put("Status", status.getText().toString().trim());
        }
        if (jenis_kelamin.getText().toString()== null){
            userFirestore.put("Jenis Kelamin",null);
        }else if (!(jenis_kelamin.getText().toString().isEmpty())){
            userFirestore.put("Jenis Kelamin", jenis_kelamin.getText().toString().trim());
        }
        if (pendidikan_terakhir.getText().toString()== null){
            userFirestore.put("Pendidikan Terakhir",null);
        }else if (!(pendidikan_terakhir.getText().toString().isEmpty())){
            userFirestore.put("Pendidikan Terakhir", pendidikan_terakhir.getText().toString().trim());
        }
        if (pekerjaan_usaha.getText().toString()== null){
            userFirestore.put("Pekerjaan atau Usaha",null);
        }else if (!(pekerjaan_usaha.getText().toString().isEmpty())){
            userFirestore.put("Pekerjaan atau Usaha", pekerjaan_usaha.getText().toString().trim());
        }
        if (noNPWP.getText().toString()== null){
            userFirestore.put("Nomor NPWP",null);
        }else if (!(noNPWP.getText().toString().isEmpty())){
            userFirestore.put("Nomor NPWP", noNPWP.getText().toString().trim());
        }
        if (noKTP.getText().toString()== null){
            userFirestore.put("Nomor KTP",null);
        }else if (!(noKTP.getText().toString().isEmpty())){
            userFirestore.put("Nomor KTP", noKTP.getText().toString().trim());
        }
        if (lama_bekerja_usaha.getText().toString()== null){
            userFirestore.put("Lama bekerja atau Usaha",null);
        }else if (!(lama_bekerja_usaha.getText().toString().isEmpty())){
            userFirestore.put("Lama bekerja atau Usaha", lama_bekerja_usaha.getText().toString().trim());
        }
        if (nama_perusahaan_saat_bekerja.getText().toString()== null){
            userFirestore.put("Nama Perusahaan saat Bekerja",null);
        }else if (!(nama_perusahaan_saat_bekerja.getText().toString().isEmpty())){
            userFirestore.put("Nama Perusahaan saat Bekerja", nama_perusahaan_saat_bekerja.getText().toString().trim());
        }
        if (jumlah_pengajuan_pembiayaan.getText().toString()== null){
            userFirestore.put("Jumlah Pengajuan Pembiayaan",null);
        }else if (!(jumlah_pengajuan_pembiayaan.getText().toString().isEmpty())){
            userFirestore.put("Jumlah Pengajuan Pembiayaan", jumlah_pengajuan_pembiayaan.getText().toString().trim());
        }
        if (tipe_tujuan_pembiayaan.getText().toString()== null){
            userFirestore.put("Tipe Tujuan Pembiayaan",null);
        }else if (!(tipe_tujuan_pembiayaan.getText().toString().isEmpty())){
            userFirestore.put("Tipe Tujuan Pembiayaan", tipe_tujuan_pembiayaan.getText().toString().trim());
        }
        if (latitude.getText().toString()== null){
            userFirestore.put("Alamat Latitude",null);
        }else if (!(latitude.getText().toString().isEmpty())){
            userFirestore.put("Alamat Latitude", latitude.getText().toString().trim());
        }
        if (longitude.getText().toString()== null){
            userFirestore.put("Alamat Longitude",null);
        }else if (!(longitude.getText().toString().isEmpty())){
            userFirestore.put("Alamat Longitude", longitude.getText().toString().trim());
        }
//        if (provinsi.getText().toString()== null){
//            userFirestore.put("Provinsi",null);
//        }else if (!(provinsi.getText().toString().isEmpty())){
//            userFirestore.put("Provinsi", provinsi.getText().toString().trim());
//        }
//        if (kota_kabupaten.getText().toString()== null){
//            userFirestore.put("KotaKabupaten",null);
//        }else if (!(namaLengkap.getText().toString().isEmpty())){
//            userFirestore.put("KotaKabupaten", kota_kabupaten.getText().toString().trim());
//        }
        if (kecamatan.getText().toString()== null){
            userFirestore.put("Kecamatan",null);
        }else if (!(kecamatan.getText().toString().isEmpty())){
            userFirestore.put("Kecamatan", kecamatan.getText().toString().trim());
        }
        if (kodePos.getText().toString()== null){
            userFirestore.put("Kode Pos",null);
        }else if (!(kodePos.getText().toString().isEmpty())){
            userFirestore.put("Kode Pos", kodePos.getText().toString().trim());
        }
        if (alamatLengkap.getText().toString()== null){
            userFirestore.put("Alamat Lengkap",null);
        }else if (!(alamatLengkap.getText().toString().isEmpty())){
            userFirestore.put("Alamat Lengkap", alamatLengkap.getText().toString().trim());
        }
        if (photoProfileUri== null){
            if (tempphotoprofile.getText().toString() == null){
                userFirestore.put("uriPhotoProfile",null);
            }else{
                userFirestore.put("uriPhotoProfile",tempphotoprofile.getText().toString().trim());
            }
        }else if (photoProfileUri !=null){
            userFirestore.put("uriPhotoProfile",photoProfileUri);
        }
        if (photoKtpUri== null){
            if (tempphotoktp.getText().toString() == null){
                userFirestore.put("uriPhotoKtp",null);
            }else{
                userFirestore.put("uriPhotoKtp",tempphotoktp.getText().toString().trim());
            }
        }else if (photoKtpUri !=null){
            userFirestore.put("uriPhotoKtp",photoKtpUri);
        }
        if (photoKkUri== null){
            if (tempphotokk.getText().toString() == null){
                userFirestore.put("uriPhotoKk",null);
            }else{
                userFirestore.put("uriPhotoKk",tempphotokk.getText().toString().trim());
            }
        }else if (photoKkUri !=null){
            userFirestore.put("uriPhotoKk",photoKkUri);
        }
        if (photoJaminanUri== null){
            if (tempphotojaminan.getText().toString() == null){
                userFirestore.put("uriPhotoJaminan",null);
            }else{
                userFirestore.put("uriPhotoJaminan",tempphotojaminan.getText().toString().trim());
            }
        }else if (photoJaminanUri !=null){
            userFirestore.put("uriPhotoJaminan",photoJaminanUri);
        }

        loadDataPresenter.savedata(userFirestore);
    }

    @Override
    public void displayToastUploadSuccess() {
        Toast.makeText(edit_profile.this,"Upload Success",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void spinnerData(String[] data, HashMap<Integer, String> value) {
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinsi.setAdapter(adapter);
        spinerProvince = value;
    }

    @Override
    public void spinnerDataKota(String[] data, HashMap<Integer, String> value) {
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kota_kabupaten.setAdapter(adapter);
    }

    @Override
    public void spinerDataKategoriPengajuan(String[] data, HashMap<Integer, String> value) {

    }

    @Override
    public void spinerDataDetailKategoriPengajuan(String[] data, HashMap<Integer, String> value) {

    }

    @Override
    public void spinerDataCabangPengajuan(String[] data, HashMap<Integer, String> value) {

    }
}
