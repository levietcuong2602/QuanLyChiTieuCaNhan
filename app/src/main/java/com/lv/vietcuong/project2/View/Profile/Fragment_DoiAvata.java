package com.lv.vietcuong.project2.View.Profile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.lv.vietcuong.project2.Adapter.AdapterHinhAnh;
import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
import com.lv.vietcuong.project2.Model.ObjectClass.HinhAnh;
import com.lv.vietcuong.project2.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administor on 3/25/2018.
 */

public class Fragment_DoiAvata extends Fragment implements View.OnClickListener {

    ImageView imgCamera;
    ImageView imgPerson;
    TableRow row_chonanh, row_chupanh, row_xemanh, row_huy;
    Dialog dialog;
    private int REQUEST_CODE = 123;
    private int REQUEST_FOLDER = 456;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_doiavata, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        imgCamera = view.findViewById(R.id.imgCamera);
        imgPerson = view.findViewById(R.id.imgPerson);

        imgCamera.setOnClickListener(this);

        SharedPreferences preferences = getActivity().getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        int idhinhanh = preferences.getInt("idhinhanh", -1);

        if (idhinhanh != -1){
            Bitmap bitmap = SQLHinhAnh.getImage(getActivity(), idhinhanh);
            imgPerson.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imgCamera:
                showBottomSheetDialog();
                break;
            case R.id.row_chonanh:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent, REQUEST_FOLDER);
                break;
            case R.id.row_chupanh:
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent1, REQUEST_CODE);
                break;
            case R.id.row_xemanh:
                showDialogAnhDaiDien();
                break;
            case R.id.row_huy:
                dialog.hide();
                break;
        }
    }

    private void showDialogAnhDaiDien() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_dialog_anhdaidien);
        dialog.setTitle(null);

        RecyclerView recyclerView = dialog.findViewById(R.id.recycleView);
        ImageView imageView = dialog.findViewById(R.id.imageAvata);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        ArrayList<HinhAnh> listHinhAnh = SQLHinhAnh.getAllHinhAnhType(getActivity(), "avata");
        AdapterHinhAnh adapterHinhAnh = new AdapterHinhAnh(getContext(), R.layout.item_recycleview_anhdaidien, listHinhAnh);
        adapterHinhAnh.notifyDataSetChanged();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterHinhAnh);

        SharedPreferences preferences = getActivity().getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        int idhinhAnh = preferences.getInt("idhinhanh", -1);
        Bitmap bitmap = SQLHinhAnh.getImage(getActivity(), idhinhAnh);
        imageView.setImageBitmap(bitmap);
        dialog.show();
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(R.layout.bottomsheet_thaydoi_avata);
        row_chonanh = dialog.findViewById(R.id.row_chonanh);
        row_chupanh = dialog.findViewById(R.id.row_chupanh);
        row_xemanh = dialog.findViewById(R.id.row_xemanh);
        row_huy = dialog.findViewById(R.id.row_huy);

        row_xemanh.setOnClickListener(this);
        row_chupanh.setOnClickListener(this);
        row_chonanh.setOnClickListener(this);
        row_huy.setOnClickListener(this);

        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[]byteArray = stream.toByteArray();
            int idHinhAnh = SQLHinhAnh.addHinhAnh(getActivity(), byteArray, "avata");

            if (idHinhAnh >= 0) {
                setupAvata(bitmap, idHinhAnh);
            }
        }

        if (requestCode == REQUEST_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[]byteArray = stream.toByteArray();
                int idHinhAnh = SQLHinhAnh.addHinhAnh(getActivity(), byteArray, "avata");
                if (idHinhAnh >= 0) {
                    setupAvata(bitmap, idHinhAnh);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setupAvata(Bitmap bitmap, int idHinhAnh){
        imgPerson.setImageBitmap(bitmap);
        SharedPreferences preferences = getActivity().getSharedPreferences("TaiKhoan", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("idhinhanh", idHinhAnh);
        editor.commit();

        NavigationView navigation = getActivity().findViewById(R.id.navigationView);
        View view = navigation.getHeaderView(0);
        navigation.removeHeaderView(view);
        View header = navigation.inflateHeaderView(R.layout.layout_header_profile);
        CircleImageView circleImageView = header.findViewById(R.id.imgAvata);
        circleImageView.setImageBitmap(bitmap);

        TextView textView = header.findViewById(R.id.tvName);
        String hoten = preferences.getString("hoten", "");
        textView.setText(hoten);
    }

}
