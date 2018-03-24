package com.lv.vietcuong.project2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

public class Layout_TrangChu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        toolbar = findViewById(R.id.toolBarTrangChu);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        View headerLayout = navigationView.inflateHeaderView(R.layout.layout_header_profile);
        textName = headerLayout.findViewById(R.id.tvName);
        textName.setText("haha");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(Layout_TrangChu.this, drawerLayout, R.string.open, R.string.close);
        drawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemDoiTaiKhoan:
                break;
            case R.id.itemDoiMatKhau:
                break;
            case R.id.itemDoiAvata:
                break;
            case R.id.itemDangXuat:
                Intent intent = new Intent(Layout_TrangChu.this, DangNhapActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}
