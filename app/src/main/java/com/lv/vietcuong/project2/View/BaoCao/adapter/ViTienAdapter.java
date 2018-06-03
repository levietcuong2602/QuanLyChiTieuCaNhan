package com.lv.vietcuong.project2.View.BaoCao.adapter;

<<<<<<< HEAD
import android.app.Activity;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

<<<<<<< HEAD
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.ChuyenKhoan;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ThuTien;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class ViTienAdapter extends ArrayAdapter{
    private Context context;
    private int resource;
    private ArrayList<ViTien> viTiens;
<<<<<<< HEAD
    private int soTienThayDoi;
=======
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

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
<<<<<<< HEAD

            viTienViewHolder.tvTenViTien = (TextView) convertView.findViewById(R.id.tv_ten_vitien);
            viTienViewHolder.tvSoDuBanDau = (TextView) convertView.findViewById(R.id.tv_sodu_bandau);
            viTienViewHolder.tvSoTienCon = (TextView) convertView.findViewById(R.id.tv_sotien_con);
=======
            viTienViewHolder.tvSoTien = (TextView) convertView.findViewById(R.id.textViewSoTien);
            viTienViewHolder.tvSoTien = (TextView) convertView.findViewById(R.id.textViewSoTien);
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

            convertView.setTag(viTienViewHolder);
        }else {
            viTienViewHolder = (ViTienViewHolder) convertView.getTag();
        }

        ViTien viTien = viTiens.get(position);
        viTienViewHolder.tvTenViTien.setText(viTien.getTenViTien());
<<<<<<< HEAD
        viTienViewHolder.tvSoDuBanDau.setText(viTien.getSoDu() + "");
        soTienThayDoi = getSoTienThayDoi(viTien.getIdViTien());
        viTienViewHolder.tvSoTienCon.setText(viTien.getSoDu() + soTienThayDoi + "");
=======
        viTienViewHolder.tvSoTien.setText(viTien.getSoDu() + " đ");
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6

        return convertView;
    }

    class ViTienViewHolder{
<<<<<<< HEAD
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
=======
        TextView tvTenViTien, tvSoTien;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
    }
}
