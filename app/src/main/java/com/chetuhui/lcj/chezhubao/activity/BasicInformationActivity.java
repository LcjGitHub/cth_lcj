package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

public class BasicInformationActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarBi;
    /**
     * 下一步
     */
    private TextView mTvBiXyb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_information);
        initView();
    }

    private void initView() {
        mTitlebarBi = (CommonTitleBar) findViewById(R.id.titlebar_bi);
        mTitlebarBi.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mTvBiXyb = (TextView) findViewById(R.id.tv_bi_xyb);
        mTvBiXyb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;

        switch (v.getId()) {
            default:
                break;
            case R.id.tv_bi_xyb:
                intent=new Intent(BasicInformationActivity.this,UploadPhotosActivity.class);

                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }
}
