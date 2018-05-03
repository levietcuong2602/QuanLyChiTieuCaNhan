package com.lv.vietcuong.project2.Model.ObjectClass;

public class ChuyenKhoan extends GhiChep{
    private int idViTienThu;
    private int idViTienChi;
    private String dienGiai;
    public int getIdViTienThu() {
        return idViTienThu;
    }

    public void setIdViTienThu(int idViTienThu) {
        this.idViTienThu = idViTienThu;
    }

    public int getIdViTienChi() {
        return idViTienChi;
    }

    public void setIdViTienChi(int idViTienChi) {
        this.idViTienChi = idViTienChi;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getDienGiai() {
        return dienGiai;
    }
}
