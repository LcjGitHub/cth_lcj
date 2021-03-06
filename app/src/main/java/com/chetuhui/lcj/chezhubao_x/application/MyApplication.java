package com.chetuhui.lcj.chezhubao_x.application;


import android.app.Application;
import android.content.Context;

import android.support.multidex.MultiDex;

import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.Constants;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.ImageEngine.GlideEngine;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.ImageEngine.ImageEngine;

import com.lzy.okgo.OkGo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;


public class MyApplication extends Application {


    private static Context mContext;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        BaseTool.init(this);
//        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        mContext = getApplicationContext();

        Logger.addLogAdapter(new AndroidLogAdapter());
        ImageEngine.install(new GlideEngine(this));
        //必须调用初始化
        OkGo.getInstance().init(this);
    }


    /**获取Context.
     * @return
     */
    public static Context getAppContext(){
        return mContext;
    }






}