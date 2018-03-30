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

public class FragmentEditThutien extends Fragment implements View.OnClickListener{
    private Button btnMucThu, btnVaoTaiKhoan, btnNgay, btnCapNhat, btnXoa;
    private EditText edtSoTien, edtDienGiai, edtThuTuAi, edtSuKien;
    private TextView tvNgay, tvMucThu, tvVaoTaiKhoan;
    private Calendar cal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_thutien_edit,container,false);

        initWidget(view);
        Util.getDefaultInfor(cal,tvNgay);
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnMucThu = (Button) v.findViewById(R.id.btn_mucthu_5);
        btnVaoTaiKhoan = (Button) v.findViewById(R.id.btn_vaotaikhoan_5);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_5);
        btnCapNhat = (Button) v.findViewById(R.id.btn_capnhat_5);
        btnXoa = (Button) v.findViewById(R.id.btn_xoa_5);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_5);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_5);
        edtThuTuAi = (EditText) v.findViewById(R.id.edt_thu_tu_ai_5);
        edtSuKien = (EditText) v.findViewById(R.id.edt_sukien_5);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_5);
        tvMucThu = (TextView) v.findViewById(R.id.tv_mucthu_5);
        tvVaoTaiKhoan = (TextView) v.findViewById(R.id.tv_vaotaikhoan_5);
    }

    private void setEventClickViews() {
        btnMucThu.setOnClickListener(this);
        btnVaoTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnCapNhat.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mucthu_5:
                Util.showDialogMucthu(getContext(),tvMucThu,R.id.lv_mucthu);
                break;
            case R.id.btn_vaotaikhoan_5:
                Util.showDialogTaikhoan(getContext(),tvVaoTaiKhoan,R.id.lv_taikhoan);
                break;
            case R.id.btn_ngay_5:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_capnhat_5:

                break;
            case R.id.btn_xoa_4:

                break;
        }
    }
}
