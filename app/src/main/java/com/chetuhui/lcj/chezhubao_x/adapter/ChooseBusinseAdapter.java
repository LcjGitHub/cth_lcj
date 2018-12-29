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

import com.bumptech.glide.Glide;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.ChooseBusinessBean;
import com.chetuhui.lcj.chezhubao_x.model.UserlistBean;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ChooseBusinseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context        mContext;
    LayoutInflater mInflater;
    List<ChooseBusinessBean.DataBean>   mDatas;
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

    public ChooseBusinseAdapter(Context context, List<ChooseBusinessBean.DataBean> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_choosebusiness, parent, false);

            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.load_more_footview_layout, parent, false);

            return new FooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.tv_item_choosebusines_dizhi.setText("地址："+mDatas.get(position).getDetailAd());
            itemViewHolder.tv_item_choosebusines_paidui.setText("当前排队 "+mDatas.get(position).getPeopleNum()+" 人");
            itemViewHolder.tv_item_choosebusines_title.setText(mDatas.get(position).getBusinessName());
            ShowImageUtils.showImageViewToCircle(mContext,mDatas.get(position).getCover(),itemViewHolder.iv_item_choosebusines,10);



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
    public interface setOnItemClickListener{
        void OnItemClickListener(int pos);
    }
    public void setOnItemClickListener(setOnItemClickListener mListener){
        this.mListener=mListener;
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

        TextView tv_item_choosebusines_title,tv_item_choosebusines_dizhi,tv_item_choosebusines_paidui;
        ImageView iv_item_choosebusines;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_item_choosebusines_title=itemView.findViewById(R.id.tv_item_choosebusines_title);
            tv_item_choosebusines_dizhi=itemView.findViewById(R.id.tv_item_choosebusines_dizhi);
            tv_item_choosebusines_paidui=itemView.findViewById(R.id.tv_item_choosebusines_paidui);

            iv_item_choosebusines=itemView.findViewById(R.id.iv_item_choosebusines);

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


    public void AddHeaderItem(List<ChooseBusinessBean.DataBean> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<ChooseBusinessBean.DataBean> items) {
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
