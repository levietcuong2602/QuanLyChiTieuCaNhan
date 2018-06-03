package com.lv.vietcuong.project2.View.HangMucThuChi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.vietcuong.project2.Databases.SQLHangMuc;
import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
import com.lv.vietcuong.project2.Model.ObjectClass.HangMuc;
import com.lv.vietcuong.project2.Model.ObjectClass.HinhAnh;
import com.lv.vietcuong.project2.R;

public class Fragment_ThemHangMuc extends Fragment implements View.OnClickListener {
    Button btnHuy, btnLuu, btnDienGiai, btnHangMucCha;
    Button btnButtonSave;
    ImageView imgHangMuc;
    FloatingActionButton fabOption;
    EditText edtTenHangMucChi;
    ImageView image;
    TextView textViewTitle;

    static String dienGiai = "";
    static int idHangMucCha = -1;
    static int idIcon = -1;

    public String loaiHangMuc = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_themhangmucchi, container, false);
        initView(view);
        setImageHinhAnh();
        setButtonHangMucCha();
        setButtonDienGiai();
        setTitleThemHangMuc();
        return view;
    }

    private void setTitleThemHangMuc() {

<<<<<<< HEAD
        if (loaiHangMuc.equals("chitien")){
            textViewTitle.setText("Thêm Hạng Mục Chi");
        }else if (loaiHangMuc.equals("thutien")){
=======
        if (loaiHangMuc.equals("chi")){
            textViewTitle.setText("Thêm Hạng Mục Chi");
        }else if (loaiHangMuc.equals("thu")){
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
            textViewTitle.setText("Thêm Hạng Mục Thu");
        }

    }

    private void setButtonDienGiai() {
        btnDienGiai.setText(dienGiai);
    }

    private void setButtonHangMucCha() {
        if (idHangMucCha != -1){
            HangMuc hangMuc = SQLHangMuc.getHangMuc(getActivity(), loaiHangMuc, idHangMucCha);
            Bitmap bitmap = SQLHinhAnh.getImage(getActivity(), hangMuc.getIdHinhAnh());
            image.setImageBitmap(bitmap);
            btnHangMucCha.setText(hangMuc.getTenHangMuc());
        }
    }

    private void setImageHinhAnh() {
        if (idIcon != -1) {
            HinhAnh hinhAnh = SQLHinhAnh.getHinhAnh(getActivity(), idIcon);
            byte[] image = hinhAnh.getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imgHangMuc.setImageBitmap(bitmap);
        }else{
            HinhAnh hinhAnh = SQLHinhAnh.getHinhAnh(getActivity(), 57);
            byte[] image = hinhAnh.getHinhAnh();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imgHangMuc.setImageBitmap(bitmap);
        }

    }

    public void initView(View view){
        btnHuy = view.findViewById(R.id.btnHuy);
        btnLuu = view.findViewById(R.id.btnLuu);
        edtTenHangMucChi = view.findViewById(R.id.tenHangMucChi);
        btnDienGiai = view.findViewById(R.id.btnDienGiai);
        btnButtonSave = view.findViewById(R.id.idButtonSave);
        btnHangMucCha = view.findViewById(R.id.btnHangMucCha);
        fabOption = view.findViewById(R.id.fabOption);
        imgHangMuc = view.findViewById(R.id.imgHangMuc);
        image = view.findViewById(R.id.imageIConHM);
        textViewTitle = view.findViewById(R.id.tvTitle);

        btnLuu.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        btnDienGiai.setOnClickListener(this);
        btnButtonSave.setOnClickListener(this);
        btnHangMucCha.setOnClickListener(this);
        fabOption.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.btnHuy:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btnLuu:
            case R.id.idButtonSave:
                long result = addHangMuc();
                if(result > 0){
                    Toast.makeText(getContext(), "Thêm hạng mục thành công", Toast.LENGTH_SHORT).show();
                    edtTenHangMucChi.setText("");
                    dienGiai = "";
                    idHangMucCha = -1;
                    idIcon = 57;

                    HinhAnh hinhAnh = SQLHinhAnh.getHinhAnh(getActivity(), 57);
                    byte[] image = hinhAnh.getHinhAnh();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    imgHangMuc.setImageBitmap(bitmap);

                }else {
                    Toast.makeText(getContext(), "Thêm hạng mục không thành công", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDienGiai:
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment_DienGiai dienGiai = new Fragment_DienGiai();
                transaction.replace(R.id.content_layout, dienGiai);
                transaction.addToBackStack(null);

                transaction.commit();
                break;
            case R.id.btnHangMucCha:
                Fragment_DSHangMucCha hangMucCha = new Fragment_DSHangMucCha();
                hangMucCha.mode_hangmuccha = "themhangmuc";
                hangMucCha.loaiHangMuc = loaiHangMuc;

                FragmentTransaction tranHangMucCha = getActivity().getSupportFragmentManager().beginTransaction();
                tranHangMucCha.replace(R.id.content_layout, hangMucCha);
                tranHangMucCha.addToBackStack(null);

                tranHangMucCha.commit();
                break;
            case R.id.fabOption:
                Fragment_ListIcon listIcon = new Fragment_ListIcon();
<<<<<<< HEAD
                if (loaiHangMuc.equals("chitien")){
                    listIcon.mode_dsIcon = "hangmucchi";
                }else if (loaiHangMuc.equals("thutien")){
=======
                if (loaiHangMuc.equals("chi")){
                    listIcon.mode_dsIcon = "hangmucchi";
                }else if (loaiHangMuc.equals("thu")){
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
                    listIcon.mode_dsIcon = "hangmucthu";
                }

                FragmentTransaction transIcon = getActivity().getSupportFragmentManager().beginTransaction();
                transIcon.replace(R.id.content_layout, listIcon);
                transIcon.addToBackStack(null);

                transIcon.commit();
                break;
        }
    }

    private long addHangMuc() {
        HangMuc hangMuc = new HangMuc();

        hangMuc.setTenHangMuc(edtTenHangMucChi.getText().toString());

        int loaiHangMuc = HangMucThuChiActivity.mode;
        if (loaiHangMuc == 0) {
<<<<<<< HEAD
            hangMuc.setLoaiHangMuc("chitien");
        }else if (loaiHangMuc == 1) {
            hangMuc.setLoaiHangMuc("thutien");
=======
            hangMuc.setLoaiHangMuc("chi");
        }else if (loaiHangMuc == 1) {
            hangMuc.setLoaiHangMuc("thu");
>>>>>>> 66d505f1d0f366c61803ec14acb312c4634b31e6
            Toast.makeText(getContext(), "thu tiền", Toast.LENGTH_SHORT).show();
        }
        hangMuc.setDienDai(dienGiai);
        hangMuc.setIdHangMucCha(idHangMucCha);
        hangMuc.setIdHinhAnh(idIcon);

        if (hangMuc.getTenHangMuc().length() > 0) {
            return SQLHangMuc.addHangMuc(getActivity(), hangMuc);
        }
        return -1;
    }
}
