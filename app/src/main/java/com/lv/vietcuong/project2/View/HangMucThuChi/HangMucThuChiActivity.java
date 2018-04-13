package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.List;


public class HangMucThuChiActivity extends Fragment implements View.OnClickListener {
    List<String> listHangMucSpinner;
    Spinner spinnerHangMuc;
    Button btnThemHangMuc;
    static int mode = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hangmuc, container, false);
        createSpinnerHangMuc(view);

        btnThemHangMuc = view.findViewById(R.id.btnThemHangMuc);
        btnThemHangMuc.setOnClickListener(this);

        return view;
    }

    private void createSpinnerHangMuc(View view) {
        spinnerHangMuc = view.findViewById(R.id.spinnerHangMuc);

        listHangMucSpinner = new ArrayList<>();
        listHangMucSpinner.add("Hạng mục chi");
        listHangMucSpinner.add("Hạng mục thu");
        listHangMucSpinner.add("Chuyển sang hạng mục ghi chép");
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listHangMucSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerHangMuc.setAdapter(adapter);
        spinnerHangMuc.setSelection(0);

        spinnerHangMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    mode = 0;
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment_ListHangMucChi hangMucChi = new Fragment_ListHangMucChi();
                    transaction.replace(R.id.content_dshangmuc, hangMucChi);

                    transaction.commit();
                }else if (i == 1){
                    mode = 1;

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    Fragment_ListHangMucThu hangMucThu = new Fragment_ListHangMucThu();
                    transaction.replace(R.id.content_dshangmuc, hangMucThu);

                    transaction.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        //thêm hạng mục event button
        int selectSpinner = spinnerHangMuc.getSelectedItemPosition();
        if (selectSpinner == 0){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment_ThemHangMucChi hangMucChi = new Fragment_ThemHangMucChi();
            transaction.replace(R.id.content_layout, hangMucChi);
            transaction.addToBackStack("themhangmucchi");
            transaction.commit();
        }else if (selectSpinner == 1){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment_ThemHangMucThu hangMucThu = new Fragment_ThemHangMucThu();
            transaction.replace(R.id.content_layout, hangMucThu);
            transaction.addToBackStack("themhangmucthu");
            transaction.commit();
        }
    }

}
