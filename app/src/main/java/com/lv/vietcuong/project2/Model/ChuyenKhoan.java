package com.lv.vietcuong.project2.Model;

import java.util.Date;

public class ChuyenKhoan extends GhiChep {
    private int idViTienChi;
    private int idViTienThu;

    public ChuyenKhoan() {
    }

    public int getIdViTienChi() {
        return idViTienChi;
    }

    public void setIdViTienChi(int idViTienChi) {
        this.idViTienChi = idViTienChi;
    }

    public int getIdViTienThu() {
        return idViTienThu;
    }

    public void setIdViTienThu(int idViTienThu) {
        this.idViTienThu = idViTienThu;
    }
}
