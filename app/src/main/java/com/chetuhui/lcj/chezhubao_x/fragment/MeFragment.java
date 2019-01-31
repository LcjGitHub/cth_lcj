package com.chetuhui.lcj.chezhubao_x.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.AboutusActivity;
import com.chetuhui.lcj.chezhubao_x.activity.AccountActivity;
import com.chetuhui.lcj.chezhubao_x.activity.AddCarActivity;
import com.chetuhui.lcj.chezhubao_x.activity.CardCouponActivity;
import com.chetuhui.lcj.chezhubao_x.activity.ChangephoneActivity;
import com.chetuhui.lcj.chezhubao_x.activity.FeedbackActivity;
import com.chetuhui.lcj.chezhubao_x.activity.HomeActivity;
import com.chetuhui.lcj.chezhubao_x.activity.InviteFriendsActivity;
import com.chetuhui.lcj.chezhubao_x.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MessageActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MutualHelpAccountActivity;
import com.chetuhui.lcj.chezhubao_x.activity.PersonalInformationActivity;
import com.chetuhui.lcj.chezhubao_x.activity.PopularActivity;
import com.chetuhui.lcj.chezhubao_x.activity.RegisteredActivity;
import com.chetuhui.lcj.chezhubao_x.activity.ReplaceActivity;
import com.chetuhui.lcj.chezhubao_x.activity.TxActivity;
import com.chetuhui.lcj.chezhubao_x.model.APPversionBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.DeviceTool;
import com.chetuhui.lcj.chezhubao_x.tool.FileTool;
import com.chetuhui.lcj.chezhubao_x.tool.PhotoTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogShapeLoading;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSureCancel;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogTool;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static android.app.Activity.RESULT_OK;
import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;
import static com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage.LayoutType.TITLE;


