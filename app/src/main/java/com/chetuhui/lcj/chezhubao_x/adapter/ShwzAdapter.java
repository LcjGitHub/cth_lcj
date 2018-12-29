package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.QuyuBean;
import com.chetuhui.lcj.chezhubao_x.model.ShwzBean;

import java.util.List;

public class ShwzAdapter extends BaseQuickAdapter<ShwzBean.DataBean, BaseViewHolder> {

public ShwzAdapter(@LayoutRes int layoutResId, @Nullable List<ShwzBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, ShwzBean.DataBean item) {
        //可链式调用赋值
        helper

        .setText(R.id.tv_item_quyu,item.getDamageLocation());


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
