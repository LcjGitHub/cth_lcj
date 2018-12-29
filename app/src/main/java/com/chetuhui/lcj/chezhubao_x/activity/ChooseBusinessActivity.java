package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.ChooseBusinseAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.MrAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.UserlistAdapter;
import com.chetuhui.lcj.chezhubao_x.model.ChooseBusinessBean;
import com.chetuhui.lcj.chezhubao_x.model.PopularBean;
import com.chetuhui.lcj.chezhubao_x.model.ShowdgBean;
import com.chetuhui.lcj.chezhubao_x.model.UserlistBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
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

public class ChooseBusinessActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarCb;
    private ImageView iv_amc_sqt;
    private RelativeLayout mRlCb;
    private int cityid = 510100;
    private SwipeRefreshLayout mSlCb;
    private RecyclerView mRvCb;
    private LinearLayoutManager mLinearLayoutManager;
    private  boolean issoll=true;
    int num=1;
    private List<ChooseBusinessBean.DataBean> mBeanList =new ArrayList<>();
    private ChooseBusinseAdapter mAdapter;
    private ChooseBusinessBean.DataBean mDataBean;
    private  String bi_cph,bi_lxdh,bi_qk,bi_sj,bi_sjd,bi_wz,up_ct,up_cypzw,up_xj,up_cw,sa_code;
    private MyHandler mHandler =new MyHandler(ChooseBusinessActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<ChooseBusinessActivity> activityWeakReference;

            public MyHandler(ChooseBusinessActivity activity) {
                activityWeakReference = new WeakReference<ChooseBusinessActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                ChooseBusinessActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_business);
        cityid=SPTool.getInt(ChooseBusinessActivity.this,"city_id");
        initView();
        getN_findRepairMerchants(""+num);
        initListener();
    }


    public void showView(Context context, final ChooseBusinessBean.DataBean bean) {
        final BaseDialog rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_shangjia_show, null);
        TextView qrxz = view.findViewById(R.id.tv_dg_cb_qrxz);
        TextView title = view.findViewById(R.id.tv_dg_cb_title);
        TextView dizhi = view.findViewById(R.id.tv_dg_cb_dizhi);
        TextView paidui = view.findViewById(R.id.tv_dg_cb_paidui);
        TextView shjian = view.findViewById(R.id.tv_dg_cb_shijian);

        ImageView img = view.findViewById(R.id.iv_dg_cb_img);
        ImageView dianhua = view.findViewById(R.id.iv_dg_cb_dianhua);
        ImageView cancel = view.findViewById(R.id.iv_dg_cb_close);
        title.setText(bean.getBusinessName());
        dizhi.setText(bean.getDetailAd());
        paidui.setText("当前排队"+bean.getPeopleNum()+"人");
        shjian.setText(bean.getBeginWorkTime()+"至"+bean.getEndWorkTime());
        ShowImageUtils.showImageViewToCircle(context,bean.getCover(),img,10);
        up_xj=SPTool.getContent(ChooseBusinessActivity.this,"up_xj");
        up_cypzw=SPTool.getContent(ChooseBusinessActivity.this,"up_cypzw");
        up_ct=SPTool.getContent(ChooseBusinessActivity.this,"up_ct");
        up_cw=SPTool.getContent(ChooseBusinessActivity.this,"up_cw");
        bi_sjd=SPTool.getContent(ChooseBusinessActivity.this,"bi_sjd");
        bi_wz=SPTool.getContent(ChooseBusinessActivity.this,"bi_wz");
        bi_qk=SPTool.getContent(ChooseBusinessActivity.this,"bi_qk");
        bi_sj=SPTool.getContent(ChooseBusinessActivity.this,"bi_sj");
        bi_lxdh=SPTool.getContent(ChooseBusinessActivity.this,"bi_lxdh");
        bi_cph=SPTool.getContent(ChooseBusinessActivity.this,"bi_cph");
        sa_code=SPTool.getContent(ChooseBusinessActivity.this,"sa_code");





        dianhua.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + bean.getPhone());
                intent.setData(data);
                startActivity(intent);




            }
        });

        qrxz.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
//                if (!DataTool.isNullString(up_xj)&&!DataTool.isNullString(up_cypzw)&&!DataTool.isNullString(up_ct)
//                        &&!DataTool.isNullString(bi_sjd)&&!DataTool.isNullString(bi_wz)&&!DataTool.isNullString(bi_qk)
//                        &&!DataTool.isNullString(bi_cph)&&!DataTool.isNullString(bi_sj)&&!DataTool.isNullString(bi_lxdh)&&!DataTool.isNullString(up_cw)){
//
//                }
                getN_applyHelp(up_ct,up_cw,up_cypzw,up_xj,bi_sjd,bi_wz,bi_qk,bi_cph,bi_sj,bi_lxdh,bean.getBusinessCode(),sa_code);

