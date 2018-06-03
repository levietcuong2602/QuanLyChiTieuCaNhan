package com.lv.vietcuong.project2.Databases;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class SQLViTien {
    private static final String TABLE_NAME = "ViTien";
    private static final String GET_VITIEN = "SELECT * FROM ViTien";
    private static final String GET_VITIEN_BY_ID = "select tenViTien from ViTien where idViTien = ?";
    private static final String GET_VITIEN_NOT_SYNCED = "select * from ViTien where trangthai = ?";

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
                    int trangthai = cursor.getInt(6);
                    int idhangmucthu = cursor.getInt(7);

                    wallet.setIdViTien(id);
                    wallet.setTenViTien(nameWallet);
                    wallet.setLoaiVi(loaiViTien);
                    wallet.setSoDu(balance);
                    wallet.setGhiChu(ghiChu);
                    wallet.setUsername(username);
                    wallet.setTrangthai(trangthai);
                    wallet.setIdHangMucThu(idhangmucthu);

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
        values.put("trangthai", viTien.getTrangthai());
        values.put("idHangMuc", viTien.getIdHangMucThu());

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

    public static ArrayList<ViTien> getViTienNotSynced(Context context){
        ArrayList<ViTien> dsViTien = new ArrayList<>();
        SQLiteDatabase db = context.openOrCreateDatabase(DataBaseManager.DB_NAME, Context.MODE_PRIVATE, null);
        if (db != null){
            Cursor cursor = db.rawQuery(GET_VITIEN_NOT_SYNCED, new String[]{Layout_TrangChu.NOT_SYNCED_WITH_SERVER+""});
            if (cursor.moveToFirst()){
                do {
                    ViTien viTien = new ViTien();
                    viTien.setIdViTien(cursor.getInt(0));
                    viTien.setTenViTien(cursor.getString(1));
                    viTien.setLoaiVi(cursor.getString(2));
                    viTien.setSoDu(cursor.getInt(3));
                    viTien.setGhiChu(cursor.getString(4));
                    viTien.setUsername(cursor.getString(5));
                    viTien.setTrangthai(cursor.getInt(6));
                    viTien.setIdHangMucThu(cursor.getInt(7));

                    dsViTien.add(viTien);
                }while(cursor.moveToNext());
            }
        }

        return dsViTien;
    }

    public static long updateViTien(Context context, ViTien viTien) {
        SQLiteDatabase db = context.openOrCreateDatabase(DataBaseManager.DB_NAME, Context.MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("tenViTien", viTien.getTenViTien());
        values.put("loaiViTien", viTien.getLoaiVi());
        values.put("soDu", viTien.getSoDu());
        values.put("ghiChu", viTien.getGhiChu());
        values.put("username", viTien.getUsername());
        values.put("trangthai", viTien.getTrangthai());
        values.put("idHangMuc", viTien.getIdHangMucThu());

        return db.update("ViTien", values, "idViTien=?", new String[]{viTien.getIdViTien()+""});
    }
}