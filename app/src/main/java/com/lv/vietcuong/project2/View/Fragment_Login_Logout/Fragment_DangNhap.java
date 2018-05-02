package com.lv.vietcuong.project2.View.Fragment_Login_Logout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.SQLTaiKhoan;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.Presenter.DangNhap.PresenterDangNhap;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

/**
 * Created by Administor on 3/22/2018.
 */

public class Fragment_DangNhap extends Fragment implements View.OnClickListener, ViewDangNhap{
    Button btnDangNhap;
    EditText edtTaiKhoan, edtMatKhau;
    ArrayList<TaiKhoan> dsTaiKhoan;

    PresenterDangNhap presenterDangNhap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
        initView(view);
        getCacheDangNhap();
        presenterDangNhap = new PresenterDangNhap(getContext(), this);

        return view;
    }

    private void getCacheDangNhap() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        if (sharedPreferences != null){
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");
            if (username != null && username != "" && password != null && password != "") {
                DangNhapThanhCong();
            }
        }
    }

    public void initView(View view){
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        edtTaiKhoan = view.findViewById(R.id.edTaiKhoan);
        edtMatKhau = view.findViewById(R.id.edMatKhau);

        btnDangNhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       int id = view.getId();
       switch (id){
           case R.id.btnDangNhap:
               String username = edtTaiKhoan.getText().toString();
               String password = edtMatKhau.getText().toString();
               presenterDangNhap.ThucHienDangNhap(username, password);
               break;
       }
    }

    @Override
    public void DangNhapThanhCong() {
        Intent intent = new Intent(getActivity(), Layout_TrangChu.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void DangNhapThatBai() {
        Toast.makeText(getContext(), "Đăng nhập không thành công.", Toast.LENGTH_SHORT).show();
    }
}
