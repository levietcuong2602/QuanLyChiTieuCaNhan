package com.lv.vietcuong.project2.Model.GhiChep;

import com.lv.vietcuong.project2.ConnectInternet.DownloadJSON;
import com.lv.vietcuong.project2.Model.ObjectClass.ChiTien;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.ThuTien;
import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelGhiChep {
    private String links = DangNhapActivity.SERVER_GHICHEP;
    public boolean saveGhiChepToServer(GhiChep ghiChep) {
        boolean ketqua = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsIdGhiChep = new HashMap<>();
        hsIdGhiChep.put("idghichep", ghiChep.getIdGhiChep()+"");
        attrs.add(hsIdGhiChep);

        HashMap<String, String> hsSoTien = new HashMap<>();
        hsSoTien.put("sotien", ghiChep.getSoTien()+"");
        attrs.add(hsSoTien);

        HashMap<String, String> hsDienGiai = new HashMap<>();
        hsDienGiai.put("diengiai", ghiChep.getDienDai());
        attrs.add(hsDienGiai);

        HashMap<String, String> hsUsername = new HashMap<>();
        hsUsername.put("username", ghiChep.getUsername());
        attrs.add(hsUsername);

        HashMap<String, String> hsNgay = new HashMap<>();
        hsNgay.put("ngay", ghiChep.getNgay());
        attrs.add(hsNgay);

        HashMap<String, String> hsDiaDiem = new HashMap<>();
        hsDiaDiem.put("diadiem", ghiChep.getDiaDiem());
        attrs.add(hsDiaDiem);

        HashMap<String, String> hsIdhangMuc = new HashMap<>();
        hsIdhangMuc.put("idhangmuc", ghiChep.getIdHangMuc()+"");
        attrs.add(hsIdhangMuc);

        HashMap<String, String> hsLoaiGhiChep = new HashMap<>();
        hsLoaiGhiChep.put("loaighichep", ghiChep.getLoaiGhiChep());
        attrs.add(hsLoaiGhiChep);

        HashMap<String, String> hsHam = new HashMap<>();
        if (ghiChep.getLoaiGhiChep().equals("thu")){
            hsHam.put("ham", "ThemGhiChepThu");

            HashMap<String, String> hsThuTuAi = new HashMap<>();
            hsThuTuAi.put("thutuai", ((ThuTien)ghiChep).getThuTuAi());
            attrs.add(hsThuTuAi);

            HashMap<String, String> hsIdViTien = new HashMap<>();
            hsIdViTien.put("idvitien", ((ThuTien)ghiChep).getIdViTienThu()+"");
            attrs.add(hsIdViTien);

        }else  if (ghiChep.getLoaiGhiChep().equals("chi")){
            hsHam.put("ham", "ThemGhiChepChi");

            HashMap<String, String> hsChiChoAi = new HashMap<>();
            hsChiChoAi.put("chichoai", ((ChiTien)ghiChep).getChiChoAi());
            attrs.add(hsChiChoAi);

            HashMap<String, String> hsIdViTien = new HashMap<>();
            hsIdViTien.put("idvitien", ((ChiTien)ghiChep).getIdViTienChi()+"");
            attrs.add(hsIdViTien);
        }else  if (ghiChep.getLoaiGhiChep().equals("chuyenkhoan")){
            hsHam.put("ham", "ThemGhiChepChuyenKhoan");

            HashMap<String, String> hsIdViTienThu = new HashMap<>();
            hsIdViTienThu.put("idvitienthu", ((ChiTien)ghiChep).getChiChoAi());
            attrs.add(hsIdViTienThu);

            HashMap<String, String> hsIdViTienChi = new HashMap<>();
            hsIdViTienChi.put("idvitienchi", ((ChiTien)ghiChep).getIdViTienChi()+"");
            attrs.add(hsIdViTienChi);
        }
        attrs.add(hsHam);

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
