package com.lv.vietcuong.project2.Databases;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.test.ActivityTestCase;
import android.util.Log;

import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.HanMucChi;

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
                    hanMucChi.setTrangthai(cursor.getInt(6));
                    hanMucChi.setUsername(cursor.getString(7));

                    dsWallet.add(hanMucChi);
                }while(cursor.moveToNext());
            }
            Log.d("DB", "lấy dữ liệu thành công");
            database.close();
        }else {
            Log.d("DB", "Copy database thất bại");
        }
        return dsWallet;
    }

    public static long addHanMucChi(Activity activity, HanMucChi hanMucChi){
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("tenHanMucChi", hanMucChi.getTenHanMucChi());
        values.put("lapLai", hanMucChi.getLapLai());
        values.put("ngayBatDau", hanMucChi.getNgayBatDau());
        values.put("ngayKetThuc", hanMucChi.getNgayKetThuc());
        values.put("soTien", hanMucChi.getSoTien());
        values.put("trangthai", hanMucChi.getTrangthai());
        values.put("username", hanMucChi.getUsername());

        return database.insert("HanMucChi", null, values);
    }

    public static long deleteHanMucChi(Activity activity, int idHanMucChi){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        return db.delete("HanMucChi", "idHanMucChi=?", new String[]{idHanMucChi+""});
    }

    public static int getIdHanMucChiJustAdd(Activity activity){
        ArrayList<HanMucChi> dsHanMucChi = getAllHanMucChi(activity);
        int id = dsHanMucChi.get(dsHanMucChi.size()-1).getIdHanMucChi();
        return id;
    }

    public static long addHanMucChiHangMuc(Activity activity, int idHanMucChi, int idHangMuc){
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("idHangMuc", idHangMuc);
        values.put("idHanMucChi", idHanMucChi);

        return database.insert("HangMuc_HanMucChi", null, values);
    }

    public static long addHanMucChiTaiKhoan(Activity activity, int idHanMucChi, String username){
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("idHanMucChi", idHanMucChi);

        return database.insert("TaiKhoan_HanMucChi", null, values);
    }

    public static ArrayList<HanMucChi> getHanMucChiNotSync(Context context) {
        ArrayList<HanMucChi> dsHanMucChi = new ArrayList<>();
        String query = "select * from hanmucchi where trangthai=?";
        SQLiteDatabase db = context.openOrCreateDatabase(DataBaseManager.DB_NAME, Context.MODE_PRIVATE, null);
        if (db != null) {
            Cursor cursor = db.rawQuery(query, new String[]{Layout_TrangChu.NOT_SYNCED_WITH_SERVER + ""});
            if (cursor.moveToFirst()) {
                do {
                    HanMucChi hanMucChi = new HanMucChi();
                    hanMucChi.setIdHanMucChi(cursor.getInt(0));
                    hanMucChi.setTenHanMucChi(cursor.getString(1));
                    hanMucChi.setLapLai(cursor.getString(2));
                    hanMucChi.setNgayBatDau(cursor.getString(3));
                    hanMucChi.setNgayKetThuc(cursor.getString(4));
                    hanMucChi.setSoTien(cursor.getInt(5));
                    hanMucChi.setTrangthai(cursor.getInt(6));
                    hanMucChi.setUsername(cursor.getString(7));

                    dsHanMucChi.add(hanMucChi);
                } while (cursor.moveToNext());
            }
        }
        return dsHanMucChi;
    }

    public static long updateHanMucChi(Context context, HanMucChi hanMucChi) {
        SQLiteDatabase db = context.openOrCreateDatabase(DataBaseManager.DB_NAME, Context.MODE_PRIVATE, null);
        if (db != null) {
            ContentValues values = new ContentValues();
            values.put("tenHanMucChi", hanMucChi.getTenHanMucChi());
            values.put("lapLai", hanMucChi.getLapLai());
            values.put("ngayBatDau", hanMucChi.getNgayBatDau());
            values.put("ngayKetThuc", hanMucChi.getNgayKetThuc());
            values.put("soTien", hanMucChi.getSoTien());
            values.put("trangthai", hanMucChi.getTrangthai());
            values.put("username", hanMucChi.getUsername());

            return db.update("HanMucChi", values, "idhanmucchi=?", new String[]{hanMucChi.getIdHanMucChi() + ""});
        }else{
            return -1;
        }
    }

    public static long addHanMucChiViTien(FragmentActivity activity, int idHanMucChi, int idViTien) {
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(activity);
        ContentValues values = new ContentValues();
        values.put("idViTien", idViTien);
        values.put("idHanMucChi", idHanMucChi);

        return database.insert("ViTien_HanMucChi", null, values);
    }
}
