package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

public class Fragment_SuaHangMuc extends Fragment implements View.OnClickListener {
    Button btnHuy, btnXong;
    ImageView imgHangMuc, imgIconHangMuc;
    EditText edTenHangMuc;
    TextView btnHangMucCha;
    TextView tvDienGiai;
    TableRow rowHangMucCha, rowDienGiai;
    Button btnSave, btnRemove;
    FloatingActionButton fab;

    int idHangMuc;
    String loaiHangMuc = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suahangmuc, container, false);
        initView(view);
        getSharePreference();
        return view;
    }

    private void getSharePreference() {
        SharedPreferences preferences = getActivity().getSharedPreferences("hangmuc", Context.MODE_PRIVATE);

        idHangMuc = preferences.getInt("idHangMuc", -1);
        String tenHangMuc = preferences.getString("tenHangMuc", "");
        String dienGiai = preferences.getString("dienGiai", "");
        loaiHangMuc = preferences.getString("loaiHangMuc", "");
        int idHinhAnh = preferences.getInt("idHinhAnh", -1);
        int idHangMucCha = preferences.getInt("idHangMucCha", -1);

        Bitmap bitmap = SQLHinhAnh.getImage(getActivity(), idHinhAnh);
        imgHangMuc.setImageBitmap(bitmap);

        edTenHangMuc.setText(tenHangMuc);
       tvDienGiai.setText(dienGiai);

        if (idHangMucCha == -1){
            rowHangMucCha.setVisibility(View.GONE);
        }else {
            rowHangMucCha.setVisibility(View.VISIBLE);
            HangMuc hangMucCha = SQLHangMuc.getHangMuc(getActivity(), loaiHangMuc, idHangMucCha);
            Bitmap bitmap1 = SQLHinhAnh.getImage(getActivity(), hangMucCha.getIdHinhAnh());
            imgIconHangMuc.setImageBitmap(bitmap1);

            btnHangMucCha.setText(hangMucCha.getTenHangMuc());
        }
    }

    private void initView(View view) {
        btnHuy = view.findViewById(R.id.tvHuy);
        btnXong = view.findViewById(R.id.tvXong);
        rowHangMucCha = view.findViewById(R.id.rowHangMucCha);
        imgHangMuc = view.findViewById(R.id.imgHangMuc);
        edTenHangMuc = view.findViewById(R.id.tenHangMuc);
        btnHangMucCha = view.findViewById(R.id.btnHangMucCha);
        imgIconHangMuc = view.findViewById(R.id.imgIconHangMuc);
        rowDienGiai = view.findViewById(R.id.rowDienGiai);
        tvDienGiai = view.findViewById(R.id.btnDienGiai);
        btnSave = view.findViewById(R.id.btnSave);
        btnRemove = view.findViewById(R.id.btnRemove);
        fab = view.findViewById(R.id.fabAvataHangMuc);

        btnHuy.setOnClickListener(this);
        btnXong.setOnClickListener(this);
        rowHangMucCha.setOnClickListener(this);
        rowDienGiai.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tvHuy:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.tvXong:
                updateHangMuc();
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.rowDienGiai:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment_DienGiai dienGiai = new Fragment_DienGiai();
                dienGiai.loaiDienDai = "suahangmuc";
                dienGiai.dienGiaiCu = tvDienGiai.getText().toString();

                transaction.replace(R.id.content_layout, dienGiai);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.rowHangMucCha:
                Fragment_DSHangMucCha hangMucCha = new Fragment_DSHangMucCha();
                hangMucCha.mode_hangmuccha = "suahangmuc";
                hangMucCha.loaiHangMuc = loaiHangMuc;

                FragmentTransaction transaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.content_layout, hangMucCha);
                transaction1.addToBackStack(null);
                transaction1.commit();
                break;
            case R.id.btnSave:
                updateHangMuc();
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnRemove:
                long resultDelete = SQLHangMuc.deleteHangMuc(getActivity(), idHangMuc);
                if (resultDelete > 0){
                    Toast.makeText(getContext(), "Xóa hạng mục thành công", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                break;
            case R.id.fabAvataHangMuc:
                Fragment_ListIcon listIcon = new Fragment_ListIcon();
                listIcon.mode_dsIcon = "suahangmuc";

                FragmentTransaction transaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.content_layout, listIcon);
                transaction2.addToBackStack(null);
                transaction2.commit();
                break;
        }
    }

    private void updateHangMuc() {
        HangMuc hangMuc = new HangMuc();
        SharedPreferences preferences = getActivity().getSharedPreferences("hangmuc", Context.MODE_PRIVATE);
        hangMuc.setIdHangMuc(preferences.getInt("idHangMuc", -1));
        hangMuc.setTenHangMuc(preferences.getString("tenHangMuc", ""));
        hangMuc.setDienDai(preferences.getString("dienGiai", ""));
        hangMuc.setLoaiHangMuc(preferences.getString("loaiHangMuc", ""));
        hangMuc.setIdHinhAnh(preferences.getInt("idHinhAnh", -1));
        hangMuc.setIdHangMucCha(preferences.getInt("idHangMucCha", -1));

        long result = SQLHangMuc.updateHangMuc(getActivity(), hangMuc);
        if (result > 0){
            Toast.makeText(getContext(), "Update hạng mục thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Update không thành công", Toast.LENGTH_SHORT).show();
        }

    }
}
