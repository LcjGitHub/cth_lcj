package com.chetuhui.lcj.chezhubao_x.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao_x.adapter.CreAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.MessageAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.RedAdapter;
import com.chetuhui.lcj.chezhubao_x.model.MessageBean;
import com.chetuhui.lcj.chezhubao_x.model.RedBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class RedEnvelopeFragment extends Fragment {

    private View view;
    private RecyclerView mRvFragmentMsg;
    private RedAdapter mAdapter;
    private List<RedBean.DataBean> mBeanList=new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private  MyHandler mHandler =new MyHandler(RedEnvelopeFragment.this);
    private static class MyHandler extends Handler {
            private WeakReference<RedEnvelopeFragment> activityWeakReference;

            public MyHandler(RedEnvelopeFragment activity) {
                activityWeakReference = new WeakReference<RedEnvelopeFragment>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                RedEnvelopeFragment activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }


    public static RedEnvelopeFragment newInstance() {
        RedEnvelopeFragment fragment = new RedEnvelopeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_red_layout, null);


        initView(view);
        getN_findMyTickets();
        return view;
    }

    private void initView(View view) {
        mRvFragmentMsg = (RecyclerView) view.findViewById(R.id.rv_fragment_red);


    }
    private void getN_findMyTickets() {

        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findMyTickets)
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
                                    mBeanList.clear();
                                    RedBean bean= new Gson().fromJson(data, RedBean.class);
                                    mBeanList=bean.getData();
                                 initRecylerView();
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
    private void initRecylerView() {

        mAdapter = new RedAdapter(R.layout.item_red,mBeanList);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvFragmentMsg.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvFragmentMsg.setLayoutManager(mLayoutManager);
        mRvFragmentMsg.setAdapter(mAdapter);
        //条目点击事件

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {




            }
        });



    }


}
