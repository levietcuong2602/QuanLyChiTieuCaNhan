package com.lv.vietcuong.project2.View.BaoCao.adapter;

import java.util.Comparator;

public class ThuchiThang{
    String thang;
    int thu;
    int chi;

    public ThuchiThang() {
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
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


    public static class ThangComparator implements Comparator<ThuchiThang> {
        public int compare(ThuchiThang t1, ThuchiThang t2) {
            if (Integer.parseInt(t1.thang.substring(3)) == Integer.parseInt(t2.thang.substring(3))){
                return t2.thang.compareTo(t1.thang);
            }else if (Integer.parseInt(t1.thang.substring(3)) > Integer.parseInt(t2.thang.substring(3))){
                return -1;
            }else {
                return 1;
            }
        }
    }
}