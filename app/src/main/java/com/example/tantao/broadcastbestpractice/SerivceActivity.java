package com.example.tantao.broadcastbestpractice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tantao.broadcastbestpractice.base.BaseActivity;
import com.example.tantao.broadcastbestpractice.service.DownloadBinder;
import com.example.tantao.broadcastbestpractice.service.MyIntentService;
import com.example.tantao.broadcastbestpractice.service.MyService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SerivceActivity extends BaseActivity {


    private static final int UPDATE_TEXT = 1;
    @InjectView(R.id.changetext)
    TextView changetext;
    @InjectView(R.id.changeBtn)
    Button changeBtn;
    @InjectView(R.id.startBtn)
    Button startBtn;
    @InjectView(R.id.stopBtn)
    Button stopBtn;
    @InjectView(R.id.bindBtn)
    Button bindBtn;
    @InjectView(R.id.unbindBtn)
    Button unbindBtn;
    @InjectView(R.id.intentSBtn)
    Button intentSBtn;



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_TEXT:
                    changetext.setText("Change text seccussful");
                    break;
                default:
                    break;
            }
        }
    };

    //建立管道通信
    private DownloadBinder downloadBinder;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
           // Log.d("ServiceConnection","onServiceDisconnected");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serivce);
        ButterKnife.inject(this);


    }

    @OnClick({R.id.changeBtn, R.id.startBtn, R.id.stopBtn,R.id.bindBtn,R.id.unbindBtn,R.id.intentSBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changeBtn:
                Message message=new Message();
                message.what=UPDATE_TEXT;
                handler.sendMessage(message);
                break;
            case R.id.startBtn:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.stopBtn:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.bindBtn:
                bindService(new Intent(this, MyService.class), serviceConnection, BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.unbindBtn:
                unbindService(serviceConnection);
                break;
            case R.id.intentSBtn:
                startService(new Intent(this, MyIntentService.class));
                break;
        }
    }


}
