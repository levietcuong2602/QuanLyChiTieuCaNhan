package com.lv.vietcuong.project2.View.GhiChep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ThuTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterGhiChep;

import java.util.ArrayList;

public class FragmentDaGhi extends Fragment {
    private Button btnBack;
    ArrayList<GhiChep> dsGhiChep;
    AdapterGhiChep adapterGhiChep;
    private ListView lvGhiChep;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daghi_activity,container,false);

        initWidget(view);
        dsGhiChep = SQLGhiChep.getAllGhiChep(getActivity());
        setAdapterGhiChep();
        setEvent();

        return view;
    }

    private void initWidget(View v){
        btnBack = (Button) v.findViewById(R.id.btn_back);
        lvGhiChep = (ListView) v.findViewById(R.id.lv_ghichep);
    }


    private void setAdapterGhiChep(){
        if (adapterGhiChep == null){
            adapterGhiChep = new AdapterGhiChep(getActivity(), R.layout.item_list_ghichep,dsGhiChep,getActivity());
            lvGhiChep.setAdapter(adapterGhiChep);
        }else {
            adapterGhiChep.notifyDataSetChanged();
            lvGhiChep.setSelection(adapterGhiChep.getCount()-1);
        }
    }

    private void setEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.replace(R.id.content_layout, new GhiChepActivity(), getActivity());
            }
        });

        lvGhiChep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GhiChep ghiChep = dsGhiChep.get(i);
                int idGhiChep = ghiChep.getIdGhiChep();

                if (ghiChep instanceof ChiTien) {
                    FragmentEditChitien fragmentEditChitien = new FragmentEditChitien();
                    Bundle bundle = new Bundle();
                    bundle.putInt("idGhiChep",idGhiChep);
                    fragmentEditChitien.setArguments(bundle);
                    Util.replace(R.id.content_layout,fragmentEditChitien,getActivity());
                }

                if (ghiChep instanceof ThuTien) {
                    FragmentEditThutien fragmentEditThutien = new FragmentEditThutien();
                    Bundle bundle = new Bundle();
                    bundle.putInt("idGhiChep",idGhiChep);
                    fragmentEditThutien.setArguments(bundle);
                    Util.replace(R.id.content_layout,fragmentEditThutien,getActivity());
                }

                if (ghiChep instanceof ChuyenKhoan) {
                    FragmentEditChuyenkhoan fragmentEditChuyenkhoan = new FragmentEditChuyenkhoan();
                    Bundle bundle = new Bundle();
                    bundle.putInt("idGhiChep",idGhiChep);
                    fragmentEditChuyenkhoan.setArguments(bundle);
                    Util.replace(R.id.content_layout,fragmentEditChuyenkhoan,getActivity());
                }
            }
        });

    }

}
