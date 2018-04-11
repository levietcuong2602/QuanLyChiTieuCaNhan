package com.lv.vietcuong.project2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listHeader;
    private HashMap<String, List<String>> listItem;

    public ExpandableListViewAdapter(Context context, List<String> listHeader, HashMap<String, List<String>> listItem) {
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
        return listItem.get(listHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listHeader.get(i);
    }

    @Override
    public Object getChild(int groupPositiom, int childPosition) {
        return listItem.get(listHeader.get(groupPositiom)).get(childPosition);
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

    @Override
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup viewGroup) {
        String titleHeader = (String) getGroup(i);

        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_header_expandable, null);
        }

        ImageView imgIndicator = view.findViewById(R.id.imIndicator);
        ImageView imgIconHangMuc = view.findViewById(R.id.imageIconHangMuc);
        TextView textView = view.findViewById(R.id.tvHeader);

        textView.setText(titleHeader);

        int childCount = getChildrenCount(i);
        if (childCount > 0 && isExpanded){
            imgIndicator.setImageResource(R.drawable.icon_down);
        }else {
            imgIndicator.setImageResource(R.drawable.icon_up);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        String titleItem = (String) getChild(i, i1);
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_item_expandable, null);
        }

        TextView textView = view.findViewById(R.id.tvItem);
        textView.setText(titleItem);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
