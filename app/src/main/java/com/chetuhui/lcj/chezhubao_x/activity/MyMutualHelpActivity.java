package com.chetuhui.lcj.chezhubao_x.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.fragment.HomeFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MmhAllFragment;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.utils.DataGenerator;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.tabview.CustomTabViewBottom;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

public class MyMutualHelpActivity extends ActivityBase implements CustomTabViewBottom.OnTabCheckListener{

    private CommonTitleBar mTitlebarMmh;
    private Fragment[] mFragmensts;
    /**
     * 请输入车牌号搜索
     */
    private EditText mEtMmhSousuo;
    private CustomTabViewBottom mCustomTabMmh;
    private FrameLayout mMmhContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mutual_help);
        mFragmensts = DataGenerator.getFragments_mmh();
        initView();
    }

    private void initView() {
        mTitlebarMmh = (CommonTitleBar) findViewById(R.id.titlebar_mmh);
        mTitlebarMmh.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });


        mEtMmhSousuo = (EditText) findViewById(R.id.et_mmh_sousuo);


        mEtMmhSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i==0||i==3)&&keyEvent!=null){
                    if (!DataTool.isNullString(mEtMmhSousuo.getText().toString())){
                        Message msg = Message.obtain(); // 实例化消息对象
                        msg.what = 1; // 消息标识
                        msg.obj = ""+mEtMmhSousuo.getText().toString(); // 消息内容存放
                        MmhAllFragment.mhandler.sendMessage(msg);

//                        getN_findCarByCode(mEtMmhSousuo.getText().toString());
                        mCustomTabMmh.setCurrentItem(0);
                    }else {
                        BaseToast.success("搜索为空");
                    }

                    return true;
                }

                return false;
            }
        });


        mCustomTabMmh = (CustomTabViewBottom) findViewById(R.id.custom_tab_mmh);
        mMmhContainer = (FrameLayout) findViewById(R.id.mmh_container);

        CustomTabViewBottom.Tab tabHome = new CustomTabViewBottom.Tab().setText("全部")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMmh.addTab(tabHome);
        CustomTabViewBottom.Tab tabbzz = new CustomTabViewBottom.Tab().setText("保障中")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMmh.addTab(tabbzz);
        CustomTabViewBottom.Tab tabdsx = new CustomTabViewBottom.Tab().setText("待生效")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMmh.addTab(tabdsx);
        CustomTabViewBottom.Tab tabysx = new CustomTabViewBottom.Tab().setText("已失效")
                .setColor(getResources().getColor(R.color._6))
                .setCheckedColor(getResources().getColor(R.color.home_button))
                .setNormalIcon(R.drawable.shape_white)
                .setPressedIcon(R.drawable.shape_home_button);
        mCustomTabMmh.addTab(tabysx);

        mCustomTabMmh.setOnTabCheckListener(this);

        mCustomTabMmh.setCurrentItem(0);
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
            getSupportFragmentManager().beginTransaction().replace(R.id.mmh_container, fragment).commit();
        }
    }

}
