package com.lv.vietcuong.project2.ConnectInternet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.midi.MidiOutputPort;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.lv.vietcuong.project2.Databases.SQLGhiChep;
import com.lv.vietcuong.project2.Databases.SQLHanMucChi;
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
                dongBoGhiChep();
                dongBoHanMucChi();
            }
        }
    }

    private void dongBoHanMucChi() {
        //lay ra ds han muc chi chưa đồng bọ trong csdl
        ArrayList<HanMucChi> dsHanMucChi = SQLHanMucChi.getHanMucChiNotSync(context);
        Log.d("hanmucchi", dsHanMucChi.size()+"");
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
    }

    private void dongBoGhiChep() {
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
    }

    private void dongBoViTien() {
        ArrayList<ViTien> dsViTien = SQLViTien.getViTienNotSynced(context);
        Log.d("vitien", dsViTien.size()+"");
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
    }

}
