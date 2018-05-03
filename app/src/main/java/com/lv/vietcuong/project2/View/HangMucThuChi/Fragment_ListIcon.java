package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Adapter.AdapterGridView;
import com.lv.vietcuong.project2.Adapter.AdapterHinhAnh;
import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
import com.lv.vietcuong.project2.Model.ObjectClass.HinhAnh;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_ListIcon extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    TextView tvBack;
    GridView gridView;
    ArrayList<HinhAnh> dsIcon;
    AdapterGridView adapterHinhAnh;

    public String mode_dsIcon = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ds_icon, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        tvBack = view.findViewById(R.id.tvBack);
        tvBack.setOnClickListener(this);

        gridView = view.findViewById(R.id.gridView);

        if(mode_dsIcon.equals("hangmucchi")) {
            dsIcon = SQLHinhAnh.getAllHinhAnhType(getActivity(), "chi");
        }else if (mode_dsIcon.equals("hangmucthu")){
            dsIcon = SQLHinhAnh.getAllHinhAnhType(getActivity(), "thu");
        }

        adapterHinhAnh = new AdapterGridView(getContext(), R.layout.item_gridview_icon, dsIcon);
        adapterHinhAnh.notifyDataSetChanged();

        gridView.setAdapter(adapterHinhAnh);
        gridView.setOnItemClickListener(this);
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
        HinhAnh hinhAnh = dsIcon.get(i);

        if (mode_dsIcon.equals("suahangmuc")){
            SharedPreferences preferences = getActivity().getSharedPreferences("hangmuc", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("idHinhAnh", hinhAnh.getIdHinhAnh());
            editor.commit();
        }else if (mode_dsIcon.equals("hangmucthu") || mode_dsIcon.equals("hangmucchi")){
            Fragment_ThemHangMuc.idIcon = hinhAnh.getIdHinhAnh();
        }

        getActivity().getSupportFragmentManager().popBackStack();
    }
}
