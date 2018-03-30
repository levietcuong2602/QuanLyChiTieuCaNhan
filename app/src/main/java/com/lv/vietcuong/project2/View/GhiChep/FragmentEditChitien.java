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

public class FragmentEditChitien extends Fragment implements View.OnClickListener{
    private Button btnMucChi, btnTuTaiKhoan, btnNgay, btnCapNhat, btnXoa;
    private EditText edtSoTien, edtDienGiai, edtChiChoAi, edtSuKien;
    private TextView tvNgay, tvMucChi, tvTuTuKhoan;
    private Calendar cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_chitien_edit,container,false);

        initWidget(view);
        Util.getDefaultInfor(cal,tvNgay);
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnMucChi = (Button) v.findViewById(R.id.btn_mucchi_4);
        btnTuTaiKhoan = (Button) v.findViewById(R.id.btn_tutaikhoan_4);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_4);
        btnCapNhat = (Button) v.findViewById(R.id.btn_capnhat_4);
        btnXoa = (Button) v.findViewById(R.id.btn_xoa_4);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_4);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_4);
        edtChiChoAi = (EditText) v.findViewById(R.id.edt_chi_cho_ai_4);
        edtSuKien = (EditText) v.findViewById(R.id.edt_sukien_4);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_4);
        tvMucChi = (TextView) v.findViewById(R.id.tv_mucchi_4);
        tvTuTuKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan_4);
    }

    private void setEventClickViews() {
        btnMucChi.setOnClickListener(this);
        btnTuTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnCapNhat.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mucchi_4:
                Util.showDialogMucchi(getContext(),tvMucChi,R.id.lv_mucchi);
                break;
            case R.id.btn_tutaikhoan_4:
                Util.showDialogTaikhoan(getContext(),tvTuTuKhoan,R.id.lv_taikhoan);
                break;
            case R.id.btn_ngay_4:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_capnhat_4:

                break;
            case R.id.btn_xoa_4:

                break;
        }
    }
}
