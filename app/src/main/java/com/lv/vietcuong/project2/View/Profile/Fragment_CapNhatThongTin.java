package com.lv.vietcuong.project2.View.Profile;

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

import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.Model.TaiKhoan.ModelTaiKhoan;
import com.lv.vietcuong.project2.Presenter.TaiKhoan.IPresenterTaiKhoan;
import com.lv.vietcuong.project2.Presenter.TaiKhoan.PresenterTaiKhoan;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/25/2018.
 */

public class Fragment_CapNhatThongTin extends Fragment implements View.OnClickListener, ViewTaiKhoan {
    EditText editHoTenCu, editHoTenMoi;
    Button btnLuuHoTen;

    ModelTaiKhoan modelTaiKhoan;
    PresenterTaiKhoan presenterTaiKhoan;
    TaiKhoan taikhoan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capnhatthongtin, container, false);
        initView(view);

        modelTaiKhoan = new ModelTaiKhoan();
        presenterTaiKhoan = new PresenterTaiKhoan(Fragment_CapNhatThongTin.this, getContext());

        taikhoan = modelTaiKhoan.getCacheTaiKhoan(getContext());
        editHoTenCu.setText(taikhoan.getHoTen());

        return view;
    }

    private void initView(View view) {
        editHoTenCu = view.findViewById(R.id.editHoTenCu);
        editHoTenMoi = view.findViewById(R.id.editHoTenMoi);
        btnLuuHoTen = view.findViewById(R.id.btnLuuHoTen);

        btnLuuHoTen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnLuuHoTen:
                LuuHoTen();
                break;
        }
    }

    private void LuuHoTen() {
        String username = taikhoan.getUsername();
        String hoTenMoi = editHoTenMoi.getText().toString();
        if (!hoTenMoi.equals("")  || hoTenMoi != null) {
            presenterTaiKhoan.ThuHienDoiHoTen(username, hoTenMoi);
        }
    }

    @Override
    public void CapNhatThanhCong() {
        Toast.makeText(getContext(), "Đổi hộ tên thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CapNhatThatBai() {
        Toast.makeText(getContext(), "Đổi hộ tên không thành công", Toast.LENGTH_SHORT).show();
    }
}
