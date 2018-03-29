package com.lv.vietcuong.project2.View.HanMucChi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DB_HanMucChi;
import com.lv.vietcuong.project2.Model.HanMucChi;
import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/26/2018.
 */

public class Fragment_ThemHangMucChi extends Fragment implements View.OnClickListener {
    Button btnSaveHangMucChi, btnCancelHangMucChi;
    EditText edtTenHanMuc, edtSoHanMuc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themhanmucchi, container, false);
        
        btnSaveHangMucChi = view.findViewById(R.id.btnSaveHangMucChi);
        btnCancelHangMucChi = view.findViewById(R.id.btnHuySaveHangMucChi);
        edtSoHanMuc = view.findViewById(R.id.edSoHanMucChi);
        edtTenHanMuc = view.findViewById(R.id.edtenHanMucChi);
        
        btnCancelHangMucChi.setOnClickListener(this);
        btnSaveHangMucChi.setOnClickListener(this);
        
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveHangMucChi:
                saveHangMucChi();
                break;
            case R.id.btnHuySaveHangMucChi:
                cancelSaveHangMucChi();
                break;
        }
    }

    private void cancelSaveHangMucChi() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transTaiKhoan = manager.beginTransaction();
        HanMucChiActivity hanMucChiActivity = new HanMucChiActivity();
        transTaiKhoan.replace(R.id.content_layout, hanMucChiActivity);
        transTaiKhoan.commit();
    }

    private void saveHangMucChi() {
        String name = edtTenHanMuc.getText().toString();
        String soHanMuc = edtSoHanMuc.getText().toString();

        if (name.equals("") || soHanMuc.equals("")){
            Toast.makeText(getActivity(), "Bạn cần nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
        }else {
            if (!soHanMuc.matches("[0-9]+")){
                Toast.makeText(getActivity(), "Số hạn mức không hợp lệ", Toast.LENGTH_SHORT).show();
            }else {
                HanMucChi hanMucChi =  new HanMucChi(name, Double.valueOf(soHanMuc));
                long result = DB_HanMucChi.addHanMucChi(getActivity(), hanMucChi);
                if(result > 0){
                    edtTenHanMuc.setText("");
                    edtSoHanMuc.setText("");
                    Toast.makeText(getActivity(), "Thêm hạn mức chi thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Thêm hạn mức chi không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
