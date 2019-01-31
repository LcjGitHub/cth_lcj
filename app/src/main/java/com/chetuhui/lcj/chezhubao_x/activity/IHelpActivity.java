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
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.AmcAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.IhelpAdapter;
import com.chetuhui.lcj.chezhubao_x.model.AmcBean;
import com.chetuhui.lcj.chezhubao_x.model.IhelpBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
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
import java.util.ArrayList;
import java.util.List;

public class IHelpActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarIhelp;
    private ImageView iv_amc_sqt;
    /**
     * 请输入车牌号搜索
     */
    private EditText mEtIhelpSousuo;

    private RecyclerView mRvIhelp;
    private SwipeRefreshLayout mSlIhelp;
    private TextView tv_sswg;
    private boolean isss=false;
    private LinearLayoutManager mLayoutManager;
    private List<IhelpBean.DataBean> mBeanList=new ArrayList<>();
    private IhelpAdapter mAdapter;
    private MyHandler mHandler=new MyHandler(IHelpActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<IHelpActivity> activityWeakReference;

            public MyHandler(IHelpActivity activity) {
                activityWeakReference = new WeakReference<IHelpActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                IHelpActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ihelp);
        initView();
        getN_findMutualCar("");
        initPullRefresh();
    }

    private void initView() {
        iv_amc_sqt = (ImageView) findViewById(R.id.iv_amc_sqt);
        mTitlebarIhelp = (CommonTitleBar) findViewById(R.id.titlebar_ihelp);
        mTitlebarIhelp.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });
        tv_sswg = (TextView) findViewById(R.id.tv_sswg);
        tv_sswg.setVisibility(View.GONE);
        mEtIhelpSousuo = (EditText) findViewById(R.id.et_ihelp_sousuo);
        mEtIhelpSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i==0||i==3)&&keyEvent!=null){
                    if (!DataTool.isNullString(mEtIhelpSousuo.getText().toString())){
                        getN_findMutualCar(mEtIhelpSousuo.getText().toString());
                        isss=true;
                    }else {
                        BaseToast.success("搜索为空");
                    }

                    return true;
                }

                return false;
            }
        });


        mRvIhelp = (RecyclerView) findViewById(R.id.rv_ihelp);
        mSlIhelp = (SwipeRefreshLayout) findViewById(R.id.sl_ihelp);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            default:
                break;

        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    private void getN_findMutualCar(String code) {
        String s_token = SPTool.getString(IHelpActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findMutualCar+"?carNum="+code)
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
                                            IhelpBean bean= new Gson().fromJson(data, IhelpBean.class);
                                            mBeanList=bean.getData();
                                            if (mBeanList.size()==0){
                                                if (isss){
                                                    iv_amc_sqt.setVisibility(View.GONE);
                                                    mRvIhelp.setVisibility(View.GONE);
                                                    tv_sswg.setVisibility(View.VISIBLE);
                                                }else {
                                                    iv_amc_sqt.setVisibility(View.VISIBLE);
                                                    mRvIhelp.setVisibility(View.GONE);
                                                    tv_sswg.setVisibility(View.GONE);
                                                }


                                            }else {
                                                isss=false;
                                                tv_sswg.setVisibility(View.GONE);
                                                iv_amc_sqt.setVisibility(View.GONE);
                                                mRvIhelp.setVisibility(View.VISIBLE);
                                            }
                                            initRecylerView();
                                            mSlIhelp.setRefreshing(false);




                                        } else if (code==1004){
                                            ActivityTool.finishAllActivity();
                                            SPTool.remove(IHelpActivity.this,"token");
                                            startActivity(new Intent(IHelpActivity.this,LoginActivity.class));
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
    private void initPullRefresh() {
        mSlIhelp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getN_findMutualCar("");

            }
        });
    }
    private void initRecylerView() {

        mAdapter = new IhelpAdapter(R.layout.item_ihelp,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvIhelp.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvIhelp.setLayoutManager(mLayoutManager);
        mRvIhelp.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SPTool.putString(IHelpActivity.this,"ih_carnum",""+mBeanList.get(position).getCarNum());


               Intent intent = new Intent(IHelpActivity.this, MutualAssistanceActivity.class);
               startActivity(intent);
            }
        });


    }


}
