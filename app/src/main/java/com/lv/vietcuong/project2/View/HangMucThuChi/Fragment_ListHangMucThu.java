package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.ExpandableListViewAdapter;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_ListHangMucThu extends Fragment {
    ExpandableListView expandableListView;
    List<String> listHeader;
    HashMap<String, List<String>> listItem;
    ExpandableListViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dshangmuc_thu, container, false);
        createExpandableList(view);

        return view;
    }

    private void createListDataExpandable() {
        listHeader = new ArrayList<>();
        listItem = new HashMap<>();

        listHeader.add("Được cho/tặng");
        listHeader.add("khác");
        listHeader.add("Lương");
        listHeader.add("Thưởng");
        listHeader.add("Tiền lãi");


        List<String> listTang = new ArrayList<>();
        List<String> listKhac = new ArrayList<>();
        List<String> listLuong = new ArrayList<>();
        List<String> listTienLai = new ArrayList<>();
        List<String> listThuong = new ArrayList<>();

        listItem.put(listHeader.get(0), listTang);
        listItem.put(listHeader.get(1), listKhac);
        listItem.put(listHeader.get(2), listLuong);
        listItem.put(listHeader.get(3), listThuong);
        listItem.put(listHeader.get(4), listTienLai);
    }

    private void createExpandableList(View view) {
        expandableListView = view.findViewById(R.id.expandedHangMucThu);

        createListDataExpandable();
        adapter = new ExpandableListViewAdapter(getContext(), listHeader, listItem);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getActivity(), ""+listItem.get(listHeader.get(i)).get(i1), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
