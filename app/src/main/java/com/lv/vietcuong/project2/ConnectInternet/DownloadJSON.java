package com.lv.vietcuong.project2.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadJSON extends AsyncTask<String, Void, String>{
    private String duongDan;
    private List<HashMap<String, String>> attrs;
    private boolean method = true;

    public DownloadJSON(String duongDan, List<HashMap<String, String>> attrs) {
        this.duongDan = duongDan;
        this.attrs = attrs;

        method = false;
    }

    public DownloadJSON(String duongDan) {
        this.duongDan = duongDan;
        method = true;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        try {
            URL url = new URL(duongDan);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(method){
                data = methodGet(connection);
            }else{
                data = methodPost(connection);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("dulieu",data);
        return data;
    }

    private String methodPost(HttpURLConnection connection) {
        String key = "";
        String value = "";
        String data = "";
        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            Uri.Builder uri = new Uri.Builder();

            int count = attrs.size();
            for (int i = 0; i < count; i++){
                for(Map.Entry<String,String> values : attrs.get(i).entrySet()){
                    key = values.getKey();
                    value = values.getValue();
                }
                uri.appendQueryParameter(key, value);
            }
            String postData = uri.build().getEncodedQuery();

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();
            writer.close();
            outputStream.close();

            data = methodGet(connection);

            Log.d("dulieu", data);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String methodGet(HttpURLConnection connection) {
        //lay du lieu tren server ve client
        String data = "";
        StringBuilder builder = null;
        try {
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            builder = new StringBuilder();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }

            data = builder.toString();

            bufferedReader.close();
            reader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
