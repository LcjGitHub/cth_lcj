package com.chetuhui.lcj.chezhubao_x.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AmcBean;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

public class VehicleDetailsActivity extends ActivityBase {

    private CommonTitleBar mTitlebarVd;
    /**  */
    private TextView mTvVdCph;
    /**  */
    private TextView mTvVdSyr;
    /**  */
    private TextView mTvVdPpxh;
    /**  */
    private TextView mTvVdClsbdh;
    /**  */
    private TextView mTvVdFdjxh;
    /**
     *
     */
    private ImageView mIvVdXszzp;
    private AmcBean.DataBean mBean=new AmcBean.DataBean();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        mBean=(AmcBean.DataBean)getIntent().getSerializableExtra("amc_bean");
        initView();
    }

    private void initView() {
        mTitlebarVd = (CommonTitleBar) findViewById(R.id.titlebar_vd);
        mTitlebarVd.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

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

        mTvVdCph = (TextView) findViewById(R.id.tv_vd_cph);
        mTvVdSyr = (TextView) findViewById(R.id.tv_vd_syr);
        mTvVdPpxh = (TextView) findViewById(R.id.tv_vd_ppxh);
        mTvVdClsbdh = (TextView) findViewById(R.id.tv_vd_clsbdh);
        mTvVdFdjxh = (TextView) findViewById(R.id.tv_vd_fdjxh);
        mIvVdXszzp = (ImageView) findViewById(R.id.iv_vd_xszzp);
        Log.d("VehicleDetailsActivity", ">>>>>"+mBean.getCarNum());
        mTvVdCph.setText(mBean.getCarNum());
        mTvVdSyr.setText(mBean.getOwner());
        mTvVdPpxh.setText(mBean.getCarBrand());
        mTvVdClsbdh.setText(mBean.getCarCode());
        mTvVdFdjxh.setText(mBean.getEngineNum());
        ShowImageUtils.showImageViewToCircle(VehicleDetailsActivity.this,mBean.getLicensePicture(),mIvVdXszzp,10);
//





    }
}
