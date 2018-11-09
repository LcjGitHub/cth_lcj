package com.chetuhui.lcj.myapplication.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chetuhui.lcj.myapplication.R;
import com.chetuhui.lcj.myapplication.view.scaleimage.ImageSource;
import com.chetuhui.lcj.myapplication.view.scaleimage.RxScaleImageView;



/**
 * @author vondear
 * @date 2016/7/19
 * 查看图片并支持手势缩放
 */
public class DialogScaleView extends BaseDialog {

    private RxScaleImageView mRxScaleImageView;
    private String filePath;
    private Uri fileUri;
    private String fileAssetName;
    private Bitmap fileBitmap;
    private int resId;
    private int maxScale = 100;

    public DialogScaleView(Context context) {
        super(context);
        initView();
    }

    public DialogScaleView(Activity context) {
        super(context);
        initView();
    }


    public DialogScaleView(Context context, String filePath, boolean isAssets) {
        super(context);
        initView();
        setImage(filePath,isAssets);
    }

    public DialogScaleView(Context context, Uri uri) {
        super(context);
        initView();
        setImage(uri);
    }

    public DialogScaleView(Context context, int resId, boolean isResId) {
        super(context);
        initView();
        if (isResId) {
            setImage(resId);
        }
    }

    public DialogScaleView(Context context, Bitmap bitmap) {
        super(context);
        initView();
        setImage(bitmap);
    }

    public DialogScaleView(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public DialogScaleView(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }


    public DialogScaleView(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    public RxScaleImageView getRxScaleImageView() {
        return mRxScaleImageView;
    }

    public void setImage(String filePath, boolean isAssets) {
        if (isAssets) {
            this.fileAssetName = fileAssetName;
            mRxScaleImageView.setImage(ImageSource.asset(filePath));
        }else {
            this.filePath = filePath;
            mRxScaleImageView.setImage(ImageSource.uri(filePath));
        }
    }

    public void setImage(Uri uri) {
        this.fileUri = uri;
        mRxScaleImageView.setImage(ImageSource.uri(uri));
    }

    public void setImage(int resId) {
        this.resId = resId;
        mRxScaleImageView.setImage(ImageSource.resource(resId));
    }

    public void setImage(Bitmap bitmap) {
        this.fileBitmap = bitmap;
        mRxScaleImageView.setImage(ImageSource.bitmap(fileBitmap));
    }

    private void initView() {
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_scaleview, null);
        mRxScaleImageView = dialogView.findViewById(R.id.rx_scale_view);
        mRxScaleImageView.setMaxScale(maxScale);
        ImageView ivClose =  dialogView.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        setFullScreen();
        setContentView(dialogView);
    }

    public int getMaxScale() {
        return maxScale;
    }

    public void setMaxScale(int maxScale) {
        this.maxScale = maxScale;
        initView();
    }

    public String getFilePath() {
        return filePath;
    }

    public Uri getFileUri() {
        return fileUri;
    }

    public String getFileAssetName() {
        return fileAssetName;
    }

    public Bitmap getFileBitmap() {
        return fileBitmap;
    }

    public int getResId() {
        return resId;
    }


}
