package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Switch;

import com.lv.vietcuong.project2.Adapter.ExpandableListViewAdapter;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_ChonHangMucChi extends Fragment {
    ExpandableListView expandableListView;
    ExpandableListViewAdapter adapter;
    ArrayList<HangMuc> header;
    HashMap<Integer, List<HangMuc>> item;
    Switch switchAll;

    boolean checkExpandable = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chon_dshangmuc, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        expandableListView = view.findViewById(R.id.dsHangMuc);
        switchAll = view.findViewById(R.id.switchHangMuc);

        header = new ArrayList<>();
        item = new HashMap<>();

        header = SQLHangMuc.getHangMucCha(getActivity(), "chitien");
        for (int i = 0; i < header.size(); i++) {
            HangMuc hangMucCha = header.get(i);
            item.put(hangMucCha.getIdHangMuc(), SQLHangMuc.getHangMucCon(getActivity(), hangMucCha.getLoaiHangMuc(), hangMucCha.getIdHangMuc()));
        }
        adapter = new ExpandableListViewAdapter(getActivity(), header, item, true);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                CheckedTextView checkedTextView = v.findViewById(R.id.checkboxParent);

                if (checkedTextView.isChecked()) {
                    checkedTextView.setChecked(false);
                } else {
                    checkedTextView.setChecked(true);
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                CheckedTextView checkedTextView = v.findViewById(R.id.checkboxChild);

                if (checkedTextView.isChecked()) {
                    checkedTextView.setChecked(false);
                } else {
                    checkedTextView.setChecked(true);
                }

                return false;
            }
        });
    }
}
