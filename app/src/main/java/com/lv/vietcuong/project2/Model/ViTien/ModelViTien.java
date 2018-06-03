package com.lv.vietcuong.project2.Model.ViTien;

import android.util.Log;
import android.widget.Toast;

import com.lv.vietcuong.project2.ConnectInternet.DownloadJSON;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelViTien {
    private String links = DangNhapActivity.SERVER_VITIEN;

    public boolean saveViTienToServer(ViTien viTien) {
        boolean ketqua = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "ThemViTien");

        HashMap<String, String> hsIdViTien = new HashMap<>();
        hsIdViTien.put("idvitien", viTien.getIdViTien()+"");

        HashMap<String, String> hsTenViTien = new HashMap<>();
        hsTenViTien.put("tenvitien", viTien.getTenViTien());

        HashMap<String, String> hsLoaiViTien = new HashMap<>();
        hsLoaiViTien.put("loaivitien", viTien.getLoaiVi());

        HashMap<String, String> hsSoDu = new HashMap<>();
        hsSoDu.put("sodu", viTien.getSoDu()+"");

        HashMap<String, String> hsGhiChu = new HashMap<>();
        hsGhiChu.put("ghichu", viTien.getGhiChu());

        HashMap<String, String> hsUsername = new HashMap<>();
        hsUsername.put("username", viTien.getUsername());

        HashMap<String, String> hsIdHangMucThu = new HashMap<>();
        hsIdHangMucThu.put("idhangmucthu", viTien.getIdHangMucThu()+"");

        attrs.add(hsHam);
        attrs.add(hsIdViTien);
        attrs.add(hsTenViTien);
        attrs.add(hsLoaiViTien);
        attrs.add(hsSoDu);
        attrs.add(hsGhiChu);
        attrs.add(hsUsername);
        attrs.add(hsIdHangMucThu);

        DownloadJSON downloadJSON = new DownloadJSON(links, attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            String kqua = jsonObject.getString("ketqua");
            if (kqua.equals("thanhcong")) {
                ketqua = true;
            } else {
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
