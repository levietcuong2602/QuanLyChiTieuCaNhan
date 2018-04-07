package com.lv.vietcuong.project2.Model;

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
