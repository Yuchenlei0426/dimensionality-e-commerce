package com.bawei.shop2019101511.fragment.adaper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class OrderAdaper extends FragmentPagerAdapter {
    ArrayList<Fragment> fragment;
    public OrderAdaper(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);

    }
    public void addAll(ArrayList<Fragment> fragment){
        this.fragment=fragment;
    }

    @Override
    public Fragment getItem(int i) {
        return fragment.get(i);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

}
