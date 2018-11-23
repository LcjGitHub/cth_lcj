package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.model.AdverBean;
import com.chetuhui.lcj.chezhubao.model.LoginBean;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class AdvertiseActivity extends ActivityBase {

    private TextView mStartSkipCountDown;
    private ImageView mImageView;
    private MyCountDownTimer mCountDownTimer;
    private  AdverBean adverBean;
   MyHandler myHandler = new MyHandler(AdvertiseActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        mStartSkipCountDown = (TextView) findViewById(R.id.start_skip_count_down);
        mImageView=findViewById(R.id.iv_ad_);
        mImageView.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
//                getN_saveHitCount(String.valueOf(adverBean.getData().getId()));

            }
        });
        mStartSkipCountDown.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                mCountDownTimer.cancel();
                String token= SPTool.getString(AdvertiseActivity.this,"token");
                Log.d("Main2Activity", token);
                if (token.equals("")){
                    startActivity(new Intent(AdvertiseActivity.this, LoginActivity.class));
                    finish();
                }else {
//                    startActivity(new Intent(AdvertiseActivity.this, CityActivity.class));
//                    finish();
                }

            }
        } );

        mStartSkipCountDown.setText("3s 跳过");
        //创建倒计时类
        mCountDownTimer = new MyCountDownTimer(4000, 1000);
        mCountDownTimer.start();
        getN_findAdvertisement();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCountDownTimer!=null){
            mCountDownTimer.cancel();
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
            mStartSkipCountDown.setText("0s 跳过");
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    String token= SPTool.getString(AdvertiseActivity.this,"token");
                    Log.d("Main2Activity", token);
                    if (token.equals("")){
                        startActivity(new Intent(AdvertiseActivity.this, LoginActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(AdvertiseActivity.this, HomeActivity.class));
                        finish();
                    }
//                    Intent intent = new Intent();
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    if (Build.VERSION.SDK_INT >= 9) {
//                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//                        intent.setData(Uri.fromParts("package", "com.example.scwlyd.cth_wycgg", null));
//                    } else if (Build.VERSION.SDK_INT <= 8) {
//                        intent.setAction(Intent.ACTION_VIEW);
//                        intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
//                        intent.putExtra("com.android.settings.ApplicationPkgName", "com.example.scwlyd.cth_wycgg");
//                    }

//                    startActivity(intent);
                }
            });

        }

        public void onTick(long millisUntilFinished) {
            mStartSkipCountDown.setText(millisUntilFinished / 1000 + "s 跳过");
        }

    }

    private static class MyHandler extends Handler {
        private WeakReference<AdvertiseActivity> activityWeakReference;

        public MyHandler(AdvertiseActivity activity) {
            activityWeakReference = new WeakReference<AdvertiseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AdvertiseActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }
    private void getN_findAdvertisement() {

        OkGo.<String>get(NetData.N_findAdvertisement)
                .tag(this)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        if (DataTool.isNullString(data)){
                            return;
                        }else {
                            try {
                                jsonObject = new JSONObject(data);
                                String msg = jsonObject.getString("msg");
                                BaseToast.success(msg);
                                adverBean=new Gson().fromJson(data, AdverBean.class);
                                Glide.with(AdvertiseActivity.this).load(adverBean.getData().getImgUrl()).into(mImageView);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }



                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }
    private void getN_saveHitCount(String s) {

        OkGo.<String>get(NetData.N_saveHitCount+s)
                .tag(this)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        if (DataTool.isNullString(data)){
                            return;
                        }else {
                            try {
                                jsonObject = new JSONObject(data);
                                String msg = jsonObject.getString("msg");
                                BaseToast.success(msg);
                                mCountDownTimer.cancel();
                                Uri uri = Uri.parse(adverBean.getData().getHttpUrl());
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }



                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }


}
