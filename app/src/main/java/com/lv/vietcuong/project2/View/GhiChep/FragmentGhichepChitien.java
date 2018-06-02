package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.AdapterHangMucThuChi;
import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
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

public class FragmentGhichepChitien extends android.support.v4.app.Fragment implements View.OnClickListener{
    private Button btnMucChi, btnTuTaiKhoan, btnNgay, btnGhi;
    private EditText edtSoTien, edtDienGiai, edtChiChoAi;
    private TextView tvNgay, tvMucChi, tvTuTaiKhoan;
    private Calendar cal;
    private ListView listView;
    private Dialog dialog;

    private int idHangMucChi;
    private int idViTien;

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

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay);
        tvMucChi = (TextView) v.findViewById(R.id.tv_mucchi);
        tvTuTaiKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan);
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
                showDialogMucchi(getContext());
                break;
            case R.id.btn_tutaikhoan:
                showDialogTaikhoan(getContext());
                break;
            case R.id.btn_ngay:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_ghi:
                saveData();
                break;
        }
    }

    private void showDialogMucchi(Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_hangmuc);
        setListViewMucChi();

        dialog.show();
    }

    private void setListViewMucChi(){
        final ArrayList<HangMuc> dsHangMucChi = SQLHangMuc.getAllHangMuc(getActivity(), "chitien");

        listView = (ListView) dialog.findViewById(R.id.lv_hangmuc_thuchi);
        AdapterHangMucThuChi adapterHangMuc = new AdapterHangMucThuChi(getActivity(), R.layout.item_hangmucchi_2, dsHangMucChi);
        listView.setAdapter(adapterHangMuc);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               HangMuc hangMuc = dsHangMucChi.get(i);
               idHangMucChi = hangMuc.getIdHangMuc();
               tvMucChi.setText(hangMuc.getTenHangMuc());
               dialog.dismiss();
           }
       });
    }

   private void showDialogTaikhoan(Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_taikhoan);
        setListViewTaikhoan();

        dialog.show();
    }

    private void setListViewTaikhoan(){
        final ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());;

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi, dsViTien);
        listView.setAdapter(adapterTaiKhoan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViTien viTien = dsViTien.get(i);
                idViTien = viTien.getIdViTien();
                tvTuTaiKhoan.setText(viTien.getTenViTien());
                dialog.dismiss();
            }
        });
    }



    public void saveData() {

        String dienGiai = edtDienGiai.getText().toString();
        String username = Layout_TrangChu.taiKhoanDangNhap.getUsername();
        String ngay = tvNgay.getText().toString();
        int idHangMuc = idHangMucChi;
        String loaiGhiChep = "chitien";

        String chiChoAi = edtChiChoAi.getText().toString();
        int idViTienChi = idViTien;

        if (idHangMuc == 0 || edtSoTien.getText().toString().isEmpty() || idViTienChi == 0) {
            Toast.makeText(getActivity(), "Hạng mục hoặc số tiền hoặc ví tiền không được để trống!", Toast.LENGTH_SHORT).show();
        } else {
            int soTien = Integer.parseInt(edtSoTien.getText().toString());

            if (SQLGhiChep.getSotienchi(getActivity(), idViTienChi) + soTien > SQLHanMucChi.getSotienHanmucChi(getActivity(), idViTienChi)){
                Toast.makeText(getActivity(), "Đã vượt quá hạn mức chi của ví tiền!!!", Toast.LENGTH_SHORT).show();
            }else {
                SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
                database.execSQL("Insert into GhiChep(soTien, dienDai, username, ngay, idHangMuc, loaiGhiChep) values (" +
                        soTien + ", '" + dienGiai + "', '" + username + "', '" + ngay + "', " + idHangMuc + ", '" + loaiGhiChep + "')");

                Cursor cursor = database.rawQuery("select Max(idGhiChep) from GhiChep", null);
                cursor.moveToFirst();
                int idGhiChep = cursor.getInt(0);

                database.execSQL("Insert into ChiTien(chiChoAi, idGhiChep, idViTienChi, username) values ('" +
                        chiChoAi + "', " + idGhiChep + ", " + idViTienChi + ", '" + username + "')");

                Toast.makeText(getActivity(), "Thêm ghi chép thành công!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
