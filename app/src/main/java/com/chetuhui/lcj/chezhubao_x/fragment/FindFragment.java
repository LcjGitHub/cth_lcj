package com.chetuhui.lcj.chezhubao_x.fragment;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.AllMutualCarsActivity;
import com.chetuhui.lcj.chezhubao_x.activity.IHelpActivity;
import com.chetuhui.lcj.chezhubao_x.activity.JoinActivity;
import com.chetuhui.lcj.chezhubao_x.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MessageActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MutualRecordActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MyMutualHelpActivity;
import com.chetuhui.lcj.chezhubao_x.activity.SelectCarActivity;
import com.chetuhui.lcj.chezhubao_x.adapter.FindfanganAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.MyCarAdapter;
import com.chetuhui.lcj.chezhubao_x.model.FindFanganBean;
import com.chetuhui.lcj.chezhubao_x.model.MycarBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
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


public class FindFragment extends Fragment implements View.OnClickListener {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒

    private View view;
    private CommonTitleBar mTitlebarFind;
    /**
     * 我的互助单
     */
    private TextView mTvFindWdhzd;
    /**
     * 我要互助
     */
    private TextView mTvFindWyhz;
    /**
     * 互助记录
     */
    private TextView mTvFindHzjl;
    /**
     * 全部车辆
     */
    private TextView mTvFindQbcl;
    private TextView mTvFindTjhzcl;
    private LinearLayout mLlHome2;
    private  ImageView iv_amc_sqt;
    private RecyclerView mRvFindHuzhucheliang;


    private LinearLayoutManager mLayoutManager;
    private List<MycarBean.DataBean> mBeanList = new ArrayList<>();
    private MyCarAdapter mAdapter;

    private LinearLayoutManager mLayoutManager1;
    private List<FindFanganBean.DataBean> mBeanList1 = new ArrayList<>();
    private FindfanganAdapter mFindfanganAdapter;

    private RelativeLayout mRlFindCarmutual;
    private MyHandler mHandler = new MyHandler(FindFragment.this);
    private RecyclerView mRlFindFangan;

    private static class MyHandler extends Handler {
        private WeakReference<FindFragment> activityWeakReference;

        public MyHandler(FindFragment activity) {
            activityWeakReference = new WeakReference<FindFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            FindFragment activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_layout, null);


        initView(view);
        getN_findMyCars();
        return view;
    }

    private void initView(View view) {
        iv_amc_sqt = (ImageView) view.findViewById(R.id.iv_amc_sqt);
        mTitlebarFind = (CommonTitleBar) view.findViewById(R.id.titlebar_find);
        mTitlebarFind.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_RIGHT_BUTTON) {
                   startActivity(new Intent(getContext(), MessageActivity.class));

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

        mTitlebarFind.setOnClickListener(this);
        mTvFindWdhzd = (TextView) view.findViewById(R.id.tv_find_wdhzd);
        mTvFindWdhzd.setOnClickListener(this);
        mTvFindWyhz = (TextView) view.findViewById(R.id.tv_find_wyhz);
        mTvFindWyhz.setOnClickListener(this);
        mTvFindHzjl = (TextView) view.findViewById(R.id.tv_find_hzjl);
        mTvFindHzjl.setOnClickListener(this);
        mTvFindQbcl = (TextView) view.findViewById(R.id.tv_find_qbcl);
        mTvFindQbcl.setOnClickListener(this);
        mTvFindTjhzcl = (TextView) view.findViewById(R.id.tv_find_tianjiacheliang);
        mTvFindTjhzcl.setOnClickListener(this);
        mLlHome2 = (LinearLayout) view.findViewById(R.id.ll_home_2);
        mLlHome2.setOnClickListener(this);
        mRvFindHuzhucheliang = (RecyclerView) view.findViewById(R.id.rv_find_huzhucheliang);






        mRlFindCarmutual = (RelativeLayout) view.findViewById(R.id.rl_find_carmutual);
        mRlFindFangan = (RecyclerView) view.findViewById(R.id.rl_find_fangan);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
            switch (v.getId()) {
                default:
                    break;

                case R.id.tv_find_wdhzd:
                    intent = new Intent(getContext(), MyMutualHelpActivity.class);
                    break;
                case R.id.tv_find_wyhz:
                    intent = new Intent(getContext(), IHelpActivity.class);
                    break;
                case R.id.tv_find_hzjl:
                    intent = new Intent(getContext(), MutualRecordActivity.class);
                    break;
                case R.id.tv_find_qbcl:
                    intent = new Intent(getContext(), AllMutualCarsActivity.class);
                    break;
                case R.id.ll_home_2:
                    break;
                case R.id.rv_find_huzhucheliang:
                    break;
                case R.id.tv_find_tianjiacheliang:
                    intent = new Intent(getContext(), SelectCarActivity.class);
                    break;
                case R.id.tv_find_title:
                    break;
                case R.id.tv_find_cishu:
                    break;
                case R.id.tv_find_time:
                    break;
                case R.id.iv_find_listpic:
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }
        } else {
            //BaseToast.normal("点得太快了");
            return;
        }
    }

    private void getN_findMyCars() {
        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findMyCars)
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
                                        //BaseToast.success(msg);
                                        mBeanList.clear();
                                        MycarBean bean = new Gson().fromJson(data, MycarBean.class);
                                        mBeanList = bean.getData();
                                        if (mBeanList.size()==0){
                                            iv_amc_sqt.setVisibility(View.VISIBLE);
                                            mRvFindHuzhucheliang.setVisibility(View.GONE);
                                        }else {
                                            iv_amc_sqt.setVisibility(View.GONE);
                                            mRvFindHuzhucheliang.setVisibility(View.VISIBLE);
                                        }
                                        initRecylerView();


                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(getContext(), "token");
                                        startActivity(new Intent(getContext(), LoginActivity.class));
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

    private void initRecylerView() {

        mAdapter = new MyCarAdapter(R.layout.item_mycar, mBeanList);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        //添加动画
        mRvFindHuzhucheliang.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvFindHuzhucheliang.setLayoutManager(mLayoutManager);
        mRvFindHuzhucheliang.setAdapter(mAdapter);
        //条目点击事件
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                getN_findMutualbillListByCarNum(mBeanList.get(position).getCarNum());

            }
        });


    }

    private void initRecylerViewfind() {

        mFindfanganAdapter = new FindfanganAdapter(R.layout.item_find_fangan, mBeanList1);
        mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


        //添加动画
        mRlFindFangan.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRlFindFangan.setLayoutManager(mLayoutManager1);
        mRlFindFangan.setAdapter(mFindfanganAdapter);
        //条目点击事件
        mFindfanganAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });


    }

    private void getN_findMutualbillListByCarNum(String code) {
        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findMutualbillListByCarNum + "?carNum=" + code)
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
                                        //BaseToast.success(msg);
                                        mBeanList1.clear();
                                        FindFanganBean bean = new Gson().fromJson(data, FindFanganBean.class);
                                        mBeanList1=bean.getData();
                                        initRecylerViewfind();
//
//

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(getContext(), "token");
                                        startActivity(new Intent(getContext(), LoginActivity.class));
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


}
