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
import android.widget.ScrollView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Adapter.AdapterListGhiChep;
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Model.GhiChep;
import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_ThongTinGhiChepViTien extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    Button btnBack, btnThemGhiChep;
    ListView listViewGhiChep;
    AdapterListGhiChep adapterListGhiChep;
    TextView tvNotData, tvSoTienHienTai, tvSoTienBanDau;
    ScrollView scrollView;
    ViTien viTien;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtinvitien, container,false);

        initView(view);
        initListViewGhiChep();
        getViTien();

        return view;
    }

    private void getViTien() {
        Bundle bundle = getArguments();
        if (bundle != null){
            viTien = (ViTien) bundle.getSerializable("ViTien");
            tvSoTienBanDau.setText(viTien.getSoDu()+"");
        }
    }

    private void initListViewGhiChep() {
//        ArrayList<GhiChep> dsGhiChep = SQLGhiChep.getAllGhiChep(getActivity());
        ArrayList<GhiChep> dsGhiChep = new ArrayList<>();
        dsGhiChep.add(new GhiChep());

        if (dsGhiChep.size() == 0){
            tvNotData.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }

        adapterListGhiChep = new AdapterListGhiChep(getContext(), R.layout.item_thongtinghichep,dsGhiChep);
        listViewGhiChep.setAdapter(adapterListGhiChep);
    }

    private void initView(View view) {
        btnBack = view.findViewById(R.id.btnBack);
        btnThemGhiChep = view.findViewById(R.id.btnThemGhiChep);
        listViewGhiChep = view.findViewById(R.id.lstViewGhiChep);
        tvNotData = view.findViewById(R.id.tvKhongCoDuLieu);
        scrollView = view.findViewById(R.id.contentGhiChep);
        tvSoTienBanDau = view.findViewById(R.id.tvSoTienBanDau);
        tvSoTienHienTai = view.findViewById(R.id.tvSoTienHienTai);

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
