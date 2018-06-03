package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ThuTien;

import java.util.ArrayList;

public class SQLGhiChep {
    public static ArrayList<GhiChep> getAllGhiChep(Activity activity){
        ArrayList<GhiChep> dsGhiChep = new ArrayList<>();
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        String SQLQueryGhiChep = "Select * from GhiChep";

        if (db != null){
            Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

            if (cursor.moveToFirst()){
                do {
                    GhiChep ghiChep = null;
                    String loaiGhiChep = cursor.getString(7);
                    if (loaiGhiChep.equals("chi")) {
                        ghiChep = new ChiTien();
                    }else if (loaiGhiChep.equals("thu")){
                        ghiChep = new ThuTien();
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        ghiChep = new ChuyenKhoan();
                    }else {
                        Log.d("SQLGhiChep", "loại ghi chép không đúng");
                    }

                    ghiChep.setIdGhiChep(cursor.getInt(0));
                    ghiChep.setSoTien(cursor.getInt(1));
                    ghiChep.setDienDai(cursor.getString(2));
                    ghiChep.setUsername(cursor.getString(3));
                    ghiChep.setNgay(cursor.getString(4));
                    ghiChep.setDiaDiem(cursor.getString(5));
                    ghiChep.setIdHangMuc(cursor.getInt(6));
                    ghiChep.setLoaiGhiChep(loaiGhiChep);
                    ghiChep.setTrangthai(cursor.getInt(8));

                    if (loaiGhiChep.equals("chi")){
                        String SQLQueryChiTien = "Select * from ChiTien where idGhiChep="+ghiChep.getIdGhiChep();
                        ChiTien chiTien;
                        chiTien = (ChiTien) ghiChep;

                        Cursor cursorChiTien = db.rawQuery(SQLQueryChiTien, null);
                        if (cursorChiTien.moveToFirst()) {
                            chiTien.setChiChoAi(cursorChiTien.getString(0));
                            chiTien.setIdViTienChi(cursorChiTien.getInt(2));

                            dsGhiChep.add(chiTien);
                        }
                    }else if (loaiGhiChep.equals("thu")){
                        String SQLQueryThuTien = "Select * from ThuTien where idGhiChep="+ghiChep.getIdGhiChep();
                        Cursor cursorThuTien = db.rawQuery(SQLQueryThuTien, null);
                        ThuTien thuTien = (ThuTien) ghiChep;

                        if (cursorThuTien.moveToFirst()) {
                            thuTien.setThuTuAi(cursorThuTien.getString(1));
                            thuTien.setIdViTienThu(cursorThuTien.getInt(3));

                            dsGhiChep.add(thuTien);
                        }
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        String SQLQueryChuyenKhoan = "Select * from ChuyenKhoan where idGhiChep="+ghiChep.getIdGhiChep();
                        ChuyenKhoan chuyenKhoan = (ChuyenKhoan) ghiChep;
                        Cursor cursorChuyenKhoan = db.rawQuery(SQLQueryChuyenKhoan, null);

                        if (cursorChuyenKhoan.moveToFirst()) {
                            chuyenKhoan.setIdViTienChi(cursorChuyenKhoan.getInt(1));
                            chuyenKhoan.setIdViTienThu(cursorChuyenKhoan.getInt(3));

                            dsGhiChep.add(chuyenKhoan);
                        }
                    }
                }while (cursor.moveToNext());
            }
            db.close();
        }else {
            Toast.makeText(activity, "load csdl ghi chép không thành công", Toast.LENGTH_SHORT).show();
        }

        return dsGhiChep;
    }

