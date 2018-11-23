package com.chetuhui.lcj.chezhubao.activity;

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
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.PhotoTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.dialog.DialogChooseImage;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;
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
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.graphics.Bitmap.CompressFormat.PNG;
import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;
import static com.chetuhui.lcj.chezhubao.tool.Constants.PICTURE_COMPRESS_PATH;
import static com.chetuhui.lcj.chezhubao.tool.Constants.PICTURE_ORIGIN_PATH;
import static com.chetuhui.lcj.chezhubao.tool.ImageTool.compressByQuality;
import static com.chetuhui.lcj.chezhubao.tool.ImageTool.save;
import static com.chetuhui.lcj.chezhubao.view.dialog.DialogChooseImage.LayoutType.TITLE;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        initView();
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
                initDialogChooseImage();
                break;
            case R.id.tv_addcar_cxps:
                initDialogChooseImage();
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
                if (TextUtils.isEmpty(imgurl)||TextUtils.isEmpty(mTvAddcarCph.getText().toString())||
                        TextUtils.isEmpty(mTvAddcarSyr.getText().toString())||
                        TextUtils.isEmpty(mTvAddcarPpxh.getText().toString())||
                        TextUtils.isEmpty(mTvAddcarClsbdh.getText().toString())||TextUtils.isEmpty(mTvAddcarFdjh.getText().toString())){
                        BaseToast.error("填写不能为空，请检查");
                }else {
                    getN_confirmInformation(imgurl,mTvAddcarCph.getText().toString(),
                            mTvAddcarSyr.getText().toString(),mTvAddcarPpxh.getText().toString(),mTvAddcarClsbdh.getText().toString(),mTvAddcarFdjh.getText().toString());

                }


                break;
        }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
//            BaseToast.normal("点得太快了");
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
    private void getN_upload(String uri) {
        String s_token = SPTool.getString(AddCarActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        File  file = new File(uri);


        OkGo.<String>post(NetData.N_upload)
                .tag(this)
                .headers("token",s_token)
                .params("file",file)

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
                                imgurl=jsonObject.getString("data");

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
//                    PhotoTool.cropImage(AddCarActivity.this,data.getData() );// 裁剪图片
//                    initUCrop(data.getData());
                    ContentResolver cr = AddCarActivity.this.getContentResolver();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(data.getData()));

                        mIvAddcarPhoto.setImageBitmap( rotateBitmap(bitmap,-90));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    String[] proj = {MediaStore.Images.Media.DATA};
                    CursorLoader loader = new CursorLoader(AddCarActivity.this, data.getData(), proj, null, null, null);
                    Cursor cursor = loader.loadInBackground();
                    if (cursor != null) {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();

                        img_src = cursor.getString(column_index);//图片实际路径
                        img_src=   data.getData().getPath();
                        Log.d("AddCarActivity", "dd:"+img_src);

                    }
                    cursor.close();

                    getN_upload(img_src);

//
                    mTvAddcarCxps.setVisibility(View.VISIBLE);


                }

                break;
            case PhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                    /* data.getExtras().get("data");*/
//                    PhotoTool.cropImage(AddCarActivity.this, PhotoTool.imageUriFromCamera);// 裁剪图片
//                    initUCrop(PhotoTool.imageUriFromCamera);
                    ContentResolver cr = AddCarActivity.this.getContentResolver();
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(PhotoTool.imageUriFromCamera));

                        mIvAddcarPhoto.setImageBitmap( rotateBitmap(bitmap,-90));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    String[] proj = {MediaStore.Images.Media.DATA};
                    CursorLoader loader = new CursorLoader(AddCarActivity.this,  PhotoTool.imageUriFromCamera, proj, null, null, null);
                    Cursor cursor = loader.loadInBackground();
                    if (cursor != null) {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();

                        img_src = cursor.getString(column_index);//图片实际路径
                        Log.d("AddCarActivity", img_src);

                    }
                    cursor.close();

//                    uploadFileInThreadByOkHttp(new File(String.valueOf(PhotoTool.imageUriFromCamera)));
                    getN_upload(img_src);
//
                    mTvAddcarCxps.setVisibility(View.VISIBLE);
                }

                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }







    public static Bitmap rotateBitmap(Bitmap bitmap,int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }



}
