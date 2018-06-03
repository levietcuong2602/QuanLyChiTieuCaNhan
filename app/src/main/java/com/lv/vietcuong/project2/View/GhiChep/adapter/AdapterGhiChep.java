package com.lv.vietcuong.project2.View.GhiChep.adapter;

import android.app.Activity;
import android.content.Context;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
=======
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Databases.SQLHangMuc;
<<<<<<< HEAD
import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;

<<<<<<< HEAD
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.Model.ObjectClass.ThuTien;
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
<<<<<<< HEAD

            HangMuc hangMuc = SQLHangMuc.getHangMuc(activity, "thutien", ghiChep.getIdHangMuc());
            Bitmap bitmap = SQLHinhAnh.getImage(activity, hangMuc.getIdHinhAnh());
            if (bitmap != null) {
                    viewHolderGhiChep.imgIconHangMuc.setImageBitmap(bitmap);
            }

=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }
        if (ghiChep instanceof ChiTien){
            ChiTien chiTien = (ChiTien) ghiChep;
            viewHolderGhiChep.tvTenViTien.setText(SQLViTien.getTenViTien(activity, chiTien.getIdViTienChi()));
            viewHolderGhiChep.tvLoaiGhiChep.setText("Chi: ");
            viewHolderGhiChep.tvHangMuc.setText(SQLHangMuc.getTenHangMuc(activity,ghiChep.getIdHangMuc()));
<<<<<<< HEAD

            HangMuc hangMuc = SQLHangMuc.getHangMuc(activity, "chitien", ghiChep.getIdHangMuc());
            Bitmap bitmap = SQLHinhAnh.getImage(activity, hangMuc.getIdHinhAnh());
            if (bitmap != null) {
                viewHolderGhiChep.imgIconHangMuc.setImageBitmap(bitmap);
            }
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }
        if (ghiChep instanceof ChuyenKhoan){
            ChuyenKhoan chuyenKhoan = (ChuyenKhoan) ghiChep;
            viewHolderGhiChep.tvTenViTien.setText(SQLViTien.getTenViTien(activity, chuyenKhoan.getIdViTienChi()));
            viewHolderGhiChep.tvLoaiGhiChep.setText("Chuyển tới: ");
            viewHolderGhiChep.tvHangMuc.setText(SQLViTien.getTenViTien(activity, chuyenKhoan.getIdViTienThu()));
<<<<<<< HEAD
            Log.d("tag9", chuyenKhoan.getIdViTienThu()+"");
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        }

        return convertView;
    }

    class ViewHolderGhiChep{
        ImageView imgIconHangMuc;
        TextView tvLoaiGhiChep, tvNgayGhiChep, tvHangMuc, tvSoTien, tvTenViTien;
    }

}
