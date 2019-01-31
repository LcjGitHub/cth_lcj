package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AccountFormulaBean;
import com.chetuhui.lcj.chezhubao_x.model.IfBean;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

import static com.chetuhui.lcj.chezhubao_x.tool.DataTool.getAmountValue;

public class IfAdapter extends BaseQuickAdapter<IfBean.DataBean.InviterVoListBean, BaseViewHolder> {

public IfAdapter(@LayoutRes int layoutResId, @Nullable List<IfBean.DataBean.InviterVoListBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, IfBean.DataBean.InviterVoListBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_item_if_ph,item.getFriendPhone() )
        .setText(R.id.tv_item_if_time,item.getGetDate() );
        if (item.getIsGetTicket().equals("1")){
                TextView textView =helper.itemView.findViewById(R.id.tv_item_if_is);
                textView.setText("加入");

        }else  if (item.getIsGetTicket().equals("2")){
                TextView textView =helper.itemView.findViewById(R.id.tv_item_if_is);
                textView.setText("未加入");
                textView.setTextColor(mContext.getResources().getColor(R.color.fb64));
        }




        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
