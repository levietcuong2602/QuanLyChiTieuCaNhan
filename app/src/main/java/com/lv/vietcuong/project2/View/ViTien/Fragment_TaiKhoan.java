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

public class Fragment_TaiKhoan extends Fragment implements View.OnClickListener {
    Button btnSaveWallet, btnHuyThem;
    EditText edName, edBalance;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan, container, false);

        btnSaveWallet = view.findViewById(R.id.btnLuwViTien);
        btnHuyThem = view.findViewById(R.id.btnHuyLuuViTien);
        edBalance = view.findViewById(R.id.edSoDu);
        edName = view.findViewById(R.id.edTenViTien);

        btnSaveWallet.setOnClickListener(this);
        btnHuyThem.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLuwViTien:
                saveWallet();
                break;
            case R.id.btnHuyLuuViTien:
                cancellSaveWallet();
                break;
        }

    }

    private void cancellSaveWallet() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
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
