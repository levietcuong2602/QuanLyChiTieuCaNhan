package com.lv.vietcuong.project2.View.Fragment_Login_Logout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.SQLTaiKhoan;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.TaiKhoan;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

/**
 * Created by Administor on 3/22/2018.
 */

public class Fragment_DangNhap extends Fragment implements View.OnClickListener{
    Button btnDangNhap;
    EditText edtTaiKhoan, edtMatKhau;
    ArrayList<TaiKhoan> dsTaiKhoan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
        initView(view);
        getTaiKhoan();
        return view;
    }

    private void getTaiKhoan() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        if (sharedPreferences != null){
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");
            login(username, password);
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
                login(username, password);
                saveTaiKhoan(username, password);
                break;
        }
    }

    private void saveTaiKhoan(String username, String password) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    public void login(String username, String password){
        dsTaiKhoan = SQLTaiKhoan.getAllTaiKhoan(getActivity());
        boolean check = false;
        if(!username.equals("") && !password.equals("")){
            for(TaiKhoan taiKhoan : dsTaiKhoan){

                if (username.equals(taiKhoan.getUsername()) && password.equals(taiKhoan.getPassword())){
                    check = true;

                    Intent intent = new Intent(getActivity(), Layout_TrangChu.class);
                    intent.putExtra("taiKhoan", taiKhoan);
                    startActivity(intent);

                    getActivity().finish();
                    break;
                }
            }
            if (check == false){
                Toast.makeText(getActivity(), "Tài khoản, hoặc mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Bạn cần nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
        }
    }
}
