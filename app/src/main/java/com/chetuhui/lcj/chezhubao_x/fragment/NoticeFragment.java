package com.chetuhui.lcj.chezhubao_x.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.AuditStationActivity;
import com.chetuhui.lcj.chezhubao_x.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao_x.adapter.MessageAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.NoticeAdapter;
import com.chetuhui.lcj.chezhubao_x.model.MessageBean;
import com.chetuhui.lcj.chezhubao_x.model.NoticeBean;
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


public class NoticeFragment extends Fragment {

    private View view;
    private RecyclerView mRvFragmentNotice;
    private NoticeAdapter mNoticeAdapter;
    private List<NoticeBean.DataBean> mBeanList=new ArrayList<>();
    private MyHandler mHandler =new MyHandler(NoticeFragment.this);
    private static class MyHandler extends Handler {
            private WeakReference<NoticeFragment> activityWeakReference;

            public MyHandler(NoticeFragment activity) {
                activityWeakReference = new WeakReference<NoticeFragment>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                NoticeFragment activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    public static NoticeFragment newInstance() {
        NoticeFragment fragment = new NoticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_notice_layout, null);


        initView(view);
        getN_findAnnouncement();
        return view;
    }

    private void initView(View view) {
        mRvFragmentNotice = (RecyclerView) view.findViewById(R.id.rv_fragment_notice);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvFragmentNotice.setLayoutManager(layoutManager);
    }
    private void getN_findAnnouncement() {

        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {

//            SPTool.remove(getContext(),"token");

//            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
        }
        OkGo.<String>get(NetData.N_findAnnouncement)
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
                                        NoticeBean noticeBean = new Gson().fromJson(data, NoticeBean.class);
                                        mBeanList=noticeBean.getData();
                                        //创建适配器
                                        mNoticeAdapter = new NoticeAdapter(R.layout.item_notice, mBeanList);

                                        //给RecyclerView设置适配器
                                        mRvFragmentNotice.setAdapter(mNoticeAdapter);
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

}
