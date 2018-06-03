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

<<<<<<< HEAD
import com.lv.vietcuong.project2.Adapter.AdapterHangMucThuChi;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.Databases.DataBaseManager;
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

public class FragmentGhiChepThutien extends android.support.v4.app.Fragment implements View.OnClickListener{
    private Button btnMucThu, btnVaoTaiKhoan, btnNgay, btnGhi;
    private EditText edtSoTien, edtDienGiai, edtThuTuAi;
    private TextView tvNgay, tvMucThu, tvVaoTaiKhoan;
    private Calendar cal;

    private int idHangMucThu, idViTien;
    private Dialog dialog;
    private ListView listView;
<<<<<<< HEAD

=======
    ArrayList<HangMuc> dsHangMucThu;
    ArrayList<ViTien> dsViTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
<<<<<<< HEAD
        dialog.setContentView(R.layout.dialog_hangmuc);
=======
        dialog.setTitle("Mục thu");
        dialog.setContentView(R.layout.dialog_hangmuc);

>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        setListViewMucThu();

        dialog.show();
    }

    private void setListViewMucThu(){
<<<<<<< HEAD
        final ArrayList<HangMuc> dsHangMucThu = SQLHangMuc.getAllHangMuc(getActivity(), "thutien");

        listView = (ListView) dialog.findViewById(R.id.lv_hangmuc_thuchi);
        AdapterHangMucThuChi adapterMucThu = new AdapterHangMucThuChi(getActivity(),R.layout.item_hangmucchi_2,dsHangMucThu);
=======
        SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        Cursor cursor = database.rawQuery("select * from HangMuc where loaiHangMuc = 'thu'",null);
        dsHangMucThu = new ArrayList<>();
        dsHangMucThu = SQLHangMuc.getAllHangMuc(getActivity(), "thu");
//        for(int i = 0; i < cursor.getCount(); i++){
//            cursor.moveToPosition(i);
//            int id = cursor.getInt(0);
//            String tenHangMuc = cursor.getString(1);
//            String dienDai = cursor.getString(2);
//            int icon = cursor.getInt(3);
//            String loaiHangMuc = cursor.getString(4);
//            HangMuc hangMucThu = new HangMuc(id,tenHangMuc,dienDai,loaiHangMuc,icon);
//            dsHangMucThu.add(hangMucThu);
//            Log.d("tag",hangMucThu.getTenHangMuc());
//        }

        listView = (ListView) dialog.findViewById(R.id.lv_hangmuc_thuchi);
        dialog.setTitle("Muc thu");
        AdapterHangMuc adapterMucThu = new AdapterHangMuc(getContext(),R.layout.item_ds_mucchi,dsHangMucThu);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
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
<<<<<<< HEAD
        dialog.setContentView(R.layout.dialog_taikhoan);
=======
        dialog.setTitle("Tài khoản");
        dialog.setContentView(R.layout.dialog_taikhoan);
        dialog.setCancelable(false);
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
        dsViTien = SQLViTien.getAllWallet(getActivity());
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
<<<<<<< HEAD
        String loaiGhiChep = "thutien";

        String thuTuAi = edtThuTuAi.getText().toString();
        int idViTienThu = idViTien;
        int trangThai = 0;
=======
        String loaiGhiChep = "thu";

        String thuTuAi = edtThuTuAi.getText().toString();
        int idViTienThu = idViTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

        if (idHangMuc == 0 || edtSoTien.getText().toString().isEmpty() || idViTienThu == 0) {
            Toast.makeText(getActivity(), "Hạng mục hoặc số tiền hoặc ví tiền không được để trống!", Toast.LENGTH_SHORT).show();
        } else {
            int soTien = Integer.parseInt(edtSoTien.getText().toString());

            SQLiteDatabase database = DataBaseManager.initDataBaseQlyThuChi(getActivity());
<<<<<<< HEAD
            ContentValues cv1 = new ContentValues();
            cv1.put("soTien",soTien);
            cv1.put("dienDai",dienGiai);
            cv1.put("username",username);
            cv1.put("ngay",ngay);
            cv1.put("idHangMuc",idHangMuc);
            cv1.put("loaiGhiChep",loaiGhiChep);
            cv1.put("trangThai",trangThai);
            database.insert("GhiChep", null, cv1);
=======
            database.execSQL("Insert into GhiChep(soTien, dienDai, username, ngay, idHangMuc, loaiGhiChep, trangthai) values (" +
                    soTien + ", '" + dienGiai + "', '" + username + "', '" + ngay + "', " + idHangMuc + ", '" + loaiGhiChep + "', "+Layout_TrangChu.NOT_SYNCED_WITH_SERVER+"')");
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

            Cursor cursor = database.rawQuery("select Max(idGhiChep) from GhiChep", null);
            cursor.moveToFirst();
            int idGhiChep = cursor.getInt(0);

<<<<<<< HEAD
            ContentValues cv2 = new ContentValues();
            cv2.put("thuTuAi",thuTuAi);
            cv2.put("idGhiChep",idGhiChep);
            cv2.put("idViTienThu",idViTienThu);
            cv2.put("username",username);
            database.insert("ThuTien", null, cv2);

            Toast.makeText(getActivity(), "Thêm ghi chép thành công!", Toast.LENGTH_SHORT).show();
            database.close();
=======
            database.execSQL("Insert into ThuTien(thuTuAi, idGhiChep, idViTienThu, username) values ('" +
                    thuTuAi + "', " + idGhiChep + ", " + idViTienThu + ", '" + username + "')");

            Toast.makeText(getActivity(), "Thêm ghi chép thành công!", Toast.LENGTH_SHORT).show();
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }
    }

}
