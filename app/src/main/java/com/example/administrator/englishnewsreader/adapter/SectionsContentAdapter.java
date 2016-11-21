package com.example.administrator.englishnewsreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class SectionsContentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> tabs;
    private LinkedList<Fragment> fragmentList;
    public SectionsContentAdapter(FragmentManager fm, LinkedList<Fragment> fragmentlist,LinkedList<String> tablist) {
        super(fm);
        this.tabs = new ArrayList<>(tablist);
        this.fragmentList = fragmentlist;
    }

    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }

}
