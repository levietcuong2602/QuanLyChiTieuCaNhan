package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class SQLViTien {
    private static final String TABLE_NAME = "ViTien";

    public static ArrayList<ViTien> getAllWallet(Activity activity){
        ArrayList<ViTien> dsWallet = new ArrayList<>();

        String SQLQuery = "SELECT * FROM ViTien";
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        if (database != null) {
            Log.d("DB", "vừa khởi tạo được csdl.");

            Cursor cursor = database.rawQuery(SQLQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    ViTien wallet = new ViTien();

                    int id = cursor.getInt(0);
                    String nameWallet = cursor.getString(1);
                    String loaiViTien = cursor.getString(2);
                    int balance = cursor.getInt(3);
                    String ghiChu = cursor.getString(4);
                    String username = cursor.getString(5);

                    wallet.setIdViTien(id);
                    wallet.setTenViTien(nameWallet);
                    wallet.setLoaiVi(loaiViTien);
                    wallet.setSoDu(balance);
                    wallet.setGhiChu(ghiChu);
                    wallet.setUsername(username);

                    dsWallet.add(wallet);
                } while (cursor.moveToNext());
            }

            database.close();
            Log.d("DB", "lấy dữ liệu wallet thành công");
        }else{
            Log.d("DB", "khoi tao database khong thanh cong");
        }
        return dsWallet;
    }

    public static long addViTien(Activity activity, ViTien viTien){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("tenViTien", viTien.getTenViTien());
        values.put("loaiViTien", viTien.getLoaiVi());
        values.put("soDu", viTien.getSoDu());
        values.put("ghiChu", viTien.getGhiChu());
        values.put("username", viTien.getUsername());

        return db.insert("ViTien", null, values);
    }

    public static long deleteViTien(Activity activity, int idViTien){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        return db.delete("ViTien", "idViTien=?", new String[]{idViTien+""});
    }

    public static String getTenViTien(Activity activity, int idViTien){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        Cursor cursor = db.rawQuery("select tenViTien from ViTien where idViTien = " +idViTien,null);
        cursor.moveToFirst();
        if (cursor.getCount() != 0){
            String tenViTien = cursor.getString(0);
            db.close();
            return tenViTien;
        }else return null;
    }
}