package com.chetuhui.lcj.chezhubao_x.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;


/**
 * @author vondear
 * @date 2016/7/19
 * Mainly used for confirmation and cancel.
 */
public class DialogEditSureCancelYuan extends BaseDialog {

    private ImageView mIvLogo;
    private TextView mTvSure;
    private TextView mTvCancel;
    private EditText editText;
    private TextView mTvTitle;

    public DialogEditSureCancelYuan(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public DialogEditSureCancelYuan(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public DialogEditSureCancelYuan(Context context) {
        super(context);
        initView();
    }

    public DialogEditSureCancelYuan(Activity context) {
        super(context);
        initView();
    }

    public DialogEditSureCancelYuan(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public ImageView getLogoView() {
        return mIvLogo;
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public TextView getTitleView() {
        return mTvTitle;
    }

    public EditText getEditText() {
        return editText;
    }

    public TextView getSureView() {
        return mTvSure;
    }

    public void setSure(String strSure) {
        this.mTvSure.setText(strSure);
    }

    public TextView getCancelView() {
        return mTvCancel;
    }


    public void setCancel(String strCancel) {
        this.mTvCancel.setText(strCancel);
    }
    public void setSureListener(View.OnClickListener sureListener) {
        mTvSure.setOnClickListener(sureListener);
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        mTvCancel.setOnClickListener(cancelListener);
    }
    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edittext_sure_false_yuan, null);
        mIvLogo = (ImageView) dialogView.findViewById(R.id.iv_logo);
        mTvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        mTvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        mTvCancel = (TextView) dialogView.findViewById(R.id.tv_cancle);
        editText = (EditText) dialogView.findViewById(R.id.editText);
        setContentView(dialogView);
    }
}