public class MeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private CommonTitleBar mTitlebarMe;
    private String imgurl;
    private Uri resultUri;
    private ImageView mImageView;
    /**
     * 不加糖
     */
    private static TextView mTvMeName;
    /**
     * 不加糖
     */
    private static TextView mTvMePh;
    /**
     * 不加糖
     */
    private static TextView mTvMeDay;
    /**
     * 待付款
     */
    private TextView mTvMeDfk;
    /**
     * 待使用
     */
    private TextView mTvMeDsy;
    /**
     * 待评价
     */
    private TextView mTvMeDpj;
    /**
     * 售后/退款
     */
    private TextView mTvMeTuikuan;
    /**
     * 个人信息
     */
    private TextView mTvMeGrxx;
    /**
     * 红包卡卷
     */
    private TextView mTvMeKajuan;
    /**
     * 关于我们
     */
    private RelativeLayout mTvMeAbout;
    /**
     * 邀请好友
     */
    private TextView mTvMeHaoyou;
    private TextView tv_me_yue;
    private RelativeLayout mRlMeZhanghao;
    private RelativeLayout mRlMeLianxi;
    private RelativeLayout mRlMeYijian;
    private RelativeLayout mRlMeJiancha;
    /**
     * 退出登录
     */
    private SuperTextView mTvMeTuichu;
    private TextView tv_me_hzzh;
    private static String name;
    private static String ph;
    private static String headimgurl,helpPeopleNum;
    private static String yue;
    private String con;
    private static String day;

    private Context context;
    DialogShapeLoading rxDialogShapeLoading;
    private  boolean ismust=false;

    MyHandler myHandler = new MyHandler(MeFragment.this);
    private static class MyHandler extends Handler {
            private WeakReference<MeFragment> activityWeakReference;

            public MyHandler(MeFragment activity) {
                activityWeakReference = new WeakReference<MeFragment>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                MeFragment activity = activityWeakReference.get();
                if (activity != null) {


                }
            }
        }

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s_token = SPTool.getString(getContext(), "token");
        if (DataTool.isNullString(s_token)) {
            ActivityTool.finishAllActivity();
//            SPTool.remove(getContext(),"token");
            startActivity(new Intent(getContext(),LoginActivity.class));

//            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        getN_findClause();
        getN_personalInformation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_me_layout, null);


        context=getContext();
        initView(view);
        getN_findClause();
        getN_personalInformation();
        return view;
    }

    private void initView(View view) {
        mImageView = (ImageView) view.findViewById(R.id.iv_me_tx);
        mTitlebarMe = (CommonTitleBar) view.findViewById(R.id.titlebar_me);
        mTitlebarMe.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mTitlebarMe.setOnClickListener(this);
        mTvMeName = (TextView) view.findViewById(R.id.tv_me_name);
        tv_me_yue = (TextView) view.findViewById(R.id.tv_me_yue);
        mTvMeName.setOnClickListener(this);
        mTvMePh = (TextView) view.findViewById(R.id.tv_me_ph);
        mTvMePh.setOnClickListener(this);
        mTvMeDay = (TextView) view.findViewById(R.id.tv_me_day);
        mTvMeDay.setOnClickListener(this);
        mTvMeDfk = (TextView) view.findViewById(R.id.tv_me_dfk);
        mTvMeDfk.setOnClickListener(this);
        mTvMeDsy = (TextView) view.findViewById(R.id.tv_me_dsy);
        mTvMeDsy.setOnClickListener(this);
        mTvMeDpj = (TextView) view.findViewById(R.id.tv_me_dpj);
        mTvMeDpj.setOnClickListener(this);
        mTvMeTuikuan = (TextView) view.findViewById(R.id.tv_me_tuikuan);
        mTvMeTuikuan.setOnClickListener(this);
        mTvMeGrxx = (TextView) view.findViewById(R.id.tv_me_grxx);
        mTvMeGrxx.setOnClickListener(this);
        mTvMeKajuan = (TextView) view.findViewById(R.id.tv_me_kajuan);
        mTvMeKajuan.setOnClickListener(this);
        mTvMeAbout = (RelativeLayout) view.findViewById(R.id.rl_me_about);
        mTvMeAbout.setOnClickListener(this);
        mTvMeHaoyou = (TextView) view.findViewById(R.id.tv_me_haoyou);
        mTvMeHaoyou.setOnClickListener(this);
        mRlMeZhanghao = (RelativeLayout) view.findViewById(R.id.rl_me_zhanghao);
        mRlMeZhanghao.setOnClickListener(this);
        mRlMeLianxi = (RelativeLayout) view.findViewById(R.id.rl_me_lianxi);
        mRlMeLianxi.setOnClickListener(this);
        mRlMeYijian = (RelativeLayout) view.findViewById(R.id.rl_me_yijian);
        mRlMeYijian.setOnClickListener(this);
        mRlMeJiancha = (RelativeLayout) view.findViewById(R.id.rl_me_jiancha);
        mRlMeJiancha.setOnClickListener(this);
        mTvMeTuichu = (SuperTextView) view.findViewById(R.id.tv_me_tuichu);
        tv_me_hzzh = (TextView) view.findViewById(R.id.tv_me_hzzh);
        mTvMeTuichu.setOnClickListener(this);
        tv_me_hzzh.setOnClickListener(this);

mImageView.setOnClickListener(new OnRepeatClickListener() {
    @Override
    public void onRepeatClick(View v) {
        Intent  intent=new Intent(getContext(), PersonalInformationActivity.class);
        intent .putExtra("imgurl",headimgurl);
        startActivity(intent);
    }
});


    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {

            Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.titlebar_me:
                break;
            case R.id.tv_me_name:
                break;
            case R.id.tv_me_ph:
                break;
            case R.id.tv_me_day:
                break;
            case R.id.tv_me_dfk:
                break;
            case R.id.tv_me_dsy:
                break;
            case R.id.tv_me_dpj:
                break;
            case R.id.tv_me_tuikuan:
                break;
            case R.id.tv_me_grxx:
                intent=new Intent(getContext(), PersonalInformationActivity.class);
                intent .putExtra("imgurl",headimgurl);

                break;
            case R.id.tv_me_kajuan:
                intent=new Intent(getContext(), CardCouponActivity.class);

                break;
            case R.id.rl_me_about:
                intent=new Intent(getContext(), AboutusActivity.class);

                break;
            case R.id.tv_me_haoyou:
                intent=new Intent(getContext(), InviteFriendsActivity.class);
                break;
            case R.id.rl_me_zhanghao:
                intent=new Intent(getContext(), AccountActivity.class);
                break;
            case R.id.tv_me_hzzh:
                intent=new Intent(getContext(), MutualHelpAccountActivity.class);

                break;
            case R.id.rl_me_lianxi:
                showkf();

                break;
            case R.id.rl_me_yijian:
                intent=new Intent(getContext(), FeedbackActivity.class);

                break;
            case R.id.rl_me_jiancha:
                getN_checkAppVersion(""+0);


                break;
            case R.id.tv_me_tuichu:
                cancleTagAndAlias();
                ActivityTool.finishAllActivity();
                SPTool.remove(getContext(),"token");
                startActivity(new Intent(getContext(),LoginActivity.class));


                break;
        }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
            //BaseToast.normal("点得太快了");
            return;
        }
    }
    /**
     * 取消设置标签与别名
     */
    private void cancleTagAndAlias() {
        //TODO 上下文、别名、标签、回调  退出后空数组与空字符串取消之前的设置
        Set<String> tags = new HashSet<String>();
        JPushInterface.setAliasAndTags(getContext(), "", tags, mAliasCallback);
    }
    /**
     * /**
     * TagAliasCallback类是JPush开发包jar中的类，用于
     * 设置别名和标签的回调接口，成功与否都会回调该方法
     * 同时给定回调的代码。如果code=0,说明别名设置成功。
     * /**
     * 6001   无效的设置，tag/alias 不应参数都为 null
     * 6002   设置超时    建议重试
     * 6003   alias 字符串不合法    有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6004   alias超长。最多 40个字节    中文 UTF-8 是 3 个字节
     * 6005   某一个 tag 字符串不合法  有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6006   某一个 tag 超长。一个 tag 最多 40个字节  中文 UTF-8 是 3 个字节
     * 6007   tags 数量超出限制。最多 100个 这是一台设备的限制。一个应用全局的标签数量无限制。
     * 6008   tag/alias 超出总长度限制。总长度最多 1K 字节
     * 6011   10s内设置tag或alias大于3次 短时间内操作过于频繁
     **/
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    //这里可以往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    //UserUtils.saveTagAlias(getHoldingActivity(), true);
                    logs = "Set tag and alias success极光推送别名设置成功";
                    Log.e("TAG", logs);
                    break;
                case 6002:
                    //极低的可能设置失败 我设置过几百回 出现3次失败 不放心的话可以失败后继续调用上面那个方面 重连3次即可 记得return 不要进入死循环了...
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    Log.e("TAG", logs);
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    Log.e("TAG", logs);
                    break;
            }
        }
    };

    private void  showkf(){
        final DialogSureCancel mDialog = new DialogSureCancel(getContext(), R.style.PushUpInDialogThem);
        mDialog.setTitle("拨打客服电话");
        mDialog.setContent(""+con);
        mDialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                VibrateTool.vibrateOnce(mContext, 150);
                mDialog.cancel();
            }
        });
        mDialog.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.cancel();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:"+con );
                intent.setData(data);
                startActivity(intent);


            }
        });
        mDialog.setCancelable(false);
        mDialog.show();


    }
    private void getN_findClause() {
        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);


