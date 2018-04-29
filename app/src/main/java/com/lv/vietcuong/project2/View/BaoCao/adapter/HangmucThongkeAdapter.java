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

public class HangmucThongkeAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<HangmucThongke> hangmucThongkes;


    public HangmucThongkeAdapter(@NonNull Context context, int resource, ArrayList<HangmucThongke> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.hangmucThongkes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HangmucThangViewHolder hangmucThangViewHolder;
        if (convertView == null){
            hangmucThangViewHolder  = new HangmucThangViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            hangmucThangViewHolder.tvHangmucThang = convertView.findViewById(R.id.tv_hang_muc_thang);
            hangmucThangViewHolder.tvSotienThang = convertView.findViewById(R.id.tv_sotien_thang);
            hangmucThangViewHolder.tvPhantramThang = convertView.findViewById(R.id.phantram_thang);

            convertView.setTag(hangmucThangViewHolder);
        }else {
            hangmucThangViewHolder = (HangmucThangViewHolder) convertView.getTag();
        }

        HangmucThongke hangmucThongke = hangmucThongkes.get(position);
        hangmucThangViewHolder.tvHangmucThang.setText(hangmucThongke.getTenHangMuc());
        hangmucThangViewHolder.tvSotienThang.setText(hangmucThongke.getSoTien() + " đ");
        hangmucThangViewHolder.tvPhantramThang.setText("("+ hangmucThongke.getPhanTram() + "%)");

        return convertView;
    }

    class HangmucThangViewHolder{
        TextView tvHangmucThang, tvSotienThang, tvPhantramThang;
    }
}