//
                rxDialog.cancel();
            }
        });

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
    private void getN_applyHelp(String up_ct, String up_cw, String up_cypzw, String up_xj, String bi_sjd, String bi_wz, String bi_qk,
                                String bi_cph, String bi_sj, String bi_lxdh,String bh,String sa_code) {
        String s_token = SPTool.getString(ChooseBusinessActivity.this, "token");
        Log.d("PopularActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_applyHelp)
                .tag(this)
                .headers("token",s_token)
                .params("carNum",bi_cph)
                .params("phone",bi_lxdh)
                .params("damageLocation",bi_wz)
                .params("readyFixTime",bi_sj)
                .params("readyFixTimeQuantum",bi_sjd)
                .params("detail",bi_qk)
                .params("carHeadimg",up_ct)
                .params("carEndimg",up_cw)
                .params("carInjuredimg",up_cypzw)
                .params("carDetailimg",up_xj)
                .params("businessCode",bh)
                .params("billCode",sa_code)


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
                                BaseToast.success("选择成功，可在互助记录查看信息");
                                ActivityTool.finishAllActivity();
                               startActivity(new Intent(ChooseBusinessActivity.this,HomeActivity.class));




                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(ChooseBusinessActivity.this,"token");
                                startActivity(new Intent(ChooseBusinessActivity.this,LoginActivity.class));
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


    private void initView() {
        iv_amc_sqt = (ImageView) findViewById(R.id.iv_amc_sqt);
        mTitlebarCb = (CommonTitleBar) findViewById(R.id.titlebar_cb);
        mTitlebarCb.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mRlCb = (RelativeLayout) findViewById(R.id.rl_cb_);
        mRlCb.setOnClickListener(this);
        mSlCb = (SwipeRefreshLayout) findViewById(R.id.sl_cb);
        mRvCb = (RecyclerView) findViewById(R.id.rv_cb);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_cb_:

                break;
        }
    }

    private void getN_findRepairMerchants(String num) {

        String s_token = SPTool.getString(ChooseBusinessActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findRepairMerchants + "?pageSIze=5&pageNum=" + num + "&cityId=" + cityid)
                .tag(this)
                .headers("token", s_token)

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
                                            ChooseBusinessBean bean = new Gson().fromJson(data, ChooseBusinessBean.class);
                                            mBeanList = bean.getData();
                                            if (mBeanList.size()==0){
                                                iv_amc_sqt.setVisibility(View.VISIBLE);
                                                mRvCb.setVisibility(View.GONE);
                                            }else {
                                                iv_amc_sqt.setVisibility(View.GONE);
                                                mRvCb.setVisibility(View.VISIBLE);
                                            }
                                            initRecylerView();
                                            mSlCb.setRefreshing(false);
                                            if (mBeanList.size()<10){
                                                issoll=false;
                                                mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                                                Log.d("UserListActivity", "没得了");
                                            }

                                            //BaseToast.success(msg);

                                        } else if (code == 1004) {
                                            ActivityTool.finishAllActivity();
                                            SPTool.remove(ChooseBusinessActivity.this, "token");
                                            startActivity(new Intent(ChooseBusinessActivity.this, LoginActivity.class));
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

    private void getfindUserAllByPage_more(String num) {

        String s_token = SPTool.getString(ChooseBusinessActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findUserAllByPage + "?pageSize=10&pageNum=" + num)
                .tag(this)
                .headers("token", s_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code = jsonObject.getInt("code");
                            if (code == 0) {

                                ChooseBusinessBean bean = new Gson().fromJson(data, ChooseBusinessBean.class);
                                mBeanList = bean.getData();

//

                                mAdapter.AddFooterItem(mBeanList);
                                mAdapter.changeMoreStatus(mAdapter.PULLUP_LOAD_MORE);
                                if (mBeanList.size() < 10) {
                                    issoll = false;
                                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                                    Log.d("UserListActivity", "没得了");
                                }


                                //BaseToast.success(msg);

                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(ChooseBusinessActivity.this, "token");
                                startActivity(new Intent(ChooseBusinessActivity.this, LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            } else {
                                BaseToast.success(msg);

                            }

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

    private void initRecylerView() {

        mAdapter = new ChooseBusinseAdapter(this, mBeanList);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        //添加动画
        mRvCb.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvCb.setLayoutManager(mLinearLayoutManager);
        mRvCb.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ChooseBusinseAdapter.setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int pos) {

                        mDataBean=mBeanList.get(pos);
                showView(ChooseBusinessActivity.this, mDataBean);

            }
        });

    }

    private void initListener() {

        initPullRefresh();

        initLoadMoreListener();

    }


    private void initPullRefresh() {
        mSlCb.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                num = 1;
                issoll = true;
                getN_findRepairMerchants("" + num);

            }
        });
    }

    private void initLoadMoreListener() {

        mRvCb.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {


                    if (issoll) {
                        //设置正在加载更多
                        mAdapter.changeMoreStatus(mAdapter.LOADING_MORE);
                        num++;
                        getfindUserAllByPage_more("" + num);
                    }

                    //改为网络请求
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            //
//                            List<String> footerDatas = new ArrayList<String>();
//                            for (int i = 0; i< 10; i++) {
//
//                                footerDatas.add("footer  item" + i);
//                            }
//                            mRefreshAdapter.AddFooterItem(footerDatas);
//                            //设置回到上拉加载更多
//                            mRefreshAdapter.changeMoreStatus(mRefreshAdapter.PULLUP_LOAD_MORE);
//                            //没有加载更多了
//                            //mRefreshAdapter.changeMoreStatus(mRefreshAdapter.NO_LOAD_MORE);
//                        }
//                    }, 3000);


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

    }


}
