package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;

import java.util.ArrayList;
import java.util.Iterator;

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
                    hangMuc.setIdHinhAnh(cursor.getInt(3));
                    hangMuc.setLoaiHangMuc(cursor.getString(4));
                    hangMuc.setIdHangMucCha(cursor.getInt(5));

                    dsHangMuc.add(hangMuc);
                } while (cursor.moveToNext());
            }

            db.close();
        } else {
            Toast.makeText(activity, "khởi tạo csdl không thành công", Toast.LENGTH_SHORT).show();
        }
        return dsHangMuc;
    }

    public static ArrayList<HangMuc> getHangMucCha(Activity activity, String loaiHangMuc){
        ArrayList<HangMuc> dsHangMuc = getAllHangMuc(activity, loaiHangMuc);
        ArrayList<HangMuc> lsResult = new ArrayList<>();
        Iterator<HangMuc> itr = dsHangMuc.iterator();
        while (itr.hasNext()){
            HangMuc hangMuc = itr.next();
            if (hangMuc.getIdHangMucCha() == -1){
                lsResult.add(hangMuc);
            }
        }

        return lsResult;
    }

    public static ArrayList<HangMuc> getHangMucCon(Activity activity, String loaiHangMuc, int idHangMucCha){
        ArrayList<HangMuc> dsHangMuc = getAllHangMuc(activity, loaiHangMuc);
        ArrayList<HangMuc> lsResult = new ArrayList<>();
        Iterator<HangMuc> itr = dsHangMuc.iterator();
        while (itr.hasNext()){
            HangMuc hangMuc = itr.next();
            if (hangMuc.getIdHangMucCha() == idHangMucCha){
                lsResult.add(hangMuc);
            }
        }

        return lsResult;
    }

    public static HangMuc getHangMuc(Activity activity, String loaiHangMuc, int idHangMuc){
        ArrayList<HangMuc> dsHangMuc = getAllHangMuc(activity, loaiHangMuc);
        Iterator<HangMuc> iterator = dsHangMuc.iterator();
        while (iterator.hasNext()){
            HangMuc hangMuc = iterator.next();
            if (hangMuc.getIdHangMuc() == idHangMuc){
                return hangMuc;
            }
        }
        return null;
    }

    public static long addHangMuc(Activity activity, HangMuc hangMuc){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("tenHangMuc", hangMuc.getTenHangMuc());
        values.put("dienDai", hangMuc.getDienDai());
        values.put("icon", hangMuc.getIdHinhAnh());
        values.put("loaiHangMuc", hangMuc.getLoaiHangMuc());
        values.put("idHangMucCha", hangMuc.getIdHangMucCha());

        return db.insert("HangMuc", null, values);
    }

    public static long deleteHangMuc(Activity activity, int idHangMuc){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        return db.delete("HangMuc", "idHangMuc=?", new String[]{idHangMuc+""});
    }

    public static long updateHangMuc(FragmentActivity activity, HangMuc hangMuc) {
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("tenHangMuc", hangMuc.getTenHangMuc());
        values.put("dienDai", hangMuc.getDienDai());
        values.put("icon", hangMuc.getIdHinhAnh());
        values.put("loaiHangMuc", hangMuc.getLoaiHangMuc());
        values.put("idHangMucCha", hangMuc.getIdHangMucCha());

        return db.update("HangMuc", values, "idHangMuc=?", new String[]{hangMuc.getIdHangMuc()+""});
    }
}
