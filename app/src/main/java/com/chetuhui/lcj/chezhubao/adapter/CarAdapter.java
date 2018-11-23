package com.chetuhui.lcj.chezhubao.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.model.CarBean;
import com.chetuhui.lcj.chezhubao.model.UserlistBean;
import com.chetuhui.lcj.chezhubao.tool.DataTool;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class CarAdapter extends BaseQuickAdapter<CarBean.DataBean, BaseViewHolder> {

public CarAdapter(@LayoutRes int layoutResId, @Nullable List<CarBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, CarBean.DataBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_item_select_car_name, DataTool.hideMobilePhone4(item.getCarBrand()))
        .setText(R.id.tv_item_select_car_code, DataTool.hideCarcode2(item.getCarNum()));
    Glide.with(mContext).load(item.getLicensePicture()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) helper.getView(R.id.iv_item_select_car));

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
