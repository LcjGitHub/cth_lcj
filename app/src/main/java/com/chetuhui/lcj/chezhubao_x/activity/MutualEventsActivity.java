package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.DataGenerator;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.tabview.CustomTabViewBottom;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MutualEventsActivity extends ActivityBase implements CustomTabViewBottom.OnTabCheckListener {

    private CommonTitleBar mTitlebarMutualevent;
    /**
     * 事件公式
     */
    private TextView mTvMutualeventSjgs;
    private RelativeLayout mRlMutualevent;
    private CustomTabViewBottom mCustomTabMutualevent;
    private FrameLayout mMutualeventContainer;
    private Fragment[] mFragmensts;
    /**
     * 公式中\n15件
     */
    private TextView mTvMutualeventGsz;
    /**
     * 已互助\n15件
     */
    private TextView mTvMutualeventYhz;
    private MyHandler mHandler=new MyHandler(MutualEventsActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<MutualEventsActivity> activityWeakReference;

            public MyHandler(MutualEventsActivity activity) {
                activityWeakReference = new WeakReference<MutualEventsActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                MutualEventsActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_events);
        mFragmensts = DataGenerator.getFragments_mutual();
        initView();
        getN_mutualAidEventNum();
    }

    private void initView() {
        mTitlebarMutualevent = (CommonTitleBar) findViewById(R.id.titlebar_mutualevent);
        mTitlebarMutualevent.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }

            }
        });


        mTvMutualeventSjgs = (TextView) findViewById(R.id.tv_mutualevent_sjgs);
        mRlMutualevent = (RelativeLayout) findViewById(R.id.rl_mutualevent);
        mCustomTabMutualevent = (CustomTabViewBottom) findViewById(R.id.custom_tab_mutualevent);
        mMutualeventContainer = (FrameLayout) findViewById(R.id.mutualevent_container);

        CustomTabViewBottom.Tab tabHome = new CustomTabViewBottom.Tab().setText("公示中")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMutualevent.addTab(tabHome);
        CustomTabViewBottom.Tab tabDis = new CustomTabViewBottom.Tab().setText("已互助")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMutualevent.addTab(tabDis);
        mTvMutualeventGsz = (TextView) findViewById(R.id.tv_mutualevent_gsz);
        mTvMutualeventYhz = (TextView) findViewById(R.id.tv_mutualevent_yhz);
        mCustomTabMutualevent.setOnTabCheckListener(this);

        mCustomTabMutualevent.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(View v, int position) {
        onTabItemSelected(position);
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            default:
                break;
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;


        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mutualevent_container, fragment).commit();
        }
    }

    private void getN_mutualAidEventNum() {

        String s_token = SPTool.getString(MutualEventsActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(MutualEventsActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_mutualAidEventNum)
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
                                            JSONObject jsonObject1 = new JSONObject(data1);

                                            int help = jsonObject1.getInt("help");
                                            int pub = jsonObject1.getInt("public");
                                            mTvMutualeventGsz.setText("公示中\n"+pub+"件");
                                            mTvMutualeventYhz.setText("已互助\n"+help+"件");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }



                                        //BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(MutualEventsActivity.this, "token");
                                        startActivity(new Intent(MutualEventsActivity.this, LoginActivity.class));
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
