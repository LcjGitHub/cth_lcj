package com.chetuhui.lcj.chezhubao.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.model.UserlistBean;
import com.chetuhui.lcj.chezhubao.tool.DataTool;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class UserlistAdapter extends BaseQuickAdapter<UserlistBean.DataBean, BaseViewHolder> {

public UserlistAdapter(@LayoutRes int layoutResId, @Nullable List<UserlistBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, UserlistBean.DataBean item) {
        //可链式调用赋值
        helper.setText(R.id.tv_item_userlist_name, item.getNickName())
        .setText(R.id.tv_item_userlist_ph, DataTool.hideMobilePhone4(item.getPhone()))
        .setText(R.id.tv_item_userlist_chepai, DataTool.hideCarcode2(item.getCarCode()))

        .setText(R.id.tv_item_userlist_time, "加入时间："+item.getCreateTime());
    Glide.with(mContext).load(item.getHeadimgurl()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) helper.getView(R.id.iv_item_userlist));

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
