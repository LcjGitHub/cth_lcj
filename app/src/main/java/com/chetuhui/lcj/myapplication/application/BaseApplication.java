package com.chetuhui.lcj.myapplication.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.chetuhui.lcj.myapplication.tool.BaseTool;
import com.chetuhui.lcj.myapplication.view.supertextview.ImageEngine.GlideEngine;
import com.chetuhui.lcj.myapplication.view.supertextview.ImageEngine.ImageEngine;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class BaseApplication  extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        BaseTool.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        ImageEngine.install(new GlideEngine(this));
    }

}