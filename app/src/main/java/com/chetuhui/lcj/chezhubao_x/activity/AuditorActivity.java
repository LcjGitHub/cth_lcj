package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.FananAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.QuyuAdapter;
import com.chetuhui.lcj.chezhubao_x.model.FananBean;
import com.chetuhui.lcj.chezhubao_x.model.QuyuBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSure;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

public class AuditorActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAuditor;
    /**
     * 请填写联系人姓名
     */
    private EditText mEtAuditorName;
    /**
     * 请输入
     */
    private EditText mEtAuditorPh;
    /**  */
    private EditText mEtAuditorXxdz;
    /**
     * 下一步
     */
    private SuperTextView mTvAuditorXiayibu;
    /**
     * 请选择
     */
    private boolean isonclick=false;
    private TextView mTvAuditorQxz;
    private TextView tv_auditor_city;
    private RecyclerView recyclerView;
    private List<QuyuBean.DataBean> mBeanList =new ArrayList<>();
    private QuyuAdapter mAdapter;
    private  BaseDialog rxDialog;
    private LinearLayoutManager mLayoutManager;
    private int cityid = 510100;
    private String tt="审核流程",con="1、在平台收到您提交的资料后" +
            "审核员或维修站会接收您提交的信息，内容包括:" +
            "行驶证主页照片、车牌号、所有人、品牌型号、车辆识别代号、发动机号\n" +
            "2、接收您的资料后，审核员或维修站会主动联系您确定信息，内容包含：" +
            "审核时间、审核地点、审核人\n" +
            "3、当您与平台或服务站审核员见面后，审核员将会收集您的车辆信息，内容包括：" +
            "审核表照片（审核员填写）、您的车辆车头及车尾45°照片、车身伤痕情况照片\n" +
            "4、审核员会根据收集的信息判定您的车辆是否通过审核：" +
            "（1）若您的信息全部通过审核，您的互助方案即时生效\n" +
            "（2）若您的信息未通过审核，您的互助方案审核失败，系统自动退款并收取（互助单总金额）10%的手续费；";
    private  MyHandler mHandler=new MyHandler(AuditorActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<AuditorActivity> activityWeakReference;

            public MyHandler(AuditorActivity activity) {
                activityWeakReference = new WeakReference<AuditorActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                AuditorActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor);
        cityid=SPTool.getInt(AuditorActivity.this,"city_id");
        initView();

    }

    private void initView() {
        mTitlebarAuditor = (CommonTitleBar) findViewById(R.id.titlebar_auditor);
        mTitlebarAuditor.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }else if (action == CommonTitleBar.ACTION_RIGHT_TEXT){
                    showview(mContext,con,tt);
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

        mEtAuditorName = (EditText) findViewById(R.id.et_auditor_name);
        mEtAuditorName.setOnClickListener(this);
        mEtAuditorPh = (EditText) findViewById(R.id.et_auditor_ph);
        mEtAuditorPh.setOnClickListener(this);
        mEtAuditorXxdz = (EditText) findViewById(R.id.et_auditor_xxdz);
        mEtAuditorXxdz.setOnClickListener(this);
        mTvAuditorXiayibu = (SuperTextView) findViewById(R.id.tv_auditor_xiayibu);
        mTvAuditorXiayibu.setOnClickListener(this);
        mTvAuditorQxz = (TextView) findViewById(R.id.tv_auditor_qxz);
        tv_auditor_city = (TextView) findViewById(R.id.tv_auditor_city);
        mTvAuditorQxz.setOnClickListener(this);

        tv_auditor_city.setText(""+ SPTool.getString(mContext,"city_name"));
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
            switch (v.getId()) {
                default:
                    break;
                case R.id.et_auditor_name:
                    break;
                    case R.id.tv_auditor_qxz:
                        getN_queryNextlevel();
                    break;
                case R.id.et_auditor_ph:
                    break;
                case R.id.et_auditor_xxdz:
                    break;
                case R.id.tv_auditor_xiayibu:
                    if (DataTool.isNullString(mEtAuditorName.getText().toString()) || DataTool.isNullString(mEtAuditorPh.getText().toString()) || DataTool.isNullString(mEtAuditorXxdz.getText().toString())) {
                        BaseToast.error("填写不能为空");
                    } else {
                        if (isonclick){
                            SPTool.putString(AuditorActivity.this, "auditor_name", mEtAuditorName.getText().toString());
                            SPTool.putString(AuditorActivity.this, "auditor_ph", mEtAuditorPh.getText().toString());
                            SPTool.putString(AuditorActivity.this, "auditor_xxdz", mEtAuditorXxdz.getText().toString());
                            intent = new Intent(AuditorActivity.this, ConfirmActivity.class);
                        }else {
                            BaseToast.error("选择区域");
                        }

                    }

                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }

        } else {
            //BaseToast.normal("点得太快了");
            return;
        }
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

    private void getN_queryNextlevel() {

        String s_token = SPTool.getString(AuditorActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(AuditorActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_queryNextlevel+"?cityId="+cityid)
                .tag(this)
                .headers("token", s_token)
                .params("","")

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
                           mHandler.post(new Runnable() {
                               @Override
                               public void run() {
                                   if (code == 0) {
                                       mBeanList.clear();
                                       QuyuBean bean= new Gson().fromJson(data, QuyuBean.class);
                                       mBeanList=bean.getData();
                                       showquyu(AuditorActivity.this);

                                       //BaseToast.success(msg);

                                   } else if (code == 1004) {
                                       ActivityTool.finishAllActivity();
                                       SPTool.remove(AuditorActivity.this, "token");
                                       startActivity(new Intent(AuditorActivity.this, LoginActivity.class));
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
    public void showquyu(Context context) {
        rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quyu_show, null);
        recyclerView = view.findViewById(R.id.rv_dg_quyu);
        initRecylerView();
        ImageView cancel = view.findViewById(R.id.iv_dg_quyu);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });


        rxDialog.setContentView(view);
        rxDialog.getLayoutParams();
        rxDialog.mLayoutParams.gravity = Gravity.BOTTOM;

        rxDialog.show();
        rxDialog.setFullScreen();
    }
    private void initRecylerView() {

        mAdapter = new QuyuAdapter(R.layout.item_quyu, mBeanList);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        //添加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                rxDialog.cancel();
                mTvAuditorQxz.setText(mBeanList.get(position).getName());
                isonclick=true;
                SPTool.putInt(AuditorActivity.this,"qy_id",mBeanList.get(position).getId());


            }
        });



    }


}
