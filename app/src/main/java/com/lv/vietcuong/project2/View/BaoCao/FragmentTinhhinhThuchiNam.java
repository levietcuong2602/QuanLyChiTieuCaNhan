package com.lv.vietcuong.project2.View.BaoCao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThuchiNam;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThuchiNamAdapter;
import com.lv.vietcuong.project2.View.GhiChep.Util;

import java.util.ArrayList;
import java.util.Collections;

public class FragmentTinhhinhThuchiNam extends Fragment{
    ArrayList<ThuchiNam> dsThuchiNam;
    ThuchiNamAdapter thuchiNamAdapter;
    private ListView lvThuchiNam;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao_tinhhinhthuchi_thang, container, false);
        lvThuchiNam = view.findViewById(R.id.lv_thuchithang);
        dsThuchiNam = getAllThuchiNam();
        setAdapter();
        setEvent();

        return view;
    }

    private ArrayList<ThuchiNam> getAllThuchiNam(){
        ArrayList<ThuchiNam> dsThuchiNam = new ArrayList<>();

        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        String SQLQueryGhiChep = "Select * from GhiChep where username = '" + Layout_TrangChu.taiKhoanDangNhap.getUsername()+"'";
        Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

        if(cursor.moveToFirst()){
            do {
                if (getThuchiNam(cursor.getString(4).substring(6),dsThuchiNam) != null){
                    ThuchiNam thuchiNam = getThuchiNam(cursor.getString(4).substring(6),dsThuchiNam);
                    if (cursor.getString(6).equals("thutien")){
                        thuchiNam.setThu(thuchiNam.getThu() + cursor.getInt(1));
                    }
                    if (cursor.getString(6).equals("chitien")){
                        thuchiNam.setChi(thuchiNam.getChi() + cursor.getInt(1));
                    }

                }else {
                    ThuchiNam thuchiNam = new ThuchiNam();
                    thuchiNam.setNam(cursor.getString(4).substring(6));
                    if (cursor.getString(6).equals("chitien")){
                        thuchiNam.setChi(cursor.getInt(1));
                    }
                    if (cursor.getString(6).equals("thutien")){
                        thuchiNam.setThu(cursor.getInt(1));
                    }
                    dsThuchiNam.add(thuchiNam);
                }

            }while (cursor.moveToNext());
        }
        db.close();

        Collections.sort(dsThuchiNam, new ThuchiNam.NamComparator());
        return dsThuchiNam;
    }

    private ThuchiNam getThuchiNam(String input, ArrayList<ThuchiNam> dsThuchiNam){
        for (ThuchiNam thuchiNam: dsThuchiNam) {
            if (input.equals(thuchiNam.getNam())){
                return thuchiNam;
            }
        }
        return null;
    }

    private void setAdapter(){
        if (thuchiNamAdapter == null){
            thuchiNamAdapter = new ThuchiNamAdapter(getActivity(), R.layout.item_list_thangthuchi, dsThuchiNam);
            lvThuchiNam.setAdapter(thuchiNamAdapter);
        }else {
            thuchiNamAdapter.notifyDataSetChanged();
            lvThuchiNam.setSelection(thuchiNamAdapter.getCount()-1);
        }
    }

    private void setEvent(){
        lvThuchiNam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentThongkeNam fragmentThongkeNam = new FragmentThongkeNam();
                Bundle bundle = new Bundle();
                bundle.putString("nam", dsThuchiNam.get(i).getNam());
                fragmentThongkeNam.setArguments(bundle);
                Util.replace(R.id.content_layout, fragmentThongkeNam,getActivity());
            }
        });
    }
}
