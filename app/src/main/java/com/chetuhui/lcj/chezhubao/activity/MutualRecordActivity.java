package com.chetuhui.lcj.chezhubao.activity;

import android.os.Bundle;
import android.view.View;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

public class MutualRecordActivity extends ActivityBase {

    private CommonTitleBar mTitlebarMr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_record);
        initView();
    }

    private void initView() {
        mTitlebarMr = (CommonTitleBar) findViewById(R.id.titlebar_mr);
        mTitlebarMr.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

    }
}
