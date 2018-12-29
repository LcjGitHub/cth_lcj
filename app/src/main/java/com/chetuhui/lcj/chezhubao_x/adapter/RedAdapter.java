package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.MessageBean;
import com.chetuhui.lcj.chezhubao_x.model.RedBean;

import java.util.List;


public class RedAdapter extends BaseQuickAdapter<RedBean.DataBean, BaseViewHolder> {

    public RedAdapter(@LayoutRes int layoutResId, @Nullable List<RedBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RedBean.DataBean item) {
        //可链式调用赋值
        helper
                .setText(R.id.tv_item_red_money, "￥"+item.getTicketMoney())
                .setText(R.id.tv_item_red_con, item.getRemark())
        .setText(R.id.tv_item_red_title, item.getTicketName());


//                .setText(R.id.tv_item_userlist_ph, DataTool.hideMobilePhone4(item.getPhone()))
//                .setText(R.id.tv_item_userlist_chepai, DataTool.hideCarcode2(item.getCarCode()))
//
//                .setText(R.id.tv_item_userlist_time, "加入时间："+item.getCreateTime());
//        Glide.with(mContext).load(item.getHeadimgurl()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) helper.getView(R.id.iv_item_userlist));

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }
}