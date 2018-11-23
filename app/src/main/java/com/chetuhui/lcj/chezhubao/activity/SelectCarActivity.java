package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.adapter.CarAdapter;
import com.chetuhui.lcj.chezhubao.adapter.RefreshAdapter;
import com.chetuhui.lcj.chezhubao.model.CarBean;
import com.chetuhui.lcj.chezhubao.model.UserlistBean;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;

public class SelectCarActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarSelect;
    /**
     * 添加车辆
     */
    private TextView mTvSelectTianjiachengliang;
    private LinearLayout mLlSelectNull;
    /**
     * 请输入车牌号搜索
     */
    private EditText mEtSelectSousuo;
    private CheckBox mCbSelectQuanxuan;
    private ImageView mIvItemSelectCar;
    /**
     * 川 A**154
     */
    private TextView mTvItemSelectCarCode;
    /**
     * 雪铁龙
     */
    private TextView mTvItemSelectCarName;
    private CheckBox mCbItemSelectCar;
    private RecyclerView mRvSelectCar;
    private SwipeRefreshLayout mSlSelectCar;
    /**
     * 确定
     */
    private TextView mTvSelectQueding;
    private RelativeLayout mRlSelectCentent;


    private List<CarBean.DataBean> mBeanList=new ArrayList<>();
    private CarAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);
        initView();
        initPullRefresh();
        getN_findCarByCode("");
    }

    private void initView() {
        mTitlebarSelect = (CommonTitleBar) findViewById(R.id.titlebar_select);
        mTitlebarSelect.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON) {
                    finish();

                }
                if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {

                   Intent intent =new Intent(SelectCarActivity.this,AddCarActivity.class);
                   startActivity(intent);

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


        mTvSelectTianjiachengliang = (TextView) findViewById(R.id.tv_select_tianjiachengliang);
        mTvSelectTianjiachengliang.setOnClickListener(this);
        mLlSelectNull = (LinearLayout) findViewById(R.id.ll_select_null);
        mLlSelectNull.setOnClickListener(this);
        mEtSelectSousuo = (EditText) findViewById(R.id.et_select_sousuo);
        mEtSelectSousuo.setOnClickListener(this);
        mCbSelectQuanxuan = (CheckBox) findViewById(R.id.cb_select_quanxuan);
        mCbSelectQuanxuan.setOnClickListener(this);
        mIvItemSelectCar = (ImageView) findViewById(R.id.iv_item_select_car);
        mIvItemSelectCar.setOnClickListener(this);
        mTvItemSelectCarCode = (TextView) findViewById(R.id.tv_item_select_car_code);
        mTvItemSelectCarCode.setOnClickListener(this);
        mTvItemSelectCarName = (TextView) findViewById(R.id.tv_item_select_car_name);
        mTvItemSelectCarName.setOnClickListener(this);
        mCbItemSelectCar = (CheckBox) findViewById(R.id.cb_item_select_car);
        mCbItemSelectCar.setOnClickListener(this);
        mRvSelectCar = (RecyclerView) findViewById(R.id.rv_select_car);
        mRvSelectCar.setOnClickListener(this);
        mSlSelectCar = (SwipeRefreshLayout) findViewById(R.id.sl_select_car);
        mSlSelectCar.setOnClickListener(this);
        mTvSelectQueding = (TextView) findViewById(R.id.tv_select_queding);
        mTvSelectQueding.setOnClickListener(this);
        mRlSelectCentent = (RelativeLayout) findViewById(R.id.rl_select_centent);
        mRlSelectCentent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.titlebar_select:
                break;
            case R.id.tv_select_tianjiachengliang:
                intent =new Intent(SelectCarActivity.this,AddCarActivity.class);
                break;

            case R.id.et_select_sousuo:
                break;
            case R.id.cb_select_quanxuan:
                break;
            case R.id.iv_item_select_car:
                break;
            case R.id.tv_item_select_car_code:
                break;
            case R.id.tv_item_select_car_name:
                break;
            case R.id.cb_item_select_car:
                break;

            case R.id.tv_select_queding:
                intent =new Intent(SelectCarActivity.this,SelectReviewActivity.class);
                break;

        }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
            BaseToast.normal("点得太快了");
            return;
        }
    }

    private void getN_findCarByCode(String code) {
        String s_token = SPTool.getString(SelectCarActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findCarByCode)
                .tag(this)
                .headers("token",s_token)

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
                                BaseToast.success(msg);
                                mBeanList.clear();
                                CarBean carBean= new Gson().fromJson(data, CarBean.class);
                                mBeanList=carBean.getData();
                                initRecylerView();
                                mSlSelectCar.setRefreshing(false);
                                if (mBeanList.size()==0){
                                    mLlSelectNull.setVisibility(View.VISIBLE);
                                    mRlSelectCentent.setVisibility(View.GONE);
                                }else {
                                    mLlSelectNull.setVisibility(View.GONE);
                                    mRlSelectCentent.setVisibility(View.VISIBLE);
                                }



                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(SelectCarActivity.this,"token");
                                startActivity(new Intent(SelectCarActivity.this,LoginActivity.class));
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
    private void initPullRefresh() {
        mSlSelectCar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getN_findCarByCode("");

            }
        });
    }
    private void initRecylerView() {

        mAdapter = new CarAdapter(R.layout.item_selectcar,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvSelectCar.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvSelectCar.setLayoutManager(mLayoutManager);
        mRvSelectCar.setAdapter(mAdapter);
    }




}
