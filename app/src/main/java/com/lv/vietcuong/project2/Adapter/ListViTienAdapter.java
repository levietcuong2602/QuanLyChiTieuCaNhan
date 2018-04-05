package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lv.vietcuong.project2.Model.Wallet;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administor on 3/26/2018.
 */

public class ListViTienAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Wallet> arrayWallet;

    public ListViTienAdapter(@NonNull Context context, int resource, ArrayList<Wallet> arrayWallet) {
        super(context, resource, arrayWallet);
        this.context = context;
        this.resource = resource;
        this.arrayWallet = arrayWallet;
    }

    public class ViewHolder{
        public TextView textViewNameWallet, textViewSoTien;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);

            viewHolder.textViewNameWallet = convertView.findViewById(R.id.textViewTenViTien);
            viewHolder.textViewSoTien = convertView.findViewById(R.id.textViewSoTien);

            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        Wallet viTien = arrayWallet.get(position);

        viewHolder.textViewSoTien.setText(viTien.getBalance()+"");
        viewHolder.textViewNameWallet.setText(viTien.getNameWallet());

        return convertView;
    }
}
