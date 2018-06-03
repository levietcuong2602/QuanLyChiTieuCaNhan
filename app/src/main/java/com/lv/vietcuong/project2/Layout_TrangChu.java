package com.lv.vietcuong.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.Model.TaiKhoan.ModelTaiKhoan;
import com.lv.vietcuong.project2.View.BaoCao.FragmentBaoCao;
import com.lv.vietcuong.project2.View.GhiChep.GhiChepActivity;
import com.lv.vietcuong.project2.View.HanMucChi.Fragment_ThemHanMucChi;
import com.lv.vietcuong.project2.View.HanMucChi.HanMucChiFragment;
import com.lv.vietcuong.project2.View.HanMucChi.OnFragmentManager;
import com.lv.vietcuong.project2.View.HangMucThuChi.HangMucThuChiActivity;
import com.lv.vietcuong.project2.View.Profile.Fragment_DoiAvata;
import com.lv.vietcuong.project2.View.Profile.Fragment_DoiMatKhau;
import com.lv.vietcuong.project2.View.Profile.Fragment_CapNhatThongTin;
import com.lv.vietcuong.project2.View.ViTien.TaiKhoanActivity;
import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

public class Layout_TrangChu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static final int SYNCED_WITH_SERVER = 1;
    public static final int NOT_SYNCED_WITH_SERVER = 0;
    public static final int EDIT_STATE = 3;
    public static final int DELETE_STATE = 2;

    //    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView textName;
    FragmentManager manager;
    ModelTaiKhoan modelTaiKhoan;

    public static BottomNavigationView bottomNavigationView;

    public static TaiKhoan taiKhoanDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        initView();
        setHeaderNavigation();
        setEvent();
        //vào thẳng màn hình ghi chép
        manager = getSupportFragmentManager();
        FragmentTransaction transGhiChep = manager.beginTransaction();
        GhiChepActivity ghiChep = new GhiChepActivity();
        transGhiChep.replace(R.id.content_layout, ghiChep);
        transGhiChep.commit();
    }

    public void initView(){
        //        toolbar = findViewById(R.id.toolBarTrangChu);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        modelTaiKhoan = new ModelTaiKhoan();
    }

    public void setEvent(){
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.itemBaoCao:
                        FragmentTransaction transBaoCao = manager.beginTransaction();
                        FragmentBaoCao baoCao = new FragmentBaoCao();
                        transBaoCao.replace(R.id.content_layout, baoCao);
                        transBaoCao.commit();
                        item.setChecked(true);
                        break;
                    case R.id.itemGhiChep:
                        FragmentTransaction transGhiChep = manager.beginTransaction();
                        GhiChepActivity ghiChep = new GhiChepActivity();
                        transGhiChep.replace(R.id.content_layout, ghiChep);
                        transGhiChep.commit();

                        item.setChecked(true);

                        Toast.makeText(Layout_TrangChu.this, "Ghi chép", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.itemLimit:
                        FragmentTransaction transHanMucChi = manager.beginTransaction();
                        HanMucChiFragment hanMucChi= new HanMucChiFragment();
                        transHanMucChi.replace(R.id.content_layout, hanMucChi);
                        transHanMucChi.commit();

                        item.setChecked(true);
                        Toast.makeText(Layout_TrangChu.this, "Hạn mức chi", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itemSetting:
                        drawerLayout.openDrawer(GravityCompat.START);
                        item.setChecked(true);
                        break;
                    case R.id.itemTaiKhoan:
                        FragmentTransaction transTaiKhoan = manager.beginTransaction();
                        TaiKhoanActivity taiKhoanActivity = new TaiKhoanActivity();
                        transTaiKhoan.replace(R.id.content_layout, taiKhoanActivity);
                        transTaiKhoan.commit();

                        item.setChecked(true);
                        break;
                }
                return false;
            }
        });
    }

    private void setHeaderNavigation() {
        View headerLayout = navigationView.inflateHeaderView(R.layout.layout_header_profile);
        textName = headerLayout.findViewById(R.id.tvName);

        taiKhoanDangNhap = modelTaiKhoan.getCacheTaiKhoan(this);
        if (taiKhoanDangNhap != null) {
            textName.setText("Xin chào: " + taiKhoanDangNhap.getHoTen());
        }

        if (taiKhoanDangNhap.getLoaiTaiKhoan().equals("thanhvien")) {
            Menu menu = navigationView.getMenu();
            MenuItem itemQuanLy = menu.findItem(R.id.item_quanly);
            itemQuanLy.setVisible(false);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemCapNhatThongTin:
                FragmentTransaction transDoiTenTK = manager.beginTransaction();

                Fragment_CapNhatThongTin fragmentDoiTenTK = new Fragment_CapNhatThongTin();
                transDoiTenTK.replace(R.id.content_layout, fragmentDoiTenTK);
                transDoiTenTK.commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.itemDoiMatKhau:
                FragmentTransaction transaction = manager.beginTransaction();

                Fragment fragmentDoiMK = new Fragment_DoiMatKhau();
                transaction.replace(R.id.content_layout, fragmentDoiMK);
                transaction.commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.itemDoiAvata:
                FragmentTransaction transDoiAvata = manager.beginTransaction();

                Fragment fragmentDoiAvata = new Fragment_DoiAvata();
                transDoiAvata.replace(R.id.content_layout, fragmentDoiAvata);
                transDoiAvata.commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.itemDangXuat:
                Intent intent = new Intent(Layout_TrangChu.this, DangNhapActivity.class);
                startActivity(intent);

                //update information in sharepreferences file xml
                SharedPreferences sharedPreferences = getSharedPreferences("TaiKhoan", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", "");
                editor.putString("password", "");
                editor.commit();

                break;
            case R.id.itemHangMucThuChi:
                FragmentTransaction transHangMuc = manager.beginTransaction();

                Fragment hangMuc = new HangMucThuChiActivity();
                transHangMuc.replace(R.id.content_layout, hangMuc);
                transHangMuc.addToBackStack("themhangmuc");
                transHangMuc.commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }

        return false;
    }

}