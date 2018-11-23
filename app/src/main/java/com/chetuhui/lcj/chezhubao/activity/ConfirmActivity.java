package com.chetuhui.lcj.chezhubao.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

public class ConfirmActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarConfirm;
    /**
     * 去支付
     */
    private SuperTextView mTvConfirmQuzhifu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        initView();
    }

    private void initView() {
        mTitlebarConfirm = (CommonTitleBar) findViewById(R.id.titlebar_confirm);
        mTitlebarConfirm.setListener(new CommonTitleBar.OnTitleBarListener() {
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


        mTvConfirmQuzhifu = (SuperTextView) findViewById(R.id.tv_confirm_quzhifu);
        mTvConfirmQuzhifu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.titlebar_confirm:
                break;
            case R.id.tv_confirm_quzhifu:
                showView(ConfirmActivity.this);

                break;
        }
    }
    public static void showView(Context context) {
        final BaseDialog rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pay_show, null);
        final TextView wx=view.findViewById(R.id.tv_dg_pay_wx);
        TextView zfb=view.findViewById(R.id.tv_dg_pay_zfb);
        TextView cancel=view.findViewById(R.id.tv_dg_pay_cancel);
        wx.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                rxDialog.cancel();
            }
        });
        zfb.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                rxDialog.cancel();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });


        rxDialog.setContentView(view);
        rxDialog.getLayoutParams();
        rxDialog.mLayoutParams.gravity = Gravity.BOTTOM;

        rxDialog.show();
        rxDialog.setFullScreen();
    }

}
