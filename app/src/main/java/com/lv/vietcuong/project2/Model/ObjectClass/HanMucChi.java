package com.lv.vietcuong.project2.Model.ObjectClass;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administor on 3/26/2018.
 */

public class HanMucChi {
   private int idHanMucChi;
   private String tenHanMucChi;
   private String lapLai;
   private String ngayBatDau;
   private String ngayKetThuc;
   private int soTien;
   private String username;
   private int trangthai;
   private ArrayList<ViTien> dsViTien;

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public int getIdHanMucChi() {
        return idHanMucChi;
    }

    public void setIdHanMucChi(int idHanMucChi) {
        this.idHanMucChi = idHanMucChi;
    }

    public String getTenHanMucChi() {
        return tenHanMucChi;
    }

    public void setTenHanMucChi(String tenHanMucChi) {
        this.tenHanMucChi = tenHanMucChi;
    }

    public String getLapLai() {
        return lapLai;
    }

    public void setLapLai(String lapLai) {
        this.lapLai = lapLai;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
