package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Dialog;
<<<<<<< HEAD
import android.content.ContentValues;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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

<<<<<<< HEAD
import com.lv.vietcuong.project2.Adapter.AdapterHangMucThuChi;
import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
=======
import com.lv.vietcuong.project2.Databases.DataBaseManager;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
<<<<<<< HEAD
=======
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterHangMuc;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
<<<<<<< HEAD
    private TextView tvNgay, tvMucChi, tvTuTaiKhoan;
=======
    private TextView tvNgay, tvMucChi, tvTuTuKhoan;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    private Calendar cal;
    private ListView listView;
    private Dialog dialog;

    private int idHangMucChi;
    private int idViTien;
<<<<<<< HEAD

=======
    ArrayList<HangMuc> dsHangMucChi;
    ArrayList<ViTien> dsViTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
<<<<<<< HEAD
        tvTuTaiKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan);
=======
        tvTuTuKhoan = (TextView) v.findViewById(R.id.tv_tutaikhoan);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
<<<<<<< HEAD
        dialog.setContentView(R.layout.dialog_hangmuc);
=======
        dialog.setTitle("Mục chi");
        dialog.setContentView(R.layout.dialog_hangmuc);

>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        setListViewMucChi();

        dialog.show();
    }

    private void setListViewMucChi(){
<<<<<<< HEAD
        final ArrayList<HangMuc> dsHangMucChi = SQLHangMuc.getAllHangMuc(getActivity(), "chitien");

        listView = (ListView) dialog.findViewById(R.id.lv_hangmuc_thuchi);
        AdapterHangMucThuChi adapterHangMuc = new AdapterHangMucThuChi(getActivity(), R.layout.item_hangmucchi_2, dsHangMucChi);
=======
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        Cursor cursor = database.rawQuery("select * from HangMuc where loaiHangMuc = 'chi'",null);
        dsHangMucChi = new ArrayList<>();

//        for(int i = 0; i < cursor.getCount(); i++){
//            cursor.moveToPosition(i);
//            int id = cursor.getInt(0);
//            String tenHangMuc = cursor.getString(1);
//            String dienDai = cursor.getString(2);
//            int icon = cursor.getInt(3);
//            String loaiHangMuc = cursor.getString(4);
//            HangMuc hangMucChi = new HangMuc(id,tenHangMuc,dienDai,loaiHangMuc,icon);
//            dsHangMucChi.add(hangMucChi);
//        }
        dsHangMucChi = SQLHangMuc.getAllHangMuc(getActivity(), "chi");

        listView = (ListView) dialog.findViewById(R.id.lv_hangmuc_thuchi);
        dialog.setTitle("Muc chi");
        AdapterHangMuc adapterHangMuc = new AdapterHangMuc(getContext(),R.layout.item_ds_mucchi,dsHangMucChi);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
<<<<<<< HEAD
        dialog.setContentView(R.layout.dialog_taikhoan);
=======
        dialog.setTitle("Tài khoản");
        dialog.setContentView(R.layout.dialog_taikhoan);
//        dialog.setCancelable(false);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        setListViewTaikhoan();

        dialog.show();
    }

    private void setListViewTaikhoan(){
<<<<<<< HEAD
        final ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());;

        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi, dsViTien);
=======
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        Cursor cursor = database.rawQuery("select * from ViTien",null);
        dsViTien = new ArrayList<>();

//        for(int i = 0; i < cursor.getCount(); i++){
//            cursor.moveToPosition(i);
//            int id = cursor.getInt(0);
//            String tenViTien = cursor.getString(1);
//            String loaiViTien = cursor.getString(2);
//            int soDu = cursor.getInt(3);
//            String ghiChu = cursor.getString(4);
//            String username = cursor.getString(5);
//            ViTien viTien = new ViTien(id,tenViTien,loaiViTien,soDu,ghiChu,username);
//            dsViTien.add(viTien);
//        }
        dsViTien = SQLViTien.getAllWallet(getActivity());


        listView = (ListView) dialog.findViewById(R.id.lv_taikhoan);
        dialog.setTitle("Ví tiền");
        AdapterTaiKhoan adapterTaiKhoan = new AdapterTaiKhoan(getContext(),R.layout.item_ds_mucchi,dsViTien);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        listView.setAdapter(adapterTaiKhoan);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViTien viTien = dsViTien.get(i);
                idViTien = viTien.getIdViTien();
<<<<<<< HEAD
                tvTuTaiKhoan.setText(viTien.getTenViTien());
=======
                tvTuTuKhoan.setText(viTien.getTenViTien());
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
                dialog.dismiss();
            }
        });
    }



    public void saveData() {

        String dienGiai = edtDienGiai.getText().toString();
        String username = Layout_TrangChu.taiKhoanDangNhap.getUsername();
        String ngay = tvNgay.getText().toString();
        int idHangMuc = idHangMucChi;
<<<<<<< HEAD
        String loaiGhiChep = "chitien";

        String chiChoAi = edtChiChoAi.getText().toString();
        int idViTienChi = idViTien;
        int trangThai = 0;
=======
        String loaiGhiChep = "chi";

        String chiChoAi = edtChiChoAi.getText().toString();
        int idViTienChi = idViTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

        if (idHangMuc == 0 || edtSoTien.getText().toString().isEmpty() || idViTienChi == 0) {
            Toast.makeText(getActivity(), "Hạng mục hoặc số tiền hoặc ví tiền không được để trống!", Toast.LENGTH_SHORT).show();
        } else {
            int soTien = Integer.parseInt(edtSoTien.getText().toString());

<<<<<<< HEAD
            if ((SQLHanMucChi.getSotienHanmucChi(getActivity(), idViTienChi) != -1) && (SQLGhiChep.getSotienchi(getActivity(), idViTienChi) + soTien > SQLHanMucChi.getSotienHanmucChi(getActivity(), idViTienChi))){
                Toast.makeText(getActivity(), "Đã vượt quá hạn mức chi của ví tiền!!!", Toast.LENGTH_SHORT).show();
            }else {
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
                cv2.put("chiChoAi",chiChoAi);
                cv2.put("idGhiChep",idGhiChep);
                cv2.put("idViTienChi",idViTienChi);
                cv2.put("username",username);
                database.insert("ChiTien", null, cv2);

                Toast.makeText(getActivity(), "Thêm ghi chép thành công!", Toast.LENGTH_SHORT).show();
                database.close();
            }
=======
            SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
            database.execSQL("Insert into GhiChep(soTien, dienDai, username, ngay, idHangMuc, loaiGhiChep, trangthai) values (" +
                    soTien + ", '" + dienGiai + "', '" + username + "', '" + ngay + "', " + idHangMuc + ", '" + loaiGhiChep + "', "+Layout_TrangChu.NOT_SYNCED_WITH_SERVER+")");

            Cursor cursor = database.rawQuery("select Max(idGhiChep) from GhiChep", null);
            cursor.moveToFirst();
            int idGhiChep = cursor.getInt(0);

            database.execSQL("Insert into ChiTien(chiChoAi, idGhiChep, idViTienChi, username) values ('" +
                    chiChoAi + "', " + idGhiChep + ", " + idViTienChi + ", '" + username + "')");

            Toast.makeText(getActivity(), "Thêm ghi chép thành công!", Toast.LENGTH_SHORT).show();
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }
    }

}
