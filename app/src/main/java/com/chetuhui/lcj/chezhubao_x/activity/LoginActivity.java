package com.chetuhui.lcj.chezhubao_x.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.LoginBean;
import com.chetuhui.lcj.chezhubao_x.model.WeiXin;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.AnimationTool;
import com.chetuhui.lcj.chezhubao_x.tool.Constants;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.KeyboardTool;
import com.chetuhui.lcj.chezhubao_x.tool.PermissionsTool;
import com.chetuhui.lcj.chezhubao_x.tool.RegTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSure;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSureCancel;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

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

    private IWXAPI wxAPI;
    private TextView tvNickname, tvAge;
    public static final int IMAGE_SIZE = 32768;//微信分享图片大小限制
    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;
    private LinearLayout mLlLoginContent;
    private String con, title;
    private boolean iszc = false;
    private DialogSureCancel rxDialogSureCancel;
    private MyCountDownTimer mCountDownTimer;
    /**
     * 短信验证码登录
     */
    private TextView mTvLogDxdl;
    private TextView mTvLogDxdlXian;
    /**
     * 密码登录
     */
    private TextView mTvLogMmdl;
    private TextView mTvLogMmdlXian;
    /**  */
    private EditText mEtLoginMobileYzm;
    private ImageView mIvLoginCleanPhoneYzm;
    /**  */
    private EditText mEtLoginYzm;
    /**
     * 获取验证码
     */
    private TextView mTvLogYzm;
    private LinearLayout mLlYzm;
    private LinearLayout mLlMm;
    private boolean isonclick=true;
    private boolean isyzm=true;

    private static class MyHandler extends Handler {
        private WeakReference<LoginActivity> activityWeakReference;

        public MyHandler(LoginActivity activity) {
            activityWeakReference = new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LoginActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() {
            // ...你的操作

        }
    };


    private MyHandler mMyHandler = new MyHandler(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this);
        }
        EventBus.getDefault().register(this);//注册
        wxAPI = WXAPIFactory.createWXAPI(this, Constants.WECHAT_APPID, true);
        wxAPI.registerApp(Constants.WECHAT_APPID);
        initView();
        initEvent();
        PermissionsTool.
                with(mContext).
                addPermission(Manifest.permission.ACCESS_FINE_LOCATION).
                addPermission(Manifest.permission.ACCESS_COARSE_LOCATION).
                addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.CAMERA).
                addPermission(Manifest.permission.CALL_PHONE).
                addPermission(Manifest.permission.READ_PHONE_STATE).
                addPermission(Manifest.permission.RECORD_AUDIO).
                initPermission();

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
        mTvLogDxdl = (TextView) findViewById(R.id.tv_log_dxdl);
        mTvLogDxdlXian = (TextView) findViewById(R.id.tv_log_dxdl_xian);
        mTvLogMmdl = (TextView) findViewById(R.id.tv_log_mmdl);
        mTvLogMmdlXian = (TextView) findViewById(R.id.tv_log_mmdl_xian);
        mEtLoginMobileYzm = (EditText) findViewById(R.id.et_login_mobile_yzm);
        mIvLoginCleanPhoneYzm = (ImageView) findViewById(R.id.iv_login_clean_phone_yzm);
        mEtLoginYzm = (EditText) findViewById(R.id.et_login_yzm);
        mTvLogYzm = (TextView) findViewById(R.id.tv_log_yzm);
        mLlYzm = (LinearLayout) findViewById(R.id.ll_yzm);
        mLlMm = (LinearLayout) findViewById(R.id.ll_mm);
        mTvLogDxdlXian.setVisibility(View.VISIBLE);
        mLlYzm.setVisibility(View.VISIBLE);
        mTvLogDxdl.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                mTvLogDxdlXian.setVisibility(View.VISIBLE);
                mLlYzm.setVisibility(View.VISIBLE);
                mTvLogMmdlXian.setVisibility(View.GONE);
                mLlMm.setVisibility(View.GONE);
                isyzm=true;

            }
        });
        mTvLogMmdl.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                mTvLogDxdlXian.setVisibility(View.GONE);
                mLlYzm.setVisibility(View.GONE);
                mTvLogMmdlXian.setVisibility(View.VISIBLE);
                mLlMm.setVisibility(View.VISIBLE);
                isyzm=false;

            }
        });
        mTvLogYzm.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                if (isonclick){
                    if (!DataTool.isNullString(mEtLoginMobileYzm.getText().toString())){
                        if (RegTool.isMobile(mEtLoginMobileYzm.getText().toString())){
                            getSmsCode(mEtLoginMobileYzm.getText().toString());

                        }else {
                            BaseToast.error("请输入正确的手机号");
                        }
                    }else {
                        BaseToast.error("手机号不能空");
                    }
                }


            }
        });
        mIvLoginCleanPhoneYzm.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                mEtLoginMobileYzm.setText("");
            }
        });


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

