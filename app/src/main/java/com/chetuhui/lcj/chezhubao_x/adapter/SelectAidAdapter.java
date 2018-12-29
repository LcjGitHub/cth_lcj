package com.chetuhui.lcj.chezhubao_x.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AuditStationBean;
import com.chetuhui.lcj.chezhubao_x.model.SelectAidBean;

import java.util.ArrayList;
import java.util.List;


public class SelectAidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context        mContext;
    LayoutInflater mInflater;
    List<SelectAidBean.DataBean>   mDatas;
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
    private List<Boolean> booleanlist=new ArrayList<>();
    public SelectAidAdapter(Context context, List<SelectAidBean.DataBean> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        for (int i = 0; i < datas.size(); i++) {
            //设置默认的显示
            booleanlist.add(false);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.item_selectaid, parent, false);

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
            itemViewHolder.tv_item_select_car_code.setText(mDatas.get(position).getProgramName());
            itemViewHolder.tv_item_select_car_cod.setText("剩余次数："+mDatas.get(position).getMutualSize());
            itemViewHolder.tv_item_select_car_co.setText("保障期限："+mDatas.get(position).getEffectiveTime()+"至"+mDatas.get(position).getEndTime());
            itemViewHolder.cb_item_as.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //用集合保存当前的状态
                    booleanlist.set(position,isChecked);
                }
            });

            itemViewHolder.cb_item_as.setChecked(booleanlist.get(position));


//            itemViewHolder.cb_item_as.setChecked(booleanlist.get(position));
//            itemViewHolder.tv_item_userlist_time.setText("加入时间："+mDatas.get(position).getCreateTime());
//            Glide.with(mContext).load(mDatas.get(position).getHeadimgurl()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) itemViewHolder.iv_item_userlist);
//

//            String         str            = mDatas.get(position);
//            itemViewHolder.mTvContent.setText(str);

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

        TextView tv_item_select_car_code,tv_item_select_car_cod,tv_item_select_car_co;
        CheckBox cb_item_as;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_item_select_car_code=itemView.findViewById(R.id.tv_item_sa_title);
            tv_item_select_car_cod=itemView.findViewById(R.id.tv_item_sa_cishu);
            tv_item_select_car_co=itemView.findViewById(R.id.tv_item_sa_qixian);
            cb_item_as=itemView.findViewById(R.id.cb_item_sa);

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
    //更改集合内部存储的状态
    public void initCheck(boolean flag) {

        for (int i = 0; i < mDatas.size(); i++) {
            //更改指定位置的数据
            booleanlist.set(i,flag);
        }
    } public void initCheck(int p) {

        booleanlist.set(p,true);
    }
    public void unSelectAll(){
        initCheck(false);
        notifyDataSetChanged();
    }
    public void unSelect(int p){
        initCheck(p);
        notifyDataSetChanged();
    }

    public interface setOnItemClickListener{
        void OnItemClickListener(int pos);
    }
    public void setOnItemClickListener(setOnItemClickListener mListener){
        this.mListener=mListener;
    }
    public void AddHeaderItem(List<SelectAidBean.DataBean> items) {
        mDatas.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<SelectAidBean.DataBean> items) {
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
