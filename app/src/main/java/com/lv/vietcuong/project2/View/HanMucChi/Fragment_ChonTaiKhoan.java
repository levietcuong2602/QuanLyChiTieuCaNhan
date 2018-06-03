package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
<<<<<<< HEAD
=======
import android.widget.Switch;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

import com.lv.vietcuong.project2.Adapter.ListAdapterChonViTien;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class Fragment_ChonTaiKhoan extends Fragment implements View.OnClickListener {
    ListView listView;
<<<<<<< HEAD
    Button btnBack, btnXong;
=======
    ListAdapterChonViTien adapter;
    Button btnBack, btnXong;
    Switch aSwitch;

    ArrayList<ViTien> dsViTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chon_dstaikhoan, container, false);

<<<<<<< HEAD
        btnBack = view.findViewById(R.id.btnBack);
        btnXong = view.findViewById(R.id.btnXong);

        listView = view.findViewById(R.id.dsTaiKhoan);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayList<ViTien> dsViTien = SQLViTien.getAllWallet(getActivity());

        ListAdapterChonViTien adapter = new ListAdapterChonViTien(getContext(), R.layout.item_chon_dstaikhoan, dsViTien);
=======
        initView(view);

        return view;
    }

    private void initView(View view) {
        btnBack = view.findViewById(R.id.btnBack);
        btnXong = view.findViewById(R.id.btnXong);
        aSwitch = view.findViewById(R.id.switchTK);

        listView = view.findViewById(R.id.dsTaiKhoan);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        dsViTien = SQLViTien.getAllWallet(getActivity());

        adapter = new ListAdapterChonViTien(getContext(), R.layout.item_chon_dstaikhoan, dsViTien);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = view.findViewById(R.id.tenViTien);
                if (checkedTextView.isChecked()){
                    checkedTextView.setChecked(false);
<<<<<<< HEAD
                }else {
                    checkedTextView.setChecked(true);
                }
=======
                    aSwitch.setChecked(false);
                    adapter.check[i] = false;
                }else {
                    checkedTextView.setChecked(true);
                    adapter.check[i] = true;

                    //kiểm tra ds tai khoản
                    boolean check = false;
                    for (int index = 0; index < dsViTien.size(); index++){
//                        CheckedTextView checkedText = listView.getChildAt(i).findViewById(R.id.tenViTien);
                        if (adapter.check[index]){
                            check = true;
                        }else{
                            check = false;
                            break;
                        }

                    }
                    adapter.notifyDataSetChanged();
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
                    adapter.check[i] = check;
                }
                adapter.notifyDataSetChanged();

>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
            }
        });

        btnXong.setOnClickListener(this);
        btnBack.setOnClickListener(this);
<<<<<<< HEAD

        return view;
=======
//        aSwitch.setOnClickListener(this);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnBack:
<<<<<<< HEAD
            case R.id.btnXong:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
=======
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnXong:
                ArrayList<Integer> dsId = new ArrayList<>();
                for (int i = 0; i < dsViTien.size(); i++){
                    if (adapter.check[i]){
                        dsId.add(dsViTien.get(i).getIdViTien());
                    }
                }

                Fragment_ThemHanMucChi.dsIdViTien = new int[dsId.size()];
                for (int i = 0; i < dsId.size(); i++){
                    Fragment_ThemHanMucChi.dsIdViTien[i] = dsId.get(i);
                }

                getActivity().getSupportFragmentManager().popBackStack();
                break;

>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }
    }
}
