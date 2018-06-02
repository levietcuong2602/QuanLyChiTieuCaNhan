package com.lv.vietcuong.project2.View.BaoCao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.Model.TaiKhoan.ModelTaiKhoan;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.GhiChep.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FragmentTinhhinhThuchiHienTai extends Fragment {
    private TextView tvHomnay, imgHomnay, tvThangnay, imgThangnay, tvNamnay, imgNamnay, tvThuHomnay, tvChiHomnay, tvThuThangnay, tvChiThangnay, tvThuNamnay, tvChiNamnay;
    private RelativeLayout relativeLayoutThang, relativeLayoutNam, relativeLayoutNgay;
    String ngay, thang, nam;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao_tinhhinhthuchi_hientai, container, false);
        initWidget(view);
        setNgay();
        setEvent();

        return view;
    }

    private void initWidget(View v){
        tvHomnay = (TextView) v.findViewById(R.id.tv_homnay);
        tvThangnay = (TextView) v.findViewById(R.id.tv_thangnay);
        tvNamnay = (TextView) v.findViewById(R.id.tv_namnay);

        imgHomnay = (TextView) v.findViewById(R.id.img_homnay);
        imgThangnay = (TextView) v.findViewById(R.id.img_thangnay);
        imgNamnay = (TextView) v.findViewById(R.id.img_namnay);

        tvThuHomnay = (TextView) v.findViewById(R.id.tv_thu_homnay);
        tvChiHomnay = (TextView) v.findViewById(R.id.tv_chi_homnay);
        tvThuThangnay = (TextView) v.findViewById(R.id.tv_thu_thangnay);
        tvChiThangnay = (TextView) v.findViewById(R.id.tv_chi_thangnay);
        tvThuNamnay = (TextView) v.findViewById(R.id.tv_thu_namnay);
        tvChiNamnay = (TextView) v.findViewById(R.id.tv_chi_namnay);

        relativeLayoutNgay = (RelativeLayout) v.findViewById(R.id.rlt_homnay);
        relativeLayoutThang = (RelativeLayout) v.findViewById(R.id.rlt_thangnay);
        relativeLayoutNam = (RelativeLayout) v.findViewById(R.id.rlt_namnay);
    }

    private void setNgay(){
        //lấy ngày hiện tại của hệ thống
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dftHomnay = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        ngay = dftHomnay.format(cal.getTime());
        //hiển thị lên giao diện
        tvHomnay.setText("Hôm nay (" +ngay+ ")");
        imgHomnay.setText(ngay.substring(0,2));


        SimpleDateFormat dftThangnay = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        thang = dftThangnay.format(cal.getTime());
        //hiển thị lên giao diện
        tvThangnay.setText("Tháng này (" +thang+ ")");
        imgThangnay.setText(thang.substring(0,2));

        SimpleDateFormat dftNamnay = new SimpleDateFormat("yyyy", Locale.getDefault());
        nam = dftNamnay.format(cal.getTime());
        //hiển thị lên giao diện
        tvNamnay.setText("Năm nay (" +nam+ ")");
        imgNamnay.setText(nam);

        int thuHomnay = 0, chiHomnay = 0, thuThangnay = 0, chiThangnay = 0, thuNamnay = 0, chiNamnay = 0;
        SQLiteDatabase db = DataBaseManager.initDataBaseQlyThuChi(getActivity());
        TaiKhoan taikhoan = new ModelTaiKhoan().getCacheTaiKhoan(getContext());
        String query = "Select * from GhiChep where username = '" + taikhoan.getUsername()+"'";
        Cursor cs = db.rawQuery(query,null);

        if (cs.moveToFirst()){
            do {
                if (cs.getString(7).equals("chitien")){
                    if (cs.getString(4).equals(ngay)){
                        chiHomnay += cs.getInt(1);
                    }
                    if (cs.getString(4).substring(3).equals(thang)){
                        chiThangnay += cs.getInt(1);
                    }
                    if (cs.getString(4).substring(6).equals(nam)){
                        chiNamnay += cs.getInt(1);
                    }
                }

                if (cs.getString(7).equals("thutien")){
                    if (cs.getString(4).equals(ngay)){
                        thuHomnay += cs.getInt(1);
                    }
                    if (cs.getString(4).substring(3).equals(thang)){
                        thuThangnay += cs.getInt(1);
                    }
                    if (cs.getString(4).substring(6).equals(nam)){
                        thuNamnay += cs.getInt(1);
                    }
                }
            }while (cs.moveToNext());
        }

        tvChiHomnay.setText(chiHomnay + " đ");
        tvThuHomnay.setText(thuHomnay + " đ");
        tvChiThangnay.setText(chiThangnay + " đ");
        tvThuThangnay.setText(thuThangnay + " đ");
        tvChiNamnay.setText(chiNamnay + " đ");
        tvThuNamnay.setText(thuNamnay + " đ");

        db.close();
    }

    private void setEvent(){
        relativeLayoutNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentThongkeNgay fragmentThongkeNgay = new FragmentThongkeNgay();
                Bundle bundle = new Bundle();
                bundle.putString("ngay", ngay);
                fragmentThongkeNgay.setArguments(bundle);
                Util.replace(R.id.content_layout, fragmentThongkeNgay,getActivity());
            }
        });

        relativeLayoutThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentThongkeThang fragmentThongkeThang = new FragmentThongkeThang();
                Bundle bundle = new Bundle();
                bundle.putString("thang", thang);
                fragmentThongkeThang.setArguments(bundle);
                Util.replace(R.id.content_layout, fragmentThongkeThang,getActivity());
            }
        });

        relativeLayoutNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentThongkeNam fragmentThongkeNam = new FragmentThongkeNam();
                Bundle bundle = new Bundle();
                bundle.putString("nam", nam);
                fragmentThongkeNam.setArguments(bundle);
                Util.replace(R.id.content_layout, fragmentThongkeNam,getActivity());
            }
        });
    }

}
