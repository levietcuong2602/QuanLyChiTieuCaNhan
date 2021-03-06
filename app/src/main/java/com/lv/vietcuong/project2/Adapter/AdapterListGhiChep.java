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

import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
<<<<<<< HEAD
=======
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class AdapterListGhiChep extends ArrayAdapter<GhiChep> {
    private Context context;
    private int resource;
    private ArrayList<GhiChep> dsGhiChep;
<<<<<<< HEAD

    public AdapterListGhiChep(@NonNull Context context, int resource, @NonNull ArrayList<GhiChep> objects) {
=======
    private ViTien viTien;
    public int soTienTemp;
    public AdapterListGhiChep(@NonNull Context context, int resource, @NonNull ArrayList<GhiChep> objects, ViTien viTien) {
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dsGhiChep = objects;
<<<<<<< HEAD
=======
        this.viTien = viTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    }

    public class ViewHolder{
        ImageView imgage;
        TextView tvLoaiGhiChep, tvTenHangMuc, tvSoTien, tvSoTienConLai, tvNgay;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thongtinghichep, parent, false);

            viewHolder.imgage = convertView.findViewById(R.id.ImageView);
            viewHolder.tvLoaiGhiChep = convertView.findViewById(R.id.tvLoaiGhiChep);
            viewHolder.tvTenHangMuc = convertView.findViewById(R.id.ten_hang_muc);
            viewHolder.tvSoTien = convertView.findViewById(R.id.so_tien_ghi_chep);
            viewHolder.tvSoTienConLai = convertView.findViewById(R.id.so_tien_con_lai);
            viewHolder.tvNgay = convertView.findViewById(R.id.tvNgayGhiChep);

            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        GhiChep ghiChep = dsGhiChep.get(position);

        viewHolder.tvNgay.setText(ghiChep.getNgay());
        viewHolder.tvLoaiGhiChep.setText(ghiChep.getLoaiGhiChep());
        viewHolder.tvSoTienConLai.setText("");
        viewHolder.tvTenHangMuc.setText(ghiChep.getIdHangMuc()+"");
        viewHolder.tvSoTien.setText(ghiChep.getSoTien()+"");

<<<<<<< HEAD
=======
        if (position == 0){
            soTienTemp = viTien.getSoDu();
        }else{
            soTienTemp += dsGhiChep.get(position-1).getSoTien();
        }
        viewHolder.tvSoTienConLai.setText(soTienTemp+"");
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

        return convertView;
    }
}
