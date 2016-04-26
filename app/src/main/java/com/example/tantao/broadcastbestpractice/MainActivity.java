package com.example.tantao.broadcastbestpractice;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.tantao.broadcastbestpractice.base.BaseActivity;
import com.example.tantao.broadcastbestpractice.service.LongRunningService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.SQLBtn)
    Button SqlBtn;
    @InjectView(R.id.notifBtn)
    Button NotifiBtn;
    @InjectView(R.id.ServiceBtn)
    Button serviceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }


    @OnClick({R.id.button,R.id.SQLBtn,R.id.notifBtn,R.id.ServiceBtn,R.id.AlarmBtn,R.id.webviewBtn,R.id.HttpBtn,R.id.MoreBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent1 = new Intent("com.example.tantao.broadcastbestpractice.LOACL_BROADCAST");
                sendBroadcast(intent1);
                break;
            case R.id.SQLBtn:
                Intent intent2=new Intent(this,SqlActivity.class);
                startActivity(intent2);
                break;
            case R.id.notifBtn:
                Intent intent3=new Intent(this,NotifiActivity.class);
                startActivity(intent3);
                break;
            case R.id.ServiceBtn:
                Intent intent4=new Intent(this,SerivceActivity.class);
                startActivity(intent4);
                break;
            case R.id.AlarmBtn:
                //startActivity(new Intent(this,AlarmActivity.class));
                startService(new Intent(this,LongRunningService.class));
                break;
            case R.id.webviewBtn:
                startActivity(new Intent(this,WebViewActivity.class));
                break;
            case R.id.HttpBtn:
                startActivity(new Intent(this,HttpActivity.class));
                break;
            case R.id.MoreBtn:
                startActivity(new Intent(this,MoreActivity.class));
                break;
        }
    }


}
