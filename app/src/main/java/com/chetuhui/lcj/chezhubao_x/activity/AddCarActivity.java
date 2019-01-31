package com.chetuhui.lcj.chezhubao_x.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.model.AddcarBean;
import com.chetuhui.lcj.chezhubao_x.model.AuditStationBean;
import com.chetuhui.lcj.chezhubao_x.ocr.CameraActivity;
import com.chetuhui.lcj.chezhubao_x.ocr.FileUtil;
import com.chetuhui.lcj.chezhubao_x.ocr.RecognizeService;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.PhotoTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.util.FileUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.graphics.Bitmap.CompressFormat.PNG;
import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;
import static com.chetuhui.lcj.chezhubao_x.tool.Constants.PICTURE_COMPRESS_PATH;
import static com.chetuhui.lcj.chezhubao_x.tool.Constants.PICTURE_ORIGIN_PATH;
import static com.chetuhui.lcj.chezhubao_x.tool.ImageTool.compressByQuality;
import static com.chetuhui.lcj.chezhubao_x.tool.ImageTool.save;
import static com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage.LayoutType.TITLE;

public class AddCarActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAddcar;
    private ImageView mIvAddcarPhoto;
    /**
     * 重新拍摄
     */
    private SuperTextView mTvAddcarCxps;
    private RelativeLayout mRlAddcar1;
    /**
     * 请输入
     */
    private EditText mTvAddcarCph;
    private ImageView mIvAddcarCph;
    /**
     * 请输入
     */
    private EditText mTvAddcarSyr;
    private ImageView mIvAddcarSyr;
    /**
     * 请输入
     */
    private EditText mTvAddcarPpxh;
    private ImageView mIvAddcarPpxh;
    /**
     * 请输入
     */
    private EditText mTvAddcarClsbdh;
    private ImageView mIvAddcarClsbdh;
    /**
     * 请输入
     */
    private EditText mTvAddcarFdjh;
    private ImageView mIvAddcarFdjh;
    /**
     * 确认信息无误
     */
    private TextView mTvAddcarQrww;

    private Uri resultUri;
    private String img_src,imgurl;


    private boolean hasGotToken = false;
    private AlertDialog.Builder alertDialog;


    private static final int REQUEST_CODE_VEHICLE_LICENSE = 120;


    private MyHandler mHandler =new MyHandler(AddCarActivity.this);

    private static class MyHandler extends Handler {
            private WeakReference<AddCarActivity> activityWeakReference;

            public MyHandler(AddCarActivity activity) {
                activityWeakReference = new WeakReference<AddCarActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                AddCarActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        alertDialog = new AlertDialog.Builder(this);
        initView();
        initAccessTokenWithAkSk();
    }

    private void initView() {
        mTitlebarAddcar = (CommonTitleBar) findViewById(R.id.titlebar_addcar);
        mTitlebarAddcar.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mIvAddcarPhoto = (ImageView) findViewById(R.id.iv_addcar_photo);
        mIvAddcarPhoto.setOnClickListener(this);
        mTvAddcarCxps = (SuperTextView) findViewById(R.id.tv_addcar_cxps);
        mTvAddcarCxps.setOnClickListener(this);
        mRlAddcar1 = (RelativeLayout) findViewById(R.id.rl_addcar_1);
        mRlAddcar1.setOnClickListener(this);
        mTvAddcarCph = (EditText) findViewById(R.id.tv_addcar_cph);
        mTvAddcarCph.setOnClickListener(this);
        mIvAddcarCph = (ImageView) findViewById(R.id.iv_addcar_cph);
        mIvAddcarCph.setOnClickListener(this);
        mTvAddcarSyr = (EditText) findViewById(R.id.tv_addcar_syr);
        mTvAddcarSyr.setOnClickListener(this);
        mIvAddcarSyr = (ImageView) findViewById(R.id.iv_addcar_syr);
        mIvAddcarSyr.setOnClickListener(this);
        mTvAddcarPpxh = (EditText) findViewById(R.id.tv_addcar_ppxh);
        mTvAddcarPpxh.setOnClickListener(this);
        mIvAddcarPpxh = (ImageView) findViewById(R.id.iv_addcar_ppxh);
        mIvAddcarPpxh.setOnClickListener(this);
        mTvAddcarClsbdh = (EditText) findViewById(R.id.tv_addcar_clsbdh);
        mTvAddcarClsbdh.setOnClickListener(this);
        mIvAddcarClsbdh = (ImageView) findViewById(R.id.iv_addcar_clsbdh);
        mIvAddcarClsbdh.setOnClickListener(this);
        mTvAddcarFdjh = (EditText) findViewById(R.id.tv_addcar_fdjh);
        mTvAddcarFdjh.setOnClickListener(this);
        mIvAddcarFdjh = (ImageView) findViewById(R.id.iv_addcar_fdjh);
        mIvAddcarFdjh.setOnClickListener(this);
        mTvAddcarQrww = (TextView) findViewById(R.id.tv_addcar_qrww);

        mTvAddcarQrww.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {

            Intent intent=null;
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_addcar_photo:
                if (!checkTokenStatus()) {
                    return;
                }
                Intent  intent1 = new Intent(AddCarActivity.this, CameraActivity.class);
                intent1.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent1.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent1, REQUEST_CODE_VEHICLE_LICENSE);
//                initDialogChooseImage();
                break;
            case R.id.tv_addcar_cxps:
                if (!checkTokenStatus()) {
                    return;
                }
                Intent    intent2 = new Intent(AddCarActivity.this, CameraActivity.class);
                intent2.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent2.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent2, REQUEST_CODE_VEHICLE_LICENSE);
//                initDialogChooseImage();
                break;
            case R.id.rl_addcar_1:
                break;
            case R.id.tv_addcar_cph:
                break;
            case R.id.iv_addcar_cph:
                break;
            case R.id.tv_addcar_syr:
                break;
            case R.id.iv_addcar_syr:
                break;
            case R.id.tv_addcar_ppxh:
                break;
            case R.id.iv_addcar_ppxh:
                break;
            case R.id.tv_addcar_clsbdh:
                break;
            case R.id.iv_addcar_clsbdh:
                break;
            case R.id.tv_addcar_fdjh:
                break;
            case R.id.iv_addcar_fdjh:
                break;
            case R.id.tv_addcar_qrww:
                if (DataTool.isNullString(imgurl)||DataTool.isNullString(mTvAddcarCph.getText().toString())||
                        DataTool.isNullString(mTvAddcarSyr.getText().toString())||
                        DataTool.isNullString(mTvAddcarPpxh.getText().toString())||
                        DataTool.isNullString(mTvAddcarClsbdh.getText().toString())||DataTool.isNullString(mTvAddcarFdjh.getText().toString())){
                        BaseToast.error("填写不能为空，请检查");
                }else {
                    if (mTvAddcarCph.getText().toString().length()!=7&mTvAddcarCph.getText().toString().length()!=8){
                        BaseToast.error("车牌号位数有误请检查");
                    }else {
                        getN_confirmInformation(imgurl,mTvAddcarCph.getText().toString(),
                                mTvAddcarSyr.getText().toString(),mTvAddcarPpxh.getText().toString(),mTvAddcarClsbdh.getText().toString(),mTvAddcarFdjh.getText().toString());

                    }

                }


                break;
        }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
