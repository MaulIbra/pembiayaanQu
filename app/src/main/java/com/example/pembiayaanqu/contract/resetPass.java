package com.example.pembiayaanqu.contract;

public interface resetPass {
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
        void resetPassword(String userEmail);
    }
}
