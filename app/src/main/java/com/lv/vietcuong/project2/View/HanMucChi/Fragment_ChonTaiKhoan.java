package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.ListAdapterChonViTien;
import com.lv.vietcuong.project2.Adapter.ListViTienAdapter;
import com.lv.vietcuong.project2.Model.Wallet;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_ChonTaiKhoan extends Fragment implements View.OnClickListener {
    ListView listView;
    Button btnBack, btnXong;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chon_dstaikhoan, container, false);

        btnBack = view.findViewById(R.id.btnBack);
        btnXong = view.findViewById(R.id.btnXong);

        listView = view.findViewById(R.id.dsTaiKhoan);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayList<Wallet> dsViTien = new ArrayList<>();
        dsViTien.add(new Wallet("Ví tiền 1", 500000));
        dsViTien.add(new Wallet("Ví tiền 2", 100000));
        dsViTien.add(new Wallet("Ví tiền 3", 500000));
        dsViTien.add(new Wallet("Ví tiền 4", 100000));

        ListAdapterChonViTien adapter = new ListAdapterChonViTien(getContext(), R.layout.item_chon_dstaikhoan, dsViTien);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = view.findViewById(R.id.tenViTien);
                if (checkedTextView.isChecked()){
                    checkedTextView.setChecked(false);
                }else {
                    checkedTextView.setChecked(true);
                }
            }
        });

        btnXong.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnBack:
            case R.id.btnXong:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
