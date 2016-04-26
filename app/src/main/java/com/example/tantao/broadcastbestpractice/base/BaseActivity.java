package com.example.tantao.broadcastbestpractice.base;

import android.app.Activity;
import android.os.Bundle;

import com.example.tantao.broadcastbestpractice.activitymanger.ActivityCollector;

/**
 * Created by tantao on 2016/4/15.
 */
public class BaseActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.AddActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.RemoveActivity(this);
    }
}
