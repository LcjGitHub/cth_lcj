package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.model.PopularBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class PopularAdapter extends BaseQuickAdapter<PopularBean.DataBean, BaseViewHolder> {

public PopularAdapter(@LayoutRes int layoutResId, @Nullable List<PopularBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, PopularBean.DataBean item) {
        //可链式调用赋值
        helper

        .setText(R.id.tv_item_popular,item.getTitle());

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
