package com.lv.vietcuong.project2.View.BaoCao.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ThuTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class ViTienAdapter extends ArrayAdapter{
    private Context context;
    private int resource;
    private ArrayList<ViTien> viTiens;
    private int soTienThayDoi;

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

            viTienViewHolder.tvTenViTien = (TextView) convertView.findViewById(R.id.tv_ten_vitien);
            viTienViewHolder.tvSoDuBanDau = (TextView) convertView.findViewById(R.id.tv_sodu_bandau);
            viTienViewHolder.tvSoTienCon = (TextView) convertView.findViewById(R.id.tv_sotien_con);

            convertView.setTag(viTienViewHolder);
        }else {
            viTienViewHolder = (ViTienViewHolder) convertView.getTag();
        }

        ViTien viTien = viTiens.get(position);
        viTienViewHolder.tvTenViTien.setText(viTien.getTenViTien());
        viTienViewHolder.tvSoDuBanDau.setText(viTien.getSoDu() + "");
        soTienThayDoi = getSoTienThayDoi(viTien.getIdViTien());
        viTienViewHolder.tvSoTienCon.setText(viTien.getSoDu() + soTienThayDoi + "");

        return convertView;
    }

    class ViTienViewHolder{
        TextView tvTenViTien, tvSoDuBanDau, tvSoTienCon;
    }

    public int getSoTienThayDoi(int idViTien) {
        ArrayList<GhiChep> dsGhiChep;

        int soTienChi = 0;
        int soTienThu = 0;
        int soTienThayDoi;

        dsGhiChep =  SQLGhiChep.getGhiChepViTien((Activity) getContext(), idViTien);
        for (GhiChep ghiChep: dsGhiChep) {
            if (ghiChep instanceof ChiTien){
                soTienChi += ghiChep.getSoTien();
            }else if (ghiChep instanceof ThuTien){
                soTienThu += ghiChep.getSoTien();
            }else if (ghiChep instanceof ChuyenKhoan){
                if (((ChuyenKhoan) ghiChep).getIdViTienChi() == idViTien){
                    soTienChi += ghiChep.getSoTien();
                }
                if (((ChuyenKhoan) ghiChep).getIdViTienThu() == idViTien){
                    soTienThu += ghiChep.getSoTien();
                }
            }
        }

        soTienThayDoi = soTienThu - soTienChi;
        return soTienThayDoi;
    }
}
