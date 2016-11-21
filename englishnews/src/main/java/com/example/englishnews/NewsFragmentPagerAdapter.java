package com.example.englishnews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<String> tabs;
    private List<Fragment> fragmentList;
    public NewsFragmentPagerAdapter(FragmentManager fm,
List<Fragment> fragmentList,List<String> tabs) {
        super(fm);
        this.tabs=tabs;
        this.fragmentList=fragmentList;

    }
    @Override
    public Fragment getItem(int idx) {
        return null;
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
