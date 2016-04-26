package com.example.tantao.broadcastbestpractice.service;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.tantao.broadcastbestpractice.MainActivity;
import com.example.tantao.broadcastbestpractice.R;

/**
 * Created by tantao on 2016/4/19.
 */
public class MyService extends Service {


    private DownloadBinder mBinder=new DownloadBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate");
       // NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent=new Intent(this, MainActivity.class);
        PendingIntent pintent=PendingIntent.getActivities(this,0,new Intent[]{intent},0);
        Notification notification=new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Service title")
                .setContentText("Service text")
                .setContentIntent(pintent)
                .setWhen(System.currentTimeMillis())
                .build();
        startForeground(1,notification);
        Log.d("MyService", "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
