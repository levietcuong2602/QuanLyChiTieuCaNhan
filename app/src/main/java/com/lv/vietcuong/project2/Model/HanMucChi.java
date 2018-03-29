package com.lv.vietcuong.project2.Model;

/**
 * Created by Administor on 3/26/2018.
 */

public class HanMucChi {
    private String name;
    private double limit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public HanMucChi(String name, double limit) {
        this.name = name;
        this.limit = limit;
    }

    public HanMucChi() {
    }
}
