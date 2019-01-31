package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

public class PaySuccessActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarPaySuccess;
    /**
     * 恭喜您，充值成功！
     */
    private TextView mTvPsCg;
    /**
     * 我的互助单
     */
    private SuperTextView mTvPsWdhzd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        initView();
    }

    private void initView() {
        mTitlebarPaySuccess = (CommonTitleBar) findViewById(R.id.titlebar_pay_success);
        mTitlebarPaySuccess.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {
                    ActivityTool.finishAllActivity();
                    startActivity(new Intent(PaySuccessActivity.this, HomeActivity.class));



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

        mTvPsCg = (TextView) findViewById(R.id.tv_ps_cg);
        mTvPsWdhzd = (SuperTextView) findViewById(R.id.tv_ps_wdhzd);
        mTvPsWdhzd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_ps_wdhzd:
                ActivityTool.finishAllActivity();
                startActivity(new Intent(PaySuccessActivity.this, HomeActivity.class));
                startActivity(new Intent(PaySuccessActivity.this, MyMutualHelpActivity.class));

                break;
        }
    }
}
