package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

public class Fragment_ThemHangMucChi extends Fragment implements View.OnClickListener {
    Button btnHuy, btnLuu, btnDienGiai;
    Button btnButtonSave;
    EditText edtTenHangMucChi;

    static String dienDaiChi = "";
    static int iconChi = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themhangmucchi, container, false);
        initView(view);
        return view;
    }

    public void initView(View view){
        btnHuy = view.findViewById(R.id.btnHuy);
        btnLuu = view.findViewById(R.id.btnLuu);
        edtTenHangMucChi = view.findViewById(R.id.tenHangMucChi);
        btnDienGiai = view.findViewById(R.id.btnDienGiai);
        btnButtonSave = view.findViewById(R.id.idButtonSave);

        btnLuu.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        btnDienGiai.setOnClickListener(this);
        btnButtonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnHuy:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnLuu:
            case R.id.idButtonSave:
                long result = addHangMuc();
                if(result > 0){
                    Toast.makeText(getContext(), "Thêm hạng mục thành công", Toast.LENGTH_SHORT).show();
                    edtTenHangMucChi.setText("");
                    dienDaiChi = "";

//                    getActivity().getSupportFragmentManager().popBackStack();
                }else {
                    Toast.makeText(getContext(), "Thêm hạng mục không thành công", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDienGiai:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment_DienGiai dienGiai = new Fragment_DienGiai();
                transaction.replace(R.id.content_layout, dienGiai);
                transaction.addToBackStack(null);

                transaction.commit();
                break;
        }
    }

    private long addHangMuc() {
        HangMuc hangMuc = new HangMuc();

        hangMuc.setTenHangMuc(edtTenHangMucChi.getText().toString());
        hangMuc.setLoaiHangMuc("chi");
        hangMuc.setDienDai(dienDaiChi);
        hangMuc.setIdHinhAnh(iconChi);

        if (hangMuc.getTenHangMuc().length() > 0) {
            return SQLHangMuc.addHangMuc(getActivity(), hangMuc);
        }
        return -1;
    }
}
