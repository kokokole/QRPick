package com.ldcc.eleven.qrpick.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ldcc.eleven.qrpick.fragments.ItemViewFragment;

import java.util.ArrayList;


public class ListPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ItemViewFragment> fragments = new ArrayList<ItemViewFragment>();
    public ListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addItem(ItemViewFragment fragment){
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}