//            //BaseToast.normal("点得太快了");
            return;
        }
    }

    private void getN_confirmInformation(String licensePicture ,String carNum,String owner,String carBrand,String carCode,String engineNum) {
        String s_token = SPTool.getString(AddCarActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_confirmInformation)
                .tag(this)
                .headers("token",s_token)
                .params("licensePicture",licensePicture)
                .params("carNum",carNum)
                .params("owner",owner)
                .params("carBrand",carBrand)
                .params("carCode",carCode)
                .params("engineNum",engineNum)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                //BaseToast.success(msg);
                                finish();





                            }
                            else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(AddCarActivity.this,"token");
                                startActivity(new Intent(AddCarActivity.this,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
                                BaseToast.success(msg);

                            }

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
    private void getN_upload(File uri) {
        String s_token = SPTool.getString(AddCarActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }



        OkGo.<String>post(NetData.N_upload)
                .tag(this)
                .headers("token",s_token)
                .params("file",uri)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code=jsonObject.getInt("code");
                            final JSONObject finalJsonObject = jsonObject;
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code==0){
                                        try {
                                            imgurl= finalJsonObject.getString("data");
                                            mTvAddcarCxps.setVisibility(View.VISIBLE);
                                            ShowImageUtils.showImageView(AddCarActivity.this,imgurl,mIvAddcarPhoto);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        BaseToast.success("照片上传成功");

                                        Log.d("AddCarActivity", imgurl);

                                    }
                                    else if (code==1004){
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(AddCarActivity.this,"token");
                                        startActivity(new Intent(AddCarActivity.this,LoginActivity.class));
                                        BaseToast.error("登录过期，请重新登录");

                                    }else {
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
protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
    // 识别成功回调，行驶证识别
    if (requestCode == REQUEST_CODE_VEHICLE_LICENSE && resultCode == Activity.RESULT_OK) {
        BaseToast.cancelToast();
        BaseToast.success("正在识别");

        RecognizeService.recVehicleLicense(this, FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                new RecognizeService.ServiceListener() {
                    @Override
                    public void onResult(final String result) {
//                            JsonObject object = new JsonParser().parse(result).getAsJsonObject();
//                            Log.e("11111111",object+"");
                        Log.d("AddCarActivity", result);
                        Log.d("AddCarActivity", ""+data.getData());
                        Log.d("AddCarActivity", ""+data);
                        Log.d("AddCarActivity", ""+FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath());

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {

        if (result.length()<100){
            BaseToast.error("识别失败，请重新上传正确的行驶证");


        }else {
            getN_upload(new File(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath()));


            AddcarBean bean= new Gson().fromJson(result, AddcarBean.class);
            Log.d("AddCarActivity", "bean.getWords_result().get号牌号码():" + bean.getWords_result().get号牌号码().getWords());
            Log.d("AddCarActivity", "bean.getWords_result().get号牌号码():" + bean.getWords_result().get所有人().getWords());
            Log.d("AddCarActivity", "bean.getWords_result().get号牌号码():" + bean.getWords_result().get品牌型号().getWords());
            Log.d("AddCarActivity", "bean.getWords_result().get号牌号码():" + bean.getWords_result().get车辆识别代号().getWords());
            Log.d("AddCarActivity", "bean.getWords_result().get号牌号码():" + bean.getWords_result().get发动机号码().getWords());
            mTvAddcarCph.setText(""+ bean.getWords_result().get号牌号码().getWords());
            mTvAddcarSyr.setText(""+ bean.getWords_result().get所有人().getWords());
            mTvAddcarPpxh.setText(""+ bean.getWords_result().get品牌型号().getWords());
            mTvAddcarClsbdh.setText(""+ bean.getWords_result().get车辆识别代号().getWords());
            mTvAddcarFdjh.setText(""+ bean.getWords_result().get发动机号码().getWords());

            BaseToast.success("识别成功");
        }


                            }
                        });




//                        pplx.setText(result);
//                            infoPopText(result);
                    }
                });
    }
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
                File shb=  roadImageView(resultUri, mIvAddcarPhoto);
//                getN_upload(shb);



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


    /**
     * 用明文ak，sk初始化
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(mContext).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                String token = result.getAccessToken();
                hasGotToken = true;
            }
            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError子类SDKError对象
                error.printStackTrace();
                alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext());


    }

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;
    }

    private void alertText(final String title, final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }



}
