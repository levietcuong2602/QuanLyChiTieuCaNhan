package com.lv.vietcuong.project2.View.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/25/2018.
 */

public class Fragment_DoiTenTaiKhoan extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_doitentaikhoan, container, false);
        return view;
    }
}
