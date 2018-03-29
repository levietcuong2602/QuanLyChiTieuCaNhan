package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.lv.vietcuong.project2.Adapter.ListLimitAdapter;
import com.lv.vietcuong.project2.Model.HanMucChi;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class HanMucChiActivity extends Fragment implements View.OnClickListener {

    ListView listView;
    ArrayList<HanMucChi> arrayHanMucChi;
    Button btnThemHangMucChi;
    Button btnDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_hanmucchi, container, false);
        listView = view.findViewById(R.id.listViewHanMucChi);
        btnThemHangMucChi = view.findViewById(R.id.btnThemHanMucChi);

        arrayHanMucChi = new ArrayList<>();
        arrayHanMucChi.add(new HanMucChi("Tiền ăn", 500000));
        arrayHanMucChi.add(new HanMucChi("Tiền tiêu vặt", 600000));
        ListLimitAdapter adapter = new ListLimitAdapter(getContext(), R.layout.item_list_hanmucchi, arrayHanMucChi);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        btnThemHangMucChi.setOnClickListener(this);

        btnDialog = view.findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        return view;
    }


    @Override
    public void onClick(View view) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment_ThemHangMucChi themHangMucChi = new Fragment_ThemHangMucChi();
        transaction.replace(R.id.content_layout, themHangMucChi);
        transaction.commit();
    }
}
