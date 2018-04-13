package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lv.vietcuong.project2.Model.HanMucChi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class SQLHanMucChi {
    public static ArrayList<HanMucChi> getAllHanMucChi(Activity activity){
        ArrayList<HanMucChi> dsWallet = new ArrayList<>();

        String SQLQuery = "SELECT * FROM HanMucChi";
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        if (database != null){
            Log.d("DB", "Copy database thành công");
        }else {
            Log.d("DB", "Copy database thất bại");
        }
        Cursor cursor = database.rawQuery(SQLQuery, null);

        if(cursor.moveToFirst()){
            do{
                HanMucChi hanMucChi = new HanMucChi();

                hanMucChi.setIdHanMucChi(cursor.getInt(0));
                hanMucChi.setTenHanMucChi(cursor.getString(1));
                hanMucChi.setLapLai(cursor.getString(2));
                hanMucChi.setNgayBatDau(cursor.getString(3));
                hanMucChi.setNgayKetThuc(cursor.getString(4));
                hanMucChi.setSoTien(cursor.getInt(5));

                dsWallet.add(hanMucChi);
            }while(cursor.moveToNext());
        }

        database.close();
        Log.d("DB", "lấy dữ liệu thành công");
        return dsWallet;
    }

    public static long addHanMucChi(Activity activity, HanMucChi hanMucChi){
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("tenHanMucChi", hanMucChi.getTenHanMucChi());
        values.put("lapLai", hanMucChi.getLapLai());
        values.put("ngayBatDau", hanMucChi.getNgayKetThuc().toString());
        values.put("soTien", hanMucChi.getSoTien());

        return database.insert("HanMucChi", null, values);
    }
}
