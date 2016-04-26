package com.example.tantao.broadcastbestpractice.gosn;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.tantao.broadcastbestpractice.application.MyApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tantao on 2016/4/22.
 */
public class HttpUtil {

   public static void sendResponsWithHttpURlConnection(final String address,final HttpCallbackListener listener){
      // if (!isNetworkAvailable()){
      //     Toast.makeText(MyApplication.getContext(),"network is unavaliable",Toast.LENGTH_SHORT).show();
      //     return;
      // }

       new Thread(new Runnable() {
           @Override
           public void run() {
               HttpURLConnection connection=null;
               try {
                   URL url=new URL(address);
                   connection=(HttpURLConnection)url.openConnection();
                   connection.setRequestMethod("GET");
                   connection.setConnectTimeout(8000);
                   connection.setReadTimeout(8000);
                   connection.setDoInput(true);
                   connection.setDoOutput(true);
                   InputStream in=connection.getInputStream();
                   BufferedReader reader=new BufferedReader(new InputStreamReader(in,"utf-8"));
                   StringBuilder response=new StringBuilder();
                   String line;
                   while ((line=reader.readLine())!=null){
                        response.append(line);
                   }
                   if (listener!=null){
                       listener.onFinish(response.toString());
                   }

               }catch (Exception e){
                   if (listener!=null)
                       listener.onError(e.toString());
               }finally {
                   if (connection!=null)
                       connection.disconnect();
               }
           }
       }).start();
   }

    private static boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null){
            NetworkInfo[] networkInfos=connectivityManager.getAllNetworkInfo();
            if (networkInfos != null && networkInfos.length > 0){
                for (int i=0;i<networkInfos.length;i++){
                    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }
}
