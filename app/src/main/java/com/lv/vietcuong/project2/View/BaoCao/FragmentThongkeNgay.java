package com.lv.vietcuong.project2.View.BaoCao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.BaoCao.adapter.HangmucThongke;
import com.lv.vietcuong.project2.View.BaoCao.adapter.HangmucThongkeAdapter;
import com.lv.vietcuong.project2.View.GhiChep.Util;

import java.util.ArrayList;

public class FragmentThongkeNgay extends Fragment {
    private ListView lvThongkeChi, lvThongkeThu;
    private ArrayList<HangmucThongke> dsHangmucThongkeChi, dsHangmucThongkeThu;
    private HangmucThongkeAdapter hangmucThongkeAdapterChi, hangmucThongkeAdapterThu;
    private TextView tvTongChi, tvTongThu;
    private Button btnBack;
    private int tongSoTienChi = 0, tongSoTienThu = 0;

    private String ngay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
=======
        View view = inflater.inflate(R.layout.fragment_thongke_thang, container, false);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        lvThongkeChi = view.findViewById(R.id.lv_thongke_chi);
        lvThongkeThu = view.findViewById(R.id.lv_thongke_thu);
        tvTongChi = view.findViewById(R.id.tv_tongchi);
        tvTongThu = view.findViewById(R.id.tv_tongthu);
        btnBack = view.findViewById(R.id.btn_back_2);

