package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.fragment.MeFragment;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogEditSureCancel;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSureCancel;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class PersonalInformationActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarPi;
    /**  */
    private TextView mTvPiNc;
    private RelativeLayout mRlPiNc;
    /**
     * 未认证
     */
    private SuperTextView mTvPiSmxx;
    private RelativeLayout mRlPiSmxx;
    /**  */
    private TextView mTvPiBdsj;
    private RelativeLayout mRlPiBdsj;
    private String name,ph;
    private  DialogEditSureCancel mDialog;
    MyHandler myHandler = new MyHandler(PersonalInformationActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<PersonalInformationActivity> activityWeakReference;

            public MyHandler(PersonalInformationActivity activity) {
                activityWeakReference = new WeakReference<PersonalInformationActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                PersonalInformationActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        initView();
        getN_personalInformation();
    }

    private void initView() {
        mTitlebarPi = (CommonTitleBar) findViewById(R.id.titlebar_pi);
        mTitlebarPi.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mTvPiNc = (TextView) findViewById(R.id.tv_pi_nc);
        mRlPiNc = (RelativeLayout) findViewById(R.id.rl_pi_nc);
        mRlPiNc.setOnClickListener(this);
        mTvPiSmxx = (SuperTextView) findViewById(R.id.tv_pi_smxx);
        mRlPiSmxx = (RelativeLayout) findViewById(R.id.rl_pi_smxx);
        mRlPiSmxx.setOnClickListener(this);
        mTvPiBdsj = (TextView) findViewById(R.id.tv_pi_bdsj);
        mRlPiBdsj = (RelativeLayout) findViewById(R.id.rl_pi_bdsj);
        mRlPiBdsj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_pi_nc:
                showview();
                break;
            case R.id.rl_pi_smxx:
                break;
            case R.id.rl_pi_bdsj:
                break;
        }
    }
    private void getN_personalInformation() {
        String s_token = SPTool.getString(PersonalInformationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(PersonalInformationActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_personalInformation)
                .tag(this)
                .headers("token",s_token)


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
                                name=jsonObject1.getString("nickName");
                                ph=jsonObject1.getString("phone");
                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mTvPiNc.setText(name);
                                        mTvPiBdsj.setText(ph);
                                    }
                                });





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(PersonalInformationActivity.this,"token");
                                startActivity(new Intent(PersonalInformationActivity.this,LoginActivity.class));
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
    private void  showview(){
          mDialog = new DialogEditSureCancel(mContext, R.style.PushUpInDialogThem);
        mDialog.setTitle("修改昵称");

        mDialog.setCancelListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                mDialog.cancel();
            }
        });

        mDialog.setSureListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                if(!DataTool.isNullString(mDialog.getEditText().toString())){
                    getN_updatePersonalInformation(mDialog.getEditText().getText().toString());

                }else {
                    BaseToast.error("输入不能为空，请检查");
                }

            }
        });
        mDialog.setCancelable(false);
        mDialog.show();
    }
    private void getN_updatePersonalInformation(String name) {
        String s_token = SPTool.getString(PersonalInformationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(PersonalInformationActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_updatePersonalInformation)
                .tag(this)
                .headers("token",s_token)
                .params("nickname",""+name)

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

                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDialog.cancel();
                                        getN_personalInformation();
                                    }
                                });





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(PersonalInformationActivity.this,"token");
                                startActivity(new Intent(PersonalInformationActivity.this,LoginActivity.class));
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
