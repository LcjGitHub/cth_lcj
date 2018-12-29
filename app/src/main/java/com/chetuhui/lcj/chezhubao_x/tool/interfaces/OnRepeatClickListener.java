package com.chetuhui.lcj.chezhubao_x.tool.interfaces;

import android.view.View;

import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;


/**
 *
 * @author Vondear
 * @date 2017/7/24
 * 重复点击的监听器
 */

public abstract class OnRepeatClickListener implements View.OnClickListener {



    public abstract void onRepeatClick(View v);

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            onRepeatClick(v);
        }else{
            //BaseToast.normal("点得太快了");
            return;
        }
    }

}
