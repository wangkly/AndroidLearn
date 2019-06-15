package com.wangky.scrollleran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetWorkActivity extends AppCompatActivity implements View.OnClickListener{



    private  Button network;

    private  Button okHttp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.netlayout);

        network = findViewById(R.id.network);
        okHttp = findViewById(R.id.okHttp);

        network.setOnClickListener(this);
        okHttp.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.network :

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader reader=null;

                        try {
                            URL url = new URL("http://172.19.8.38:3000/user/playlist?uid=32953014");

                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                            connection.setRequestMethod("GET");

                            connection.setConnectTimeout(10000);


                            InputStream in =connection.getInputStream();

                            reader = new BufferedReader(new InputStreamReader(in));

                            StringBuilder response = new StringBuilder();

                            String line;
                            while ((line =reader.readLine())!=null){

                                response.append(line);

                            }

                            JSONObject json = new JSONObject(response.toString());

                            JSONArray playlist = (JSONArray) json.get("playlist");


                            System.out.println(response.toString());

                            connection.disconnect();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {

                        }



                    }
                }).start();


                break;


            case R.id.okHttp:

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        OkHttpClient client = new OkHttpClient();

                        Request request = new Request.Builder().url("http://172.19.8.38:3000/user/playlist?uid=32953014").build();

                        try {
                            Response response =  client.newCall(request).execute();

//                    RequestBody requestBody = new FormBody.Builder().add("name","wangkly").build();


                            String str = response.body().string();

                            System.out.println(str);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

                break;

        }

    }
}
