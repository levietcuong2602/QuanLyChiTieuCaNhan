package com.lv.vietcuong.project2.View.GhiChep;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administor on 3/26/2018.
 */


public class GhiChepActivity extends Fragment implements View.OnClickListener{
    private Button btnDaGhi;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ghichep,container,false);

        initWidget(view);
        setSpinner();
        btnDaGhi.setOnClickListener(this);
        return view;
    }

    private void initWidget(View v) {
        btnDaGhi = (Button) v.findViewById(R.id.btn_daghi);
        spinner = (Spinner) v.findViewById(R.id.spn_danhmuc);
    }

    private void setSpinner() {
        List<String> list = new ArrayList<>();
        list.add("Chi tiền");
        list.add("Thu tiền");
        list.add("Chuyển khoản");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int id = R.id.fragment_content;

                switch (Integer.parseInt(spinner.getItemIdAtPosition(i)+"")){
                    case 0:
                        Util.replace(id, new FragmentGhichepChitien(), getActivity());
                        break;
                    case 1:
                        Util.replace(id, new FragmentGhiChepThutien(), getActivity());
                        break;
                    case 2:
                        Util.replace(id, new FragmentGhichepChuyenKhoan(), getActivity());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_daghi:{
                Intent intent = new Intent(getActivity(), DaGhiActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
