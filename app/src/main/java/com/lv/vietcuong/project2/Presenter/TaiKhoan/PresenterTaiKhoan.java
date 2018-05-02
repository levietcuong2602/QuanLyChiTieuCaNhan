package com.lv.vietcuong.project2.Presenter.TaiKhoan;

import android.content.Context;

import com.lv.vietcuong.project2.Model.TaiKhoan.ModelTaiKhoan;
import com.lv.vietcuong.project2.View.Profile.ViewTaiKhoan;

public class PresenterTaiKhoan implements IPresenterTaiKhoan {
    ViewTaiKhoan viewTaiKhoan;
    ModelTaiKhoan modelTaiKhoan;
    Context context;

    public PresenterTaiKhoan(ViewTaiKhoan viewTaiKhoan, Context context) {
        this.viewTaiKhoan = viewTaiKhoan;
        modelTaiKhoan = new ModelTaiKhoan();
        this.context = context;
    }

    @Override
    public void ThucHienCapNhap(String username, String password) {
        boolean ketqua = modelTaiKhoan.CapNhatTaiKhoan(context, username, password);

        if (ketqua){
            viewTaiKhoan.CapNhatThanhCong();
        }else {
            viewTaiKhoan.CapNhatThatBai();
        }
    }

    @Override
    public void ThuHienDoiHoTen(String username, String hoten) {
        boolean ketqua = modelTaiKhoan.CapNhatHoTen(context, username, hoten);

        if (ketqua){
            viewTaiKhoan.CapNhatThanhCong();
        }else {
            viewTaiKhoan.CapNhatThatBai();
        }
    }
}
