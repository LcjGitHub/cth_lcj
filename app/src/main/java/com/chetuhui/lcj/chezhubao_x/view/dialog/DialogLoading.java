package com.chetuhui.lcj.chezhubao_x.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.progressing.SpinKitView;


/**
 * @author Vondear
 * @date 2017/3/16
 */

public class DialogLoading extends BaseDialog {

    private SpinKitView mLoadingView;
    private View mDialogContentView;
    private TextView mTextView;

    public DialogLoading(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public DialogLoading(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public DialogLoading(Context context) {
        super(context);
        initView(context);
    }

    public DialogLoading(Activity context) {
        super(context);
        initView(context);
    }

    public DialogLoading(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView(context);
    }

    private void initView(Context context) {
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_spinkit, null);
        mLoadingView = mDialogContentView.findViewById(R.id.spin_kit);
        mTextView = mDialogContentView.findViewById(R.id.name);
        setContentView(mDialogContentView);
    }

    public void setLoadingText(CharSequence charSequence) {
        mTextView.setText(charSequence);
    }

    public void setLoadingColor(int color){
        mLoadingView.setColor(color);
    }

    public void cancel(RxCancelType code, String str) {
        cancel();
        switch (code) {
            case normal:
                BaseToast.normal(str);
                break;
            case error:
                BaseToast.error(str);
                break;
            case success:
                BaseToast.success(str);
                break;
            case info:
                BaseToast.info(str);
                break;
            default:
                BaseToast.normal(str);
                break;
        }
    }

    public void cancel(String str) {
        cancel();
        BaseToast.normal(str);
    }

    public SpinKitView getLoadingView() {
        return mLoadingView;
    }

    public View getDialogContentView() {
        return mDialogContentView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public enum RxCancelType {normal, error, success, info}
}
