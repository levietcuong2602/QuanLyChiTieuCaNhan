package com.lv.vietcuong.project2.Presenter.DangKy;

import android.net.ConnectivityManager;

import com.lv.vietcuong.project2.Model.DangNhap_DangKy.ModelDangKy;
import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.View.Fragment_Login_Logout.ViewDangKy;

public class PresenterDangKy implements IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        this.modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThuHienDangKyTaiKhoan(TaiKhoan taiKhoan) {
        boolean kiemtra = modelDangKy.DangKyTaiKhoan(taiKhoan);

        if (kiemtra){
            viewDangKy.dangKyThanhCong();
        }else {
            viewDangKy.dangKyThatBai();
        }
    }
}
