package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.APPversionBean;
import com.chetuhui.lcj.chezhubao_x.model.UserlistBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.AppTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.DeviceTool;
import com.chetuhui.lcj.chezhubao_x.tool.FileTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.DataGenerator;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogShapeLoading;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSureCancel;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogTool;
import com.chetuhui.lcj.chezhubao_x.view.tabview.CustomTabView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class HomeActivity extends ActivityBase implements CustomTabView.OnTabCheckListener {
    private CustomTabView mCustomTabView;
    private Fragment[] mFragmensts;
    //双击返回键 退出
    //----------------------------------------------------------------------------------------------
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private String us_code;
    private Context context;
    DialogShapeLoading rxDialogShapeLoading;
    private  boolean ismust=false;
     DialogSureCancel rxDialogSureCancel;

    private MyHandler mHandler=new MyHandler(HomeActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<HomeActivity> activityWeakReference;

            public MyHandler(HomeActivity activity) {
                activityWeakReference = new WeakReference<HomeActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                HomeActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        us_code= SPTool.getString(HomeActivity.this,"user_code");
        setTagAndAlias();
        getN_checkAppVersion(""+0);
        mFragmensts = DataGenerator.getFragments();
        initView();

    }

    private void initView() {
        mCustomTabView = (CustomTabView) findViewById(R.id.custom_tab_container);
        CustomTabView.Tab tabHome = new CustomTabView.Tab().setText("首页")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.tab_home1)
                .setPressedIcon(R.drawable.tab_home2);
        mCustomTabView.addTab(tabHome);
        CustomTabView.Tab tabDis = new CustomTabView.Tab().setText("互助")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.tab_huzhu1)
                .setPressedIcon(R.drawable.tab_huzhu2);
        mCustomTabView.addTab(tabDis);
        CustomTabView.Tab tabAttention = new CustomTabView.Tab().setText("发现")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.tab_faxian1)
                .setPressedIcon(R.drawable.tab_faxian2);
        mCustomTabView.addTab(tabAttention);
        CustomTabView.Tab tabProfile = new CustomTabView.Tab().setText("我的")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.tab_user1)
                .setPressedIcon(R.drawable.tab_user2);
        mCustomTabView.addTab(tabProfile);

        mCustomTabView.setOnTabCheckListener(this);

        mCustomTabView.setCurrentItem(0);

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

            case 2:
//                fragment = mFragmensts[2];
                BaseToast.success("正在开发");
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再次点击返回键退出", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    /**
     * 设置标签与别名
     */
    private void setTagAndAlias() {
        /**
         *这里设置了别名，在这里获取的用户登录的信息
         *并且此时已经获取了用户的userId,然后就可以用用户的userId来设置别名了
         **/
        //false状态为未设置标签与别名成功
        //if (UserUtils.getTagAlias(getHoldingActivity()) == false) {
        Set<String> tags = new HashSet<String>();
        //这里可以设置你要推送的人，一般是用户uid 不为空在设置进去 可同时添加多个
        if (!TextUtils.isEmpty(us_code)) {
            tags.add(us_code);//设置tag
        }
        //上下文、别名【Sting行】、标签【Set型】、回调
        JPushInterface.setAliasAndTags(HomeActivity.this, us_code, tags,
                mAliasCallback);
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
                    for (int i = 0; i < 3; i++) {
                        if (i>3){
                            return;
                        }else {
                            setTagAndAlias();
                        }

                    }

                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    Log.e("TAG", logs);
                    break;
            }
        }
    };
    private void getN_checkAppVersion(String type) {
//        String s_token = SPTool.getString(HomeActivity.this, "token");
//        Log.d("CityActivity", s_token);
//
//        if (DataTool.isNullString(s_token)) {
//            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
//        }

        OkGo.<String>get(NetData.N_checkAppVersion+"?type="+type)
                .tag(this)
//                .headers("token",s_token)


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
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        APPversionBean bean=new Gson().fromJson(data, APPversionBean.class);
                                        String oldnum= DeviceTool.getAppVersionName(HomeActivity.this);
                                        int VersionNo= DeviceTool.getAppVersionNo(HomeActivity.this);

                                        String newnum =bean.getData().getVersionNum();
                                        if (bean.getData().getIsMust()==0){
                                            ismust=false;
                                        }else if (bean.getData().getIsMust()==1){
                                            ismust=true;
                                        }
                                        Log.d("HomeActivity", oldnum+"-------------"+oldnum);
                                        Log.d("HomeActivity", newnum+"-------------"+newnum);
                                        Log.d("HomeActivity", VersionNo+"-------------"+VersionNo);
                                        if (Double.parseDouble(String.valueOf(oldnum))>=Double.parseDouble(bean.getData().getVersionNum())){

                                        }else {
                                            ShowDialog(bean.getData().getContent(),bean.getData().getUrl());
                                        }
//                                        if (oldnum.equals(newnum)){
////                                            BaseToast.success("已是最新版本");
//                                        }else {
//
//                                        }





                                    }
                                });





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(HomeActivity.this,"token");
                                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
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
         rxDialogSureCancel = new DialogSureCancel(context);//提示弹窗
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
        rxDialogSureCancel.setCanceledOnTouchOutside(false);

        rxDialogSureCancel.setCancelable(false);
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

            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<File> response) {

                Logger.e("下载文件成功" + "DDDDD" + response.body().length());
//               mBasePath=response.body().getAbsolutePath();
                BaseToast.success("下载文件成功");
                rxDialogShapeLoading.cancel();
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
                mHandler.post(new Runnable() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rxDialogSureCancel!= null) {
            rxDialogSureCancel.cancel();
        }
        if (rxDialogShapeLoading!= null) {
            rxDialogShapeLoading.cancel();
        }

    }
}