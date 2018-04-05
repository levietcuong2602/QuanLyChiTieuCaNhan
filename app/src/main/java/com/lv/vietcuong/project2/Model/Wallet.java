package com.lv.vietcuong.project2.Model;

/**
 * Created by Administor on 3/26/2018.
 */

public class Wallet {
    private String nameWallet;
    private String loaiVi;
    private double balance;
    private String ghiChu;
    private String username;

    public Wallet(String nameWallet, double balance) {
        this.nameWallet = nameWallet;
        this.balance = balance;
    }

    public Wallet() {
    }

    public String getNameWallet() {
        return nameWallet;
    }

    public void setNameWallet(String nameWallet) {
        this.nameWallet = nameWallet;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
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

    @Override
    public String toString() {
        return super.toString();
    }
}
