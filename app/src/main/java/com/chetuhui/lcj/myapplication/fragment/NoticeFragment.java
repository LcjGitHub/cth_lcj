package com.chetuhui.lcj.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chetuhui.lcj.myapplication.R;


/**
 * Created by zhouwei on 17/4/23.
 */

public class NoticeFragment extends Fragment {

    public static NoticeFragment newInstance(){
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_layout,null);


        return view;
    }
}
