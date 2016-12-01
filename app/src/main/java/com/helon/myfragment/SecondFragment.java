package com.helon.myfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helon.endlessloop.IFragment;


/**
 * Created by yaohailong on 2016/11/30.
 */
public class SecondFragment extends IFragment {
    private TextView mText = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout,null);
        view.setBackgroundColor(Color.RED);
        mText = (TextView) view.findViewById(R.id.text);
        return view;
    }
    public void upDateView(Object... content){
        String text = (String)content[0];
        mText.setText("SecondFragment"+text);
    }
}
