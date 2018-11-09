package com.chetuhui.lcj.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.myapplication.MainActivity;
import com.chetuhui.lcj.myapplication.R;
import com.chetuhui.lcj.myapplication.activity.JoinActivity;
import com.chetuhui.lcj.myapplication.activity.MessageActivity;
import com.chetuhui.lcj.myapplication.tool.BaseTool;
import com.chetuhui.lcj.myapplication.view.BaseToast;
import com.chetuhui.lcj.myapplication.view.titlebar.CommonTitleBar;


/**
 * Created by zhouwei on 17/4/23.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private final int MIN_CLICK_DELAY_TIME = 1000;
    private View view;
    private CommonTitleBar mTitlebarHome;
    /**
     * 123456
     */
    private TextView mTvHomeMoney;
    /**
     * 已有23423辆车加入
     */
    private TextView mTvHomeLiangche;
    private LinearLayout mLlHomeMoneyCenter;
    /**
     * 用户138****2325加入了车助宝互助平台，获得1年1500元的维修基金
     */
    private TextView mTvHomeScroll;
    /**
     * 账户公式
     */
    private TextView mTvHomeZhanghugongshi;
    /**
     * 互助事件
     */
    private TextView mTvHomeHuzhushijian;
    /**
     * 用户列表
     */
    private TextView mTvHomeYonghuliebiao;
    /**
     * 热门问题
     */
    private TextView mTvHomeRemenshijian;

    /**
     * 申请互助
     */
    private TextView mTvHomeShenqinghuzhu;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home_layout, null);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitlebarHome = (CommonTitleBar) view.findViewById(R.id.titlebar_home);
        mTitlebarHome.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_RIGHT_BUTTON) {
                    Intent intent=new Intent(getContext(), MessageActivity.class);
                    startActivity(intent);
                    return;

                }
                // CommonTitleBar.ACTION_LEFT_TEXT;        // 左边TextView被点击
                // CommonTitleBar.ACTION_LEFT_BUTTON;      // 左边ImageBtn被点击
                // CommonTitleBar.ACTION_RIGHT_TEXT;       // 右边TextView被点击
                // CommonTitleBar.ACTION_RIGHT_BUTTON;     // 右边ImageBtn被点击
                // CommonTitleBar.ACTION_SEARCH;           // 搜索框被点击,搜索框不可输入的状态下会被触发
                // CommonTitleBar.ACTION_SEARCH_SUBMIT;    // 搜索框输入状态下,键盘提交触发，此时，extra为输入内容
                // CommonTitleBar.ACTION_SEARCH_VOICE;     // 语音按钮被点击
                // CommonTitleBar.ACTION_SEARCH_DELETE;    // 搜索删除按钮被点击
                // CommonTitleBar.ACTION_CENTER_TEXT;      // 中间文字点击
            }
        });



        mTvHomeMoney = (TextView) view.findViewById(R.id.tv_home_money_);
        mTvHomeMoney.setOnClickListener(this);
        mTvHomeLiangche = (TextView) view.findViewById(R.id.tv_home_liangche);
        mTvHomeLiangche.setOnClickListener(this);
        mLlHomeMoneyCenter = (LinearLayout) view.findViewById(R.id.ll_home_money_center);
        mLlHomeMoneyCenter.setOnClickListener(this);
        mTvHomeScroll = (TextView) view.findViewById(R.id.tv_home_scroll);
        mTvHomeScroll.setOnClickListener(this);
        mTvHomeZhanghugongshi = (TextView) view.findViewById(R.id.tv_home_zhanghugongshi);
        mTvHomeZhanghugongshi.setOnClickListener(this);
        mTvHomeHuzhushijian = (TextView) view.findViewById(R.id.tv_home_huzhushijian);
        mTvHomeHuzhushijian.setOnClickListener(this);
        mTvHomeYonghuliebiao = (TextView) view.findViewById(R.id.tv_home_yonghuliebiao);
        mTvHomeYonghuliebiao.setOnClickListener(this);
        mTvHomeRemenshijian = (TextView) view.findViewById(R.id.tv_home_remenshijian);
        mTvHomeRemenshijian.setOnClickListener(this);
        mTvHomeShenqinghuzhu = (TextView) view.findViewById(R.id.tv_home_shenqinghuzhu);
        mTvHomeShenqinghuzhu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent=null;
            switch (v.getId()) {
                default:
                    break;

                case R.id.tv_home_money_:
                    break;
                case R.id.tv_home_liangche:
                    break;
                case R.id.ll_home_money_center:
                    break;
                case R.id.tv_home_scroll:
                    break;
                case R.id.tv_home_zhanghugongshi:
                    break;
                case R.id.tv_home_huzhushijian:
                    break;
                case R.id.tv_home_yonghuliebiao:
                    break;
                case R.id.tv_home_remenshijian:
                    break;
                case R.id.tv_home_shenqinghuzhu:
                    intent=new Intent(getContext(), JoinActivity.class);
                    break;
            }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
            BaseToast.normal("点得太快了");
            return;
        }
    }
}
