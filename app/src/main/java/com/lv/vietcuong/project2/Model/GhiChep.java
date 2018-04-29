package com.lv.vietcuong.project2.Model;

import java.util.Comparator;

public class GhiChep implements Comparator<GhiChep>{
    private int idGhiChep;
    private int soTien;
    private String dienGiai;
    private String username;
    private String ngay;
    private int idHangMuc;
    private String loaiGhiChep;

    public GhiChep() {
    }

    public int getIdGhiChep() {
        return idGhiChep;
    }

    public void setIdGhiChep(int idGhiChep) {
        this.idGhiChep = idGhiChep;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getIdHangMuc() {
        return idHangMuc;
    }

    public void setIdHangMuc(int idHangMuc) {
        this.idHangMuc = idHangMuc;
    }

    public String getLoaiGhiChep() {
        return loaiGhiChep;
    }

    public void setLoaiGhiChep(String loaiGhichep) {
        this.loaiGhiChep = loaiGhichep;
    }

    @Override
    public int compare(GhiChep g1, GhiChep g2) {
        if (Integer.parseInt(g1.ngay.substring(6)) > Integer.parseInt(g2.ngay.substring(6))){
            return -1;
        }else if (Integer.parseInt(g1.ngay.substring(6)) < Integer.parseInt(g2.ngay.substring(6))){
            return 1;
        }else {
            if (Integer.parseInt(g1.ngay.substring(3,5)) > Integer.parseInt(g2.ngay.substring(3,5))) {
                return -1;
            } else if (Integer.parseInt(g1.ngay.substring(3,5)) < Integer.parseInt(g2.ngay.substring(3,5))) {
                return 1;
            } else {
                return g2.ngay.compareTo(g1.ngay);
            }
        }
    }
}
