package com.lv.vietcuong.project2.View.BaoCao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThanhVien;
import com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class FragmentTaichinhThanhVien extends Fragment {
    private TextView tvHomnay, imgHomnay, tvThangnay, imgThangnay, tvNamnay, imgNamnay, tvThuHomnay, tvChiHomnay, tvThuThangnay, tvChiThangnay, tvThuNamnay, tvChiNamnay, tvHoten;
    private RelativeLayout relativeLayoutThang, relativeLayoutNam, relativeLayoutNgay;
    String ngay, thang, nam;

    ThanhVien thanhVien;
    ArrayList<GhiChep> ghiCheps = new ArrayList<>();
    String urlGetGhichep = DangNhapActivity.HOST +"thanhvien/getGhichep.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baocao_tinhhinhthuchi_hientai, container, false);

        Bundle bundle = getArguments();
        thanhVien = (ThanhVien) bundle.getSerializable("thanhvien");

        initWidget(view);

        tvHoten.setText("Thành viên: "+thanhVien.getHoTen());
        getGhichep();
        setNgay();



        return view;
    }

    private void initWidget(View v){
        tvHomnay = (TextView) v.findViewById(R.id.tv_homnay);
        tvThangnay = (TextView) v.findViewById(R.id.tv_thangnay);
        tvNamnay = (TextView) v.findViewById(R.id.tv_namnay);

        imgHomnay = (TextView) v.findViewById(R.id.img_homnay);
        imgThangnay = (TextView) v.findViewById(R.id.img_thangnay);
        imgNamnay = (TextView) v.findViewById(R.id.img_namnay);

        tvThuHomnay = (TextView) v.findViewById(R.id.tv_thu_homnay);
        tvChiHomnay = (TextView) v.findViewById(R.id.tv_chi_homnay);
        tvThuThangnay = (TextView) v.findViewById(R.id.tv_thu_thangnay);
        tvChiThangnay = (TextView) v.findViewById(R.id.tv_chi_thangnay);
        tvThuNamnay = (TextView) v.findViewById(R.id.tv_thu_namnay);
        tvChiNamnay = (TextView) v.findViewById(R.id.tv_chi_namnay);
        tvHoten = (TextView) v.findViewById(R.id.tv_hoten_thanhvien);

        relativeLayoutNgay = (RelativeLayout) v.findViewById(R.id.rlt_homnay);
        relativeLayoutThang = (RelativeLayout) v.findViewById(R.id.rlt_thangnay);
        relativeLayoutNam = (RelativeLayout) v.findViewById(R.id.rlt_namnay);
    }

    private void setNgay(){
        //lấy ngày hiện tại của hệ thống
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dftHomnay = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        ngay = dftHomnay.format(cal.getTime());
        //hiển thị lên giao diện
        tvHomnay.setText("Hôm nay (" +ngay+ ")");
        imgHomnay.setText(ngay.substring(0,2));


        SimpleDateFormat dftThangnay = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        thang = dftThangnay.format(cal.getTime());
        //hiển thị lên giao diện
        tvThangnay.setText("Tháng này (" +thang+ ")");
        imgThangnay.setText(thang.substring(0,2));

        SimpleDateFormat dftNamnay = new SimpleDateFormat("yyyy", Locale.getDefault());
        nam = dftNamnay.format(cal.getTime());
        //hiển thị lên giao diện
        tvNamnay.setText("Năm nay (" +nam+ ")");
        imgNamnay.setText(nam);

        for (GhiChep ghichep: ghiCheps) {
            if (ghichep.getLoaiGhiChep().equals("chitien")){
                if (ghichep.getNgay().equals(ngay)){
                    thanhVien.setChiHomnay(thanhVien.getChiHomnay() + ghichep.getSoTien());
                }
                if (ghichep.getNgay().substring(3).equals(ngay)){
                    thanhVien.setChiThangnay(thanhVien.getChiThangnay() + ghichep.getSoTien());
                }
                if (ghichep.getNgay().substring(6).equals(nam)){
                    thanhVien.setChiNamnay(thanhVien.getChiNamnay() + ghichep.getSoTien());
                }
            }
            if (ghichep.getLoaiGhiChep().equals("thutien")){
                if (ghichep.getNgay().equals(ngay)){
                    thanhVien.setThuHomnay(thanhVien.getThuHomnay() + ghichep.getSoTien());
                }
                if (ghichep.getNgay().substring(3).equals(ngay)){
                    thanhVien.setThuThangnay(thanhVien.getThuThangnay() + ghichep.getSoTien());
                }
                if (ghichep.getNgay().substring(6).equals(nam)){
                    thanhVien.setThuNamnay(thanhVien.getThuNamnay() + ghichep.getSoTien());
                }
            }
        }

        tvChiHomnay.setText(thanhVien.getChiHomnay() + " đ");
        tvThuHomnay.setText(thanhVien.getThuHomnay() + " đ");
        tvChiThangnay.setText(thanhVien.getChiThangnay() + " đ");
        tvThuThangnay.setText(thanhVien.getThuThangnay() + " đ");
        tvChiNamnay.setText(thanhVien.getChiNamnay() + " đ");
        tvThuNamnay.setText(thanhVien.getThuNamnay() + " đ");

    }

    private void getGhichep(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetGhichep, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ghiCheps.clear();

                        for (int i=0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                GhiChep ghiChep = new GhiChep();

                                if (object.getString("UserName") == thanhVien.getUsername()) {
                                    ghiChep.setSoTien(object.getInt("SoTien"));
                                    ghiChep.setUsername(object.getString("UserName"));
                                    ghiChep.setNgay(object.getString("Ngay"));
                                    ghiChep.setLoaiGhiChep(object.getString("LoaiGhiChep"));

                                    ghiCheps.add(ghiChep);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

}
