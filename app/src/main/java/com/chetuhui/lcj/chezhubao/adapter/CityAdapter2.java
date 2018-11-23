package com.chetuhui.lcj.chezhubao.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.model.CityBean;
import com.chetuhui.lcj.chezhubao.model.UserlistBean;
import com.chetuhui.lcj.chezhubao.tool.DataTool;

import java.util.List;

import cc.solart.turbo.BaseTurboAdapter;
import cc.solart.turbo.BaseViewHolder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class CityAdapter2 extends BaseQuickAdapter<CityBean.DataBean, com.chad.library.adapter.base.BaseViewHolder> {

    public CityAdapter2(@LayoutRes int layoutResId, @Nullable List<CityBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(com.chad.library.adapter.base.BaseViewHolder helper, CityBean.DataBean item) {
        //可链式调用赋值
        helper.setText(R.id.city_name, item.getCity());

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }

    public int getLetterPosition(String letter){
        for (int i = 0 ; i < getData().size(); i++){
            if( DataTool.getPYFirstLetter(getData().get(i).getCity()).equals(letter)){
                return i;
            }
        }
        return -1;
    }
}