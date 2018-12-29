package com.chetuhui.lcj.chezhubao_x.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chetuhui.lcj.chezhubao_x.PayResult;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.FananAdapter;

import com.chetuhui.lcj.chezhubao_x.model.FananBean;
import com.chetuhui.lcj.chezhubao_x.model.WxpayBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.Constants;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.DeviceTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfirmActivity extends ActivityBase implements View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;
    private CommonTitleBar mTitlebarConfirm;
    /**
     * 去支付
     */
    private SuperTextView mTvConfirmQuzhifu;

    private List<FananBean.DataBean> mBeanList = new ArrayList<>();
    private FananAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    /**
     * 99元初级互助单
     */
    private TextView mTvConfirmLeibie;
    /**
     * 0辆
     */
    private TextView mTvConfirmShuliang;
    /**
     * ￥00.00
     */
    private TextView mTvConfirmDanjia;
    /**
     * ￥00.00
     */
    private TextView mTvConfirmHongbao;
    /**
     * ￥00.00
     */
    private TextView mTvConfirmZongji;
    private  BaseDialog rxDialog;
    private int car_size=0,or_shenhe=0,qy_id=0;
    private  String carjosn,au_bh,auditor_name,auditor_ph,auditor_xxdz,hzbhfa,qr_data,zfb_data;
    private  String fanan_jine,fanan_name,fanan_code;
    private String hb,hb_id;
    private String ticketId="0";
    private double zj,xz_money;
    private MyHandler mHandler1=new MyHandler(ConfirmActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<ConfirmActivity> activityWeakReference;

            public MyHandler(ConfirmActivity activity) {
                activityWeakReference = new WeakReference<ConfirmActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                ConfirmActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        car_size=SPTool.getInt(ConfirmActivity.this,"car_size");
        fanan_jine=SPTool.getString(ConfirmActivity.this,"fanan_jine");
        fanan_name=SPTool.getString(ConfirmActivity.this,"fanan_name");
        fanan_code=SPTool.getString(ConfirmActivity.this,"fanan_code");
        hzbhfa=fanan_code;

        initView();
        getN_findAll();
    }

    private void initView() {
        mTitlebarConfirm = (CommonTitleBar) findViewById(R.id.titlebar_confirm);
        mTitlebarConfirm.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

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


        mTvConfirmQuzhifu = (SuperTextView) findViewById(R.id.tv_confirm_quzhifu);
        mTvConfirmQuzhifu.setOnClickListener(this);
        mTvConfirmLeibie = (TextView) findViewById(R.id.tv_confirm_leibie);
        mTvConfirmLeibie.setOnClickListener(this);
        mTvConfirmShuliang = (TextView) findViewById(R.id.tv_confirm_shuliang);
        mTvConfirmShuliang.setOnClickListener(this);
        mTvConfirmDanjia = (TextView) findViewById(R.id.tv_confirm_danjia);
        mTvConfirmDanjia.setOnClickListener(this);
        mTvConfirmHongbao = (TextView) findViewById(R.id.tv_confirm_hongbao);
        mTvConfirmHongbao.setOnClickListener(this);
        mTvConfirmZongji = (TextView) findViewById(R.id.tv_confirm_zongji);
        mTvConfirmZongji.setOnClickListener(this);
        mTvConfirmShuliang.setText(""+car_size+"辆");
        mTvConfirmLeibie.setText(""+fanan_name);
        mTvConfirmDanjia.setText("￥"+fanan_jine);
        zj =Double.parseDouble(fanan_jine)*car_size;
        mTvConfirmZongji.setText("￥"+zj);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.titlebar_confirm:
                break;
            case R.id.tv_confirm_quzhifu:
                carjosn=SPTool.getString(ConfirmActivity.this,"car_jsonString");
                au_bh=SPTool.getString(ConfirmActivity.this,"au_bh");
                auditor_name=SPTool.getString(ConfirmActivity.this,"auditor_name");
                auditor_ph=SPTool.getString(ConfirmActivity.this,"auditor_ph");
                auditor_xxdz=SPTool.getString(ConfirmActivity.this,"auditor_xxdz");
                or_shenhe=SPTool.getInt(ConfirmActivity.this,"or_shenhe");
                qy_id=SPTool.getInt(ConfirmActivity.this,"qy_id");

                if (or_shenhe==1){
                    getN_confirmMutualList(ticketId,carjosn,hzbhfa,""+or_shenhe,auditor_name,auditor_ph,auditor_xxdz,""+qy_id,"");
                }else if (or_shenhe==2){
                    getN_confirmMutualList(ticketId,carjosn,hzbhfa,""+or_shenhe,"","","","",au_bh);

                }




                break;
            case R.id.tv_confirm_leibie:
                showFanan(ConfirmActivity.this);
                break;
            case R.id.tv_confirm_shuliang:
                break;
            case R.id.tv_confirm_danjia:
                break;
            case R.id.tv_confirm_hongbao:
                Intent  intent =new Intent(mContext,ChooseRedEnvelopeActivity.class);
                intent.putExtra("bh_code",""+hzbhfa);
                startActivityForResult(intent, 1);//带请求码打开activity (请求码自定 这里设为1


                break;
            case R.id.tv_confirm_zongji:
                break;
        }
    }

    public  void showView(Context context) {
        final BaseDialog viewDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pay_show, null);
        final TextView wx = view.findViewById(R.id.tv_dg_pay_wx);
        TextView zfb = view.findViewById(R.id.tv_dg_pay_zfb);
        TextView cancel = view.findViewById(R.id.tv_dg_pay_cancel);
        wx.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                String getip= DeviceTool.getIP(mContext);
                Log.d("ConfirmActivity", getip);

                if (isWXAppInstalledAndSupported()){
                    Log.d("ConfirmActivity", "111111111111111111");
                    getN_weChatFirst(""+zj,qr_data,getip);

                }else {
                    Log.d("ConfirmActivity", "00000000000000000");
//
                    BaseToast.error("请先下载微信");
                }

            }
        });
        zfb.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                getN_alipayOrders(""+zj,qr_data);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDialog.cancel();
            }
        });


        viewDialog.setContentView(view);
        viewDialog.getLayoutParams();
        viewDialog.mLayoutParams.gravity = Gravity.BOTTOM;

        viewDialog.show();
        viewDialog.setFullScreen();
    }

    public void showFanan(Context context) {
          rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fanan_show, null);
        recyclerView = view.findViewById(R.id.rv_dg_fanan);
        initRecylerView();
        ImageView cancel = view.findViewById(R.id.iv_dg_fanan);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });


        rxDialog.setContentView(view);
        rxDialog.getLayoutParams();
        rxDialog.mLayoutParams.gravity = Gravity.BOTTOM;

        rxDialog.show();
        rxDialog.setFullScreen();
    }

    private void getN_findAll() {

        String s_token = SPTool.getString(ConfirmActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(ConfirmActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findAll)
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
                            mHandler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        mBeanList.clear();
                                        FananBean bean= new Gson().fromJson(data, FananBean.class);
                                        mBeanList=bean.getData();

                                        //BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(ConfirmActivity.this, "token");
                                        startActivity(new Intent(ConfirmActivity.this, LoginActivity.class));
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

        mAdapter = new FananAdapter(R.layout.item_fanan, mBeanList);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        //添加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                rxDialog.cancel();
                mTvConfirmLeibie.setText(mBeanList.get(position).getProgramName());
                mTvConfirmDanjia.setText("￥"+mBeanList.get(position).getMpMoney());
                xz_money=mBeanList.get(position).getMpMoney();
                zj =(xz_money*car_size);
                mTvConfirmZongji.setText("￥"+zj);
                hzbhfa=mBeanList.get(position).getProgramCode();

                Log.d("ConfirmActivity", "zj:" + zj);

            }
        });



    }

    private void getN_confirmMutualList(String ticketId_id,String carjosn, String hzbhfa, String or_shenhe, String auditor_name, String auditor_ph, String auditor_xxdz, String qy_id, String au_bh) {

        String s_token = SPTool.getString(ConfirmActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(ConfirmActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_confirmMutualList)
                .tag(this)
                .headers("token", s_token)
                .params("carNumList",""+carjosn)
                .params("programCode",""+hzbhfa)
                .params("review_type",""+or_shenhe)
                .params("contact",""+auditor_name)
                .params("contactPhone",""+auditor_ph)
                .params("areaId",""+qy_id)
                .params("address",""+auditor_xxdz)
                .params("businessCode",""+au_bh)
                .params("ticketId",""+ticketId_id)

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
                            mHandler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        try {
                                            qr_data= finalJsonObject.getString("data");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        BaseToast.success(msg);
                                        showView(ConfirmActivity.this);


                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(ConfirmActivity.this, "token");
                                        startActivity(new Intent(ConfirmActivity.this, LoginActivity.class));
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
    private boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(Constants.WECHAT_APPID);

        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled();

        return sIsWXAppInstalledAndSupported;
    }
    private void getN_weChatFirst(String money,String billList,String ip) {

        String s_token = SPTool.getString(ConfirmActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(ConfirmActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_weChatFirst)
                .tag(this)
                .headers("token", s_token)
                .params("money",""+money)
                .params("ip",""+ip)
                .params("billList",""+billList)
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
                            final JSONObject finalJsonObject = jsonObject;
                            mHandler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        WxpayBean bean= new Gson().fromJson(data, WxpayBean.class);
//                                        try {
//                                            zfb_data= finalJsonObject.getString("data");
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }


                                        IWXAPI wxAPI = WXAPIFactory.createWXAPI(mContext, null);
                                        wxAPI.registerApp(Constants.WECHAT_APPID);
                                        PayReq req    = new PayReq();
                                        req.appId =bean.getData().getAppid();// json.getString("appid");　　　　//appid
                                        req.partnerId = bean.getData().getPartnerid();//json.getString("partnerid"); //商户号
                                        req.prepayId =bean.getData().getPrepayid(); //json.getString("prepayid"); //预支付交易会话id
                                        req.nonceStr =bean.getData().getNoncestr(); //json.getString("noncestr"); //随机字符串,不超过32位
                                        req.timeStamp = bean.getData().getTimestamp();//json.getString("timestamp");//时间戳
                                        req.packageValue =bean.getData().getPackageX(); //json.getString("package");//扩展字段
                                        req.sign =bean.getData().getSign(); //json.getString("sign");//签名信息MD5加密后的

                                        wxAPI.sendReq(req);

                                        BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(ConfirmActivity.this, "token");
                                        startActivity(new Intent(ConfirmActivity.this, LoginActivity.class));
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

    private void getN_alipayOrders(String money,String billList) {

        String s_token = SPTool.getString(ConfirmActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(ConfirmActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_alipayOrders)
                .tag(this)
                .headers("token", s_token)
                .params("money",""+money)
                .params("billList",""+billList)
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
                            mHandler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        try {
                                            zfb_data= finalJsonObject.getString("data");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        pay(zfb_data);
                                        BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(ConfirmActivity.this, "token");
                                        startActivity(new Intent(ConfirmActivity.this, LoginActivity.class));
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
    private void pay(String zfb_data){
        final String orderInfo = zfb_data;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ConfirmActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        startActivity(new Intent(ConfirmActivity.this,PaySuccessActivity.class));
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        BaseToast.error("支付失败，请重新支付");
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }

                default:
                    break;
            }
        };
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){  //根据请求码可处理不同活动返回的数据
            case 1: //返回的请求码
                if (data!=null){
                    //操作
                    hb = data.getExtras().getString("hb");
                    hb_id = data.getExtras().getString("hb_id");
                    if (hb.equals("0.00")){
                        zj=   Double.parseDouble(fanan_jine)*car_size;
                        ticketId="0";
                    }else {
                        zj=(zj-Double.parseDouble(hb));
                        if (zj<0){
                            zj=0.10;
                        }
                        ticketId=hb_id;
                    }
                    mTvConfirmHongbao.setText("￥"+hb);
                    mTvConfirmZongji.setText(""+zj);
                }


                break;
        }
    }



}
