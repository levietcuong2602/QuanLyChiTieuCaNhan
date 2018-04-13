package com.lv.vietcuong.project2.Model;

public class HangMuc {
    private int idHangMuc;
    private String tenHangMuc;
    private String dienDai;
    private String loaiHangMuc;
    private int icon;

    public HangMuc() {
    }

    public HangMuc(int idHangMuc, String tenHangMuc, String dienDai, String loaiHangMuc, int icon) {
        this.idHangMuc = idHangMuc;
        this.tenHangMuc = tenHangMuc;
        this.dienDai = dienDai;
        this.loaiHangMuc = loaiHangMuc;
        this.icon = icon;
    }

    public int getIdHangMuc() {
        return idHangMuc;
    }

    public void setIdHangMuc(int idHangMuc) {
        this.idHangMuc = idHangMuc;
    }

    public String getTenHangMuc() {
        return tenHangMuc;
    }

    public void setTenHangMuc(String tenHangMuc) {
        this.tenHangMuc = tenHangMuc;
    }

    public String getDienDai() {
        return dienDai;
    }

    public void setDienDai(String dienDai) {
        this.dienDai = dienDai;
    }

    public String getLoaiHangMuc() {
        return loaiHangMuc;
    }

    public void setLoaiHangMuc(String loaiHangMuc) {
        this.loaiHangMuc = loaiHangMuc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
