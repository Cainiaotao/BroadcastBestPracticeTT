package com.example.tantao.broadcastbestpractice.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.example.tantao.broadcastbestpractice.AlarmActivity;

/**
 * Created by tantao on 2016/4/19.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, AlarmActivity.class));
        

    }

}
