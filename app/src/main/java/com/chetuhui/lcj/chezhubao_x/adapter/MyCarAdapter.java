package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AmcBean;
import com.chetuhui.lcj.chezhubao_x.model.MycarBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MyCarAdapter extends BaseQuickAdapter<MycarBean.DataBean, BaseViewHolder> {

public MyCarAdapter(@LayoutRes int layoutResId, @Nullable List<MycarBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, MycarBean.DataBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_item_myacr_name, item.getCarNum());
    ShowImageUtils.showImageViewToCrop(mContext,item.getLicensePicture(),(ImageView) helper.getView(R.id.iv_item_myacr));
//    Glide.with(mContext).load(item.getLicensePicture()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) helper.getView(R.id.iv_item_select_car));

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
