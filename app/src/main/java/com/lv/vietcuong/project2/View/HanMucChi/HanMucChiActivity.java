package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.lv.vietcuong.project2.Adapter.AdapterHanMucChi;
import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
import com.lv.vietcuong.project2.Model.ObjectClass.HanMucChi;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class HanMucChiActivity extends Fragment implements View.OnClickListener {

    ListView listView, listViewBottom;
    ArrayList<HanMucChi> arrayHanMucChi;
    Button btnThemHanMucChi, btnBottom;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hanmucchi, container, false);

        createListViewHanMuc(view);
        createBottomSheet(view);

        btnThemHanMucChi = view.findViewById(R.id.btnThemHanMucChi);
        btnThemHanMucChi.setOnClickListener(this);

        return view;
    }

    private void createBottomSheet(View view) {
        final View bottomsheet = view.findViewById(R.id.bottomsheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomsheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setPeekHeight(100);

        fab = view.findViewById(R.id.fab);

        btnBottom = bottomsheet.findViewById(R.id.btnButton);
        listViewBottom = bottomsheet.findViewById(R.id.listViewBottom);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        adapter.add("hàng ngày");
        adapter.add("hàng tháng");
        adapter.add("hàng quý");
        adapter.add("hàng năm");
        listViewBottom.setAdapter(adapter);
        listViewBottom.setEnabled(false);

        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                    fab.setImageResource(R.drawable.ic_action_up);
                }

                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    behavior.setPeekHeight(100);

                    fab.setImageResource(R.drawable.ic_action_down);
                }
            }
        });
    }

    private void createListViewHanMuc(View view) {
        listView = view.findViewById(R.id.listViewHanMucChi);
        arrayHanMucChi = SQLHanMucChi.getAllHanMucChi(getActivity());
        AdapterHanMucChi adapter = new AdapterHanMucChi(getContext(), R.layout.item_list_hanmucchi, arrayHanMucChi);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment_ThemHanMucChi themHangMucChi = new Fragment_ThemHanMucChi();
        transaction.replace(R.id.content_layout, themHangMucChi);
        transaction.commit();
    }
}
