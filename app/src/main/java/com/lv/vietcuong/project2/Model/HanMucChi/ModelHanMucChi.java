package com.lv.vietcuong.project2.Model.HanMucChi;

import android.content.Context;

import com.lv.vietcuong.project2.ConnectInternet.DownloadJSON;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.ObjectClass.HanMucChi;
import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelHanMucChi {

    private String links = DangNhapActivity.SERVER_HANMUCCHI;

    public boolean saveHanMucChiToServer(HanMucChi hanMucChi) {
        boolean ketqua = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "ThemHanMucChi");

        HashMap<String, String> hsIdHanMucChi = new HashMap<>();
        hsIdHanMucChi.put("idhanmucchi", hanMucChi.getIdHanMucChi()+"");

        HashMap<String, String> hsTenHanMuc = new HashMap<>();
        hsTenHanMuc.put("tenhanmucchi", hanMucChi.getTenHanMucChi());

        HashMap<String, String> hsLapLai = new HashMap<>();
        hsLapLai.put("laplai", hanMucChi.getLapLai());

        HashMap<String, String> hsNgayBatDau = new HashMap<>();
        hsNgayBatDau.put("ngaybatdau", hanMucChi.getNgayBatDau());

        HashMap<String, String> hsNgayKetThuc = new HashMap<>();
        hsNgayKetThuc.put("ngayketthuc", hanMucChi.getNgayKetThuc());

        HashMap<String, String> hsSoTien = new HashMap<>();
        hsSoTien.put("sotien", hanMucChi.getSoTien()+"");

        HashMap<String, String> hsUsername = new HashMap<>();
        hsUsername.put("username", hanMucChi.getUsername());

        attrs.add(hsHam);
        attrs.add(hsIdHanMucChi);
        attrs.add(hsTenHanMuc);
        attrs.add(hsLapLai);
        attrs.add(hsNgayBatDau);
        attrs.add(hsNgayKetThuc);
        attrs.add(hsSoTien);
        attrs.add(hsUsername);

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

    public boolean deleteHanMucChiToServer(HanMucChi hanMucChi) {
        boolean ketqua = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "XoaHanMucChi");

        HashMap<String, String> hsIdHanMucChi = new HashMap<>();
        hsIdHanMucChi.put("idhanmucchi", hanMucChi.getIdHanMucChi()+"");

        HashMap<String, String> hsUsername = new HashMap<>();
        hsUsername.put("username", hanMucChi.getUsername());

        attrs.add(hsHam);
        attrs.add(hsIdHanMucChi);
        attrs.add(hsUsername);

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
