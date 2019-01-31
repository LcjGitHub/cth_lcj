package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.AuditStationAdapter;

import com.chetuhui.lcj.chezhubao_x.model.AuditStationBean;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.model.UserlistBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
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

public class AuditStationActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAs;
    private ImageView iv_amc_sqt;

    /**
     * 审核服务站名称
     */
    private TextView mTvItemSelectCarCode;
    /**
     * 电话：028-12434242
     */
    private TextView mTvItemSelectCarCod;
    /**
     * 高新区环球中心S2三楼
     */
    private TextView mTvItemSelectCarCo;
    private RecyclerView mRvAs;
    private SwipeRefreshLayout mSlAs;
    /**
     * 下一步
     */
    private SuperTextView mTvAsXiayibu;

    private boolean isonclick =false;
    private List<AuditStationBean.DataBean> mBeanList=new ArrayList<>();
    private AuditStationAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int num=1;
    private  boolean issoll=true;
    private int cityid = 0;
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
    private MyHandler mHandler=new MyHandler(AuditStationActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<AuditStationActivity> activityWeakReference;

            public MyHandler(AuditStationActivity activity) {
                activityWeakReference = new WeakReference<AuditStationActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                AuditStationActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_station);
        cityid=SPTool.getInt(AuditStationActivity.this,"city_id");
        initView();
        initPullRefresh();
        initLoadMoreListener();
        getN_findBusinessByCity(""+cityid,""+num);
    }

    private void initView() {
        iv_amc_sqt = (ImageView) findViewById(R.id.iv_amc_sqt);
        mTitlebarAs = (CommonTitleBar) findViewById(R.id.titlebar_as);
        mTitlebarAs.setListener(new CommonTitleBar.OnTitleBarListener() {
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



        mTvItemSelectCarCode = (TextView) findViewById(R.id.tv_item_select_car_code);
        mTvItemSelectCarCode.setOnClickListener(this);
        mTvItemSelectCarCod = (TextView) findViewById(R.id.tv_item_select_car_cod);
        mTvItemSelectCarCod.setOnClickListener(this);
        mTvItemSelectCarCo = (TextView) findViewById(R.id.tv_item_select_car_co);
        mTvItemSelectCarCo.setOnClickListener(this);
        mRvAs = (RecyclerView) findViewById(R.id.rv_as_);
        mRvAs.setOnClickListener(this);
        mSlAs = (SwipeRefreshLayout) findViewById(R.id.sl_as_);
        mSlAs.setOnClickListener(this);
        mTvAsXiayibu = (SuperTextView) findViewById(R.id.tv_as_xiayibu);
        mTvAsXiayibu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.cb_item_as:
                break;
            case R.id.tv_item_select_car_code:
                break;
            case R.id.tv_item_select_car_cod:
                break;
            case R.id.tv_item_select_car_co:
                break;
            case R.id.rv_as_:
                break;
            case R.id.sl_as_:
                break;
            case R.id.tv_as_xiayibu:
                if (isonclick){
                    intent=new Intent(AuditStationActivity.this,ConfirmActivity.class);
                }else {
                    BaseToast.error("还没选择站点");
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


    private void initPullRefresh() {
        mSlAs.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                num=1;
                issoll=true;
                getN_findBusinessByCity(""+cityid,""+num);

            }
        });
    }
    private void getN_findBusinessByCity(String code,String num) {
        String s_token = SPTool.getString(AuditStationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findBusinessByCity+"?pageNum="+num+"&pageSize=10&cityId="+code)
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
                                      AuditStationBean bean= new Gson().fromJson(data, AuditStationBean.class);
                                      mBeanList=bean.getData();if (mBeanList.size()==0){
                                          iv_amc_sqt.setVisibility(View.VISIBLE);
                                          mRvAs.setVisibility(View.GONE);
                                      }else {
                                          iv_amc_sqt.setVisibility(View.GONE);
                                          mRvAs.setVisibility(View.VISIBLE);
                                      }
                                      initRecylerView();
                                      mSlAs.setRefreshing(false);
                                      if (mBeanList.size()==0){
                                          BaseToast.success("暂无服务站");
                                      }
                                      if (mBeanList.size()<10){
                                          issoll=false;
                                          mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                                          Log.d("UserListActivity", "没得了");
                                      }



                                  } else if (code==1004){
                                      ActivityTool.finishAllActivity();
                                      SPTool.remove(AuditStationActivity.this,"token");
                                      startActivity(new Intent(AuditStationActivity.this,LoginActivity.class));
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
    private void getN_findBusinessByCity_more(String code,String num) {

        String s_token = SPTool.getString(AuditStationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findBusinessByCity+"?pageNum="+num+"&pageSize=10&cityId="+code)
                .tag(this)
                .headers("token",s_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
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

                                        AuditStationBean bean= new Gson().fromJson(data, AuditStationBean.class);
                                        mBeanList=bean.getData();

//

                                        mAdapter.AddFooterItem(mBeanList);
                                        mAdapter.changeMoreStatus(mAdapter.PULLUP_LOAD_MORE);
                                        if (mBeanList.size()<10){
                                            issoll=false;
                                            mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                                            Log.d("UserListActivity", "没得了");
                                        }


                                        //BaseToast.success(msg);

                                    }else if (code==1004){
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(AuditStationActivity.this,"token");
                                        startActivity(new Intent(AuditStationActivity.this,LoginActivity.class));
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
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }


    private void initRecylerView() {

        mAdapter = new AuditStationAdapter(this,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvAs.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvAs.setLayoutManager(mLayoutManager);
        mRvAs.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AuditStationAdapter.setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int pos) {
                    mAdapter.unSelectAll();
                    mAdapter.unSelect(pos);
                 List<Boolean> booleanlist=mAdapter.Check();
                for (int i = 0; i <mBeanList.size() ; i++) {
                    if ( booleanlist.get(i)){
                        isonclick=true;
                    }


                }
                    SPTool.putString(AuditStationActivity.this,"au_bh",mBeanList.get(pos).getBusinessCode());

            }
        });

    }

    private void initLoadMoreListener() {

        mRvAs.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==mAdapter.getItemCount()){


                    if (issoll){
                        //设置正在加载更多
                        mAdapter.changeMoreStatus(mAdapter.LOADING_MORE);
                        num++;
                        getN_findBusinessByCity_more(""+cityid,""+num);
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
                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
            }
        });

    }


}
