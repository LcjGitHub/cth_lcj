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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.AuditStationActivity;
import com.chetuhui.lcj.chezhubao_x.activity.BasicInformationActivity;
import com.chetuhui.lcj.chezhubao_x.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao_x.activity.SelectAidActivity;
import com.chetuhui.lcj.chezhubao_x.activity.UserListActivity;
import com.chetuhui.lcj.chezhubao_x.adapter.MessageAdapter;

import com.chetuhui.lcj.chezhubao_x.model.MessageBean;
import com.chetuhui.lcj.chezhubao_x.model.UserlistBean;
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
import java.util.List;


public class MessageFragment extends Fragment {

    private View view;
    private RecyclerView mRvFragmentMsg;
    private MessageAdapter mMessageAdapter;
    private List<MessageBean.DataBean> mBeanList;
    private  MyHandler mHandler =new MyHandler(MessageFragment.this);
    private static class MyHandler extends Handler {
            private WeakReference<MessageFragment> activityWeakReference;

            public MyHandler(MessageFragment activity) {
                activityWeakReference = new WeakReference<MessageFragment>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                MessageFragment activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }


    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_message_layout, null);


        initView(view);
        getfindJiGuangMessage();
        return view;
    }

    private void initView(View view) {
        mRvFragmentMsg = (RecyclerView) view.findViewById(R.id.rv_fragment_msg);
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvFragmentMsg.setLayoutManager(layoutManager);


    }
    private void getfindJiGuangMessage() {

        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {

//            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
        }
        OkGo.<String>get(NetData.N_findJiGuangMessage)
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
                                    MessageBean messageBean= new Gson().fromJson(data, MessageBean.class);
                                    mBeanList=messageBean.getData();
                                    //创建适配器
                                    mMessageAdapter = new MessageAdapter(R.layout.item_msg, mBeanList);

                                    //给RecyclerView设置适配器
                                    mRvFragmentMsg.setAdapter(mMessageAdapter);
                                    mMessageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                            SPTool.putString(getContext(),"sa_code",""+mBeanList.get(position).getJumpParam().getBillCode());
                                            SPTool.putString(getContext(),"ih_carnum",""+mBeanList.get(position).getJumpParam().getCarNum());

                                            startActivity(new Intent(getContext(), BasicInformationActivity.class));

                                        }
                                    });


                                    //BaseToast.success(msg);

                                }else if (code==1004){


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
