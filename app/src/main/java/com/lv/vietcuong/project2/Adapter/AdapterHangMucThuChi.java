package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Model.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class AdapterHangMucThuChi extends ArrayAdapter<HangMuc> {

    Context context;
    int resource;
    ArrayList<HangMuc> dsHangMuc;
    public AdapterHangMucThuChi(@NonNull Context context, int resource, @NonNull ArrayList<HangMuc> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.dsHangMuc = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        ImageView imageView = convertView.findViewById(R.id.iconHangMuc);
        TextView textView = convertView.findViewById(R.id.tvTenHangMuc);

        HangMuc hangMuc = dsHangMuc.get(position);

        textView.setText(hangMuc.getTenHangMuc());

        return convertView;
    }
}
