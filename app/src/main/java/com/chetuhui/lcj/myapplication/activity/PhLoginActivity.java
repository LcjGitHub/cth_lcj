package com.chetuhui.lcj.myapplication.activity;

import android.content.Intent;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetuhui.lcj.myapplication.R;
import com.chetuhui.lcj.myapplication.tool.BaseTool;
import com.chetuhui.lcj.myapplication.tool.TextTool;
import com.chetuhui.lcj.myapplication.view.BaseToast;

public class PhLoginActivity extends ActivityBase implements View.OnClickListener {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private final int MIN_CLICK_DELAY_TIME = 1000;
    private ImageView mIvPhloginBack;
    /**
     * 手机号
     */
    private EditText mEtPhloginPh;
    private ImageView mIvRegisteredPhClear;
    /**
     * 密码
     */
    private EditText mEtPhloginPw;
    private ImageView mIvPhloginPwEye;
    /**
     * 登录
     */
    private TextView mTvPhloginDenglu;
    /**
     * 还没有账号？马上注册
     */
    private TextView mTvPhloginZhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_login);
        initView();
    }

    private void initView() {
        mIvPhloginBack = (ImageView) findViewById(R.id.iv_phlogin_back);
        mIvPhloginBack.setOnClickListener(this);
        mEtPhloginPh = (EditText) findViewById(R.id.et_phlogin_ph);
        mEtPhloginPh.setOnClickListener(this);
        mIvRegisteredPhClear = (ImageView) findViewById(R.id.iv_registered_ph_clear);
        mIvRegisteredPhClear.setOnClickListener(this);
        mEtPhloginPw = (EditText) findViewById(R.id.et_phlogin_pw);
        mEtPhloginPw.setOnClickListener(this);
        mIvPhloginPwEye = (ImageView) findViewById(R.id.iv_phlogin_pw_eye);
        mIvPhloginPwEye.setOnClickListener(this);
        mTvPhloginDenglu = (TextView) findViewById(R.id.tv_phlogin_denglu);
        mTvPhloginDenglu.setOnClickListener(this);
        mTvPhloginZhuce = (TextView) findViewById(R.id.tv_phlogin_zhuce);
        mTvPhloginZhuce.setOnClickListener(this);
        // 响应点击事件的话必须设置以下属性
        mTvPhloginZhuce.setMovementMethod(LinkMovementMethod.getInstance());

        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                .append("还没有账号？马上").append("注册").setForegroundColor(getResources().getColor(R.color.home_button))

                .into(mTvPhloginZhuce);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_phlogin_back:
                break;
            case R.id.et_phlogin_ph:
                break;
            case R.id.iv_registered_ph_clear:
                break;
            case R.id.et_phlogin_pw:
                break;
            case R.id.iv_phlogin_pw_eye:
                break;
            case R.id.tv_phlogin_denglu:
                break;
            case R.id.tv_phlogin_zhuce:
                break;
        }
            if (intent!=null){
                startActivity(intent);
            }
        }else {
            BaseToast.normal("点得太快了");
            return;
        }
    }
}
