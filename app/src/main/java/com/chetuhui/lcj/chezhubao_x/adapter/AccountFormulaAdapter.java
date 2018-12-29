package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AccountFormulaBean;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.chetuhui.lcj.chezhubao_x.tool.DataTool.getAmountValue;

public class AccountFormulaAdapter extends BaseQuickAdapter<AccountFormulaBean.DataBean.ListBean, BaseViewHolder> {

public AccountFormulaAdapter(@LayoutRes int layoutResId, @Nullable List<AccountFormulaBean.DataBean.ListBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, AccountFormulaBean.DataBean.ListBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_item_accountformula_time,item.getComparisonTime() )
        .setText(R.id.tv_item_accountformula_yue, "¥"+getAmountValue(item.getMoney()));
    ShowImageUtils.showImageView(mContext,item.getMonthUrl(),(ImageView) helper.getView(R.id.iv_item_accountformula));




        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
