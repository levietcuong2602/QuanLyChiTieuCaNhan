package com.lv.vietcuong.project2.Model.ObjectClass;

public class HinhAnh {
    private int idHinhAnh;
    private byte[] hinhAnh;
    private String loaiHinhAnh;

    public String getLoaiHinhAnh() {
        return loaiHinhAnh;
    }

    public void setLoaiHinhAnh(String loaiHinhAnh) {
        this.loaiHinhAnh = loaiHinhAnh;
    }

    public int getIdHinhAnh() {
        return idHinhAnh;
    }

    public void setIdHinhAnh(int idHinhAnh) {
        this.idHinhAnh = idHinhAnh;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
