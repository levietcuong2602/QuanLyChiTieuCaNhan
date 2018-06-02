package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lv.vietcuong.project2.Adapter.AdapterHangMucThuChi;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_DSHangMucCha extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, CompoundButton.OnCheckedChangeListener {

    TextView tvBack;
    ListView lvHangMucCha;
    AdapterHangMucThuChi adapterHangMucThuChi;
    ArrayList<HangMuc> dsHangMuc;
    RadioButton rdbKhongChon;

    public String mode_hangmuccha = "";
    public String loaiHangMuc = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hangmuccha, container, false);
        initView(view);
        initListViewHangMucCha();
        return view;
    }

    private void initListViewHangMucCha() {
        dsHangMuc = new ArrayList<>();
        dsHangMuc = SQLHangMuc.getHangMucCha(getActivity(), loaiHangMuc);
        adapterHangMucThuChi = new AdapterHangMucThuChi(getActivity(), R.layout.item_hangmucchi_2, dsHangMuc);
        lvHangMucCha.setAdapter(adapterHangMucThuChi);
    }

    private void initView(View view) {
        tvBack = view.findViewById(R.id.tvBack);
        lvHangMucCha = view.findViewById(R.id.lvHangMucCha);
        rdbKhongChon = view.findViewById(R.id.rdbKhongChon);

        tvBack.setOnClickListener(this);
        lvHangMucCha.setOnItemClickListener(this);
        rdbKhongChon.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tvBack:
                getActivity().getSupportFragmentManager().popBackStack();
            break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        HangMuc hangMuc = dsHangMuc.get(i);
        if (mode_hangmuccha.equals("suahangmuc")){
            SharedPreferences preferences = getActivity().getSharedPreferences("hangmuc", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("idHangMucCha", hangMuc.getIdHangMuc());
            editor.commit();

            getActivity().getSupportFragmentManager().popBackStack();
        }else if (mode_hangmuccha.equals("themhangmuc")){
            Fragment_ThemHangMuc.idHangMucCha = hangMuc.getIdHangMuc();
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();

        switch (id){
            case R.id.rdbKhongChon:
                if (mode_hangmuccha.equals("suahangmuc")){
                    getActivity().getSupportFragmentManager().popBackStack();
                }else {
                    Fragment_ThemHangMuc.idHangMucCha = -1;
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                break;
        }
    }
}
