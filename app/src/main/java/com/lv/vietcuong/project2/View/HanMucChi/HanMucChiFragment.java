package com.lv.vietcuong.project2.View.HanMucChi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Adapter.AdapterHanMucChi;
import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
import com.lv.vietcuong.project2.Model.ObjectClass.HanMucChi;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.ViTien.Fragment_DanhSachTaiKhoan;

import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class HanMucChiFragment extends Fragment implements View.OnClickListener {

    ListView listView, listViewBottom;
    ArrayList<HanMucChi> arrayHanMucChi;
    Button btnThemHanMucChi, btnBottom;
    FloatingActionButton fab;
    View bottomsheet;
    AdapterHanMucChi adapter;
    BottomSheetBehavior behavior;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hanmucchi, container, false);

        createListViewHanMuc(view);
        createBottomSheet(view);

        btnThemHanMucChi = view.findViewById(R.id.btnThemHanMucChi);
        btnThemHanMucChi.setOnClickListener(this);

        return view;
    }

    private void createBottomSheet(View view) {
        bottomsheet = view.findViewById(R.id.bottomsheet);
        behavior = BottomSheetBehavior.from(bottomsheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setPeekHeight(100);

        fab = view.findViewById(R.id.fab);

        btnBottom = bottomsheet.findViewById(R.id.btnButton);
        listViewBottom = bottomsheet.findViewById(R.id.listViewBottom);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        adapter.add("hàng ngày");
        adapter.add("hàng tháng");
        adapter.add("hàng quý");
        adapter.add("hàng năm");
        listViewBottom.setAdapter(adapter);
        listViewBottom.setEnabled(false);

        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                    fab.setImageResource(R.drawable.ic_action_up);
                }

                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    behavior.setPeekHeight(100);

                    fab.setImageResource(R.drawable.ic_action_down);
                }
            }
        });
    }

    private void createListViewHanMuc(View view) {
        listView = view.findViewById(R.id.listViewHanMucChi);
        arrayHanMucChi = SQLHanMucChi.getAllHanMucChi(getActivity());
        adapter = new AdapterHanMucChi(getContext(), R.layout.item_list_hanmucchi, arrayHanMucChi);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final HanMucChi hanMucChi = arrayHanMucChi.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa hạn mức chi?");

                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long result = SQLHanMucChi.deleteHanMucChi(getActivity(), hanMucChi.getIdHanMucChi());
                        if (result > 0){
                            Toast.makeText(getContext(), "Xóa hạn mức chi thành công.", Toast.LENGTH_SHORT).show();
                            arrayHanMucChi.clear();
                            arrayHanMucChi.addAll(SQLHanMucChi.getAllHanMucChi(getActivity()));
                            adapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getContext(), "Xóa hạn mức chi không thành công.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
    }


    @Override
    public void onClick(View view) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment_ThemHanMucChi themHangMucChi = new Fragment_ThemHanMucChi();
        transaction.replace(R.id.content_layout, themHangMucChi);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
