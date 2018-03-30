package com.lv.vietcuong.project2.View.GhiChep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.util.Calendar;

public class FragmentEditChuyenkhoan extends Fragment implements View.OnClickListener{
    private Button btnVaoTaiKhoan, btnTuTaiKhoan, btnNgay, btnCapNhat, btnXoa;
    private EditText edtSoTien, edtDienGiai;
    private TextView tvNgay, tvVaoTaiKhoan, tvTuTuKhoan;
    private Calendar cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_chuyenkhoan_edit,container,false);

        initWidget(view);
        Util.getDefaultInfor(cal,tvNgay);
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnVaoTaiKhoan = (Button) v.findViewById(R.id.btn_vaotaikhoan_6);
        btnTuTaiKhoan = (Button) v.findViewById(R.id.btn_tutaikhoan_6);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_6);
        btnCapNhat = (Button) v.findViewById(R.id.btn_capnhat_6);
        btnXoa = (Button) v.findViewById(R.id.btn_xoa_6);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_6);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_6);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_6);
        tvVaoTaiKhoan = (TextView) v.findViewById(R.id.tv_vaotaikhoan_6);
        tvTuTuKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan_6);
    }

    private void setEventClickViews() {
        btnVaoTaiKhoan.setOnClickListener(this);
        btnTuTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnCapNhat.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_vaotaikhoan_6:
                Util.showDialogTaikhoan(getContext(),tvVaoTaiKhoan,R.id.lv_taikhoan);
                break;
            case R.id.btn_tutaikhoan_6:
                Util.showDialogTaikhoan(getContext(),tvTuTuKhoan,R.id.lv_taikhoan);
                break;
            case R.id.btn_ngay_6:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_capnhat_6:

                break;
            case R.id.btn_xoa_6:

                break;
        }
    }
}
