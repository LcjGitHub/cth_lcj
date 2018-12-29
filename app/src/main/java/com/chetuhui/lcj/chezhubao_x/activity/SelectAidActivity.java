package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.AuditStationAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.SelectAidAdapter;
import com.chetuhui.lcj.chezhubao_x.model.AuditStationBean;
import com.chetuhui.lcj.chezhubao_x.model.SelectAidBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SelectAidActivity extends ActivityBase  {

    private CommonTitleBar mTitlebarSa;
    private RecyclerView mRvSa;

    /**
     * 确认选择
     */
    private TextView mTvSaQrxz;

    private LinearLayoutManager mLayoutManager;
    private List<SelectAidBean.DataBean> mBeanList=new ArrayList<>();
    private SelectAidAdapter mAdapter;
    private  String num;
    private boolean isonclick=false;
    private MyHandler mHandler=new MyHandler(SelectAidActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<SelectAidActivity> activityWeakReference;

            public MyHandler(SelectAidActivity activity) {
                activityWeakReference = new WeakReference<SelectAidActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                SelectAidActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_aid);
        num=SPTool.getString(SelectAidActivity.this,"ih_carnum");
        initView();
        getN_chooseBill(num);
    }

    private void initView() {
        mTitlebarSa = (CommonTitleBar) findViewById(R.id.titlebar_sa);
        mTitlebarSa.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mRvSa = (RecyclerView) findViewById(R.id.rv_sa);


        mTvSaQrxz = (TextView) findViewById(R.id.tv_sa_qrxz);
        mTvSaQrxz.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                if (isonclick){
                    startActivity(new Intent(SelectAidActivity.this,BasicInformationActivity.class));

                }
            }
        });
    }


    private void getN_chooseBill(String num) {
        String s_token = SPTool.getString(SelectAidActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_chooseBill+"?carNum="+num)
                .tag(this)
                .headers("token",s_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code=jsonObject.getInt("code");
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code==0){
                                        //BaseToast.success(msg);
                                        mBeanList.clear();
                                        SelectAidBean bean= new Gson().fromJson(data, SelectAidBean.class);
                                        mBeanList=bean.getData();
                                        initRecylerView();





                                    } else if (code==1004){
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(SelectAidActivity.this,"token");
                                        startActivity(new Intent(SelectAidActivity.this,LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    }else {
                                        BaseToast.success(msg);

                                    }

                                }
                            });

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
    private void initRecylerView() {

        mAdapter = new SelectAidAdapter(this,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvSa.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvSa.setLayoutManager(mLayoutManager);
        mRvSa.setAdapter(mAdapter);
        mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
        mAdapter.setOnItemClickListener(new SelectAidAdapter.setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int pos) {
                mAdapter.unSelectAll();
                mAdapter.unSelect(pos);
                isonclick=true;
                SPTool.putString(SelectAidActivity.this,"sa_code",mBeanList.get(pos).getBillCode());


            }
        });

    }

}
