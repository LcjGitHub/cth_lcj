package com.chetuhui.lcj.chezhubao_x;

import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MultipleItemAdapter extends BaseMultiItemQuickAdapter<MyMultipleItem, BaseViewHolder> {

    public MultipleItemAdapter(List data) {
        super(data);
        //必须绑定type和layout的关系
        addItemType(MyMultipleItem.FIRST_TYPE, R.layout.open_record_title);
        addItemType(MyMultipleItem.SECOND_TYPE, R.layout.open_record_content);


    }

    @Override
    protected void convert(BaseViewHolder helper, MyMultipleItem item) {
        switch (helper.getItemViewType()) {
            case MyMultipleItem.FIRST_TYPE:
                helper
                        .setText(R.id.tv_item_ad_title_day, item.getData().getMonth())
                        .setText(R.id.tv_item_ad_title_cz, "充值："+item.getData().getRechargeAmount()+"元")
                        .setText(R.id.tv_item_ad_title_hz, "互助："+item.getData().getHelpSize()+"次")
                        .setText(R.id.tv_item_ad_title_ft, "分摊："+item.getData().getAmortizedAmountMonth()+"元");
                Log.i("tag","FIRST_TYPE==============="+helper.getLayoutPosition());
                break;
            case MyMultipleItem.SECOND_TYPE:
                helper
                        .setText(R.id.tv_item_ad_con_name, item.getDatam().getProgrameName())
                        .setText(R.id.tv_item_ad_con_carnum, "："+item.getDatam().getCarNum()+"")
                        .setText(R.id.tv_item_ad_con_ci, "："+item.getDatam().getBillHelpSize()+"")
                        .setText(R.id.tv_item_ad_con_money, "："+item.getDatam().getAmortizedAmount()+"");
                Log.i("tag","SECOND_TYPE==============="+helper.getLayoutPosition());
                break;
//            case MyMultipleItem.NORMAL_TYPE:
//                helper.setImageResource(R.id.iv_img, R.mipmap.ic_launcher)
//                        .setText(R.id.tv_title, item.getData().getTitle())
//                        .setText(R.id.tv_content, item.getData().getContent());
//                break;
        }
    }
}