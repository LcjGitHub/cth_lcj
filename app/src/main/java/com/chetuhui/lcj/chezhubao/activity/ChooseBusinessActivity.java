package com.chetuhui.lcj.chezhubao.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;

public class ChooseBusinessActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarCb;
    private RelativeLayout mRlCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_business);
        initView();
    }


    public static void showView(Context context) {
        final BaseDialog rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_shangjia_show, null);
        TextView qrxz = view.findViewById(R.id.tv_dg_shangjia_qrxz);
        ImageView cancel = view.findViewById(R.id.iv_dg_shangjia);
        qrxz.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                BaseToast.success("选择成功，可在互助记录查看信息");
                rxDialog.cancel();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialog.cancel();
            }
        });


        rxDialog.setContentView(view);
        rxDialog.getLayoutParams();
        rxDialog.mLayoutParams.gravity = Gravity.BOTTOM;

        rxDialog.show();
        rxDialog.setFullScreen();
    }

    private void initView() {
        mTitlebarCb = (CommonTitleBar) findViewById(R.id.titlebar_cb);
        mTitlebarCb.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mRlCb = (RelativeLayout) findViewById(R.id.rl_cb_);
        mRlCb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_cb_:
                showView(ChooseBusinessActivity.this);

                break;
        }
    }
}
