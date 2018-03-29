package com.lv.vietcuong.project2.View.Fragment_Login_Logout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DB_Manager;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.Account;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

/**
 * Created by Administor on 3/22/2018.
 */

public class Fragment_DangNhap extends Fragment implements View.OnClickListener{
    Button btnDangNhap;
    EditText edtTaiKhoan, edtMatKhau;
    ArrayList<Account> dsAccount;
    DB_Manager db_manager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        edtTaiKhoan = view.findViewById(R.id.edTaiKhoan);
        edtMatKhau = view.findViewById(R.id.edMatKhau);

        btnDangNhap.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        dsAccount = db_manager.getAllAcount(getActivity());

        String username = edtTaiKhoan.getText().toString();
        String password = edtMatKhau.getText().toString();

        boolean check = false;
        if(!username.equals("") && !password.equals("")){
            for(Account account:dsAccount){
                if (username.equals(account.getUsername()) && password.equals(account.getPassword())){
                    check = true;

                    Intent intent = new Intent(getActivity(), Layout_TrangChu.class);
                    intent.putExtra("account",account);
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
