package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.model.MhaBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MhaAdapter extends BaseQuickAdapter<MhaBean.DataBean.MubillVoListBean, BaseViewHolder> {
    private Map<Integer, Boolean> map = new HashMap<>();

public MhaAdapter(@LayoutRes int layoutResId, @Nullable List<MhaBean.DataBean.MubillVoListBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, MhaBean.DataBean.MubillVoListBean item) {
        //可链式调用赋值
        helper

        .setText(R.id.tv_item_mha_title, item.getProgrameName())
        .setText(R.id.tv_item_mha_carnum, item.getCarNum())
        .setText(R.id.tv_item_mha_yue, "余额："+item.getBalance()+"元")
                .addOnClickListener(R.id.tv_item_mha_cz)    //给图标添加点击事件
        ;
        ImageView imageView =helper.itemView.findViewById(R.id.iv_item_mha);
    ShowImageUtils.showImageViewToCircle(mContext,item.getCarBrandImgurl(),imageView,10);

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
