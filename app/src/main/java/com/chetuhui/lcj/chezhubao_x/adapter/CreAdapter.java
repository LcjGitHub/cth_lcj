package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AccountFormulaBean;
import com.chetuhui.lcj.chezhubao_x.model.CreBean;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

import static com.chetuhui.lcj.chezhubao_x.tool.DataTool.getAmountValue;

public class CreAdapter extends BaseQuickAdapter<CreBean.DataBean, BaseViewHolder> {

public CreAdapter(@LayoutRes int layoutResId, @Nullable List<CreBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, CreBean.DataBean item) {
        helper
                .setText(R.id.tv_item_cre_title,item.getTicketName() )
                .setText(R.id.tv_item_cre_time,""+item.getBeginTime()+"至"+item.getEndTime() )

                .setText(R.id.tv_item_cre_money, "¥ "+item.getTicketMoney());
        //可链式调用赋值
        if (item.getIsUse().equals("1")){

                View imageView=helper.itemView.findViewById(R.id.iv_item_cre);
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                imageView.setVisibility(View.GONE);
                LinearLayout linearLayout=helper.itemView.findViewById(R.id.ll_item_cre2);
                linearLayout.setVisibility(View.GONE);

        }else if (item.getIsUse().equals("2")) {
                helper
                        .setText(R.id.tv_item_cre_yy,item.getReason() );
                LinearLayout linearLayout=helper.itemView.findViewById(R.id.ll_item_cre2);
                linearLayout.setVisibility(View.VISIBLE);
                View imageView=helper.itemView.findViewById(R.id.iv_item_cre);
                imageView.setVisibility(View.VISIBLE);
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.white_66));
        }






        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
