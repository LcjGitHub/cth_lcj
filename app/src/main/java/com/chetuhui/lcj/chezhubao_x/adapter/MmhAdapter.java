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
import com.chetuhui.lcj.chezhubao_x.model.GszBean;
import com.chetuhui.lcj.chezhubao_x.model.MmhBean;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

public class MmhAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context        mContext;
    LayoutInflater mInflater;
    List<MmhBean.DataBean>   mDatas;
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

    public MmhAdapter(Context context, List<MmhBean.DataBean> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_mmh, parent, false);


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
            //0：待生效 1：保障中 2：已用完 3：已失效
            if(mDatas.get(position).getState()==0){
                itemViewHolder.tv_item_mmh_tianshu.setText("待生效");
                ShowImageUtils.showImageView(mContext,R.drawable.wdhud_dsx,(ImageView) itemViewHolder.iv_item_mmh);


            }else if (mDatas.get(position).getState()==1){
                itemViewHolder.tv_item_mmh_tianshu.setText("已保障\n"+mDatas.get(position).getProtectionDays()+"天");
                ShowImageUtils.showImageView(mContext,R.drawable.huzhu_listpic,(ImageView) itemViewHolder.iv_item_mmh);

            }else if (mDatas.get(position).getState()==2){
                itemViewHolder.tv_item_mmh_tianshu.setText("已用完");
                ShowImageUtils.showImageView(mContext,R.drawable.wdhud_yyw,(ImageView) itemViewHolder.iv_item_mmh);

            }else if (mDatas.get(position).getState()==3){
                itemViewHolder.tv_item_mmh_tianshu.setText("已失效");
                ShowImageUtils.showImageView(mContext,R.drawable.wdhud_ysx,(ImageView) itemViewHolder.iv_item_mmh);

            }else if (mDatas.get(position).getState()==4){
                itemViewHolder.tv_item_mmh_tianshu.setText("已取消");
                ShowImageUtils.showImageView(mContext,R.drawable.wdhud_yqx,(ImageView) itemViewHolder.iv_item_mmh);

            }


            itemViewHolder.tv_item_mmh_title.setText(mDatas.get(position).getProgramName());
            itemViewHolder.tv_item_mmh_hzcl.setText("互助车辆："+mDatas.get(position).getCarNum());
            itemViewHolder.tv_item_mmh_sycs.setText("剩余维修次数："+mDatas.get(position).getMutualSize());
            itemViewHolder.tv_item_mmh_bzqx.setText("保障期限："+mDatas.get(position).getEffectiveTime()+" 至 "+mDatas.get(position).getEndTime());
//            Glide.with(mContext).load(mDatas.get(position).getUserUrl()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) itemViewHolder.iv_item_userlist);





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

        TextView tv_item_mmh_title,tv_item_mmh_hzcl,tv_item_mmh_sycs,tv_item_mmh_bzqx,tv_item_mmh_tianshu;
        ImageView iv_item_mmh;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_item_mmh_title=itemView.findViewById(R.id.tv_item_mmh_title);
            tv_item_mmh_tianshu=itemView.findViewById(R.id.tv_item_mmh_tianshu);
            tv_item_mmh_hzcl=itemView.findViewById(R.id.tv_item_mmh_hzcl);
            tv_item_mmh_sycs=itemView.findViewById(R.id.tv_item_mmh_sycs);
            tv_item_mmh_bzqx=itemView.findViewById(R.id.tv_item_mmh_bzqx);
            iv_item_mmh=itemView.findViewById(R.id.iv_item_mmh);


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

    public void AddHeaderItem(List<MmhBean.DataBean> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<MmhBean.DataBean> items) {
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
