package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AccountFormulaBean;
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
import static com.chetuhui.lcj.chezhubao_x.tool.DataTool.getAmountValue;

public class FeedbackActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarFb;
    /**
     * 描述你遇到的问题，帮助我们为你提供优质的服务
     */
    private EditText mEtFbCon;
    /**
     * 请输入手机号/邮箱
     */
    private EditText mEtFbLxfs;
    /**
     * 提交
     */
    private SuperTextView mTvFbTijiao;
    private MyHandler mHandler=new MyHandler(FeedbackActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<FeedbackActivity> activityWeakReference;

            public MyHandler(FeedbackActivity activity) {
                activityWeakReference = new WeakReference<FeedbackActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                FeedbackActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    private void initView() {
        mTitlebarFb = (CommonTitleBar) findViewById(R.id.titlebar_fb);
        mTitlebarFb.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mEtFbCon = (EditText) findViewById(R.id.et_fb_con);
        mEtFbLxfs = (EditText) findViewById(R.id.et_fb_lxfs);
        mTvFbTijiao = (SuperTextView) findViewById(R.id.tv_fb_tijiao);
        mTvFbTijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_fb_tijiao:
                if (!DataTool.isNullString(mEtFbCon.getText().toString())&&!DataTool.isNullString(mEtFbLxfs.getText().toString())){
                    getN_addOpinion(mEtFbCon.getText().toString(),mEtFbLxfs.getText().toString());
                }else {
                    BaseToast.error("输入不能为空，请检查");
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


    private void getN_addOpinion(String con,String ph) {
        String s_token = SPTool.getString(FeedbackActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_addOpinion)
                .tag(this)
                .headers("token",s_token)
                .params("content",""+con)
                .params("phone",""+ph)

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
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mEtFbCon.setText("");
                                        mEtFbLxfs.setText("");
                                    }
                                });





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(FeedbackActivity.this,"token");
                                startActivity(new Intent(FeedbackActivity.this,LoginActivity.class));
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


}
