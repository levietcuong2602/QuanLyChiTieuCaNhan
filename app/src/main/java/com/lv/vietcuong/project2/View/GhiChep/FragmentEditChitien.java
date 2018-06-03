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

import com.lv.vietcuong.project2.Adapter.AdapterHangMucThuChi;
import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterTaiKhoan;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentEditChitien extends Fragment implements View.OnClickListener{
    private Button btnMucChi, btnTuTaiKhoan, btnNgay, btnCapNhat, btnXoa;
    private EditText edtSoTien, edtDienGiai, edtChiChoAi;
    private TextView tvNgay, tvMucChi, tvTuTaiKhoan;
    private Calendar cal;
    private ListView listView;
    private Dialog dialog;

    private int idGhiChep, soTien, soTienBanDau, idHangMucChi, idViTien;
    private String dienGiai, ngay, chiChoAi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichep_chitien_edit,container,false);
        initWidget(view);

        Bundle bundle = getArguments();
        idGhiChep = bundle.getInt("idGhiChep");
        setData();
        setEventClickViews();

        return view;
    }

    public void initWidget(View v){
        btnMucChi = (Button) v.findViewById(R.id.btn_mucchi_4);
        btnTuTaiKhoan = (Button) v.findViewById(R.id.btn_tutaikhoan_4);
        btnNgay = (Button) v.findViewById(R.id.btn_ngay_4);
        btnCapNhat = (Button) v.findViewById(R.id.btn_capnhat_4);
        btnXoa = (Button) v.findViewById(R.id.btn_xoa_4);

        edtSoTien = (EditText) v.findViewById(R.id.edt_sotien_4);
        edtDienGiai = (EditText) v.findViewById(R.id.edt_diengiai_4);
        edtChiChoAi = (EditText) v.findViewById(R.id.edt_chi_cho_ai_4);

        tvNgay = (TextView) v.findViewById(R.id.tv_ngay_4);
        tvMucChi = (TextView) v.findViewById(R.id.tv_mucchi_4);
        tvTuTaiKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan_4);
    }

    private void setEventClickViews() {
        btnMucChi.setOnClickListener(this);
        btnTuTaiKhoan.setOnClickListener(this);
        btnNgay.setOnClickListener(this);
        btnCapNhat.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mucchi_4:
                showDialogMucchi(getContext());
                break;
            case R.id.btn_tutaikhoan_4:
                showDialogTaikhoan(getContext());
                break;
            case R.id.btn_ngay_4:
                Util.showDatePickerDialog(tvNgay,getActivity());
                break;
            case R.id.btn_capnhat_4:
                updateData();
                break;
            case R.id.btn_xoa_4:
                //update trang thai = 2
                SQLGhiChep.xoaTamThoiGhichep(getActivity(), idGhiChep);
                break;
        }
    }

    private void showDialogMucchi(Context context){
        dialog = new Dialog(context);
        dialog.setTitle("Mục chi");
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

    private void setData(){
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        Cursor cursor = db.rawQuery("select * from GhiChep where idGhiChep = "+idGhiChep,null);
        cursor.moveToFirst();
        soTienBanDau = cursor.getInt(1);
        idHangMucChi = cursor.getInt(6);
        dienGiai = cursor.getString(2);
        ngay = cursor.getString(4);

        edtSoTien.setText(soTienBanDau+"");
        tvMucChi.setText(SQLHangMuc.getTenHangMuc(getActivity(),idHangMucChi));
        edtDienGiai.setText(dienGiai);
        tvNgay.setText(ngay);

        cursor = db.rawQuery("select * from ChiTien where idGhiChep = "+idGhiChep,null);
        cursor.moveToFirst();
        chiChoAi = cursor.getString(0);
        idViTien = cursor.getInt(2);

        edtChiChoAi.setText(chiChoAi);
        tvTuTaiKhoan.setText(SQLViTien.getTenViTien(getActivity(),idViTien));
    }

    private void updateData(){
        ChiTien chiTien = new ChiTien();
        if (edtSoTien.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Số tiền không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            soTien = Integer.parseInt(edtSoTien.getText().toString());
            dienGiai = edtDienGiai.getText().toString();
            ngay = ngay = tvNgay.getText().toString();
            chiChoAi = edtChiChoAi.getText().toString();

            if ((SQLHanMucChi.getSotienHanmucChi(getActivity(),idViTien) != -1) && (SQLGhiChep.getSotienchi(getActivity(), idViTien) + soTien - soTienBanDau > SQLHanMucChi.getSotienHanmucChi(getActivity(),idViTien))){
                Toast.makeText(getActivity(), "Đã vượt quá hạn mức chi của ví tiền!!!", Toast.LENGTH_SHORT).show();
            }else {
                chiTien.setSoTien(soTien);
                chiTien.setDienGiai(dienGiai);
                chiTien.setNgay(ngay);
                chiTien.setIdHangMuc(idHangMucChi);
                chiTien.setChiChoAi(chiChoAi);
                chiTien.setIdViTienChi(idViTien);

                SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
                ContentValues cv1 = new ContentValues();
                ContentValues cv2 = new ContentValues();
                cv1.put("soTien",chiTien.getSoTien());
                cv1.put("dienDai",chiTien.getDienGiai());
                cv1.put("ngay",chiTien.getNgay());
                cv1.put("idHangMuc",chiTien.getIdHangMuc());
                cv1.put("trangThai", 3);

                cv2.put("chiChoAi",chiTien.getChiChoAi());
                cv2.put("idViTienChi",chiTien.getIdViTienChi());

                db.update("GhiChep",cv1,"idGhiChep = "+idGhiChep,null);
                db.update("ChiTien",cv2,"idGhiChep = "+idGhiChep,null);
                db.close();
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                Util.replace(R.id.content_layout,new FragmentDaGhi(),getActivity());
            }
        }
    }
}
