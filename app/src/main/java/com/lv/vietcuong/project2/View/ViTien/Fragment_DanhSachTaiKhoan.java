package com.lv.vietcuong.project2.View.ViTien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.ListViTienAdapter;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_DanhSachTaiKhoan extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView listTaiKhoan;
    ArrayList<ViTien> dsViTien;
    TextView textViewTongTien;
    ListViTienAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_danhsach_taikhoan, container, false);

        initView(view);
        initListViewDanhSachViTien(view);

        setTexViewTongTien();

        return view;
    }

    public void initView(View view){
        listTaiKhoan = view.findViewById(R.id.listViTien);
        textViewTongTien = view.findViewById(R.id.textViewTong);
    }


    private void initListViewDanhSachViTien(View view) {
        dsViTien = SQLViTien.getAllWallet(getActivity());
        adapter = new ListViTienAdapter(getActivity(), R.layout.item_list_vitien, dsViTien);
        adapter.notifyDataSetChanged();
        listTaiKhoan.setAdapter(adapter);

        listTaiKhoan.setOnItemClickListener(this);
        listTaiKhoan.setOnItemLongClickListener(this);
    }

    private void setTexViewTongTien() {
        int tong = 0;
        for(int i = 0; i < dsViTien.size(); i++){
            ViTien wallet = dsViTien.get(i);
            tong += wallet.getSoDu();
        }

        textViewTongTien.setText(tong+"");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ViTien viTien = (ViTien) adapterView.getItemAtPosition(i);

        Fragment_ThongTinGhiChepViTien thongTinViTien = new Fragment_ThongTinGhiChepViTien();

        Bundle bundle = new Bundle();
        bundle.putSerializable("ViTien", viTien);
        thongTinViTien.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_layout, thongTinViTien);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        ViTien viTien = (ViTien) adapterView.getItemAtPosition(i);
        long result = SQLViTien.deleteViTien(getActivity(), viTien.getIdViTien());

        if (result > 0){
            Toast.makeText(getContext(), "delete ví tiền thành công", Toast.LENGTH_SHORT).show();

            dsViTien.clear();
            dsViTien.addAll(SQLViTien.getAllWallet(getActivity()));
            adapter.notifyDataSetChanged();
            setAdapter();
        }else {
            Toast.makeText(getContext(), "delete ví tiền không thành công", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void setAdapter(){
        if (adapter == null){
            adapter = new ListViTienAdapter(getActivity(), R.layout.item_list_vitien, dsViTien);
            adapter.notifyDataSetChanged();
            listTaiKhoan.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
            listTaiKhoan.setSelection(adapter.getCount()-1);
        }
    }
}
