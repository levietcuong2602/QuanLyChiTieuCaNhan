package com.lv.vietcuong.project2.View.DangNhap;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.ViewPagerAdapterDangNhap;
import com.lv.vietcuong.project2.Databases.DB_Manager;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/22/2018.
 */

public class DangNhapActivity extends AppCompatActivity {

    TabLayout tabDangNhap;
    ViewPager viewPagerDangNhap;
    Toolbar toolbar;

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
