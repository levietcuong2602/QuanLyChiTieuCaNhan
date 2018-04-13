package com.lv.vietcuong.project2.View.GhiChep.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Databases.DataBaseManager;
import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ChiTien;
import com.lv.vietcuong.project2.Model.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.GhiChep;
import com.lv.vietcuong.project2.Model.ThuTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class AdapterGhiChep extends ArrayAdapter{
    private Context context;
    private int resource;
    private ArrayList<GhiChep> ghiCheps;
    private Activity activity;


    public AdapterGhiChep(@NonNull Context context, int resource, ArrayList<GhiChep> objects, Activity activity) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.ghiCheps = objects;
        this.activity = activity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolderGhiChep viewHolderGhiChep;

        if(convertView == null){
            viewHolderGhiChep = new ViewHolderGhiChep();

            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolderGhiChep.imgIconHangMuc = (ImageView) convertView.findViewById(R.id.img_icon_hangmuc);
            viewHolderGhiChep.tvLoaiGhiChep = (TextView) convertView.findViewById(R.id.tv_loai_ghi_chep);
            viewHolderGhiChep.tvNgayGhiChep = (TextView) convertView.findViewById(R.id.tv_ngayghichep);
            viewHolderGhiChep.tvHangMuc = (TextView) convertView.findViewById(R.id.tv_hangmuc);
            viewHolderGhiChep.tvSoTien = (TextView) convertView.findViewById(R.id.tv_so_tien);
            viewHolderGhiChep.tvTenViTien = (TextView) convertView.findViewById(R.id.tv_ten_vi_tien);

            convertView.setTag(viewHolderGhiChep);
        }else {
            viewHolderGhiChep = (ViewHolderGhiChep) convertView.getTag();
        }

        GhiChep ghiChep = ghiCheps.get(position);

        //viewHolderGhiChep.imgIconHangMuc.setBackgroundResource();
        viewHolderGhiChep.tvNgayGhiChep.setText(ghiChep.getNgay());
        viewHolderGhiChep.tvSoTien.setText(ghiChep.getSoTien()+" đ");

        if (ghiChep instanceof ThuTien) {
            ThuTien thuTien = (ThuTien) ghiChep;
            viewHolderGhiChep.tvTenViTien.setText(SQLViTien.getTenViTien(activity, thuTien.getIdViTienThu()));
            viewHolderGhiChep.tvLoaiGhiChep.setText("Thu: ");
            viewHolderGhiChep.tvHangMuc.setText(SQLHangMuc.getTenHangMuc(activity,ghiChep.getIdHangMuc()));
        }
        if (ghiChep instanceof ChiTien){
            ChiTien chiTien = (ChiTien) ghiChep;
            viewHolderGhiChep.tvTenViTien.setText(SQLViTien.getTenViTien(activity, chiTien.getIdViTienChi()));
            viewHolderGhiChep.tvLoaiGhiChep.setText("Chi: ");
            viewHolderGhiChep.tvHangMuc.setText(SQLHangMuc.getTenHangMuc(activity,ghiChep.getIdHangMuc()));
        }
        if (ghiChep instanceof ChuyenKhoan){
            ChuyenKhoan chuyenKhoan = (ChuyenKhoan) ghiChep;
            viewHolderGhiChep.tvTenViTien.setText(SQLViTien.getTenViTien(activity, chuyenKhoan.getIdViTienChi()));
            viewHolderGhiChep.tvLoaiGhiChep.setText("Chuyển tới: ");
            viewHolderGhiChep.tvHangMuc.setText(SQLViTien.getTenViTien(activity, chuyenKhoan.getIdViTienThu()));
        }

        return convertView;
    }

    class ViewHolderGhiChep{
        ImageView imgIconHangMuc;
        TextView tvLoaiGhiChep, tvNgayGhiChep, tvHangMuc, tvSoTien, tvTenViTien;
    }

}
