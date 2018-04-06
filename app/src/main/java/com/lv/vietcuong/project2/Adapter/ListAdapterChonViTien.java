package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.lv.vietcuong.project2.Model.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;

public class ListAdapterChonViTien extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ViTien> dsWallet;

    public ListAdapterChonViTien(@NonNull Context context, int resource, @NonNull ArrayList<ViTien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dsWallet = objects;
    }

    public class ViewHolder{
        CheckedTextView textView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder.textView = convertView.findViewById(R.id.tenViTien);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();

        ViTien wallet = dsWallet.get(position);
        holder.textView.setText(wallet.getNameWallet());
        holder.textView.setChecked(true);


        return convertView;
    }
}
