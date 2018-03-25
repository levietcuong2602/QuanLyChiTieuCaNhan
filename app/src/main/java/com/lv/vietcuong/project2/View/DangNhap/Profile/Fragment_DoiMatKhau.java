package com.lv.vietcuong.project2.View.DangNhap.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DB_Manager;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.Account;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/24/2018.
 */

public class Fragment_DoiMatKhau extends Fragment implements View.OnClickListener {
    EditText edOldPass, edNewPass, edReNewPass;
    TextView tvUserName;
    Button btnSavePass;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_doimatkhau, container, false);

        edOldPass = view.findViewById(R.id.edOldPass);
        edNewPass = view.findViewById(R.id.edNewPass);
        edReNewPass = view.findViewById(R.id.edReNewPass);
        btnSavePass = view.findViewById(R.id.btnSaveMatKhau);
        tvUserName = view.findViewById(R.id.tvUsername);

        btnSavePass.setOnClickListener(this);


        tvUserName.setText(tvUserName.getText()+Layout_TrangChu.accountDangNhap.getUsername());
        return view;
    }

    @Override
    public void onClick(View view) {
        String oldPass = edOldPass.getText().toString();
        String newPass = edNewPass.getText().toString();
        String reNewPass = edReNewPass.getText().toString();

        if (oldPass.equals("") || newPass.equals("") || reNewPass.equals("")){
            Toast.makeText(getActivity(), "Bạn cần nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
        }else {
            if(newPass.length()<=4 || !newPass.equals(reNewPass) || oldPass.equals(newPass)){
                Toast.makeText(getActivity(), "Mật khẩu mới không hợp lệ.", Toast.LENGTH_SHORT).show();
            }else {
                Account account = new Account();
                account.setUsername(Layout_TrangChu.accountDangNhap.getUsername());
                account.setPassword(newPass);

                long result = DB_Manager.updatePasswordAccount(getActivity(), account);
                if (result > 0){
                    edOldPass.setText("");
                    edNewPass.setText("");
                    edReNewPass.setText("");

                    Toast.makeText(getActivity(), "Đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}