package com.lv.vietcuong.project2.View.GhiChep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lv.vietcuong.project2.R;

import java.util.ArrayList;
import java.util.List;

public class EditGhichepActivity extends AppCompatActivity{
    private Button btnHuy;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ghichep);
        initWidget();
        setSpinner();
        huyEdit();

    }

    private void initWidget() {
        btnHuy = (Button) findViewById(R.id.btn_huy);
        spinner = (Spinner) findViewById(R.id.spn_danhmuc_2);
    }

    private void setSpinner() {
        List<String> list = new ArrayList<>();
        list.add("Chi tiền");
        list.add("Thu tiền");
        list.add("Chuyển khoản");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int id = R.id.fragment_content_2;

                switch (Integer.parseInt(spinner.getItemIdAtPosition(i)+"")){
                    case 0:
                        Util.replace(id, new FragmentEditChitien(), EditGhichepActivity.this);
                        break;
                    case 1:
                        Util.replace(id, new FragmentEditThutien(), EditGhichepActivity.this);
                        break;
                    case 2:
                        Util.replace(id, new FragmentEditChuyenkhoan(), EditGhichepActivity.this);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void huyEdit(){
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
