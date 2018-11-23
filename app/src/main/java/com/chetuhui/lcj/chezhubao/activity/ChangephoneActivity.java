package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.model.ChangePhoneBean;
import com.chetuhui.lcj.chezhubao.model.LoginBean;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;

public class ChangephoneActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAccount;
    /**
     * 现有手机号:
     */
    private TextView mTvChangephXysjh;
    /**
     * 输入验证码
     */
    private EditText mEtChangephYzm;
    /**
     * 获取验证码
     */
    private SuperTextView mTvChangephHqyzm;
    /**
     * 验证
     */
    private SuperTextView mTvChangephYz;
    private String phone;
   private MyHandler mMyHandler=new MyHandler(ChangephoneActivity.this);
     private MyCountDownTimer mCountDownTimer;
     private  boolean isonclick=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephone);
        phone= SPTool.getString(ChangephoneActivity.this,"phone");
        Log.d("ChangephoneActivity", phone);
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

        mTvChangephXysjh = (TextView) findViewById(R.id.tv_changeph_xysjh);
        mTvChangephXysjh.setOnClickListener(this);
        mEtChangephYzm = (EditText) findViewById(R.id.et_changeph_yzm);
        mEtChangephYzm.setOnClickListener(this);
        mTvChangephHqyzm = (SuperTextView) findViewById(R.id.tv_changeph_hqyzm);
        mTvChangephHqyzm.setOnClickListener(this);
        mTvChangephYz = (SuperTextView) findViewById(R.id.tv_changeph_yz);
        mTvChangephYz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {

            Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_changeph_xysjh:
                break;
            case R.id.et_changeph_yzm:
                break;
            case R.id.tv_changeph_hqyzm:

                if (isonclick){
                    getSmsCode(phone);

                }

                break;
            case R.id.tv_changeph_yz:
                if (!DataTool.isNullString(mEtChangephYzm.getText().toString())){
                    getN_updatePhoneFirst(phone,mEtChangephYzm.getText().toString());

                }else {
                    BaseToast.error("验证码不能为空");
                }

                break;
        }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
            BaseToast.normal("点得太快了");
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
                                BaseToast.success(msg);
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
    private void getN_updatePhoneFirst(String phone ,String code) {
        String s_token = SPTool.getString(ChangephoneActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_updatePhoneFirst)
                .tag(this)
                .headers("token",s_token)
                .params("phone",phone)
                .params("code",code)

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
                                ChangePhoneBean changePhoneBean= new Gson().fromJson(data, ChangePhoneBean.class);


                                Intent intent=new Intent(ChangephoneActivity.this,ReplaceActivity.class);
                                intent.putExtra("phoneToken",changePhoneBean.getData().getPhoneToken());
                                intent.putExtra("userCode",changePhoneBean.getData().getUserCode());

                                startActivity(intent);

                            }
                            else if (code==1004){
                                ActivityTool.finishAllActivity();
                                startActivity(new Intent(ChangephoneActivity.this,LoginActivity.class));
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
            private WeakReference<ChangephoneActivity> activityWeakReference;

            public MyHandler(ChangephoneActivity activity) {
                activityWeakReference = new WeakReference<ChangephoneActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                ChangephoneActivity activity = activityWeakReference.get();
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
            mTvChangephHqyzm.setText("重新获取验证码");
            mMyHandler.post(new Runnable() {
                @Override
                public void run() {
                    isonclick=true;

//
                }
            });

        }

        public void onTick(long millisUntilFinished) {
            mTvChangephHqyzm.setText(millisUntilFinished / 1000 + "s ");
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
