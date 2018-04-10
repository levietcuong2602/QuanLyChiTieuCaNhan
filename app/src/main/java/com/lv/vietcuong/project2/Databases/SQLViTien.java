package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lv.vietcuong.project2.Model.ViTien;

import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class SQLWallet {
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

    public static String getTenViTien(Activity activity, int idViTien){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        Cursor cursor = db.rawQuery("select tenViTien from ViTien where idViTien = " +idViTien,null);
        cursor.moveToFirst();
        String tenViTien = cursor.getString(0);
        db.close();
        return tenViTien;
    }
}
