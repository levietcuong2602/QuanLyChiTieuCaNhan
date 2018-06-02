package com.lv.vietcuong.project2.View.GhiChep.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class AdapterTaiKhoan extends ArrayAdapter {
    private Context context;
    private ArrayList<ViTien> dsViTien;
    private int resource;

    public AdapterTaiKhoan(@NonNull Context context, int resource, ArrayList<ViTien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dsViTien = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView tvItemViTien = convertView.findViewById(R.id.tv_item);

        ViTien viTien = dsViTien.get(position);
        tvItemViTien.setText(viTien.getTenViTien());
        return convertView;
    }
}
