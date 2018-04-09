package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;

import com.lv.vietcuong.project2.Model.TaiKhoan;

import java.util.ArrayList;

public class SQLTaiKhoan {
    public static ArrayList<TaiKhoan> getAllTaiKhoan(Activity activity){
        ArrayList<TaiKhoan>dsTaiKhoan = new ArrayList<>();
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        String SQLQuery = "Select * from TaiKhoan";
        if (database != null) {
            Cursor cursor = database.rawQuery(SQLQuery, null);

            if (cursor.moveToFirst()){
                do {
                    TaiKhoan taiKhoan = new TaiKhoan();

                    taiKhoan.setUsername(cursor.getString(0));
                    taiKhoan.setPassword(cursor.getString(1));
                    taiKhoan.setHoTen(cursor.getString(2));
                    taiKhoan.setIdGiaDinh(cursor.getInt(3));
                    taiKhoan.setLoaiTaiKhoan(cursor.getString(4));

                    dsTaiKhoan.add(taiKhoan);
                }while (cursor.moveToNext());
            }
        }
        database.close();

        return dsTaiKhoan;
    }

    public static long addTaiKhoan(Activity activity, TaiKhoan taiKhoan){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("username", taiKhoan.getUsername());
        values.put("password", taiKhoan.getPassword());
        values.put("hoten", taiKhoan.getHoTen());
        values.put("idGiaDinh", taiKhoan.getIdGiaDinh());
        values.put("loaiTaiKhoan", taiKhoan.getLoaiTaiKhoan());

        return db.insert("TaiKhoan", null, values);
    }

    public static long updatedAccount(FragmentActivity activity, TaiKhoan taiKhoan) {
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues value = new ContentValues();
        value.put("password", taiKhoan.getPassword());
        value.put("hoten", taiKhoan.getHoTen());

        return db.update("TaiKhoan", value, "username=?", new String[]{taiKhoan.getUsername()});
    }
}
