package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Model.HangMuc;
import com.lv.vietcuong.project2.R;

public class Fragment_ThemHangMucChi extends Fragment implements View.OnClickListener {
    Button btnHuy, btnLuu;
    EditText edtTenHangMucChi;

    static String dienDaiChi = "";
    static int iconChi = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themhangmucchi, container, false);
        btnHuy = view.findViewById(R.id.btnHuy);
        btnLuu = view.findViewById(R.id.btnLuu);
        edtTenHangMucChi = view.findViewById(R.id.tenHangMucChi);

        btnLuu.setOnClickListener(this);
        btnHuy.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnHuy:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnLuu:
                long result = addHangMuc();
                if(result > 0){
                    Toast.makeText(getContext(), "Thêm hạng mục thành công", Toast.LENGTH_SHORT).show();
                    edtTenHangMucChi.setText("");
                    dienDaiChi = "";
                }else {
                    Toast.makeText(getContext(), "Thêm hạng mục không thành công", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private long addHangMuc() {
        HangMuc hangMuc = new HangMuc();
        hangMuc.setTenHangMuc(edtTenHangMucChi.getText().toString());
        hangMuc.setLoaiHangMuc("chi");
        hangMuc.setDienDai(dienDaiChi);
        hangMuc.setIcon(iconChi);

        return SQLHangMuc.addHangMuc(getActivity(), hangMuc);
    }
}
