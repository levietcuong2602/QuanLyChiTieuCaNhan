package com.lv.vietcuong.project2.ConnectInternet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.midi.MidiOutputPort;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.lv.vietcuong.project2.Adapter.ListAdapterChonViTien;
import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
import com.lv.vietcuong.project2.Databases.SQLHinhAnh;
import com.lv.vietcuong.project2.Databases.SQLViTien;
import com.lv.vietcuong.project2.Layout_TrangChu;
import com.lv.vietcuong.project2.Model.GhiChep.ModelGhiChep;
import com.lv.vietcuong.project2.Model.HanMucChi.ModelHanMucChi;
import com.lv.vietcuong.project2.Model.ObjectClass.GhiChep;
import com.lv.vietcuong.project2.Model.ObjectClass.HanMucChi;
import com.lv.vietcuong.project2.Model.ObjectClass.ViTien;
import com.lv.vietcuong.project2.Model.ViTien.ModelViTien;

import java.util.ArrayList;
import java.util.Iterator;

public class NetWorkStateChecker extends BroadcastReceiver {
    private Context context;
    private ModelViTien modelViTien;
    private ModelGhiChep modelGhiChep;
    private ModelHanMucChi modelHanMucChi;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        modelGhiChep = new ModelGhiChep();
        modelHanMucChi = new ModelHanMucChi();
        modelViTien = new ModelViTien();

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo  = manager.getActiveNetworkInfo();

        //neu co mang
        if (networkInfo != null){
            //neu la mang wifi or mobile
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                dongBoViTien();
//                dongBoGhiChep();
                dongBoHanMucChi();
            }
        }
    }

    private void dongBoHanMucChi() {
        //lay ra ds han muc chi chưa đồng bọ trong csdl
        ArrayList<HanMucChi> dsHanMucChi = SQLHanMucChi.getHanMucChiNotSync(context, Layout_TrangChu.NOT_SYNCED_WITH_SERVER);
        Log.d("hanmucchi them", dsHanMucChi.size()+"");
        Iterator<HanMucChi> iterator = dsHanMucChi.iterator();
        while (iterator.hasNext()){
            HanMucChi hanMucChi = iterator.next();
            boolean check = modelHanMucChi.saveHanMucChiToServer(hanMucChi);
            if (check){
                hanMucChi.setTrangthai(Layout_TrangChu.SYNCED_WITH_SERVER);
                long result = SQLHanMucChi.updateHanMucChi(context, hanMucChi);
                if (result > 0){
                    Log.d("SQLHanMucChi", "successfull");
                }
            }
        }

        //dong bo trang thai xoa
        ArrayList<HanMucChi> dsXoa = SQLHanMucChi.getHanMucChiNotSync(context, Layout_TrangChu.DELETE_STATE);
        Log.d("hanmucchi xoa", dsXoa.size()+"");
        Iterator<HanMucChi> itrXoa = dsXoa.iterator();
        while(itrXoa.hasNext()){
            HanMucChi hanMucChi = itrXoa.next();
            boolean check = modelHanMucChi.deleteHanMucChiToServer(hanMucChi);
            if (check){
                long result = SQLHanMucChi.deleteHanMucChi(context, hanMucChi.getIdHanMucChi());
                if (result > 0){
                    Log.d("SQLHanMucChi delete", "successfull");
                }
            }
        }
        //dong bo trang thai sua

    }

    private void dongBoGhiChep() {
        //dong bo trang thai them
        ArrayList<GhiChep> dsGhiChep = SQLGhiChep.getGhiChepNotSyn(context);
        Iterator<GhiChep> iterator = dsGhiChep.iterator();
        Log.d("ghichep", dsGhiChep.size()+"");
        while(iterator.hasNext()){
            GhiChep ghiChep = iterator.next();
            boolean check = modelGhiChep.saveGhiChepToServer(ghiChep);
            if (check){
                ghiChep.setTrangthai(Layout_TrangChu.SYNCED_WITH_SERVER);
                long result = SQLGhiChep.updateGhiChep(context, ghiChep);
                if (result > 0){
                    Log.d("SQLGhiChep", "successfull");
                }
            }
        }

        //dong bo trang thai xoa


        //dong bo trang thai sua
    }

    private void dongBoViTien() {
        //dong bo trang thai them
        ArrayList<ViTien> dsViTien = SQLViTien.getViTienNotSynced(context, Layout_TrangChu.NOT_SYNCED_WITH_SERVER);
        Log.d("vitien them", dsViTien.size()+"");
        Iterator<ViTien> itViTien = dsViTien.iterator();
        while(itViTien.hasNext()){
            ViTien viTien = itViTien.next();
            boolean check = modelViTien.saveViTienToServer(viTien);
            if (check){
                viTien.setTrangthai(Layout_TrangChu.SYNCED_WITH_SERVER);
                long result = SQLViTien.updateViTien(context, viTien);
                if (result > 0){
                    Log.d("SQLViTien", "successfull");
                }
            }
        }
        //dong bo trang thai xoa
            ArrayList<ViTien> dsXoa = SQLViTien.getViTienNotSynced(context, Layout_TrangChu.DELETE_STATE);
            Log.d("vitien xoa", dsXoa.size()+"");
            Iterator<ViTien> itrXoa = dsXoa.iterator();
            while (itrXoa.hasNext()){
                ViTien vitien = itrXoa.next();
                boolean check = modelViTien.deleleViTienToServer(vitien);
                if(check){
                    long result = SQLViTien.deleteViTien(context, vitien.getIdViTien());
                    if (result > 0){
                        Log.d("SQLViTienDelete", "successfull");
                    }
                }
            }
        //dong bo trang thai sua
    }


}
