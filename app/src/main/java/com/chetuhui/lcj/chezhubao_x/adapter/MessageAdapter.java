package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;



import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.MessageBean;



import java.util.List;



public class MessageAdapter extends BaseQuickAdapter<MessageBean.DataBean, BaseViewHolder> {

    public MessageAdapter(@LayoutRes int layoutResId, @Nullable List<MessageBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean.DataBean item) {
        //可链式调用赋值
        helper.setText(R.id.tv_item_msg_time, item.getCreateTime())
        .setText(R.id.tv_item_msg_title, item.getContent());


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