//                            BaseToast.info(msg);
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
            mTvLogYzm.setText("重新获取验证码");
            mMyHandler.post(new Runnable() {
                @Override
                public void run() {
                    isonclick=true;

//
                }
            });

        }

        public void onTick(long millisUntilFinished) {
            mTvLogYzm.setText(millisUntilFinished / 1000 + "s ");
        }

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
                if (!DataTool.isNullString(s.toString()) && mIvLoginCleanPhone.getVisibility() == View.GONE) {
                    mIvLoginCleanPhone.setVisibility(View.VISIBLE);
                } else if (DataTool.isNullString(s.toString())) {
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
                if (!DataTool.isNullString(s.toString()) && mIvLoginCleanPassword.getVisibility() == View.GONE) {
                    mIvLoginCleanPassword.setVisibility(View.VISIBLE);
                } else if (DataTool.isNullString(s.toString())) {
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
        Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_login_logo:
                break;
            case R.id.iv_login_clean_phone:
                mEtLoginMobile.setText("");

                break;
            case R.id.iv_login_clean_password:
                mEtLoginPassword.setText("");

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
                if (!DataTool.isNullString(pwd)) {
                    mEtLoginPassword.setSelection(pwd.length());

                }

                break;
            case R.id.tv_login_denglu:
                KeyboardTool.hideSoftInput(mContext);
                if (isyzm){
                if (DataTool.isNullString(mEtLoginMobileYzm.getText().toString()) || DataTool.isNullString(mEtLoginYzm.getText().toString())) {
                    BaseToast.error("输入有误，请检查");

                } else {
                    if (RegTool.isMobile(mEtLoginMobileYzm.getText().toString())) {

                            getLoginyzm(mEtLoginMobileYzm.getText().toString(), mEtLoginYzm.getText().toString());


                    } else {
                        BaseToast.error("请输入正确的手机号");
                    }

                }
                }else {
                    if (DataTool.isNullString(mEtLoginMobile.getText().toString()) || DataTool.isNullString(mEtLoginPassword.getText().toString())) {
                        BaseToast.error("输入有误，请检查");

                    } else {
                        if (RegTool.isMobile(mEtLoginMobile.getText().toString())) {

                            getLogin(mEtLoginMobile.getText().toString(), mEtLoginPassword.getText().toString());


                        } else {
                            BaseToast.error("请输入正确的手机号");
                        }


                    }
                }

                break;
            case R.id.tv_login_regist:

                showdg(mContext, title, con);


                break;
            case R.id.tv_login_forget_password:
                intent = new Intent(this, RegisteredActivity.class);
                intent.putExtra("name", 2);
                break;
            case R.id.tv_login_weixin:
                loginToWeiXin();
                break;
            case R.id.tv_login_xieyi:
                iszc = false;
                getN_findClause("4");
                break;
            case R.id.tv_login_zhengce:
                iszc = false;
                getN_findClause("5");
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    public void showdg(Context context, String title_str, String con_str) {
        final BaseDialog rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_login, null);

        TextView cancel = view.findViewById(R.id.tv_cancel_login);
        TextView title = view.findViewById(R.id.tv_title_login);
        TextView con = view.findViewById(R.id.tv_content_login);
        TextView suer = view.findViewById(R.id.tv_sure_login);
        TextView xy = view.findViewById(R.id.tv_log_xy);
        TextView zc = view.findViewById(R.id.tv_log_zc);
//        title.setText(""+title_str);
//        con.setText(""+con_str);

        xy.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                getN_findClause("4");
            }
        });
        zc.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                getN_findClause("5");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });
        suer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtLoginMobile.setText("");
                mEtLoginPassword.setText("");
                Intent intent;
                intent = new Intent(mContext, RegisteredActivity.class);
                intent.putExtra("name", 1);
                startActivity(intent);
                rxDialog.cancel();
            }
        });


        rxDialog.setContentView(view);
        rxDialog.getLayoutParams();
        rxDialog.mLayoutParams.gravity = Gravity.CENTER;

        rxDialog.show();
        rxDialog.setFullScreen();
    }
    private void getLoginyzm(String phone, String pw) {

        OkGo.<String>post(NetData.N_loginByPhoneAndCode)
                .tag(this)
                .params("phone", phone)
                .params("code", pw)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);


                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code = jsonObject.getInt("code");
                            mMyHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        LoginBean loginBean = new Gson().fromJson(data, LoginBean.class);
                                        String token = loginBean.getData().getToken();
                                        Log.d("LoginActivity", token);
                                        SPTool.putString(LoginActivity.this, "token", token);
                                        SPTool.putString(LoginActivity.this, "phone", loginBean.getData().getUser().getPhone());
                                        SPTool.putString(LoginActivity.this, "name", loginBean.getData().getUser().getNickName());
                                        SPTool.putString(LoginActivity.this, "user_code", loginBean.getData().getUser().getUserCode());

                                        //BaseToast.success(msg);
                                        ActivityTool.finishAllActivity();
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(LoginActivity.this, "token");
                                        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    } else {
                                        BaseToast.success(msg);

                                    }
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void getLogin(String phone, String pw) {

        OkGo.<String>post(NetData.N_login)
                .tag(this)
                .params("phone", phone)
                .params("password", pw)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);


                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code = jsonObject.getInt("code");
                            mMyHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        LoginBean loginBean = new Gson().fromJson(data, LoginBean.class);
                                        String token = loginBean.getData().getToken();
                                        Log.d("LoginActivity", token);
                                        SPTool.putString(LoginActivity.this, "token", token);
                                        SPTool.putString(LoginActivity.this, "phone", loginBean.getData().getUser().getPhone());
                                        SPTool.putString(LoginActivity.this, "name", loginBean.getData().getUser().getNickName());
                                        SPTool.putString(LoginActivity.this, "user_code", loginBean.getData().getUser().getUserCode());

                                        //BaseToast.success(msg);
                                        ActivityTool.finishAllActivity();
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(LoginActivity.this, "token");
                                        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    } else {
                                        BaseToast.success(msg);

                                    }
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void loginToWeiXin() {


        if (wxAPI != null && wxAPI.isWXAppInstalled()) {
            login();

        } else {
            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();

        }
    }

    private void getN_loginByWx(String code) {

        OkGo.<String>get(NetData.N_loginByWx + code)
                .tag(this)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                LoginBean loginBean = new Gson().fromJson(data, LoginBean.class);
                                String token = loginBean.getData().getToken();
                                Log.d("LoginActivity", token);
                                SPTool.putString(LoginActivity.this, "token", token);
                                SPTool.putString(LoginActivity.this, "phone", loginBean.getData().getUser().getPhone());
                                SPTool.putString(LoginActivity.this, "name", loginBean.getData().getUser().getNickName());
                                SPTool.putString(LoginActivity.this, "user_code", loginBean.getData().getUser().getUserCode());
                                //BaseToast.success(msg);
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();

                            } else if (code == 1) {
                                JSONObject object = new JSONObject(String.valueOf(jsonObject.getJSONObject("data")));
                                Log.d("LoginActivity", object.getString("openId"));
                                Log.d("LoginActivity", object.getString("access_token"));

                                Intent intent = null;
                                intent = new Intent(LoginActivity.this, RegisteredActivity.class);
                                intent.putExtra("name", 3);
                                intent.putExtra("openId", object.getString("openId"));
                                intent.putExtra("access_token", object.getString("access_token"));

                                startActivity(intent);
                                BaseToast.success("微信登录成功后需要你绑定手机号");


                            }

                            BaseToast.info(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    /**
     * 这里用到的了EventBus框架
     *
     * @param weiXin
     */
    @Subscribe
    public void onEventMainThread(WeiXin weiXin) {
        Log.i("ansen", "收到eventbus请求 type:" + weiXin.getType());
        if (weiXin.getType() == 1) {//登录
            Log.d("LoginActivity", "" + weiXin.getCode());

            getN_loginByWx("" + weiXin.getCode());


        } else if (weiXin.getType() == 2) {//分享
            switch (weiXin.getErrCode()) {
                case BaseResp.ErrCode.ERR_OK:
                    Log.i("ansen", "微信分享成功.....");
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL://分享取消
                    Log.i("ansen", "微信分享取消.....");
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED://分享被拒绝
                    Log.i("ansen", "微信分享被拒绝.....");
                    break;
            }
        } else if (weiXin.getType() == 3) {//微信充值
            if (weiXin.getErrCode() == BaseResp.ErrCode.ERR_OK) {//成功
                Log.i("ansen", "微信充值成功.....");
            }
        }
    }

    /**
     * 微信登陆(三个步骤)
     * 1.微信授权登陆
     * 2.根据授权登陆code 获取该用户token
     * 3.根据token获取用户资料
     */
    public void login() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = String.valueOf(System.currentTimeMillis());
        wxAPI.sendReq(req);
    }

    private void getN_findClause(String type) {
//        String s_token = SPTool.getString(JoinActivity.this, "token");
//        Log.d("CityActivity", s_token);
//
//        if (DataTool.isNullString(s_token)) {
//            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
//1：法律声明 2：车助保服务协议 3：服务合作协议 4：关于我们 5：客服电话
        OkGo.<String>get(NetData.N_findClause + "?type=" + type)
                .tag(this)
//                .headers("token",s_token)


                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                //BaseToast.success(msg);
                                String d = jsonObject.getString("data");
                                JSONObject jsonObject1 = new JSONObject(d);
                                con = jsonObject1.getString("content");
                                title = jsonObject1.getString("name");
                                mMyHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (iszc) {


                                        } else {
                                            showview(LoginActivity.this, con, title);
                                        }


                                    }
                                });


                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(LoginActivity.this, "token");
                                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            } else {
                                BaseToast.success(msg);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    public void showview(final Context mContext, String str, String title) {
        final DialogSure rxDialogSure = new DialogSure(mContext);
        rxDialogSure.getLogoView().setVisibility(View.GONE);

        rxDialogSure.setTitle(title);
        rxDialogSure.setContent(str);
        rxDialogSure.getContentView().setTextSize(20);
        rxDialogSure.getContentView().setTextColor(ContextCompat.getColor(mContext, R.color._3));
        rxDialogSure.getContentView().setGravity(Gravity.CENTER);
        rxDialogSure.setCanceledOnTouchOutside(false);
        rxDialogSure.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSure.cancel();
            }
        });
        rxDialogSure.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
}
