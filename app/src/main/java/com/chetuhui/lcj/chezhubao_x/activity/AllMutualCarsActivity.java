package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.AmcAdapter;

import com.chetuhui.lcj.chezhubao_x.model.AmcBean;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AllMutualCarsActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAmc;
    /**
     * 请输入车牌号搜索
     */
    private EditText mEtAmcSousuo;
    /**
     * 我的爱车：
     */
    private TextView mTvAmcAiche;

    private RecyclerView mRvAmc;
    private SwipeRefreshLayout mSlAmc;
    /**
     * 确定
     */
    private TextView mTvAmcQueding;
    private  ImageView iv_amc_sqt;
    private LinearLayoutManager mLayoutManager;
    private List<AmcBean.DataBean> mBeanList=new ArrayList<>();
    private AmcAdapter mAdapter;
    private MyHandler mHandler=new MyHandler(AllMutualCarsActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<AllMutualCarsActivity> activityWeakReference;

            public MyHandler(AllMutualCarsActivity activity) {
                activityWeakReference = new WeakReference<AllMutualCarsActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                AllMutualCarsActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_mutual_cars);
        initView();
        initPullRefresh();
        getN_allMutualCars("");
    }

    private void initView() {

        iv_amc_sqt = (ImageView) findViewById(R.id.iv_amc_sqt);
        mTitlebarAmc = (CommonTitleBar) findViewById(R.id.titlebar_amc);
        mTitlebarAmc.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mEtAmcSousuo = (EditText) findViewById(R.id.et_amc_sousuo);
        mEtAmcSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i==0||i==3)&&keyEvent!=null){
                    if (!DataTool.isNullString(mEtAmcSousuo.getText().toString())){
                        getN_allMutualCars(mEtAmcSousuo.getText().toString());
                    }else {
                        BaseToast.success("搜索为空");
                    }

                    return true;
                }

                return false;
            }
        });

        mTvAmcAiche = (TextView) findViewById(R.id.tv_amc_aiche);

        mRvAmc = (RecyclerView) findViewById(R.id.rv_amc);
        mSlAmc = (SwipeRefreshLayout) findViewById(R.id.sl_amc);
        mTvAmcQueding = (TextView) findViewById(R.id.tv_amc_queding);
        mTvAmcQueding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_amc_queding:
                break;
        }
    }

    private void getN_allMutualCars(String code) {
        String s_token = SPTool.getString(AllMutualCarsActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_allMutualCars+"?carNum="+code)
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
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //BaseToast.success(msg);
                                        mBeanList.clear();
                                        AmcBean bean= new Gson().fromJson(data, AmcBean.class);
                                        mBeanList=bean.getData();
                                        mTvAmcAiche.setText("我的爱车："+mBeanList.size());
                                        if (mBeanList.size()==0){
                                            iv_amc_sqt.setVisibility(View.VISIBLE);
                                            mRvAmc.setVisibility(View.GONE);
                                        }else {
                                            iv_amc_sqt.setVisibility(View.GONE);
                                            mRvAmc.setVisibility(View.VISIBLE);
                                        }
                                        initRecylerView();
                                        mSlAmc.setRefreshing(false);
                                    }
                                });





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(AllMutualCarsActivity.this,"token");
                                startActivity(new Intent(AllMutualCarsActivity.this,LoginActivity.class));
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
    private void initPullRefresh() {
        mSlAmc.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getN_allMutualCars("");

            }
        });
    }
    private void initRecylerView() {

        mAdapter = new AmcAdapter(R.layout.item_amc,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvAmc.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvAmc.setLayoutManager(mLayoutManager);
        mRvAmc.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    AmcBean.DataBean bean=new AmcBean.DataBean();
                bean.setCarNum(mBeanList.get(position).getCarNum());
                bean.setCarBrand(mBeanList.get(position).getCarBrand());
                bean.setCarCode(mBeanList.get(position).getCarCode());
                bean.setEngineNum(mBeanList.get(position).getEngineNum());
                bean.setLicensePicture(mBeanList.get(position).getLicensePicture());
                bean.setOwner(mBeanList.get(position).getOwner());

                Intent intent =new Intent(AllMutualCarsActivity.this,VehicleDetailsActivity.class);

                intent.putExtra("amc_bean", bean);
                startActivity(intent);





            }
        });


    }


}
