package com.lv.vietcuong.project2.View.BaoCao.adapter;

import java.util.Comparator;

public class ThuchiNam {
    String nam;
    int thu;
    int chi;

    public ThuchiNam() {
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public int getChi() {
        return chi;
    }

    public void setChi(int chi) {
        this.chi = chi;
    }

    public static class NamComparator implements Comparator<ThuchiNam> {
        public int compare(ThuchiNam t1, ThuchiNam t2) {
            return t2.nam.compareTo(t1.nam);
        }
    }
}
