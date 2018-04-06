package com.lv.vietcuong.project2.Model;

/**
 * Created by Administor on 3/26/2018.
 */

public class ViTien {
    private int idViTien;
    private String nameWallet;
    private String loaiVi;
    private int balance;
    private String ghiChu;
    private String username;

    public ViTien() {
    }

    public String getNameWallet() {
        return nameWallet;
    }

    public void setNameWallet(String nameWallet) {
        this.nameWallet = nameWallet;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getLoaiVi() {
        return loaiVi;
    }

    public void setLoaiVi(String loaiVi) {
        this.loaiVi = loaiVi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdViTien() {
        return idViTien;
    }

    public void setIdViTien(int idViTien) {
        this.idViTien = idViTien;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
