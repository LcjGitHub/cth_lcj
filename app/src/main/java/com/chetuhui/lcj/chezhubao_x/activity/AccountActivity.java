package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

public class AccountActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAccount;
    private RelativeLayout mRlAccountXgsjh;
    private RelativeLayout mRlAccountXgdlmm;
    private RelativeLayout mRlAccountSfbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
    }

    private void initView() {
        mTitlebarAccount = (CommonTitleBar) findViewById(R.id.titlebar_account);
        mTitlebarAccount.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
                // CommonTitleBar.ACTION_LEFT_TEXT;        // 左边TextView被点击
                // CommonTitleBar.ACTION_LEFT_BUTTON;      // 左边ImageBtn被点击
                // CommonTitleBar.ACTION_RIGHT_TEXT;       // 右边TextView被点击
                // CommonTitleBar.ACTION_RIGHT_BUTTON;     // 右边ImageBtn被点击
                // CommonTitleBar.ACTION_SEARCH;           // 搜索框被点击,搜索框不可输入的状态下会被触发
                // CommonTitleBar.ACTION_SEARCH_SUBMIT;    // 搜索框输入状态下,键盘提交触发，此时，extra为输入内容
                // CommonTitleBar.ACTION_SEARCH_VOICE;     // 语音按钮被点击
                // CommonTitleBar.ACTION_SEARCH_DELETE;    // 搜索删除按钮被点击
                // CommonTitleBar.ACTION_CENTER_TEXT;      // 中间文字点击
            }
        });




        mRlAccountXgsjh = (RelativeLayout) findViewById(R.id.rl_account_xgsjh);
        mRlAccountXgsjh.setOnClickListener(this);
        mRlAccountXgdlmm = (RelativeLayout) findViewById(R.id.rl_account_xgdlmm);
        mRlAccountXgdlmm.setOnClickListener(this);
        mRlAccountSfbd = (RelativeLayout) findViewById(R.id.rl_account_sfbd);
        mRlAccountSfbd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {

        Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_account_xgsjh:
                intent=new Intent(AccountActivity.this,ChangephoneActivity.class);
                break;
            case R.id.rl_account_xgdlmm:
                intent=new Intent(AccountActivity.this,ChangePasswordActivity.class);
                break;
            case R.id.rl_account_sfbd:
                intent=new Intent(AccountActivity.this,HomeActivity.class);
                break;
        }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
            //BaseToast.normal("点得太快了");
            return;
        }
    }
}
