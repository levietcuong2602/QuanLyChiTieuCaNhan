package com.lv.vietcuong.project2.Model.TaiKhoan;

import android.content.Context;
import android.content.SharedPreferences;

import com.lv.vietcuong.project2.ConnectInternet.DownloadJSON;
import com.lv.vietcuong.project2.Model.ObjectClass.TaiKhoan;
import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelTaiKhoan {
    String links = DangNhapActivity.SERVER_NAME;

    public boolean CapNhatTaiKhoan(Context context, String username, String password){
        boolean ketqua = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "CapNhapTaiKhoan");

        HashMap<String, String> hsUser = new HashMap<>();
        hsUser.put("username", username);

        HashMap<String, String> hsPass = new HashMap<>();
        hsPass.put("password", password);

        attrs.add(hsHam);
        attrs.add(hsUser);
        attrs.add(hsPass);

        DownloadJSON downloadJSON = new DownloadJSON(links, attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            String kqua = jsonObject.getString("ketqua");
            if (kqua.equals("thanhcong")){
                ketqua = true;
                CapNhatCacheTaiKhoan(context, "password", password);
            }else{
                ketqua = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ketqua;
    }

    public void CapNhatCacheTaiKhoan(Context context, String title, String data){
        SharedPreferences preferences = context.getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(title,data);
        editor.commit();
    }

    public TaiKhoan getCacheTaiKhoan(Context context){
        SharedPreferences preferences = context.getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);

        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        String hoten = preferences.getString("hoten", "");
        String loaitaikhoan = preferences.getString("loaitaikhoan", "");
        int idgiadinh = preferences.getInt("idgiadinh", -1);

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setUsername(username);
        taiKhoan.setPassword(password);
        taiKhoan.setHoTen(hoten);
        taiKhoan.setIdGiaDinh(idgiadinh);
        taiKhoan.setLoaiTaiKhoan(loaitaikhoan);

        return taiKhoan;
    }

    public boolean CapNhatHoTen(Context context, String username, String hoten){
        boolean ketqua = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "CapNhatHoTen");

        HashMap<String, String> hsUser = new HashMap<>();
        hsUser.put("username", username);

        HashMap<String, String> hsHoTen = new HashMap<>();
        hsHoTen.put("hoten", hoten);

        attrs.add(hsHam);
        attrs.add(hsUser);
        attrs.add(hsHoTen);

        DownloadJSON downloadJSON = new DownloadJSON(links, attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            String kqua = jsonObject.getString("ketqua");
            if (kqua.equals("thanhcong")){
                ketqua = true;
                CapNhatCacheTaiKhoan(context, "hoten", hoten);
            }else{
                ketqua = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ketqua;
    }
}
