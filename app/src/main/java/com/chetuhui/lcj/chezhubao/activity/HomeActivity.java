package com.chetuhui.lcj.chezhubao.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.utils.DataGenerator;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.tabview.CustomTabView;

public class HomeActivity extends ActivityBase implements CustomTabView.OnTabCheckListener{
    private CustomTabView mCustomTabView;
    private Fragment[]mFragmensts;
    //双击返回键 退出
    //----------------------------------------------------------------------------------------------
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mFragmensts = DataGenerator.getFragments();
        initView();

    }

    private void initView() {
        mCustomTabView = (CustomTabView) findViewById(R.id.custom_tab_container);
        CustomTabView.Tab tabHome = new CustomTabView.Tab().setText("首页")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.tab_home1)
                .setPressedIcon(R.drawable.tab_home2);
        mCustomTabView.addTab(tabHome);
        CustomTabView.Tab tabDis = new CustomTabView.Tab().setText("互助")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.tab_huzhu1)
                .setPressedIcon(R.drawable.tab_huzhu2);
        mCustomTabView.addTab(tabDis);
        CustomTabView.Tab tabAttention = new CustomTabView.Tab().setText("发现")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.tab_faxian1)
                .setPressedIcon(R.drawable.tab_faxian2);
        mCustomTabView.addTab(tabAttention);
        CustomTabView.Tab tabProfile = new CustomTabView.Tab().setText("我的")
                .setColor(getResources().getColor(android.R.color.darker_gray))
                .setCheckedColor(getResources().getColor(android.R.color.black))
                .setNormalIcon(R.drawable.tab_user1)
                .setPressedIcon(R.drawable.tab_user2);
        mCustomTabView.addTab(tabProfile);

        mCustomTabView.setOnTabCheckListener(this);

        mCustomTabView.setCurrentItem(0);

    }

    @Override
    public void onTabSelected(View v, int position) {
        onTabItemSelected(position);
    }

    private void onTabItemSelected(int position){
        Fragment fragment = null;
        switch (position){
            default:
                break;
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
//                fragment = mFragmensts[2];
                BaseToast.success("正在开发");
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再次点击返回键退出", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}