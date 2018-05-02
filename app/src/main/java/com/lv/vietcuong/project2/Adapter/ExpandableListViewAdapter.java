package com.lv.vietcuong.project2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Activity context;
    private List<HangMuc> listHeader;
    private HashMap<Integer, List<HangMuc>> listItem;

    public ExpandableListViewAdapter(Activity context, List<HangMuc> listHeader, HashMap<Integer, List<HangMuc>> listItem) {
        this.context = context;
        this.listHeader = listHeader;
        this.listItem = listItem;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listItem.get(listHeader.get(i).getIdHangMuc()).size();
    }

    @Override
    public Object getGroup(int i) {
        return listHeader.get(i);
    }

    @Override
    public Object getChild(int groupPositiom, int childPosition) {
        return listItem.get(listHeader.get(groupPositiom).getIdHangMuc()).get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    class ViewHolderCha{
        ImageView imgIndicator;
        ImageView imgIconHangMuc;
        TextView textView;
    }

    @Override
    public View getGroupView(int i, final boolean isExpanded, View view, ViewGroup viewGroup) {
        HangMuc hangMuc = (HangMuc) getGroup(i);
        String titleHeader = hangMuc.getTenHangMuc();
        ViewHolderCha viewHolderCha;
        if (view == null){
            viewHolderCha = new ViewHolderCha();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_header_expandable, null);

            viewHolderCha.imgIndicator = view.findViewById(R.id.imIndicator);
            viewHolderCha.imgIconHangMuc = view.findViewById(R.id.imageIconHangMuc);
            viewHolderCha.textView = view.findViewById(R.id.tvHeader);

            view.setTag(viewHolderCha);
        }
        viewHolderCha = (ViewHolderCha) view.getTag();


        viewHolderCha.textView.setText(titleHeader);
        Bitmap bitmap = SQLHinhAnh.getImage(context, hangMuc.getIdHinhAnh());
        if (bitmap != null){
            viewHolderCha.imgIconHangMuc.setImageBitmap(bitmap);
        }

        int childCount = getChildrenCount(i);
        if (childCount > 0 && isExpanded){
            viewHolderCha.imgIndicator.setImageResource(R.drawable.icon_down);
        }else {
            viewHolderCha.imgIndicator.setImageResource(R.drawable.icon_up);
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getX() < 60 && motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (view.isSelected()){
                        view.setSelected(false);
                    }else {
                        view.setSelected(true);
                    }
                }
                return false;
            }
        });

        return view;
    }

    class ViewHolderCon{
        ImageView imgIconHangMuc;
        TextView textView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        HangMuc hangMuc = (HangMuc) getChild(i, i1);
        String titleItem = hangMuc.getTenHangMuc();

        ViewHolderCon holderCon;
        if (view == null){
            holderCon = new ViewHolderCon();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_item_expandable, null);
            holderCon.textView = view.findViewById(R.id.tvItem);
            holderCon.imgIconHangMuc = view.findViewById(R.id.imageIcon);

            view.setTag(holderCon);
        }

        holderCon = (ViewHolderCon) view.getTag();
        holderCon.textView.setText(titleItem);

        Bitmap bitmap = SQLHinhAnh.getImage(context, hangMuc.getIdHinhAnh());
        if (bitmap != null){
            holderCon.imgIconHangMuc.setImageBitmap(bitmap);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