    public static ArrayList<GhiChep> getGhiChepViTien(Activity activity, int idViTien){
        ArrayList<GhiChep> dsGhiChep = new ArrayList<>();
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        String SQLQueryGhiChep = "Select * from GhiChep";

        if (db != null){
            Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

            if (cursor.moveToFirst()){
                do {
                    GhiChep ghiChep = null;
                    String loaiGhiChep = cursor.getString(7);
                    if (loaiGhiChep.equals("chi")) {
                        ghiChep = new ChiTien();
                    }else if (loaiGhiChep.equals("thu")){
                        ghiChep = new ThuTien();
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        ghiChep = new ChuyenKhoan();
                    }else {
                        Log.d("SQLGhiChep", "loai ghi chep khong dung");
                    }

                    ghiChep.setIdGhiChep(cursor.getInt(0));
                    ghiChep.setSoTien(cursor.getInt(1));
                    ghiChep.setDienDai(cursor.getString(2));
                    ghiChep.setUsername(cursor.getString(3));
                    ghiChep.setNgay(cursor.getString(4));
                    ghiChep.setDiaDiem(cursor.getString(5));
                    ghiChep.setIdHangMuc(cursor.getInt(6));
                    ghiChep.setLoaiGhiChep(loaiGhiChep);
                    ghiChep.setTrangthai(cursor.getInt(8));

                    if (loaiGhiChep.equals("chi")){
                        String SQLQueryChiTien = "Select * from ChiTien where idGhiChep="+ghiChep.getIdGhiChep()+" and idViTienChi="+idViTien;
                        ChiTien chiTien;
                        chiTien = (ChiTien) ghiChep;

                        Cursor cursorChiTien = db.rawQuery(SQLQueryChiTien, null);
                        if (cursorChiTien.moveToFirst()) {
                            chiTien.setChiChoAi(cursorChiTien.getString(0));
                            chiTien.setIdViTienChi(cursorChiTien.getInt(2));

                            dsGhiChep.add(chiTien);
                        }
                    }else if (loaiGhiChep.equals("thu")){
                        String SQLQueryThuTien = "Select * from ThuTien where idGhiChep="+ghiChep.getIdGhiChep()+" and idViTienThu="+idViTien;
                        Cursor cursorThuTien = db.rawQuery(SQLQueryThuTien, null);
                        ThuTien thuTien = (ThuTien) ghiChep;

                        if (cursorThuTien.moveToFirst()) {
                            thuTien.setThuTuAi(cursorThuTien.getString(1));
                            thuTien.setIdViTienThu(cursorThuTien.getInt(3));

                            dsGhiChep.add(thuTien);
                        }
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        String SQLQueryChuyenKhoan = "Select * from ChuyenKhoan where idGhiChep="+ghiChep.getIdGhiChep()+" and idViTien="+idViTien;
                        ChuyenKhoan chuyenKhoan = (ChuyenKhoan) ghiChep;
                        Cursor cursorChuyenKhoan = db.rawQuery(SQLQueryChuyenKhoan, null);

                        if (cursorChuyenKhoan.moveToFirst()) {
                            chuyenKhoan.setIdViTienChi(cursorChuyenKhoan.getInt(1));
                            chuyenKhoan.setIdViTienThu(cursorChuyenKhoan.getInt(3));

                            dsGhiChep.add(chuyenKhoan);
                        }
                    }
                }while (cursor.moveToNext());
            }
            db.close();
        }else {
            Toast.makeText(activity, "load csdl ghi chép không thành công", Toast.LENGTH_SHORT).show();
        }

        return dsGhiChep;
    }

