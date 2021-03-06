package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
<<<<<<< HEAD
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterTaiKhoan;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentEditChuyenkhoan extends Fragment implements View.OnClickListener{
    private Button btnVaoTaiKhoan, btnTuTaiKhoan, btnNgay, btnCapNhat, btnXoa;
    private EditText edtSoTien, edtDienGiai;
    private TextView tvNgay, tvVaoTaiKhoan, tvTuTaiKhoan;
    private Calendar cal;

    private Dialog dialog;
    private ListView listView;
    private int idGhiChep, soTien, idViTienChi, idViTienThu;
    private String dienGiai, ngay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_chuyenkhoan_edit,container,false);
        initWidget(view);

        Bundle bundle = getArguments();
        idGhiChep = bundle.getInt("idGhiChep");
        setData();
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnVaoTaiKhoan = (Button) v.findViewById(R.id.btn_vaotaikhoan_6);
        btnTuTaiKhoan = (Button) v.findViewById(R.id.btn_tutaikhoan_6);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_6);
        btnCapNhat = (Button) v.findViewById(R.id.btn_capnhat_6);
        btnXoa = (Button) v.findViewById(R.id.btn_xoa_6);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_6);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_6);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_6);
        tvVaoTaiKhoan = (TextView) v.findViewById(R.id.tv_vaotaikhoan_6);
        tvTuTaiKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan_6);
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
                showDialogTaikhoanThu(getContext());
                break;
            case R.id.btn_tutaikhoan_6:
                showDialogTaikhoanChi(getContext());
                break;
            case R.id.btn_ngay_6:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_capnhat_6:
                updateData();
                break;
            case R.id.btn_xoa_6:
<<<<<<< HEAD
                //update trang thai = 2
                SQLGhiChep.xoaTamThoiGhichep(getActivity(), idGhiChep);
=======
                deleteData();
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
                break;
        }
    }

    private void showDialogTaikhoanChi(Context context) {
        dialog = new Dialog(context);
<<<<<<< HEAD
        dialog.setContentView(R.layout.dialog_taikhoan);
=======
        dialog.setTitle("Tài khoản chi");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        setListViewTaikhoanChi();

        dialog.show();
    }

    private void setListViewTaikhoanChi(){
<<<<<<< HEAD
        final ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());;

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi, dsViTien);
=======
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
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
<<<<<<< HEAD
        dialog.setContentView(R.layout.dialog_taikhoan);
=======
        dialog.setTitle("Tài khoản thu");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        setListViewTaikhoanThu();

        dialog.show();
    }

    private void setListViewTaikhoanThu(){
<<<<<<< HEAD
        final ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());;

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi, dsViTien);
=======
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
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
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

        tvTuTaiKhoan.setText(SQLViTien.getTenViTien(getActivity(),idViTienChi));
        tvVaoTaiKhoan.setText(SQLViTien.getTenViTien(getActivity(),idViTienThu));
    }

    private void updateData(){
        ChuyenKhoan chuyenKhoan = new ChuyenKhoan();
        if (edtSoTien.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Số tiền không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            soTien = Integer.parseInt(edtSoTien.getText().toString());
            dienGiai = edtDienGiai.getText().toString();
            ngay = ngay = tvNgay.getText().toString();

            chuyenKhoan.setSoTien(soTien);
            chuyenKhoan.setDienGiai(dienGiai);
            chuyenKhoan.setNgay(ngay);
            chuyenKhoan.setIdViTienChi(idViTienChi);
            chuyenKhoan.setIdViTienThu(idViTienThu);

            SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
            ContentValues cv1 = new ContentValues();
            ContentValues cv2 = new ContentValues();
            cv1.put("soTien",chuyenKhoan.getSoTien());
            cv1.put("dienDai",chuyenKhoan.getDienGiai());
            cv1.put("ngay",chuyenKhoan.getNgay());
<<<<<<< HEAD
            cv1.put("trangThai", 3);
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

            cv2.put("idViTienChi",chuyenKhoan.getIdViTienChi());
            cv2.put("idViTienThu",chuyenKhoan.getIdViTienThu());

            db.update("GhiChep",cv1,"idGhiChep = "+idGhiChep,null);
            db.update("ChuyenKhoan",cv2,"idGhiChep = "+idGhiChep,null);
            db.close();
            Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            Util.replace(R.id.content_layout,new FragmentDaGhi(),getActivity());
        }
    }
<<<<<<< HEAD
=======

    private void deleteData(){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        db.delete("GhiChep","idGhiChep = "+idGhiChep,null);
        db.delete("ChuyenKhoan","idGhiChep = "+idGhiChep,null);
        db.close();
        Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
        Util.replace(R.id.content_layout,new FragmentDaGhi(),getActivity());
    }


>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
}
