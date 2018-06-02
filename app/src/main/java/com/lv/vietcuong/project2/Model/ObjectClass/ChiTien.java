package com.lv.vietcuong.project2.Model.ObjectClass;

public class ChiTien extends GhiChep {
    private String chiChoAi;
    private int idViTienChi;
    private String dienGiai;

    public String getChiChoAi() {
        return chiChoAi;
    }

    public void setChiChoAi(String chiChoAi) {
        this.chiChoAi = chiChoAi;
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
