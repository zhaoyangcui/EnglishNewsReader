package com.example.administrator.englishnewsreader.web;

import android.content.Context;
import android.os.Message;
import android.support.annotation.MainThread;
import android.util.Log;

import com.example.administrator.englishnewsreader.activity.DrawerLayoutActivity;
import com.example.administrator.englishnewsreader.activity.SectionsActivity;
import com.example.administrator.englishnewsreader.handler.MainHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/6.
 */
public class HTTPGetter {
    //    private String address = "http://www.jianshu.com/users/1c40186e3248/latest_articles";
//    private String address = "http://10.18.22.21:8080/englishnews";
    private Context context;
    private URL url;
    private MainHandler handler_main;

    public HTTPGetter(Context context, String request_url) throws MalformedURLException {
        this.context = context;
        url = new URL(request_url);
//        new Thread(new GetFromWebTask()).start();
        new Thread(new GetFromWebTask()).start();
    }

    private class GetFromWebTask implements Runnable {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                InputStream ins = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
                String line = "";
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                handler_main = ((DrawerLayoutActivity) context).getHandler_drawerlayout();
//                Log.d("denig",builder.toString());

                Message msg = handler_main.obtainMessage();
                msg.obj = builder;

                if (url.toString().equals("http://192.168.191.1:8080/englishnews")) {
                    msg.what = 0;
                }else {
                    msg.what = 1;
                }
                handler_main.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
