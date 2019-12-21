package com.final_test.moneylovely.view;

import android.app.Activity;

import com.developer.kalert.KAlertDialog;

public class DialogCustom {
    public static void createDialogCustom(String messs, final Activity activity){
        new KAlertDialog(activity, KAlertDialog.SUCCESS_TYPE)
                .setTitleText("Thông báo")
                .setContentText(messs)
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog kAlertDialog) {
                        kAlertDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}
