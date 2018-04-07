package com.lv.vietcuong.project2.View.ViTien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lv.vietcuong.project2.Adapter.AdapterThongTinViTien;
import com.lv.vietcuong.project2.Model.GhiChep;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_ThongTinViTien extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btnBack, btnThemGhiChep;
    ListView listViewGhiChep;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtinvitien, container,false);
        initView(view);
        initListViewGhiChep();
        return view;
    }

    private void initListViewGhiChep() {
        ArrayList<GhiChep> dsGhiChep = new ArrayList<>();

        AdapterThongTinViTien adapter = new AdapterThongTinViTien(getContext(), R.layout.item_thongtinghichep,dsGhiChep);
        listViewGhiChep.setAdapter(adapter);
    }

    private void initView(View view) {
        btnBack = view.findViewById(R.id.btnBack);
        btnThemGhiChep = view.findViewById(R.id.btnThemGhiChep);
        listViewGhiChep = view.findViewById(R.id.lstViewGhiChep);
        
        btnThemGhiChep.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        listViewGhiChep.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnBack:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnThemGhiChep:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        
    }
}
