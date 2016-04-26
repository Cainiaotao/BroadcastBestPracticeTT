package com.example.tantao.broadcastbestpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tantao.broadcastbestpractice.base.BaseActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HttpActivity extends BaseActivity {

    @InjectView(R.id.sendhttp)
    Button sendhttp;
    @InjectView(R.id.response)
    TextView responseText;
    @InjectView(R.id.urltext)
    TextView urltext;

    private static final int SHOWEHTTPMSG=0;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case SHOWEHTTPMSG:
                   String response=(String)msg.obj;
                   responseText.setText(response);
                   Log.d("handleMessage",response);
           }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        ButterKnife.inject(this);
    }



    private void sendRequsetWithHttpResponseConnecion()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlstr=urltext.getText().toString();
                HttpURLConnection connection=null;
                try {
                    URL url = new URL(urlstr);
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    Message message=new Message();
                    message.what=SHOWEHTTPMSG;
                    message.obj=response.toString();
                    handler.sendMessage(message);

                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("Exception","error:"+e);
                }finally {
                    if (connection!=null){
                        connection.disconnect();
                        Log.d("Connection", "disconnect");
                    }

                }
            }
        }).start();
    }

    private void sendRequseWithHttpClient(){

    }




    @OnClick(R.id.sendhttp)
    public void onClick() {
        sendRequsetWithHttpResponseConnecion();
    }
}
