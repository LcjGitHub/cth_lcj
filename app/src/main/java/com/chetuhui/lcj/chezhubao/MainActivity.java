package com.chetuhui.lcj.chezhubao;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.eventbus.TestEvent;
import com.chetuhui.lcj.chezhubao.tool.PermissionsTool;
import com.chetuhui.lcj.chezhubao.view.risenumber.RiseNumberTextView;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.prefs.PreferencesFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String TAG = "MainActivity";
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /**
     * Button
     */
    private Button mButton;
    private EditText mEt;
    private ImageView mImg;


    private RiseNumberTextView mRtv;
    private ProgressBar mProgressBar;
    private int tiem = 0;
    private ProgressBar mPb;

    /**
     * 跳过
     */
    private TextView mStartSkipCountDown;
    private MyCountDownTimer mCountDownTimer;
    MyHandler myHandler = new MyHandler(MainActivity.this);
    private Bitmap bitmap;
    private LinearLayout mLl;
    private View mTopView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ImmersionBar.with(this)
                .statusBarView(R.id.top_view)
                .navigationBarColor(R.color.colorPrimary)
                .fullScreen(true)
                .addTag("PicAndColor")  //给上面参数打标记，以后可以通过标记恢复
                .init();

        initView();
        PermissionsTool.
                with(MainActivity.this).
                addPermission(Manifest.permission.ACCESS_FINE_LOCATION).
                addPermission(Manifest.permission.ACCESS_COARSE_LOCATION).
                addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.CAMERA).
                addPermission(Manifest.permission.CALL_PHONE).
                addPermission(Manifest.permission.READ_PHONE_STATE).
                initPermission();
        EventBus.getDefault().register(this);
        verifyStoragePermissions(MainActivity.this);
        mRtv.withNumber(9999).start();
        mStartSkipCountDown.setText("3s 跳过");
        //创建倒计时类
        mCountDownTimer = new MyCountDownTimer(4000, 1000);
        mCountDownTimer.start();

//        //这是一个 Handler 里面的逻辑是从 Splash 界面跳转到 Main 界面，这里的逻辑每个公司基本上一致
//            tmpHandler.postDelayed(runnable, 3000);
//
//        //我司需求，在没有 Banner 广告的时候一秒跳过开屏页，有 Banner 广告的时候三秒跳过
//        if (PreferencesFactory.getCommonPref().getBoolean(CommonPreferences.PREFS_HAS_START_PAGE_BANNER, false)) {
//            mStartSkipCountDown.setText("3s 跳过");
//            //创建倒计时类
//            mCountDownTimer = new MyCountDownTimer(3000, 1000);
//            mCountDownTimer.start();
//            //这是一个 Handler 里面的逻辑是从 Splash 界面跳转到 Main 界面，这里的逻辑每个公司基本上一致
//            tmpHandler.postDelayed(runnable, 3000);
//        } else {
//            mStartSkipCountDown.setText("1s 跳过");
//            mCountDownTimer = new MyCountDownTimer(1000, 1000);
//            mCountDownTimer.start();
//            tmpHandler.postDelayed(runnable, 1000);
//        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
        EventBus.getDefault().unregister(this);
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TestEvent event) {
        mProgressBar.setProgress(event.getMsg());

    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mEt = (EditText) findViewById(R.id.et);
        mEt.setEnabled(false);
        mEt.setFocusable(false);
        mEt.setKeyListener(null);//重点

        mImg = (ImageView) findViewById(R.id.img);
        mProgressBar = findViewById(R.id.pb);


        mRtv = (RiseNumberTextView) findViewById(R.id.rtv);
        mEt.setOnClickListener(this);
        mImg.setOnClickListener(this);
        mRtv.setOnClickListener(this);
        mPb = (ProgressBar) findViewById(R.id.pb);


        mPb.setOnClickListener(this);

        mStartSkipCountDown = (TextView) findViewById(R.id.start_skip_count_down);
        mStartSkipCountDown.setOnClickListener(this);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mTopView = (View) findViewById(R.id.top_view);

        mLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", "com.example.scwlyd.cth_wycgg", null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent.putExtra("com.android.settings.ApplicationPkgName", "com.example.scwlyd.cth_wycgg");
                }

                startActivity(intent);
                Toast.makeText(MainActivity.this, "提现申请已提交\n预计5-10个工作日到账", Toast.LENGTH_SHORT).show();
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.animlayout);


//                mImg.setAnimation(shake);
                if (mEt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                } else {
                    mRtv.withNumber(Float.parseFloat(mEt.getText().toString().trim())).start();

                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (tiem < 100) {
                            tiem += 15;
                            EventBus.getDefault().post(new TestEvent(tiem));
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();

                mImg.setAnimation(shakeAnimation(6));
                mRtv.setAnimation(shakeAnimation2(12));
                mEt.setText("6666");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse("https://ds.alipay.com/?from=pc");
//                intent.setData(content_url);
//                startActivity(intent);

                generatBitmap(mLl);


                break;
            case R.id.et:
                break;
            case R.id.img:
                break;
            case R.id.rtv:
                break;
            case R.id.pb:
                break;


            case R.id.start_skip_count_down:
                finish();
                break;
            case R.id.ll:
                break;
        }
    }

    /**
     * 晃动动画
     * <p>
     * 那么CycleInterpolator是干嘛用的？？
     * Api demo里有它的用法，是个摇头效果！
     *
     * @param counts 1秒钟晃动多少下
     * @return Animation
     */
    public static Animation shakeAnimation(int counts) {
        Animation rotateAnimation = new RotateAnimation(0, 20, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new CycleInterpolator(counts));
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setDuration(3000);
        return rotateAnimation;
    }

    /**
     * 晃动动画-左右平移
     * <p>
     * <p>
     * 那么CycleInterpolator是干嘛用的？？
     * Api demo里有它的用法，是个摇头效果！
     *
     * @param counts 1秒钟晃动多少下
     * @return Animation
     */
    public static Animation shakeAnimation2(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setRepeatCount(100000);
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    /**
     * @param linearLayout 要转化为图片的布局
     */
    private void generatBitmap(LinearLayout linearLayout) {
        linearLayout.setDrawingCacheEnabled(true);
        linearLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(), linearLayout.getMeasuredHeight());
        linearLayout.buildDrawingCache();
        bitmap = Bitmap.createBitmap(linearLayout.getDrawingCache());
        saveImageToGallery(MainActivity.this, bitmap);
        linearLayout.setDrawingCacheEnabled(false);
        linearLayout.setGravity(Gravity.CENTER);  //因为刚刚重新测量布局一次，需要重新设置view居中
    }

    /**
     * 保存图片到图库
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "desheng");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "保存失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(file.getPath()))));
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> activityWeakReference;

        public MyHandler(MainActivity activity) {
            activityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以「 毫秒 」为单位倒计时的总数
         *                          例如 millisInFuture = 1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick()
         *                          例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
         */

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        public void onFinish() {
            mStartSkipCountDown.setText("0s 跳过");
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= 9) {
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", "com.example.scwlyd.cth_wycgg", null));
                    } else if (Build.VERSION.SDK_INT <= 8) {
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                        intent.putExtra("com.android.settings.ApplicationPkgName", "com.example.scwlyd.cth_wycgg");
                    }

                    startActivity(intent);
                }
            });

        }

        public void onTick(long millisUntilFinished) {
            mStartSkipCountDown.setText(millisUntilFinished / 1000 + "s 跳过");
        }

    }


}
