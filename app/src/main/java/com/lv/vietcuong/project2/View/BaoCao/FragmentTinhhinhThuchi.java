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

import java.util.ArrayList;
import java.util.List;

public class FragmentTinhhinhThuchi extends Fragment implements View.OnClickListener {

    TextView tvTaiKhoan, tvThoiGian;
    Button btnTaiKhoan, btnThoiGian, btnBaoCao;
    ListView lvTinhhinhThuChi;

    private ListView lvTaikhoan, lvThoigian;
    private Button btnXong;
    private CheckBox cbCheckAll;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao_tinhhinhthuchi, container, false);
        initWidget(view);
        setEvent();

        return view;
    }

    private void initWidget(View v) {
        tvTaiKhoan = (TextView) v.findViewById(R.id.tv_taikhoan);
        tvThoiGian = (TextView) v.findViewById(R.id.tv_thoigian);

        btnBaoCao = (Button) v.findViewById(R.id.btn_baocao);
        btnTaiKhoan = (Button) v.findViewById(R.id.btn_taikhoan);
        btnThoiGian = (Button) v.findViewById(R.id.btn_thoigian);

        lvTinhhinhThuChi = (ListView) v.findViewById(R.id.lv_tinhhinh_thuchi);
    }

    private void setEvent() {
        btnThoiGian.setOnClickListener(this);
        btnTaiKhoan.setOnClickListener(this);
        btnBaoCao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_taikhoan:
                showSeLectTaiKhoan();
                break;
            case R.id.btn_baocao:

                break;
            case R.id.btn_thoigian:
                showDialogThoiGian();
                break;
        }
    }

    public void showSeLectTaiKhoan(){
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Chọn tài khoản");
        dialog.setContentView(R.layout.dialog_taikhoan_tinhhinhthuchi);
        dialog.setCancelable(false);
        setListViewTaikhoan(dialog);

        dialog.show();
    }

    public void setListViewTaikhoan(final Dialog dialog1) {
        //set data
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("Item " + i);
        }
        btnXong = (Button) dialog1.findViewById(R.id.btn_xong);
        cbCheckAll = (CheckBox) dialog1.findViewById(R.id.cb_check_all);

        lvTaikhoan = (ListView) dialog1.findViewById(R.id.lv_taikhoan_tinhhinhthuchi);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, data);
        lvTaikhoan.setAdapter(adapter);
        lvTaikhoan.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        //set event
        btnXong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SparseBooleanArray sparseBooleanArray = lvTaikhoan.getCheckedItemPositions();
                String itemsSelected = "";
                for (int i = 0; i < sparseBooleanArray.size(); i++) {
                    int position = sparseBooleanArray.keyAt(i);
                        itemsSelected = lvTaikhoan.getAdapter().getItem( sparseBooleanArray.keyAt(i)).toString();
                        Log.i("TAG",itemsSelected + " was selected");
                    itemsSelected += "item " + position + ",";
                }

                Toast.makeText(getActivity(), itemsSelected , Toast.LENGTH_SHORT).show();
                dialog1.dismiss();
            }
        });

        cbCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < adapter.getCount(); i++) {
                    lvTaikhoan.setItemChecked(i, isChecked);
                }
            }
        });
    }

    public void showDialogThoiGian(){
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Thời gian");
        dialog.setContentView(R.layout.dialog_thoigian_tinhhinhthuchi);
        dialog.setCancelable(false);
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
                Toast.makeText(getActivity(), "Dữ liệu hiển thị theo: " + data.get(i), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}