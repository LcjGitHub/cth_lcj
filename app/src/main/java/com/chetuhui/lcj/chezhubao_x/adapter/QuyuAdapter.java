package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AmcBean;
import com.chetuhui.lcj.chezhubao_x.model.QuyuBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

public class QuyuAdapter extends BaseQuickAdapter<QuyuBean.DataBean, BaseViewHolder> {

public QuyuAdapter(@LayoutRes int layoutResId, @Nullable List<QuyuBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, QuyuBean.DataBean item) {
        //可链式调用赋值
        helper

        .setText(R.id.tv_item_quyu,item.getName());


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
