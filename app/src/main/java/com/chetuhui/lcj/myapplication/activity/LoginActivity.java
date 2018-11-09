package com.chetuhui.lcj.myapplication.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.myapplication.R;
import com.chetuhui.lcj.myapplication.tool.AnimationTool;
import com.chetuhui.lcj.myapplication.tool.KeyboardTool;

public class LoginActivity extends ActivityBase implements View.OnClickListener {


    private ImageView mIvLoginLogo;
    /**  */
    private EditText mEtLoginMobile;
    private ImageView mIvLoginCleanPhone;
    /**  */
    private EditText mEtLoginPassword;
    private ImageView mIvLoginCleanPassword;
    private ImageView mIvLoginShowPwd;
    /**
     * 登录
     */
    private TextView mTvLoginDenglu;
    /**
     * 注册新用户
     */
    private TextView mTvLoginRegist;
    /**
     * 忘记密码
     */
    private TextView mTvLoginForgetPassword;
    /**
     * 第三方登录
     */
    private TextView mTvLoginWeixin;
    private NestedScrollView mSvLogin;
    /**
     * 登录及代表您已阅读并同意一下协议
     */
    private TextView mTvLogin1;
    /**
     * 《车助宝服务协议》
     */
    private TextView mTvLoginXieyi;
    /**
     * 《法律声明及隐私权政策》
     */
    private TextView mTvLoginZhengce;


    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;
    private LinearLayout mLlLoginContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this);
        }
        initView();
        initEvent();
    }


    private void initView() {
        mIvLoginLogo = (ImageView) findViewById(R.id.iv_login_logo);
        mIvLoginLogo.setOnClickListener(this);
        mEtLoginMobile = (EditText) findViewById(R.id.et_login_mobile);
        mIvLoginCleanPhone = (ImageView) findViewById(R.id.iv_login_clean_phone);
        mIvLoginCleanPhone.setOnClickListener(this);
        mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
        mIvLoginCleanPassword = (ImageView) findViewById(R.id.iv_login_clean_password);
        mIvLoginCleanPassword.setOnClickListener(this);
        mIvLoginShowPwd = (ImageView) findViewById(R.id.iv_login_show_pwd);
        mIvLoginShowPwd.setOnClickListener(this);
        mTvLoginDenglu = (TextView) findViewById(R.id.tv_login_denglu);
        mTvLoginDenglu.setOnClickListener(this);
        mTvLoginRegist = (TextView) findViewById(R.id.tv_login_regist);
        mTvLoginRegist.setOnClickListener(this);
        mTvLoginForgetPassword = (TextView) findViewById(R.id.tv_login_forget_password);
        mTvLoginForgetPassword.setOnClickListener(this);
        mTvLoginWeixin = (TextView) findViewById(R.id.tv_login_weixin);
        mTvLoginWeixin.setOnClickListener(this);
        mSvLogin = (NestedScrollView) findViewById(R.id.sv_login_);
        mTvLogin1 = (TextView) findViewById(R.id.tv_login_1);
        mTvLoginXieyi = (TextView) findViewById(R.id.tv_login_xieyi);
        mTvLoginXieyi.setOnClickListener(this);
        mTvLoginZhengce = (TextView) findViewById(R.id.tv_login_zhengce);
        mTvLoginZhengce.setOnClickListener(this);


        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
        mLlLoginContent = (LinearLayout) findViewById(R.id.ll_login_content);
    }

    private void initEvent() {
        mEtLoginMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvLoginCleanPhone.getVisibility() == View.GONE) {
                    mIvLoginCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvLoginCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        mEtLoginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvLoginCleanPassword.getVisibility() == View.GONE) {
                    mIvLoginCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvLoginCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty()) {
                    return;
                }
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(mContext, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtLoginPassword.setSelection(s.length());
                }
            }
        });
        /**
         * 禁止键盘弹起的时候可以滚动
         */
        mSvLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mSvLogin.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = mLlLoginContent.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mLlLoginContent, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        AnimationTool.zoomIn(mIvLoginLogo, 0.6f, dist);
                    }

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((mLlLoginContent.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mLlLoginContent, "translationY", mLlLoginContent.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        AnimationTool.zoomOut(mIvLoginLogo, 0.6f);
                    }
                }
            }
        });


    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @Override
    public void onClick(View v) {
        Intent intent =null;
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_login_logo:
                break;
            case R.id.iv_login_clean_phone:
                break;
            case R.id.iv_login_clean_password:
                break;
            case R.id.iv_login_show_pwd:
                if (mEtLoginPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvLoginShowPwd.setImageResource(R.drawable.openeye);
                } else {
                    mEtLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvLoginShowPwd.setImageResource(R.drawable.closeeye);
                }
                String pwd = mEtLoginPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd)) {
                    mEtLoginPassword.setSelection(pwd.length());

                }

                break;
            case R.id.tv_login_denglu:
                KeyboardTool.hideSoftInput(mContext);
                break;
            case R.id.tv_login_regist:
                intent=new Intent(this,RegisteredActivity.class);
                break;
            case R.id.tv_login_forget_password:
                break;
            case R.id.tv_login_weixin:
                break;
            case R.id.tv_login_xieyi:
                break;
            case R.id.tv_login_zhengce:
                break;
        }
        if (intent!=null){
            startActivity(intent);
        }
    }
}
