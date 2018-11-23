package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.adapter.CityAdapter;
import com.chetuhui.lcj.chezhubao.adapter.CityAdapter2;
import com.chetuhui.lcj.chezhubao.model.CityBean;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.sidebar.PinnedHeaderDecoration;
import com.chetuhui.lcj.chezhubao.view.sidebar.WaveSideBarView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class CityActivity extends ActivityBase {


    private RecyclerView mRvCityRecyclerView;
    private WaveSideBarView mSbvsideView;
    private CityAdapter2 mAdapterContactCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initView();
        getByOkGoModelCity("");

    }


    /**
     * get请求获取数据 城市列表
     *
     * @param
     */
    private void getByOkGoModelCity(String string) {

        String s_token = SPTool.getString(CityActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_findCitys)
                .tag(this)
                .headers("token",s_token)
                .params("city",string)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            String msg = jsonObject.getString("msg");
                            int code=jsonObject.getInt("code");
                            if (code==0){

                                CityBean cityBean= new Gson().fromJson(data, CityBean.class);

                                mAdapterContactCity = new CityAdapter2(R.layout.item_wave_contact, cityBean.getData());
                                mRvCityRecyclerView.setAdapter(mAdapterContactCity);
                                mAdapterContactCity.notifyDataSetChanged();

                                BaseToast.success(msg);

                            }else if (code==1004){
                                ActivityTool.finishAllActivity();
                                startActivity(new Intent(CityActivity.this,LoginActivity.class));
                                BaseToast.error("登录过期，请重新登录");

                            }else {
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


    private void initView() {
        mRvCityRecyclerView = (RecyclerView) findViewById(R.id.rv_city_recycler_view);
        mSbvsideView = (WaveSideBarView) findViewById(R.id.sbvside_view);

        mRvCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRvCityRecyclerView.addItemDecoration(decoration);



        mSbvsideView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int pos = mAdapterContactCity.getLetterPosition(letter);

                if (pos != -1) {
                    mRvCityRecyclerView.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) mRvCityRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });
    }

    }

