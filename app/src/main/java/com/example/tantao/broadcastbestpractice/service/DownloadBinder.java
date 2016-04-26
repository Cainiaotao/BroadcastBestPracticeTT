package com.example.tantao.broadcastbestpractice.service;

import android.os.Binder;
import android.util.Log;

/**
 * Created by tantao on 2016/4/19.
 */
public class DownloadBinder extends Binder {

    public void startDownload(){
        Log.d("DownloadBinder", "startDownload");
    }
    public int getProgress(){
        Log.d("DownloadBinder","getProgress");
        return 0;
    }
}
