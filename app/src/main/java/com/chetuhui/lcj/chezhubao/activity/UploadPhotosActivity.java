package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

public class UploadPhotosActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarUp;
    /**
     * 下一步
     */
    private TextView mTvUpXyb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photos);
        initView();
    }

    private void initView() {
        mTitlebarUp = (CommonTitleBar) findViewById(R.id.titlebar_up);
        mTitlebarUp.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mTvUpXyb = (TextView) findViewById(R.id.tv_up_xyb);
        mTvUpXyb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_up_xyb:
                intent=new Intent(UploadPhotosActivity.this,ChooseBusinessActivity.class);
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }
}
