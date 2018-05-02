package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lv.vietcuong.project2.Model.ObjectClass.HinhAnh;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.HangMucThuChi.Fragment_ListIcon;
import com.lv.vietcuong.project2.View.HangMucThuChi.Fragment_ThemHangMuc;

import java.util.ArrayList;

public class AdapterHinhAnh extends RecyclerView.Adapter<AdapterHinhAnh.ViewHolder> {
    Context context;
    int resource;
    ArrayList<HinhAnh> arrayHinhAnh;

    public AdapterHinhAnh(Context context, int resource, ArrayList<HinhAnh> arrayHinhAnh){
        this.context = context;
        this.resource = resource;
        this.arrayHinhAnh = arrayHinhAnh;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        byte[]hinhAnh = arrayHinhAnh.get(position).getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return arrayHinhAnh.size();
    }
}
