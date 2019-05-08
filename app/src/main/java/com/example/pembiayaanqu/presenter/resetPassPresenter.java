package com.example.pembiayaanqu.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.example.pembiayaanqu.contract.resetPass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassPresenter implements resetPass.presenter {

    resetPass.view view;
    FirebaseAuth mAuth;

    public resetPassPresenter(resetPass.view view) {
        this.view = view;
    }

    @Override
    public void resetPassword(String userEmail) {
        mAuth = FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(userEmail)){
            view.displayToastIsEmpty();
        }else{
            view.showProgressBar();
            mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        view.hideProgressBar();
                        view.displayToastSuccess();
                        view.changeActivity();
                    }
                    else{
                        view.displayToastFailure();
                    }
                }
            });
        }
    }
}
