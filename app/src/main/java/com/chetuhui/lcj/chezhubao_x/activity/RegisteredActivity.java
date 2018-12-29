package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.LoginBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.AppTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Response;


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
    int name= 0;
    String access_token="",openId="";
    private MyHandler mMyHandler=new MyHandler(RegisteredActivity.this);
    private MyCountDownTimer mCountDownTimer;
    private  boolean isonclick=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        name=  getIntent().getIntExtra("name",0);
        openId=getIntent().getStringExtra("openId");
        access_token=getIntent().getStringExtra("access_token");


        initView();
        initEvent();
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
        if (name==1){
            mTvRegisteredTitile.setText("绑定手机号");

        }else if (name==2){
            mTvRegisteredTitile.setText("忘记密码");
        }
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
//                if (!DataTool.isNullString(s) && mCleanPassword.getVisibility() == View.GONE) {
//                    mCleanPassword.setVisibility(View.VISIBLE);
//                } else if (DataTool.isNullString(s)) {
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
//                if (!DataTool.isNullString(s) && mCleanPassword.getVisibility() == View.GONE) {
//                    mCleanPassword.setVisibility(View.VISIBLE);
//                } else if (DataTool.isNullString(s)) {
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
                finish();
                break;

            case R.id.tv_registered_code:
                if (isonclick){
                    if (!DataTool.isNullString(mEtRegisteredPh.getText().toString())){
                        getSmsCode(mEtRegisteredPh.getText().toString());
                    }else {
                        BaseToast.error("请输入电话号码");
                    }
                }


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
                if (!DataTool.isNullString(pwd))
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
                if (!DataTool.isNullString(pwd2))
                    mEtRegisteredPw2.setSelection(pwd2.length());
                break;
            case R.id.tv_registered_wancheng:

                    if (DataTool.isNullString(mEtRegisteredPh.getText().toString())||DataTool.isNullString(mEtRegisteredPw.getText().toString())
                            ||DataTool.isNullString(mEtRegisteredCode.getText().toString())){
                        BaseToast.error("输入有误，请检查");
                    } else {
                        String pw1=mEtRegisteredPw.getText().toString();
                        String pw2=mEtRegisteredPw2.getText().toString();
                        if (!pw1.equals(pw2)){
                            BaseToast.error("两次密码不一致，请重新输入");
                        }else {
                            if(name==1){
                                getRegistered(mEtRegisteredPh.getText().toString(),mEtRegisteredPw.getText().toString(),mEtRegisteredPw2.getText().toString(),mEtRegisteredCode.getText().toString());

                            }else if (name==2)   {
                                getResetPasswordSecond(mEtRegisteredPh.getText().toString(),mEtRegisteredPw.getText().toString(),mEtRegisteredPw2.getText().toString(),mEtRegisteredCode.getText().toString());

                            }else if (name==3){
                                N_updateOpenid(mEtRegisteredPh.getText().toString(),mEtRegisteredPw.getText().toString(),mEtRegisteredPw2.getText().toString(),mEtRegisteredCode.getText().toString());

                            }



                        }

                    }





                break;
        }
    }
    private void getSmsCode(String phone) {

        OkGo.<String>get(NetData.N_SMSCODE)
                .tag(this)
                .params("phone",phone)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                String data = response.body();//这个就是返回来的结果
                Log.d("RegisteredActivity", data);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(data);
                    String msg = jsonObject.getString("msg");
                    int code=jsonObject.getInt("code");
                    if (code==0){
                        //BaseToast.success(msg);
                        mCountDownTimer = new MyCountDownTimer(60000, 1000);
                        mCountDownTimer.start();
                        isonclick=false;
                    }

                    BaseToast.info(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onError(com.lzy.okgo.model.Response<String> response) {
                super.onError(response);
            }
        });
    }


    private void getRegistered(String phone ,String pw1,String pw2,String code) {

        OkGo.<String>post(NetData.N_registered)
                .tag(this)
                .params("phone",phone)
                .params("code",code)
                .params("password",pw1)
                .params("passwordAgain",pw2)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                BaseToast.success("注册成功，请登录");
                                finish();
                            }
                            else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(RegisteredActivity.this,"token");
                                startActivity(new Intent(RegisteredActivity.this,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
                                BaseToast.success(msg);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }
    private void getResetPasswordSecond(String phone ,String pw1,String pw2,String code) {

        OkGo.<String>post(NetData.N_resetPasswordSecond)
                .tag(this)
                .params("phone",phone)
                .params("code",code)
                .params("newPassword",pw1)
                .params("passwordAgain",pw2)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                BaseToast.success("修改成功请重新登录");
                                finish();
                            }else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(RegisteredActivity.this,"token");
                                startActivity(new Intent(RegisteredActivity.this,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
                                BaseToast.success(msg);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }
    private void N_updateOpenid(String phone ,String pw1,String pw2,String code) {

        OkGo.<String>post(NetData.N_updateOpenid)
                .tag(this)
                .params("phone",phone)
                .params("code",code)
                .params("newPassword",pw1)
                .params("passwordAgain",pw2)
                .params("openId",openId)
                .params("access_token",access_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                LoginBean loginBean= new Gson().fromJson(data, LoginBean.class);
                                String token= loginBean.getData().getToken();
                                Log.d("LoginActivity", token);
                                SPTool.putString(RegisteredActivity.this,"token",token);
                                SPTool.putString(RegisteredActivity.this,"phone",loginBean.getData().getUser().getPhone());
                                SPTool.putString(RegisteredActivity.this,"name",loginBean.getData().getUser().getNickName());
                                SPTool.putString(RegisteredActivity.this,"user_code",loginBean.getData().getUser().getUserCode());
                                ActivityTool.finishAllActivity();
                                startActivity(new Intent(RegisteredActivity.this,HomeActivity.class));


                            }else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(RegisteredActivity.this,"token");
                                startActivity(new Intent(RegisteredActivity.this,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
                                BaseToast.success(msg);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }


    private static class MyHandler extends Handler {
            private WeakReference<RegisteredActivity> activityWeakReference;

            public MyHandler(RegisteredActivity activity) {
                activityWeakReference = new WeakReference<RegisteredActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                RegisteredActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }
    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以「 毫秒 」为单位倒计时的总数
         *                          例如 millisInFuture = 1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick()
         *                          例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
         */

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        public void onFinish() {
            mTvRegisteredCode.setText("重新获取验证码");
            mMyHandler.post(new Runnable() {
                @Override
                public void run() {
                    isonclick=true;


//
                }
            });

        }

        public void onTick(long millisUntilFinished) {
            mTvRegisteredCode.setText(millisUntilFinished / 1000 + "s ");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

}
