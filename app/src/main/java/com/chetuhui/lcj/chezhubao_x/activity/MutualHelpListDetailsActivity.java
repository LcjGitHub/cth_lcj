package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.EventDetailsBean;
import com.chetuhui.lcj.chezhubao_x.model.MhldBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MutualHelpListDetailsActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarMhld;
    /**
     * 11
     */
    private TextView mTvMhldTitle;
    /**
     * 11
     */
    private TextView mTvMhldDdh;
    private ImageView mIvMhld;
    /**  */
    private TextView mTvMhldCishu;
    /**  */
    private TextView mTvMhldShangxian;
    /**  */
    private TextView mTvMhldFeiyong;
    /**  */
    private TextView mTvMhldGoumai;
    /**  */
    private TextView mTvMhldHometime;
    /**
     * 保障结束时间：
     */
    private TextView mTvMhldEndtime;
    /**  */
    private TextView mTvMhldQixian;
    /**
     * 查看
     */
    private TextView mTvMhldChakan;
    /**  */
    private TextView mTvMhldCph;
    private  String coed;
    private MyHandler mHandler=new MyHandler(MutualHelpListDetailsActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<MutualHelpListDetailsActivity> activityWeakReference;

            public MyHandler(MutualHelpListDetailsActivity activity) {
                activityWeakReference = new WeakReference<MutualHelpListDetailsActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                MutualHelpListDetailsActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_help_list);
        coed=getIntent().getStringExtra("code");
        initView();
        N_findMutualBillDetail(coed);
    }

    private void initView() {
        mTitlebarMhld = (CommonTitleBar) findViewById(R.id.titlebar_mhld);
        mTitlebarMhld.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }

            }
        });

        mTvMhldTitle = (TextView) findViewById(R.id.tv_mhld_title);
        mTvMhldDdh = (TextView) findViewById(R.id.tv_mhld_ddh);
        mIvMhld = (ImageView) findViewById(R.id.iv_mhld);
        mTvMhldCishu = (TextView) findViewById(R.id.tv_mhld_cishu);
        mTvMhldShangxian = (TextView) findViewById(R.id.tv_mhld_shangxian);
        mTvMhldFeiyong = (TextView) findViewById(R.id.tv_mhld_feiyong);
        mTvMhldGoumai = (TextView) findViewById(R.id.tv_mhld_goumai);
        mTvMhldHometime = (TextView) findViewById(R.id.tv_mhld_hometime);
        mTvMhldEndtime = (TextView) findViewById(R.id.tv_mhld_endtime);
        mTvMhldQixian = (TextView) findViewById(R.id.tv_mhld_qixian);
        mTvMhldChakan = (TextView) findViewById(R.id.tv_mhld_chakan);
        mTvMhldChakan.setOnClickListener(this);
        mTvMhldCph = (TextView) findViewById(R.id.tv_mhld_cph);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_mhld_chakan:
                startActivity( new Intent(MutualHelpListDetailsActivity.this,MutualRecordActivity.class));


                break;
        }
    }
    private void N_findMutualBillDetail(String code) {

        String s_token = SPTool.getString(MutualHelpListDetailsActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(MutualHelpListDetailsActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findMutualBillDetail+"?billCode="+code)
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
                                        MhldBean bean=new Gson().fromJson(data, MhldBean.class);
                                        mTvMhldTitle.setText(bean.getData().getProgramName());
                                        mTvMhldDdh.setText(bean.getData().getBillCode());
                                        //0：待生效 1：保障中 2：已用完 3：已失效
                                        if (bean.getData().getBillState()==0){
                                            ShowImageUtils.showImageView(MutualHelpListDetailsActivity.this,R.drawable.hzdxq_dsx,mIvMhld);
                                        }else  if (bean.getData().getBillState()==1){
                                            ShowImageUtils.showImageView(MutualHelpListDetailsActivity.this,R.drawable.hzdxq_bzz,mIvMhld);
                                        }else  if (bean.getData().getBillState()==2){
                                            ShowImageUtils.showImageView(MutualHelpListDetailsActivity.this,R.drawable.hzdxq_yyw,mIvMhld);
                                        }else  if (bean.getData().getBillState()==3){
                                            ShowImageUtils.showImageView(MutualHelpListDetailsActivity.this,R.drawable.hzdxq_ysx,mIvMhld);
                                        }else  if (bean.getData().getBillState()==4){
                                            ShowImageUtils.showImageView(MutualHelpListDetailsActivity.this,R.drawable.hzdxq_yqx,mIvMhld);
                                        }
                                        mTvMhldCishu.setText(""+bean.getData().getMutualSize());
                                        mTvMhldShangxian.setText(""+bean.getData().getLimitMoney()+"元");
                                        mTvMhldFeiyong.setText(""+bean.getData().getMpMoney()+"元");
                                        mTvMhldGoumai.setText(bean.getData().getBuyTime());
                                        mTvMhldHometime.setText(bean.getData().getEffectiveTime());
                                        mTvMhldEndtime.setText(bean.getData().getEndTime());
                                        mTvMhldQixian.setText(""+bean.getData().getProgramTime()+"月");
                                        mTvMhldCph.setText(bean.getData().getCarNum());








                                        //BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(MutualHelpListDetailsActivity.this, "token");
                                        startActivity(new Intent(MutualHelpListDetailsActivity.this, LoginActivity.class));
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
