package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lv.vietcuong.project2.Model.ObjectClass.HinhAnh;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class AdapterGridView extends BaseAdapter {
    Context context;
    int resource;
    ArrayList<HinhAnh> dsHinhAnh;

    public AdapterGridView(Context context, int resource, ArrayList<HinhAnh> dsHinhAnh) {
        this.context = context;
        this.resource = resource;
        this.dsHinhAnh = dsHinhAnh;
    }

    @Override
    public int getCount() {
        return dsHinhAnh.size();
    }

    @Override
    public Object getItem(int i) {
        return dsHinhAnh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(resource, viewGroup, false);

            viewHolder.imageView = view.findViewById(R.id.imgIcon);

            view.setTag(viewHolder);
        }
        byte[]hinhAnh = dsHinhAnh.get(i).getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);

        viewHolder = (ViewHolder) view.getTag();
        viewHolder.imageView.setImageBitmap(bitmap);

        return view;
    }
}
