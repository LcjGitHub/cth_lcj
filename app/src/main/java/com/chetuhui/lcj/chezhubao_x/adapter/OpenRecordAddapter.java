package com.chetuhui.lcj.chezhubao_x.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.activity.ActivityBase;
import com.chetuhui.lcj.chezhubao_x.model.AdBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is HomeAddapter
 */

public class OpenRecordAddapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;
    private int ITEM_CONTENT = 2;
    private List<AdBean.DataBean> mDataBeans;
    private Context mContext;
    private List<AdBean.DataBean.MubillRecordVoListBean> mMubillRecordVoListBeans;
//    private List<Object> objects;

    public OpenRecordAddapter(ActivityBase context) {
        this.  mContext=context;
    }

    /**
     * 传入数据
     * @param objects
     */
    public void setDate(List<AdBean.DataBean> objects) {
        this.mDataBeans = objects;
        notifyDataSetChanged();


    }
//    public void setDate(List<Object> objects) {
//        this.objects = objects;
//        notifyDataSetChanged();
//    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_record_title, parent, false);
            holder = new ViewHolderTitle(view);
        } else if (viewType == ITEM_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_record_content, parent, false);
            holder = new ViewHolderContent(view);

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderTitle) {
            AdBean.DataBean dataBean = (AdBean.DataBean) mDataBeans.get(position);

            Log.d("OpenRecordAddapter", ""+dataBean.getMonth());

            ((ViewHolderTitle) holder).tv_item_ad_title_day.setText(dataBean.getMonth());
            ((ViewHolderTitle) holder).tv_item_ad_title_cz.setText("充值："+dataBean.getRechargeAmount()+"元");
            ((ViewHolderTitle) holder).tv_item_ad_title_hz.setText("互助："+dataBean.getHelpSize()+"次");
            ((ViewHolderTitle) holder).tv_item_ad_title_ft.setText("分摊："+dataBean.getAmortizedAmountMonth()+"元");
            LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            ((ViewHolderTitle) holder). rv_item_ad.setLayoutManager(manager);
            MyGridAdapter myGridAdapter=new MyGridAdapter(R.layout.open_record_content,dataBean.getMubillRecordVoList());

            ((ViewHolderTitle) holder). rv_item_ad.setAdapter(myGridAdapter);

        }else if (holder instanceof ViewHolderContent) {
//            AdBean.DataBean.MubillRecordVoListBean mubillRecordVoListBean=mDataBeans.get(position).getMubillRecordVoList();
//            List<AdBean.DataBean.MubillRecordVoListBean> mubillRecordVoListBean=new ArrayList<>();
//
//            mubillRecordVoListBean= mDataBeans.get(position).getMubillRecordVoList();
            AdBean.DataBean.MubillRecordVoListBean mubillRecordVoListBean = (AdBean.DataBean.MubillRecordVoListBean) mDataBeans.get(position).getMubillRecordVoList();
            Log.d("OpenRecordAddapter", ">>>>>>"+mDataBeans.get(position).getMubillRecordVoList().get(position).getCarNum());

        ((ViewHolderContent) holder).tv_item_ad_con_name.setText(mubillRecordVoListBean.getProgrameName());
        ((ViewHolderContent) holder).tv_item_ad_con_carnum.setText(mubillRecordVoListBean.getCarNum());
        ((ViewHolderContent) holder).tv_item_ad_con_ci.setText(mubillRecordVoListBean.getBillHelpSize());
        if (mubillRecordVoListBean.getType() ==1  ){
            ((ViewHolderContent) holder).tv_item_ad_con_money.setTextColor(mContext.getResources().getColor(R.color.red));
            ((ViewHolderContent) holder).tv_item_ad_con_money.setText("-"+mubillRecordVoListBean.getAmortizedAmount());
        }else if (mubillRecordVoListBean.getType() ==2){
            ((ViewHolderContent) holder).tv_item_ad_con_money.setTextColor(mContext.getResources().getColor(R.color.home_button));
            ((ViewHolderContent) holder).tv_item_ad_con_money.setText("+"+mubillRecordVoListBean.getAmortizedAmount());
        }


        }

    }

    @Override
    public int getItemViewType(int position) {
        if (mDataBeans.get(position) instanceof AdBean.DataBean) {
            return ITEM_TITLE;
        } else if (mDataBeans.get(position).getMubillRecordVoList() instanceof AdBean.DataBean.MubillRecordVoListBean) {
            return ITEM_CONTENT;
        }
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return mDataBeans == null ? 0 : mDataBeans.size();
    }
    public class ViewHolderTitle extends RecyclerView.ViewHolder {
        TextView tv_item_ad_title_day,tv_item_ad_title_cz,tv_item_ad_title_ft,tv_item_ad_title_hz;
        RecyclerView rv_item_ad;
        public ViewHolderTitle(View itemView) {
            super(itemView);
            rv_item_ad = (RecyclerView) itemView.findViewById(R.id.rv_item_ad);
            tv_item_ad_title_day = (TextView) itemView.findViewById(R.id.tv_item_ad_title_day);
            tv_item_ad_title_cz = (TextView) itemView.findViewById(R.id.tv_item_ad_title_cz);
            tv_item_ad_title_hz = (TextView) itemView.findViewById(R.id.tv_item_ad_title_hz);
            tv_item_ad_title_ft = (TextView) itemView.findViewById(R.id.tv_item_ad_title_ft);


        }
    }
    public class ViewHolderContent extends RecyclerView.ViewHolder {

        TextView tv_item_ad_con_name,tv_item_ad_con_carnum,tv_item_ad_con_ci,tv_item_ad_con_money;
        public ViewHolderContent(View view) {
            super(view);
            tv_item_ad_con_name = (TextView) view.findViewById(R.id.tv_item_ad_con_name);
            tv_item_ad_con_carnum = (TextView) view.findViewById(R.id.tv_item_ad_con_carnum);
            tv_item_ad_con_ci = (TextView) view.findViewById(R.id.tv_item_ad_con_ci);
            tv_item_ad_con_money = (TextView) view.findViewById(R.id.tv_item_ad_con_money);


        }
    }

    class MyGridAdapter extends BaseQuickAdapter {

        public MyGridAdapter(int layoutResId, List<AdBean.DataBean.MubillRecordVoListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {
            AdBean.DataBean.MubillRecordVoListBean bean= (AdBean.DataBean.MubillRecordVoListBean) item;
            helper.setText(R.id.tv_item_ad_con_name,bean.getProgrameName());
            helper.setText(R.id.tv_item_ad_con_carnum,bean.getCarNum());

            if (bean.getType() ==1  ){
                TextView tv_item_ad_con_money=helper.itemView.findViewById(R.id.tv_item_ad_con_money);
                tv_item_ad_con_money.setTextColor(mContext.getResources().getColor(R.color.red));
                tv_item_ad_con_money.setText("-"+bean.getAmortizedAmount()+"元");
                helper.setText(R.id.tv_item_ad_con_ci,"互助："+bean.getBillHelpSize()+"次");

            }else if (bean.getType() ==2){
                TextView tv_item_ad_con_money=helper.itemView.findViewById(R.id.tv_item_ad_con_money);
                tv_item_ad_con_money.setTextColor(mContext.getResources().getColor(R.color.home_button));
                tv_item_ad_con_money.setText("+"+bean.getAmortizedAmount()+"元");
                helper.setText(R.id.tv_item_ad_con_ci,"本月为该互助单充值："+bean.getAmortizedAmount()+"元互助金");

            }


        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
        }
    }

}

