package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.FileTool;
import com.chetuhui.lcj.chezhubao_x.tool.PhotoTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.ZipTool;
import com.chetuhui.lcj.chezhubao_x.utils.FileUtils;
import com.chetuhui.lcj.chezhubao_x.utils.ImageUtils;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.utils.ShowImageUtils;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogLoading;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogTool;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.chetuhui.lcj.chezhubao_x.view.dialog.DialogChooseImage.LayoutType.TITLE;

public class SelfAuditActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarSa;
    /**
     * 下一辆
     */
    private TextView mTvSaXyl;
    private RelativeLayout mRlSa1;
    private ImageView mIvSa1;
    private RelativeLayout mRlSaSlt1;
    private RelativeLayout mRlSa2;
    private ImageView mIvSa2;
    private RelativeLayout mRlSaSlt2;
    private RelativeLayout mRlSa3;
    private ImageView mIvSa3;
    private RelativeLayout mRlSaSlt3;
    private RelativeLayout mRlSa4;
    private ImageView mIvSa4;
    private RelativeLayout mRlSaSlt4;
    private RelativeLayout mRlSa5;
    private ImageView mIvSa5;
    private RelativeLayout mRlSaSlt5;
    private RelativeLayout mRlSa6;
    private ImageView mIvSa6;
    private RelativeLayout mRlSaSlt6;
    private RelativeLayout mRlSa7;
    private ImageView mIvSa7;
    private RelativeLayout mRlSaSlt7;
    private RelativeLayout mRlSa8;
    private ImageView mIvSa8;
    private RelativeLayout mRlSaSlt8;
    private CheckBox mCbSaYes;
    private CheckBox mCbSaNo;
    private RecyclerView mRvSa;
    private RelativeLayout mRlSaTjsp;
    private VideoView mVvSa;
    private ScrollView sv_sa;

    private String img_src;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private String img7;
    private String img8;
    private String video_url;
    private String getVideo_url;
    private String carjosn;
