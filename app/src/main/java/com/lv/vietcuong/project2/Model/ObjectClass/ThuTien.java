package com.lv.vietcuong.project2.Model.ObjectClass;

public class ThuTien extends GhiChep{
    private String thuTuAi;
    private int idViTienThu;
    private String dienGiai;

    public void init(GhiChep ghiChep){}

    public String getThuTuAi() {
        return thuTuAi;
    }

    public void setThuTuAi(String thuTuAi) {
        this.thuTuAi = thuTuAi;
    }

    public int getIdViTienThu() {
        return idViTienThu;
    }

    public void setIdViTienThu(int idViTienThu) {
        this.idViTienThu = idViTienThu;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getDienGiai() {
        return dienGiai;
    }
}
