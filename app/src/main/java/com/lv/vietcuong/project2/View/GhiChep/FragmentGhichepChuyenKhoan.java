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
        getDefaultInfor();
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
                showDialogTuTaikhoan();
                break;
            case R.id.btn_vaotaikhoan_3:
                showDialogVaoTaikhoan();
                break;
            case R.id.btn_ngay_3:
                showDatePickerDialog();
                break;
            case R.id.btn_ghi_3:
                saveData();
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

    public void showDialogTuTaikhoan(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Từ Tài khoản");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
        setListViewTuTaikhoan(dialog);

        dialog.show();
    }

    public void showDialogVaoTaikhoan(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Vào Tài khoản");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
        setListViewVaoTaikhoan(dialog);

        dialog.show();
    }

    public void setListViewTuTaikhoan(final Dialog dialog){
        ListView lv;
        final String[] taikhoan = {"0506515615","65105320840","560184065123","60840615316","41605131566","6040686161"};

        lv = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,taikhoan);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvTuTaiKhoan.setText("");
                dialog.dismiss();
            }
        });
    }

    public void setListViewVaoTaikhoan(final Dialog dialog){
        ListView lv;
        final String[] taikhoan = {"0506515615","65105320840","560184065123","60840615316","41605131566","6040686161"};

        lv = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,taikhoan);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvVaoTaiKhoan.setText("");
                dialog.dismiss();
            }
        });
    }

    public void saveData(){

    }
}