package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.FananBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.Constants;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.ImageTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.TextTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSure;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

public class JoinActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarJoin;
    /**
     * ￥1234567
     */
    private TextView mTvJoinJijinchijine;
    private RelativeLayout mRlJoinR1;
    /**  */
    private TextView mTvJoinFangan99;
    /**  */
    private TextView mTvJoinFangan199;
    /**  */
    private TextView mTvJoinFangan399;
    /**  */
    private TextView mTvJoinFangan599;
    /**  */
    private TextView mTvJoinFangan799;
    /**
     * 99/年
     */
    private SuperTextView mTvJoinXuanze99;
    /**
     * 199/年
     */
    private SuperTextView mTvJoinXuanze199;
    /**
     * 399/年
     */
    private SuperTextView mTvJoinXuanze399;
    /**
     * 599/年
     */
    private SuperTextView mTvJoinXuanze599;
    /**
     * 799/年
     */
    private SuperTextView mTvJoinXuanze799;
    private CheckBox mCbJoinTongyi;
    /**
     * 《互助平台须知》
     */
    private TextView mTvJoinXuzhi;
    /**
     * 《平台条款》
     */
    private TextView mTvJoinTiaokuan;
    /**
     * 《用户协议》
     */
    private TextView mTvJoinXieyi;

    /**
     * ￥99
     */
    private TextView mTvJoinYixuanjine;
    /**
     * 我要加入
     */

    private SuperTextView mTvJoinWoyaojiaru;
    private RelativeLayout mRlJoinR2;
    private String con, title;
    private String fanan_name, fanan_code;
    private double fanan_jine;
    private boolean isonclick_cb = true;
    private List<FananBean.DataBean> mBeanList = new ArrayList<>();
    private double je;

    private IWXAPI wxApi;
    private MyHandler mHandler = new MyHandler(JoinActivity.this);
    private ImageView mIvJoinContent1;
    private ImageView mIvJoinContent2;
    private ImageView mIvJoinContent3;
    private ImageView mIvJoinContent4;
    private ImageView mIvJoinContent5;
    private ImageView mIvJoinContent6;
    private TextView tv_yue;

    private static class MyHandler extends Handler {
        private WeakReference<JoinActivity> activityWeakReference;

        public MyHandler(JoinActivity activity) {
            activityWeakReference = new WeakReference<JoinActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            JoinActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        je=getIntent().getDoubleExtra("je",0);
        initView();
        getN_findAll();
    }

    private void initView() {
        mTitlebarJoin = (CommonTitleBar) findViewById(R.id.titlebar_join);
        mTitlebarJoin.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                } else if (action == CommonTitleBar.ACTION_RIGHT_BUTTON) {
                    showfx(mContext);

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

        mTvJoinJijinchijine = (TextView) findViewById(R.id.tv_join_jijinchijine);
        mTvJoinJijinchijine.setText("￥"+DataTool.getAmountValue(je));
        mTvJoinJijinchijine.setOnClickListener(this);
        mRlJoinR1 = (RelativeLayout) findViewById(R.id.rl_join_r1);
        mTvJoinFangan99 = (TextView) findViewById(R.id.tv_join_fangan99);
        mTvJoinFangan99.setOnClickListener(this);
        mTvJoinFangan199 = (TextView) findViewById(R.id.tv_join_fangan199);
        mTvJoinFangan199.setOnClickListener(this);
        mTvJoinFangan399 = (TextView) findViewById(R.id.tv_join_fangan399);
        mTvJoinFangan399.setOnClickListener(this);
        mTvJoinFangan599 = (TextView) findViewById(R.id.tv_join_fangan599);
        mTvJoinFangan599.setOnClickListener(this);
        mTvJoinFangan799 = (TextView) findViewById(R.id.tv_join_fangan799);
        mTvJoinFangan799.setOnClickListener(this);
        mTvJoinXuanze99 = (SuperTextView) findViewById(R.id.tv_join_xuanze99);
        mTvJoinXuanze99.setOnClickListener(this);
        mTvJoinXuanze199 = (SuperTextView) findViewById(R.id.tv_join_xuanze199);
        mTvJoinXuanze199.setOnClickListener(this);
        mTvJoinXuanze399 = (SuperTextView) findViewById(R.id.tv_join_xuanze399);
        mTvJoinXuanze399.setOnClickListener(this);
        mTvJoinXuanze599 = (SuperTextView) findViewById(R.id.tv_join_xuanze599);
        mTvJoinXuanze599.setOnClickListener(this);
        mTvJoinXuanze799 = (SuperTextView) findViewById(R.id.tv_join_xuanze799);
        mTvJoinXuanze799.setOnClickListener(this);
        mCbJoinTongyi = (CheckBox) findViewById(R.id.cb_join_tongyi);
        mCbJoinTongyi.setOnClickListener(this);
        mTvJoinXuzhi = (TextView) findViewById(R.id.tv_join_xuzhi);
        mTvJoinXuzhi.setOnClickListener(this);
        mTvJoinTiaokuan = (TextView) findViewById(R.id.tv_join_tiaokuan);
        mTvJoinTiaokuan.setOnClickListener(this);
        mTvJoinXieyi = (TextView) findViewById(R.id.tv_join_xieyi);
        mTvJoinXieyi.setOnClickListener(this);
        mIvJoinContent1 = (ImageView) findViewById(R.id.iv_join_content1);
        mIvJoinContent2 = (ImageView) findViewById(R.id.iv_join_content2);
        mIvJoinContent3 = (ImageView) findViewById(R.id.iv_join_content3);
        mIvJoinContent4 = (ImageView) findViewById(R.id.iv_join_content4);
        mIvJoinContent5 = (ImageView) findViewById(R.id.iv_join_content5);
        mIvJoinContent6 = (ImageView) findViewById(R.id.iv_join_content6);
        mTvJoinYixuanjine = (TextView) findViewById(R.id.tv_join_yixuanjine);
        tv_yue = (TextView) findViewById(R.id.tv_yue);
        mTvJoinYixuanjine.setOnClickListener(this);
        mTvJoinWoyaojiaru = (SuperTextView) findViewById(R.id.tv_join_woyaojiaru);
        mTvJoinWoyaojiaru.setOnClickListener(this);
        mRlJoinR2 = (RelativeLayout) findViewById(R.id.rl_join_r2);
        mCbJoinTongyi.setChecked(true);
        mCbJoinTongyi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isonclick_cb = true;

                } else {
                    isonclick_cb = false;
                }


            }
        });
        getDefault();



    }

    public void showfx(Context context) {
        final BaseDialog Dialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fenxiang, null);


        ImageView cancel = view.findViewById(R.id.iv_dg_if_fx_cha);
        TextView pyq = view.findViewById(R.id.tv_dg_if_pyq);

        TextView hy = view.findViewById(R.id.tv_dg_if_hy);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.cancel();
            }
        });
        pyq.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                share(true);
            }
        });
        hy.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                share(false);
            }
        });


        Dialog.setContentView(view);
        Dialog.getLayoutParams();
        Dialog.mLayoutParams.gravity = Gravity.CENTER;

        Dialog.show();
        Dialog.setFullScreen();
    }

    private void setText() {
        // 响应点击事件的话必须设置以下属性

        mTvJoinFangan99.setMovementMethod(LinkMovementMethod.getInstance());
        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                .append("99元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
                .append("800元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")

                .into(mTvJoinFangan99);
//        mTvJoinFangan199.setMovementMethod(LinkMovementMethod.getInstance());
//        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
//
//                .append("199元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
//                .append("1000元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")
//
//                .into(mTvJoinFangan199);
//        mTvJoinFangan399.setMovementMethod(LinkMovementMethod.getInstance());
//        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
//
//                .append("399元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
//                .append("1500元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")
//
//                .into(mTvJoinFangan399);
//        mTvJoinFangan599.setMovementMethod(LinkMovementMethod.getInstance());
//        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
//
//                .append("599元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过\n")
//                .append("3000元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")
//
//                .into(mTvJoinFangan599);
//        mTvJoinFangan799.setMovementMethod(LinkMovementMethod.getInstance());
//        TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
//
//                .append("799元/年\n").setForegroundColor(getResources().getColor(R.color.ff73)).append("一年两次，一次不超过")
//                .append("6000元").setForegroundColor(getResources().getColor(R.color.ff73)).append("的维修机会")
//
//                .into(mTvJoinFangan799);

    }

    private void getDefault() {
        mTvJoinXuanze99.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze99.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze99.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze199.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze199.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze199.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze399.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze399.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze399.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze599.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze599.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze599.setStrokeColor(getResources().getColor(R.color.a));

        mTvJoinXuanze799.setSolid(getResources().getColor(R.color.white));
        mTvJoinXuanze799.setTextColor(getResources().getColor(R.color._6));
        mTvJoinXuanze799.setStrokeColor(getResources().getColor(R.color.a));


    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
            switch (v.getId()) {
                default:
                    break;

                case R.id.tv_join_xuanze99:
                    getDefault();
                    mTvJoinXuanze99.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze99.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze99.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥" + mBeanList.get(0).getMpMoney());
                    tv_yue.setText("/" + mBeanList.get(0).getProgramTime()+"月");

                    fanan_name = mBeanList.get(0).getProgramName();
                    fanan_jine = mBeanList.get(0).getMpMoney();
                    fanan_code = mBeanList.get(0).getProgramCode();
                    getimg(0);




                    break;
                case R.id.tv_join_xuanze199:
                    getDefault();
                    mTvJoinXuanze199.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze199.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze199.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥" + mBeanList.get(1).getMpMoney());
                    tv_yue.setText("/" + mBeanList.get(1).getProgramTime()+"月");
                    fanan_name = mBeanList.get(1).getProgramName();
                    fanan_jine = mBeanList.get(1).getMpMoney();
                    fanan_code = mBeanList.get(1).getProgramCode();
                    getimg(1);
                    break;
                case R.id.tv_join_xuanze399:
                    getDefault();
                    mTvJoinXuanze399.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze399.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze399.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥" + mBeanList.get(2).getMpMoney());
                    tv_yue.setText("/" + mBeanList.get(2).getProgramTime()+"月");
                    fanan_name = mBeanList.get(2).getProgramName();
                    fanan_jine = mBeanList.get(2).getMpMoney();
                    fanan_code = mBeanList.get(2).getProgramCode();
                    getimg(2);
                    break;
                case R.id.tv_join_xuanze599:
                    getDefault();
                    mTvJoinXuanze599.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze599.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze599.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥" + mBeanList.get(3).getMpMoney());
                    tv_yue.setText("/" + mBeanList.get(3).getProgramTime()+"月");
                    fanan_name = mBeanList.get(3).getProgramName();
                    fanan_jine = mBeanList.get(3).getMpMoney();
                    fanan_code = mBeanList.get(3).getProgramCode();
                    getimg(3);
                    break;
                case R.id.tv_join_xuanze799:
                    getDefault();
                    mTvJoinXuanze799.setSolid(getResources().getColor(R.color.d6f3));
                    mTvJoinXuanze799.setTextColor(getResources().getColor(R.color.home_button));
                    mTvJoinXuanze799.setStrokeColor(getResources().getColor(R.color.home_button));
                    mTvJoinYixuanjine.setText("￥" + mBeanList.get(4).getMpMoney());
                    tv_yue.setText("/" + mBeanList.get(4).getProgramTime()+"月");
                    fanan_name = mBeanList.get(4).getProgramName();
                    fanan_jine = mBeanList.get(4).getMpMoney();
                    fanan_code = mBeanList.get(4).getProgramCode();
                    getimg(4);
                    break;

                case R.id.tv_join_xuzhi:
                    getN_findClause("1");
                    break;
                case R.id.tv_join_tiaokuan:
                    getN_findClause("2");
                    break;
                case R.id.tv_join_xieyi:
                    getN_findClause("3");
                    break;
                case R.id.tv_join_yixuanjine:
                    break;
                case R.id.tv_join_woyaojiaru:
                    getN_spikeMutualprogram(fanan_code);


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

    private void getimg(int a) {
        ShowImageUtils.showImageViewGone(mContext,mIvJoinContent1,mBeanList.get(a).getImgUrl1());
        ShowImageUtils.showImageViewGone(mContext,mIvJoinContent2,mBeanList.get(a).getImgUrl2());
        ShowImageUtils.showImageViewGone(mContext,mIvJoinContent3,mBeanList.get(a).getImgUrl3());
        ShowImageUtils.showImageViewGone(mContext,mIvJoinContent4,mBeanList.get(a).getImgUrl4());
        ShowImageUtils.showImageViewGone(mContext,mIvJoinContent5,mBeanList.get(a).getImgUrl5());
        ShowImageUtils.showImageViewGone(mContext,mIvJoinContent6,mBeanList.get(a).getImgUrl6());
    }

    private void getN_findAll() {

        String s_token = SPTool.getString(JoinActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(JoinActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
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
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        mBeanList.clear();
                                        FananBean bean = new Gson().fromJson(data, FananBean.class);
                                        mBeanList = bean.getData();
                                        for (int i = 0; i < mBeanList.size(); i++) {
                                            if (i == 0) {
                                                mTvJoinXuanze99.setVisibility(View.VISIBLE);
                                                mTvJoinFangan99.setVisibility(View.VISIBLE);
                                                mTvJoinXuanze99.setText("" + mBeanList.get(i).getMpMoney() + "/年");
                                                mTvJoinFangan99.setMovementMethod(LinkMovementMethod.getInstance());
                                                TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                                                        .append("" + mBeanList.get(i).getMpMoney() + "/"+mBeanList.get(i).getProgramTime()+"月\n").setForegroundColor(getResources().getColor(R.color.ff73)).append(mBeanList.get(i).getDescription())


                                                        .into(mTvJoinFangan99);
//                                                mTvJoinFangan99.setText(""+mBeanList.get(i).getMpMoney()+"/年\n"+mBeanList.get(i).getDescription());
                                                mTvJoinXuanze99.setSolid(getResources().getColor(R.color.d6f3));
                                                mTvJoinXuanze99.setTextColor(getResources().getColor(R.color.home_button));
                                                mTvJoinXuanze99.setStrokeColor(getResources().getColor(R.color.home_button));
                                                mTvJoinYixuanjine.setText("￥" + mBeanList.get(0).getMpMoney());
                                                tv_yue.setText("/" + mBeanList.get(0).getProgramTime()+"月");
                                                fanan_name = mBeanList.get(0).getProgramName();
                                                fanan_jine = mBeanList.get(0).getMpMoney();
                                                fanan_code = mBeanList.get(0).getProgramCode();
                                                getimg(0);
                                            } else if (i == 1) {
                                                mTvJoinXuanze199.setVisibility(View.VISIBLE);
                                                mTvJoinFangan199.setVisibility(View.VISIBLE);
                                                mTvJoinXuanze199.setText("" + mBeanList.get(i).getMpMoney() + "/年");
                                                TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                                                        .append("" + mBeanList.get(i).getMpMoney() + "/"+mBeanList.get(i).getProgramTime()+"月\n").setForegroundColor(getResources().getColor(R.color.ff73)).append(mBeanList.get(i).getDescription())


                                                        .into(mTvJoinFangan199);
//                                                mTvJoinFangan199.setText(""+mBeanList.get(i).getMpMoney()+"/年\n"+mBeanList.get(i).getDescription());
                                            } else if (i == 2) {
                                                mTvJoinXuanze399.setVisibility(View.VISIBLE);
                                                mTvJoinFangan399.setVisibility(View.VISIBLE);
                                                mTvJoinXuanze399.setText("" + mBeanList.get(i).getMpMoney() + "/年");
                                                TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                                                        .append("" + mBeanList.get(i).getMpMoney() + "/"+mBeanList.get(i).getProgramTime()+"月\n").setForegroundColor(getResources().getColor(R.color.ff73)).append(mBeanList.get(i).getDescription())


                                                        .into(mTvJoinFangan399);
//                                                mTvJoinFangan399.setText(""+mBeanList.get(i).getMpMoney()+"/年\n"+mBeanList.get(i).getDescription());
                                            } else if (i == 3) {
                                                mTvJoinXuanze599.setVisibility(View.VISIBLE);
                                                mTvJoinFangan599.setVisibility(View.VISIBLE);
                                                mTvJoinXuanze599.setText("" + mBeanList.get(i).getMpMoney() + "/年");
                                                TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                                                        .append("" + mBeanList.get(i).getMpMoney() + "/"+mBeanList.get(i).getProgramTime()+"月\n").setForegroundColor(getResources().getColor(R.color.ff73)).append(mBeanList.get(i).getDescription())


                                                        .into(mTvJoinFangan599);
//                                                mTvJoinFangan599.setText(""+mBeanList.get(i).getMpMoney()+"/年\n"+mBeanList.get(i).getDescription());
                                            } else if (i == 4) {
                                                mTvJoinXuanze799.setVisibility(View.VISIBLE);
                                                mTvJoinFangan799.setVisibility(View.VISIBLE);
                                                mTvJoinXuanze799.setText("" + mBeanList.get(i).getMpMoney() + "/年");
                                                TextTool.getBuilder("").setBold().setAlign(Layout.Alignment.ALIGN_CENTER)

                                                        .append("" + mBeanList.get(i).getMpMoney() + "/"+mBeanList.get(i).getProgramTime()+"月\n").setForegroundColor(getResources().getColor(R.color.ff73)).append(mBeanList.get(i).getDescription())


                                                        .into(mTvJoinFangan799);
//                                                mTvJoinFangan799.setText(""+mBeanList.get(i).getMpMoney()+"/年\n"+mBeanList.get(i).getDescription());
                                            }


                                        }


//


                                        BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(JoinActivity.this, "token");
                                        startActivity(new Intent(JoinActivity.this, LoginActivity.class));
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

    private void getN_findClause(String type) {
//        String s_token = SPTool.getString(JoinActivity.this, "token");
//        Log.d("CityActivity", s_token);
//
//        if (DataTool.isNullString(s_token)) {
//            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
//1：法律声明 2：车助保服务协议 3：服务合作协议 4：关于我们 5：客服电话
        OkGo.<String>get(NetData.N_findClause + "?type=" + type)
                .tag(this)
//                .headers("token",s_token)


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
//                                BaseToast.success(msg);
                                String d = jsonObject.getString("data");
                                JSONObject jsonObject1 = new JSONObject(d);
                                con = jsonObject1.getString("content");
                                title = jsonObject1.getString("name");
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        showview(JoinActivity.this, con, title);

                                    }
                                });


                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(JoinActivity.this, "token");
                                startActivity(new Intent(JoinActivity.this, LoginActivity.class));
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

    public void showview(final Context mContext, String str, String title) {
        final DialogSure rxDialogSure = new DialogSure(mContext);
        rxDialogSure.getLogoView().setVisibility(View.GONE);

        rxDialogSure.setTitle(title);
        rxDialogSure.setContent(str);
        rxDialogSure.getContentView().setTextSize(20);
        rxDialogSure.getContentView().setTextColor(ContextCompat.getColor(mContext, R.color._3));
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

    public void share(boolean friendsCircle) {
        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WECHAT_APPID, true);
        wxApi.registerApp(Constants.WECHAT_APPID);
        WXWebpageObject webpage = new WXWebpageObject();
//        webpage.webpageUrl = "http://h5.chetuhui.com.cn/Invitefriends/en.html";//分享url
        webpage.webpageUrl = "http://h5.chetuhui.com.cn/applytojoin/";//分享url
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "加入车辆维修互助平台，最高享4000元车辆维修互助金！";
        msg.description = "加入车助宝，从此以后车身剐蹭不再愁，无需自费修车，无需报险，解决你的一切维修难题";

        msg.thumbData = ImageTool.bitmap2Bytes(ImageTool.drawable2Bitmap(mContext.getResources().getDrawable(R.mipmap.fx_log)), Bitmap.CompressFormat.PNG);//封面图片byte数组

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        //发送的目标场景， 可以选择发送到会话 WXSceneSession 或者朋友圈 WXSceneTimeline。 默认发送到会话。
        req.scene = friendsCircle ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        wxApi.sendReq(req);
    }


    private void getN_spikeMutualprogram(String type) {
        String s_token = SPTool.getString(JoinActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_spikeMutualprogram + "?programCode=" + type)
                .tag(this)
                .headers("token",s_token)


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
//                                BaseToast.success(msg);


                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isonclick_cb) {
                                            SPTool.putString(JoinActivity.this, "fanan_name", fanan_name);
                                            SPTool.putString(JoinActivity.this, "fanan_code", fanan_code);
                                            SPTool.putString(JoinActivity.this, "fanan_jine", String.valueOf(fanan_jine));


                                         Intent   intent = new Intent(JoinActivity.this, SelectCarActivity.class);
                                         intent.putExtra("fanan_code",fanan_code);
                                         startActivity(intent);
                                        } else {
                                            BaseToast.error("还没同意协议");
                                        }


                                    }
                                });


                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(JoinActivity.this, "token");
                                startActivity(new Intent(JoinActivity.this, LoginActivity.class));
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
