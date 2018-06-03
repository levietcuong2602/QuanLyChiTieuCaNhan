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

import com.lv.vietcuong.project2.Adapter.AdapterHangMucThuChi;
import com.lv.vietcuong.project2.Databases.DataBaseManager;
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
        dialog.setContentView(R.layout.dialog_hangmuc);
        setListViewMucThu();

        dialog.show();
    }

    private void setListViewMucThu(){
        final ArrayList<HangMuc> dsHangMucThu = SQLHangMuc.getAllHangMuc(getActivity(), "thutien");

        listView = (ListView) dialog.findViewById(R.id.lv_hangmuc_thuchi);
        AdapterHangMucThuChi adapterMucThu = new AdapterHangMucThuChi(getActivity(),R.layout.item_hangmucchi_2,dsHangMucThu);
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
        int trangThai = 0;

        if (idHangMuc == 0 || edtSoTien.getText().toString().isEmpty() || idViTienThu == 0) {
            Toast.makeText(getActivity(), "Hạng mục hoặc số tiền hoặc ví tiền không được để trống!", Toast.LENGTH_SHORT).show();
        } else {
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
            cv2.put("thuTuAi",thuTuAi);
            cv2.put("idGhiChep",idGhiChep);
            cv2.put("idViTienThu",idViTienThu);
            cv2.put("username",username);
            database.insert("ThuTien", null, cv2);

            Toast.makeText(getActivity(), "Thêm ghi chép thành công!", Toast.LENGTH_SHORT).show();
            database.close();
        }
    }

}
