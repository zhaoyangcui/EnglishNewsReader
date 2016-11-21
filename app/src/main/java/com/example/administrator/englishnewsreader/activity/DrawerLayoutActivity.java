package com.example.administrator.englishnewsreader.activity;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.englishnewsreader.R;
import com.example.administrator.englishnewsreader.fragment.PhotoFragment;
import com.example.administrator.englishnewsreader.fragment.SectionsContentFragment;
import com.example.administrator.englishnewsreader.fragment.SectionsFragment;
import com.example.administrator.englishnewsreader.handler.MainHandler;
import com.example.administrator.englishnewsreader.listener.DrawerListItemClickedListener;
import com.example.administrator.englishnewsreader.web.HTTPGetter;

import java.net.MalformedURLException;

public class DrawerLayoutActivity extends AppCompatActivity implements SectionsFragment.OnFragmentInteractionListener,SectionsContentFragment.OnFragmentInteractionListener,PhotoFragment.OnFragmentInteractionListener{
    private String[] mPlanetTitles = new String[]{"Featured Sections","Photos you should see"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private MainHandler handler_drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        handler_drawerlayout = new MainHandler(Looper.getMainLooper(),this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this,R.layout.drawer_list_item,mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerListItemClickedListener(this));
    }

    public MainHandler getHandler_drawerlayout(){
        return handler_drawerlayout;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
