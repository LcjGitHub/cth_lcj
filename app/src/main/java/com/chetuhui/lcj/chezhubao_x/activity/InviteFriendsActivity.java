package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.IfAdapter;
import com.chetuhui.lcj.chezhubao_x.model.IfBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.Constants;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.ImageTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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

import static com.chetuhui.lcj.chezhubao_x.tool.DataTool.getAmountValue;

public class InviteFriendsActivity extends ActivityBase {

    private CommonTitleBar mTitlebarIf;
    private ImageView mIvIfHdgz;
    private LinearLayout mLlIf1;
    /**
     * 每成功邀请10名好友，你可获得
     */
    private TextView mTvIfMenkan;
    /**
     * ￥5
     */
    private TextView mTvIfJine;
    /**
     * 邀请成功后，你获得5元红包，好友获得5元
     */
    private TextView mTvIfYqchh;
    /**
     * 您已邀请7位好友
     */
    private TextView mTvIfWei;
    private RecyclerView mRvIf;
    private ImageView mIvIfDjqy;
    private IWXAPI wxApi;
    private IfAdapter mAdapter;
    private  BaseDialog rxDialog;
    private String hdgz,us_code;
    private List<IfBean.DataBean.InviterVoListBean> mBeanList=new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private  MyHandler mHandler =new MyHandler(InviteFriendsActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<InviteFriendsActivity> activityWeakReference;

            public MyHandler(InviteFriendsActivity activity) {
                activityWeakReference = new WeakReference<InviteFriendsActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                InviteFriendsActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        us_code=SPTool.getString(mContext,"user_code");
        initView();
        getN_ticketReport();

    }

    private void initView() {
        mTitlebarIf = (CommonTitleBar) findViewById(R.id.titlebar_if);

        mTitlebarIf.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mIvIfHdgz = (ImageView) findViewById(R.id.iv_if_hdgz);
        mLlIf1 = (LinearLayout) findViewById(R.id.ll_if1);
        mTvIfMenkan = (TextView) findViewById(R.id.tv_if_menkan);
        mTvIfJine = (TextView) findViewById(R.id.tv_if_jine);
        mTvIfYqchh = (TextView) findViewById(R.id.tv_if_yqchh);
        mTvIfWei = (TextView) findViewById(R.id.tv_if_wei);
        mRvIf = (RecyclerView) findViewById(R.id.rv_if);
        mIvIfDjqy = (ImageView) findViewById(R.id.iv_if_djqy);
        mIvIfHdgz.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                showhdgz(mContext,hdgz);
            }
        });
        mIvIfDjqy.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                showfx(mContext,us_code);
            }
        });
    }

    public void showhdgz(Context context, String scon) {
        rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_if_hdgz_show, null);



        ImageView cancel = view.findViewById(R.id.iv_dg_if_hdgz_cha);
        TextView con = view.findViewById(R.id.tv_dg_if_hdgz_con);
        con.setText(""+scon);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });


        rxDialog.setContentView(view);
        rxDialog.getLayoutParams();
        rxDialog.mLayoutParams.gravity = Gravity.CENTER;

        rxDialog.show();
        rxDialog.setFullScreen();
    }
    public void showfx(Context context, final String ucode) {
        final BaseDialog  Dialog = new BaseDialog(context);
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
                share(true,ucode);
            }
        });
        hy.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                share(false,ucode);
            }
        });


        Dialog.setContentView(view);
        Dialog.getLayoutParams();
        Dialog.mLayoutParams.gravity = Gravity.CENTER;

        Dialog.show();
        Dialog.setFullScreen();
    }

    private void getN_ticketReport() {
        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_ticketReport)
                .tag(this)
                .headers("token",s_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //BaseToast.success(msg);
                                        mBeanList.clear();
                                        IfBean bean= new Gson().fromJson(data, IfBean.class);
                                        mBeanList=bean.getData().getInviterVoList();
                                        initRecylerView();
                                        mTvIfMenkan.setText("每成功邀请"+bean.getData().getDoorsill()+"名好友，你可获得");
                                        mTvIfJine.setText("￥"+bean.getData().getTicketMoney());
                                        mTvIfYqchh.setText("邀请成功后，你获得"+bean.getData().getTicketMoney()+"元红包，好友获得"+bean.getData().getBeInvitedMoney()+"元");
                                        mTvIfWei.setText("您已邀请"+bean.getData().getInviterNum()+"位好友");
                                        hdgz=bean.getData().getRuleOfActivity();
                                    }
                                });






                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(mContext,"token");
                                startActivity(new Intent(mContext,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
                                BaseToast.success(msg);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }
    private void initRecylerView() {

        mAdapter = new IfAdapter(R.layout.item_if,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvIf.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvIf.setLayoutManager(mLayoutManager);
        mRvIf.setAdapter(mAdapter);
    }
    public void share(boolean friendsCircle,String code){
        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WECHAT_APPID, true);
        wxApi.registerApp(Constants.WECHAT_APPID);
        WXWebpageObject webpage = new WXWebpageObject();
        //http://h5.chetuhui.com.cn/Invitefriends/?userCode=04ce76fcc3eb48b692e85ccc783a136d
//        webpage.webpageUrl = "http://h5.chetuhui.com.cn/Invitefriends/en.html";//分享url
        webpage.webpageUrl = "http://h5.chetuhui.com.cn/Invitefriends/?userCode="+code;//分享url
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "好友邀请你加入车助宝互助，最高享4000元车辆维修互助金！";
        msg.description = "车助宝旨在以“一车受损，多车互助，维修费用均摊”的方式，为车主提供的互助维修公益平台";

        msg.thumbData = ImageTool. bitmap2Bytes(ImageTool. drawable2Bitmap(mContext.getResources().getDrawable(R.mipmap.fx_log)), Bitmap.CompressFormat.PNG) ;//封面图片byte数组

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        //发送的目标场景， 可以选择发送到会话 WXSceneSession 或者朋友圈 WXSceneTimeline。 默认发送到会话。
        req.scene = friendsCircle ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        wxApi.sendReq(req);
    }

}
