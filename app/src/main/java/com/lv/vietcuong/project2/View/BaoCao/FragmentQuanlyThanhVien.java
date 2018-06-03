package com.lv.vietcuong.project2.View.BaoCao;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.R;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThanhVien;
import com.lv.vietcuong.project2.View.BaoCao.adapter.ThanhvienAdapter;
import com.lv.vietcuong.project2.View.GhiChep.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.lv.vietcuong.project2.View.DangNhap.DangNhapActivity.HOST;

public class FragmentQuanlyThanhVien extends Fragment {


    String urlGetThanhVien = HOST + "thanhvien/getThanhvien.php";
    String urlXoaThanhVien = HOST + "thanhvien/deleteThanhvien.php";
    String urlThemThanhVien = HOST + "thanhvien/addThanhvien.php";

    Button btnThemThanhvien, btnThem, btnHuy;
    EditText edtUsername;
    ListView lvThanhvien;
    Dialog dialog;

    ThanhvienAdapter adapter;
    ArrayList<ThanhVien> arrayThanhVien;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlythanhvien,container,false);
        anhXa(view);
        setEnvent();

        arrayThanhVien = new ArrayList<>();
        adapter = new ThanhvienAdapter(getContext(), R.layout.item_thanhvien, arrayThanhVien, FragmentQuanlyThanhVien.this);
        lvThanhvien.setAdapter(adapter);

        getThanhVien(Layout_TrangChu.taiKhoanDangNhap.getIdGiaDinh());

        return view;
    }

    private void getThanhVien(final int idgd){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetThanhVien, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayThanhVien.clear();

                        for (int i=0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                ThanhVien thanhVien = new ThanhVien();
                                thanhVien.setUsername(object.getString("TenTaiKhoan"));
                                thanhVien.setHoTen(object.getString("HoTen"));
                                if ((object.getInt("IdGiaDinh") == idgd) && (object.getString("LoaiTaiKhoan").equals("thanhvien"))) {
                                    arrayThanhVien.add(thanhVien);
                                }
                                Log.d("Tag", thanhVien.getHoTen());
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
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

    public void xoaThanhVien(final String tentaikhoan){
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlXoaThanhVien, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(getActivity(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                    getThanhVien(Layout_TrangChu.taiKhoanDangNhap.getIdGiaDinh());
                }else {
                    Toast.makeText(getActivity(), "Lỗi xoá", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tenTaiKhoan", tentaikhoan);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void anhXa(View v){
        btnThemThanhvien = (Button) v.findViewById(R.id.btn_themthanhvien);
        lvThanhvien = (ListView) v.findViewById(R.id.lv_thanhvien);
    }

    private void setEnvent(){
        btnThemThanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        lvThanhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ThanhVien thanhVien = arrayThanhVien.get(i);

                FragmentTaichinhThanhVien fragmentTaichinhThanhVien = new FragmentTaichinhThanhVien();
                Bundle bundle = new Bundle();
                bundle.putSerializable("thanhvien", thanhVien);
                fragmentTaichinhThanhVien.setArguments(bundle);
                Util.replace(R.id.fragment_content_baocao, fragmentTaichinhThanhVien, getActivity());
            }
        });
    }

    private void showDialog(){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_themthanhvien);
        setEnventDialog(dialog);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;

        dialog.getWindow().setAttributes(lp);

        dialog.show();
    }

    private void setEnventDialog(final Dialog dialog) {
        btnThem = dialog.findViewById(R.id.btn_them);
        btnHuy = dialog.findViewById(R.id.btn_huy);
        edtUsername = dialog.findViewById(R.id.edt_username);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themThanhvien(edtUsername.getText().toString().trim());
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void themThanhvien(final String tentaikhoan){
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlThemThanhVien, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Tag10", response);

                if (response.trim().equals("1")){
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    getThanhVien(Layout_TrangChu.taiKhoanDangNhap.getIdGiaDinh());
                }else if (response.trim().equals("0")){
                    Toast.makeText(getActivity(), "Tên tài khoản chưa tồn tại hoặc đã trong có trong gia đình!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tenTaiKhoan", tentaikhoan);
                params.put("idGiaDinh", String.valueOf(Layout_TrangChu.taiKhoanDangNhap.getIdGiaDinh()));

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
