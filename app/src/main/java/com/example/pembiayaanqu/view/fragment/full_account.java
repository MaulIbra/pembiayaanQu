package com.example.pembiayaanqu.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pembiayaanqu.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class full_account extends Fragment {

    private TextView nomorHP;
    private TextView email;
    private TextView umur;
    private TextView status;
    private TextView jenis_kelamin;
    private TextView pendidikan_terakhir;
    private TextView pekerjaan_usaha;
    private TextView noNPWP;
    private TextView noKTP;
    private TextView lama_bekerja_usaha;
    private TextView nama_perusahaan_saat_bekerja;
    private TextView jumlah_pengajuan_pembiayaan;
    private TextView tipe_tujuan_pembiayaan;
    private TextView provinsi;
    private TextView kota_kabupaten;
    private TextView kecamatan;
    private TextView kodePos;
    private TextView alamatLengkap;
    private TextView lokasi_pengambilan;
    private TextView alamat_lokasi;
    private ImageView imageKTP;
    private ImageView imageKK;
    private Button toShorAccount;


    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_account_full, container, false);

        toShorAccount = view.findViewById(R.id.layout_id_to_shortAccount);
        toShorAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_account, new short_account()).commit();
            }
        });

        imageKK = view.findViewById(R.id.imageKK);
        imageKTP = view.findViewById(R.id.imageKTP);
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
        provinsi = view.findViewById(R.id.provinsi);
        kota_kabupaten = view.findViewById(R.id.kota_kabupaten);
        kecamatan = view.findViewById(R.id.kecamatan);
        alamatLengkap = view.findViewById(R.id.alamatLengkap);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        final FirebaseUser user = mAuth.getCurrentUser();
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
                                jenis_kelamin.setText(documentSnapshot.getString("Jenis Kelamin"));
                                pendidikan_terakhir.setText(documentSnapshot.getString("Pendidikan Terakhir"));
                                pekerjaan_usaha.setText(documentSnapshot.getString("Pekerjaan atau Usaha"));
                                noNPWP.setText(documentSnapshot.getString("Nomor NPWP"));
                                noKTP.setText(documentSnapshot.getString("Nomor KTP"));
                                lama_bekerja_usaha.setText(documentSnapshot.getString("Lama bekerja atau Usaha"));
                                nama_perusahaan_saat_bekerja.setText(documentSnapshot.getString("Nama Perusahaan saat Bekerja"));
                                jumlah_pengajuan_pembiayaan.setText(documentSnapshot.getString("Jumlah Pengajuan Pembiayaan"));
                                tipe_tujuan_pembiayaan.setText(documentSnapshot.getString("Tipe Tujuan Pembiayaan"));
                                provinsi.setText(documentSnapshot.getString("Provinsi"));
                                kota_kabupaten.setText(documentSnapshot.getString("KotaKabupaten"));
                                kecamatan.setText(documentSnapshot.getString("Kecamatan"));
//                                kodePos.setText(documentSnapshot.getString("Kode Pos"));
                                alamatLengkap.setText(documentSnapshot.getString("Alamat Lengkap"));
                                if (documentSnapshot.getString("uriPhotoKk") != null){
                                    imageKK.setVisibility(View.VISIBLE);
                                    Glide.with(getActivity()).load(documentSnapshot.getString("uriPhotoKk")).into(imageKK);
                                }
                                if (documentSnapshot.getString("uriPhotoKtp") != null){
                                    imageKTP.setVisibility(View.VISIBLE);
                                    Glide.with(getActivity()).load(documentSnapshot.getString("uriPhotoKtp")).into(imageKTP);
                                }
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
