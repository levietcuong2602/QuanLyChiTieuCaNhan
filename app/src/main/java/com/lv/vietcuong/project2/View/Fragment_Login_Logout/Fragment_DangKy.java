package com.lv.vietcuong.project2.View.Fragment_Login_Logout;

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
import com.lv.vietcuong.project2.Model.TaiKhoan;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/22/2018.
 */

public class Fragment_DangKy extends Fragment implements View.OnClickListener {
    Button btnDangKy;
    EditText edUsername, edPassWord, edRePassword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);

        btnDangKy = view.findViewById(R.id.btnDangKy);
        edPassWord = view.findViewById(R.id.edPassword);
        edRePassword = view.findViewById(R.id.edRePassword);
        edUsername = view.findViewById(R.id.edUsername);

        btnDangKy.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String user = edUsername.getText().toString();
        String pass = edPassWord.getText().toString();
        String rePass = edRePassword.getText().toString();

        TaiKhoan tk = new TaiKhoan();
        tk.setUsername(user);
        tk.setPassword(pass);

        if(checkTaiKhoan(tk)){
            if(rePass.equals(pass)){
                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setUsername(user);
                taiKhoan.setPassword(pass);
                taiKhoan.setLoaiTaiKhoan("member");

                long result = SQLTaiKhoan.addTaiKhoan(getActivity(), taiKhoan);
                if (result > 0){
                    setEmptyEditText();
                    Toast.makeText(getActivity(), "Đăng kí tài khoản thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setEmptyEditText(){
        edUsername.setText("");
        edRePassword.setText("");
        edPassWord.setText("");
    }

    public boolean checkTaiKhoan(TaiKhoan taiKhoan){
        if(taiKhoan.getUsername().equals("") || taiKhoan.getPassword().equals("")){
            Toast.makeText(getActivity(), "Bạn cần nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!(taiKhoan.getPassword().length() > 4) || !(taiKhoan.getUsername().length() >= 8) || taiKhoan.getUsername().indexOf(" ") > 0){
            Toast.makeText(getActivity(), "tk không được chứa khoảng trắng, lớn 4 kí tự, mật khẩu lớn hơn 4 kí tự", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}
