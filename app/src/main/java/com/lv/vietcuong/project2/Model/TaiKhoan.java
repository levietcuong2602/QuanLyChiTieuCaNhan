package com.lv.vietcuong.project2.Model;

import java.io.Serializable;

/**
 * Created by Administor on 3/24/2018.
 */

public class TaiKhoan implements Serializable{
    private String username;
    private String password;
    private String hoTen;
    private int idGiaDinh;
    private String loaiTaiKhoan;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getIdGiaDinh() {
        return idGiaDinh;
    }

    public void setIdGiaDinh(int idGiaDinh) {
        this.idGiaDinh = idGiaDinh;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
