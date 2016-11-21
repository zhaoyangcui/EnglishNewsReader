package com.example.administrator.englishnewsreader.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.administrator.englishnewsreader.fragment.PhotoFragment;
import com.example.administrator.englishnewsreader.fragment.SectionsContentFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/10.
 */
public class ImgTask implements Runnable{
    int flag;
    public ImgTask(int flag){
        this.flag = flag;
    }
    public static Handler handler_img;
    @Override
    public void run() {
        Looper.prepare();
        handler_img = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String url = (String) msg.obj;

                URL myFileUrl = null;
                Bitmap bitmap = null;
                try {
                    myFileUrl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
                if (flag == 0){
                    Message msg_handle = SectionsContentFragment.handler.obtainMessage();
                    msg_handle.obj = bitmap;
                    SectionsContentFragment.handler.sendMessage(msg_handle);
                }else {
                    Message msg_photo = PhotoFragment.handler_photo.obtainMessage();
                    msg_photo.obj = bitmap;
                    PhotoFragment.handler_photo.sendMessage(msg_photo);
                }
            }
        };
        Looper.loop();
    }
}
