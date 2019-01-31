package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.FindFanganBean;
import com.chetuhui.lcj.chezhubao_x.model.MycarBean;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

public class FindfanganAdapter extends BaseQuickAdapter<FindFanganBean.DataBean, BaseViewHolder> {

public FindfanganAdapter(@LayoutRes int layoutResId, @Nullable List<FindFanganBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, FindFanganBean.DataBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_find_title, item.getProgramName())
        .setText(R.id.tv_find_cishu, "剩余维修次数：" +item.getMutualSize())
        .setText(R.id.tv_find_cishu, "剩余维修次数：" +item.getMutualSize())
        .setText(R.id.tv_find_time, "保障期限：" + item.getEffectiveTime() + " 至 " + item.getEndTime());
        if (item.getState()==0){
                TextView textView =helper.itemView.findViewById(R.id.tv_find_tianshu);
                textView.setText("待生效");

        }else if (item.getState()==1){
                TextView textView =helper.itemView.findViewById(R.id.tv_find_tianshu);
                textView.setText("已保障\n" + item.getProtectionDays() + "天");

        }
        else if (item.getState()==2){
                TextView textView =helper.itemView.findViewById(R.id.tv_find_tianshu);
                textView.setText("已用完");

        }
        else if (item.getState()==3){
                TextView textView =helper.itemView.findViewById(R.id.tv_find_tianshu);
                textView.setText("已失效");

        }


//        .setText(R.id.tv_find_tianshu, "已保障\n" + item.getProtectionDays() + "天");


//    mRlFindCarmutual.setVisibility(View.VISIBLE);
//                                        mTvFindTitle.setText(bean.getData().getProgramName());
//                                        mTvFindCishu.setText("剩余维修次数：" + bean.getData().getMutualSize());
//                                        mTvFindTime.setText("保障期限：" + bean.getData().getEffectiveTime() + " 至 " + bean.getData().getEndTime());
//                                        mTvFindTianshu.setText("" + bean.getData().getProtectionDays() + "天");
//
//    ShowImageUtils.showImageViewToCrop(mContext,item.getLicensePicture(),(ImageView) helper.getView(R.id.iv_item_myacr));



        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
