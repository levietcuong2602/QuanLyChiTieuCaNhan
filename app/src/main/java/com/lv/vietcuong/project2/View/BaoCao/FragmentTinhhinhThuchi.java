package com.lv.vietcuong.project2.View.BaoCao;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.Util;

import java.util.ArrayList;
import java.util.List;

public class FragmentTinhhinhThuchi extends Fragment implements View.OnClickListener {

    TextView tvThoiGian;
    Button btnThoiGian;

    private ListView lvThoigian;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao_tinhhinhthuchi, container, false);
        initWidget(view);
        Util.replace(R.id.fragment_data,new FragmentTinhhinhThuchiHienTai(),getActivity());
        setEvent();

        return view;
    }

    private void initWidget(View v) {
        tvThoiGian = (TextView) v.findViewById(R.id.tv_thoigian);

        btnThoiGian = (Button) v.findViewById(R.id.btn_thoigian);
        }

    private void setEvent() {
        btnThoiGian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_thoigian:
                showDialogThoiGian();
                break;
        }
    }

    public void showDialogThoiGian(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_thoigian_tinhhinhthuchi);
        setListViewThoigian(dialog);

        dialog.show();
    }

    public void setListViewThoigian(final Dialog dialog){
        lvThoigian = dialog.findViewById(R.id.lv_thoigian_tinhhinhthuchi);
        final List<String> data = new ArrayList<>();
        data.add("Hiện tại");
        data.add("Tháng");
        data.add("Năm");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_single_choice, data);
        lvThoigian.setAdapter(adapter);
        lvThoigian.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        lvThoigian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvThoiGian.setText(data.get(i));
                dialog.dismiss();

                if (i == 0){
                    Toast.makeText(getActivity(), "Hiện tại", Toast.LENGTH_SHORT).show();
                    Util.replace(R.id.fragment_data,new FragmentTinhhinhThuchiHienTai(),getActivity());
                }else if (i == 1){
                    Toast.makeText(getActivity(), "Tháng", Toast.LENGTH_SHORT).show();
                    Util.replace(R.id.fragment_data,new FragmentTinhhinhThuchiThang(),getActivity());
                }else {
                    Toast.makeText(getActivity(), "Năm", Toast.LENGTH_SHORT).show();
                    Util.replace(R.id.fragment_data,new FragmentTinhhinhThuchiNam(),getActivity());
                }
            }
        });
    }

}