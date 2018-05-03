package com.lv.vietcuong.project2.View.BaoCao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class FragmentTaichinhHientai extends Fragment {
    private TextView tvTongSoTien;
    private ListView lvViTien;
    private ArrayList<ViTien> dsViTien;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taichinh_hientai,container,false);
        initWidget(view);

        return view;
    }

    private void initWidget(View v){
        tvTongSoTien = (TextView) v.findViewById(R.id.tv_tong_sotien);
        lvViTien = (ListView) v.findViewById(R.id.lv_vitien);
    }

    private void getAllViTien(){

    }


}
