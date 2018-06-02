package com.lv.vietcuong.project2.View.BaoCao.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.BaoCao.FragmentQuanlyThanhVien;

import java.util.List;

public class ThanhvienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThanhVien> thanhVienList;
    private FragmentQuanlyThanhVien frag;

    public ThanhvienAdapter(Context context, int layout, List<ThanhVien> thanhVienList, FragmentQuanlyThanhVien frag) {
        this.context = context;
        this.layout = layout;
        this.thanhVienList = thanhVienList;
        this.frag = frag;
    }

    @Override
    public int getCount() {
        return thanhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvHoTen;
        ImageView imgXoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvHoTen = view.findViewById(R.id.tv_hoten);
            holder.imgXoa = view.findViewById(R.id.img_xoa);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        final ThanhVien thanhVien = thanhVienList.get(i);

        holder.tvHoTen.setText(thanhVien.getHoTen());

        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xacNhanXoa(thanhVien.getHoTen(), thanhVien.getUsername());
            }
        });
        return view;
    }

    private void xacNhanXoa(String hoTen, final String tentaikhoan){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xoá thành viên "+hoTen+" không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                frag.xoaThanhVien(tentaikhoan);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}
