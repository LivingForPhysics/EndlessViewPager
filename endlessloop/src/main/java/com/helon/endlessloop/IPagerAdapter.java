package com.helon.endlessloop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager适配器
 * Created by yaohailong on 2016/11/30.
 */
public class IPagerAdapter extends FragmentPagerAdapter {
    private List<IFragment> mListViews;

    public IPagerAdapter(FragmentManager fm, List<IFragment> list) {
        super(fm);
        mListViews = new ArrayList<>();
        mListViews.addAll(list);
    }

    @Override
    public Fragment getItem(int i) {
        return mListViews.get(i);
    }

    @Override
    public int getCount() {
        return mListViews.size();
    }
}
