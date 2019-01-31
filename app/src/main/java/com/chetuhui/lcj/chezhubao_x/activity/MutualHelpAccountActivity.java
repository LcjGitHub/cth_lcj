package com.chetuhui.lcj.chezhubao_x.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.PayResult;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.FananAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.MhaAdapter;
import com.chetuhui.lcj.chezhubao_x.model.FananBean;
import com.chetuhui.lcj.chezhubao_x.model.MhaBean;
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
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogEditSureCancel;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogEditSureCancelYuan;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSure;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MutualHelpAccountActivity extends ActivityBase {

    private CommonTitleBar mTitlebarMha;
    /**
     * 请输入车牌号搜索
     */
    private EditText mEtSelectSousuo;
    /**
     * 互助账户余额：0.00元
     */
    private TextView mTvMhaYue;
    /**
     * 分摊：0.00元
     */
    private TextView mTvMhaFentan;
    private RecyclerView mRvMha;
    private ImageView iv_mha_sqt;
    private TextView tv_sswg;
    private boolean isss=false;
    private List<MhaBean.DataBean.MubillVoListBean> mBeanList=new ArrayList<>();
    private MhaAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private DialogEditSureCancelYuan mDialog;
    private String zfb_data;
    private static final int SDK_PAY_FLAG = 1;

    private MyHandler mHandler1=new MyHandler(MutualHelpAccountActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<MutualHelpAccountActivity> activityWeakReference;

            public MyHandler(MutualHelpAccountActivity activity) {
                activityWeakReference = new WeakReference<MutualHelpAccountActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                MutualHelpAccountActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_help_account);
        initView();
        isss=false;
        N_mutualHelpAccountList("");
    }

    private void initView() {
        mTitlebarMha = (CommonTitleBar) findViewById(R.id.titlebar_mha);
        mTitlebarMha.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {

                        finish();
                }
                if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {

                  startActivity( new Intent( mContext ,AccountDetailsActivity.class));
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

        mEtSelectSousuo = (EditText) findViewById(R.id.et_select_sousuo);
        mTvMhaYue = (TextView) findViewById(R.id.tv_mha_yue);
        mTvMhaFentan = (TextView) findViewById(R.id.tv_mha_fentan);
        iv_mha_sqt = (ImageView) findViewById(R.id.iv_mha_sqt);
        tv_sswg = (TextView) findViewById(R.id.tv_sswg);
        mRvMha = (RecyclerView) findViewById(R.id.rv_mha);
        mEtSelectSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i==0||i==3)&&keyEvent!=null){
                    if (!DataTool.isNullString(mEtSelectSousuo.getText().toString())){
                        isss=true;
                        N_mutualHelpAccountList(mEtSelectSousuo.getText().toString());

                    }else {
                        BaseToast.success("搜索为空");
                    }

                    return true;
                }

                return false;
            }
        });

    }


    private void N_mutualHelpAccountList(String carNum) {


        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(mContext, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_mutualHelpAccountList)
                .tag(this)
                .headers("token", s_token)
                .params("carNum",""+carNum)

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
                                        MhaBean bean= new Gson().fromJson(data, MhaBean.class);
                                        mTvMhaYue.setText("账户余额："+bean.getData().getMutualAccountBalance()+"元");
                                        mTvMhaFentan.setText("分摊："+bean.getData().getApportion()+"元");
                                        mBeanList=bean.getData().getMubillVoList();
                                        initRecylerView();
                                        if (mBeanList.size()==0){
                                            if (isss) {
                                                iv_mha_sqt.setVisibility(View.GONE);
                                                tv_sswg.setVisibility(View.VISIBLE);

                                            }else {
                                                iv_mha_sqt.setVisibility(View.VISIBLE);
                                                tv_sswg.setVisibility(View.GONE);
                                            }

                                        }else {
                                            iv_mha_sqt.setVisibility(View.GONE);
                                        }


                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(mContext, "token");
                                        startActivity(new Intent(mContext, LoginActivity.class));
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

        mAdapter = new MhaAdapter(R.layout.item_mha, mBeanList);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        //添加动画
        mRvMha.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvMha.setLayoutManager(mLayoutManager);
        mRvMha.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {


            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                showview(mBeanList.get(position).getLimitMoney(),""+mBeanList.get(position).getBillCode());
            }
        });


    }


    private void showview(final double limitMoney, final String code ) {


        mDialog = new DialogEditSureCancelYuan(mContext, R.style.PushUpInDialogThem);

        mDialog.setTitle("请输入金额");

        mDialog.setCancelListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                mDialog.cancel();
            }
        });

        mDialog.setSureListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                if (!DataTool.isNullString(mDialog.getEditText().getText().toString())) {
                    if (Double.parseDouble(mDialog.getEditText().getText().toString())>limitMoney){

                        shouck(mContext,"根据您的互助方案,\n您最多可充值"+limitMoney+"元");
                    }else {
                        mDialog.cancel();
                        showView(mContext ,""+code,mDialog.getEditText().getText().toString());

                    }


                } else {
                    BaseToast.error("输入不能为空，请检查");
                }

            }
        });
        mDialog.setCancelable(false);
        mDialog.show();
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

    public  void showView(Context context, final String code, final String money) {
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
                    viewDialog.cancel();
                    N_confirmPayment("1",code,money);

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
                viewDialog.cancel();
                N_confirmPayment("2",code,money);
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

    private boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp(Constants.WECHAT_APPID);

        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled();

        return sIsWXAppInstalledAndSupported;
    }
    private void N_confirmPayment(final String outTradeType, String billCode, final String money) {

        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(mContext, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_confirmPayment)
                .tag(this)
                .headers("token", s_token)
                .params("outTradeType",""+outTradeType)
                .params("billCode",""+billCode)
                .params("money",""+money)
                .params("ip",""+DeviceTool.getIP(mContext))

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;

                        try {
                            jsonObject = new JSONObject(data);
                            final String data_d = jsonObject.getString("data");
                            final String msg = jsonObject.getString("msg");
                            final int code = jsonObject.getInt("code");
                            
                            mHandler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
//                                        BaseToast.success("充值成功");
//                                        isss=false;
//                                        N_mutualHelpAccountList("");
                                        if (outTradeType.equals("1")){


                                            if (isWXAppInstalledAndSupported()){

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

                                            }else {
                                                Log.d("ConfirmActivity", "00000000000000000");
//
                                                BaseToast.error("请先下载微信");
                                            }


                                        }else if (outTradeType.equals("2")){

                                            pay(data_d);

                                        }


                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(mContext, "token");
                                        startActivity(new Intent(mContext, LoginActivity.class));
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

        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(mContext, "获取token失败", Toast.LENGTH_SHORT).show();
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
                                        SPTool.remove(mContext, "token");
                                        startActivity(new Intent(mContext, LoginActivity.class));
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
                PayTask alipay = new PayTask(mContext);
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
                        startActivity(new Intent(mContext,PaySuccessActivity.class));
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        BaseToast.error("充值失败，请重新充值");
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
    private void getN_weChatFirst(String money,String billList,String ip) {

        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(mContext, "获取token失败", Toast.LENGTH_SHORT).show();
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
                                        SPTool.remove(mContext, "token");
                                        startActivity(new Intent(mContext, LoginActivity.class));
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
