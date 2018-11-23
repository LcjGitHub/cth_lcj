package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;

public class SelectReviewActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarSr;
    private CheckBox mCbSrFuwuzhan;
    private ImageView mIvSr1;
    private CheckBox mCbSrShenheyuan;
    private ImageView mIvSr2;
    /**
     * 确定
     */
    private SuperTextView mTvSrQueding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_review);
        initView();
    }

    private void initView() {
        mTitlebarSr = (CommonTitleBar) findViewById(R.id.titlebar_sr);
        mTitlebarSr.setListener(new CommonTitleBar.OnTitleBarListener() {
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


        mCbSrFuwuzhan = (CheckBox) findViewById(R.id.cb_sr_fuwuzhan);
        mCbSrFuwuzhan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mCbSrShenheyuan.setChecked(false);

                }else {


                }


            }
        });


        mIvSr1 = (ImageView) findViewById(R.id.iv_sr_1);
        mIvSr1.setOnClickListener(this);
        mCbSrShenheyuan = (CheckBox) findViewById(R.id.cb_sr_shenheyuan);
        mCbSrShenheyuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mCbSrFuwuzhan.setChecked(false);
                }
            }
        });
        mIvSr2 = (ImageView) findViewById(R.id.iv_sr_2);
        mIvSr2.setOnClickListener(this);
        mTvSrQueding = (SuperTextView) findViewById(R.id.tv_sr_queding);
        mTvSrQueding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.titlebar_sr:
                break;
            case R.id.cb_sr_fuwuzhan:
                break;
            case R.id.iv_sr_1:
                break;
            case R.id.cb_sr_shenheyuan:
                break;
            case R.id.iv_sr_2:
                break;
            case R.id.tv_sr_queding:
                if (mCbSrShenheyuan.isChecked()){
                    intent=new Intent(SelectReviewActivity.this,AuditorActivity.class);
                }else if(mCbSrFuwuzhan.isChecked()){
                    intent=new Intent(SelectReviewActivity.this,AuditStationActivity.class);

                }else {
                    BaseToast.error("请选择审核方式");
                }

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