//    private List<String > mStringList=new ArrayList<>();
    private int num = 0,yn=0;
    private  List<String > mStringList_car=new ArrayList<>();
    private  List<String > mStringList_AuditCodeList=new ArrayList<>();
    private  List<String > mStringList_shanghen=new ArrayList<>();
    private  UpAdapter mAdapter;
    private  LinearLayoutManager mLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private  int i=0;
    private  boolean istz=false;
    private Uri resultUri;
    private DialogLoading mDialogLoading;
    String[] all;


    private static final int TIME_OUT = 60 * 1000; //超时时间
    private static final String CONTENT_TYPE = "multipart/form-data"; //内容类型
    private static final String BOUNDARY = "Boundary+818A250585C93D0F"; //UUID.randomUUID().toString(); //边界标识 随机生成 String PREFIX = "--" , LINE_END = "\r\n";
    private static final String LINE_END = "\n\r";
    private static final String PREFIX = "--";


    private  MyHandler mHandler =new MyHandler(SelfAuditActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<SelfAuditActivity> activityWeakReference;

            public MyHandler(SelfAuditActivity activity) {
                activityWeakReference = new WeakReference<SelfAuditActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                SelfAuditActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_audit);
        carjosn=SPTool.getString(mContext,"carjosn");
       all=carjosn.split(",");



        Log.d("SelfAuditActivity", "mStringList>>>>>>>>>>"+all.length);
        Log.d("SelfAuditActivity", "carjosn>>>>>>>>>>"+carjosn);
        for (int j = 0; j <all.length ; j++) {
            Log.d("SelfAuditActivity", "mStringList>>>>>get>>>>>"+all[j]);


        }

        initView();
    }
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
        }
    }
    private void initView() {
        mTitlebarSa = (CommonTitleBar) findViewById(R.id.titlebar_sa);
        mTitlebarSa.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mTvSaXyl = (TextView) findViewById(R.id.tv_sa_xyl);
        sv_sa = (ScrollView) findViewById(R.id.sv_sa);
        mTvSaXyl.setOnClickListener(this);
        mRlSa1 = (RelativeLayout) findViewById(R.id.rl_sa_1);
        mRlSa1.setOnClickListener(this);
        mIvSa1 = (ImageView) findViewById(R.id.iv_sa_1);
        mRlSaSlt1 = (RelativeLayout) findViewById(R.id.rl_sa_slt1);
        mRlSa2 = (RelativeLayout) findViewById(R.id.rl_sa_2);
        mRlSa2.setOnClickListener(this);
        mIvSa2 = (ImageView) findViewById(R.id.iv_sa_2);
        mRlSaSlt2 = (RelativeLayout) findViewById(R.id.rl_sa_slt2);
        mRlSa3 = (RelativeLayout) findViewById(R.id.rl_sa_3);
        mRlSa3.setOnClickListener(this);
        mIvSa3 = (ImageView) findViewById(R.id.iv_sa_3);
        mRlSaSlt3 = (RelativeLayout) findViewById(R.id.rl_sa_slt3);
        mRlSa4 = (RelativeLayout) findViewById(R.id.rl_sa_4);
        mRlSa4.setOnClickListener(this);
        mIvSa4 = (ImageView) findViewById(R.id.iv_sa_4);
        mRlSaSlt4 = (RelativeLayout) findViewById(R.id.rl_sa_slt4);
        mRlSa5 = (RelativeLayout) findViewById(R.id.rl_sa_5);
        mRlSa5.setOnClickListener(this);
        mIvSa5 = (ImageView) findViewById(R.id.iv_sa_5);
        mRlSaSlt5 = (RelativeLayout) findViewById(R.id.rl_sa_slt5);
        mRlSa6 = (RelativeLayout) findViewById(R.id.rl_sa_6);
        mRlSa6.setOnClickListener(this);
        mIvSa6 = (ImageView) findViewById(R.id.iv_sa_6);
        mRlSaSlt6 = (RelativeLayout) findViewById(R.id.rl_sa_slt6);
        mRlSa7 = (RelativeLayout) findViewById(R.id.rl_sa_7);
        mRlSa7.setOnClickListener(this);
        mIvSa7 = (ImageView) findViewById(R.id.iv_sa_7);
        mRlSaSlt7 = (RelativeLayout) findViewById(R.id.rl_sa_slt7);
        mRlSa8 = (RelativeLayout) findViewById(R.id.rl_sa_8);
        mRlSa8.setOnClickListener(this);
        mIvSa8 = (ImageView) findViewById(R.id.iv_sa_8);
        mRlSaSlt8 = (RelativeLayout) findViewById(R.id.rl_sa_slt8);
        mCbSaYes = (CheckBox) findViewById(R.id.cb_sa_yes);
        mCbSaNo = (CheckBox) findViewById(R.id.cb_sa_no);
        mRvSa = (RecyclerView) findViewById(R.id.rv_sa);
        mRlSaTjsp = (RelativeLayout) findViewById(R.id.rl_sa_tjsp);
        mRlSaTjsp.setOnClickListener(this);
        mVvSa = (VideoView) findViewById(R.id.vv_sa);
        if (i==all.length-1){
            mTvSaXyl.setText("下一步");
            istz=true;
        }
        mCbSaNo.setChecked(true);
        mCbSaYes.setChecked(false);
        mCbSaNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mCbSaYes.setChecked(false);
                    yn=0;
                }else {
                    mCbSaYes.setChecked(true);
                    yn=1;
                    initRecylerView();
                }

            }
        });
        mCbSaYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mCbSaNo.setChecked(false);
                    yn=1;
                    initRecylerView();
                }else {
                    mCbSaNo.setChecked(true);
                    yn=0;
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_sa_1:
                num =1 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_2:
                num =2 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_3:
                num =3 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_4:
                num =4 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_5:
                num =5 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_6:
                num =6 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_7:
                num =7 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_8:
                num =8 ;
                initDialogChooseImage();
                break;
            case R.id.rl_sa_tjsp:
                Intent  intent =new Intent(mContext,VideoActivity.class);

                startActivityForResult(intent, 1);//带请求码打开activity (请求码自定 这里设为1


                break;
            case R.id.tv_sa_xyl:
                Log.d("SelfAuditActivity", "tv_sa_xyl");
                Gson g = new Gson();
                String jsonString = g.toJson(mStringList_shanghen);

                    if (!DataTool.isNullString(img1)&!DataTool.isNullString(img2)&!DataTool.isNullString(img3)&!DataTool.isNullString(img4)&!DataTool.isNullString(img5)
                            &!DataTool.isNullString(img6)&!DataTool.isNullString(img7)&!DataTool.isNullString(img8)&!DataTool.isNullString(getVideo_url)){

                        N_saveSelfAudit(img1,img5,img2,img6,img3,img4,img7,img8,yn,jsonString,getVideo_url,""+all[i]);

                    }else {
                        BaseToast.error("信息不完整，请检查");
                    }

                break;
        }
    }

    private void initRecylerView() {

        mAdapter = new UpAdapter(R.layout.item_up,mStringList_shanghen);
//        mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
            mGridLayoutManager=new GridLayoutManager(mContext,2);


        //添加动画
        mRvSa.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

//        mRvSa.setLayoutManager(mLayoutManager);
        mRvSa.setLayoutManager(mGridLayoutManager);

        View headerView = getHeaderView( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=9;
                initDialogChooseImage();
            }
        });
        mAdapter.addHeaderView(headerView);

        mRvSa.setAdapter(mAdapter);


    }
    public class UpAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public UpAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            //可链式调用赋值


            ShowImageUtils.showImageView(mContext,item,(ImageView) helper.getView(R.id.iv_item_up));

            //获取当前条目position
            //int position = helper.getLayoutPosition();
        }
    }
    private View getHeaderView( View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRvSa.getParent(), false);

        view.setOnClickListener(listener);
        return view;
    }


    private void N_saveSelfAudit(String leftSideHead,String leftTail,String rightSideHead,String rightTail,String frontOfCar,String rearOfCar,String mainDrivingSide
                                            ,String frontPassengerSide,int scarred,String carScarList,String videoUrl,String carNum) {
        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_saveSelfAudit)
                .tag(this)
                .headers("token", s_token)
                .params("leftSideHead", ""+leftSideHead)
                .params("leftTail", ""+leftTail)
                .params("rightSideHead", ""+rightSideHead)
                .params("rightTail", ""+rightTail)
                .params("frontOfCar", ""+frontOfCar)
                .params("rearOfCar", ""+rearOfCar)
                .params("mainDrivingSide", ""+mainDrivingSide)
                .params("frontPassengerSide", ""+frontPassengerSide)
                .params("scarred", ""+scarred)
                .params("carScarList", ""+carScarList)
                .params("videoUrl", ""+videoUrl)
                .params("carNum", ""+carNum)

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
                                        BaseToast.success("提交成功");

                                        try {

                                            mStringList_AuditCodeList.add(finalJsonObject.getString("data"));
                                            i++;
//                                            Gson g = new Gson();
//                                            String jsonString = g.toJson(mStringList_AuditCodeList);
//                                            SPTool.putString(mContext ,"AuditCodeList",jsonString);
//                                            startActivity(new Intent(mContext ,ConfirmActivity.class));
//                                            finish();

                                            setxyl();

                                            if (istz){

                                                Gson g = new Gson();
                                                String jsonString = g.toJson(mStringList_AuditCodeList);
                                                SPTool.putString(mContext ,"AuditCodeList",jsonString);
                                                startActivity(new Intent(mContext ,ConfirmActivity.class));
                                                finish();
                                            }
                                            if (i==all.length-1){
                                                mTvSaXyl.setText("下一步");
                                                istz=true;
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void setxyl() {
        sv_sa.smoothScrollTo(0, 0);
        img1="";
        img2="";
        img3="";
        img4="";
        img5="";
        img6="";
        img7="";
        img8="";
        video_url="";
        mIvSa1.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.up_ct));
        mIvSa2.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.up_ct));
        mIvSa3.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.up_ct));
        mIvSa4.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.up_ct));
        mIvSa5.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.up_ct));
        mIvSa6.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.up_ct));
        mIvSa7.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.up_ct));
        mIvSa8.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.up_ct));
        mRlSaSlt1.setVisibility(View.VISIBLE);
        mRlSaSlt2.setVisibility(View.VISIBLE);
        mRlSaSlt3.setVisibility(View.VISIBLE);
        mRlSaSlt4.setVisibility(View.VISIBLE);
        mRlSaSlt5.setVisibility(View.VISIBLE);
        mRlSaSlt6.setVisibility(View.VISIBLE);
        mRlSaSlt7.setVisibility(View.VISIBLE);
        mRlSaSlt8.setVisibility(View.VISIBLE);
        mVvSa.setVisibility(View.GONE);
        mStringList_shanghen.clear();
        if (mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }

        yn=0;
        mCbSaNo.setChecked(true);
        mCbSaYes.setChecked(false);
    }
    private void N_uploadVideo(File uri) {
        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_uploadVideo)
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
                                        BaseToast.success("视频上传成功");
                                        mDialogLoading.cancel();
                                        try {
                                            getVideo_url = finalJsonObject.getString("data");
                                            //网络视频
                                            mVvSa.setVisibility(View.VISIBLE);

                                            Uri uri = Uri.parse( getVideo_url );
                                            //设置视频控制器
                                            mVvSa.setMediaController(new MediaController(SelfAuditActivity.this));

                                            //播放完成回调
                                            mVvSa.setOnCompletionListener( new MyPlayerOnCompletionListener());

                                            //设置视频路径
                                            mVvSa.setVideoURI(uri);

//                                            //开始播放视频
//                                            mVvSa.start();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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
                                                img1 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt1.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img1,mIvSa1);

                                        } else if (num == 2) {
                                            try {
                                                img2 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt2.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img2,mIvSa2);
                                        } else if (num == 3) {
                                            try {
                                                img3 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt3.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img3,mIvSa3);

                                        }
                                        else if (num == 4) {
                                            try {
                                                img4 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt4.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img4,mIvSa4);
                                        } else if (num == 5) {
                                            try {
                                                img5 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt5.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img5,mIvSa5);
                                        } else if (num == 6) {
                                            try {
                                                img6 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt6.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img6,mIvSa6);
                                        } else if (num == 7) {
                                            try {
                                                img7 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt7.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img7,mIvSa7);
                                        } else if (num == 8) {
                                            try {
                                                img8 = finalJsonObject.getString("data");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                            mRlSaSlt8.setVisibility(View.GONE);
                                            ShowImageUtils.showImageView(mContext,img8,mIvSa8);
                                        }else if (num == 9) {
                                            try {

                                                mStringList_shanghen.add(finalJsonObject.getString("data"));
                                                initRecylerView();

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                        }


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
            case 1: //返回的请求码
                if (data!=null){
                    //操作
                    video_url = data.getExtras().getString("video_url");
                    File vfile=new File(video_url);
                    BaseToast.success("正在上传，请稍等");
                    mDialogLoading = new DialogLoading(mContext);
                    mDialogLoading.setCanceledOnTouchOutside(false);
                    mDialogLoading.setCancelable(false);
                    mDialogLoading.show();
                    Log.d("SelfAuditActivity", ""+video_url);
//                    N_uploadVideo(vfile);

                    FileUtils.upLoadVideo(mContext, video_url, new ImageUtils.UploadSuccess() {
                        @Override
                        public void success(final String imgUri) {
                            if (!imgUri.isEmpty()) {
                                //这是压缩完的路径，可以上传了
//                            submitVedio(imgUri);

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        File vfile=new File(imgUri);
                                        N_uploadVideo(vfile);
                                        // 写子线程中的操作
//                                        uploadFile(new File(imgUri),postUrl);
//                                    submitVedio(imgUri);
                                    }
                                }).start();

                            } else {
                                Toast.makeText(mContext, "压缩失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


                break;
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
                    File shb=  roadImageView(resultUri);
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
//

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

    public static String uploadFile(File file, String requestURL) {
        URL url = null;
        try {
            url = new URL(requestURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
//            LogUtils.i("打开连接时报异常");
        }
        try {
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false); //不允许使用缓存
            conn.setRequestMethod("POST"); //请求方式
            //头信息
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + "; boundary=" + BOUNDARY);
            conn.setRequestProperty("Connection", "keep-alive");

            if (file != null) {
                /** * 当文件不为空，把文件包装并且上传 */
                OutputStream outputSteam = conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);


                //file内容
                StringBuffer sb = new StringBuffer();
                dos.write(sb.toString().getBytes());
                //读取文件的内容
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                //写入文件二进制内容
                dos.write(LINE_END.getBytes());
                //写入end data
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                //dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功
                 * 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
//                LogUtils.i("相应码" + res);
                if (res == 200) {
                    String oneLine;
                    StringBuffer response = new StringBuffer();
                    BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((oneLine = input.readLine()) != null) {
                        response.append(oneLine);
                    }
                    return response.toString();
                } else {
                    return "";
                }
            } else {
//                LogUtils.i("没找到文件");
                return "";
            }
        } catch (MalformedURLException e) {
//            LogUtils.i("MalformedURLException" +":",e.toString());
            return "";
        } catch (IOException e) {
//            LogUtils.i("IOException" +":",e.toString());
            return "";
        }
    }
}




