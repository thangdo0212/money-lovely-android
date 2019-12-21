package com.final_test.moneylovely.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Database extends SQLiteOpenHelper {


    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //thêm loại cho các hoạt động
    public void insertType(String table, String tenloai, int deleteflag, byte[] imgIcon, int idUser) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO '" + table + "' VALUES(null, ? ,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, tenloai);
        statement.bindString(2, deleteflag + "");
        statement.bindBlob(3, imgIcon);
        statement.bindLong(4, idUser);
        statement.executeInsert();
    }

    //thêm công việc ( chi cho việc gì || thu cho việc gì )
    public void insertJob(String table, String nameCV, String money, String donviThu, int danhGia, int deleteFlag, String date, String ghiChu, byte[] img, int idLoai, int idUser) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO '" + table + "' VALUES(null, ?, ? ,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, nameCV);
        statement.bindString(2, money);
        statement.bindString(3, donviThu);
        statement.bindString(4, danhGia + "");
        statement.bindString(5, deleteFlag + "");
        statement.bindString(6, date);
        statement.bindString(7, ghiChu);
        statement.bindBlob(8, img);
        statement.bindString(9, idLoai + "");
        statement.bindLong(10, idUser);


        statement.executeInsert();
    }

    // thêm người dùng
    public void insertUser(String table, String username, int deleteFlag, String password, String fullName, String address) {
        if (checkExistingUsername(username) == true) {
            throw new IllegalArgumentException("username is existing");
        }
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO '" + table + "' VALUES(null, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, username);
        statement.bindString(2, deleteFlag + "");
        statement.bindString(3, password);
        statement.bindString(4, fullName);
        statement.bindString(5, address);
        statement.executeInsert();
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    private boolean checkExistingUsername(String username) {
        Cursor result = GetData("SELECT * FROM user WHERE username = '" + username + "'");
        if (result != null) {
            return false;
        } else {
            return true;
        }
    }

    public int checkAccount(String username, String password) {
        Cursor result = GetData("SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'");
        if(result != null) {
            result.moveToFirst();
        } else {
            return 0;
        }

        if(result != null && result.getCount() > 0) {
            return result.getInt(0);
        } else {
            return 0;
        }
    }
}
