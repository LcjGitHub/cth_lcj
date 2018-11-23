package com.chetuhui.lcj.chezhubao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.activity.ChangephoneActivity;
import com.chetuhui.lcj.chezhubao.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao.activity.RegisteredActivity;
import com.chetuhui.lcj.chezhubao.activity.ReplaceActivity;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;


public class MeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private CommonTitleBar mTitlebarMe;
    /**
     * 不加糖
     */
    private TextView mTvMeName;
    /**
     * 不加糖
     */
    private TextView mTvMePh;
    /**
     * 不加糖
     */
    private TextView mTvMeDay;
    /**
     * 待付款
     */
    private TextView mTvMeDfk;
    /**
     * 待使用
     */
    private TextView mTvMeDsy;
    /**
     * 待评价
     */
    private TextView mTvMeDpj;
    /**
     * 售后/退款
     */
    private TextView mTvMeTuikuan;
    /**
     * 个人信息
     */
    private TextView mTvMeGrxx;
    /**
     * 红包卡卷
     */
    private TextView mTvMeKajuan;
    /**
     * 关于我们
     */
    private TextView mTvMeAbout;
    /**
     * 邀请好友
     */
    private TextView mTvMeHaoyou;
    private RelativeLayout mRlMeZhanghao;
    private RelativeLayout mRlMeLianxi;
    private RelativeLayout mRlMeYijian;
    private RelativeLayout mRlMeJiancha;
    /**
     * 退出登录
     */
    private SuperTextView mTvMeTuichu;

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_me_layout, null);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitlebarMe = (CommonTitleBar) view.findViewById(R.id.titlebar_me);
        mTitlebarMe.setOnClickListener(this);
        mTvMeName = (TextView) view.findViewById(R.id.tv_me_name);
        mTvMeName.setOnClickListener(this);
        mTvMePh = (TextView) view.findViewById(R.id.tv_me_ph);
        mTvMePh.setOnClickListener(this);
        mTvMeDay = (TextView) view.findViewById(R.id.tv_me_day);
        mTvMeDay.setOnClickListener(this);
        mTvMeDfk = (TextView) view.findViewById(R.id.tv_me_dfk);
        mTvMeDfk.setOnClickListener(this);
        mTvMeDsy = (TextView) view.findViewById(R.id.tv_me_dsy);
        mTvMeDsy.setOnClickListener(this);
        mTvMeDpj = (TextView) view.findViewById(R.id.tv_me_dpj);
        mTvMeDpj.setOnClickListener(this);
        mTvMeTuikuan = (TextView) view.findViewById(R.id.tv_me_tuikuan);
        mTvMeTuikuan.setOnClickListener(this);
        mTvMeGrxx = (TextView) view.findViewById(R.id.tv_me_grxx);
        mTvMeGrxx.setOnClickListener(this);
        mTvMeKajuan = (TextView) view.findViewById(R.id.tv_me_kajuan);
        mTvMeKajuan.setOnClickListener(this);
        mTvMeAbout = (TextView) view.findViewById(R.id.tv_me_about);
        mTvMeAbout.setOnClickListener(this);
        mTvMeHaoyou = (TextView) view.findViewById(R.id.tv_me_haoyou);
        mTvMeHaoyou.setOnClickListener(this);
        mRlMeZhanghao = (RelativeLayout) view.findViewById(R.id.rl_me_zhanghao);
        mRlMeZhanghao.setOnClickListener(this);
        mRlMeLianxi = (RelativeLayout) view.findViewById(R.id.rl_me_lianxi);
        mRlMeLianxi.setOnClickListener(this);
        mRlMeYijian = (RelativeLayout) view.findViewById(R.id.rl_me_yijian);
        mRlMeYijian.setOnClickListener(this);
        mRlMeJiancha = (RelativeLayout) view.findViewById(R.id.rl_me_jiancha);
        mRlMeJiancha.setOnClickListener(this);
        mTvMeTuichu = (SuperTextView) view.findViewById(R.id.tv_me_tuichu);
        mTvMeTuichu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {

            Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.titlebar_me:
                break;
            case R.id.tv_me_name:
                break;
            case R.id.tv_me_ph:
                break;
            case R.id.tv_me_day:
                break;
            case R.id.tv_me_dfk:
                break;
            case R.id.tv_me_dsy:
                break;
            case R.id.tv_me_dpj:
                break;
            case R.id.tv_me_tuikuan:
                break;
            case R.id.tv_me_grxx:
                break;
            case R.id.tv_me_kajuan:
                break;
            case R.id.tv_me_about:
                break;
            case R.id.tv_me_haoyou:
                break;
            case R.id.rl_me_zhanghao:
                intent=new Intent(getContext(), ChangephoneActivity.class);
                break;
            case R.id.rl_me_lianxi:
                break;
            case R.id.rl_me_yijian:
                break;
            case R.id.rl_me_jiancha:
                break;
            case R.id.tv_me_tuichu:
                ActivityTool.finishAllActivity();
                SPTool.remove(getContext(),"token");
                startActivity(new Intent(getContext(),LoginActivity.class));


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
