package com.chetuhui.lcj.chezhubao_x.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.model.UserlistBean;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class CarAdapter extends BaseQuickAdapter<CarBean.DataBean, BaseViewHolder> {
    private Map<Integer, Boolean> map = new HashMap<>();

public CarAdapter(@LayoutRes int layoutResId, @Nullable List<CarBean.DataBean> data) {
        super(layoutResId, data);
        }

@Override
protected void convert(BaseViewHolder helper, CarBean.DataBean item) {
        //可链式调用赋值
        helper
        .setText(R.id.tv_item_select_car_name, DataTool.hideMobilePhone4(item.getCarBrand()))
        .setText(R.id.tv_item_select_car_code, item.getCarNum());
    final int position = helper.getLayoutPosition();
    CheckBox mCheckBox=helper.getView(R.id.cb_item_select_car);
    mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked == true) {
                map.put(position, true);
            } else {
                map.remove(position);
            }
        }
    });
    if (map != null && map.containsKey(position)) {
      mCheckBox.setChecked(true);
    } else {
       mCheckBox.setChecked(false);
    }

    Glide.with(mContext).load(item.getLicensePicture()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) helper.getView(R.id.iv_item_select_car));

//    Glide.with().load("https://www.baid.com/a.jpg").into(imageView);//加载网络图片


        //获取当前条目position
        //int position = helper.getLayoutPosition();
        }
        }
