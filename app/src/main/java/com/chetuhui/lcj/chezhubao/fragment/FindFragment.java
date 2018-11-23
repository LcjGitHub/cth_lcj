package com.chetuhui.lcj.chezhubao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.activity.AddCarActivity;
import com.chetuhui.lcj.chezhubao.activity.IHelpActivity;
import com.chetuhui.lcj.chezhubao.activity.MutualAssistanceActivity;
import com.chetuhui.lcj.chezhubao.activity.MutualRecordActivity;
import com.chetuhui.lcj.chezhubao.activity.MyMutualHelpActivity;
import com.chetuhui.lcj.chezhubao.activity.SelectCarActivity;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;


public class FindFragment extends Fragment implements View.OnClickListener {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒

    private View view;
    private CommonTitleBar mTitlebarFind;
    /**
     * 我的互助单
     */
    private TextView mTvFindWdhzd;
    /**
     * 我要互助
     */
    private TextView mTvFindWyhz;
    /**
     * 互助记录
     */
    private TextView mTvFindHzjl;
    /**
     * 全部车辆
     */
    private TextView mTvFindQbcl;
    private LinearLayout mLlHome2;
    private RecyclerView mRvFindHuzhucheliang;
    /**
     * 添加互助车辆
     */
    private TextView mTvFindTianjiacheliang;
    /**
     * 100元享受每年2次维修机会
     */
    private TextView mTvFindTitle;
    /**
     * 剩余维修次数：2
     */
    private TextView mTvFindCishu;
    /**
     * 保障期限：2018-09-21 至2019-09-21
     */
    private TextView mTvFindTime;
    private ImageView mIvFindListpic;

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_layout, null);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitlebarFind = (CommonTitleBar) view.findViewById(R.id.titlebar_find);
        mTitlebarFind.setOnClickListener(this);
        mTvFindWdhzd = (TextView) view.findViewById(R.id.tv_find_wdhzd);
        mTvFindWdhzd.setOnClickListener(this);
        mTvFindWyhz = (TextView) view.findViewById(R.id.tv_find_wyhz);
        mTvFindWyhz.setOnClickListener(this);
        mTvFindHzjl = (TextView) view.findViewById(R.id.tv_find_hzjl);
        mTvFindHzjl.setOnClickListener(this);
        mTvFindQbcl = (TextView) view.findViewById(R.id.tv_find_qbcl);
        mTvFindQbcl.setOnClickListener(this);
        mLlHome2 = (LinearLayout) view.findViewById(R.id.ll_home_2);
        mLlHome2.setOnClickListener(this);
        mRvFindHuzhucheliang = (RecyclerView) view.findViewById(R.id.rv_find_huzhucheliang);
        mRvFindHuzhucheliang.setOnClickListener(this);
        mTvFindTianjiacheliang = (TextView) view.findViewById(R.id.tv_find_tianjiacheliang);
        mTvFindTianjiacheliang.setOnClickListener(this);
        mTvFindTitle = (TextView) view.findViewById(R.id.tv_find_title);
        mTvFindTitle.setOnClickListener(this);
        mTvFindCishu = (TextView) view.findViewById(R.id.tv_find_cishu);
        mTvFindCishu.setOnClickListener(this);
        mTvFindTime = (TextView) view.findViewById(R.id.tv_find_time);
        mTvFindTime.setOnClickListener(this);
        mIvFindListpic = (ImageView) view.findViewById(R.id.iv_find_listpic);
        mIvFindListpic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent=null;
            switch (v.getId()) {
                default:
                    break;
                
                case R.id.tv_find_wdhzd:
                    intent=new Intent(getContext(), MyMutualHelpActivity.class);
                    break;
                case R.id.tv_find_wyhz:
                    intent=new Intent(getContext(), IHelpActivity.class);
                    break;
                case R.id.tv_find_hzjl:
                    intent=new Intent(getContext(), MutualRecordActivity.class);
                    break;
                case R.id.tv_find_qbcl:
                    intent=new Intent(getContext(), SelectCarActivity.class);
                    break;
                case R.id.ll_home_2:
                    break;
                case R.id.rv_find_huzhucheliang:
                    break;
                case R.id.tv_find_tianjiacheliang:
                    intent=new Intent(getContext(), SelectCarActivity.class);
                    break;
                case R.id.tv_find_title:
                    break;
                case R.id.tv_find_cishu:
                    break;
                case R.id.tv_find_time:
                    break;
                case R.id.iv_find_listpic:
                    break;
            }
            if (intent!=null){
                startActivity(intent);
            }
        }else {
            BaseToast.normal("点得太快了");
            return;
        }
    }
}
