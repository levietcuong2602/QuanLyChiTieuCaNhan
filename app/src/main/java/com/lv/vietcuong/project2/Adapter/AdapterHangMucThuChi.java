package com.lv.vietcuong.project2.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class AdapterHangMucThuChi extends ArrayAdapter<HangMuc> {

    Activity context;
    int resource;
    ArrayList<HangMuc> dsHangMuc;

    public AdapterHangMucThuChi(@NonNull Activity context, int resource, @NonNull ArrayList<HangMuc> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.dsHangMuc = objects;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder.imageView = convertView.findViewById(R.id.iconHangMuc);
            viewHolder.textView = convertView.findViewById(R.id.tvTenHangMuc);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        HangMuc hangMuc = dsHangMuc.get(position);
        viewHolder.textView.setText(hangMuc.getTenHangMuc());
        Bitmap bitmap = SQLHinhAnh.getImage(context, hangMuc.getIdHinhAnh());
        if (bitmap != null) {
            viewHolder.imageView.setImageBitmap(bitmap);
            Log.d("HinhAnh", "khác null");
        }

        return convertView;
    }
}
