package com.chetuhui.lcj.chezhubao_x.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.AccountFormulaAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.CreAdapter;
import com.chetuhui.lcj.chezhubao_x.model.AccountFormulaBean;
import com.chetuhui.lcj.chezhubao_x.model.CreBean;
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

public class ChooseRedEnvelopeActivity extends ActivityBase {

    private CommonTitleBar mTitlebarCre;
    private CheckBox mCbCreBsyhb;
    private RecyclerView mRvCre;
    private CreAdapter mAdapter;
    private List<CreBean.DataBean> mBeanList=new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private  String bh_code;

    private MyHandler mMyHandler=new MyHandler(ChooseRedEnvelopeActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<ChooseRedEnvelopeActivity> activityWeakReference;

            public MyHandler(ChooseRedEnvelopeActivity activity) {
                activityWeakReference = new WeakReference<ChooseRedEnvelopeActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                ChooseRedEnvelopeActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_red_envelope);
        bh_code=getIntent().getStringExtra("bh_code");
        initView();
        getN_findTicket(bh_code);
    }

    private void initView() {
        mTitlebarCre = (CommonTitleBar) findViewById(R.id.titlebar_cre);
        mTitlebarCre.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mCbCreBsyhb = (CheckBox) findViewById(R.id.cb_cre_bsyhb);
        mCbCreBsyhb.setChecked(true);
        mRvCre = (RecyclerView) findViewById(R.id.rv_cre);
        mCbCreBsyhb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Intent intent = new Intent();
                    intent.putExtra("hb", "0.00"); //放置要传出的数据
                    intent.putExtra("hb_id", "0"); //放置要传出的数据
                    //这里是在Recycleview的适配器里，传了一个Activity才能用方法setResult
                    mContext.setResult(Activity.RESULT_OK,intent);
                    mContext.finish(); //关闭活动
                }else {

                }

            }
        });
    }
    private void getN_findTicket(String code) {
        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findTicket+"?programeCode="+code)
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
                                        CreBean bean= new Gson().fromJson(data, CreBean.class);
                                        mBeanList=bean.getData();
                                        initRecylerView();

                                    }
                                });


                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(mContext,"token");
                                startActivity(new Intent(mContext,LoginActivity.class));
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

        mAdapter = new CreAdapter(R.layout.item_cre,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvCre.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvCre.setLayoutManager(mLayoutManager);
        mRvCre.setAdapter(mAdapter);
        //条目点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mBeanList.get(position).getIsUse().equals("1")){

                    Intent intent = new Intent();
                    intent.putExtra("hb", ""+mBeanList.get(position).getTicketMoney()); //放置要传出的数据
                    intent.putExtra("hb_id", ""+mBeanList.get(position).getId()); //放置要传出的数据
                    //这里是在Recycleview的适配器里，传了一个Activity才能用方法setResult
                    mContext.setResult(Activity.RESULT_OK,intent);
                    mContext.finish(); //关闭活动

                }else if (mBeanList.get(position).getIsUse().equals("2")) {
                    BaseToast.error("当前红包不可用");
                }



            }
        });



    }



}
