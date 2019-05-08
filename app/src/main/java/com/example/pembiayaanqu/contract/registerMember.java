package com.example.pembiayaanqu.contract;

import java.util.Map;
import java.util.Objects;

public interface registerMember {

    interface view{
        void showProgressBar();
        void hideProgressBar();
        void changeActivity();
        void displayToastSuccess();
        void displayToastFailure();
        void displayToastIsEmpty();
        void backActivity();
    }

    interface presenter{
        void createAuth(String userName,String phoneNumber,String email, String Password,String collection);
    }
}
