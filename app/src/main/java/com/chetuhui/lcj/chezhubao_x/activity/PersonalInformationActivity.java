package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.MainActivity;
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
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogEditSureCancel;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage.LayoutType.TITLE;

public class PersonalInformationActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarPi;
    /**  */
    private TextView mTvPiNc;
    private RelativeLayout mRlPiNc;
    /**
     * 未认证
     */
    private SuperTextView mTvPiSmxx;
    private RelativeLayout mRlPiSmxx;
    /**  */
    private TextView mTvPiBdsj;
    private RelativeLayout mRlPiBdsj;
    private String name, ph;
    private DialogEditSureCancel mDialog;
    private MyHandler myHandler = new MyHandler(PersonalInformationActivity.this);
    private ImageView mIvPiTc;
    private RelativeLayout mRlPiTc;
    private String imgurl;
    private Uri resultUri;
    private static class MyHandler extends Handler {
        private WeakReference<PersonalInformationActivity> activityWeakReference;

        public MyHandler(PersonalInformationActivity activity) {
            activityWeakReference = new WeakReference<PersonalInformationActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            PersonalInformationActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        imgurl = getIntent().getStringExtra("imgurl");
        initView();
        getN_personalInformation();
    }

    private void initView() {
        mTitlebarPi = (CommonTitleBar) findViewById(R.id.titlebar_pi);
        mTitlebarPi.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mTvPiNc = (TextView) findViewById(R.id.tv_pi_nc);
        mRlPiNc = (RelativeLayout) findViewById(R.id.rl_pi_nc);
        mRlPiNc.setOnClickListener(this);
        mTvPiSmxx = (SuperTextView) findViewById(R.id.tv_pi_smxx);
        mRlPiSmxx = (RelativeLayout) findViewById(R.id.rl_pi_smxx);
        mRlPiSmxx.setOnClickListener(this);
        mTvPiBdsj = (TextView) findViewById(R.id.tv_pi_bdsj);
        mRlPiBdsj = (RelativeLayout) findViewById(R.id.rl_pi_bdsj);
        mRlPiBdsj.setOnClickListener(this);
        mIvPiTc = (ImageView) findViewById(R.id.iv_pi_tc);
        mIvPiTc.setOnClickListener(this);
        mRlPiTc = (RelativeLayout) findViewById(R.id.rl_pi_tc);
        mRlPiTc.setOnClickListener(this);
        ShowImageUtils.showImageViewToCrop(mContext, imgurl, mIvPiTc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_pi_nc:
                showview();
                break;
            case R.id.rl_pi_smxx:
                break;
            case R.id.rl_pi_bdsj:
                break;
            case R.id.iv_pi_tc:
                initDialogChooseImage();
                break;
            case R.id.rl_pi_tc:
                break;
        }
    }

    private void getN_personalInformation() {
        String s_token = SPTool.getString(PersonalInformationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(PersonalInformationActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_personalInformation)
                .tag(this)
                .headers("token", s_token)


                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                //BaseToast.success(msg);
                                String d = jsonObject.getString("data");
                                JSONObject jsonObject1 = new JSONObject(d);
                                name = jsonObject1.getString("nickName");
                                ph = jsonObject1.getString("phone");
                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mTvPiNc.setText(name);
                                        mTvPiBdsj.setText(ph);
                                    }
                                });


                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(PersonalInformationActivity.this, "token");
                                startActivity(new Intent(PersonalInformationActivity.this, LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            } else {
                                BaseToast.success(msg);

                            }


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

    private void showview() {
        InputFilter inputFilter= new InputFilter() {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                                        Toast.makeText(mContext,"不支持输入表情", Toast.LENGTH_SHORT).show();

                    return "";
                }
                return null;
            }
        };


        mDialog = new DialogEditSureCancel(mContext, R.style.PushUpInDialogThem);
        mDialog.getEditText().setFilters(new InputFilter[]{inputFilter,new InputFilter.LengthFilter(8)});
        mDialog.setTitle("修改昵称");

        mDialog.setCancelListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                mDialog.cancel();
            }
        });

        mDialog.setSureListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                if (!DataTool.isNullString(mDialog.getEditText().getText().toString())) {
                    getN_updatePersonalInformation(mDialog.getEditText().getText().toString());

                } else {
                    BaseToast.error("输入不能为空，请检查");
                }

            }
        });
        mDialog.setCancelable(false);
        mDialog.show();
    }

    private void getN_updatePersonalInformation(String name) {
        String s_token = SPTool.getString(PersonalInformationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(PersonalInformationActivity.this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_updatePersonalInformation)
                .tag(this)
                .headers("token", s_token)
                .params("nickname", "" + name)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                //BaseToast.success(msg);

                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mDialog.cancel();
                                        getN_personalInformation();
                                    }
                                });


                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(PersonalInformationActivity.this, "token");
                                startActivity(new Intent(PersonalInformationActivity.this, LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            } else {
                                BaseToast.success(msg);

                            }


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
                            myHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {
                                        try {
                                            imgurl = finalJsonObject.getString("data");
                                            ShowImageUtils.showImageViewToCrop(mContext, imgurl, mIvPiTc);
                                            getN_updateHeardImage(imgurl);

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
                    File shb = roadImageView(resultUri);
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
    private File roadImageView(Uri uri) {
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
                            myHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {

                                        BaseToast.success("修改成功");



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
