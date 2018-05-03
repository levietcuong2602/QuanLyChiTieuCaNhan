package com.lv.vietcuong.project2.View.Fragment_Login_Logout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.SQLTaiKhoan;
import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.Presenter.DangKy.PresenterDangKy;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/22/2018.
 */

public class Fragment_DangKy extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, ViewDangKy{
    Button btnDangKy;
    EditText edTenTaiKhoan, edMatKhau, edNhapLaiMatKhau, edtHoTen;
    RadioButton rdbThanhVien, rdbChuTaiChinh;
    TextInputLayout input_taikhoan;
    TextInputLayout input_matkhau;
    TextInputLayout input_nhaplaimatkhau;
    TextInputLayout input_hoten;

    boolean kiemtrathongtin = false;
    private PresenterDangKy presenterDangKy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);

        initView(view);

        return view;
    }

    public void initView(View view) {
        btnDangKy = view.findViewById(R.id.btnDangKy);
        edMatKhau = view.findViewById(R.id.edPassword);
        edNhapLaiMatKhau = view.findViewById(R.id.edRePassword);
        edTenTaiKhoan = view.findViewById(R.id.edUsername);
        edtHoTen = view.findViewById(R.id.edHoTen);
        rdbThanhVien = view.findViewById(R.id.rdbThanhVien);
        rdbChuTaiChinh = view.findViewById(R.id.rdbChuTaiChinh);

        input_taikhoan = view.findViewById(R.id.input_tentaikhoan);
        input_matkhau = view.findViewById(R.id.input_matkhau);
        input_nhaplaimatkhau = view.findViewById(R.id.input_nhaplaimatkhau);
        input_hoten = view.findViewById(R.id.input_hoten);

        presenterDangKy = new PresenterDangKy(this);

        btnDangKy.setOnClickListener(this);
        edTenTaiKhoan.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);
        edtHoTen.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        String user = edTenTaiKhoan.getText().toString();
        String pass = edMatKhau.getText().toString();
        String hoTen = edtHoTen.getText().toString();
        String loaiTaiKhoan;

        if (rdbThanhVien.isChecked()) {
            loaiTaiKhoan = "thanhvien";
        } else {
            loaiTaiKhoan = "chugiadinh";
        }

        if (kiemtrathongtin){
            TaiKhoan tk = new TaiKhoan();
            tk.setUsername(user);
            tk.setPassword(pass);
            tk.setHoTen(hoTen);
            tk.setLoaiTaiKhoan(loaiTaiKhoan);
            tk.setIdGiaDinh(-1);

            presenterDangKy.ThuHienDangKyTaiKhoan(tk);
            setEmptyEditText();
        }
    }

    public void setEmptyEditText() {
        edTenTaiKhoan.setText("");
        edNhapLaiMatKhau.setText("");
        edMatKhau.setText("");
        edtHoTen.setText("");
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();
        switch (id) {
            case R.id.edUsername:
                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_taikhoan.setErrorEnabled(true);
                        input_taikhoan.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    } else {
                        input_taikhoan.setErrorEnabled(false);
                        input_taikhoan.setError("");
                        kiemtrathongtin = true;
                    }
                }
                ;
                break;

            case R.id.edPassword:
                if (!b) {

                    String chuoi = ((EditText) view).getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_matkhau.setErrorEnabled(true);
                        input_matkhau.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    } else {
                        input_taikhoan.setErrorEnabled(false);
                        input_taikhoan.setError("");
                        kiemtrathongtin = true;
                    }
                }
                ;
                break;

            case R.id.edRePassword:
                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    String matkhau = edMatKhau.getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_matkhau.setErrorEnabled(true);
                        input_matkhau.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    } else {
                        input_taikhoan.setErrorEnabled(false);
                        input_taikhoan.setError("");
                        kiemtrathongtin = true;
                    }

                    if (!chuoi.equals(matkhau)) {
                        input_nhaplaimatkhau.setErrorEnabled(true);
                        input_nhaplaimatkhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    } else {
                        input_nhaplaimatkhau.setErrorEnabled(false);
                        input_nhaplaimatkhau.setError("");
                        kiemtrathongtin = true;
                    }
                }
                ;
                break;
            case R.id.edHoTen:
                if (!b) {
                    String chuoi = ((EditText) view).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_hoten.setErrorEnabled(true);
                        input_hoten.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    } else {
                        input_hoten.setErrorEnabled(false);
                        input_hoten.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
        }
    }

    @Override
    public void dangKyThanhCong() {
        Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dangKyThatBai() {
        Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }
}
