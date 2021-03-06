package com.chetuhui.lcj.chezhubao_x.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.City3Adapter;

import com.chetuhui.lcj.chezhubao_x.model.CityBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.sort.PinyinComparator;
import com.chetuhui.lcj.chezhubao_x.view.sort.TitleItemDecoration;
import com.chetuhui.lcj.chezhubao_x.view.sort.WaveSideBar;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CityActivity extends ActivityBase {


    private RecyclerView mRvCityRecyclerView;
    //    private WaveSideBarView mSbvsideView;


    private WaveSideBar mSideBar;
    private City3Adapter mAdapter;
    //    private ClearEditText mClearEditText;
    private LinearLayoutManager manager;

    private List<CityBean.DataBean> mDateList = new ArrayList<>();
    private TitleItemDecoration mDecoration;

    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator mComparator;
    private CommonTitleBar mTitlebarCity;

    private MyHandler mHandler = new MyHandler(CityActivity.this);

    private static class MyHandler extends Handler {
        private WeakReference<CityActivity> activityWeakReference;

        public MyHandler(CityActivity activity) {
            activityWeakReference = new WeakReference<CityActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            CityActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

    private TextView positionTextView;
    private LocationManager locationManager;
    private String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initView();
        locationManager = (LocationManager) getSystemService(Context.
                LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "请检查网络或GPS是否打开",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        final Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (location != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    List<Address> addList = null;
                    Geocoder ge = new Geocoder(getApplicationContext());
                    try {
                        addList = ge.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (addList != null && addList.size() > 0) {
                        for (int i = 0; i < addList.size(); i++) {
                            Address ad = addList.get(i);
                            provider = ad.getLocality();
                        }
                    }
                    positionTextView.setText(provider);
                    if (DataTool.isNullString(positionTextView.getText().toString())){
                        BaseToast.error("自动定位失败，请选择城市");

                    }else {
                        getN_findCitysAutomatic(positionTextView.getText().toString());

                    }


                }
            });
            String currentPosition = "latitude is " + location.getLatitude() + "\n"
                    + "longitude is " + location.getLongitude();
            Log.d("CityActivity", currentPosition);

        }
    }









    /**
     * get请求获取数据 城市列表
     *
     * @param
     */
    private void getN_findCitys(String string) {

//        String s_token = SPTool.getString(CityActivity.this, "token");
//        Log.d("CityActivity", s_token);
//
//        if (DataTool.isNullString(s_token)) {
//            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
        OkGo.<String>get(NetData.N_findCitys + "?city=" + string)
                .tag(this)
//                .headers("token", s_token)
//                .params("city",string)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code = jsonObject.getInt("code");
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (code == 0) {

                                        CityBean bean = new Gson().fromJson(data, CityBean.class);
                                        mDateList = bean.getData();
                                        // 根据a-z进行排序源数据
                                        Collections.sort(mDateList, mComparator);

                                        //RecyclerView设置manager
                                        manager = new LinearLayoutManager(CityActivity.this);
                                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                                        mRvCityRecyclerView.setLayoutManager(manager);
                                        mAdapter = new City3Adapter(CityActivity.this, mDateList);
                                        mRvCityRecyclerView.setAdapter(mAdapter);
                                        mDecoration = new TitleItemDecoration(CityActivity.this, mDateList);
                                        //如果add两个，那么按照先后顺序，依次渲染。
                                        mRvCityRecyclerView.addItemDecoration(mDecoration);
                                        mRvCityRecyclerView.addItemDecoration(new DividerItemDecoration(CityActivity.this, DividerItemDecoration.VERTICAL));
                                        mAdapter.setOnItemClickListener(new City3Adapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, int position) {
                                                SPTool.putString(CityActivity.this,"city_name",mDateList.get(position).getName());
                                                SPTool.putInt(CityActivity.this,"city_id",mDateList.get(position).getId());
                                                finish();




                                            }
                                        });
                                        //BaseToast.success(msg);

                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(CityActivity.this, "token");
                                        startActivity(new Intent(CityActivity.this, LoginActivity.class));
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


    private void initView() {
        positionTextView = (TextView) findViewById(R.id.tv_city);
        positionTextView.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                if (DataTool.isNullString(positionTextView.getText().toString())){
                    BaseToast.error("自动定位失败，请选择城市");

                }else {
//                    SPTool.putString(CityActivity.this,"city_name",positionTextView.getText().toString());
                    finish();
                }


            }
        });
        mRvCityRecyclerView = (RecyclerView) findViewById(R.id.rv_city_recycler_view);
        mSideBar = (WaveSideBar) findViewById(R.id.sbvside_view);

        mComparator = new PinyinComparator();


        //设置右侧SideBar触摸监听
        mSideBar.setOnTouchLetterChangeListener(new WaveSideBar.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //该字母首次出现的位置
                int position = mAdapter.getPositionForSection(letter.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

        getN_findCitys("");


        mTitlebarCity = (CommonTitleBar) findViewById(R.id.titlebar_city);
        mTitlebarCity.setListener(new CommonTitleBar.OnTitleBarListener() {
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

    }

    private void initViews() {

//
//        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
//
//        //根据输入框输入值的改变来过滤搜索
//        mClearEditText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
//                filterData(s.toString());
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
    }
    private void getN_findCitysAutomatic(String string) {

//        String s_token = SPTool.getString(CityActivity.this, "token");
//        Log.d("CityActivity", s_token);
//
//        if (DataTool.isNullString(s_token)) {
//            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
//            return;
//        }
        OkGo.<String>post(NetData.N_findCitysAutomatic)
                .tag(this)
//                .headers("token", s_token)
                .params("cityName",string)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
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
                                         int cityid = 0;
                                        try {
                                            cityid = finalJsonObject.getInt("data");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        SPTool.putString(CityActivity.this,"city_name",positionTextView.getText().toString());

                                        SPTool.putInt(CityActivity.this,"city_id",cityid);





                                    } else if (code == 1004) {
                                        ActivityTool.finishAllActivity();
                                        SPTool.remove(CityActivity.this, "token");
                                        startActivity(new Intent(CityActivity.this, LoginActivity.class));
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


}