        Bundle bundle = getArguments();
        ngay = bundle.getString("ngay");
        dsHangmucThongkeChi = getAllHangmucNgayChi();
        dsHangmucThongkeThu = getAllHangmucNgayThu();
        setPhanTram();
        setAdapter();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.replace(R.id.content_layout, new FragmentBaoCao(), getActivity());
            }
        });

        return view;
    }

    private ArrayList<HangmucThongke> getAllHangmucNgayChi(){
        ArrayList<HangmucThongke> dsHangmucThongkeChi = new ArrayList<>();

        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        String SQLQueryGhiChep = "Select * from GhiChep where username = '" + Layout_TrangChu.taiKhoanDangNhap.getUsername()+"'";
        Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

        if(cursor.moveToFirst()){
            do {
                if (cursor.getString(4).equals(ngay)){
<<<<<<< HEAD
                    if (cursor.getString(7).equals("chitien")){
                        if (getHangmucNgay(cursor.getInt(6), dsHangmucThongkeChi) == null){
                            HangmucThongke hangmucThongke = new HangmucThongke();
                            hangmucThongke.setTenHangMuc(SQLHangMuc.getTenHangMuc(getActivity(),cursor.getInt(6)));
                            hangmucThongke.setSoTien(cursor.getInt(1));
                            dsHangmucThongkeChi.add(hangmucThongke);
                        }else {
                            HangmucThongke hangmucThongke = getHangmucNgay(cursor.getInt(6), dsHangmucThongkeChi);
=======
                    if (cursor.getString(6).equals("chitien")){
                        if (getHangmucNgay(cursor.getInt(5), dsHangmucThongkeChi) == null){
                            HangmucThongke hangmucThongke = new HangmucThongke();
                            hangmucThongke.setTenHangMuc(SQLHangMuc.getTenHangMuc(getActivity(),cursor.getInt(5)));
                            hangmucThongke.setSoTien(cursor.getInt(1));
                            dsHangmucThongkeChi.add(hangmucThongke);
                        }else {
                            HangmucThongke hangmucThongke = getHangmucNgay(cursor.getInt(5), dsHangmucThongkeChi);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
                            hangmucThongke.setSoTien(hangmucThongke.getSoTien() + cursor.getInt(1));
                        }
                    }
                }
            }while (cursor.moveToNext());
        }
        db.close();

        return dsHangmucThongkeChi;
    }

    private ArrayList<HangmucThongke> getAllHangmucNgayThu(){
        ArrayList<HangmucThongke> dsHangmucThongkeThu = new ArrayList<>();

        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        String SQLQueryGhiChep = "Select * from GhiChep where username = '" + Layout_TrangChu.taiKhoanDangNhap.getUsername()+"'";
        Cursor cursor = db.rawQuery(SQLQueryGhiChep, null);

        if(cursor.moveToFirst()){
            do {
                if (cursor.getString(4).equals(ngay)){
<<<<<<< HEAD
                    if (cursor.getString(7).equals("thutien")){
                        if (getHangmucNgay(cursor.getInt(6), dsHangmucThongkeThu) == null){
                            HangmucThongke hangmucThongke = new HangmucThongke();
                            hangmucThongke.setTenHangMuc(SQLHangMuc.getTenHangMuc(getActivity(),cursor.getInt(6)));
                            hangmucThongke.setSoTien(cursor.getInt(1));
                            dsHangmucThongkeThu.add(hangmucThongke);
                        }else {
                            HangmucThongke hangmucThongke = getHangmucNgay(cursor.getInt(6), dsHangmucThongkeThu);
=======
                    if (cursor.getString(6).equals("thutien")){
                        if (getHangmucNgay(cursor.getInt(5), dsHangmucThongkeThu) == null){
                            HangmucThongke hangmucThongke = new HangmucThongke();
                            hangmucThongke.setTenHangMuc(SQLHangMuc.getTenHangMuc(getActivity(),cursor.getInt(5)));
                            hangmucThongke.setSoTien(cursor.getInt(1));
                            dsHangmucThongkeThu.add(hangmucThongke);
                        }else {
                            HangmucThongke hangmucThongke = getHangmucNgay(cursor.getInt(5), dsHangmucThongkeThu);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
                            hangmucThongke.setSoTien(hangmucThongke.getSoTien() + cursor.getInt(1));
                        }
                    }
                }
            }while (cursor.moveToNext());
        }
        db.close();

        return dsHangmucThongkeThu;
    }

    private HangmucThongke getHangmucNgay(int id, ArrayList<HangmucThongke> dsHangmucThongke){
        String tenHangmuc = SQLHangMuc.getTenHangMuc(getActivity(), id);
        for (HangmucThongke hangmucThongke : dsHangmucThongke) {
            if (tenHangmuc.equals(hangmucThongke.getTenHangMuc())){
                return hangmucThongke;
            }
        }
        return null;
    }

    private void setPhanTram() {
        if (dsHangmucThongkeChi != null) {
            for (HangmucThongke hangmucThongke : dsHangmucThongkeChi) {
                tongSoTienChi += hangmucThongke.getSoTien();
            }
            tvTongChi.setText("Tổng chi: " +tongSoTienChi + " đ");

            for (HangmucThongke hangmucThongke : dsHangmucThongkeChi){
                double soTien = hangmucThongke.getSoTien();
                double phanTram = (double) 100*(soTien / (double) tongSoTienChi);
                phanTram = (double) Math.round(phanTram*100)/100;
                hangmucThongke.setPhanTram(phanTram);
            }
        }

        if (dsHangmucThongkeThu != null) {
            for (HangmucThongke hangmucThongke : dsHangmucThongkeThu) {
                tongSoTienThu += hangmucThongke.getSoTien();
            }
            tvTongThu.setText("Tổng thu: " +tongSoTienThu + " đ");

            for (HangmucThongke hangmucThongke : dsHangmucThongkeThu){
                double soTien = hangmucThongke.getSoTien();
                double phanTram = (double) 100*(soTien / (double) tongSoTienThu);
                phanTram = (double) Math.round(phanTram*100)/100;
                hangmucThongke.setPhanTram(phanTram);
            }
        }
    }

    private void setAdapter(){
        if (hangmucThongkeAdapterChi == null){
            hangmucThongkeAdapterChi = new HangmucThongkeAdapter(getActivity(), R.layout.item_thongke_hangmuc, dsHangmucThongkeChi);
            lvThongkeChi.setAdapter(hangmucThongkeAdapterChi);
        }else {
            hangmucThongkeAdapterChi.notifyDataSetChanged();
            lvThongkeChi.setSelection(hangmucThongkeAdapterChi.getCount()-1);
        }

        if (hangmucThongkeAdapterThu == null){
            hangmucThongkeAdapterThu = new HangmucThongkeAdapter(getActivity(), R.layout.item_thongke_hangmuc, dsHangmucThongkeThu);
            lvThongkeThu.setAdapter(hangmucThongkeAdapterThu);
        }else {
            hangmucThongkeAdapterThu.notifyDataSetChanged();
            lvThongkeThu.setSelection(hangmucThongkeAdapterThu.getCount()-1);
        }
    }
}
