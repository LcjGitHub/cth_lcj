package com.chetuhui.lcj.myapplication.tool.interfaces;

import android.view.View;

import com.chetuhui.lcj.myapplication.tool.BaseTool;
import com.chetuhui.lcj.myapplication.view.BaseToast;


/**
 *
 * @author Vondear
 * @date 2017/7/24
 * 重复点击的监听器
 */

public abstract class OnRepeatClickListener implements View.OnClickListener {

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private final int MIN_CLICK_DELAY_TIME = 1000;

    public abstract void onRepeatClick(View v);

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            onRepeatClick(v);
        }else{
            BaseToast.normal("点得太快了");
            return;
        }
    }

}
