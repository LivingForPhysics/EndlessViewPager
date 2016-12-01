package com.helon.endlessloop;

/**
 * ViewPager切换成功之后，回调对象
 * Created by yaohailong on 2016/12/1.
 */
public interface IPagerChangeListener {
    /**
     * 回调方法
     * @param index 当前ViewPager显示的下标
     * @param pointer 显示当前数据，在数据结合中的下标:0~(size-1）
     */
    void onPagerChange(int index,int pointer);
}
