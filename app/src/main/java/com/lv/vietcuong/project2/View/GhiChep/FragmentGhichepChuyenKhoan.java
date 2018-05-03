package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterTaiKhoan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 3/24/2018.
 */

public class FragmentGhichepChuyenKhoan extends android.support.v4.app.Fragment implements View.OnClickListener{
    private Button btnTuTaiKhoan, btnVaoTaiKhoan, btnNgay, btnGhi;
    private EditText edtSoTien, edtDienGiai;
    private TextView tvNgay, tvTuTaiKhoan, tvVaoTaiKhoan;
    private Calendar cal;

    private Dialog dialog;
    private ListView listView;
    private int idViTienChi, idViTienThu;

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
                showDialogTaikhoanChi(getContext());
                break;
            case R.id.btn_vaotaikhoan_3:
                showDialogTaikhoanThu(getContext());
                break;
            case R.id.btn_ngay_3:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_ghi_3:
                saveData();
                break;
        }
    }


    private void showDialogTaikhoanChi(Context context) {
        dialog = new Dialog(context);
        dialog.setTitle("Tài khoản chi");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
        setListViewTaikhoanChi();

        dialog.show();
    }

    private void setListViewTaikhoanChi(){
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        Cursor cursor = database.rawQuery("select * from ViTien",null);
        final ArrayList<ViTien> dsViTien = new ArrayList<>();

        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String tenViTien = cursor.getString(1);
            String loaiViTien = cursor.getString(2);
            int soDu = cursor.getInt(3);
            String ghiChu = cursor.getString(4);
            String username = cursor.getString(5);
            ViTien viTien = new ViTien(id,tenViTien,loaiViTien,soDu,ghiChu,username);
            dsViTien.add(viTien);
        }

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi,dsViTien);
        listView.setAdapter(adapterTaiKhoan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViTien viTien = dsViTien.get(i);
                idViTienChi = viTien.getIdViTien();
                tvTuTaiKhoan.setText(viTien.getTenViTien());
                dialog.dismiss();
            }
        });
    }

    private void showDialogTaikhoanThu(Context context) {
        dialog = new Dialog(context);
        dialog.setTitle("Tài khoản thu");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
        setListViewTaikhoanThu();

        dialog.show();
    }

    private void setListViewTaikhoanThu(){
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        Cursor cursor = database.rawQuery("select * from ViTien",null);
        final ArrayList<ViTien> dsViTien = new ArrayList<>();

        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String tenViTien = cursor.getString(1);
            String loaiViTien = cursor.getString(2);
            int soDu = cursor.getInt(3);
            String ghiChu = cursor.getString(4);
            String username = cursor.getString(5);
            ViTien viTien = new ViTien(id,tenViTien,loaiViTien,soDu,ghiChu,username);
            dsViTien.add(viTien);
        }

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi,dsViTien);
        listView.setAdapter(adapterTaiKhoan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViTien viTien = dsViTien.get(i);
                idViTienThu = viTien.getIdViTien();
                tvVaoTaiKhoan.setText(viTien.getTenViTien());
                dialog.dismiss();
            }
        });
    }



    public void saveData(){

        String dienGiai = edtDienGiai.getText().toString();
        String username = Layout_TrangChu.taiKhoanDangNhap.getUsername();
        String ngay = tvNgay.getText().toString();
        int idHangMuc = 0;
        String loaiGhiChep = "chuyenkhoan";

        String chiChoAi = null;

        if (edtSoTien.getText().toString().isEmpty() || idViTienChi == 0 || idViTienThu == 0){
            Toast.makeText(getActivity(), "Số tiền hoặc các ví tiền không được để trống!", Toast.LENGTH_SHORT).show();
        }else {
            int soTien = Integer.parseInt(edtSoTien.getText().toString());

            SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
            database.execSQL("Insert into GhiChep(soTien, dienDai, username, ngay, idHangMuc, loaiGhiChep) values (" +
                    soTien + ", '" + dienGiai + "', '" + username + "', '" + ngay + "', " + idHangMuc + ", '" + loaiGhiChep + "')");

            Cursor cursor = database.rawQuery("select Max(idGhiChep) from GhiChep", null);
            cursor.moveToFirst();
            int idGhiChep = cursor.getInt(0);

            database.execSQL("Insert into ChuyenKhoan(username, idViTienChi, idViTienThu, idGhiChep) values ('" +
                    username + "', " + idViTienChi + ", " + idViTienThu + ", " + idGhiChep + ")");

            Toast.makeText(getActivity(), "Thêm ghi chép thành công", Toast.LENGTH_SHORT).show();
        }
    }

}