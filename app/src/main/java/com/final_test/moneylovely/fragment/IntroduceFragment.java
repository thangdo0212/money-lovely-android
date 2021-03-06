package com.final_test.moneylovely.fragment;


import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.final_test.moneylovely.MainActivity;
import com.final_test.moneylovely.R;
import com.final_test.moneylovely.view.CallbackFragment;


public class IntroduceFragment extends Fragment {

    EditText edTenDangNhap, edMatKhau, edHoTen, edDiaChi;
    Button btDmk, btHuy;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduce, container, false);
        anhxa(view);
        getUserInfo(MainActivity.UserID);
        btDmk.setOnClickListener(v -> changePass());
        //onclick callback
        CallbackFragment.callBackFragment(view, fragmentManager);
        return view;


    }

    private void anhxa(View view) {
        edTenDangNhap = (EditText) view.findViewById(R.id.edTenDangNhap);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhau);
        edHoTen = (EditText) view.findViewById(R.id.edHoTen);
        edDiaChi = (EditText) view.findViewById(R.id.edDiaChi);
        btDmk = (Button) view.findViewById(R.id.btDmk);
    }

    private void getUserInfo(int UserID) {
        Cursor res = MainActivity.database.GetData("SELECT * FROM user WHERE iduser = " + UserID );
        if(res.moveToFirst() && res.getCount() > 0) {
            res.moveToFirst();
            edTenDangNhap.setText(res.getString(1));
            edHoTen.setText(res.getString(4));
            edMatKhau.setText(res.getString(3));
            edDiaChi.setText(res.getString(5));
            disableEditText(edTenDangNhap);
            disableEditText(edHoTen);
            disableEditText(edDiaChi);
        }
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    private void changePass() {
        String pass = edMatKhau.getText().toString().trim();
        if(pass.isEmpty()) {
            Toast.makeText(getActivity(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.database.QueryData("UPDATE user SET password = '" + pass + "' WHERE iduser = " + MainActivity.UserID);
        }
    }
}