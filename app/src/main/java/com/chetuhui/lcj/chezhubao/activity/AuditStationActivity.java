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
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.adapter.AuditStationAdapter;
import com.chetuhui.lcj.chezhubao.adapter.CarAdapter;
import com.chetuhui.lcj.chezhubao.model.AuditStationBean;
import com.chetuhui.lcj.chezhubao.model.CarBean;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao.tool.BaseTool;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.chetuhui.lcj.chezhubao.tool.Constants.MIN_CLICK_DELAY_TIME;

public class AuditStationActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarAs;
    private CheckBox mCbItemAs;
    /**
     * 审核服务站名称
     */
    private TextView mTvItemSelectCarCode;
    /**
     * 电话：028-12434242
     */
    private TextView mTvItemSelectCarCod;
    /**
     * 高新区环球中心S2三楼
     */
    private TextView mTvItemSelectCarCo;
    private RecyclerView mRvAs;
    private SwipeRefreshLayout mSlAs;
    /**
     * 下一步
     */
    private SuperTextView mTvAsXiayibu;


    private List<AuditStationBean.DataBean> mBeanList=new ArrayList<>();
    private AuditStationAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private int num=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_station);
        initView();
//        initPullRefresh();
//        getN_findBusinessByCity("",""+num);
    }

    private void initView() {
        mTitlebarAs = (CommonTitleBar) findViewById(R.id.titlebar_as);
        mTitlebarAs.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mCbItemAs = (CheckBox) findViewById(R.id.cb_item_as);
        mCbItemAs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){

                }
            }
        });

        mTvItemSelectCarCode = (TextView) findViewById(R.id.tv_item_select_car_code);
        mTvItemSelectCarCode.setOnClickListener(this);
        mTvItemSelectCarCod = (TextView) findViewById(R.id.tv_item_select_car_cod);
        mTvItemSelectCarCod.setOnClickListener(this);
        mTvItemSelectCarCo = (TextView) findViewById(R.id.tv_item_select_car_co);
        mTvItemSelectCarCo.setOnClickListener(this);
        mRvAs = (RecyclerView) findViewById(R.id.rv_as_);
        mRvAs.setOnClickListener(this);
        mSlAs = (SwipeRefreshLayout) findViewById(R.id.sl_as_);
        mSlAs.setOnClickListener(this);
        mTvAsXiayibu = (SuperTextView) findViewById(R.id.tv_as_xiayibu);
        mTvAsXiayibu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.cb_item_as:
                break;
            case R.id.tv_item_select_car_code:
                break;
            case R.id.tv_item_select_car_cod:
                break;
            case R.id.tv_item_select_car_co:
                break;
            case R.id.rv_as_:
                break;
            case R.id.sl_as_:
                break;
            case R.id.tv_as_xiayibu:
                if (mCbItemAs.isChecked()){
                    intent=new Intent(AuditStationActivity.this,ConfirmActivity.class);
                }else {
                    BaseToast.error("请选择站点");
                }

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
    private void initPullRefresh() {
        mSlAs.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                num=1;
                getN_findBusinessByCity("",""+num);

            }
        });
    }
    private void getN_findBusinessByCity(String code,String num) {
        String s_token = SPTool.getString(AuditStationActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findBusinessByCity+"?pageNum="+num+"&pageSize=10&cityId="+code)
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
                                AuditStationBean bean= new Gson().fromJson(data, AuditStationBean.class);
                                mBeanList=bean.getData();
                                initRecylerView();
                                mSlAs.setRefreshing(false);




                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(AuditStationActivity.this,"token");
                                startActivity(new Intent(AuditStationActivity.this,LoginActivity.class));
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

    private void initRecylerView() {

        mAdapter = new AuditStationAdapter(this,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvAs.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvAs.setLayoutManager(mLayoutManager);
        mRvAs.setAdapter(mAdapter);
    }



}
