package com.chetuhui.lcj.chezhubao.activity;

import android.os.Bundle;
import android.view.View;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

public class MyMutualHelpActivity extends ActivityBase {

    private CommonTitleBar mTitlebarMmh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mutual_help);
        initView();
    }

    private void initView() {
        mTitlebarMmh = (CommonTitleBar) findViewById(R.id.titlebar_mmh);
        mTitlebarMmh.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });


    }
}
