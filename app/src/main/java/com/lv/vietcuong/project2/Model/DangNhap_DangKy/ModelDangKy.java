package com.lv.vietcuong.project2.Model.DangNhap_DangKy;

<<<<<<< HEAD
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.lv.vietcuong.project2.ConnectInternet.DownloadJSON;
import com.lv.vietcuong.project2.Databases.DataBaseManager;
=======
import android.util.Log;

import com.lv.vietcuong.project2.ConnectInternet.DownloadJSON;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {

    //thuc hien chusc nang voi csdl
    public boolean DangKyTaiKhoan(TaiKhoan taiKhoan){
<<<<<<< HEAD
        String links = DangNhapActivity.SERVER_NAME;
=======
        String links = DangNhapActivity.SERVER_TAIKHOAN;
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
        boolean result = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "ThemTaiKhoan");

        HashMap<String, String> hsTenTK = new HashMap<>();
        hsTenTK.put("tentaikhoan", taiKhoan.getUsername());

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", taiKhoan.getPassword());

        HashMap<String, String> hsHoTen = new HashMap<>();
        hsHoTen.put("hoten", taiKhoan.getHoTen());

        HashMap<String, String> hsIDGiaDinh = new HashMap<>();
        hsIDGiaDinh.put("idgiadinh", taiKhoan.getIdGiaDinh()+"");

        HashMap<String, String> hsLoaiTK= new HashMap<>();
        hsLoaiTK.put("loaitaikhoan", taiKhoan.getLoaiTaiKhoan());

        attrs.add(hsHam);
        attrs.add(hsTenTK);
        attrs.add(hsMatKhau);
        attrs.add(hsHoTen);
        attrs.add(hsIDGiaDinh);
        attrs.add(hsLoaiTK);

        DownloadJSON downloadJSON = new DownloadJSON(links, attrs);
        downloadJSON.execute();

        try {
            String dulieuJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String ketqua = jsonObject.getString("ketqua");

            Log.d("kiemtra", ketqua);
            if (ketqua.equals("thanhcong")){
                result = true;
            }else{
                result = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
