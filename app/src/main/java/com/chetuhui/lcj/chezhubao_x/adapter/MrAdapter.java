package com.chetuhui.lcj.chezhubao_x.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.MmhBean;
import com.chetuhui.lcj.chezhubao_x.model.MrBean;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;

import java.util.List;

public class MrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context        mContext;
    LayoutInflater mInflater;
    List<MrBean.DataBean>   mDatas;
    private static final int TYPE_ITEM   = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE     = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE     = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;
    private setOnItemClickListener mListener;

    public MrAdapter(Context context, List<MrBean.DataBean> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_mr, parent, false);


            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.load_more_footview_layout, parent, false);

            return new FooterViewHolder(itemView);
        }


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ItemViewHolder) {

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            if(mDatas.get(position).getState()==0){
                itemViewHolder.tv_item_mr_jzzt.setText("待确认");
                itemViewHolder.tv_item_mr_but.setVisibility(View.GONE);

            }else {
                //1：救助完成 2：等待救助 3：商家拒绝
                if(mDatas.get(position).getHelpState()==1){
                    itemViewHolder.tv_item_mr_jzzt.setText("救助完成");
                    itemViewHolder.tv_item_mr_but.setVisibility(View.GONE);


                }else if (mDatas.get(position).getHelpState()==2){
                    itemViewHolder.tv_item_mr_jzzt.setText("等待救助");
                    itemViewHolder.tv_item_mr_but.setVisibility(View.VISIBLE);
                    itemViewHolder.tv_item_mr_but.setText("填写救助结果");

                }else if (mDatas.get(position).getHelpState()==3){


                    itemViewHolder.tv_item_mr_jzzt.setText("已拒绝");
                    itemViewHolder.tv_item_mr_but.setVisibility(View.VISIBLE);
                    itemViewHolder.tv_item_mr_but.setText("更换维修商家");
                }else if (mDatas.get(position).getHelpState()==4){

                    itemViewHolder.tv_item_mr_but.setVisibility(View.GONE);
                    itemViewHolder.tv_item_mr_jzzt.setText("已取消");

                }
            }
            itemViewHolder.tv_item_mr_chepai.setText(mDatas.get(position).getCarNum());
            itemViewHolder.tv_item_mr_title.setText(mDatas.get(position).getProgramName());
            itemViewHolder.tv_item_mr_ddh.setText(mDatas.get(position).getSalvationCode());
//




        } else if (holder instanceof FooterViewHolder) {


            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;


            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.mTvLoadText.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.mTvLoadText.setText("正加载更多...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.mPbLoad.setVisibility(View.GONE);
                    footerViewHolder.mTvLoadText.setText("已经到底了");

                    break;

            }
        }

    }

    @Override
    public int getItemCount() {
        //RecyclerView的count设置为数据总条数+ 1（footerView）
        return mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            //最后一个item设置为footerView
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tv_item_mr_chepai,tv_item_mr_ddh,tv_item_mr_jzzt,tv_item_mr_title;
        SuperTextView tv_item_mr_but;



        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_item_mr_chepai=itemView.findViewById(R.id.tv_item_mr_chepai);
            tv_item_mr_ddh=itemView.findViewById(R.id.tv_item_mr_ddh);
            tv_item_mr_jzzt=itemView.findViewById(R.id.tv_item_mr_jzzt);
            tv_item_mr_title=itemView.findViewById(R.id.tv_item_mr_title);
            tv_item_mr_but=itemView.findViewById(R.id.tv_item_mr_but);



            if(mListener!=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.OnItemClickListener(getAdapterPosition());
                    }
                });
            }
        }

    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        ProgressBar  mPbLoad;

        TextView     mTvLoadText;

        LinearLayout mLoadLayout;
        public FooterViewHolder(View itemView) {
            super(itemView);
            mPbLoad=itemView.findViewById(R.id.pbLoad);
            mTvLoadText=itemView.findViewById(R.id.tvLoadText);
            mLoadLayout=itemView.findViewById(R.id.loadLayout);


        }
    }
    public interface setOnItemClickListener{
        void OnItemClickListener(int pos);
    }
    public void setOnItemClickListener(setOnItemClickListener mListener){
        this.mListener=mListener;
    }

    public void AddHeaderItem(List<MrBean.DataBean> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<MrBean.DataBean> items) {
        mDatas.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 更新加载更多状态
     * @param status
     */
    public void changeMoreStatus(int status){
        mLoadMoreStatus=status;
        notifyDataSetChanged();
    }
}
