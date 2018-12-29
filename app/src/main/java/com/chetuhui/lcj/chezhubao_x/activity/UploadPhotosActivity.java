package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.PhotoTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage.LayoutType.TITLE;

public class UploadPhotosActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarUp;
    /**
     * 下一步
     */
    private TextView mTvUpXyb;
    private ImageView mIvUpChetou;
    /**
     * 受损车车头45°
     */
    private TextView mTvUpChetou;
    private ImageView mIvUpCypzw;
    /**
     * 车辆与碰撞物照片
     */
    private TextView mTvUpCypzw;
    private ImageView mIvUpXijie;
    /**
     * 受损细节照片
     */
    private TextView mTvUpXijie;
    private String img_src, imgurl, url_ct, url_cypzw, url_xj,url_cw;
    private int num = 0;
    private ImageView mIvUpChewei;
    /**
     * 受损车车尾45°
     */
    private TextView mTvUpChewei;

    private Uri resultUri;

    private MyHandler mHandler=new MyHandler(UploadPhotosActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<UploadPhotosActivity> activityWeakReference;

            public MyHandler(UploadPhotosActivity activity) {
                activityWeakReference = new WeakReference<UploadPhotosActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                UploadPhotosActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photos);
        initView();
    }

    private void initView() {
        mTitlebarUp = (CommonTitleBar) findViewById(R.id.titlebar_up);
        mTitlebarUp.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
            }
        });

        mTvUpXyb = (TextView) findViewById(R.id.tv_up_xyb);
        mTvUpXyb.setOnClickListener(this);
        mIvUpChetou = (ImageView) findViewById(R.id.iv_up_chetou);
        mTvUpChetou = (TextView) findViewById(R.id.tv_up_chetou);
        mTvUpChetou.setOnClickListener(this);
        mIvUpCypzw = (ImageView) findViewById(R.id.iv_up_cypzw);
        mTvUpCypzw = (TextView) findViewById(R.id.tv_up_cypzw);
        mTvUpCypzw.setOnClickListener(this);
        mIvUpXijie = (ImageView) findViewById(R.id.iv_up_xijie);
        mTvUpXijie = (TextView) findViewById(R.id.tv_up_xijie);
        mTvUpXijie.setOnClickListener(this);
        mIvUpChewei = (ImageView) findViewById(R.id.iv_up_chewei);
        mTvUpChewei = (TextView) findViewById(R.id.tv_up_chewei);
        mTvUpChewei.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_up_xyb:
                Log.d("UploadPhotosActivity", ">>>>>>>>"+url_ct+"\n>>>>>>>>>>"+url_cypzw+"\n>>>>>>>>>>"+url_xj+"\n>>>>>>>>>>"+url_cw);
                if (DataTool.isNullString(url_ct) || DataTool.isNullString(url_cypzw) || DataTool.isNullString(url_xj)|| DataTool.isNullString(url_cw)) {
                    BaseToast.error("还没上传完成");

                } else {
                    intent = new Intent(UploadPhotosActivity.this, ChooseBusinessActivity.class);
                }


                break;
            case R.id.tv_up_chetou:
                num = 1;

                initDialogChooseImage();
                break;
            case R.id.tv_up_cypzw:
                num = 2;

                initDialogChooseImage();
                break;
            case R.id.tv_up_xijie:
                num = 3;

                initDialogChooseImage();
                break;
            case R.id.tv_up_chewei:
                num =4 ;

                initDialogChooseImage();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    private void getN_upload(File uri) {
        String s_token = SPTool.getString(UploadPhotosActivity.this, "token");
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
                    public void onSuccess(Response<String> response) {
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
                                       BaseToast.success("照片上传成功");
                                       if (num == 1) {
                                           try {
                                               url_ct = finalJsonObject.getString("data");
                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                           SPTool.putString(UploadPhotosActivity.this, "up_ct", "" + url_ct);
                                           ShowImageUtils.showImageView(UploadPhotosActivity.this,url_ct,mIvUpChetou);

                                       } else if (num == 2) {
                                           try {
                                               url_cypzw = finalJsonObject.getString("data");
                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                           SPTool.putString(UploadPhotosActivity.this, "up_cypzw", "" + url_cypzw);
                                           ShowImageUtils.showImageView(UploadPhotosActivity.this,url_cypzw,mIvUpCypzw);
                                       } else if (num == 3) {
                                           try {
                                               url_xj = finalJsonObject.getString("data");
                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                           SPTool.putString(UploadPhotosActivity.this, "up_xj", "" + url_xj);
                                           ShowImageUtils.showImageView(UploadPhotosActivity.this,url_xj,mIvUpXijie);

                                       }
                                       else if (num == 4) {
                                           try {
                                               url_cw = finalJsonObject.getString("data");
                                           } catch (JSONException e) {
                                               e.printStackTrace();
                                           }
                                           SPTool.putString(UploadPhotosActivity.this, "up_cw", "" + url_cw);
                                           ShowImageUtils.showImageView(UploadPhotosActivity.this,url_cw,mIvUpChewei);
                                       }


                                   } else if (code == 1004) {
                                       ActivityTool.finishAllActivity();
                                       SPTool.remove(UploadPhotosActivity.this, "token");
                                       startActivity(new Intent(UploadPhotosActivity.this, LoginActivity.class));
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
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }


    private void initDialogChooseImage() {
        DialogChooseImage dialogChooseImage = new DialogChooseImage(mContext, TITLE);
        dialogChooseImage.show();
    }

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
                    if (num==1){
                        File shb=  roadImageView(resultUri, mIvUpChetou);
                        getN_upload(shb);
                    }else if (num==2){

                        File shb= roadImageView(resultUri, mIvUpCypzw);
                        getN_upload(shb);
                    }
                    else if (num==3){

                        File shb=  roadImageView(resultUri, mIvUpXijie);
                        getN_upload(shb);
                    }
                    else if (num==4){

                        File shb=   roadImageView(resultUri, mIvUpChewei);
                        getN_upload(shb);
                    }




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



}
