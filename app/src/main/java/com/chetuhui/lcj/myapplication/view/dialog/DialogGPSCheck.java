package com.chetuhui.lcj.myapplication.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;

import com.chetuhui.lcj.myapplication.tool.LocationTool;


/**
 * @author vondear
 * @date 2016/7/19
 * 检测Gps状态
 */
public class DialogGPSCheck extends DialogSureCancel {
    
    public DialogGPSCheck(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public DialogGPSCheck(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public DialogGPSCheck(Context context) {
        super(context);
        initView();
    }

    public DialogGPSCheck(Activity context) {
        super(context);
        initView();
    }

    public DialogGPSCheck(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    private void initView() {
        getTitleView().setBackgroundDrawable(null);
        setTitle("GPS未打开");
        getTitleView().setTextSize(16f);
        getTitleView().setTextColor(Color.BLACK);
        setContent("您需要在系统设置中打开GPS方可采集数据");
        getSureView().setText("去设置");
        getCancelView().setText("知道了");

        getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationTool.openGpsSettings(mContext);
                cancel();
            }
        });

        getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

}
