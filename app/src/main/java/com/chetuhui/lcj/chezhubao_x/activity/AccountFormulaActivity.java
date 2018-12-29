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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.AccountFormulaAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.CarAdapter;
import com.chetuhui.lcj.chezhubao_x.model.AccountFormulaBean;
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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.chetuhui.lcj.chezhubao_x.tool.DataTool.getAmountValue;

public class AccountFormulaActivity extends ActivityBase {

    private CommonTitleBar mTitlebarAccountformula;
    private ImageView iv_amc_sqt;
    /**
     * 事件公式
     */
    private TextView mTvMutualeventSjgs;
    /**  */
    private TextView mTvAccountformulaYue;
    /**  */
    private TextView mTvAccountformulaZhichu;
    private RelativeLayout mRlAccountformula;
    private RecyclerView mRvAccountformula;

    private List<AccountFormulaBean.DataBean.ListBean> mBeanList=new ArrayList<>();
    private AccountFormulaAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private MyHandler mMyHandler=new MyHandler(AccountFormulaActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<AccountFormulaActivity> activityWeakReference;

            public MyHandler(AccountFormulaActivity activity) {
                activityWeakReference = new WeakReference<AccountFormulaActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                AccountFormulaActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_formula);
        initView();
        getN_acountDisclosure();
    }

    private void initView() {
        iv_amc_sqt = (ImageView) findViewById(R.id.iv_amc_sqt);
        mTitlebarAccountformula = (CommonTitleBar) findViewById(R.id.titlebar_accountformula);
        mTitlebarAccountformula.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }

            }
        });

        mTvMutualeventSjgs = (TextView) findViewById(R.id.tv_mutualevent_sjgs);
        mTvAccountformulaYue = (TextView) findViewById(R.id.tv_accountformula_yue);
        mTvAccountformulaZhichu = (TextView) findViewById(R.id.tv_accountformula_zhichu);
        mRlAccountformula = (RelativeLayout) findViewById(R.id.rl_accountformula);
        mRvAccountformula = (RecyclerView) findViewById(R.id.rv_accountformula);
    }

    private void getN_acountDisclosure() {
        String s_token = SPTool.getString(AccountFormulaActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_acountDisclosure)
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
                                mMyHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //BaseToast.success(msg);
                                        mBeanList.clear();
                                        AccountFormulaBean bean= new Gson().fromJson(data, AccountFormulaBean.class);
                                        mBeanList=bean.getData().getList();if (mBeanList.size()==0){
                                            iv_amc_sqt.setVisibility(View.VISIBLE);
                                            mRvAccountformula.setVisibility(View.GONE);
                                        }else {
                                            iv_amc_sqt.setVisibility(View.GONE);
                                            mRvAccountformula.setVisibility(View.VISIBLE);
                                        }
                                        initRecylerView();
                                        mTvAccountformulaYue.setText("¥"+getAmountValue(bean.getData().getBalance()));
                                        mTvAccountformulaZhichu.setText("¥"+getAmountValue(bean.getData().getExpenditure()));

                                    }
                                });






                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(AccountFormulaActivity.this,"token");
                                startActivity(new Intent(AccountFormulaActivity.this,LoginActivity.class));
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

    private void initRecylerView() {

        mAdapter = new AccountFormulaAdapter(R.layout.item_accountformula,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvAccountformula.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvAccountformula.setLayoutManager(mLayoutManager);
        mRvAccountformula.setAdapter(mAdapter);
    }



}
