package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Switch;

import com.lv.vietcuong.project2.Adapter.ListAdapterChonViTien;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_ChonTaiKhoan extends Fragment implements View.OnClickListener {
    ListView listView;
    Button btnBack, btnXong;
    Switch aSwitch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chon_dstaikhoan, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        btnBack = view.findViewById(R.id.btnBack);
        btnXong = view.findViewById(R.id.btnXong);
        aSwitch = view.findViewById(R.id.switchTK);

        listView = view.findViewById(R.id.dsTaiKhoan);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        final ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());

        ListAdapterChonViTien adapter = new ListAdapterChonViTien(getContext(), R.layout.item_chon_dstaikhoan, dsViTien);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = view.findViewById(R.id.tenViTien);
                if (checkedTextView.isChecked()){
                    checkedTextView.setChecked(false);
                    aSwitch.setChecked(false);
                }else {
                    checkedTextView.setChecked(true);

                    boolean check = true;
                    for (int index = 0; index < dsViTien.size(); index++){
                        CheckedTextView checkedText = listView.getChildAt(i).findViewById(R.id.tenViTien);
                        if (!checkedText.isChecked()){
                            check = false;
                            break;
                        }
                    }
                    aSwitch.setChecked(check);
                }

            }
        });

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = dsViTien.size();
                boolean check = aSwitch.isChecked();
                for (int i = 0; i < count; i++){
                    CheckedTextView checkedTextView = listView.getChildAt(i).findViewById(R.id.tenViTien);
                    checkedTextView.setChecked(check);
                }

            }
        });

        btnXong.setOnClickListener(this);
        btnBack.setOnClickListener(this);
//        aSwitch.setOnClickListener(this);
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
