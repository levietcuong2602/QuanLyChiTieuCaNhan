package com.lv.vietcuong.project2.View.GhiChep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.R;

public class DaGhiActivity extends AppCompatActivity {
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daghi_activity);

        initWidget();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void initWidget(){
        btnBack = (Button) findViewById(R.id.btn_back);
    }
}
