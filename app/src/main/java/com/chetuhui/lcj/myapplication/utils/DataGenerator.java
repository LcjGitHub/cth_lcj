package com.chetuhui.lcj.myapplication.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetuhui.lcj.myapplication.R;
import com.chetuhui.lcj.myapplication.fragment.FindFragment;
import com.chetuhui.lcj.myapplication.fragment.HomeFragment;
import com.chetuhui.lcj.myapplication.fragment.MeFragment;


/**
 * Created by zhouwei on 17/4/23.
 */

public class DataGenerator {


    public static Fragment[] getFragments(){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance();
        fragments[1] = FindFragment.newInstance();
        fragments[2] = HomeFragment.newInstance();
        fragments[3] = MeFragment.newInstance();
        return fragments;
    }


}
