package com.lv.vietcuong.project2.View.GhiChep;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.lv.vietcuong.project2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Util {
    public static void replace(int id, Fragment fragment, FragmentActivity activity){
        android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }









    public static void getDefaultInfor(Calendar cal, TextView textView) {
        //lấy ngày hiện tại của hệ thống
        cal = Calendar.getInstance();
        SimpleDateFormat dft = null;
        //Định dạng ngày / tháng /năm
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        //hiển thị lên giao diện
        textView.setText(strDate);
    }

    public static void showDatePickerDialog(final TextView textView, Activity activity) {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                if (dayOfMonth<10 && monthOfYear < 9){
                    textView.setText("0"+(dayOfMonth) +"/0"+(monthOfYear+1)+"/"+year);
                }else if (monthOfYear < 9){
                    textView.setText((dayOfMonth) +"/0"+(monthOfYear+1)+"/"+year);
                }else if (dayOfMonth < 10){
                    textView.setText("0"+(dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                }else {
                    textView.setText((dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                }
                //Lưu vết lại biến ngày hoàn thành
                // cal.set(year, monthOfYear, dayOfMonth);
                // dateFinish = cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = textView.getText()+"";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1])-1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(activity, callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày chi tiêu");
        pic.show();
    }




}