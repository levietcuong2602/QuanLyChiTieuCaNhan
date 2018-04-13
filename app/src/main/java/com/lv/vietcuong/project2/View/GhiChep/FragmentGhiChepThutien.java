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
import com.lv.vietcuong.project2.Model.HangMuc;
import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterHangMuc;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterTaiKhoan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 3/24/2018.
 */

public class FragmentGhiChepThutien extends android.support.v4.app.Fragment implements View.OnClickListener{
    private Button btnMucThu, btnVaoTaiKhoan, btnNgay, btnGhi;
    private EditText edtSoTien, edtDienGiai, edtThuTuAi;
    private TextView tvNgay, tvMucThu, tvVaoTaiKhoan;
    private Calendar cal;

    private int idHangMucThu, idViTien;
    private Dialog dialog;
    private ListView listView;

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
        btnMucThu = (Button) v.findViewById(R.id.btn_mucthu_2);
        btnVaoTaiKhoan = (Button) v.findViewById(R.id.btn_vaotaikhoan_2);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_2);
        btnGhi = (Button) v.findViewById(R.id.btn_ghi_2);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_2);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_2);
        edtThuTuAi = (EditText) v.findViewById(R.id.edt_thu_tu_ai_2);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_2);
        tvMucThu = (TextView) v.findViewById(R.id.tv_mucthu_2);
        tvVaoTaiKhoan = (TextView) v.findViewById(R.id.tv_vaotaikhoan_2);
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
            case R.id.btn_mucthu_2:
                showDialogMucthu(getContext());
                break;
            case R.id.btn_vaotaikhoan_2:
                showDialogTaikhoan(getContext());
                break;
            case R.id.btn_ngay_2:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_ghi_2:
                saveData();
                break;
        }
    }

    private void showDialogMucthu(Context context){
        dialog = new Dialog(context);
        dialog.setTitle("Mục thu");
        dialog.setContentView(R.layout.dialog_hangmuc);

        setListViewMucThu();

        dialog.show();
    }

    private void setListViewMucThu(){
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        Cursor cursor = database.rawQuery("select * from HangMuc where loaiHangMuc = 'thu'",null);
        final ArrayList<HangMuc> dsHangMucThu = new ArrayList<>();

        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String tenHangMuc = cursor.getString(1);
            String dienDai = cursor.getString(2);
            int icon = cursor.getInt(3);
            String loaiHangMuc = cursor.getString(4);
            HangMuc hangMucThu = new HangMuc(id,tenHangMuc,dienDai,loaiHangMuc,icon);
            dsHangMucThu.add(hangMucThu);
            Log.d("tag",hangMucThu.getTenHangMuc());
        }

        listView = (ListView) dialog.findViewById(R.id.lv_hangmuc_thuchi);
        dialog.setTitle("Muc thu");
        AdapterHangMuc adapterMucThu = new AdapterHangMuc(getContext(),R.layout.item_ds_mucchi,dsHangMucThu);
        listView.setAdapter(adapterMucThu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HangMuc hangMuc = dsHangMucThu.get(i);
                idHangMucThu = hangMuc.getIdHangMuc();
                tvMucThu.setText(hangMuc.getTenHangMuc());
                dialog.dismiss();
            }
        });
    }

    private void showDialogTaikhoan(Context context){
        dialog = new Dialog(context);
        dialog.setTitle("Tài khoản");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
        setListViewTaikhoan();

        dialog.show();
    }

    private void setListViewTaikhoan(){
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
        dialog.setTitle("Ví tiền");
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi,dsViTien);
        listView.setAdapter(adapterTaiKhoan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViTien viTien = dsViTien.get(i);
                idViTien = viTien.getIdViTien();
                tvVaoTaiKhoan.setText(viTien.getTenViTien());
                dialog.dismiss();
            }
        });
    }

    public void saveData(){
        String dienGiai = edtDienGiai.getText().toString();
        String username = Layout_TrangChu.taiKhoanDangNhap.getUsername();
        String ngay = tvNgay.getText().toString();
        int idHangMuc = idHangMucThu;
        String loaiGhiChep = "thutien";

        String thuTuAi = edtThuTuAi.getText().toString();
        int idViTienThu = idViTien;

        if (idHangMuc == 0 || edtSoTien.getText().toString().isEmpty() || idViTienThu == 0) {
            Toast.makeText(getActivity(), "Hạng mục hoặc số tiền hoặc ví tiền không được để trống!", Toast.LENGTH_SHORT).show();
        } else {
            int soTien = Integer.parseInt(edtSoTien.getText().toString());

            SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
            database.execSQL("Insert into GhiChep(soTien, dienDai, username, ngay, idHangMuc, loaiGhiChep) values (" +
                    soTien + ", '" + dienGiai + "', '" + username + "', '" + ngay + "', " + idHangMuc + ", '" + loaiGhiChep + "')");

            Cursor cursor = database.rawQuery("select Max(idGhiChep) from GhiChep", null);
            cursor.moveToFirst();
            int idGhiChep = cursor.getInt(0);

            database.execSQL("Insert into ThuTien(thuTuAi, idGhiChep, idViTienThu, username) values ('" +
                    thuTuAi + "', " + idGhiChep + ", " + idViTienThu + ", '" + username + "')");

            Toast.makeText(getActivity(), "Thêm ghi chép thành công!", Toast.LENGTH_SHORT).show();
        }
    }

}
