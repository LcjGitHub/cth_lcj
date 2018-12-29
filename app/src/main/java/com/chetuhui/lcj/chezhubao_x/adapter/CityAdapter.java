package com.chetuhui.lcj.chezhubao_x.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.CityBean;
import com.chetuhui.lcj.chezhubao_x.tool.DBTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;

import java.util.List;

import cc.solart.turbo.BaseTurboAdapter;
import cc.solart.turbo.BaseViewHolder;

public class CityAdapter extends BaseTurboAdapter<CityBean.DataBean, BaseViewHolder> {

public CityAdapter(Context context) {
        super(context);
        }

public CityAdapter(Context context, List<CityBean.DataBean> data) {
        super(context, data);
        }

@Override
protected int getDefItemViewType(int position) {
    CityBean.DataBean city = getItem(position);
        return city.getId();
        }

@Override
protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
        return new CityHolder(inflateItemView(R.layout.item_wave_contact, parent));
        } else {
        return new PinnedHolder(inflateItemView(R.layout.item_pinned_header, parent));
        }
        }


@Override
protected void convert(BaseViewHolder holder, CityBean.DataBean item) {
        if (holder instanceof CityHolder) {
        ((CityHolder) holder).city_name.setText(item.getName());
        }else {
        String letter = DataTool.getPYFirstLetter(item.getName());
        ((PinnedHolder) holder).city_tip.setText(letter);
        }
        }

public int getLetterPosition(String letter){
        for (int i = 0 ; i < getData().size(); i++){
        if( DataTool.getPYFirstLetter(getData().get(i).getName()).equals(letter)){
        return i;
        }
        }
        return -1;
        }

class CityHolder extends BaseViewHolder {

    TextView city_name;

    public CityHolder(View view) {
        super(view);
        city_name = findViewById(R.id.city_name);
    }
}


class PinnedHolder extends BaseViewHolder {

    TextView city_tip;

    public PinnedHolder(View view) {
        super(view);
        city_tip = findViewById(R.id.city_tip);
    }
}

}