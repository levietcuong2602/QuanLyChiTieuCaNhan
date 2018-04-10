package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLiteGhiChep;
import com.lv.vietcuong.project2.Model.ChiTien;
import com.lv.vietcuong.project2.Model.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.GhiChep;
import com.lv.vietcuong.project2.Model.ThuTien;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.adapter.AdapterGhiChep;

import java.util.ArrayList;

public class DaGhiActivity extends Fragment {
    private Button btnBack;
    ArrayList<GhiChep> dsGhiChep;
    AdapterGhiChep adapterGhiChep;
    private ListView lvGhiChep;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.daghi_activity);
//
//        initWidget();
//
//        dsGhiChep = SQLiteGhiChep.getAllGhiChep(this);
//        setAdapterGhiChep();
//        setEvent();
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daghi_activity,container,false);

        initWidget(view);
        dsGhiChep = SQLiteGhiChep.getAllGhiChep(getActivity());
        setAdapterGhiChep();
        setEvent();

        return view;
    }

    private void initWidget(View v){
        btnBack = (Button) v.findViewById(R.id.btn_back);
        lvGhiChep = (ListView) v.findViewById(R.id.lv_ghichep);
    }


    private void setAdapterGhiChep(){
        if (adapterGhiChep == null){
            adapterGhiChep = new AdapterGhiChep(getActivity(), R.layout.item_list_ghichep,dsGhiChep,getActivity());
            lvGhiChep.setAdapter(adapterGhiChep);
        }else {
            adapterGhiChep.notifyDataSetChanged();
            lvGhiChep.setSelection(adapterGhiChep.getCount()-1);
        }
    }

    private void setEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.replace(R.id.content_layout, new GhiChepActivity(), getActivity());
            }
        });

        lvGhiChep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GhiChep ghiChep = dsGhiChep.get(i);
                int idGhiChep = ghiChep.getIdGhiChep();

//                if (ghiChep instanceof ChiTien) {
//                    Intent intent = new Intent(DaGhiActivity.this, ActivityEditChitien.class);
//                    intent.putExtra("idGhiChep",idGhiChep);
//                    startActivity(intent);
//                }
//
//                if (ghiChep instanceof ThuTien) {
//                    Intent intent = new Intent(DaGhiActivity.this, ActivityEditThutien.class);
//                    intent.putExtra("idGhiChep",idGhiChep);
//                    startActivity(intent);
//                }
//
//                if (ghiChep instanceof ChuyenKhoan) {
//                    Intent intent = new Intent(DaGhiActivity.this, ActivityEditChuyenkhoan.class);
//                    intent.putExtra("idGhiChep",idGhiChep);
//                    startActivity(intent);
//                }
            }
        });

    }

//    public static void updateListGhiChep(Activity activity){
//        DaGhiActivity.dsGhiChep.clear();
//        DaGhiActivity.dsGhiChep.addAll(SQLiteGhiChep.getAllGhiChep(activity));
//        if (DaGhiActivity.adapterGhiChep != null){
//            DaGhiActivity.adapterGhiChep.notifyDataSetChanged();
//        }
//    }
}
