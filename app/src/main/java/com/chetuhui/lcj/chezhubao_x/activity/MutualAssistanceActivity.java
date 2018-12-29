package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

public class MutualAssistanceActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarMa;
    /**
     * 立即申请
     */
    private TextView mTvMaLjsq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_assistance);
        initView();
    }

    private void initView() {
        mTitlebarMa = (CommonTitleBar) findViewById(R.id.titlebar_ma);
        mTitlebarMa.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mTvMaLjsq = (TextView) findViewById(R.id.tv_ma_ljsq);
        mTvMaLjsq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =null;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_ma_ljsq:
                intent=new Intent(MutualAssistanceActivity.this,SelectAidActivity.class);
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }
}
