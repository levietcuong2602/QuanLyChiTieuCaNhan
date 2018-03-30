package com.lv.vietcuong.project2.View.GhiChep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.util.Calendar;

/**
 * Created by admin on 3/24/2018.
 */

public class FragmentGhichepChitien extends android.support.v4.app.Fragment implements View.OnClickListener{
    private Button btnMucChi, btnTuTaiKhoan, btnNgay, btnGhi;
    private EditText edtSoTien, edtDienGiai, edtChiChoAi, edtSuKien;
    private TextView tvNgay, tvMucChi, tvTuTuKhoan;
    private Calendar cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_chitien,container,false);

        initWidget(view);
        Util.getDefaultInfor(cal,tvNgay);
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnMucChi = (Button) v.findViewById(R.id.btn_mucchi);
        btnTuTaiKhoan = (Button) v.findViewById(R.id.btn_tutaikhoan);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay);
        btnGhi = (Button) v.findViewById(R.id.btn_ghi);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai);
        edtChiChoAi = (EditText) v.findViewById(R.id.edt_chi_cho_ai);
        edtSuKien = (EditText) v.findViewById(R.id.edt_sukien);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay);
        tvMucChi = (TextView) v.findViewById(R.id.tv_mucchi);
        tvTuTuKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan);
    }

    public void setEventClickViews(){
        btnMucChi.setOnClickListener(this);
        btnTuTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnGhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mucchi:
                Util.showDialogMucchi(getContext(),tvMucChi,R.id.lv_mucchi);
                break;
            case R.id.btn_tutaikhoan:
                Util.showDialogTaikhoan(getContext(),tvTuTuKhoan,R.id.lv_mucchi);
                break;
            case R.id.btn_ngay:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_ghi:
                saveData();
                break;
        }
    }

    public void saveData(){

    }

}
