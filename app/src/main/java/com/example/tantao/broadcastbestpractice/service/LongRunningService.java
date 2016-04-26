package com.example.tantao.broadcastbestpractice.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.tantao.broadcastbestpractice.broadcast.AlarmReceiver;

import java.util.Date;

/**
 * Created by tantao on 2016/4/19.
 */
public class LongRunningService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService","date:"+new Date().toString());
            }
        }).start();
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int anhor=60*1000;
        long triggerAlatem= SystemClock.elapsedRealtime()+anhor;
        PendingIntent pi=PendingIntent.getActivities(this,0,new Intent[]{new Intent(this,AlarmReceiver.class)},0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAlatem,pi);


        return super.onStartCommand(intent, flags, startId);
    }
}
