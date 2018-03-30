package com.lv.vietcuong.project2.View.GhiChep;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by admin on 3/24/2018.
 */

public class FragmentGhichepChuyenKhoan extends android.support.v4.app.Fragment implements View.OnClickListener{
    private Button btnTuTaiKhoan, btnVaoTaiKhoan, btnNgay, btnGhi;
    private EditText edtSoTien, edtDienGiai;
    private TextView tvNgay, tvTuTaiKhoan, tvVaoTaiKhoan;
    private Calendar cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_chuyenkhoan,container,false);

        initWidget(view);
        Util.getDefaultInfor(cal,tvNgay);
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnTuTaiKhoan= (Button) v.findViewById(R.id.btn_tutaikhoan_3);
        btnVaoTaiKhoan = (Button) v.findViewById(R.id.btn_vaotaikhoan_3);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_3);
        btnGhi = (Button) v.findViewById(R.id.btn_ghi_3);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_3);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_3);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_3);
        tvTuTaiKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan_3);
        tvVaoTaiKhoan = (TextView) v.findViewById(R.id.tv_vaotaikhoan_3);
    }

    public void setEventClickViews(){
        btnTuTaiKhoan.setOnClickListener(this);
        btnVaoTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnGhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tutaikhoan_3:
                Util.showDialogTaikhoan(getContext(),tvTuTaiKhoan,R.id.lv_taikhoan);
                break;
            case R.id.btn_vaotaikhoan_3:
                Util.showDialogTaikhoan(getContext(),tvVaoTaiKhoan,R.id.lv_taikhoan);
                break;
            case R.id.btn_ngay_3:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_ghi_3:
                saveData();
                break;
        }
    }

    public void saveData(){

    }
}