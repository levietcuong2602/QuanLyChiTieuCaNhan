package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLWallet;
import com.lv.vietcuong.project2.Model.ChiTien;
import com.lv.vietcuong.project2.Model.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterTaiKhoan;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityEditChuyenkhoan extends AppCompatActivity implements View.OnClickListener{
    private Button btnVaoTaiKhoan, btnTuTaiKhoan, btnNgay, btnCapNhat, btnXoa;
    private EditText edtSoTien, edtDienGiai;
    private TextView tvNgay, tvVaoTaiKhoan, tvTuTaiKhoan;
    private Calendar cal;

    private Dialog dialog;
    private ListView listView;
    private int idGhiChep, soTien, idViTienChi, idViTienThu;
    private String dienGiai, ngay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ghichep_chuyenkhoan_edit);

        initWidget();
        Intent intent = getIntent();
        idGhiChep = intent.getIntExtra("idGhiChep",0);

        setData();
        setEventClickViews();
    }

    public void initWidget(){
        btnVaoTaiKhoan = (Button) findViewById(R.id.btn_vaotaikhoan_6);
        btnTuTaiKhoan = (Button) findViewById(R.id.btn_tutaikhoan_6);
        btnNgay = (Button) findViewById(R.id.btn_ngay_6);
        btnCapNhat = (Button) findViewById(R.id.btn_capnhat_6);
        btnXoa = (Button) findViewById(R.id.btn_xoa_6);

        edtSoTien = (EditText) findViewById(R.id.edt_sotien_6);
        edtDienGiai = (EditText) findViewById(R.id.edt_diengiai_6);

        tvNgay = (TextView) findViewById(R.id.tv_ngay_6);
        tvVaoTaiKhoan = (TextView) findViewById(R.id.tv_vaotaikhoan_6);
        tvTuTaiKhoan = (TextView) findViewById(R.id.tv_tutaikhoan_6);
    }

    private void setEventClickViews() {
        btnVaoTaiKhoan.setOnClickListener(this);
        btnTuTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnCapNhat.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_vaotaikhoan_6:
                showDialogTaikhoanThu(this);
                break;
            case R.id.btn_tutaikhoan_6:
                showDialogTaikhoanChi(this);
                break;
            case R.id.btn_ngay_6:
                Util.showDatePickerDialog(tvNgay,this);
                break;
            case R.id.btn_capnhat_6:
                updateData();
                break;
            case R.id.btn_xoa_6:
                deleteData();
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
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(this);
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
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(this,R.layout.item_ds_mucchi,dsViTien);
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
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(this);
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
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(this,R.layout.item_ds_mucchi,dsViTien);
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

    private void setData(){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(this);
        Cursor cursor = db.rawQuery("select * from GhiChep where idGhiChep = "+idGhiChep,null);
        cursor.moveToFirst();
        soTien = cursor.getInt(1);
        dienGiai = cursor.getString(2);
        ngay = cursor.getString(4);

        edtSoTien.setText(soTien+"");
        edtDienGiai.setText(dienGiai);
        tvNgay.setText(ngay);

        cursor = db.rawQuery("select * from ChuyenKhoan where idGhiChep = "+idGhiChep,null);
        cursor.moveToFirst();
        idViTienChi = cursor.getInt(1);
        idViTienThu = cursor.getInt(2);

        tvTuTaiKhoan.setText(SQLWallet.getTenViTien(this,idViTienChi));
        tvVaoTaiKhoan.setText(SQLWallet.getTenViTien(this,idViTienThu));
    }

    private void updateData(){
        ChuyenKhoan chuyenKhoan = new ChuyenKhoan();
        if (edtSoTien.getText().toString().isEmpty()){
            Toast.makeText(this, "Số tiền không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            soTien = Integer.parseInt(edtSoTien.getText().toString());
            dienGiai = edtDienGiai.getText().toString();
            ngay = ngay = tvNgay.getText().toString();

            chuyenKhoan.setSoTien(soTien);
            chuyenKhoan.setDienGiai(dienGiai);
            chuyenKhoan.setNgay(ngay);
            chuyenKhoan.setIdViTienChi(idViTienChi);
            chuyenKhoan.setIdViTienThu(idViTienThu);

            SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(this);
            ContentValues cv1 = new ContentValues();
            ContentValues cv2 = new ContentValues();
            cv1.put("soTien",chuyenKhoan.getSoTien());
            cv1.put("dienDai",chuyenKhoan.getDienGiai());
            cv1.put("ngay",chuyenKhoan.getNgay());

            cv2.put("idViTienChi",chuyenKhoan.getIdViTienChi());
            cv2.put("idViTienThu",chuyenKhoan.getIdViTienThu());

            db.update("GhiChep",cv1,"idGhiChep = "+idGhiChep,null);
            db.update("ChuyenKhoan",cv2,"idGhiChep = "+idGhiChep,null);
            db.close();
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
     //       DaGhiActivity.updateListGhiChep(this);
            finish();
        }
    }

    private void deleteData(){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(this);
        db.delete("GhiChep","idGhiChep = "+idGhiChep,null);
        db.delete("ChuyenKhoan","idGhiChep = "+idGhiChep,null);
        db.close();
        Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
    //    DaGhiActivity.updateListGhiChep(this);
        finish();
    }


}
