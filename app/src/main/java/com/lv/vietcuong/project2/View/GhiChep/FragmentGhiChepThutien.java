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

public class FragmentGhiChepThutien extends android.support.v4.app.Fragment implements View.OnClickListener{
    private Button btnMucThu, btnVaoTaiKhoan, btnNgay, btnGhi;
    private EditText edtSoTien, edtDienGiai, edtThuTuAi, edtSuKien;
    private TextView tvNgay, tvMucThu, tvVaoTaiKhoan;
    private Calendar cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_thutien,container,false);

        initWidget(view);
        Util.getDefaultInfor(cal,tvNgay);
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnMucThu = (Button) v.findViewById(R.id.btn_mucthu);
        btnVaoTaiKhoan = (Button) v.findViewById(R.id.btn_vaotaikhoan);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_2);
        btnGhi = (Button) v.findViewById(R.id.btn_ghi_2);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_2);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_2);
        edtThuTuAi = (EditText) v.findViewById(R.id.edt_thu_tu_ai);
        edtSuKien = (EditText) v.findViewById(R.id.edt_sukien_2);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_2);
        tvMucThu = (TextView) v.findViewById(R.id.tv_mucthu);
        tvVaoTaiKhoan = (TextView) v.findViewById(R.id.tv_vaotaikhoan);
    }

    public void setEventClickViews(){
        btnMucThu.setOnClickListener(this);
        btnVaoTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnGhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mucthu:
                Util.showDialogMucthu(getContext(),tvMucThu,R.id.lv_mucthu);
                break;
            case R.id.btn_vaotaikhoan:
                Util.showDialogTaikhoan(getContext(),tvVaoTaiKhoan,R.id.lv_taikhoan);
                break;
            case R.id.btn_ngay_2:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_ghi_2:
                saveData();
                break;
        }
    }

    public void saveData(){

    }
}
