package com.example.pembiayaanqu.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pembiayaanqu.R;

public class short_account extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.account_short, container, false);

        Button toFullAccount = (Button) view.findViewById(R.id.layout_id_to_fullAccount);
        toFullAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_account, new full_account()).commit();
            }
        });

        return view;
    }
}
