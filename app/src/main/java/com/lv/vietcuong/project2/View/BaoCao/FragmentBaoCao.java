package com.lv.vietcuong.project2.View.BaoCao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

<<<<<<< HEAD
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.R;
=======
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.FragmentGhiChepThutien;
import com.lv.vietcuong.project2.View.GhiChep.FragmentGhichepChitien;
import com.lv.vietcuong.project2.View.GhiChep.FragmentGhichepChuyenKhoan;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.View.GhiChep.Util;

import java.util.ArrayList;
import java.util.List;

public class FragmentBaoCao extends Fragment {
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao,container,false);
        spinner = view.findViewById(R.id.spn_danhmuc_3);
        setSpinner();
        return view;
    }

    private void setSpinner() {
        List<String> list = new ArrayList<>();
        list.add("Tình hình thu chi");
        list.add("Tài chính hiện tại");
<<<<<<< HEAD
       // list.add("Quản lý thành viên");
        if (Layout_TrangChu.taiKhoanDangNhap.getLoaiTaiKhoan().equals("chugiadinh")){
            list.add("Quản lý thành viên");
        }
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int id = R.id.fragment_content_baocao;

                switch (Integer.parseInt(spinner.getItemIdAtPosition(i)+"")){
                    case 0:
                        Util.replace(id, new FragmentTinhhinhThuchi(), getActivity());
                        break;
                    case 1:
                        Util.replace(id, new FragmentTaichinhHientai(), getActivity());
                        break;
<<<<<<< HEAD
                    case 2:
                        Util.replace(id, new FragmentQuanlyThanhVien(), getActivity());
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
