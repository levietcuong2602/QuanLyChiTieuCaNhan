package com.lv.vietcuong.project2.View.HanMucChi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
import com.lv.vietcuong.project2.Model.HanMucChi;
import com.lv.vietcuong.project2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administor on 3/26/2018.
 */

public class Fragment_ThemHanMucChi extends Fragment implements View.OnClickListener {
    Button btnSaveHanMucChi, btnCancelHangMucChi, btnLuuHanMucChi;
    EditText edtTenHanMuc, edtSoHanMuc;
    Button btnHangMucChi, btnTaiKhoan, btnLapLai, btnNgayBatDau, btnNgayKetThuc;
    static String ngayKetThuc = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themhanmucchi, container, false);
        initWidget(view);
        getDataNgayKetThuc();
        return view;
    }

    private void getDataNgayKetThuc() {
        btnNgayKetThuc.setText(ngayKetThuc);
    }

    public void initWidget(View view){
        btnSaveHanMucChi = view.findViewById(R.id.btnSaveHanMucChi);
        btnLuuHanMucChi = view.findViewById(R.id.btnLuuHanMucChi);
        btnCancelHangMucChi = view.findViewById(R.id.btnHuySaveHanMucChi);
        edtSoHanMuc = view.findViewById(R.id.edSoHanMucChi);
        edtTenHanMuc = view.findViewById(R.id.edtenHanMucChi);
        btnHangMucChi = view.findViewById(R.id.btnHangMucChi);
        btnTaiKhoan = view.findViewById(R.id.btnTaiKhoan);
        btnLapLai = view.findViewById(R.id.btnLapLai);
        btnNgayBatDau = view.findViewById(R.id.btnNgayBatDau);
        btnNgayKetThuc = view.findViewById(R.id.btnNgayKetThuc);

        btnCancelHangMucChi.setOnClickListener(this);
        btnSaveHanMucChi.setOnClickListener(this);
        btnLuuHanMucChi.setOnClickListener(this);
        btnLapLai.setOnClickListener(this);
        btnTaiKhoan.setOnClickListener(this);
        btnNgayBatDau.setOnClickListener(this);
        btnNgayKetThuc.setOnClickListener(this);
        btnHangMucChi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveHanMucChi:
            case R.id.btnLuuHanMucChi:
                saveHangMucChi();
                break;
            case R.id.btnHuySaveHanMucChi:
                cancelSaveHangMucChi();
                break;
            case R.id.btnLapLai:
                showDialogLapLai();
                break;
            case R.id.btnTaiKhoan:
                showListTaiKhoan();
                break;
            case R.id.btnNgayBatDau:
                showDatePickerNgayBatDau();
                break;
            case R.id.btnNgayKetThuc:
                showOptionNgayKetThuc();
                break;
            case R.id.btnHangMucChi:
                showHangMucChi();
                break;
        }
    }

    private void showHangMucChi() {
        //danh sách hạng mục thu/chi
    }

    private void showOptionNgayKetThuc() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment_NgayKetThuc ngayKetThuc = new Fragment_NgayKetThuc();
        transaction.replace(R.id.content_layout, ngayKetThuc);
        transaction.addToBackStack("ngayketthuc");

        transaction.commit();
    }

    private void showDatePickerNgayBatDau() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                btnNgayBatDau.setText(day + "/" + (month+1) + "/" + year);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), callback, year, month, day);
        pickerDialog.show();

    }

    private void showListTaiKhoan() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment_ChonTaiKhoan chonTaiKhoan = new Fragment_ChonTaiKhoan();
        transaction.replace(R.id.content_layout, chonTaiKhoan);
        transaction.addToBackStack("themhangmucchi");

        transaction.commit();
    }

    private void showDialogLapLai() {
        final String[]arr = {"không lặp", "hàng tháng", "hàng quý","hàng năm"};

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        
        dialog.setTitle("Lặp lại");
        dialog.setItems(arr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                btnLapLai.setText(arr[i]);
                dialogInterface.dismiss();
            }
        });

        AlertDialog aler = dialog.create();
        aler.show();
    }

    private void cancelSaveHangMucChi() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transTaiKhoan = manager.beginTransaction();
        HanMucChiActivity hanMucChiActivity = new HanMucChiActivity();
        transTaiKhoan.replace(R.id.content_layout, hanMucChiActivity);
        transTaiKhoan.commit();
    }

    private void saveHangMucChi() {
        String name = edtTenHanMuc.getText().toString();
        String soHanMuc = edtSoHanMuc.getText().toString();
        int soTienHanMuc = Integer.parseInt(edtSoHanMuc.getText().toString());
        String ngayBdau = btnNgayBatDau.getText().toString();
        String ngayKT = ngayKetThuc;
        String lapLai = btnLapLai.getText().toString();

        if (name.equals("") || soHanMuc.equals("")){
            Toast.makeText(getActivity(), "Bạn cần nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
        }else {
            if (!soHanMuc.matches("[0-9]+")){
                Toast.makeText(getActivity(), "Số hạn mức không hợp lệ", Toast.LENGTH_SHORT).show();
            }else {
                HanMucChi hanMucChi =  new HanMucChi();

                hanMucChi.setSoTien(soTienHanMuc);
                hanMucChi.setTenHanMucChi(name);
                hanMucChi.setLapLai(lapLai);
                hanMucChi.setNgayBatDau(ngayBdau);
                hanMucChi.setNgayKetThuc(ngayKT);
                hanMucChi.setSoTien(soTienHanMuc);

                long result = SQLHanMucChi.addHanMucChi(getActivity(), hanMucChi);
                if(result > 0){
                    edtTenHanMuc.setText("");
                    edtSoHanMuc.setText("");
                    Toast.makeText(getActivity(), "Thêm hạn mức chi thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Thêm hạn mức chi không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
