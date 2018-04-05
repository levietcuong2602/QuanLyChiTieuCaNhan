package com.lv.vietcuong.project2.View.ViTien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lv.vietcuong.project2.R;

public class Fragment_GhiChu extends Fragment implements View.OnClickListener {

    Button btnHuy, btnLuuGhiChu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghichu, container, false);

        btnHuy = view.findViewById(R.id.btnHuyGhiChu);
        btnLuuGhiChu = view.findViewById(R.id.btnLuuGhiChu);

        btnHuy.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnHuyGhiChu:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnLuuGhiChu:
                break;
        }
    }
}
