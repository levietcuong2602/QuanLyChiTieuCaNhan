package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.lv.vietcuong.project2.Adapter.ExpandableListViewAdapter;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_ListHangMucThu extends Fragment{
    ExpandableListView expandableListView;
    List<HangMuc> listHeader;
    HashMap<Integer, List<HangMuc>> listItem;
    ExpandableListViewAdapter adapter;

    boolean checkExpandable = false;

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
        listHeader = SQLHangMuc.getHangMucCha(getActivity(), "thu");

        for (int i = 0; i < listHeader.size(); i++) {
            HangMuc hangMucCha = listHeader.get(i);
            listItem.put(hangMucCha.getIdHangMuc(), SQLHangMuc.getHangMucCon(getActivity(), hangMucCha.getLoaiHangMuc(), hangMucCha.getIdHangMuc()));
        }
    }

    private void createExpandableList(View view) {
        expandableListView = view.findViewById(R.id.expandedHangMucThu);

        createListDataExpandable();
        adapter = new ExpandableListViewAdapter(getActivity(), listHeader, listItem, false);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int group, int child, long l) {

                if (checkExpandable) {
                    Fragment_SuaHangMuc suaHangMuc = new Fragment_SuaHangMuc();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_layout, suaHangMuc);
                    transaction.addToBackStack(null);

                    HangMuc hangMuc = (HangMuc) adapter.getChild(group, child);
                    writeHangMucSharePreference(hangMuc);

                    transaction.commit();
                }else {
                    if (view.isSelected()){
                        view.setSelected(false);
                    }else {
                        view.setSelected(true);
                    }
                }

                return false;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (checkExpandable) {
                    Fragment_SuaHangMuc suaHangMuc = new Fragment_SuaHangMuc();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_layout, suaHangMuc);
                    transaction.addToBackStack(null);

                    HangMuc hangMuc = (HangMuc) adapter.getGroup(i);
                    writeHangMucSharePreference(hangMuc);
                    transaction.commit();
                }else {
                    if (view.isSelected()){
                        view.setSelected(false);
                    }else {
                        view.setSelected(true);
                    }
                }
                return false;
            }
        });

        expandableListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getX() > 60) {
                    checkExpandable = true;
                }else{
                    checkExpandable = false;
                    if (view.isSelected()){
                        view.setSelected(false);
                    }else {
                        view.setSelected(true);
                    }
                }
                return false;
            }
        });

    }

    public void writeHangMucSharePreference(HangMuc hangMuc){
        SharedPreferences preferences = getActivity().getSharedPreferences("hangmuc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("idHangMuc", hangMuc.getIdHangMuc());
        editor.putString("tenHangMuc", hangMuc.getTenHangMuc());
        editor.putString("dienGiai", hangMuc.getDienDai());
        editor.putString("loaiHangMuc", hangMuc.getLoaiHangMuc());
        editor.putInt("idHinhAnh", hangMuc.getIdHinhAnh());
        editor.putInt("idHangMucCha", hangMuc.getIdHangMucCha());
        editor.commit();
    }
}
