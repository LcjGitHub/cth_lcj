package com.chetuhui.lcj.chezhubao_x.utils;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.chetuhui.lcj.chezhubao_x.fragment.FindFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.GszFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.HomeFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MeFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MmhAllFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MmhBzzFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MmhDsxFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MmhYsxFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MrAllFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MrDjzFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MrDqrFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MrYjjFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.YhzFragment;


public class DataGenerator {


    public static Fragment[] getFragments(){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance();
        fragments[1] = FindFragment.newInstance();
        fragments[2] = HomeFragment.newInstance();
        fragments[3] = MeFragment.newInstance();
        return fragments;
    }
    public static Fragment[] getFragments_mutual(){
        Fragment fragments[] = new Fragment[2];
        fragments[0] = GszFragment.newInstance();
        fragments[1] = YhzFragment.newInstance();

        return fragments;
    }
    public static Fragment[] getFragments_mmh(){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = MmhAllFragment.newInstance();
        fragments[1] = MmhBzzFragment.newInstance();
        fragments[2] = MmhDsxFragment.newInstance();
        fragments[3] = MmhYsxFragment.newInstance();


        return fragments;
    }
    public static Fragment[] getFragments_mr(){
        Fragment fragments[] = new Fragment[4];
        fragments[0] = MrAllFragment.newInstance();
        fragments[1] = MrDqrFragment.newInstance();
        fragments[2] = MrDjzFragment.newInstance();
        fragments[3] = MrYjjFragment.newInstance();


        return fragments;
    }
}
