package com.lv.vietcuong.project2.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.lv.vietcuong.project2.Model.Account;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administor on 3/24/2018.
 */

public class DB_Manager {

    public static SQLiteDatabase initDatabases(Activity activity, String dbName){
            String outFileName = activity.getApplicationInfo().dataDir+"/databases/" + dbName;
            try {
                File file = new File(outFileName);

                //nếu file không tồn tại tiến hành copy file
                if(!file.exists()){

                    InputStream inputStream = activity.getAssets().open(dbName);
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

        return activity.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    public static ArrayList<Account> getAllAcount(Activity activity){
        ArrayList<Account> dsTaiKhoan = new ArrayList<>();

        String SQLQuery = "SELECT * FROM TaiKhoan;";
        SQLiteDatabase database = DB_Manager.initDatabases(activity, "Accounts.sqlite");
        if (database != null){
            Log.d("DB", "Copy database thành công");
        }else {
            Log.d("DB", "Copy database thất bại");
        }
        Cursor cursor = database.rawQuery(SQLQuery, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String password = cursor.getString(2);
                int avata = cursor.getInt(3);

                dsTaiKhoan.add(new Account(id, username, password, avata));
            }while(cursor.moveToNext());
        }

        database.close();
        Log.d("DB", "lấy dữ liệu thành công");
        return dsTaiKhoan;
    }

    public static long addAccount(Activity activity, Account account){
        SQLiteDatabase database = DB_Manager.initDatabases(activity, "Accounts.sqlite");
        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        values.put("avata", account.getAvata());

        return database.insert("TaiKhoan", null, values);
    }

    public static long updatePasswordAccount(Activity activity, Account account){
        SQLiteDatabase database = DB_Manager.initDatabases(activity, "Accounts.sqlite");
        ContentValues values = new ContentValues();
        values.put("username", account.getUsername());
        values.put("password", account.getPassword());
        return database.update("TaiKhoan", values, "username=?", new String[]{account.getUsername()});
    }
}
