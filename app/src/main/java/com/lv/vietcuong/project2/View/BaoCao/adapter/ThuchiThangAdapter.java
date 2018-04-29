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

public class ThuchiThangAdapter extends ArrayAdapter{
    private Context context;
    private int resource;
    private ArrayList<ThuchiThang> thuchiThangs;

    public ThuchiThangAdapter(@NonNull Context context, int resource, ArrayList<ThuchiThang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.thuchiThangs = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ThuchiThangViewHolder thuchiThangViewHolder;
        if (convertView == null){
            thuchiThangViewHolder = new ThuchiThangViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            thuchiThangViewHolder.imgThang = (ImageView) convertView.findViewById(R.id.img_thang);
            thuchiThangViewHolder.tvThang = (TextView) convertView.findViewById(R.id.tv_thang);
            thuchiThangViewHolder.tvThuThang = (TextView) convertView.findViewById(R.id.tv_thu_thang);
            thuchiThangViewHolder.tvChiThang = (TextView) convertView.findViewById(R.id.tv_chi_thang);
            thuchiThangViewHolder.tvThuChiThang = (TextView) convertView.findViewById(R.id.tv_thuchi_thang);

            convertView.setTag(thuchiThangViewHolder);
        }else {
            thuchiThangViewHolder = (ThuchiThangViewHolder) convertView.getTag();
        }

        ThuchiThang thuchiThang = thuchiThangs.get(position);
        thuchiThangViewHolder.tvThang.setText("Tháng ("+thuchiThang.getThang()+")");
        thuchiThangViewHolder.tvThuThang.setText(thuchiThang.getThu()+" đ");
        thuchiThangViewHolder.tvChiThang.setText(thuchiThang.getChi()+" đ");
        thuchiThangViewHolder.tvThuChiThang.setText(thuchiThang.getThu() - thuchiThang.getChi() + " đ");

        return convertView;
    }

    class ThuchiThangViewHolder{
        ImageView imgThang;
        TextView tvThang, tvThuThang, tvChiThang, tvThuChiThang;
    }
}
