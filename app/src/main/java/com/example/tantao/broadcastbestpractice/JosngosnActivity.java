package com.example.tantao.broadcastbestpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.tantao.broadcastbestpractice.base.BaseActivity;
import com.example.tantao.broadcastbestpractice.gosn.App;
import com.example.tantao.broadcastbestpractice.gosn.HttpCallbackListener;
import com.example.tantao.broadcastbestpractice.gosn.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class JosngosnActivity extends BaseActivity {

    @InjectView(R.id.geturl_Textview)
    TextView geturlTextview;
    @InjectView(R.id.GSON)
    Button GSON;
    @InjectView(R.id.getjsondata)
    TextView getjsondata;

    private static final int SHOWERROR=0;
    private static final int SHOWTEXTVIEW=1;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOWERROR:
                    geturlTextview.setText("Requset is error");
                    String err=(String)msg.obj;
                    getjsondata.setText(err);
                case SHOWTEXTVIEW:
                    geturlTextview.setText("http://localhost:8080/getjson.json");
                    String requset=(String)msg.obj;
                    getjsondata.setText(requset);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_josngosn);
        ButterKnife.inject(this);
    }

    private void ReceiveResponseData(){

        String address="http://10.10.1.131:8080/weatherdata.json";
        //String address="http://10.10.1.131:8080/getjson.json";
        HttpUtil httpUtil=new HttpUtil();
        httpUtil.sendResponsWithHttpURlConnection(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String request) {
                if (!request.isEmpty()) {
                    // String response=preasGSONWithJSONdata(request);
                    String response=testJSON(request);
                    Message message = new Message();
                    message.what = SHOWTEXTVIEW;
                    message.obj=response;
                    //message.obj = request;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onError(String error) {
                Message message = new Message();
                message.what = SHOWTEXTVIEW;
                message.obj = error;
                handler.sendMessage(message);
            }
        });
    }

    private String preasGSONWithJSONdata(String jsondata){
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(jsondata,new TypeToken<List<App>>(){}.getType());
        StringBuilder response=new StringBuilder();
        for (App app:appList){
            String id=app.getId();
            String name=app.getName();
            String version=app.getVersion();
            String result="Result:"+id+"\t"+name+"\t"+version+"\n";
            response.append(result);
        }
        return response.toString();
    }

    private String testJSON(String jsondata){
        try{
            JSONArray jsonArray=new JSONArray(jsondata);
            StringBuilder response=new StringBuilder();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String province=jsonObject.getString("省");
                JSONArray cities=jsonObject.getJSONArray("市");
                for (int j=0;j<cities.length();j++){
                    JSONObject jsonObjectCity=cities.getJSONObject(j);
                    String code=jsonObjectCity.getString("编码");
                    String city=jsonObjectCity.getString("市名");
                    String result=province+"."+city+"\t"+code+"\n";
                    response.append(result);
                }
            }
            return response.toString();

        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }

    }

    @OnClick(R.id.GSON)
    public void onClick() {
        ReceiveResponseData();
    }
}
