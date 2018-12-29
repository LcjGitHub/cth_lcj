package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.AppTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.IntentTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSure;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

public class AboutusActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarFb;
    /**
     * 版本号
     */
    private TextView mTvAuBbh;
    private RelativeLayout mRlAuXy;
    private RelativeLayout mRlAuZc;
    private  String con,title;
    private  MyHandler mHandler =new MyHandler(AboutusActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<AboutusActivity> activityWeakReference;

            public MyHandler(AboutusActivity activity) {
                activityWeakReference = new WeakReference<AboutusActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                AboutusActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        initView();

    }

    private void initView() {
        mTitlebarFb = (CommonTitleBar) findViewById(R.id.titlebar_au);
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

        mTvAuBbh = (TextView) findViewById(R.id.tv_au_bbh);
        mRlAuXy = (RelativeLayout) findViewById(R.id.rl_au_xy);
        mRlAuXy.setOnClickListener(this);
        mRlAuZc = (RelativeLayout) findViewById(R.id.rl_au_zc);
        mRlAuZc.setOnClickListener(this);

       mTvAuBbh.setText("版本号："+AppTool. getAppVersionName(AboutusActivity.this));
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_au_xy:
                getN_findClause("1");

                break;
            case R.id.rl_au_zc:
                getN_findClause("5");

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

    private void getN_findClause(String type) {
//        String s_token = SPTool.getString(AboutusActivity.this, "token");
//        Log.d("CityActivity", s_token);
//
//        if (DataTool.isNullString(s_token)) {
//            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
//1：法律声明 2：车助保服务协议 3：服务合作协议 4：关于我们 5：客服电话
        OkGo.<String>get(NetData.N_findClause+"?type="+type)
                .tag(this)
//                .headers("token",s_token)


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
                                String d=jsonObject.getString("data");
                                JSONObject jsonObject1=new JSONObject(d);
                              con=  jsonObject1.getString("content");
                                title=  jsonObject1.getString("name");
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showview(AboutusActivity.this,con,title);

                                    }
                                });



                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(AboutusActivity.this,"token");
                                startActivity(new Intent(AboutusActivity.this,LoginActivity.class));
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
    public  void showview(final Context mContext, String str,String title) {
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


}
