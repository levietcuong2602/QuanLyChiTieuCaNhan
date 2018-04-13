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

public class Fragment_ListHangMucChi extends Fragment implements AdapterView.OnItemLongClickListener {
    ExpandableListView expandableListView;
    List<String> listHeader;
    HashMap<String, List<String>> listItem;
    ExpandableListViewAdapter adapter;

    ListView lvHangMucChi;
    AdapterHangMucThuChi adapterHangMucThuChi;
    ArrayList<HangMuc> dsHangMuc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dshangmuc_chi, container, false);
//        createExpandableList(view);
        createListViewHangMucChi(view);
        return view;
    }

    private void createListViewHangMucChi(View view) {
        lvHangMucChi = view.findViewById(R.id.lvHangMuc);
        String loaiHangMuc = "chi";

        dsHangMuc = SQLHangMuc.getAllHangMuc(getActivity(), loaiHangMuc);

//        dsHangMuc = new ArrayList<>();
//        HangMuc hangMuc = new HangMuc();
//        hangMuc.setTenHangMuc("Ăn uống");
//        dsHangMuc.add(hangMuc);

        adapterHangMucThuChi = new AdapterHangMucThuChi(getContext(), R.layout.item_hangmucchi_2, dsHangMuc);
        lvHangMucChi.setAdapter(adapterHangMucThuChi);

        lvHangMucChi.setOnItemLongClickListener(this);
    }

    private void createListDataExpandable() {
        listHeader = new ArrayList<>();
        listItem = new HashMap<>();

        listHeader.add("Ăn uống");
        listHeader.add("Con cái");
        listHeader.add("Đi lại");
        listHeader.add("Dịch vụ sinh hoạt");
        listHeader.add("Hiếu hỉ");
        listHeader.add("Hưởng thụ");

        List<String> listAnUong = new ArrayList<>();
        listAnUong.add("Ăn tiệm");
        listAnUong.add("Cafe");
        listAnUong.add("Đi chợ");

        List<String> listConCai = new ArrayList<>();
        listConCai.add("Đồ chơi");
        listConCai.add("Học phí");
        listConCai.add("Sách vở");
        listConCai.add("Sữa");
        listConCai.add("Tiền tiêu vặt");

        List<String> listDiLai = new ArrayList<>();
        List<String> listDichVu = new ArrayList<>();
        List<String> listHieuHi = new ArrayList<>();
        List<String> listHuongThu = new ArrayList<>();

        listItem.put(listHeader.get(0), listAnUong);
        listItem.put(listHeader.get(1), listConCai);
        listItem.put(listHeader.get(2), listDiLai);
        listItem.put(listHeader.get(3), listDichVu);
        listItem.put(listHeader.get(4), listHieuHi);
        listItem.put(listHeader.get(5), listHuongThu);
    }

    private void createExpandableList(View view) {
        expandableListView = view.findViewById(R.id.expandedHangMuc);

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
            dsHangMuc.addAll(SQLHangMuc.getAllHangMuc(getActivity(), "chi"));

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
            lvHangMucChi.setAdapter(adapterHangMucThuChi);
        }else {
            adapterHangMucThuChi.notifyDataSetChanged();
            lvHangMucChi.setSelection(adapterHangMucThuChi.getCount()-1);
        }
    }
}
