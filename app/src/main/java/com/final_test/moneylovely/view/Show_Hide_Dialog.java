package com.final_test.moneylovely.view;

import android.app.ProgressDialog;

public class Show_Hide_Dialog {
    public static void showProgressDialogWithTitle(String substring, ProgressDialog progressDialog) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(substring);
        progressDialog.show();
    }

    public static void hideProgressDialogWithTitle(ProgressDialog progressDialog) {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.dismiss();
    }
}
