package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.EventDetailsBean;
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

public class EventDetailsActivity extends ActivityBase {

    private CommonTitleBar mTitlebarEventdate;
    private ImageView mIvEdTouxiang;
    /**  */
    private TextView mTvEdName;
    /**  */
    private TextView mTvEdChepai;
    /**  */
    private TextView mTvEdJrhz;
    /**  */
    private TextView mTvEdWxjg;
    /**  */
    private TextView mTvEdXqTime;
    /**  */
    private TextView mTvEdXqContent;
    private ImageView mIvEdXqImg1;
    private ImageView mIvEdXqImg2;
    private ImageView mIvEdXqImg3;
    private ImageView mIvEdXqImg4;
    /**  */
    private TextView mTvEdDsTiem;
    /**  */
    private TextView mTvEdDsWxdp;
    /**  */
    private TextView mTvEdDsDdh;
    /**  */
    private TextView mTvEdDsDsjg;
    /**  */
    private TextView mTvEdDsSscd;
    /**  */
    private TextView mTvEdDsSsbw;
    private ImageView mIvEdDsImg1;
    private ImageView mIvEdDsImg2;
    private ImageView mIvEdDsImg3;
    /**  */
    private TextView mTvEdJgTime;
    private ImageView mIvEdJgImg1;
    private ImageView mIvEdJgImg2;
    private ImageView mIvEdJgImg3;
    String e_id = null;
    private MyHandler mHandler=new MyHandler(EventDetailsActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<EventDetailsActivity> activityWeakReference;

            public MyHandler(EventDetailsActivity activity) {
                activityWeakReference = new WeakReference<EventDetailsActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                EventDetailsActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        e_id = getIntent().getStringExtra("e_id");
        Log.d("EventDetailsActivity", e_id);
        initView();
        getN_eventDetails(e_id);
    }

    private void initView() {
        mTitlebarEventdate = (CommonTitleBar) findViewById(R.id.titlebar_eventdate);
        mIvEdTouxiang = (ImageView) findViewById(R.id.iv_ed_touxiang);
        mTvEdName = (TextView) findViewById(R.id.tv_ed_name);
        mTvEdChepai = (TextView) findViewById(R.id.tv_ed_chepai);
        mTvEdJrhz = (TextView) findViewById(R.id.tv_ed_jrhz);
        mTvEdWxjg = (TextView) findViewById(R.id.tv_ed_wxjg);
        mTvEdXqTime = (TextView) findViewById(R.id.tv_ed_xq_time);
        mTvEdXqContent = (TextView) findViewById(R.id.tv_ed_xq_content);
        mIvEdXqImg1 = (ImageView) findViewById(R.id.iv_ed_xq_img1);
        mIvEdXqImg2 = (ImageView) findViewById(R.id.iv_ed_xq_img2);
        mIvEdXqImg3 = (ImageView) findViewById(R.id.iv_ed_xq_img3);
        mIvEdXqImg4 = (ImageView) findViewById(R.id.iv_ed_xq_img4);
        mTvEdDsTiem = (TextView) findViewById(R.id.tv_ed_ds_tiem);
        mTvEdDsWxdp = (TextView) findViewById(R.id.tv_ed_ds_wxdp);
        mTvEdDsDdh = (TextView) findViewById(R.id.tv_ed_ds_ddh);
        mTvEdDsDsjg = (TextView) findViewById(R.id.tv_ed_ds_dsjg);
        mTvEdDsSscd = (TextView) findViewById(R.id.tv_ed_ds_sscd);
        mTvEdDsSsbw = (TextView) findViewById(R.id.tv_ed_ds_ssbw);
        mIvEdDsImg1 = (ImageView) findViewById(R.id.iv_ed_ds_img1);
        mIvEdDsImg2 = (ImageView) findViewById(R.id.iv_ed_ds_img2);
        mIvEdDsImg3 = (ImageView) findViewById(R.id.iv_ed_ds_img3);
        mTvEdJgTime = (TextView) findViewById(R.id.tv_ed_jg_time);
        mIvEdJgImg1 = (ImageView) findViewById(R.id.iv_ed_jg_img1);
        mIvEdJgImg2 = (ImageView) findViewById(R.id.iv_ed_jg_img2);
        mIvEdJgImg3 = (ImageView) findViewById(R.id.iv_ed_jg_img3);


    }

    private void getN_eventDetails(String eid) {

        String s_token = SPTool.getString(EventDetailsActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(EventDetailsActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_eventDetails + "?salvationCode=" + eid)
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
                            String msg = jsonObject.getString("msg");
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                final EventDetailsBean eventDetailsBean = new Gson().fromJson(data, EventDetailsBean.class);
                                BaseToast.success(msg);

                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ShowImageUtils.showImageViewToCrop(EventDetailsActivity.this, eventDetailsBean.getData().getHeadimgurl(), mIvEdTouxiang);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getUser_carHeadimg(), mIvEdXqImg1, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getUser_carEndimg(), mIvEdXqImg2, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getUser_carInjuredimg(), mIvEdXqImg3, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getUser_carDetailimg(), mIvEdXqImg4, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getBusiness_carHeadimg(), mIvEdDsImg1, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getBusiness_carEndimg(), mIvEdDsImg2, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getBusiness_carDetailimg(), mIvEdDsImg3, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getFinish_carHeadimg(), mIvEdJgImg1, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getFinish_carEndimg(), mIvEdJgImg2, 10);
                                        ShowImageUtils.showImageViewToCircle(EventDetailsActivity.this, eventDetailsBean.getData().getFinish_carDetailimg(), mIvEdJgImg3, 10);


                                        mTvEdName.setText(eventDetailsBean.getData().getNickName());
                                        mTvEdChepai.setText(eventDetailsBean.getData().getCarNum());
                                        mTvEdWxjg.setText("维修价格：  " + eventDetailsBean.getData().getRescue_money() + "元");
                                        mTvEdDsDsjg.setText(""+ eventDetailsBean.getData().getRescue_money() + "元");
                                        mTvEdJrhz.setText("加入互助：  " + eventDetailsBean.getData().getJoinNum() + "天");
                                        mTvEdXqTime.setText(eventDetailsBean.getData().getApplyTime());
                                        mTvEdXqContent.setText(eventDetailsBean.getData().getDetail());
                                        mTvEdDsTiem.setText(eventDetailsBean.getData().getFixedTime());
                                        mTvEdDsWxdp.setText(eventDetailsBean.getData().getBusinessName());
                                        mTvEdDsDdh.setText(eventDetailsBean.getData().getSalvationCode());
                                        mTvEdDsSscd.setText(eventDetailsBean.getData().getDegreeDamage());
                                        mTvEdDsSsbw.setText(eventDetailsBean.getData().getDamagedPart());
                                        mTvEdJgTime.setText(eventDetailsBean.getData().getFinishTime());


                                    }
                                });


                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(EventDetailsActivity.this, "token");
                                startActivity(new Intent(EventDetailsActivity.this, LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            } else {
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


}
