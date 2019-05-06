package com.example.pembiayaanqu.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pembiayaanqu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class full_account extends Fragment {

    private EditText namaLengkap;
    private EditText nomorHP;
    private EditText email;
    private EditText umur;
    private EditText status;
    private EditText jenis_kelamin;
    private EditText pendidikan_terakhir;
    private EditText pekerjaan_usaha;
    private EditText noNPWP;
    private EditText noKTP;
    private EditText lama_bekerja_usaha;
    private EditText nama_perusahaan_saat_bekerja;
    private EditText jumlah_pengajuan_pembiayaan;
    private EditText tipe_tujuan_pembiayaan;
    private EditText lokasi_pengambilan;
    private EditText alamat_lokasi;
    private Button editProfile;
    private Button savedata;
    private Button toShorAccount;


    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.account_full, container, false);

        toShorAccount = view.findViewById(R.id.layout_id_to_shortAccount);
        toShorAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_account, new short_account()).commit();
            }
        });

        namaLengkap = view.findViewById(R.id.namaLengkap);
        nomorHP = view.findViewById(R.id.nomorHP);
        email = view.findViewById(R.id.email);
        umur = view.findViewById(R.id.umur);
        status = view.findViewById(R.id.status);
        jenis_kelamin = view.findViewById(R.id.jenis_kelamin);
        pendidikan_terakhir = view.findViewById(R.id.pendidikanTerakhir);
        pekerjaan_usaha = view.findViewById(R.id.pekerjaanUsaha);
        noNPWP = view.findViewById(R.id.noNPWP);
        noKTP = view.findViewById(R.id.noKTP);
        lama_bekerja_usaha = view.findViewById(R.id.lamaBekerjaUsaha);
        nama_perusahaan_saat_bekerja = view.findViewById(R.id.namaPerusahaanSaatBekerjaUsaha);
        jumlah_pengajuan_pembiayaan = view.findViewById(R.id.jumlahPengajuanPembiayaan);
        tipe_tujuan_pembiayaan = view.findViewById(R.id.tipeTujuanPembiayaan);
        lokasi_pengambilan = view.findViewById(R.id.lokasiPengambilan);
        alamat_lokasi = view.findViewById(R.id.alamatLokasi);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            firebaseFirestore.collection("Users").document(user.getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                String namalengkap = documentSnapshot.getString("Nama Lengkap");
                                namaLengkap.setText(namalengkap);
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
                                lokasi_pengambilan.setText(documentSnapshot.getString("Lokasi Pengambilan"));
                                alamat_lokasi.setText(documentSnapshot.getString("Alamat Lokasi"));
                                notEditableEdittext();
                            }else {
                                namaLengkap.setText(user.getDisplayName());
                                nomorHP.setText(user.getPhoneNumber());
                                email.setText(user.getEmail());
                                notEditableEdittext();
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

        editProfile=view.findViewById(R.id.layout_id_to_editAccount);
        savedata = view.findViewById(R.id.layout_id_to_savedata);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditEdittext();
                editProfile.setVisibility(View.GONE);
                toShorAccount.setVisibility(View.GONE);
                savedata.setVisibility(View.VISIBLE);
            }
        });


        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> userFirestore = new HashMap<>();
                userFirestore.put("Nama Lengkap",namaLengkap.getText().toString());
                userFirestore.put("Nomor Handphone",nomorHP.getText().toString());
                userFirestore.put("Email",email.getText().toString());
                userFirestore.put("Umur",umur.getText().toString());
                userFirestore.put("Status",status.getText().toString());
                userFirestore.put("Jenis Kelamin",jenis_kelamin.getText().toString());
                userFirestore.put("Pendidikan Terakhir",pendidikan_terakhir.getText().toString());
                userFirestore.put("Pekerjaan atau Usaha",pekerjaan_usaha.getText().toString());
                userFirestore.put("Nomor NPWP",noNPWP.getText().toString());
                userFirestore.put("Nomor KTP",noKTP.getText().toString());
                userFirestore.put("Lama bekerja atau Usaha",lama_bekerja_usaha.getText().toString());
                userFirestore.put("Nama Perusahaan saat Bekerja",nama_perusahaan_saat_bekerja.getText().toString());
                userFirestore.put("Jumlah Pengajuan Pembiayaan",jumlah_pengajuan_pembiayaan.getText().toString());
                userFirestore.put("Tipe Tujuan Pembiayaan",tipe_tujuan_pembiayaan.getText().toString());
                userFirestore.put("Lokasi Pengambilan",lokasi_pengambilan.getText().toString());
                userFirestore.put("Alamat Lokasi",alamat_lokasi.getText().toString());

                firebaseFirestore.collection("Users").document(user.getUid()).set(userFirestore);

                notEditableEdittext();
                editProfile.setVisibility(View.VISIBLE);
                toShorAccount.setVisibility(View.VISIBLE);
                savedata.setVisibility(View.GONE);
            }
        });
        return view;
    }

    private void onEditEdittext(){
        namaLengkap.setEnabled(true);
        nomorHP.setEnabled(true);
        email.setEnabled(true);
        umur.setEnabled(true);
        status.setEnabled(true);
        jenis_kelamin.setEnabled(true);
        pendidikan_terakhir.setEnabled(true);
        pekerjaan_usaha.setEnabled(true);
        noNPWP.setEnabled(true);
        noKTP.setEnabled(true);
        lama_bekerja_usaha.setEnabled(true);
        nama_perusahaan_saat_bekerja.setEnabled(true);
        jumlah_pengajuan_pembiayaan.setEnabled(true);
        tipe_tujuan_pembiayaan.setEnabled(true);
        lokasi_pengambilan.setEnabled(true);
        alamat_lokasi.setEnabled(true);
    }

    private void notEditableEdittext(){
        namaLengkap.setEnabled(false);
        nomorHP.setEnabled(false);
        email.setEnabled(false);
        umur.setEnabled(false);
        status.setEnabled(false);
        jenis_kelamin.setEnabled(false);
        pendidikan_terakhir.setEnabled(false);
        pekerjaan_usaha.setEnabled(false);
        noNPWP.setEnabled(false);
        noKTP.setEnabled(false);
        lama_bekerja_usaha.setEnabled(false);
        nama_perusahaan_saat_bekerja.setEnabled(false);
        jumlah_pengajuan_pembiayaan.setEnabled(false);
        tipe_tujuan_pembiayaan.setEnabled(false);
        lokasi_pengambilan.setEnabled(false);
        alamat_lokasi.setEnabled(false);
    }

}
