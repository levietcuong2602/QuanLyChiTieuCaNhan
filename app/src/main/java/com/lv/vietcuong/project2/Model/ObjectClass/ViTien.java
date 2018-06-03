package com.lv.vietcuong.project2.Model.ObjectClass;

import java.io.Serializable;

/**
 * Created by Administor on 3/26/2018.
 */

public class ViTien implements Serializable{
    private int idViTien;
    private String tenViTien;
    private String loaiVi;
    private int soDu;
    private String ghiChu;
    private String username;
<<<<<<< HEAD
=======
    private int trangthai;
    private int idHangMucThu;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

    public ViTien() {
    }

<<<<<<< HEAD
    public ViTien(int idViTien, String tenViTien, String loaiVi, int soDu, String ghiChu, String username) {
        this.idViTien = idViTien;
        this.tenViTien = tenViTien;
        this.loaiVi = loaiVi;
        this.soDu = soDu;
        this.ghiChu = ghiChu;
        this.username = username;
=======

    public ViTien(int id, String tenViTien, String loaiViTien, int soDu, String ghiChu, String username) {
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdHangMucThu() {
        return idHangMucThu;
    }

    public void setIdHangMucThu(int idHangMucThu) {
        this.idHangMucThu = idHangMucThu;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    }

    public String getTenViTien() {
        return tenViTien;
    }

    public void setTenViTien(String tenViTien) {
        this.tenViTien = tenViTien;
    }
    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    public String getLoaiVi() {
        return loaiVi;
    }

    public void setLoaiVi(String loaiVi) {
        this.loaiVi = loaiVi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdViTien() {
        return idViTien;
    }

    public void setIdViTien(int idViTien) {
        this.idViTien = idViTien;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
