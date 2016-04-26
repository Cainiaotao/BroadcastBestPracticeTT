package com.example.tantao.broadcastbestpractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.tantao.broadcastbestpractice.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NotifiActivity extends BaseActivity {

    @InjectView(R.id.noticeBtn)
    Button noticeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifi);
        ButterKnife.inject(this);


    }

    private void sendNotification()
    {
        Intent openintent = new Intent(NotifiActivity.this,NotifiMessageActivity.class);
        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pi=PendingIntent.getActivities(NotifiActivity.this,0,new Intent[]{openintent},PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification=new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("this is title")
                .setContentText("this is context text")
                .setContentIntent(pi)
                .setWhen(System.currentTimeMillis())
                .build();
        manager.notify(1,notification);
    }

    @OnClick(R.id.noticeBtn)
    public void onClick() {
        sendNotification();

    }
}
