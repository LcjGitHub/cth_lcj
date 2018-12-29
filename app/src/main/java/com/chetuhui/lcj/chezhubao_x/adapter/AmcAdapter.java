package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AmcBean;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class AmcAdapter extends BaseQuickAdapter<AmcBean.DataBean, BaseViewHolder> {

public AmcAdapter(@LayoutRes int layoutResId, @Nullable List<AmcBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, AmcBean.DataBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_item_amc_code, item.getCarNum())
        .setText(R.id.tv_item_amc_name,item.getCarBrand());

    ShowImageUtils.showImageViewToCrop(mContext,item.getLicensePicture(),(ImageView) helper.getView(R.id.iv_item_amc));

        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
