package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

public class IHelpActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarIhelp;
    /**
     * 请输入车牌号搜索
     */
    private EditText mEtIhelpSousuo;
    /**
     * 我要救助
     */
    private SuperTextView mTvIhelpWyjz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ihelp);
        initView();
    }

    private void initView() {
        mTitlebarIhelp = (CommonTitleBar) findViewById(R.id.titlebar_ihelp);
        mTitlebarIhelp.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });
        mEtIhelpSousuo = (EditText) findViewById(R.id.et_ihelp_sousuo);
        mTvIhelpWyjz = (SuperTextView) findViewById(R.id.tv_ihelp_wyjz);
        mTvIhelpWyjz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =null;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_ihelp_wyjz:
                intent= new Intent(IHelpActivity.this,MutualAssistanceActivity.class);
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }
}