    public static ArrayList<GhiChep> getGhiChepNotSyn(Context context) {
        ArrayList<GhiChep> dsGhiChep = new ArrayList<>();
        SQLiteDatabase db = context.openOrCreateDatabase(DataBaseManager.DB_NAME, Context.MODE_PRIVATE, null);
        String SQLQueryGhiChep = "Select * from GhiChep where trangthai=?";

        if (db != null){
            Cursor cursor = db.rawQuery(SQLQueryGhiChep, new String[]{Layout_TrangChu.NOT_SYNCED_WITH_SERVER+""});

            if (cursor.moveToFirst()){
                do {
                    GhiChep ghiChep = null;
                    String loaiGhiChep = cursor.getString(7);
                    int idGhiChep = cursor.getInt(0);
                    if (loaiGhiChep.equals("chi")) {
                        ghiChep = new ChiTien();
                    }else if (loaiGhiChep.equals("thu")){
                        ghiChep = new ThuTien();
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        ghiChep = new ChuyenKhoan();
                    }else {
                        Log.d("SQLGhiChep", "loai ghi chep khong dung");
                    }

                    ghiChep.setIdGhiChep(cursor.getInt(0));
                    ghiChep.setSoTien(cursor.getInt(1));
                    ghiChep.setDienDai(cursor.getString(2));
                    ghiChep.setUsername(cursor.getString(3));
                    ghiChep.setNgay(cursor.getString(4));
                    ghiChep.setDiaDiem(cursor.getString(5));
                    ghiChep.setIdHangMuc(cursor.getInt(6));
                    ghiChep.setLoaiGhiChep(loaiGhiChep);
                    ghiChep.setTrangthai(cursor.getInt(8));

                    if (loaiGhiChep.equals("chi")){
                        String SQLQueryChiTien = "Select * from ChiTien where idGhiChep="+ghiChep.getIdGhiChep();
                        ChiTien chiTien;
                        chiTien = (ChiTien) ghiChep;

                        Cursor cursorChiTien = db.rawQuery(SQLQueryChiTien, null);
                        if (cursorChiTien.moveToFirst()) {
                            chiTien.setChiChoAi(cursorChiTien.getString(0));
                            chiTien.setIdViTienChi(cursorChiTien.getInt(2));

                            dsGhiChep.add(chiTien);
                        }
                    }else if (loaiGhiChep.equals("thu")){
                        String SQLQueryThuTien = "Select * from ThuTien where idGhiChep="+ghiChep.getIdGhiChep();
                        Cursor cursorThuTien = db.rawQuery(SQLQueryThuTien, null);
                        ThuTien thuTien = (ThuTien) ghiChep;

                        if (cursorThuTien.moveToFirst()) {
                            thuTien.setThuTuAi(cursorThuTien.getString(1));
                            thuTien.setIdViTienThu(cursorThuTien.getInt(3));

                            dsGhiChep.add(thuTien);
                        }
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        String SQLQueryChuyenKhoan = "Select * from ChuyenKhoan where idGhiChep="+ghiChep.getIdGhiChep();
                        ChuyenKhoan chuyenKhoan = (ChuyenKhoan) ghiChep;
                        Cursor cursorChuyenKhoan = db.rawQuery(SQLQueryChuyenKhoan, null);

                        if (cursorChuyenKhoan.moveToFirst()) {
                            chuyenKhoan.setIdViTienChi(cursorChuyenKhoan.getInt(1));
                            chuyenKhoan.setIdViTienThu(cursorChuyenKhoan.getInt(3));

                            dsGhiChep.add(chuyenKhoan);
                        }
                    }
                }while (cursor.moveToNext());
            }
            db.close();
        }else {
            Toast.makeText(context, "load csdl ghi chép không thành công", Toast.LENGTH_SHORT).show();
        }

        return dsGhiChep;
    }

    public static long updateGhiChep(Context context, GhiChep ghiChep) {
        SQLiteDatabase db = context.openOrCreateDatabase(DataBaseManager.DB_NAME, Context.MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("sotien", ghiChep.getSoTien());
        values.put("diendai", ghiChep.getDienDai());
        values.put("username", ghiChep.getUsername());
        values.put("ngay", ghiChep.getNgay());
        values.put("diadiem", ghiChep.getDiaDiem());
        values.put("idhangmuc", ghiChep.getIdHangMuc());
        values.put("loaighichep", ghiChep.getLoaiGhiChep());
        values.put("trangthai", ghiChep.getTrangthai());

        return db.update("GhiChep", values, "idghichep = ?", new String[]{ghiChep.getIdGhiChep()+""});
    }
}
