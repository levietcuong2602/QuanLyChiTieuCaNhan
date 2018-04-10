package com.lv.vietcuong.project2.View.GhiChep.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lv.vietcuong.project2.Model.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class AdapterMucChi extends ArrayAdapter {
    private Context context;
    private ArrayList<HangMuc> dsHangMucChi;
    private int resource;

    public AdapterMucChi(@NonNull Context context, int resource, ArrayList<HangMuc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dsHangMucChi = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView tvItemMucChi = convertView.findViewById(R.id.tv_item);

        HangMuc hangMucChi = dsHangMucChi.get(position);
        tvItemMucChi.setText(hangMucChi.getTenHangMuc());
        return convertView;
    }
}
