package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DataBaseManager {
    public static final String DB_NAME="QuanLyChiTieuCaNhan.sqlite";
    public static SQLiteDatabase initDataBaseQlyThuChi(Activity activity){
        String fileURL = activity.getApplicationInfo().dataDir+"/databases/"+DB_NAME;
        String folderURL = activity.getApplicationInfo().dataDir+"/databases";
        File file = new File(fileURL);

        try {
            if (!file.exists()) {
                //tạo mới folder
                File folder = new File(folderURL);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                file.createNewFile();

                //copy file vào foler
                InputStream fileAsset = activity.getAssets().open(DB_NAME);
                FileOutputStream fileOutput = new FileOutputStream(file);

                int length;
                byte[]buff = new byte[1024];
                while((length = fileAsset.read(buff)) > 0){
                    fileOutput.write(buff, 0, length);
                }
                fileOutput.flush();
                fileOutput.close();
                fileAsset.close();
            }
        }catch (Exception e){}

        return activity.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }
}
