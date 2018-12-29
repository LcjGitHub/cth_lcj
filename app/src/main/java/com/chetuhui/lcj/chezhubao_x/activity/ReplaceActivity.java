package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.ChangePhoneBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

public class ReplaceActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarReplace;
    /**
     * 手机号
     */
    private EditText mEtReplaceSjh;
    /**
     * 验证码
     */
    private EditText mEtReplaceYzm;
    /**
     * 获取验证码
     */
    private TextView mTvReplaceHqyzm;
    /**
     * 更换手机号
     */
    private SuperTextView mTvReplaceGhsjh;
    private  String phoneToken;
    private String userCode;
    private MyHandler mMyHandler=new MyHandler(ReplaceActivity.this);
    private MyCountDownTimer mCountDownTimer;
    private  boolean isonclick=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);
        phoneToken=getIntent().getStringExtra("phoneToken");
        userCode=getIntent().getStringExtra("userCode");
        initView();
    }

    private void initView() {
        mTitlebarReplace = (CommonTitleBar) findViewById(R.id.titlebar_replace);
        mTitlebarReplace.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mEtReplaceSjh = (EditText) findViewById(R.id.et_replace_sjh);
        mEtReplaceSjh.setOnClickListener(this);
        mEtReplaceYzm = (EditText) findViewById(R.id.et_replace_yzm);
        mEtReplaceYzm.setOnClickListener(this);
        mTvReplaceHqyzm = (TextView) findViewById(R.id.tv_replace_hqyzm);
        mTvReplaceHqyzm.setOnClickListener(this);
        mTvReplaceGhsjh = (SuperTextView) findViewById(R.id.tv_replace_ghsjh);
        mTvReplaceGhsjh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {

            Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.et_replace_sjh:
                break;
            case R.id.et_replace_yzm:
                break;
            case R.id.tv_replace_hqyzm:
                if (isonclick){
                    if (!DataTool.isNullString(mEtReplaceSjh.getText().toString())){
                        getSmsCode(mEtReplaceSjh.getText().toString());
                    }else {
                        BaseToast.error("手机号不能空");
                    }
                }


                break;
            case R.id.tv_replace_ghsjh:
                if (!DataTool.isNullString(mEtReplaceSjh.getText().toString())&&!DataTool.isNullString(mEtReplaceYzm.getText().toString())){
                    getN_updatePhoneSecond(mEtReplaceSjh.getText().toString(),mEtReplaceYzm.getText().toString(),userCode,phoneToken);
                }else {
                    BaseToast.error("请检查手机号或者验证码");
                }

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
    private void getN_updatePhoneSecond(String phone ,String code,String userCode,String phoneToken) {
        String s_token = SPTool.getString(ReplaceActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_updatePhoneSecond)
                .tag(this)
                .headers("token",s_token)
                .params("phone",phone)
                .params("code",code)
                .params("userCode",userCode)
                .params("phoneToken",phoneToken)

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

                                ActivityTool.finishAllActivity();
                                SPTool.remove(ReplaceActivity.this,"token");
                                startActivity(new Intent(ReplaceActivity.this,LoginActivity.class));
                                BaseToast.success("更换成功，请重新登录");

                            }
                            else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(ReplaceActivity.this,"token");
                                startActivity(new Intent(ReplaceActivity.this,LoginActivity.class));
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
            mTvReplaceHqyzm.setText("重新获取验证码");
            mMyHandler.post(new Runnable() {
                @Override
                public void run() {
                    isonclick=true;

//
                }
            });

        }

        public void onTick(long millisUntilFinished) {
            mTvReplaceHqyzm.setText(millisUntilFinished / 1000 + "s ");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
    private static class MyHandler extends Handler {
            private WeakReference<ReplaceActivity> activityWeakReference;

            public MyHandler(ReplaceActivity activity) {
                activityWeakReference = new WeakReference<ReplaceActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                ReplaceActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

}
