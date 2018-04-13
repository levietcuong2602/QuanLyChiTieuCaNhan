package com.lv.vietcuong.project2.Model;

import java.util.Date;

public class ThuTien extends GhiChep {
    private String thuTuAi;
    private int idViTienThu;

    public ThuTien() {
    }

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
}
