package com.chetuhui.lcj.chezhubao_x.activity;

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

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.TextTool;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

public class PhLoginActivity extends ActivityBase implements View.OnClickListener {

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
                finish();
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
            //BaseToast.normal("点得太快了");
            return;
        }
    }
}
