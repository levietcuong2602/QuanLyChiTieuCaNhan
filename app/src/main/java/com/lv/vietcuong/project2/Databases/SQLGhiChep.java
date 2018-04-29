package com.lv.vietcuong.project2.Databases;

import android.accounts.Account;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ChiTien;
import com.lv.vietcuong.project2.Model.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.GhiChep;
import com.lv.vietcuong.project2.Model.ThuTien;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThuchiThang;

import java.util.ArrayList;
import java.util.Collections;

public class SQLGhiChep {

    public static ArrayList<GhiChep> getAllGhiChep(Activity activity){
    ArrayList<GhiChep> dsGhiChep = new ArrayList<>();
    SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
    String SQLQueryGhiChep = "Select * from GhiChep where username = '" + Layout_TrangChu.taiKhoanDangNhap.getUsername()+"'";

        if (db != null){
        Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

        if (cursor.moveToFirst()){
            do {
                GhiChep ghiChep = null;
                String loaiGhiChep = cursor.getString(6);
                if (loaiGhiChep.equals("chitien")) {
                    ghiChep = new ChiTien();
                }else if (loaiGhiChep.equals("thutien")){
                    ghiChep = new ThuTien();
                }else if (loaiGhiChep.equals("chuyenkhoan")){
                    ghiChep = new ChuyenKhoan();
                }

                ghiChep.setIdGhiChep(cursor.getInt(0));
                ghiChep.setSoTien(cursor.getInt(1));
                ghiChep.setDienGiai(cursor.getString(2));
                ghiChep.setUsername(cursor.getString(3));
                ghiChep.setNgay(cursor.getString(4));
                ghiChep.setIdHangMuc(cursor.getInt(5));
                ghiChep.setLoaiGhiChep(loaiGhiChep);

                if (loaiGhiChep.equals("chitien")){
                    String SQLQueryChiTien = "Select * from ChiTien where idGhiChep="+ghiChep.getIdGhiChep();
                    ChiTien chiTien;
                    chiTien = (ChiTien) ghiChep;

                    Cursor cursorChiTien = db.rawQuery(SQLQueryChiTien, null);
                    if (cursorChiTien.moveToFirst()) {
                        chiTien.setChiChoAi(cursorChiTien.getString(0));
                        chiTien.setIdViTienChi(cursorChiTien.getInt(2));

                        dsGhiChep.add(chiTien);
                    }
                }else if (loaiGhiChep.equals("thutien")){
                    String SQLQueryThuTien = "Select * from ThuTien where idGhiChep= " + ghiChep.getIdGhiChep();
                    Cursor cursorThuTien = db.rawQuery(SQLQueryThuTien, null);
                    ThuTien thuTien = (ThuTien) ghiChep;

                    if (cursorThuTien.moveToFirst()) {
                        thuTien.setThuTuAi(cursorThuTien.getString(1));
                        thuTien.setIdViTienThu(cursorThuTien.getInt(3));

                        dsGhiChep.add(thuTien);
                    }
                }else if (loaiGhiChep.equals("chuyenkhoan")){
                    String SQLQueryChuyenKhoan = "Select * from ChuyenKhoan where idGhiChep = " + ghiChep.getIdGhiChep();
                    ChuyenKhoan chuyenKhoan = (ChuyenKhoan) ghiChep;
                    Cursor cursorChuyenKhoan = db.rawQuery(SQLQueryChuyenKhoan, null);

                    if (cursorChuyenKhoan.moveToFirst()) {
                        chuyenKhoan.setIdViTienChi(cursorChuyenKhoan.getInt(1));
                        chuyenKhoan.setIdViTienThu(cursorChuyenKhoan.getInt(2));

                        dsGhiChep.add(chuyenKhoan);
                    }
                }
            }while (cursor.moveToNext());
        }
        db.close();
    }else {
        Toast.makeText(activity, "load csdl ghi chép không thành công", Toast.LENGTH_SHORT).show();
    }

        Collections.sort(dsGhiChep, new GhiChep());

        return dsGhiChep;
    }

    public static ArrayList<GhiChep> getGhiChepViTien(Activity activity, String idViTien){
        ArrayList<GhiChep> dsGhiChep = new ArrayList<>();
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(activity);
        String SQLQueryGhiChep = "Select * from GhiChep where username = '" + Layout_TrangChu.taiKhoanDangNhap.getUsername()+"'";

        if (db != null){
            Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

            if (cursor.moveToFirst()){
                do {
                    GhiChep ghiChep = null;
                    String loaiGhiChep = cursor.getString(6);
                    if (loaiGhiChep.equals("chitien")) {
                        ghiChep = new ChiTien();
                    }else if (loaiGhiChep.equals("thutien")){
                        ghiChep = new ThuTien();
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        ghiChep = new ChuyenKhoan();
                    }else {

                    }

                    ghiChep.setIdGhiChep(cursor.getInt(0));
                    ghiChep.setSoTien(cursor.getInt(1));
                    ghiChep.setDienGiai(cursor.getString(2));
                    ghiChep.setUsername(cursor.getString(3));
                    ghiChep.setNgay(cursor.getString(4));
//                    ghiChep.setDiaDiem(cursor.getString(5));
                    ghiChep.setIdHangMuc(cursor.getInt(5));
                    ghiChep.setLoaiGhiChep(loaiGhiChep);

                    if (loaiGhiChep.equals("chitien")){
                        String SQLQueryChiTien = "Select * from ChiTien where idGhiChep="+ghiChep.getIdGhiChep()+" and idViTienChi="+idViTien;
                        ChiTien chiTien;
                        chiTien = (ChiTien) ghiChep;

                        Cursor cursorChiTien = db.rawQuery(SQLQueryChiTien, null);
                        if (cursorChiTien.moveToFirst()) {
                            chiTien.setChiChoAi(cursorChiTien.getString(0));
                            chiTien.setIdViTienChi(cursorChiTien.getInt(2));

                            dsGhiChep.add(chiTien);
                        }
                    }else if (loaiGhiChep.equals("thutien")){
                        String SQLQueryThuTien = "Select * from ThuTien where idGhiChep="+ghiChep.getIdGhiChep()+" and idViTienThu="+idViTien;
                        Cursor cursorThuTien = db.rawQuery(SQLQueryThuTien, null);
                        ThuTien thuTien = (ThuTien) ghiChep;

                        if (cursorThuTien.moveToFirst()) {
                            thuTien.setThuTuAi(cursorThuTien.getString(1));
                            thuTien.setIdViTienThu(cursorThuTien.getInt(3));

                            dsGhiChep.add(thuTien);
                        }
                    }else if (loaiGhiChep.equals("chuyenkhoan")){
                        String SQLQueryChuyenKhoan = "Select * from ChuyenKhoan where idGhiChep="+ghiChep.getIdGhiChep()+" and idViTienChi="+idViTien;
                        ChuyenKhoan chuyenKhoan = (ChuyenKhoan) ghiChep;
                        Cursor cursorChuyenKhoan = db.rawQuery(SQLQueryChuyenKhoan, null);

                        if (cursorChuyenKhoan.moveToFirst()) {
                            chuyenKhoan.setIdViTienChi(cursorChuyenKhoan.getInt(1));
                            chuyenKhoan.setIdViTienThu(cursorChuyenKhoan.getInt(2));

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
}
