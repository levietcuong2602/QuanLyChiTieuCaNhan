package com.lv.vietcuong.project2.View.HanMucChi;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fragment_NgayKetThuc extends Fragment implements View.OnClickListener {
    ListView listView;
    int yearKt, monthKt, dayKt;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    TextView textViewNgayKT;
    Button btnBack, btnSave;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngayketthuc, container, false);

        listView = view.findViewById(R.id.listViewNgayKetThuc);
        textViewNgayKT = view.findViewById(R.id.textViewNgayKT);

        btnBack = view.findViewById(R.id.btnBack);
        btnSave = view.findViewById(R.id.btnSave);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        arrayList = new ArrayList<>();
        arrayList.add("không xác định");
        arrayList.add("tùy chọn");
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_single_choice, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 1){
                    DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            yearKt = year;
                            monthKt = month;
                            dayKt = day;

                            textViewNgayKT.setText(day + "/" + month + "/" + year);
                        }
                    };

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), callback, year, month+1, day);
                    pickerDialog.show();
                }

                if(i == 0){
                    textViewNgayKT.setText("không xác định");
                }
            }
        });

        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnBack:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnSave:
                Fragment_ThemHanMucChi.ngayKetThuc = textViewNgayKT.getText().toString();
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
}
