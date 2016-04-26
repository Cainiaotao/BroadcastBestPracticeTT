package com.example.tantao.broadcastbestpractice.gosn;

/**
 * Created by tantao on 2016/4/22.
 */
public interface HttpCallbackListener {

    //获得请求的数据
    void onFinish(String request);

    //获取错误消息
    void onError(String error);
}
