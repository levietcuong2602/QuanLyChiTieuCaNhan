package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.AdapterHangMucThuChi;
import com.lv.vietcuong.project2.Adapter.ExpandableListViewAdapter;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Model.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_ListHangMucThu extends Fragment implements AdapterView.OnItemLongClickListener {
    ExpandableListView expandableListView;
    List<String> listHeader;
    HashMap<String, List<String>> listItem;
    ExpandableListViewAdapter adapter;

    ListView lvHangMucThu;
    AdapterHangMucThuChi adapterHangMucThuChi;
    ArrayList<HangMuc> dsHangMuc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dshangmuc_thu, container, false);
//        createExpandableList(view);
        createListViewHMThu(view);
        return view;
    }

    private void createListViewHMThu(View view) {
        lvHangMucThu = view.findViewById(R.id.lvHangMucThu);
        String loaiHangMuc = "thu";
        dsHangMuc = SQLHangMuc.getAllHangMuc(getActivity(), loaiHangMuc);

//        dsHangMuc = new ArrayList<>();
//        HangMuc hangMuc = new HangMuc();
//        hangMuc.setTenHangMuc("Ăn uống");
//        dsHangMuc.add(hangMuc);

        adapterHangMucThuChi = new AdapterHangMucThuChi(getContext(), R.layout.item_hangmucchi_2, dsHangMuc);
        lvHangMucThu.setAdapter(adapterHangMucThuChi);

        lvHangMucThu.setOnItemLongClickListener(this);
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

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        HangMuc hangMuc = adapterHangMucThuChi.getItem(i);
        long result = SQLHangMuc.deleteHangMuc(getActivity(), hangMuc.getIdHangMuc());
        if (result > 0){
            Toast.makeText(getContext(), "xóa hạng mục chi thành công", Toast.LENGTH_SHORT).show();
            dsHangMuc.clear();
            dsHangMuc.addAll(SQLHangMuc.getAllHangMuc(getActivity(), "thu"));
            setAdapter();
        }else {
            Toast.makeText(getContext(), "xóa hạng mục chi thất bại", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public void setAdapter(){
        if (adapterHangMucThuChi == null){
            adapterHangMucThuChi = new AdapterHangMucThuChi(getContext(), R.layout.item_hangmucchi_2, dsHangMuc);
            adapterHangMucThuChi.notifyDataSetChanged();
            lvHangMucThu.setAdapter(adapterHangMucThuChi);
        }else {
            adapterHangMucThuChi.notifyDataSetChanged();
            lvHangMucThu.setSelection(adapterHangMucThuChi.getCount()-1);
        }
    }
}
