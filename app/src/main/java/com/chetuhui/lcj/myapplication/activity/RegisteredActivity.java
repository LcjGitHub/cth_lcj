package com.chetuhui.lcj.myapplication.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.myapplication.R;


public class RegisteredActivity extends ActivityBase implements View.OnClickListener {

    private ImageView mIvRegisteredBack;
    /**
     * 手机号
     */
    private EditText mEtRegisteredPh;
    /**
     * 验证码
     */
    private EditText mEtRegisteredCode;
    /**
     * 获取验证码
     */
    private TextView mTvRegisteredCode;
    /**
     * 设置新密码
     */
    private EditText mEtRegisteredPw;
    private ImageView mIvRegisteredPwEye;
    /**
     * 再次输入密码
     */
    private EditText mEtRegisteredPw2;
    private ImageView mIvRegisteredPwEye2;
    /**
     * 完成
     */
    private TextView mTvRegisteredWancheng;
    /**
     * 绑定手机号
     */
    private TextView mTvRegisteredTitile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
    }

    private void initView() {
        mIvRegisteredBack = (ImageView) findViewById(R.id.iv_registered_back);
        mIvRegisteredBack.setOnClickListener(this);
        mEtRegisteredPh = (EditText) findViewById(R.id.et_registered_ph);

        mEtRegisteredCode = (EditText) findViewById(R.id.et_registered_code);

        mTvRegisteredCode = (TextView) findViewById(R.id.tv_registered_code);
        mTvRegisteredCode.setOnClickListener(this);
        mEtRegisteredPw = (EditText) findViewById(R.id.et_registered_pw);

        mIvRegisteredPwEye = (ImageView) findViewById(R.id.iv_registered_pw_eye);
        mIvRegisteredPwEye.setOnClickListener(this);
        mEtRegisteredPw2 = (EditText) findViewById(R.id.et_registered_pw2);

        mIvRegisteredPwEye2 = (ImageView) findViewById(R.id.iv_registered_pw_eye2);
        mIvRegisteredPwEye2.setOnClickListener(this);
        mTvRegisteredWancheng = (TextView) findViewById(R.id.tv_registered_wancheng);
        mTvRegisteredWancheng.setOnClickListener(this);
        mTvRegisteredTitile = (TextView) findViewById(R.id.tv_registered_titile);
    }

    private void initEvent() {
        mEtRegisteredPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
//                    mCleanPassword.setVisibility(View.VISIBLE);
//                } else if (TextUtils.isEmpty(s)) {
//                    mCleanPassword.setVisibility(View.GONE);
//                }
                if (s.toString().isEmpty()) {
                    return;
                }
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(mContext, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtRegisteredPw.setSelection(s.length());
                }
            }
        });
        mEtRegisteredPw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
//                    mCleanPassword.setVisibility(View.VISIBLE);
//                } else if (TextUtils.isEmpty(s)) {
//                    mCleanPassword.setVisibility(View.GONE);
//                }
                if (s.toString().isEmpty()) {
                    return;
                }
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(mContext, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtRegisteredPw.setSelection(s.length());
                }
            }
        });

//        /**
//         * 禁止键盘弹起的时候可以滚动
//         */
//        mScrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
//        mScrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
//              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
//                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
//                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
//                    int dist = mContent.getBottom() - bottom;
//                    if (dist > 0) {
//                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist);
//                        mAnimatorTranslateY.setDuration(300);
//                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
//                        mAnimatorTranslateY.start();
//                        RxAnimationTool.zoomIn(mLogo, 0.6f, dist);
//                    }
//                    mService.setVisibility(View.INVISIBLE);
//
//                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
//                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
//                    if ((mContent.getBottom() - oldBottom) > 0) {
//                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.getTranslationY(), 0);
//                        mAnimatorTranslateY.setDuration(300);
//                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
//                        mAnimatorTranslateY.start();
//                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
//                        RxAnimationTool.zoomOut(mLogo, 0.6f);
//                    }
//                    mService.setVisibility(View.VISIBLE);
//                }
//            }
//        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_registered_back:
                break;

            case R.id.tv_registered_code:
                break;
            case R.id.et_registered_pw:
                break;
            case R.id.iv_registered_pw_eye:
                if (mEtRegisteredPw.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtRegisteredPw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvRegisteredPwEye.setImageResource(R.drawable.openeye);
                } else {
                    mEtRegisteredPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvRegisteredPwEye.setImageResource(R.drawable.closeeye);
                }
                String pwd = mEtRegisteredPw.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    mEtRegisteredPw.setSelection(pwd.length());
                break;

            case R.id.iv_registered_pw_eye2:
                if (mEtRegisteredPw2.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtRegisteredPw2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvRegisteredPwEye2.setImageResource(R.drawable.openeye);
                } else {
                    mEtRegisteredPw2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvRegisteredPwEye2.setImageResource(R.drawable.closeeye);
                }
                String pwd2 = mEtRegisteredPw2.getText().toString();
                if (!TextUtils.isEmpty(pwd2))
                    mEtRegisteredPw2.setSelection(pwd2.length());
                break;
            case R.id.tv_registered_wancheng:
                break;
        }
    }
}
