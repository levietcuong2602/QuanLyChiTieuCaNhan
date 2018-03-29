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
import android.widget.ListView;

import com.lv.vietcuong.project2.Adapter.ListViTienAdapter;
import com.lv.vietcuong.project2.Databases.DB_Wallet;
import com.lv.vietcuong.project2.Model.Wallet;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

/**
 * Created by Administor on 3/25/2018.
 */

public class TaiKhoanActivity extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_taikhoan, container, false);

        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = new Fragment_TaiKhoan();
        transaction.replace(R.id.content_layout, fragment);
        transaction.addToBackStack("Back");
        transaction.commit();
    }
}
