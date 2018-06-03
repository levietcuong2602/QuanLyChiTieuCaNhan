package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.Switch;

import com.lv.vietcuong.project2.Adapter.ExpandableListViewAdapter;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_ChonHangMucChi extends Fragment implements View.OnClickListener {
    ExpandableListView expandableListView;
    ExpandableListViewAdapter adapter;
    ArrayList<HangMuc> header;
    HashMap<Integer, List<HangMuc>> item;
    Switch switchAll;
    Button btnBack;
    Button btnXong;

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
        btnBack = view.findViewById(R.id.btnBack);
        btnXong = view.findViewById(R.id.btnXong);

        switchAll.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnXong.setOnClickListener(this);

        header = new ArrayList<>();
        item = new HashMap<>();

        header = SQLHangMuc.getHangMucCha(getActivity(), "chi");
        for (int i = 0; i < header.size(); i++) {
            HangMuc hangMucCha = header.get(i);
            item.put(hangMucCha.getIdHangMuc(), SQLHangMuc.getHangMucCon(getActivity(), hangMucCha.getLoaiHangMuc(), hangMucCha.getIdHangMuc()));
        }
        adapter = new ExpandableListViewAdapter(getActivity(), header, item, true);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, final int groupPosition, long id) {

                final CheckedTextView checkedTextView = v.findViewById(R.id.checkboxParent);
                checkedTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (checkedTextView.isChecked()) {
                            checkedTextView.setChecked(false);
                            adapter.checkGroup[groupPosition] = false;

                        } else {
                            checkedTextView.setChecked(true);
                            adapter.checkGroup[groupPosition] = true;
                        }

                        //set all item
                        for (int i = 0; i < item.get(header.get(groupPosition).getIdHangMuc()).size(); i++){
                            adapter.checkChild[groupPosition][i] = adapter.checkGroup[groupPosition];
                        }
                        adapter.notifyDataSetChanged();

                        //set switch
                       checkGroup();
                    }
                });


                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {
                final CheckedTextView checkedTextView = v.findViewById(R.id.checkboxChild);
                checkedTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkedTextView.isChecked()) {
                            checkedTextView.setChecked(false);
                            adapter.checkChild[groupPosition][childPosition] = false;
                            adapter.checkGroup[groupPosition] = false;
                        } else {
                            checkedTextView.setChecked(true);
                            adapter.checkChild[groupPosition][childPosition] = true;
                        }

                        boolean flag = false;
                        for (int i = 0; i < item.get(header.get(groupPosition).getIdHangMuc()).size(); i++){
                            if (adapter.checkChild[groupPosition][i]){
                                flag = true;
                            }else{
                                flag = false;
                                break;
                            }
                        }
                        adapter.checkGroup[groupPosition] = flag;
                        adapter.notifyDataSetChanged();

                        checkGroup();
                    }
                });



                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.switchHangMuc:
                //set checkbox group
                boolean check = switchAll.isChecked();
                for (int i = 0; i < header.size(); i++) {
                    adapter.checkGroup[i] = check;
                    for(int j = 0; j < item.get(header.get(i).getIdHangMuc()).size(); j++){
                        adapter.checkChild[i][j] = check;
                    }
                }
                adapter.notifyDataSetChanged();

                break;
            case R.id.btnBack:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnXong:
                ArrayList<Integer> dsId = new ArrayList<>();
                for (int i = 0; i < header.size(); i++){
                    if (adapter.checkGroup[i]){
                        dsId.add(header.get(i).getIdHangMuc());
                    }

                    for (int j = 0; j < item.get(header.get(i).getIdHangMuc()).size(); j++){
                        if (adapter.checkChild[i][j]){
                            dsId.add(item.get(header.get(i).getIdHangMuc()).get(j).getIdHangMuc());
                        }
                    }
                }

                Fragment_ThemHanMucChi.dsIdHangMuc = new int[dsId.size()];
                for (int i = 0; i < dsId.size(); i++){
                    Fragment_ThemHanMucChi.dsIdHangMuc[i] = dsId.get(i);
                }

                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }

    public void checkGroup(){
        boolean flag = false;
        for (int i = 0; i < header.size(); i++){
            if (adapter.checkGroup[i]){
                flag = true;
            }else{
                flag = false;
                break;
            }
        }
        switchAll.setChecked(flag);
    }
}
