package com.helon.endlessloop;

import android.support.v4.app.Fragment;

/**
 * Fragment的父类，提供一个更新数据的接口
 * Created by yaohailong on 2016/11/30.
 */
public abstract class IFragment extends Fragment{

    /**
     * 设置显示的内容
     * @param objects 具体要显示的内容
     */
    public abstract void upDateView(Object... objects);
}
