package com.lv.vietcuong.project2.View.BaoCao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

<<<<<<< HEAD
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ThuTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.BaoCao.adapter.HangmucThongkeAdapter;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ViTienAdapter;
=======
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

import java.util.ArrayList;

public class FragmentTaichinhHientai extends Fragment {
    private TextView tvTongSoTien;
    private ListView lvViTien;
    private ArrayList<ViTien> dsViTien;
<<<<<<< HEAD
    private ViTienAdapter viTienAdapter;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taichinh_hientai,container,false);
        initWidget(view);

<<<<<<< HEAD
        dsViTien = SQLViTien.getAllWallet(getActivity());
        setAdapter();
        setTvTongSoTien();

=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        return view;
    }

    private void initWidget(View v){
        tvTongSoTien = (TextView) v.findViewById(R.id.tv_tong_sotien);
        lvViTien = (ListView) v.findViewById(R.id.lv_vitien);
    }

<<<<<<< HEAD
    private void setAdapter(){
        if (viTienAdapter == null){
            viTienAdapter = new ViTienAdapter(getActivity(), R.layout.item_vitien_baocao, dsViTien);
            lvViTien.setAdapter(viTienAdapter);
        }else {
            viTienAdapter.notifyDataSetChanged();
            lvViTien.setSelection(viTienAdapter.getCount()-1);
        }
    }

    private void setTvTongSoTien(){
        int tongSoTien = 0;

        for (ViTien vitien:dsViTien) {
            tongSoTien += vitien.getSoDu() +  viTienAdapter.getSoTienThayDoi(vitien.getIdViTien());
        }

        tvTongSoTien.setText(tongSoTien + " đ");
    }
=======
    private void getAllViTien(){

    }


>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
}
