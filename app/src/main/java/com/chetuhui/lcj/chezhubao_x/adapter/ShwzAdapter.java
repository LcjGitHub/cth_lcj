package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.QuyuBean;
import com.chetuhui.lcj.chezhubao_x.model.ShwzBean;
import com.chetuhui.lcj.chezhubao_x.model.WeizhiBean;

import java.util.List;

public class ShwzAdapter extends BaseQuickAdapter<WeizhiBean.DataBean.ListBean, BaseViewHolder> {

public ShwzAdapter(@LayoutRes int layoutResId, @Nullable List<WeizhiBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, WeizhiBean.DataBean.ListBean item) {
        //可链式调用赋值
        helper

        .setText(R.id.tv_item_quyu,item.getDamageLocation());
        ImageView imageView=helper.itemView.findViewById(R.id.iv_item_quyu);
        if (item.getState()==0){
                imageView.setVisibility(View.GONE);

        }else if (item.getState()==1){
                imageView.setVisibility(View.VISIBLE);
        }


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
