package com.helon.endlessloop;

import android.support.v4.view.ViewPager;

/**
 * ViewPager滑动监听事件
 * Created by yaohailong on 2016/12/1.
 */
public class IViewPagerListener implements ViewPager.OnPageChangeListener {
    /** 实际显示Fragment的个数*/
    private static final int POINT_LENGTH = 3;
    /** 第一个显示的Fragment的位置*/
    private static final int FIRST_ITEM_INDEX = 1;
    /** ViewPager是否切换*/
    private boolean mIsChanged = false;
    /** 当前ViewPager显示的下标*/
    private int mCurrentPagePosition = FIRST_ITEM_INDEX;
    /** ViewPager对象*/
    private ViewPager mPager = null;
    /** ViewPager切换之后通知页面的回调对象*/
    private IPagerChangeListener mListener = null;
    /** 上一次ViewPager显示的位置*/
    private int mLastPagePosition = mCurrentPagePosition;
    private int mDataTotalLenght = 0;
    private int mPointer = 0;
    
    public IViewPagerListener(ViewPager pager,IPagerChangeListener listener,int dataTotalLength,int pointer){
        this.mPager = pager;
        this.mListener = listener;
        this.mDataTotalLenght = dataTotalLength;
        this.mPointer = pointer;
    }
	/** 重置数据指针的位置*/
	public void setPointer(int pointer){
        this.mPointer = pointer;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        
    }

    @Override
    public void onPageSelected(int pPosition) {
        mIsChanged = true;
        if (pPosition > POINT_LENGTH) {// 末位之后，跳转到首位（1）
            mCurrentPagePosition = FIRST_ITEM_INDEX;
        } else if (pPosition < FIRST_ITEM_INDEX) {// 首位之前，跳转到末尾（3）
            mCurrentPagePosition = POINT_LENGTH;
        } else {
            mCurrentPagePosition = pPosition;
        }
    }

    @Override
    public void onPageScrollStateChanged(int pState) {
        if (ViewPager.SCROLL_STATE_IDLE == pState) {
            if (mIsChanged) {
                mIsChanged = false;
                mPager.setCurrentItem(mCurrentPagePosition, false);
                if(null != mListener){
                    int pointer;
                    if(Math.abs(mLastPagePosition-mCurrentPagePosition) >= 2){
                        if(mLastPagePosition > mCurrentPagePosition){
                            pointer = 1;
                        }else{
                            pointer = -1;
                        }
                    }else {
                        if(mLastPagePosition > mCurrentPagePosition){
                            pointer = -1;
                        }else{
                            pointer = 1;
                        }
                    }
                    // 记录当次位置
                    mLastPagePosition = mCurrentPagePosition;

                    /** 计算当次数据下标*/
                    mPointer += pointer;
                    if(mPointer > mDataTotalLenght-1){
                        mPointer = 0;
                    }else if(mPointer < 0){
                        mPointer = mDataTotalLenght-1;
                    }
                    
                    // 通知页面数据已经更新
                    mListener.onPagerChange(mCurrentPagePosition,mPointer);
                }
            }
        }
    }
}
