package com.anxin.qin.anxinju.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.anxin.qin.anxinju.R;
import com.anxin.qin.anxinju.model.Environment;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responsText;
    String requesturl = "http://127.0.0.1:3200/find/ArrayList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_data);
        responsText = (TextView) findViewById(R.id.testdata);
        sendRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send_data) {
            sendRequest();
        }
    }

    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //开启一个线程来GET POST
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url(requesturl)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                    parseJsonWithFastJson(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJsonWithFastJson(String jsondata) {
        List<Environment> userList = JSON.parseArray(jsondata, Environment.class);
        for (Environment en : userList) {
            Log.d("MainActivity", "wendu is " + en.getWendu());
            Log.d("MainActivity", "shidu is " + en.getShidu());
            Log.d("MainActivity", "date is " + en.getDate());
        }
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //将结果显示到界面上
                responsText.setText(response);
            }
        });
    }
}
