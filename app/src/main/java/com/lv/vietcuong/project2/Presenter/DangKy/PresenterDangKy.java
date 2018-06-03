package com.lv.vietcuong.project2.Presenter.DangKy;

<<<<<<< HEAD
=======
import android.net.ConnectivityManager;

>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
