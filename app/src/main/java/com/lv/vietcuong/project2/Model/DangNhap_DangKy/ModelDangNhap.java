package com.lv.vietcuong.project2.Model.DangNhap_DangKy;

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

public class ModelDangNhap {

    public void CapNhapCacheDangNhap(Context context, TaiKhoan taiKhoan){
        SharedPreferences preferences = context.getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", taiKhoan.getHoTen());
        editor.putString("password", taiKhoan.getPassword());
        editor.putString("hoten", taiKhoan.getHoTen());
        editor.putString("loaitaikhoan", taiKhoan.getLoaiTaiKhoan());
        editor.putInt("idgiadinh", taiKhoan.getIdGiaDinh());
        editor.commit();
    }

    public boolean KiemTraDangNhap(Context context, String username, String password){
        String links = DangNhapActivity.SERVER_NAME;
        boolean result = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "KiemTraDangNhap");

        HashMap<String, String> hsUser = new HashMap<>();
        hsUser.put("tentaikhoan", username);

        HashMap<String, String> hsPass = new HashMap<>();
        hsPass.put("matkhau", password);

        attrs.add(hsHam);
        attrs.add(hsUser);
        attrs.add(hsPass);

        DownloadJSON downloadJSON = new DownloadJSON(links, attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            String ketqua = jsonObject.getString("ketqua");

            if (ketqua.equals("thanhcong")){
                result = true;

                String hoten = jsonObject.getString("hoten");
                String loaitaikhoan = jsonObject.getString("loaitaikhoan");
                int idgiadinh = jsonObject.getInt("idgiadinh");

                TaiKhoan taiKhoan = new TaiKhoan();
                taiKhoan.setUsername(username);
                taiKhoan.setPassword(password);
                taiKhoan.setHoTen(hoten);
                taiKhoan.setLoaiTaiKhoan(loaitaikhoan);
                taiKhoan.setIdGiaDinh(idgiadinh);

                CapNhapCacheDangNhap(context, taiKhoan);

            }else {
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
