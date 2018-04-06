package com.lv.vietcuong.project2.View.ViTien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Adapter.ListViTienAdapter;
import com.lv.vietcuong.project2.Databases.SQLWallet;
import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_DanhSachTaiKhoan extends Fragment {
    ListView listTaiKhoan;
    ArrayList<ViTien> dsViTien;
    TextView textViewTongTien;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_danhsach_taikhoan, container, false);
        listTaiKhoan = view.findViewById(R.id.listViTien);
        textViewTongTien = view.findViewById(R.id.textViewTong);

        dsViTien = createDanhSachViTien();
        ListViTienAdapter adapter = new ListViTienAdapter(getActivity(), R.layout.item_list_vitien, dsViTien);
        adapter.notifyDataSetChanged();
        listTaiKhoan.setAdapter(adapter);

        setTexViewTongTien();

        return view;
    }

    private void setTexViewTongTien() {
        int tong = 0;
        for(int i = 0; i < dsViTien.size(); i++){
            ViTien wallet = dsViTien.get(i);
            tong += wallet.getBalance();
        }

        textViewTongTien.setText(tong+"");
    }

    private ArrayList<ViTien> createDanhSachViTien() {
        ArrayList<ViTien> dsViTien = SQLWallet.getAllWallet(getActivity());

//        ArrayList dsViTien = new ArrayList();
//        dsViTien.add(new ViTien("Ví tiền 2", 100000));
//        dsViTien.add(new ViTien("Ví tiền 3", 500000));
//        dsViTien.add(new ViTien("Ví tiền 4", 100000));

        return dsViTien;
    }

}
