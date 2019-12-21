package com.final_test.moneylovely;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.final_test.moneylovely.database.Database;

public class RegisterActivity extends AppCompatActivity {

    EditText edTenDangNhap, edMatKhau, edHoTen, edDiaChi;
    Button btDangKy, btHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mapping();
        btDangKy.setOnClickListener((v) -> createAccount());
        btHuy.setOnClickListener((v) -> finish());
    }

    private void mapping() {
        edTenDangNhap = (EditText) findViewById(R.id.edTenDangNhap);
        edMatKhau = (EditText) findViewById(R.id.edMatKhau);
        edHoTen = (EditText) findViewById(R.id.edHoTen);
        edDiaChi = (EditText) findViewById(R.id.edDiaChi);
        btDangKy = (Button) findViewById(R.id.btDangKy);
        btHuy = (Button) findViewById(R.id.btHuy);
    }

    private void showToast(String show) {
        LayoutInflater inflater1 = getLayoutInflater();
        View layout = inflater1.inflate(R.layout.them_thanh_cong, (ViewGroup) findViewById(R.id.toast_root));
        TextView text = (TextView) layout.findViewById(R.id.tvToast);
        text.setText(show);
        Toast toast = new Toast(getApplicationContext());
        //toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private void createAccount() {
        String table = "user";
        String username = edTenDangNhap.getText().toString().trim();
        String password = edMatKhau.getText().toString().trim();
        String full_name = edHoTen.getText().toString().trim();
        String address = edDiaChi.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty() || full_name.isEmpty() || address.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Không được bở trống các trường thông tin", Toast.LENGTH_LONG).show();
        } else {
            MainActivity.database.insertUser(table, username, 1, password, full_name, address);
            showToast("Tạo tài khoản thành công");
            Intent it = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(it);
        }
    }
}
