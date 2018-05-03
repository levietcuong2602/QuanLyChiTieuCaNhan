package com.lv.vietcuong.project2.View.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.SQLTaiKhoan;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.Model.TaiKhoan.ModelTaiKhoan;
import com.lv.vietcuong.project2.Presenter.TaiKhoan.PresenterTaiKhoan;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/24/2018.
 */

public class Fragment_DoiMatKhau extends Fragment implements View.OnClickListener, ViewTaiKhoan, View.OnFocusChangeListener {
    EditText edOldPass, edNewPass, edReNewPass;
    Button btnSavePass;
    TextInputLayout input_matkhaucu;
    TextInputLayout input_matkhaumoi;
    TextInputLayout input_nhaplaimkmoi;

    PresenterTaiKhoan presenterTaiKhoan;
    ModelTaiKhoan modelTaiKhoan;
    boolean kiemtra = false;
    TaiKhoan taiKhoan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_doimatkhau, container, false);
        initView(view);

        presenterTaiKhoan = new PresenterTaiKhoan(this, getContext());
        modelTaiKhoan = new ModelTaiKhoan();
        taiKhoan = modelTaiKhoan.getCacheTaiKhoan(getContext());

        return view;
    }

    public void initView(View view){
        edOldPass = view.findViewById(R.id.edOldPass);
        edNewPass = view.findViewById(R.id.edNewPass);
        edReNewPass = view.findViewById(R.id.edReNewPass);
        btnSavePass = view.findViewById(R.id.btnSaveMatKhau);

        input_matkhaucu = view.findViewById(R.id.input_matkhaucu);
        input_matkhaumoi = view.findViewById(R.id.input_matkhaumoi);
        input_nhaplaimkmoi = view.findViewById(R.id.input_nhaplaimkmoi);

        btnSavePass.setOnClickListener(this);

        edNewPass.setOnFocusChangeListener(this);
        edOldPass.setOnFocusChangeListener(this);
        edReNewPass.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnSaveMatKhau:
                if(kiemtra) {
                    String matkhaumoi = edNewPass.getText().toString();
//                    long result = SQLTaiKhoan.updatedAccount(getActivity(), taiKhoan);
//                    if (result>0) {
                    presenterTaiKhoan.ThucHienCapNhap(taiKhoan.getUsername(), matkhaumoi);
//                    }else {
//                        Toast.makeText(getContext(), "đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
//                    }
                }
                break;
        }
    }

    @Override
    public void CapNhatThanhCong() {
        Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CapNhatThatBai() {
        Toast.makeText(getContext(), "Cập nhật mật khẩu không thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFocusChange(View v, boolean b) {
        int id = v.getId();

        switch (id){
            case R.id.edNewPass:
                if (!b){
                    String chuoi = ((EditText)v).getText().toString();

                    if (chuoi.equals("") || chuoi == null){
                        input_matkhaumoi.setErrorEnabled(true);
                        input_matkhaumoi.setError("bạn chưa nhập mục này");
                        kiemtra = false;
                    }else {
                        input_matkhaumoi.setErrorEnabled(false);
                        input_matkhaumoi.setError("");
                        kiemtra = true;
                    }
                }
                break;
            case R.id.edOldPass:
                if (!b){
                    String chuoi = ((EditText)v).getText().toString();
                    String matkhau = taiKhoan.getPassword();
                    if (chuoi.equals("") || chuoi == null){
                        input_matkhaucu.setErrorEnabled(true);
                        input_matkhaucu.setError("bạn chưa nhập mục này");
                        kiemtra = false;
                    }else if (!chuoi.equals(matkhau)){
                        input_matkhaucu.setErrorEnabled(true);
                        input_matkhaucu.setError("mật khẩu không đúng");
                        kiemtra = false;
                    }else {
                        input_matkhaucu.setErrorEnabled(false);
                        input_matkhaucu.setError("");
                        kiemtra = true;
                    }
                }
                break;
            case R.id.edReNewPass:
                if (!b){
                    String chuoi = ((EditText)v).getText().toString();
                    String matkhau = edNewPass.getText().toString();
                    if (chuoi.equals("") || chuoi == null){
                        input_nhaplaimkmoi.setErrorEnabled(true);
                        input_nhaplaimkmoi.setError("bạn chưa nhập mục này");
                        kiemtra = false;
                    }else if (!chuoi.equals(matkhau)){
                        input_nhaplaimkmoi.setErrorEnabled(true);
                        input_nhaplaimkmoi.setError("mật khẩu không trùng khớp");
                        kiemtra = false;
                    }else {
                        input_nhaplaimkmoi.setErrorEnabled(false);
                        input_nhaplaimkmoi.setError("");
                        kiemtra = true;
                    }
                }
                break;
        }
    }
}
