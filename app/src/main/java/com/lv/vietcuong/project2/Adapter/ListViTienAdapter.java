package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administor on 3/26/2018.
 */

public class ListViTienAdapter extends ArrayAdapter implements View.OnClickListener {
    private Context context;
    private int resource;
    private List<ViTien> arrayWallet;

    public ListViTienAdapter(@NonNull Context context, int resource, ArrayList<ViTien> arrayWallet) {
        super(context, resource, arrayWallet);
        this.context = context;
        this.resource = resource;
        this.arrayWallet = arrayWallet;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.item_chuyenkhoan:
                Toast.makeText(context, "chuyển khoản", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_edit:
                Toast.makeText(context, "chỉnh sửa", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_hanmucchi:
                Toast.makeText(context, "đặt hạn mức chi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_ngungsudung:
                Toast.makeText(context, "ngừng sủ dụng", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public class ViewHolder{
        TextView textViewNameWallet, textViewSoTien;
        ImageView imgDots;

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
            viewHolder.imgDots = convertView.findViewById(R.id.imgDot);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        ViTien viTien = arrayWallet.get(position);

        viewHolder.textViewSoTien.setText(viTien.getSoDu()+"");
        viewHolder.textViewNameWallet.setText(viTien.getTenViTien());

        viewHolder.imgDots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });

        return convertView;
    }

    public void showBottomSheetDialog(){
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.bottom_sheet_fragment_option_vitien);

        LinearLayout layoutChuyenKhoan = dialog.findViewById(R.id.item_chuyenkhoan);
        LinearLayout layoutHanMucChi = dialog.findViewById(R.id.item_hanmucchi);
        LinearLayout layoutEdit = dialog.findViewById(R.id.item_edit);
        LinearLayout layoutNgungSuDung = dialog.findViewById(R.id.item_ngungsudung);

        layoutChuyenKhoan.setOnClickListener(this);
        layoutHanMucChi.setOnClickListener(this);
        layoutEdit.setOnClickListener(this);
        layoutNgungSuDung.setOnClickListener(this);

        dialog.show();
    }

}
