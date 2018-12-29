package com.chetuhui.lcj.chezhubao_x.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.shapeloadingview.RxShapeLoadingView;

/**
 * @author Vondear
 * @date 2017/3/16
 */

public class DialogShapeLoading extends BaseDialog {

    private RxShapeLoadingView mLoadingView;
    private TextView num;
    private View mDialogContentView;

    public DialogShapeLoading(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public DialogShapeLoading(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public DialogShapeLoading(Context context) {
        super(context);
        initView(context);
    }

    public DialogShapeLoading(Activity context) {
        super(context);
        initView(context);
    }

    public DialogShapeLoading(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView(context);
    }

    private void initView(Context context) {
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_shape_loading_view, null);
        mLoadingView = mDialogContentView.findViewById(R.id.loadView);
        num = mDialogContentView.findViewById(R.id.load_num);
        setContentView(mDialogContentView);
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

    public void setLoadingText(CharSequence charSequence) {
        mLoadingView.setLoadingText(charSequence);
    }
    public void setLoadingText_num(int num_code) {
        num.setText(""+num_code+"%");
    }
    public RxShapeLoadingView getLoadingView() {
        return mLoadingView;
    }

    public View getDialogContentView() {
        return mDialogContentView;
    }

    public enum RxCancelType {normal, error, success, info}
}
