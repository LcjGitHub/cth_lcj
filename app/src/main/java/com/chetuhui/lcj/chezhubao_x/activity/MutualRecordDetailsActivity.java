package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.MrBean;
import com.chetuhui.lcj.chezhubao_x.model.MrdBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.IntentTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSure;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MutualRecordDetailsActivity extends ActivityBase {

    private CommonTitleBar mTitlebarMrd;
    /**  */
    private TextView mTvMrdHzzt;
    /**  */
    private TextView mTvMrdHzdsqr;
    /**  */
    private TextView mTvMrdHzcph;
    /**  */
    private TextView mTvMrdLxdh;
    /**  */
    private TextView mTvMrdPp;
    /**  */
    private TextView mTvMrdClshwz;
    /**  */
    private TextView mTvMrdYjwxsj;
    /**  */
    private TextView mTvMrdYjwxsjd;
    /**
     * 点击查看
     */
    private TextView mTvMrdDjck;
    /**  */
    private TextView mTvMrdJzdmc;
    /**  */
    private TextView mTvMrdBh;
    /**  */
    private TextView mTvMrdDchzsx;
    /**  */
    private TextView mTvMrdHzdfy;
    /**  */
    private TextView mTvMrdBzkssj;
    /**  */
    private TextView mTvMrdBzjssj;
    /**  */
    private TextView mTvMrdBzqx;
    /**  */
    private TextView mTvMrdSjmc;
    /**  */
    private TextView mTvMrdSjdh;
    /**  */
    private TextView mTvMrdSjdz;
    private ImageView mIvMrdImg1;
    private ImageView mIvMrdImg2;
    private ImageView mIvMrdImg3;
    private ImageView mIvMrdImg4;
    /**
     * 更换维修商家
     */
    private TextView mTvMrdGhwxsj;
    private String code;
    int num=1;
    private MrdBean bean;
    private String miaoshu;
    private boolean isxyg = false;
    private MyHandler mHandler=new MyHandler(MutualRecordDetailsActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<MutualRecordDetailsActivity> activityWeakReference;

            public MyHandler(MutualRecordDetailsActivity activity) {
                activityWeakReference = new WeakReference<MutualRecordDetailsActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                MutualRecordDetailsActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_record_details);
        code = getIntent().getStringExtra("code");
        getN_findMutualRecord(code);
        initView();
    }

    private void initView() {
        mTitlebarMrd = (CommonTitleBar) findViewById(R.id.titlebar_mrd);

        mTitlebarMrd.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
                if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {
                    if (isxyg) {
                        num=2;
                        getdata(num);
                    }


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

        mTvMrdHzzt = (TextView) findViewById(R.id.tv_mrd_hzzt);
        mTvMrdHzdsqr = (TextView) findViewById(R.id.tv_mrd_hzdsqr);
        mTvMrdHzcph = (TextView) findViewById(R.id.tv_mrd_hzcph);
        mTvMrdLxdh = (TextView) findViewById(R.id.tv_mrd_lxdh);
        mTvMrdPp = (TextView) findViewById(R.id.tv_mrd_pp);
        mTvMrdClshwz = (TextView) findViewById(R.id.tv_mrd_clshwz);
        mTvMrdYjwxsj = (TextView) findViewById(R.id.tv_mrd_yjwxsj);
        mTvMrdYjwxsjd = (TextView) findViewById(R.id.tv_mrd_yjwxsjd);
        mTvMrdDjck = (TextView) findViewById(R.id.tv_mrd_djck);
        mTvMrdDjck.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                shouck(MutualRecordDetailsActivity.this,bean.getData().get(num).getDetail());
            }
        });
        mTvMrdJzdmc = (TextView) findViewById(R.id.tv_mrd_jzdmc);
        mTvMrdBh = (TextView) findViewById(R.id.tv_mrd_bh);
        mTvMrdDchzsx = (TextView) findViewById(R.id.tv_mrd_dchzsx);
        mTvMrdHzdfy = (TextView) findViewById(R.id.tv_mrd_hzdfy);
        mTvMrdBzkssj = (TextView) findViewById(R.id.tv_mrd_bzkssj);
        mTvMrdBzjssj = (TextView) findViewById(R.id.tv_mrd_bzjssj);
        mTvMrdBzqx = (TextView) findViewById(R.id.tv_mrd_bzqx);
        mTvMrdSjmc = (TextView) findViewById(R.id.tv_mrd_sjmc);
        mTvMrdSjdh = (TextView) findViewById(R.id.tv_mrd_sjdh);
        mTvMrdSjdz = (TextView) findViewById(R.id.tv_mrd_sjdz);
        mIvMrdImg1 = (ImageView) findViewById(R.id.iv_mrd_img1);
        mIvMrdImg2 = (ImageView) findViewById(R.id.iv_mrd_img2);
        mIvMrdImg3 = (ImageView) findViewById(R.id.iv_mrd_img3);
        mIvMrdImg4 = (ImageView) findViewById(R.id.iv_mrd_img4);
        mTvMrdGhwxsj = (TextView) findViewById(R.id.tv_mrd_ghwxsj);
    }
    public static void shouck(final Context mContext, String str) {
        final DialogSure rxDialogSure = new DialogSure(mContext);
        rxDialogSure.getLogoView().setVisibility(View.GONE);
        rxDialogSure.getTitleView().setVisibility(View.GONE);
        rxDialogSure.setContent(str);
        rxDialogSure.getContentView().setTextSize(20);
        rxDialogSure.getContentView().setTextColor(ContextCompat.getColor(mContext, R.color.black));
        rxDialogSure.getContentView().setGravity(Gravity.CENTER);
        rxDialogSure.setCanceledOnTouchOutside(false);
        rxDialogSure.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSure.cancel();
            }
        });
        rxDialogSure.show();
    }


    private void getN_findMutualRecord(String code) {

        String s_token = SPTool.getString(MutualRecordDetailsActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(MutualRecordDetailsActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findMutualRecord + "?billCode=" + code)
                .tag(this)
                .headers("token", s_token)
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
                            final int code = jsonObject.getInt("code");
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {


                                        bean = new Gson().fromJson(data, MrdBean.class);
                                        if (bean.getData().size() == 1) {
                                            num=1;
                                            getdata(num);
                                        } else if (bean.getData().size() == 2) {
                                            isxyg = true;

                                        }


                                        //BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(MutualRecordDetailsActivity.this, "token");
                                        startActivity(new Intent(MutualRecordDetailsActivity.this, LoginActivity.class));
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

    private void getdata(int i) {

        if (bean.getData().get(i).getState() == 0) {
            mTvMrdHzzt.setText("待确认");
            mTvMrdGhwxsj.setVisibility(View.GONE);
        } else {
            //1：救助完成 2：等待救助 3：商家拒绝
            if (bean.getData().get(i).getHelpState() == 1) {
                mTvMrdHzzt.setText("互助完成");
                mTvMrdGhwxsj.setVisibility(View.GONE);
            } else if (bean.getData().get(i).getHelpState() == 2) {
                mTvMrdHzzt.setText("等待救助");
                mTvMrdGhwxsj.setVisibility(View.VISIBLE);
            } else if (bean.getData().get(i).getHelpState() == 3) {
                mTvMrdHzzt.setText("商家拒绝");
                mTvMrdGhwxsj.setVisibility(View.VISIBLE);
            }
            else if (bean.getData().get(i).getHelpState() == 4) {
                mTvMrdHzzt.setText("已取消");
                mTvMrdGhwxsj.setVisibility(View.VISIBLE);
            }

        }

        mTvMrdHzdsqr.setText("" + bean.getData().get(i).getUserName());
        mTvMrdHzcph.setText("" + bean.getData().get(i).getCarNum());
        mTvMrdLxdh.setText("" + bean.getData().get(i).getPhone());
        mTvMrdPp.setText("" + bean.getData().get(i).getCarBrand());
        mTvMrdClshwz.setText("" + bean.getData().get(i).getDamageLocation());
        mTvMrdYjwxsj.setText("" + bean.getData().get(i).getReadyFixTime());
        mTvMrdYjwxsjd.setText("" + bean.getData().get(i).getReadyFixTimeQuantum());
        mTvMrdYjwxsjd.setText("" + bean.getData().get(i).getReadyFixTimeQuantum());
        miaoshu = bean.getData().get(i).getDetail();
        mTvMrdJzdmc.setText("" + bean.getData().get(i).getProgramName());
        mTvMrdBh.setText("" + bean.getData().get(i).getBillCode());
        mTvMrdDchzsx.setText("" + bean.getData().get(i).getLimitMoney());
        mTvMrdHzdfy.setText("" + bean.getData().get(i).getMpMoney());
        mTvMrdBzkssj.setText("" + bean.getData().get(i).getEffectiveTime());
        mTvMrdBzjssj.setText("" + bean.getData().get(i).getEndTime());
        mTvMrdBzqx.setText("" + bean.getData().get(i).getProgramTime());
        mTvMrdSjmc.setText("" + bean.getData().get(i).getBusinessName());
        mTvMrdSjdh.setText("" + bean.getData().get(i).getBusinessPhone());
        mTvMrdSjdz.setText("" + bean.getData().get(i).getDetailAd());
        mTvMrdSjdz.setText("" + bean.getData().get(i).getDetailAd());
        ShowImageUtils.showImageViewToCircle(MutualRecordDetailsActivity.this, bean.getData().get(i).getCarHeadimg(), mIvMrdImg1, 5);
        ShowImageUtils.showImageViewToCircle(MutualRecordDetailsActivity.this, bean.getData().get(i).getCarEndimg(), mIvMrdImg2, 5);
        ShowImageUtils.showImageViewToCircle(MutualRecordDetailsActivity.this, bean.getData().get(i).getCarInjuredimg(), mIvMrdImg3, 5);
        ShowImageUtils.showImageViewToCircle(MutualRecordDetailsActivity.this, bean.getData().get(i).getCarDetailimg(), mIvMrdImg4, 5);


    }


}
