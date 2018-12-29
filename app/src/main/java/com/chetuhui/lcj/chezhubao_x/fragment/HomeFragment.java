package com.chetuhui.lcj.chezhubao_x.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.AccountFormulaActivity;
import com.chetuhui.lcj.chezhubao_x.activity.CityActivity;
import com.chetuhui.lcj.chezhubao_x.activity.JoinActivity;
import com.chetuhui.lcj.chezhubao_x.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MessageActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MutualEventsActivity;
import com.chetuhui.lcj.chezhubao_x.activity.PopularActivity;
import com.chetuhui.lcj.chezhubao_x.activity.UserListActivity;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.RunTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import static com.chetuhui.lcj.chezhubao_x.application.MyApplication.getAppContext;
import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;


public class HomeFragment extends Fragment implements View.OnClickListener {


    private View view;
    private CommonTitleBar mTitlebarHome;
    /**
     * 123456
     */
    private TextView mTvHomeMoney;
    /**
     * 已有23423辆车加入
     */
    private TextView mTvHomeLiangche;
    private LinearLayout mLlHomeMoneyCenter;
    /**
     * 用户138****2325加入了车助宝互助平台，获得1年1500元的维修基金
     */
    private static RunTextView mTvHomeScroll;
    /**
     * 账户公式
     */
    private TextView mTvHomeZhanghugongshi;
    /**
     * 互助事件
     */
    private TextView mTvHomeHuzhushijian;
    /**
     * 用户列表
     */
    private TextView mTvHomeYonghuliebiao;
    /**
     * 热门问题
     */
    private TextView mTvHomeRemenshijian;

    /**
     * 申请互助
     */
    private TextView mTvHomeShenqinghuzhu;
    /**
     * 成都
     */
    private TextView mTvHomeCity;
    private  double je= 0;
        private String city_name;
    public static Handler mhandler = new  Handler(){
        // 通过复写handlerMessage()从而确定更新UI的操作
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    String mg= String.valueOf(msg.obj);
                    SPTool.putString(getAppContext(),"home_sl",""+mg);
                    mTvHomeScroll.setText(mg);
                    break;

            }




                        // 需执行的UI操作
        }
    };

        private MyHandler mHandler =new MyHandler(HomeFragment.this);
        private static class MyHandler extends Handler {
                private WeakReference<HomeFragment> activityWeakReference;

                public MyHandler(HomeFragment activity) {
                    activityWeakReference = new WeakReference<HomeFragment>(activity);
                }

                @Override
                public void handleMessage(Message msg) {
                    HomeFragment activity = activityWeakReference.get();
                    if (activity != null) {

                    }
                }
            }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        city_name= SPTool.getString(getContext(),"city_name");
        if (DataTool.isNullString(city_name)){
            startActivity(new Intent(getContext(),CityActivity.class));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        city_name= SPTool.getString(getContext(),"city_name");
        if (DataTool.isNullString(city_name)){
            mTvHomeCity.setText("选择城市");
        }else {
            mTvHomeCity.setText(city_name);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_layout, null);


        initView(view);
        N_home();
        return view;
    }

    private void initView(View view) {
        mTitlebarHome = (CommonTitleBar) view.findViewById(R.id.titlebar_home);
        mTitlebarHome.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_RIGHT_BUTTON) {
                    Intent intent = new Intent(getContext(), MessageActivity.class);
                    startActivity(intent);
                    return;

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


        mTvHomeMoney = (TextView) view.findViewById(R.id.tv_home_money_);
        mTvHomeMoney.setOnClickListener(this);
        mTvHomeLiangche = (TextView) view.findViewById(R.id.tv_home_liangche);
        mTvHomeLiangche.setOnClickListener(this);
        mLlHomeMoneyCenter = (LinearLayout) view.findViewById(R.id.ll_home_money_center);
        mLlHomeMoneyCenter.setOnClickListener(this);
        mTvHomeScroll = (RunTextView) view.findViewById(R.id.tv_home_scroll);
        mTvHomeScroll.setOnClickListener(this);
        mTvHomeZhanghugongshi = (TextView) view.findViewById(R.id.tv_home_zhanghugongshi);
        mTvHomeZhanghugongshi.setOnClickListener(this);
        mTvHomeHuzhushijian = (TextView) view.findViewById(R.id.tv_home_huzhushijian);
        mTvHomeHuzhushijian.setOnClickListener(this);
        mTvHomeYonghuliebiao = (TextView) view.findViewById(R.id.tv_home_yonghuliebiao);
        mTvHomeYonghuliebiao.setOnClickListener(this);
        mTvHomeRemenshijian = (TextView) view.findViewById(R.id.tv_home_remenshijian);
        mTvHomeRemenshijian.setOnClickListener(this);
        mTvHomeShenqinghuzhu = (TextView) view.findViewById(R.id.tv_home_shenqinghuzhu);
        mTvHomeShenqinghuzhu.setOnClickListener(this);
        mTvHomeCity = (TextView) view.findViewById(R.id.tv_home_city);
        mTvHomeCity.setOnClickListener(this);

        if (DataTool.isNullString(city_name)){
            mTvHomeCity.setText("选择城市");
        }else {
            mTvHomeCity.setText(city_name);
        }
        mTvHomeScroll.setText(""+SPTool.getString(getContext(),"home_sl"));
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
            switch (v.getId()) {
                default:
                    break;

                case R.id.tv_home_money_:
                    break;
                case R.id.tv_home_city:
                    intent = new Intent(getContext(), CityActivity.class);
                    break;
                case R.id.tv_home_liangche:
                    break;
                case R.id.ll_home_money_center:
                    break;
                case R.id.tv_home_scroll:
                    break;
                case R.id.tv_home_zhanghugongshi:
                    intent = new Intent(getContext(), AccountFormulaActivity.class);
                    break;
                case R.id.tv_home_huzhushijian:
                    intent = new Intent(getContext(), MutualEventsActivity.class);
                    break;
                case R.id.tv_home_yonghuliebiao:
                    intent = new Intent(getContext(), UserListActivity.class);
                    break;
                case R.id.tv_home_remenshijian:
                    intent = new Intent(getContext(), PopularActivity.class);

                    break;
                case R.id.tv_home_shenqinghuzhu:
                    intent = new Intent(getContext(), JoinActivity.class);
                    intent.putExtra("je",je);
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

    private void N_home() {
        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_home)
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
                            final String msg = jsonObject.getString("msg");
                            final int code = jsonObject.getInt("code");
                            final JSONObject finalJsonObject = jsonObject;
                            mHandler.post(new Runnable() {
                               @Override
                               public void run() {
                                   if (code == 0) {

                                       String data1 = null;
                                       try {
                                           data1 = finalJsonObject.getString("data");
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                       JSONObject jsonObject1 = null;
                                       try {
                                           jsonObject1 = new JSONObject(data1);
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }


                                       try {
                                           je = jsonObject1.getDouble("money");
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                       int  cl= 0;
                                       try {
                                           cl = jsonObject1.getInt("carJoinNum");
                                       } catch (JSONException e) {
                                           e.printStackTrace();
                                       }
                                       mTvHomeMoney.setText("￥"+DataTool.getAmountValue(je));
                                       mTvHomeLiangche.setText("已有"+DataTool.getIntValue(""+cl)+"辆车加入");




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
