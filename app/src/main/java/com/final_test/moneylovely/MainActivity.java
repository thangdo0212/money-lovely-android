package com.final_test.moneylovely;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.final_test.moneylovely.database.Database;

/**
 * @author Thang Do
 */

public class MainActivity extends AppCompatActivity {

    Button btDangKy, btDangNhap;
    EditText edTenDangNhap, edMatKhau;
    public static Database database;
    public static int UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        createDatabase();

        btDangNhap.setOnClickListener(v -> login());

        btDangKy.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(it);
        });

    }

    public void mapping() {
        edTenDangNhap = (EditText) findViewById(R.id.edTenDangNhap);
        edMatKhau = (EditText) findViewById(R.id.edMatKhau);
        btDangNhap = (Button) findViewById(R.id.btDangNhap);
        btDangKy = (Button) findViewById(R.id.btDangKy);

    }

    private void createDatabase() {

        database = new Database(this, "money_management", null, 1);

        // create tables
        database.QueryData("CREATE TABLE IF NOT EXISTS user(iduser INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(20), deleteflag INTEGER,password VARCHAR(20), full_name VARCHAR(100), address VARCHAR(400))");
        database.QueryData("CREATE TABLE IF NOT EXISTS LoaiThu(id INTEGER PRIMARY KEY , tenloai VARCHAR(20), deleteflag INTEGER,imgIcon BLOB,idUser INTEGER)");
        database.QueryData("CREATE TABLE IF NOT EXISTS LoaiChi(id INTEGER PRIMARY KEY , tenloai VARCHAR(20), deleteflag INTEGER,imgIcon BLOB,idUser INTEGER)");
        database.QueryData("CREATE TABLE IF NOT EXISTS DoanhThu(id INTEGER PRIMARY KEY , nameCV VARCHAR(20), money DECIMAL,donviThu VARCHAR(20),danhGia INTEGER,deleteFlag INTEGER,date DATE, ghiChu VARCHAR(50),img BLOB,idLoai INTEGER, idUser INTEGER)");
        database.QueryData("CREATE TABLE IF NOT EXISTS KhoangChi(id INTEGER PRIMARY KEY , nameCV VARCHAR(20), money DECIMAL,donviThu VARCHAR(20),danhGia INTEGER,deleteFlag INTEGER,date DATE, ghiChu VARCHAR(50),img BLOB,idLoai INTEGER, idUser INTEGER)");

    }

    public void login() {
        final String username = edTenDangNhap.getText().toString();
        final String password = edMatKhau.getText().toString();


        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(MainActivity.this, "Các mục đang trống", Toast.LENGTH_SHORT).show();
        } else {
            int isSuccess = database.checkAccount(username, password);
            if (isSuccess > 0) {
                UserID = isSuccess;
                Intent it = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(it);
            } else {
                Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mất khẩu chưa chính xác", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
