package com.lv.vietcuong.project2.Model.ObjectClass;

public class HangMuc {
    private int idHangMuc;
    private String tenHangMuc;
    private String dienDai;
    private String loaiHangMuc;
    private int idHinhAnh;
    private int idHangMucCha;

<<<<<<< HEAD
    public HangMuc(int idHangMuc, String tenHangMuc, String dienDai, String loaiHangMuc, int idHinhAnh) {
        this.idHangMuc = idHangMuc;
        this.tenHangMuc = tenHangMuc;
        this.dienDai = dienDai;
        this.loaiHangMuc = loaiHangMuc;
        this.idHinhAnh = idHinhAnh;
=======
    public HangMuc(int id, String tenHangMuc, String dienDai, String loaiHangMuc, int icon) {
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    }

    public HangMuc() {

    }

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
