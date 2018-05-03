package com.lv.vietcuong.project2.View.BaoCao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class ViTienAdapter extends ArrayAdapter{
    private Context context;
    private int resource;
    private ArrayList<ViTien> viTiens;

    public ViTienAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ViTien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.viTiens = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViTienViewHolder viTienViewHolder;
        if (convertView == null){
            viTienViewHolder = new ViTienViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            viTienViewHolder.tvSoTien = (TextView) convertView.findViewById(R.id.textViewSoTien);
            viTienViewHolder.tvSoTien = (TextView) convertView.findViewById(R.id.textViewSoTien);

            convertView.setTag(viTienViewHolder);
        }else {
            viTienViewHolder = (ViTienViewHolder) convertView.getTag();
        }

        ViTien viTien = viTiens.get(position);
        viTienViewHolder.tvTenViTien.setText(viTien.getTenViTien());
        viTienViewHolder.tvSoTien.setText(viTien.getSoDu() + " đ");

        return convertView;
    }

    class ViTienViewHolder{
        TextView tvTenViTien, tvSoTien;
    }
}
