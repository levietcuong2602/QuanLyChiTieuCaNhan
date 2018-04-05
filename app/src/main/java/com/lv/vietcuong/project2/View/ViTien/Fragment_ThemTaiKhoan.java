package com.lv.vietcuong.project2.View.ViTien;

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

import com.lv.vietcuong.project2.Databases.DB_Wallet;
import com.lv.vietcuong.project2.Model.Wallet;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/25/2018.
 */

public class Fragment_ThemTaiKhoan extends Fragment implements View.OnClickListener {
    Button btnSaveWallet, btnHuyThem, btnSaveTaiKhoan_tb, btnGhiChu;
    EditText edName, edBalance;
    FragmentManager manager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themtaikhoan, container, false);

        btnSaveWallet = view.findViewById(R.id.btnLuuTaiKhoan);
        btnHuyThem = view.findViewById(R.id.btnHuyLuuTaiKhoan);
        edBalance = view.findViewById(R.id.edSoDu);
        edName = view.findViewById(R.id.edTenViTien);
        btnSaveTaiKhoan_tb = view.findViewById(R.id.btnSaveTaiKhoan_tb);
        btnGhiChu = view.findViewById(R.id.btnGhiChu);

        btnSaveWallet.setOnClickListener(this);
        btnHuyThem.setOnClickListener(this);
        btnSaveTaiKhoan_tb.setOnClickListener(this);
        btnGhiChu.setOnClickListener(this);

        manager = getActivity().getSupportFragmentManager();
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLuuTaiKhoan:
            case R.id.btnSaveTaiKhoan_tb:
                saveWallet();
                break;
            case R.id.btnHuyLuuTaiKhoan:
                cancellSaveWallet();
                break;
            case R.id.btnGhiChu:
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment_GhiChu ghiChu = new Fragment_GhiChu();
                transaction.replace(R.id.content_layout, ghiChu);
                transaction.addToBackStack("themtaikhoan");
                transaction.commit();

                Toast.makeText(getActivity(), "ghi chú", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void cancellSaveWallet() {
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
            Wallet wallet = new Wallet(name, new Double(balance));
            long result = DB_Wallet.addWallet(getActivity(), wallet);
            if (result > 0) {
                edName.setText("");
                edBalance.setText("");

                Toast.makeText(getActivity(), "Lưu ví tiền thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