//1：法律声明 2：车助保服务协议 3：服务合作协议 4：关于我们 5：客服电话
        OkGo.<String>get(NetData.N_findClause+"?type=6")
                .tag(this)
                .headers("token",s_token)


                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                //BaseToast.success(msg);
                                String d=jsonObject.getString("data");
                                JSONObject jsonObject1=new JSONObject(d);
                                con=  jsonObject1.getString("content");




                            } else if (code==1004){
//                                ActivityTool.finishAllActivity();
//                                SPTool.remove(getContext(),"token");
//                                startActivity(new Intent(getContext(),LoginActivity.class));
//                                BaseToast.error("登录过期，请重新登录");

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

    private void getN_personalInformation() {
        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
//            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
        }
        OkGo.<String>get(NetData.N_personalInformation)
                .tag(this)
                .headers("token",s_token)


                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                //BaseToast.success(msg);
                                String d=jsonObject.getString("data");
                                JSONObject jsonObject1=new JSONObject(d);
                               name=jsonObject1.getString("nickName");
                               ph=jsonObject1.getString("phone");
                                headimgurl=jsonObject1.getString("headimgurl");
                                helpPeopleNum=jsonObject1.getString("helpPeopleNum");
                               day= String.valueOf(jsonObject1.getInt("joinDays"));
                               yue= String.valueOf(jsonObject1.getDouble("money"));
                               myHandler.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       mTvMeName.setText(name);
                                       mTvMePh.setText(ph);
                                       tv_me_yue.setText(yue);
                                       ShowImageUtils.showImageViewToCrop(getContext(),headimgurl,mImageView);
                                       mTvMeDay.setText("已加入 "+day+" 天,"+"帮助"+helpPeopleNum+"人");
                                   }
                               });





                            } else if (code==1004){
//                                ActivityTool.finishAllActivity();
//                                SPTool.remove(getContext(),"token");
//                                startActivity(new Intent(getContext(),LoginActivity.class));
//                                BaseToast.error("登录过期，请重新登录");

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

    private void getN_checkAppVersion(String type) {
        String s_token = SPTool.getString(context, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(context, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_checkAppVersion+"?type="+type)
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
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                //BaseToast.success(msg);
                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        DialogTool.loadingHttpCancel();
                                        APPversionBean bean=new Gson().fromJson(data, APPversionBean.class);
                                        String oldnum= DeviceTool.getAppVersionName(context);

                                        String newnum =bean.getData().getVersionNum();
                                        if (bean.getData().getIsMust()==0){
                                            ismust=false;
                                        }else if (bean.getData().getIsMust()==1){
                                            ismust=false;
                                        }
                                        Log.d("HomeActivity", oldnum+"-------------"+newnum);
                                        if (oldnum.equals(newnum)){
                                            BaseToast.success("已是最新版本");
                                        }else {
                                            ShowDialog(bean.getData().getContent(),bean.getData().getUrl());
                                        }





                                    }
                                });





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(context,"token");
                                startActivity(new Intent(context,LoginActivity.class));
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
    private void ShowDialog(String strAppVersionName, final String apk_down_url) {
        final DialogSureCancel rxDialogSureCancel = new DialogSureCancel(context);//提示弹窗
        rxDialogSureCancel.getContentView().setText(strAppVersionName);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFile(apk_down_url, FileTool.getDiskFileDir(context) + File.separator + "update", "cth_zzb.apk");
                // TODO: 第一步 在此处 使用 你的网络框架下载 新的Apk文件 可参照下面的例子 使用的是 okGo网络框架
                // TODO: 第二步 可使用 RxAppTool.InstallAPK(context,file.getAbsolutePath()); 方法进行 最新版本Apk文件的安装
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ismust){
                    rxDialogSureCancel.cancel();
                    ActivityTool.finishAllActivity();
                }else {
                    rxDialogSureCancel.cancel();
                }

            }
        });
        rxDialogSureCancel.show();
    }

    /**
     * 下载APk文件并自动弹出安装
     */
    public void getFile(String url, final String filePath, String name) {
        OkGo.<File>get(url).tag(context).execute(new FileCallback(filePath, name) { //文件下载时指定下载的路径以及下载的文件的名称
            @Override
            public void onStart(Request<File, ? extends Request> request) {
                super.onStart(request);

                Logger.e("开始下载文件" + "DDDDD");
                rxDialogShapeLoading = new DialogShapeLoading(context);
                rxDialogShapeLoading.show();
                BaseToast.success("开始下载文件");

            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<File> response) {

                Logger.e("下载文件成功" + "DDDDD" + response.body().length());
//               mBasePath=response.body().getAbsolutePath();
                rxDialogShapeLoading.cancel();
                BaseToast.success("下载文件成功");

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setDataAndType(Uri.parse("file://" + response.body().getAbsolutePath()), "application/vnd.android.package-archive");
                context.startActivity(i);
                ActivityTool.finishAllActivity();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                Logger.e("下载文件完成" + "DDDDD");
//                SPUtils.getInstance().put("localPath",mBasePath);
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<File> response) {
                super.onError(response);
                Logger.e("下载文件出错" + "DDDDD" + response.message());
                BaseToast.error("下载文件出错");
                rxDialogShapeLoading.cancel();
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                final float dLProgress = progress.fraction;
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        int num = (new Double((dLProgress)*100)).intValue();
                        rxDialogShapeLoading. setLoadingText_num(num);
//                        BaseToast.success(""+dLProgress+"%");
                    }
                });


                Logger.e("文件下载的进度" + "DDDDD" + dLProgress);
            }
        });
    }


    private void getN_updateHeardImage(String uri) {
        String s_token = SPTool.getString(getContext(), "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(getContext(), "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }



        OkGo.<String>get(NetData.N_updateHeardImage+"?imgUrl="+uri)
                .tag(this)
                .headers("token",s_token)


                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code=jsonObject.getInt("code");
                            final JSONObject finalJsonObject = jsonObject;
                            myHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code==0){
                                        try {
                                            imgurl= finalJsonObject.getString("data");
                                            ShowImageUtils.showImageView(getContext(),imgurl,mImageView);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        BaseToast.success("照片上传成功");

                                        Log.d("AddCarActivity", imgurl);

                                    }
                                    else if (code==1004){
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(getContext(),"token");
                                        startActivity(new Intent(getContext(),LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    }else {
                                        BaseToast.success(msg);

                                    }
                                }
                            });

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


}
