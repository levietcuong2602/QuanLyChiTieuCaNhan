package com.lv.vietcuong.project2.View.DangNhap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import com.lv.vietcuong.project2.Adapter.ViewPagerAdapterDangNhap;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/22/2018.
 */

public class DangNhapActivity extends AppCompatActivity {


    TabLayout tabDangNhap;
    ViewPager viewPagerDangNhap;
    Toolbar toolbar;

    public static final String SERVER ="http://192.168.1.76/quanlychitieu/";
    public static final String SERVER_TAIKHOAN = SERVER+"taikhoan.php";
    public static final String SERVER_VITIEN = SERVER +"vitien.php";
    public static final String SERVER_HANMUCCHI = SERVER +"hanmucchi.php";
    public static final String SERVER_GHICHEP = SERVER +"ghichep.php";

    boolean result = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        viewPagerDangNhap = findViewById(R.id.viewPagerDangNhap);
        tabDangNhap = findViewById(R.id.tabDangNhap);
        toolbar = findViewById(R.id.toolBarDangNhap);

        setSupportActionBar(toolbar);

        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPagerDangNhap.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();
        tabDangNhap.setupWithViewPager(viewPagerDangNhap);

    }



}
