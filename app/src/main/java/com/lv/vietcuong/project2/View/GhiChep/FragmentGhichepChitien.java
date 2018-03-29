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
        getDefaultInfor();
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
        tvMucChi = (TextView) v.findViewById(R.id.tv_muchi);
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
                showDialogMucchi();
                break;
            case R.id.btn_tutaikhoan:
                showDialogTaikhoan();
                break;
            case R.id.btn_ngay:
                showDatePickerDialog();
                break;
            case R.id.btn_ghi:

                break;
        }
    }

    public void getDefaultInfor() {
        //lấy ngày hiện tại của hệ thống
        cal = Calendar.getInstance();
        SimpleDateFormat dft = null;
        //Định dạng ngày / tháng /năm
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        //hiển thị lên giao diện
        tvNgay.setText(strDate);
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                tvNgay.setText((dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                // cal.set(year, monthOfYear, dayOfMonth);
                // dateFinish = cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = tvNgay.getText()+"";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1])-1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(getActivity(), callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày chi tiêu");
        pic.show();
    }

    public void showDialogMucchi(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Mục chi");
        dialog.setContentView(R.layout.dialog_mucchi);
        dialog.setCancelable(false);
        setListViewMucChi(dialog);

        dialog.show();
        }

    public void showDialogTaikhoan(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Tài khoản");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
        setListViewTaikhoan(dialog);

        dialog.show();
    }

    public void setListViewMucChi(final Dialog dialog){
        ListView lv;
        String[] mucchi = {"0506515615","65105320840","560184065123","60840615316","41605131566","6040686161"};

        lv = (ListView) dialog.findViewById(R.id.lv_mucchi);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,mucchi);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvMucChi.setText("");
                dialog.dismiss();
            }
        });
    }

    public void setListViewTaikhoan(final Dialog dialog){
        ListView lv;
        final String[] taikhoan = {"0506515615","65105320840","560184065123","60840615316","41605131566","6040686161"};

        lv = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,taikhoan);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvTuTuKhoan.setText("");
                dialog.dismiss();
            }
        });
    }

    public void saveData(){

    }

}
