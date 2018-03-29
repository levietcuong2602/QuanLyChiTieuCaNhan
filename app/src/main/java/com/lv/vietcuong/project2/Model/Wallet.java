package com.lv.vietcuong.project2.Model;

/**
 * Created by Administor on 3/26/2018.
 */

public class Wallet {
    private String nameWallet;
    private double balance;

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
}
