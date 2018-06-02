package com.lv.vietcuong.project2.View.BaoCao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class ThuchiNamAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<ThuchiNam> thuchiNams;


    public ThuchiNamAdapter(@NonNull Context context, int resource, ArrayList<ThuchiNam> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.thuchiNams = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ThuchiNamViewHolder thuchiNamViewHolder;
        if (convertView == null){
            thuchiNamViewHolder = new ThuchiNamViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            thuchiNamViewHolder.imgNam = (TextView) convertView.findViewById(R.id.img_thang);
            thuchiNamViewHolder.tvNam = (TextView) convertView.findViewById(R.id.tv_thang);
            thuchiNamViewHolder.tvThuNam = (TextView) convertView.findViewById(R.id.tv_thu_thang);
            thuchiNamViewHolder.tvChiNam = (TextView) convertView.findViewById(R.id.tv_chi_thang);
            thuchiNamViewHolder.tvThuChiNam = (TextView) convertView.findViewById(R.id.tv_thuchi_thang);

            convertView.setTag(thuchiNamViewHolder);
        }else {
            thuchiNamViewHolder = (ThuchiNamViewHolder) convertView.getTag();
        }

        ThuchiNam thuchiNam = thuchiNams.get(position);
        thuchiNamViewHolder.tvNam.setText("Năm ("+thuchiNam.getNam()+")");
        thuchiNamViewHolder.tvThuNam.setText(thuchiNam.getThu()+" đ");
        thuchiNamViewHolder.tvChiNam.setText(thuchiNam.getChi()+" đ");
        thuchiNamViewHolder.tvThuChiNam.setText(thuchiNam.getThu() - thuchiNam.getChi() + " đ");
        thuchiNamViewHolder.imgNam.setText(thuchiNam.getNam());

        return convertView;
    }

    class ThuchiNamViewHolder{
        TextView imgNam;
        TextView tvNam, tvThuNam, tvChiNam, tvThuChiNam;
    }
}
