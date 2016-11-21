package com.example.administrator.englishnewsreader.activity;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.englishnewsreader.R;
import com.example.administrator.englishnewsreader.adapter.SectionsContentAdapter;
import com.example.administrator.englishnewsreader.fragment.SectionsFragment;
import com.example.administrator.englishnewsreader.fragment.TestFragment;
import com.example.administrator.englishnewsreader.handler.MainHandler;
import com.example.administrator.englishnewsreader.web.HTTPGetter;

import java.net.MalformedURLException;
import java.util.LinkedList;

public class SectionsActivity extends AppCompatActivity implements TestFragment.OnFragmentInteractionListener{
    private MainHandler handler_sections;

    public MainHandler getHandler_sections() {
        return handler_sections;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        LinkedList<String> list = new LinkedList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        ViewPager pager = (ViewPager) findViewById(R.id.sections_vp);
        LinkedList<Fragment> list_f = new LinkedList<>();
        list_f.add(new TestFragment());
        list_f.add(new TestFragment());
        list_f.add(new TestFragment());
        list_f.add(new TestFragment());
        SectionsContentAdapter adapter = new SectionsContentAdapter(this.getSupportFragmentManager(),list_f,list);
        pager.setAdapter(adapter);
//        handler_sections = new MainHandler(Looper.getMainLooper());
//        try {
//            HTTPGetter http = new HTTPGetter(this,"http://10.18.22.21:8080/englishnews");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
