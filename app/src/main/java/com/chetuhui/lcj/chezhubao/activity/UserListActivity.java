package com.chetuhui.lcj.chezhubao.activity;

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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao.MainActivity;
import com.chetuhui.lcj.chezhubao.R;

import com.chetuhui.lcj.chezhubao.adapter.RefreshAdapter;



import com.chetuhui.lcj.chezhubao.model.UserlistBean;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class UserListActivity extends ActivityBase   {

    private CommonTitleBar mTitlebarUserlist;
    private RecyclerView mRvUserlist;

    private List<UserlistBean.DataBean> mUserlistBeanList=new ArrayList<UserlistBean.DataBean>();
    private  SwipeRefreshLayout mSwipeRefreshLayout;

    int num=1;
     private RefreshAdapter mRefreshAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private  boolean issoll=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        initView();
        initListener();
        getfindUserAllByPage(""+num);

    }

    private void initView() {
        mSwipeRefreshLayout=findViewById(R.id.sr_userlist_);

        mTitlebarUserlist = (CommonTitleBar) findViewById(R.id.titlebar_userlist);
        mTitlebarUserlist.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mRvUserlist = (RecyclerView) findViewById(R.id.rv_userlist_);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvUserlist.setLayoutManager(layoutManager);




    }
    private void getfindUserAllByPage(String num) {

        String s_token = SPTool.getString(UserListActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findUserAllByPage+"?pageSize=10&pageNum="+num)
                .tag(this)
                .headers("token",s_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                mUserlistBeanList.clear();
                                UserlistBean userlistBean= new Gson().fromJson(data, UserlistBean.class);
                                mUserlistBeanList=userlistBean.getData();

                             initRecylerView();
                                mSwipeRefreshLayout.setRefreshing(false);
                                BaseToast.success(msg);

                            }else if (code==1004){
                                ActivityTool.finishAllActivity();
                                startActivity(new Intent(UserListActivity.this,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
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
    private void getfindUserAllByPage_more(String num) {

        String s_token = SPTool.getString(UserListActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findUserAllByPage+"?pageSize=10&pageNum="+num)
                .tag(this)
                .headers("token",s_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){

                                UserlistBean userlistBean= new Gson().fromJson(data, UserlistBean.class);
                                mUserlistBeanList=userlistBean.getData();

//

                                mRefreshAdapter.AddFooterItem(mUserlistBeanList);
                                mRefreshAdapter.changeMoreStatus(mRefreshAdapter.PULLUP_LOAD_MORE);
                                if (mUserlistBeanList.size()<10){
                                    issoll=false;
                                    mRefreshAdapter.changeMoreStatus(mRefreshAdapter.NO_LOAD_MORE);
                                    Log.d("UserListActivity", "没得了");
                                }


                                BaseToast.success(msg);

                            }else if (code==1004){
                                ActivityTool.finishAllActivity();
                                startActivity(new Intent(UserListActivity.this,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
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

        mRefreshAdapter = new RefreshAdapter(this,mUserlistBeanList);
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvUserlist.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvUserlist.setLayoutManager(mLinearLayoutManager);
        mRvUserlist.setAdapter(mRefreshAdapter);
    }

    private void initListener() {

        initPullRefresh();

        initLoadMoreListener();

    }



    private void initPullRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                num=1;
                issoll=true;
                getfindUserAllByPage(""+num);

            }
        });
    }

    private void initLoadMoreListener() {

        mRvUserlist.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==mRefreshAdapter.getItemCount()){


                    if (issoll){
                        //设置正在加载更多
                        mRefreshAdapter.changeMoreStatus(mRefreshAdapter.LOADING_MORE);
                        num++;
                        getfindUserAllByPage_more(""+num);
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
