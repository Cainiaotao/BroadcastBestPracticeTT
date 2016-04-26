package com.example.tantao.broadcastbestpractice.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by tantao on 2016/4/22.
 */
public class MyApplication extends Application {


    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context.getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
