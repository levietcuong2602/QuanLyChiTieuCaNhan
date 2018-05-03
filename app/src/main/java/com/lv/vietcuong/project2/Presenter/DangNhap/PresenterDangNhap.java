package com.lv.vietcuong.project2.Presenter.DangNhap;

import android.content.Context;

import com.lv.vietcuong.project2.Model.DangNhap_DangKy.ModelDangNhap;
import com.lv.vietcuong.project2.View.Fragment_Login_Logout.ViewDangNhap;

public class PresenterDangNhap implements IPresenterDangNhap {
    ModelDangNhap modelDangNhap;
    ViewDangNhap viewDangNhap;
    Context context;
    public PresenterDangNhap(Context context, ViewDangNhap viewDangNhap) {
        this.context = context;
        this.viewDangNhap = viewDangNhap;
        modelDangNhap = new ModelDangNhap();
    }

    @Override
    public void ThucHienDangNhap(String username, String password) {
        boolean kiemtra = modelDangNhap.KiemTraDangNhap(context, username, password);
        if (kiemtra){
            viewDangNhap.DangNhapThanhCong();
        }else{
            viewDangNhap.DangNhapThatBai();
        }
    }
}
