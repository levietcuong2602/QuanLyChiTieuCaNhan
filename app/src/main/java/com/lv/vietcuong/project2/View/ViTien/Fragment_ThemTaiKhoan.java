package com.lv.vietcuong.project2.View.ViTien;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
=======
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.HangMucThuChi.Fragment_DSHangMucCha;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

/**
 * Created by Administor on 3/25/2018.
 */

public class Fragment_ThemTaiKhoan extends Fragment implements View.OnClickListener {
<<<<<<< HEAD
    Button btnSaveWallet, btnHuyThem, btnSaveTaiKhoan_tb, btnGhiChu, btnNoiDungGhiChu;
    EditText edName, edBalance;
    FragmentManager manager;

    static String ghiChu;
=======
    private Button btnSaveWallet, btnHuyThem, btnSaveTaiKhoan_tb, btnGhiChu, btnNoiDungGhiChu;
    private EditText edName, edBalance;
    private FragmentManager manager;
    private TextView tvTitle;
    private Button btnHangMucThu;

    public static String ghiChu;
    public static int idHangMucThu = -1;
    public ViTien viTien = null;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themtaikhoan, container, false);
        initView(view);
        manager = getActivity().getSupportFragmentManager();
        return view;
    }

    public void initView(View view){
        btnSaveWallet = view.findViewById(R.id.btnLuuTaiKhoan);
        btnHuyThem = view.findViewById(R.id.btnHuyLuuTaiKhoan);
        edBalance = view.findViewById(R.id.edSoDu);
        edName = view.findViewById(R.id.edTenViTien);
        btnSaveTaiKhoan_tb = view.findViewById(R.id.btnSaveTaiKhoan_tb);
        btnGhiChu = view.findViewById(R.id.btnGhiChu);
        btnNoiDungGhiChu = view.findViewById(R.id.btnNoiDungGhiChu);
<<<<<<< HEAD
=======
        tvTitle = view.findViewById(R.id.tvTitle);
        btnHangMucThu = view.findViewById(R.id.btnHangMucThu);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

        btnSaveWallet.setOnClickListener(this);
        btnHuyThem.setOnClickListener(this);
        btnSaveTaiKhoan_tb.setOnClickListener(this);
        btnGhiChu.setOnClickListener(this);
        btnNoiDungGhiChu.setOnClickListener(this);
<<<<<<< HEAD

        btnNoiDungGhiChu.setText(ghiChu);
=======
        btnHangMucThu.setOnClickListener(this);

        btnNoiDungGhiChu.setText(ghiChu);

        if (idHangMucThu != -1){
            HangMuc hangMuc = SQLHangMuc.getHangMuc(getActivity(), "thu", idHangMucThu);
            btnHangMucThu.setText(hangMuc.getTenHangMuc());
        }

        if (viTien != null){
            edName.setText(viTien.getTenViTien());
            edBalance.setText(viTien.getSoDu()+"");

            String tenHangMuc = SQLHangMuc.getTenHangMuc(getActivity(), viTien.getIdHangMucThu());
            btnHangMucThu.setText(tenHangMuc);
            ghiChu = viTien.getGhiChu();
        }
    }

    public ViTien getViTien() {
        return viTien;
    }

    public void setViTien(ViTien viTien) {
        this.viTien = viTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLuuTaiKhoan:
            case R.id.btnSaveTaiKhoan_tb:
                saveWallet();
                break;
            case R.id.btnHuyLuuTaiKhoan:
                noSaveWallet();
                break;
            case R.id.btnGhiChu:
            case R.id.btnNoiDungGhiChu:
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment_GhiChu ghiChu = new Fragment_GhiChu();
                transaction.replace(R.id.content_layout, ghiChu);
                transaction.addToBackStack("themtaikhoan");
                transaction.commit();

                break;
<<<<<<< HEAD
=======
            case R.id.btnHangMucThu:
                Fragment_DSHangMucCha hangMucCha = new Fragment_DSHangMucCha();
                hangMucCha.loaiHangMuc = "thu";
                hangMucCha.mode_hangmuccha = "dshangmucthu";

                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.content_layout, hangMucCha);
                transaction1.addToBackStack(null);
                transaction1.commit();
                break;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }

    }

    private void noSaveWallet() {
        FragmentTransaction transTaiKhoan = manager.beginTransaction();
        TaiKhoanActivity taiKhoanActivity = new TaiKhoanActivity();
        transTaiKhoan.replace(R.id.content_layout, taiKhoanActivity);
        transTaiKhoan.commit();
    }

    public void saveWallet(){
        String name = edName.getText().toString();
        String balance = edBalance.getText().toString();

        if (!balance.matches("[0-9]+")){
            Toast.makeText(getActivity(), "Số tài khoản không hợp lệ", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======
        }else if(idHangMucThu == -1){
            Toast.makeText(getActivity(), "Bạn cần chọn hạng mục thu", Toast.LENGTH_SHORT).show();
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }else {
            ViTien wallet = new ViTien();

            wallet.setTenViTien(name);
            wallet.setSoDu(Integer.parseInt(balance));
            wallet.setLoaiVi("ViTien");
            wallet.setGhiChu(ghiChu);
<<<<<<< HEAD
=======
            wallet.setTrangthai(Layout_TrangChu.NOT_SYNCED_WITH_SERVER);
            wallet.setUsername(Layout_TrangChu.taiKhoanDangNhap.getUsername());
            wallet.setIdHangMucThu(idHangMucThu);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

            long result = SQLViTien.addViTien(getActivity(), wallet);
            if (result > 0) {
                edName.setText("");
                edBalance.setText("");

                Toast.makeText(getActivity(), "Lưu ví tiền thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(), "Thêm ví tiền thaất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private long addWalletDataBase(ViTien wallet) {
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        ContentValues values = new ContentValues();
        values.put("tenViTien", wallet.getTenViTien());
        values.put("loaiViTien", wallet.getLoaiVi());
        values.put("soDu", wallet.getSoDu());
        values.put("ghiChu", wallet.getGhiChu());
        values.put("username", wallet.getUsername());

        return db.insert("ViTien", null, values);
    }
}
