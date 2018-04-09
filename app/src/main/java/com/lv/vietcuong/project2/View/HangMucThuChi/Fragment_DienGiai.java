package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

public class Fragment_DienGiai extends Fragment implements View.OnClickListener {
    Button btnHuy, btnLuu;
    EditText edtDienGiai;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diengiai_hangmuc, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btnHuy = view.findViewById(R.id.btnHuy);
        btnLuu = view.findViewById(R.id.btnLuu);
        edtDienGiai = view.findViewById(R.id.edtDienGiai);

        btnLuu.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnHuy:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnLuu:

                break;
        }
    }

    public void saveHangMuc(){
        if (HangMucThuChiActivity.mode == 0) {
            Fragment_ThemHangMucChi.dienDaiChi = edtDienGiai.getText().toString();
            getActivity().getSupportFragmentManager().popBackStack();
        }else if (HangMucThuChiActivity.mode == 1){
            Fragment_ThemHangMucThu.dienDaiThu = edtDienGiai.getText().toString();
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
