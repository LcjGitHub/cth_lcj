package com.chetuhui.lcj.chezhubao_x.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MutualHelpListDetailsActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MutualRecordDetailsActivity;
import com.chetuhui.lcj.chezhubao_x.adapter.MmhAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.MrAdapter;
import com.chetuhui.lcj.chezhubao_x.model.MmhBean;
import com.chetuhui.lcj.chezhubao_x.model.MrBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class MrAllFragment extends Fragment {

private ImageView iv_amc_sqt;
    private View view;
    private RecyclerView mRvGsz;
    private SwipeRefreshLayout mSlGsz;
    private List<MrBean.DataBean> mBeanList=new ArrayList<>();
    private MrAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private  boolean issoll=true;
    int num=1;
    private MyHandler mHandler =new MyHandler(MrAllFragment.this);
    private static class MyHandler extends Handler {
            private WeakReference<MrAllFragment> activityWeakReference;

            public MyHandler(MrAllFragment activity) {
                activityWeakReference = new WeakReference<MrAllFragment>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                MrAllFragment activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }


    public static MrAllFragment newInstance() {
        MrAllFragment fragment = new MrAllFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gsz_layout, null);


        initView(view);
        initListener();
        getN_applicationRecord();
        return view;
    }


    private void initView(View view) {
        iv_amc_sqt = (ImageView) view.findViewById(R.id.iv_amc_sqt);
        mRvGsz = (RecyclerView) view.findViewById(R.id.rv_gsz);
        mSlGsz = (SwipeRefreshLayout) view.findViewById(R.id.sl_gsz);
    }

    private void getN_applicationRecord() {

        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_applicationRecord+"?type=1")
                .tag(this)
                .headers("token",s_token)
                .cacheKey("mutualAidEvent1")       //由于该fragment会被复用,必须保证key唯一,否则数据会发生覆盖
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据

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
                                        mBeanList.clear();
                                        MrBean bean= new Gson().fromJson(data, MrBean.class);
                                        mBeanList=bean.getData();
                                        if (mBeanList.size()==0){
                                            iv_amc_sqt.setVisibility(View.VISIBLE);
                                            mRvGsz.setVisibility(View.GONE);
                                        }else {
                                            iv_amc_sqt.setVisibility(View.GONE);
                                            mRvGsz.setVisibility(View.VISIBLE);
                                        }
                                        initRecylerView();
                                        mSlGsz.setRefreshing(false);
                                        if (mBeanList.size()<10){
                                            issoll=false;
                                            mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                                            Log.d("UserListActivity", "没得了");
                                        }
                                        //BaseToast.success(msg);

                                    }else if (code==1004){
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(getContext(),"token");
                                        startActivity(new Intent(getContext(),LoginActivity.class));
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

    private void getN_applicationRecord_more(String num,String code) {

        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_applicationRecord+"?pageSize=10&pageNum="+num+"&type=1&carNum="+code)
                .tag(this)
                .headers("token",s_token)
                .cacheKey("mutualAidEvent1")       //由于该fragment会被复用,必须保证key唯一,否则数据会发生覆盖
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据

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

                                MrBean bean= new Gson().fromJson(data, MrBean.class);
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
                                SPTool.remove(getContext(),"token");
                                startActivity(new Intent(getContext(),LoginActivity.class));
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

        mAdapter = new MrAdapter(getContext(),mBeanList);
        mLinearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvGsz.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvGsz.setLayoutManager(mLinearLayoutManager);
        mRvGsz.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MrAdapter.setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                if(mBeanList.get(position).getState()==0){
                    Intent intent =new Intent(getContext(), MutualRecordDetailsActivity.class);
                    intent.putExtra("code",""+mBeanList.get(position).getSalvationCode());
                    intent.putExtra("type_djz","3");
                    startActivity(intent);

                }else {
                    //1：救助完成 2：等待救助 3：商家拒绝
                    if(mBeanList.get(position).getHelpState()==1){
                        Intent intent =new Intent(getContext(), MutualRecordDetailsActivity.class);
                        intent.putExtra("code",""+mBeanList.get(position).getSalvationCode());
                        intent.putExtra("type_djz","3");
                        startActivity(intent);


                    }else if (mBeanList.get(position).getHelpState()==2){
                        Intent intent =new Intent(getContext(), MutualRecordDetailsActivity.class);
                        intent.putExtra("code",""+mBeanList.get(position).getSalvationCode());
                        intent.putExtra("type_djz","1");
                        startActivity(intent);

                    }else if (mBeanList.get(position).getHelpState()==3){


                        Intent intent =new Intent(getContext(), MutualRecordDetailsActivity.class);
                        intent.putExtra("code",""+mBeanList.get(position).getSalvationCode());
                        intent.putExtra("type_djz","2");
                        startActivity(intent);
                    }else if (mBeanList.get(position).getHelpState()==4){

                        Intent intent =new Intent(getContext(), MutualRecordDetailsActivity.class);
                        intent.putExtra("code",""+mBeanList.get(position).getSalvationCode());
                        intent.putExtra("type_djz","3");
                        startActivity(intent);

                    }
                }






            }
        });


    }

    private void initListener() {

        initPullRefresh();

//        initLoadMoreListener();

    }



    private void initPullRefresh() {
        mSlGsz.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                num=1;
                issoll=true;
                getN_applicationRecord();

            }
        });
    }

    private void initLoadMoreListener() {

        mRvGsz.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
//                        getN_findMyMutualbills_more(""+num,"");
                    }


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
