package com.helon.myfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import com.helon.endlessloop.IFragment;
import com.helon.endlessloop.IPagerAdapter;
import com.helon.endlessloop.IPagerChangeListener;
import com.helon.endlessloop.IViewPagerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 无限循环，可滑动的ViewPager
 */
public class MainActivity extends FragmentActivity implements IPagerChangeListener {
    private ViewPager mPager = null;
    private IPagerAdapter mAdapter = null;
    private List<IFragment> mList = new ArrayList<>();
    // title对应的集合
    private List<String> mData = new ArrayList<>();
    // 内容对应的集合
    private List<String> mContentData = new ArrayList<>();
    /** 当前ViewPager显示的下标*/
    private int mCurrentPagerIndex = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        for (int i=0;i<280;i++){
            mData.add("这是title"+i);
        }
        for (int i=0;i<280;i++){
            mContentData.add("这是内容"+i);
        }
        mPager = (ViewPager) findViewById(R.id.viewpager);
        
        mList.add(new ThirdFragment());
        mList.add(new FirstFragment());
        mList.add(new SecondFragment());
        mList.add(new ThirdFragment());
        mList.add(new FirstFragment());
        mAdapter = new IPagerAdapter(getSupportFragmentManager(),mList);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(3);
        mPager.setCurrentItem(mCurrentPagerIndex, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                afterGetDataList();
                afterGetContentData(0);
            }
        },300);
    }

    @Override
    public void onPagerChange(int index,int pointer) {
        String title = mData.get(pointer);
        mCurrentPagerIndex = index;
        /** 此处通过title获取content内容后，可以调用方法*/
        afterGetContentData(pointer);
    }

    /**
     * 获取title数据集合之后调用的方法
     */
    private void afterGetDataList(){
        mPager.setOnPageChangeListener(new IViewPagerListener(mPager, this, mData.size(), 0));
    }

    /**
     * 获取一页内容之后调用的方法（如果是一次性获取了所有数据，则可以不用在此方法中调用upDateView方法）
     * @param pointer 可以是任意类型的数据，根据自己的需求定义
     */
    private void afterGetContentData(int pointer){
        mList.get(mCurrentPagerIndex).upDateView(mContentData.get(pointer));
    }
}
