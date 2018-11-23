package com.chetuhui.lcj.chezhubao.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.fragment.MessageFragment;
import com.chetuhui.lcj.chezhubao.fragment.NoticeFragment;
import com.chetuhui.lcj.chezhubao.tool.interfaces.OnRepeatClickListener;
import com.gyf.barlibrary.ImmersionBar;

public class MessageActivity extends ActivityBase {

    private TabLayout mTabsMessg;
    private ImageView mIvMessgBack;
    private ViewPager mViewpagerMessg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ImmersionBar.with(this)
                .statusBarColor(R.color._6)
                .init();
        initView();
        setTabsMessg();
    }

    private void initView() {
        mTabsMessg = (TabLayout) findViewById(R.id.tabs_messg_);
        mIvMessgBack = (ImageView) findViewById(R.id.iv_messg_back);
        mIvMessgBack.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                finish();
            }
        });
        mViewpagerMessg = (ViewPager) findViewById(R.id.viewpager_messg_);
    }
    private void  setTabsMessg(){
        mViewpagerMessg.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            String[] titles = new String[]{
                    "公告消息", "系统通知"
            };

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return NoticeFragment.newInstance();

                } else {
                    return MessageFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabsMessg.setupWithViewPager(mViewpagerMessg);
    }
}
