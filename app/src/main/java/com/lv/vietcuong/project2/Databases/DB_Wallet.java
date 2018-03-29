package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lv.vietcuong.project2.Model.Account;
import com.lv.vietcuong.project2.Model.Wallet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administor on 3/26/2018.
 */

public class DB_Wallet {
    private static final String TABLE_NAME = "ViTien";
    private static final String DATABASE_NAME = "Wallets.sqlite";

    public static SQLiteDatabase initDatabases(Activity activity){
        String outFileName = activity.getApplicationInfo().dataDir+"/databases/" + DATABASE_NAME;
        try {
            File file = new File(outFileName);

            //nếu file không tồn tại tiến hành copy file
            if(!file.exists()){

                InputStream inputStream = activity.getAssets().open(DATABASE_NAME);
                File folder = new File(activity.getApplicationInfo().dataDir+"/databases");
                if (!folder.exists()){
                    folder.mkdir();
                }

                file.createNewFile();
                FileOutputStream myOutput = new FileOutputStream(file);

                int len;
                byte buff[] = new byte[1024];
                while((len = inputStream.read(buff)) > 0){
                    myOutput.write(buff, 0, len);
                }

                myOutput.flush();
                myOutput.close();
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return activity.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public static ArrayList<Wallet> getAllWallet(Activity activity){
        ArrayList<Wallet> dsWallet = new ArrayList<>();

        String SQLQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase database = initDatabases(activity);
        if (database != null){
            Log.d("DB", "Copy database thành công");
        }else {
            Log.d("DB", "Copy database thất bại");
        }
        Cursor cursor = database.rawQuery(SQLQuery, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nameWallet = cursor.getString(1);
                double balance = cursor.getDouble(2);

                dsWallet.add(new Wallet(nameWallet, balance));
            }while(cursor.moveToNext());
        }

        database.close();
        Log.d("DB", "lấy dữ liệu thành công");
        return dsWallet;
    }

    public static long addWallet(Activity activity, Wallet wallet){
        SQLiteDatabase database = initDatabases(activity);
        ContentValues values = new ContentValues();
        values.put("name", wallet.getNameWallet());
        values.put("balance", wallet.getBalance());

        return database.insert(TABLE_NAME, null, values);
    }


}
