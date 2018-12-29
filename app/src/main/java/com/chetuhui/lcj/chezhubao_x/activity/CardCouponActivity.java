package com.chetuhui.lcj.chezhubao_x.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.fragment.CardCouponFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MessageFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.NoticeFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.RedEnvelopeFragment;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

public class CardCouponActivity extends ActivityBase {

    private CommonTitleBar mTitlebarCc;
    private TabLayout mTabsCc;
    private ViewPager mViewpagerCc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_coupon);
        initView();
        setTabsMessg();
    }

    private void initView() {
        mTitlebarCc = (CommonTitleBar) findViewById(R.id.titlebar_cc);
        mTitlebarCc.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mTabsCc = (TabLayout) findViewById(R.id.tabs_cc);
        mViewpagerCc = (ViewPager) findViewById(R.id.viewpager_cc);
    }
    private void  setTabsMessg(){
        mViewpagerCc.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            String[] titles = new String[]{
                    "红包", "抵用券"
            };

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return RedEnvelopeFragment.newInstance();

                } else {
                    return CardCouponFragment.newInstance();
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
        mTabsCc.setupWithViewPager(mViewpagerCc);
    }

}
