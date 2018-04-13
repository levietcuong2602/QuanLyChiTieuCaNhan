package com.lv.vietcuong.project2.Model;

import java.util.Date;

public class ChiTien extends GhiChep {
    private String chiChoAi;
    private int idViTienChi;

    public ChiTien() {
    }

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
}
