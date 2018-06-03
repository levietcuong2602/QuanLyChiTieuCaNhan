package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Dialog;
import android.content.ContentValues;
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
import com.lv.vietcuong.project2.Databases.SQLViTien;
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
        dialog.setContentView(R.layout.dialog_taikhoan);
        setListViewTaikhoanChi();

        dialog.show();
    }

    private void setListViewTaikhoanChi(){
        final ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());;

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi, dsViTien);
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
        dialog.setContentView(R.layout.dialog_taikhoan);
        setListViewTaikhoanThu();

        dialog.show();
    }

    private void setListViewTaikhoanThu(){
        final ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());;

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi, dsViTien);
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
        int trangThai = 0;

        String chiChoAi = null;

        if (edtSoTien.getText().toString().isEmpty() || idViTienChi == 0 || idViTienThu == 0){
            Toast.makeText(getActivity(), "Số tiền hoặc các ví tiền không được để trống!", Toast.LENGTH_SHORT).show();
        }else {
            int soTien = Integer.parseInt(edtSoTien.getText().toString());

            SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());

            ContentValues cv1 = new ContentValues();
            cv1.put("soTien",soTien);
            cv1.put("dienDai",dienGiai);
            cv1.put("username",username);
            cv1.put("ngay",ngay);
            cv1.put("idHangMuc",idHangMuc);
            cv1.put("loaiGhiChep",loaiGhiChep);
            cv1.put("trangThai",trangThai);
            database.insert("GhiChep", null, cv1);

            Cursor cursor = database.rawQuery("select Max(idGhiChep) from GhiChep", null);
            cursor.moveToFirst();
            int idGhiChep = cursor.getInt(0);

            ContentValues cv2 = new ContentValues();
            cv2.put("username",username);
            cv2.put("idViTienChi",idViTienChi);
            cv2.put("idViTienThu",idViTienThu);
            cv2.put("idGhiChep",idGhiChep);
            database.insert("ChuyenKhoan", null, cv2);

            Toast.makeText(getActivity(), "Thêm ghi chép thành công", Toast.LENGTH_SHORT).show();
            database.close();
        }
    }

}