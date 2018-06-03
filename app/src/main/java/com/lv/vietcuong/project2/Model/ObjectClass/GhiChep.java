package com.lv.vietcuong.project2.Model.ObjectClass;

public class GhiChep {
    protected int idGhiChep;
    protected int soTien;
    protected String dienDai;
    protected String username;
    protected String ngay;
    protected String diaDiem;
    protected int idHangMuc;
    protected String loaiGhiChep;
    protected int trangthai;

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getLoaiGhiChep() {
        return loaiGhiChep;
    }

    public void setLoaiGhiChep(String loaiGhiChep) {
        this.loaiGhiChep = loaiGhiChep;
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

    public String getDienDai() {
        return dienDai;
    }

    public void setDienDai(String dienDai) {
        this.dienDai = dienDai;
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

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public int getIdHangMuc() {
        return idHangMuc;
    }

    public void setIdHangMuc(int idHangMuc) {
        this.idHangMuc = idHangMuc;
    }
}
