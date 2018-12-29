package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.AuditStationAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.CarAdapter;
import com.chetuhui.lcj.chezhubao_x.adapter.PopularAdapter;
import com.chetuhui.lcj.chezhubao_x.model.AuditStationBean;
import com.chetuhui.lcj.chezhubao_x.model.PopularBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.tool.VibrateTool;
import com.chetuhui.lcj.chezhubao_x.tool.interfaces.OnRepeatClickListener;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.dialog.BaseDialog;
import com.chetuhui.lcj.chezhubao_x.view.dialog.DialogSureCancel;
import com.chetuhui.lcj.chezhubao_x.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

public class PopularActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarPopular;
    private RecyclerView mRvPoppular;
    private ImageView iv_amc_sqt;
    /**
     * 联系客服
     */
    private SuperTextView mTvPopularLxkf;
    private LinearLayout mLlPopular;
    private PopularAdapter mAdapter;
    private List<PopularBean.DataBean> mBeanList=new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private  String con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        initView();
        getN_findQuestions();
        getN_findClause();
    }

    private void initView() {
        iv_amc_sqt = (ImageView) findViewById(R.id.iv_amc_sqt);
        mTitlebarPopular = (CommonTitleBar) findViewById(R.id.titlebar_popular);
        mTitlebarPopular.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mRvPoppular = (RecyclerView) findViewById(R.id.rv_poppular);
        mTvPopularLxkf = (SuperTextView) findViewById(R.id.tv_popular_lxkf);
        mTvPopularLxkf.setOnClickListener(this);
        mLlPopular = (LinearLayout) findViewById(R.id.ll_popular);
    }

    @Override
    public void onClick(View v) {
        if (!BaseTool.isFastClick(MIN_CLICK_DELAY_TIME)) {
            Intent intent = null;
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_popular_lxkf:
                showkf();
                break;
        }
            if (intent!=null){
                startActivity(intent);
            }

        }else{
            //BaseToast.normal("点得太快了");
            return;
        }
    }

    private void getN_findQuestions() {
        String s_token = SPTool.getString(PopularActivity.this, "token");
        Log.d("PopularActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_findQuestions)
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
                                //BaseToast.success(msg);
                                mBeanList.clear();
                                PopularBean bean= new Gson().fromJson(data, PopularBean.class);
                                mBeanList=bean.getData();
                                if (mBeanList.size()==0){
                                    iv_amc_sqt.setVisibility(View.VISIBLE);
                                    mRvPoppular.setVisibility(View.GONE);
                                }else {
                                    iv_amc_sqt.setVisibility(View.GONE);
                                    mRvPoppular.setVisibility(View.VISIBLE);
                                }
                                initRecylerView();





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(PopularActivity.this,"token");
                                startActivity(new Intent(PopularActivity.this,LoginActivity.class));
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

        mAdapter = new PopularAdapter(R.layout.item_popular,mBeanList);

        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvPoppular.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvPoppular.setLayoutManager(mLayoutManager);
        mRvPoppular.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showView(PopularActivity.this,mBeanList.get(position).getTitle(),mBeanList.get(position).getContent(),mBeanList.get(position).getId());

            }
        });

    }
    public void showView(Context context, String title, String cont, final int id) {
        final BaseDialog rxDialog = new BaseDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_popular_show, null);
        final TextView mcontent=view.findViewById(R.id.tv_dg_popular_content);
        final TextView mtitle=view.findViewById(R.id.tv_dg_popular_title);
        final TextView myouyong=view.findViewById(R.id.tv_dg_popular_youyong);
        final TextView mmeiyong=view.findViewById(R.id.tv_dg_popular_meiyong);
        final ImageView close=view.findViewById(R.id.iv_dg_popular_close);
        mcontent.setText(cont);
        mtitle.setText(title);

        close.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                rxDialog.cancel();
            }
        });
        mmeiyong.setOnClickListener(new OnRepeatClickListener() {
            @Override
            public void onRepeatClick(View v) {
                getN_clickQuestions(id,2);

            }
        });

        myouyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getN_clickQuestions(id,1);

            }
        });


        rxDialog.setContentView(view);
        rxDialog.getLayoutParams();
        rxDialog.mLayoutParams.gravity = Gravity.BOTTOM;

        rxDialog.show();
        rxDialog.setFullScreen();
    }
    private void getN_clickQuestions(int id, int type) {
        String s_token = SPTool.getString(PopularActivity.this, "token");
        Log.d("PopularActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_clickQuestions+"?id="+id+"&type="+type)
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
                                //BaseToast.success(msg);





                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(PopularActivity.this,"token");
                                startActivity(new Intent(PopularActivity.this,LoginActivity.class));
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
    private void  showkf(){
        final DialogSureCancel mDialog = new DialogSureCancel(mContext, R.style.PushUpInDialogThem);
        mDialog.setTitle("拨打客服电话");
        mDialog.setContent(""+con);
        mDialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                VibrateTool.vibrateOnce(mContext, 150);
                mDialog.cancel();
            }
        });
        mDialog.setSureListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.cancel();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:"+con );
                intent.setData(data);
                startActivity(intent);


            }
        });
        mDialog.setCancelable(false);
        mDialog.show();


    }
    private void getN_findClause() {
        String s_token = SPTool.getString(PopularActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
//1：法律声明 2：车助保服务协议 3：服务合作协议 4：关于我们 5：客服电话
        OkGo.<String>get(NetData.N_findClause+"?type=6")
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
                                //BaseToast.success(msg);
                                String d=jsonObject.getString("data");
                                JSONObject jsonObject1=new JSONObject(d);
                                con=  jsonObject1.getString("content");




                            } else if (code==1004){
                                ActivityTool.finishAllActivity();
                                SPTool.remove(PopularActivity.this,"token");
                                startActivity(new Intent(PopularActivity.this,LoginActivity.class));
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


}
