package com.lv.vietcuong.project2.View.ViTien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/25/2018.
 */

public class TaiKhoanActivity extends Fragment implements View.OnClickListener {

    Button btnThemTaiKhoan;
    RadioGroup rdGroup;
    RadioButton rdbTaiKhoan, rdbSoTietKiem;
    FragmentManager manager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_taikhoan, container, false);

        btnThemTaiKhoan = view.findViewById(R.id.btnThemTaiKhoan);
        rdGroup = view.findViewById(R.id.radioGroup);
        rdbTaiKhoan = view.findViewById(R.id.rdbDanhSachTaiKhoan);
        rdbSoTietKiem = view.findViewById(R.id.rdbDanhSachSoTietKiem);

        btnThemTaiKhoan.setOnClickListener(this);
        setConTentDefault();

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rdbDanhSachTaiKhoan:
                        FragmentTransaction transaction = manager.beginTransaction();
                        Fragment_DanhSachTaiKhoan fragment_dsTaiKhoan = new Fragment_DanhSachTaiKhoan();
                        transaction.replace(R.id.content_layout_taikhoan, fragment_dsTaiKhoan);
                        transaction.commit();

                        Toast.makeText(getActivity(), "Danh sách tài khoản", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rdbDanhSachSoTietKiem:
                        FragmentTransaction transaction2 = manager.beginTransaction();
                        Fragment_DanhSachSoTietKiem fragment_dsSoTK = new Fragment_DanhSachSoTietKiem();
                        transaction2.replace(R.id.content_layout_taikhoan, fragment_dsSoTK);
                        transaction2.commit();
                        Toast.makeText(getActivity(), "Danh sách sổ tiết kiệm", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;
    }

    public void setConTentDefault(){
        manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment_DanhSachTaiKhoan fragment_dsTaiKhoan = new Fragment_DanhSachTaiKhoan();
        transaction.replace(R.id.content_layout_taikhoan, fragment_dsTaiKhoan);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = new Fragment_ThemTaiKhoan();
        transaction.replace(R.id.content_layout, fragment);
        transaction.addToBackStack("Back");
        transaction.commit();
    }
}
