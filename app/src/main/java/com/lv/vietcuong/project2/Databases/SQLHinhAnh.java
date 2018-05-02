package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lv.vietcuong.project2.Model.ObjectClass.HinhAnh;

import java.util.ArrayList;
import java.util.Iterator;

public class SQLHinhAnh {
    public static ArrayList<HinhAnh> getAllHinhAnh(Activity activity){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        ArrayList<HinhAnh> dsHinhAnh = new ArrayList<>();
        String SQLQuery = "SELECT * FROM HinhAnh";
        Cursor cursor = db.rawQuery(SQLQuery, null);
        if (cursor.moveToFirst()){
            do {
                HinhAnh hinhAnh = new HinhAnh();
                hinhAnh.setIdHinhAnh(cursor.getInt(0));
                hinhAnh.setHinhAnh(cursor.getBlob(1));
                hinhAnh.setLoaiHinhAnh(cursor.getString(2));

                dsHinhAnh.add(hinhAnh);
            }while (cursor.moveToNext());
        }
        db.close();
        return dsHinhAnh;
    }

    public static ArrayList<HinhAnh> getAllHinhAnhType(Activity activity, String loaiHinhAnh){
        ArrayList<HinhAnh> arrayList = getAllHinhAnh(activity);
        ArrayList<HinhAnh> arrayResult = new ArrayList<>();

        for (HinhAnh hinh: arrayList){
            if (hinh.getLoaiHinhAnh().equals(loaiHinhAnh)){
                arrayResult.add(hinh);
            }
        }

        return arrayResult;
    }

    public static HinhAnh getHinhAnh(Activity activity, int idHinhAnh){
        ArrayList<HinhAnh> dsHinhAnh = getAllHinhAnh(activity);
        Iterator<HinhAnh> iterator = dsHinhAnh.iterator();
        while (iterator.hasNext()){
            HinhAnh hinhAnh = iterator.next();
            if (hinhAnh.getIdHinhAnh() == idHinhAnh){
                return hinhAnh;
            }
        }
        return null;
    }

    public static Bitmap getImage(Activity activity, int idHinhAnh){
        HinhAnh hinhAnh = getHinhAnh(activity, idHinhAnh);
        if (hinhAnh != null) {
            byte[] data = hinhAnh.getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            return bitmap;
        }
        return null;
    }

    public static int addHinhAnh(Activity activity, byte []hinhanh, String loaiHinhAnh){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        String sql = "INSERT INTO HinhAnh VALUES (null, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1, hinhanh);
        statement.bindString(2, loaiHinhAnh);
        statement.executeInsert();

        String queryLastRowInserted = "select last_insert_rowid()";

        final Cursor cursor = db.rawQuery(queryLastRowInserted, null);
        int _idLastInsertedRow = 0;
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    _idLastInsertedRow = cursor.getInt(0);
                }
            } finally {
                cursor.close();
            }
        }

        return _idLastInsertedRow;
    }

}
