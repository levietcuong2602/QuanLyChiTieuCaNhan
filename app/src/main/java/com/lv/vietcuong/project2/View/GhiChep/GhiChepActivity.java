package com.lv.vietcuong.project2.View.GhiChep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lv.vietcuong.project2.R;

/**
 * Created by Administor on 3/26/2018.
 */

public class GhiChepActivity extends Fragment {
    Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_ghichep, container, false);

        toolbar = view.findViewById(R.id.toolBarGhiChep);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        return view;
    }
}
