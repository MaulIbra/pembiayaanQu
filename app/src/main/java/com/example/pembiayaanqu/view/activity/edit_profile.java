package com.example.pembiayaanqu.view.activity;

import android.Manifest;
import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pembiayaanqu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
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

public class edit_profile extends AppCompatActivity {

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
    private Uri getImgUriPhotoProfile;
    private Uri getimgUriPhotoKTP;
    private Uri getImgUriPhotoKK;
    private Uri getImgUriPhotoJaminan;
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
    private EditText provinsi;
    private EditText kota_kabupaten;
    private EditText kecamatan;
    private EditText kodePos;
    private EditText alamatLengkap;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;

    public static final String STORAGE_PATH = "user/";
    private Uri pathData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

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
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),122);
            }
        });

        fotoKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),123);
            }
        });

        fotoKk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),124);
            }
        });

        fotoJaminan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),125);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            firebaseFirestore.collection("users").document(user.getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                namaLengkap.setText(documentSnapshot.getString("Nama Lengkap"));
                                nomorHP.setText(documentSnapshot.getString("Nomor Handphone"));
                                email.setText(documentSnapshot.getString("Email"));
                                umur.setText(documentSnapshot.getString("Umur"));
                                status.setText(documentSnapshot.getString("Status"));
                                jenis_kelamin.setText(documentSnapshot.getString("Jenis Kelamin"));
                                pendidikan_terakhir.setText(documentSnapshot.getString("Pendidikan Terakhir"));
                                pekerjaan_usaha.setText(documentSnapshot.getString("Pekerjaan atau Usaha"));
                                noNPWP.setText(documentSnapshot.getString("Nomor NPWP"));
                                noKTP.setText(documentSnapshot.getString("Nomor KTP"));
                                lama_bekerja_usaha.setText(documentSnapshot.getString("Lama bekerja atau Usaha"));
                                nama_perusahaan_saat_bekerja.setText(documentSnapshot.getString("Nama Perusahaan saat Bekerja"));
                                jumlah_pengajuan_pembiayaan.setText(documentSnapshot.getString("Jumlah Pengajuan Pembiayaan"));
                                tipe_tujuan_pembiayaan.setText(documentSnapshot.getString("Tipe Tujuan Pembiayaan"));
                                latitude.setText(documentSnapshot.getString("Alamat Latitude"));
                                longitude.setText(documentSnapshot.getString("Alamat Longitude"));
                                provinsi.setText(documentSnapshot.getString("Provinsi"));
                                kota_kabupaten.setText(documentSnapshot.getString("KotaKabupaten"));
                                kecamatan.setText(documentSnapshot.getString("Kecamatan"));
                                kodePos.setText(documentSnapshot.getString("Kode Pos"));
                                alamatLengkap.setText(documentSnapshot.getString("Alamat Lengkap"));
                            }else {
                                namaLengkap.setText(user.getDisplayName());
                                nomorHP.setText(user.getPhoneNumber());
                                email.setText(user.getEmail());
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(edit_profile.this,"Failure",Toast.LENGTH_SHORT).show();
                        }
                    });

        }


        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getImgUriPhotoProfile != null){
                    final StorageReference ref = storageReference.child(STORAGE_PATH +System.currentTimeMillis()+"."+getImageExt(getImgUriPhotoProfile));
                    ref.putFile(getImgUriPhotoProfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(edit_profile.this,"Image Profile gagal di upload",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                Map<String, Object> userFirestore = new HashMap<>();
                if (namaLengkap.getText().toString().isEmpty()){
                    userFirestore.put("Nama Lengkap",null);
                }else if (!(namaLengkap.getText().toString().isEmpty())){
                    userFirestore.put("Nama Lengkap",namaLengkap.getText().toString());
                }
                if (nomorHP.getText().toString().isEmpty()){
                    userFirestore.put("Nomor Handphone",null);
                }else if (!(nomorHP.getText().toString().isEmpty())){
                    userFirestore.put("Nomor Handphone",nomorHP.getText().toString());
                }
                if (email.getText().toString().isEmpty()){
                    userFirestore.put("Email",null);
                }else if (!(email.getText().toString().isEmpty())){
                    userFirestore.put("Email",email.getText().toString());
                }
                if (umur.getText().toString().isEmpty()){
                    userFirestore.put("Umur",null);
                }else if (!(umur.getText().toString().isEmpty())){
                    userFirestore.put("Umur",umur.getText().toString());
                }
                if (status.getText().toString().isEmpty()){
                    userFirestore.put("Status",null);
                }else if (!(status.getText().toString().isEmpty())){
                    userFirestore.put("Status",status.getText().toString());
                }
                if (jenis_kelamin.getText().toString().isEmpty()){
                    userFirestore.put("Jenis Kelamin",null);
                }else if (!(jenis_kelamin.getText().toString().isEmpty())){
                    userFirestore.put("Jenis Kelamin",jenis_kelamin.getText().toString());
                }
                if (pendidikan_terakhir.getText().toString().isEmpty()){
                    userFirestore.put("Pendidikan Terakhir",null);
                }else if (!(pendidikan_terakhir.getText().toString().isEmpty())){
                    userFirestore.put("Pendidikan Terakhir",pendidikan_terakhir.getText().toString());
                }
                if (pekerjaan_usaha.getText().toString().isEmpty()){
                    userFirestore.put("Pekerjaan atau Usaha",null);
                }else if (!(pekerjaan_usaha.getText().toString().isEmpty())){
                    userFirestore.put("Pekerjaan atau Usaha",pekerjaan_usaha.getText().toString());
                }
                if (noNPWP.getText().toString().isEmpty()){
                    userFirestore.put("Nomor NPWP",null);
                }else if (!(noNPWP.getText().toString().isEmpty())){
                    userFirestore.put("Nomor NPWP",noNPWP.getText().toString());
                }
                if (noKTP.getText().toString().isEmpty()){
                    userFirestore.put("Nomor KTP",null);
                }else if (!(noKTP.getText().toString().isEmpty())){
                    userFirestore.put("Nomor KTP",noKTP.getText().toString());
                }
                if (lama_bekerja_usaha.getText().toString().isEmpty()){
                    userFirestore.put("Lama bekerja atau Usaha",null);
                }else if (!(lama_bekerja_usaha.getText().toString().isEmpty())){
                    userFirestore.put("Lama bekerja atau Usaha",lama_bekerja_usaha.getText().toString());
                }
                if (nama_perusahaan_saat_bekerja.getText().toString().isEmpty()){
                    userFirestore.put("Nama Perusahaan saat Bekerja",null);
                }else if (!(nama_perusahaan_saat_bekerja.getText().toString().isEmpty())){
                    userFirestore.put("Nama Perusahaan saat Bekerja",nama_perusahaan_saat_bekerja.getText().toString());
                }
                if (jumlah_pengajuan_pembiayaan.getText().toString().isEmpty()){
                    userFirestore.put("Jumlah Pengajuan Pembiayaan",null);
                }else if (!(jumlah_pengajuan_pembiayaan.getText().toString().isEmpty())){
                    userFirestore.put("Jumlah Pengajuan Pembiayaan",jumlah_pengajuan_pembiayaan.getText().toString());
                }
                if (tipe_tujuan_pembiayaan.getText().toString().isEmpty()){
                    userFirestore.put("Tipe Tujuan Pembiayaan",null);
                }else if (!(tipe_tujuan_pembiayaan.getText().toString().isEmpty())){
                    userFirestore.put("Tipe Tujuan Pembiayaan",tipe_tujuan_pembiayaan.getText().toString());
                }
                if (latitude.getText().toString().isEmpty()){
                    userFirestore.put("Alamat Latitude",null);
                }else if (!(latitude.getText().toString().isEmpty())){
                    userFirestore.put("Alamat Latitude",latitude.getText().toString());
                }
                if (longitude.getText().toString().isEmpty()){
                    userFirestore.put("Alamat Longitude",null);
                }else if (!(longitude.getText().toString().isEmpty())){
                    userFirestore.put("Alamat Longitude",longitude.getText().toString());
                }
                if (provinsi.getText().toString().isEmpty()){
                    userFirestore.put("Provinsi",null);
                }else if (!(provinsi.getText().toString().isEmpty())){
                    userFirestore.put("Provinsi",provinsi.getText().toString());
                }
                if (kota_kabupaten.getText().toString().isEmpty()){
                    userFirestore.put("Kota / Kabupaten",null);
                }else if (!(kota_kabupaten.getText().toString().isEmpty())){
                    userFirestore.put("KotaKabupaten",kota_kabupaten.getText().toString());
                }
                if (kecamatan.getText().toString().isEmpty()){
                    userFirestore.put("Kecamatan",null);
                }else if (!(kecamatan.getText().toString().isEmpty())){
                    userFirestore.put("Kecamatan",kecamatan.getText().toString());
                }
                if (kodePos.getText().toString().isEmpty()){
                    userFirestore.put("Kode Pos",null);
                }else if (!(kodePos.getText().toString().isEmpty())){
                    userFirestore.put("Kode Pos",kodePos.getText().toString());
                }
                if (alamatLengkap.getText().toString().isEmpty()){
                    userFirestore.put("Alamat Lengkap",null);
                }else if (!(alamatLengkap.getText().toString().isEmpty())){
                    userFirestore.put("Alamat Lengkap",alamatLengkap.getText().toString());
                }
//                userFirestore.put("uriPhotoKTP",pathData.toString());
//                userFirestore.put("uriPhotoKTP",userDetail.getUrlphotoKTP());
//                userFirestore.put("uriPhotoKK",userDetail.getUrlphotoKK());
//                userFirestore.put("uriPhotoJaminan",userDetail.getUrlphotoJaminan());

                firebaseFirestore.collection("users").document(user.getUid()).set(userFirestore);
                onBackPressed();
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
            getImgUriPhotoProfile= data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),getImgUriPhotoProfile);
                imagePicture.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode==123 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            getimgUriPhotoKTP = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),getimgUriPhotoKTP);
                imageKtp.setVisibility(View.VISIBLE);
                imageKtp.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode==124 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            getImgUriPhotoKK = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),getImgUriPhotoKK);
                imageKk.setVisibility(View.VISIBLE);
                imageKk.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode==125 && resultCode == RESULT_OK && data!=null && data.getData() != null){
            getImgUriPhotoJaminan = data.getData();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(),getImgUriPhotoJaminan);
                imageJaminan.setVisibility(View.VISIBLE);
                imageJaminan.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getImageExt (Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
