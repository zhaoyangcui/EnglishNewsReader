package com.example.administrator.englishnewsreader.listener;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.englishnewsreader.R;
import com.example.administrator.englishnewsreader.activity.DrawerLayoutActivity;
import com.example.administrator.englishnewsreader.data.Sections;
import com.example.administrator.englishnewsreader.fragment.SectionsFragment;
import com.example.administrator.englishnewsreader.util.ImgTask;
import com.example.administrator.englishnewsreader.web.HTTPGetter;

import java.net.MalformedURLException;

/**
 * Created by Administrator on 2016/11/9.
 */
public class DrawerListItemClickedListener implements ListView.OnItemClickListener{

    public Context context;

    public DrawerListItemClickedListener(Context context){
        this.context = context;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //得到被点击的项，加载不同的fragment
        switch (position){
            case 0:
                Log.d("debug","click 000");
                inflateSections("http://192.168.191.1:8080/englishnews",0);
                break;
            case 1:
                Log.d("debug","click 111");
                inflateSections("http://192.168.191.1:8080/photoes",1);
                break;

        }
    }

    private void inflateSections(String href,int flag){
        String url = href;
        try {
            new HTTPGetter(context,url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new Thread(new ImgTask(flag)).start();
//        Bundle argument = new Bundle();
//        sections_fragment.setArguments();
    }
}
