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
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/25/2018.
 */

public class Fragment_ThemTaiKhoan extends Fragment implements View.OnClickListener {
    Button btnSaveWallet, btnHuyThem, btnSaveTaiKhoan_tb, btnGhiChu, btnNoiDungGhiChu;
    EditText edName, edBalance;
    FragmentManager manager;

    static String ghiChu;

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

        btnSaveWallet.setOnClickListener(this);
        btnHuyThem.setOnClickListener(this);
        btnSaveTaiKhoan_tb.setOnClickListener(this);
        btnGhiChu.setOnClickListener(this);
        btnNoiDungGhiChu.setOnClickListener(this);

        btnNoiDungGhiChu.setText(ghiChu);
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
        }else {
            ViTien wallet = new ViTien();

            wallet.setNameWallet(name);
            wallet.setBalance(Integer.parseInt(balance));
            wallet.setLoaiVi("ViTien");
            wallet.setGhiChu(ghiChu);

            long result = addWalletDataBase(wallet);


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
        values.put("tenViTien", wallet.getNameWallet());
        values.put("loaiViTien", wallet.getLoaiVi());
        values.put("soDu", wallet.getBalance());
        values.put("ghiChu", wallet.getGhiChu());
        values.put("username", wallet.getUsername());

        return db.insert("ViTien", null, values);
    }
}
