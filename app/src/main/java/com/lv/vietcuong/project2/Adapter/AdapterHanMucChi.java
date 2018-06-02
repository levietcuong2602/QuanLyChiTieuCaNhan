package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lv.vietcuong.project2.Model.ObjectClass.HanMucChi;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class AdapterHanMucChi extends ArrayAdapter<HanMucChi>{

    private Context context;
    private int resource;
    private ArrayList<HanMucChi> arrayHanMucChi;

    public AdapterHanMucChi(@NonNull Context context, int resource, @NonNull ArrayList<HanMucChi> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayHanMucChi = objects;
    }

    public class ViewHolder{
        TextView textViewTenHanMucChi, textViewSoHanMucChi;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viewHolder.textViewSoHanMucChi = convertView.findViewById(R.id.textViewSoHanMucChi);
            viewHolder.textViewTenHanMucChi = convertView.findViewById(R.id.textViewTenHanMucChi);

            convertView.setTag(viewHolder);
        }
        HanMucChi hanMucChi = arrayHanMucChi.get(position);

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.textViewTenHanMucChi.setText(hanMucChi.getTenHanMucChi());
        viewHolder.textViewSoHanMucChi.setText(hanMucChi.getSoTien()+"");

        return convertView;
    }
}
