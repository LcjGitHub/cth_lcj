package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.PhotoTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage.LayoutType.TITLE;


public class TxActivity extends ActivityBase {

    private ImageView mIvTx;
    /**
     * 取消
     */
    private SuperTextView mTvTxQx;
    /**
     * 取消
     */
    private SuperTextView mTvTxQd;

    private String imgurl;

    private Uri resultUri;

    private MyHandler mHandler = new MyHandler(TxActivity.this);

    private static class MyHandler extends Handler {
        private WeakReference<TxActivity> activityWeakReference;

        public MyHandler(TxActivity activity) {
            activityWeakReference = new WeakReference<TxActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            TxActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx);
        imgurl = getIntent().getStringExtra("imgurl");
        initView();


    }

    private void initView() {
        mIvTx = (ImageView) findViewById(R.id.iv_tx);
        mTvTxQx = (SuperTextView) findViewById(R.id.tv_tx_qx);
        mTvTxQd = (SuperTextView) findViewById(R.id.tv_tx_qd);

        ShowImageUtils.showImageViewToCrop(mContext, imgurl, mIvTx);

        mTvTxQd.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                getN_updateHeardImage(imgurl);
            }
        });
        mTvTxQx.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                finish();

            }
        });
        mIvTx.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                initDialogChooseImage();
            }
        });
    }

    private void getN_upload(File uri) {
        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }


        OkGo.<String>post(NetData.N_upload)
                .tag(this)
                .headers("token", s_token)
                .params("file", uri)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code = jsonObject.getInt("code");
                            final JSONObject finalJsonObject = jsonObject;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        try {
                                            imgurl = finalJsonObject.getString("data");
                                            ShowImageUtils.showImageView(mContext, imgurl, mIvTx);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        BaseToast.success("照片上传成功");

                                        Log.d("AddCarActivity", imgurl);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(mContext, "token");
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    } else {
                                        BaseToast.success(msg);

                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void initDialogChooseImage() {
        DialogChooseImage dialogChooseImage = new DialogChooseImage(mContext, TITLE);
        dialogChooseImage.show();
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
//                    RxPhotoTool.cropImage(ActivityUser.this, );// 裁剪图片
                    initUCrop(data.getData());
                }

                break;
            case PhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                    /* data.getExtras().get("data");*/
//                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// 裁剪图片
                    initUCrop(PhotoTool.imageUriFromCamera);
                }

                break;


            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    File shb = roadImageView(resultUri, mIvTx);
                    getN_upload(shb);


                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.morentu)
//                //异常占位图(当加载异常的时候出现的图片)
//                .error(R.drawable.morentu)
//                //禁止Glide硬盘缓存缓存
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//
//        Glide.with(mContext).
//                load(uri).
//                apply(options).
//                thumbnail(0.5f).
//                into(imageView);

        return (new File(PhotoTool.getImageAbsolutePath(this, uri)));
    }

    private void initUCrop(Uri uri) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为椭圆
        //options.setCircleDimmedLayer(true);
        //设置是否展示矩形裁剪框
        // options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        //options.setCropGridColumnCount(2);
        //设置横线的数量
        //options.setCropGridRowCount(1);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this);
    }


    private void getN_updateHeardImage(String uri) {
        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(mContext, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }


        OkGo.<String>get(NetData.N_updateHeardImage + "?imgUrl=" + uri)
                .tag(this)
                .headers("token", s_token)


                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code = jsonObject.getInt("code");
                            final JSONObject finalJsonObject = jsonObject;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {

                                        BaseToast.success("修改成功");
                                        finish();


                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(mContext, "token");
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    } else {
                                        BaseToast.success(msg);

                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                    }
                });
    }


}
