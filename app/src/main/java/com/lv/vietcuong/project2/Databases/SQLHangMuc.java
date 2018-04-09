package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.app.admin.DeviceAdminInfo;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.lv.vietcuong.project2.Model.HangMuc;

import java.util.ArrayList;

public class SQLHangMuc {
    public static ArrayList<HangMuc> getAllHangMuc(Activity activity, String loaiHangMuc) {
        ArrayList<HangMuc> dsHangMuc = new ArrayList<>();
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        String SQLQuery = "SELECT * FROM HangMuc WHERE loaiHangMuc = " + "'"+loaiHangMuc +"'";

        if (db != null) {
            Cursor cursor = db.rawQuery(SQLQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    HangMuc hangMuc = new HangMuc();

                    hangMuc.setIdHangMuc(cursor.getInt(0));
                    hangMuc.setTenHangMuc(cursor.getString(1));
                    hangMuc.setDienDai(cursor.getString(2));
                    hangMuc.setIcon(cursor.getInt(3));
                    hangMuc.setLoaiHangMuc(cursor.getString(4));

                    dsHangMuc.add(hangMuc);
                } while (cursor.moveToNext());
            }

            db.close();
        } else {
            Toast.makeText(activity, "khởi tạo csdl không thành công", Toast.LENGTH_SHORT).show();
        }
        return dsHangMuc;
    }

    public static long addHangMuc(Activity activity, HangMuc hangMuc){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("tenHangMuc", hangMuc.getTenHangMuc());
        values.put("dienDai", hangMuc.getDienDai());
        values.put("icon", hangMuc.getIcon());
        values.put("loaiHangMuc", hangMuc.getLoaiHangMuc());

        return db.insert("HangMuc", null, values);
    }

    public static long deleteHangMuc(Activity activity, int idHangMuc){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        return db.delete("HangMuc", "idHangMuc=?", new String[]{idHangMuc+""});
    }
}
