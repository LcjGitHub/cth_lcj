package com.chetuhui.lcj.chezhubao_x;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chetuhui.lcj.chezhubao_x.model.AdBean;

public class MyMultipleItem implements MultiItemEntity {
    public static final int FIRST_TYPE = 1;
    public static final int SECOND_TYPE = 2;


    private int itemType;
    private AdBean.DataBean data;
    private AdBean.DataBean.MubillRecordVoListBean mMubillRecordVoListBean;

    public MyMultipleItem(int itemType, AdBean.DataBean data,AdBean.DataBean.MubillRecordVoListBean mubillRecordVoListBean) {
        this.itemType = itemType;
        this.data = data;
        this.mMubillRecordVoListBean = mubillRecordVoListBean;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public AdBean.DataBean getData(){
        return data;
    }
    public AdBean.DataBean.MubillRecordVoListBean getDatam(){
        return mMubillRecordVoListBean;
    }
}