package com.lv.vietcuong.project2.View.ViTien;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.ListViTienAdapter;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.HanMucChi.Fragment_ThemHanMucChi;

import java.util.ArrayList;

public class Fragment_DanhSachTaiKhoan extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
    ListView listTaiKhoan;
    ArrayList<ViTien> dsViTien;
    TextView textViewTongTien;
    ListViTienAdapter adapter;
    int position;
    BottomSheetDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_danhsach_taikhoan, container, false);

        initView(view);
        initListViewDanhSachViTien(view);

        setTexViewTongTien();

        return view;
    }

    public void initView(View view){
        listTaiKhoan = view.findViewById(R.id.listViTien);
        textViewTongTien = view.findViewById(R.id.textViewTong);
    }


    private void initListViewDanhSachViTien(View view) {
        dsViTien = SQLViTien.getAllWallet(getActivity());
        adapter = new ListViTienAdapter(getActivity(), R.layout.item_list_vitien, dsViTien);
        adapter.notifyDataSetChanged();
        listTaiKhoan.setAdapter(adapter);

        listTaiKhoan.setOnItemClickListener(this);
        listTaiKhoan.setOnItemLongClickListener(this);
    }

    private void setTexViewTongTien() {
        int tong = 0;
        for(int i = 0; i < dsViTien.size(); i++){
            ViTien wallet = dsViTien.get(i);
            tong += wallet.getSoDu();
        }

        textViewTongTien.setText(tong+"");
    }
    public void showBottomSheetDialog(){
        dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(R.layout.bottom_sheet_fragment_option_vitien);

//        LinearLayout layoutChuyenKhoan = dialog.findViewById(R.id.item_chuyenkhoan);
        LinearLayout layoutHanMucChi = dialog.findViewById(R.id.item_hanmucchi);
        LinearLayout layoutEdit = dialog.findViewById(R.id.item_edit);
        LinearLayout layoutNgungSuDung = dialog.findViewById(R.id.item_ngungsudung);

        layoutEdit.setOnClickListener(this);
        layoutHanMucChi.setOnClickListener(this);
        layoutNgungSuDung.setOnClickListener(this);

        dialog.show();
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getX() < v.getWidth()-40){
                    showThongTinViTien(position);
                }
                return false;
            }
        });

        ImageView imageView = view.findViewById(R.id.imgDot);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
    }

    public void showThongTinViTien(int i){
        ViTien viTien = (ViTien) dsViTien.get(i);

        Fragment_ThongTinGhiChepViTien thongTinViTien = new Fragment_ThongTinGhiChepViTien();

        Bundle bundle = new Bundle();
        bundle.putSerializable("ViTien", viTien);
        thongTinViTien.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_layout, thongTinViTien);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        ViTien viTien = (ViTien) adapterView.getItemAtPosition(i);
        long result = SQLViTien.deleteViTien(getActivity(), viTien.getIdViTien());

        if (result > 0){
            Toast.makeText(getContext(), "delete ví tiền thành công", Toast.LENGTH_SHORT).show();

            dsViTien.clear();
            dsViTien.addAll(SQLViTien.getAllWallet(getActivity()));
            adapter.notifyDataSetChanged();
            setAdapter();
        }else {
            Toast.makeText(getContext(), "delete ví tiền không thành công", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void setAdapter(){
        if (adapter == null){
            adapter = new ListViTienAdapter(getActivity(), R.layout.item_list_vitien, dsViTien);
            adapter.notifyDataSetChanged();
            listTaiKhoan.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
            listTaiKhoan.setSelection(adapter.getCount()-1);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.item_hanmucchi:
                ViTien viTien = dsViTien.get(position);
                Fragment_ThemHanMucChi themHanMucChi = new Fragment_ThemHanMucChi();
                themHanMucChi.vitien = viTien;
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.content_layout, themHanMucChi);
                transaction.addToBackStack(null);
                transaction.commit();

                dialog.hide();
                break;
            case R.id.item_edit:

                break;

            case R.id.item_ngungsudung:
                break;

        }
    }
}
