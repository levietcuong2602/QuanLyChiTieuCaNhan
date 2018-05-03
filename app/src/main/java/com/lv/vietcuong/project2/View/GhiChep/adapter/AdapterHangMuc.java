package com.lv.vietcuong.project2.View.GhiChep.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;


public class AdapterHangMuc extends ArrayAdapter {
    private Context context;
    private ArrayList<HangMuc> dsHangMuc;
    private int resource;

    public AdapterHangMuc(@NonNull Context context, int resource, ArrayList<HangMuc> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dsHangMuc = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolderHangMuc viewHolderHangMuc;
        if (convertView == null){
            viewHolderHangMuc = new ViewHolderHangMuc();

            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolderHangMuc.tvItemHangMuc = convertView.findViewById(R.id.tv_item);

            convertView.setTag(viewHolderHangMuc);
        }

        viewHolderHangMuc = (ViewHolderHangMuc) convertView.getTag();
        HangMuc hangMuc = dsHangMuc.get(position);
        viewHolderHangMuc.tvItemHangMuc.setText(hangMuc.getTenHangMuc());

        return convertView;
    }

    class ViewHolderHangMuc{
        TextView tvItemHangMuc;
    }
}
