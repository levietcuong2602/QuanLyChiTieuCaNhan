package com.lv.vietcuong.project2.View.BaoCao.adapter;

import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;

import java.io.Serializable;

public class ThanhVien extends TaiKhoan implements Serializable{
    int thuHomnay, chiHomnay, thuThangnay, chiThangnay, thuNamnay, chiNamnay;

    public int getThuHomnay() {
        return thuHomnay;
    }

    public void setThuHomnay(int thuHomnay) {
        this.thuHomnay = thuHomnay;
    }

    public int getChiHomnay() {
        return chiHomnay;
    }

    public void setChiHomnay(int chiHomnay) {
        this.chiHomnay = chiHomnay;
    }

    public int getThuThangnay() {
        return thuThangnay;
    }

    public void setThuThangnay(int thuThangnay) {
        this.thuThangnay = thuThangnay;
    }

    public int getChiThangnay() {
        return chiThangnay;
    }

    public void setChiThangnay(int chiThangnay) {
        this.chiThangnay = chiThangnay;
    }

    public int getThuNamnay() {
        return thuNamnay;
    }

    public void setThuNamnay(int thuNamnay) {
        this.thuNamnay = thuNamnay;
    }

    public int getChiNamnay() {
        return chiNamnay;
    }

    public void setChiNamnay(int chiNamnay) {
        this.chiNamnay = chiNamnay;
    }
}
