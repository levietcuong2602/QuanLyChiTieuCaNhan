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
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLWallet;
import com.lv.vietcuong.project2.Model.ChiTien;
import com.lv.vietcuong.project2.Model.HangMuc;
import com.lv.vietcuong.project2.Model.ThuTien;
import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterHangMuc;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterTaiKhoan;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityEditThutien extends AppCompatActivity implements View.OnClickListener{
    private Button btnMucThu, btnVaoTaiKhoan, btnNgay, btnCapNhat, btnXoa;
    private EditText edtSoTien, edtDienGiai, edtThuTuAi;
    private TextView tvNgay, tvMucThu, tvVaoTaiKhoan;
    private Calendar cal;
    private ListView listView;
    private Dialog dialog;

    private int idGhiChep, soTien, idHangMucThu, idViTien;
    private String dienGiai, ngay, thuTuAi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ghichep_thutien_edit);
        initWidget();

        Intent intent = getIntent();
        idGhiChep = intent.getIntExtra("idGhiChep",0);

        setData();
        setEventClickViews();

    }

    public void initWidget(){
        btnMucThu = (Button) findViewById(R.id.btn_mucthu_5);
        btnVaoTaiKhoan = (Button) findViewById(R.id.btn_vaotaikhoan_5);
        btnNgay = (Button) findViewById(R.id.btn_ngay_5);
        btnCapNhat = (Button) findViewById(R.id.btn_capnhat_5);
        btnXoa = (Button) findViewById(R.id.btn_xoa_5);

        edtSoTien = (EditText) findViewById(R.id.edt_sotien_5);
        edtDienGiai = (EditText) findViewById(R.id.edt_diengiai_5);
        edtThuTuAi = (EditText) findViewById(R.id.edt_thu_tu_ai_5);

        tvNgay = (TextView) findViewById(R.id.tv_ngay_5);
        tvMucThu = (TextView) findViewById(R.id.tv_mucthu_5);
        tvVaoTaiKhoan = (TextView) findViewById(R.id.tv_vaotaikhoan_5);
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
                showDialogMucthu(this);
                break;
            case R.id.btn_vaotaikhoan_5:
                showDialogTaikhoan(this);
                break;
            case R.id.btn_ngay_5:
                Util.showDatePickerDialog(tvNgay,this);
                break;
            case R.id.btn_capnhat_5:
                updateData();
                break;
            case R.id.btn_xoa_5:
                deleteData();
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
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(this);
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
        AdapterHangMuc adapterMucThu = new AdapterHangMuc(this,R.layout.item_ds_mucchi,dsHangMucThu);
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
        dialog.setTitle("Ví tiền");
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(this,R.layout.item_ds_mucchi,dsViTien);
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

    private void setData(){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(this);
        Cursor cursor = db.rawQuery("select * from GhiChep where idGhiChep = "+idGhiChep,null);
        cursor.moveToFirst();
        soTien = cursor.getInt(1);
        idHangMucThu = cursor.getInt(5);
        dienGiai = cursor.getString(2);
        ngay = cursor.getString(4);

        edtSoTien.setText(soTien+"");
        tvMucThu.setText(SQLHangMuc.getTenHangMuc(this,idHangMucThu));
        edtDienGiai.setText(dienGiai);
        tvNgay.setText(ngay);

        cursor = db.rawQuery("select * from ThuTien where idGhiChep = "+idGhiChep,null);
        cursor.moveToFirst();
        thuTuAi = cursor.getString(1);
        idViTien = cursor.getInt(3);

        edtThuTuAi.setText(thuTuAi);
        tvVaoTaiKhoan.setText(SQLWallet.getTenViTien(this,idViTien));
    }

    private void updateData(){
        ThuTien thuTien = new ThuTien();
        if (edtSoTien.getText().toString().isEmpty()){
            Toast.makeText(this, "Số tiền không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            soTien = Integer.parseInt(edtSoTien.getText().toString());
            dienGiai = edtDienGiai.getText().toString();
            ngay = ngay = tvNgay.getText().toString();
            thuTuAi = edtThuTuAi.getText().toString();

            thuTien.setSoTien(soTien);
            thuTien.setDienGiai(dienGiai);
            thuTien.setNgay(ngay);
            thuTien.setIdHangMuc(idHangMucThu);
            thuTien.setThuTuAi(thuTuAi);
            thuTien.setIdViTienThu(idViTien);

            SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(this);
            ContentValues cv1 = new ContentValues();
            ContentValues cv2 = new ContentValues();
            cv1.put("soTien",thuTien.getSoTien());
            cv1.put("dienDai",thuTien.getDienGiai());
            cv1.put("ngay",thuTien.getNgay());
            cv1.put("idHangMuc",thuTien.getIdHangMuc());
            cv2.put("thuTuAi",thuTien.getThuTuAi());
            cv2.put("idViTienThu",thuTien.getIdViTienThu());

            db.update("GhiChep",cv1,"idGhiChep = "+idGhiChep,null);
            db.update("ThuTien",cv2,"idGhiChep = "+idGhiChep,null);
            db.close();
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        //    DaGhiActivity.updateListGhiChep(this);
            finish();
        }
    }

    private void deleteData(){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(this);
        db.delete("GhiChep","idGhiChep = "+idGhiChep,null);
        db.delete("ThuTien","idGhiChep = "+idGhiChep,null);
        db.close();
        Toast.makeText(this, "Xoá thành công", Toast.LENGTH_SHORT).show();
    //    DaGhiActivity.updateListGhiChep(this);
        finish();
    }

}
