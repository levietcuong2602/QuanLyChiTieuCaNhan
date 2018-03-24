package com.lv.vietcuong.project2.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lv.vietcuong.project2.View.DangNhap.Fragment.Fragment_DangKy;
import com.lv.vietcuong.project2.View.DangNhap.Fragment.Fragment_DangNhap;

/**
 * Created by Administor on 3/22/2018.
 */

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter{
    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: Fragment frgDangNhap = new Fragment_DangNhap(); return frgDangNhap;
            case 1: Fragment frgDangKy = new Fragment_DangKy(); return frgDangKy;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Đăng nhập";
            case 1: return "Đăng Ký";
        }
        return null;
    }
}
