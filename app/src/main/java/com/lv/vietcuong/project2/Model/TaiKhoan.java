package com.lv.vietcuong.project2.Model;

import java.io.Serializable;

/**
 * Created by Administor on 3/24/2018.
 */

public class Account implements Serializable{
    private int id;
    private String username;
    private String password;
    private int avata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getAvata() {
        return avata;
    }

    public void setAvata(int avata) {
        this.avata = avata;
    }

    public Account() {
    }

    public Account(int id, String username, String password, int avata) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avata = avata;
    }
}
