package com.lv.vietcuong.project2.Model.ObjectClass;

public class HangMuc {
    private int idHangMuc;
    private String tenHangMuc;
    private String dienDai;
    private String loaiHangMuc;
    private int idHinhAnh;
    private int idHangMucCha;

    public int getIdHinhAnh() {
        return idHinhAnh;
    }

    public void setIdHinhAnh(int idHinhAnh) {
        this.idHinhAnh = idHinhAnh;
    }

    public int getIdHangMucCha() {
        return idHangMucCha;
    }

    public void setIdHangMucCha(int idHangMucCha) {
        this.idHangMucCha = idHangMucCha;
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


}
