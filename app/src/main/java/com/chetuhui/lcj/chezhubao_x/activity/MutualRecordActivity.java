package com.chetuhui.lcj.chezhubao_x.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.utils.DataGenerator;
import com.chetuhui.lcj.chezhubao_x.view.tabview.CustomTabViewBottom;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

public class MutualRecordActivity extends ActivityBase implements CustomTabViewBottom.OnTabCheckListener {

    private CommonTitleBar mTitlebarMr;
    private CustomTabViewBottom mCustomTabMr;
    private FrameLayout mMrContainer;
    private Fragment[] mFragmensts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutual_record);
        mFragmensts = DataGenerator.getFragments_mr();
        initView();
    }

    private void initView() {
        mTitlebarMr = (CommonTitleBar) findViewById(R.id.titlebar_mr);
        mTitlebarMr.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });


        mCustomTabMr = (CustomTabViewBottom) findViewById(R.id.custom_tab_mr);

        mMrContainer = (FrameLayout) findViewById(R.id.mr_container);
        CustomTabViewBottom.Tab tabHome = new CustomTabViewBottom.Tab().setText("全部")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMr.addTab(tabHome);
        CustomTabViewBottom.Tab tabbzz = new CustomTabViewBottom.Tab().setText("待确认")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMr.addTab(tabbzz);
        CustomTabViewBottom.Tab tabdsx = new CustomTabViewBottom.Tab().setText("等待救助")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMr.addTab(tabdsx);
        CustomTabViewBottom.Tab tabysx = new CustomTabViewBottom.Tab().setText("已拒绝")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMr.addTab(tabysx);

        mCustomTabMr.setOnTabCheckListener(this);

        mCustomTabMr.setCurrentItem(0);
    }


    @Override
    public void onTabSelected(View v, int position) {
        onTabItemSelected(position);
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position) {
            default:
                break;
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;
            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;


        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mr_container, fragment).commit();
        }
    }

}
