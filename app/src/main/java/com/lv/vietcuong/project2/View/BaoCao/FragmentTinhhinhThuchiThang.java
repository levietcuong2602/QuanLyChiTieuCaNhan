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
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThuchiThang;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThuchiThangAdapter;
import com.lv.vietcuong.project2.View.GhiChep.Util;

import java.util.ArrayList;
import java.util.Collections;

public class FragmentTinhhinhThuchiThang extends Fragment {
    ArrayList<ThuchiThang> dsThuchiThang;
    ThuchiThangAdapter thuchiThangAdapter;
    private ListView lvThuchiThang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao_tinhhinhthuchi_thang, container, false);
        lvThuchiThang = view.findViewById(R.id.lv_thuchithang);
        dsThuchiThang = getAllThuchiThang();
        setAdapter();
        setEvent();

        return view;
    }

    private ArrayList<ThuchiThang> getAllThuchiThang(){
        ArrayList<ThuchiThang> dsThuchiThang = new ArrayList<>();

        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        String SQLQueryGhiChep = "Select * from GhiChep where username = '" + Layout_TrangChu.taiKhoanDangNhap.getUsername()+"'";
        Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

        if(cursor.moveToFirst()){
            do {
                if (getThuchiThang(cursor.getString(4).substring(3),dsThuchiThang) != null){
                    ThuchiThang thuchiThang = getThuchiThang(cursor.getString(4).substring(3),dsThuchiThang);
                    if (cursor.getString(6).equals("thutien")){
                        thuchiThang.setThu(thuchiThang.getThu() + cursor.getInt(1));
                    }
                    if (cursor.getString(6).equals("chitien")){
                        thuchiThang.setChi(thuchiThang.getChi() + cursor.getInt(1));
                    }

                }else {
                    ThuchiThang thuchiThang = new ThuchiThang();
                    thuchiThang.setThang(cursor.getString(4).substring(3));
                    if (cursor.getString(6).equals("chitien")){
                        thuchiThang.setChi(cursor.getInt(1));
                    }
                    if (cursor.getString(6).equals("thutien")){
                        thuchiThang.setThu(cursor.getInt(1));
                    }
                    dsThuchiThang.add(thuchiThang);
                }

            }while (cursor.moveToNext());
        }
        db.close();

        Collections.sort(dsThuchiThang, new ThuchiThang.ThangComparator());
        return dsThuchiThang;
    }

    private ThuchiThang getThuchiThang(String input, ArrayList<ThuchiThang> dsThuchiThang){
        for (ThuchiThang thuchiThang: dsThuchiThang) {
            if (input.equals(thuchiThang.getThang())){
                return thuchiThang;
            }
        }
        return null;
    }

    private void setAdapter(){
        if (thuchiThangAdapter == null){
            thuchiThangAdapter = new ThuchiThangAdapter(getActivity(), R.layout.item_list_thangthuchi, dsThuchiThang);
            lvThuchiThang.setAdapter(thuchiThangAdapter);
        }else {
            thuchiThangAdapter.notifyDataSetChanged();
            lvThuchiThang.setSelection(thuchiThangAdapter.getCount()-1);
        }
    }

    private void setEvent(){
        lvThuchiThang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentThongkeThang fragmentThongkeThang = new FragmentThongkeThang();
                Bundle bundle = new Bundle();
                bundle.putString("thang", dsThuchiThang.get(i).getThang());
                fragmentThongkeThang.setArguments(bundle);
                Util.replace(R.id.content_layout, fragmentThongkeThang,getActivity());
            }
        });
    }
}
