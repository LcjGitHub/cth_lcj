package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AmcBean;
import com.chetuhui.lcj.chezhubao_x.model.IhelpBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;

import java.util.List;

public class IhelpAdapter extends BaseQuickAdapter<IhelpBean.DataBean, BaseViewHolder> {

public IhelpAdapter(@LayoutRes int layoutResId, @Nullable List<IhelpBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, IhelpBean.DataBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_item_ihelp_chepai, item.getCarNum());

        if (item.getMutualSize()==0){
            SuperTextView textView=helper.itemView.findViewById(R.id.tv_item_ihelp_wyjz);
            textView.setText("暂无可用");
            textView.setTextColor(mContext.getResources().getColor(R.color.white));
            textView.setSolid(mContext.getResources().getColor(R.color.cf));

        }else {
            helper.addOnClickListener(R.id.tv_item_ihelp_wyjz);
        }



        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
