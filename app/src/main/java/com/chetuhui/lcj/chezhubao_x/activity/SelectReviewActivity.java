package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

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
    private RelativeLayout rl_fwz;

    private CheckBox mCbSrZizhu;
    private RelativeLayout mRlZizhu;
    private RelativeLayout rl_shy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_review);
        initView();
    }

    private void initView() {
        mTitlebarSr = (CommonTitleBar) findViewById(R.id.titlebar_sr);
        rl_fwz = (RelativeLayout) findViewById(R.id.rl_fwz);
        rl_shy = (RelativeLayout) findViewById(R.id.rl_shy);
        rl_fwz.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {


            }
        });
        rl_shy.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                setchecked();
                mCbSrShenheyuan.setChecked(true);
            }
        });

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



        mIvSr1 = (ImageView) findViewById(R.id.iv_sr_1);
        mIvSr1.setOnClickListener(this);
        mCbSrShenheyuan = (CheckBox) findViewById(R.id.cb_sr_shenheyuan);
        mIvSr2 = (ImageView) findViewById(R.id.iv_sr_2);
        mIvSr2.setOnClickListener(this);
        mTvSrQueding = (SuperTextView) findViewById(R.id.tv_sr_queding);
        mTvSrQueding.setOnClickListener(this);
        mCbSrZizhu = (CheckBox) findViewById(R.id.cb_sr_zizhu);
        mRlZizhu = (RelativeLayout) findViewById(R.id.rl_zizhu);

        mRlZizhu.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                setchecked();
                mCbSrZizhu.setChecked(true);
            }
        });


    }

    private void setchecked() {
        mCbSrZizhu.setChecked(false);
        mCbSrShenheyuan.setChecked(false);
        mCbSrFuwuzhan.setChecked(false);
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
                    setchecked();
                    mCbSrFuwuzhan.setChecked(true);
                    break;
                case R.id.iv_sr_1:
                    break;
                case R.id.cb_sr_shenheyuan:
                    setchecked();
                    mCbSrShenheyuan.setChecked(true);
                    break;
                case R.id.iv_sr_2:
                    break;
                case R.id.tv_sr_queding:
                    if (mCbSrShenheyuan.isChecked()) {
                        SPTool.putInt(SelectReviewActivity.this, "or_shenhe", 1);
                        intent = new Intent(SelectReviewActivity.this, AuditorActivity.class);


                    } else if (mCbSrFuwuzhan.isChecked()) {
                        SPTool.putInt(SelectReviewActivity.this, "or_shenhe", 2);
                        intent = new Intent(SelectReviewActivity.this, AuditStationActivity.class);

                    } else if (mCbSrZizhu.isChecked()) {
                        SPTool.putInt(SelectReviewActivity.this, "or_shenhe", 3);
                        intent = new Intent(SelectReviewActivity.this, SelfAuditActivity.class);

                    } else {
                        BaseToast.error("请选择审核方式");
                    }

                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }

        } else {
            //BaseToast.normal("点得太快了");
            return;
        }
    }
}
