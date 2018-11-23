package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;

public class AuditorActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAuditor;
    /**
     * 请填写联系人姓名
     */
    private EditText mEtAuditorName;
    /**
     * 请输入
     */
    private EditText mEtAuditorPh;
    /**  */
    private EditText mEtAuditorXxdz;
    /**
     * 下一步
     */
    private SuperTextView mTvAuditorXiayibu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor);
        initView();
    }

    private void initView() {
        mTitlebarAuditor = (CommonTitleBar) findViewById(R.id.titlebar_auditor);
        mTitlebarAuditor.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mEtAuditorName = (EditText) findViewById(R.id.et_auditor_name);
        mEtAuditorName.setOnClickListener(this);
        mEtAuditorPh = (EditText) findViewById(R.id.et_auditor_ph);
        mEtAuditorPh.setOnClickListener(this);
        mEtAuditorXxdz = (EditText) findViewById(R.id.et_auditor_xxdz);
        mEtAuditorXxdz.setOnClickListener(this);
        mTvAuditorXiayibu = (SuperTextView) findViewById(R.id.tv_auditor_xiayibu);
        mTvAuditorXiayibu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.et_auditor_name:
                break;
            case R.id.et_auditor_ph:
                break;
            case R.id.et_auditor_xxdz:
                break;
            case R.id.tv_auditor_xiayibu:
                intent =new Intent(AuditorActivity.this,ConfirmActivity.class);
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
