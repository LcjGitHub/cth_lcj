package com.chetuhui.lcj.chezhubao_x.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.R;
import com.chetuhui.lcj.chezhubao_x.adapter.CarAdapter;
import com.chetuhui.lcj.chezhubao_x.model.CarBean;
import com.chetuhui.lcj.chezhubao_x.tool.ActivityTool;
import com.chetuhui.lcj.chezhubao_x.tool.BaseTool;
import com.chetuhui.lcj.chezhubao_x.tool.DataTool;
import com.chetuhui.lcj.chezhubao_x.tool.SPTool;
import com.chetuhui.lcj.chezhubao_x.utils.NetData;
import com.chetuhui.lcj.chezhubao_x.view.BaseToast;
import com.chetuhui.lcj.chezhubao_x.view.titlebar.CommonTitleBar;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.chetuhui.lcj.chezhubao_x.tool.Constants.MIN_CLICK_DELAY_TIME;

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

    private RecyclerView mRvSelectCar;
    private SwipeRefreshLayout mSlSelectCar;
    /**
     * 确定
     */
    private TextView mTvSelectQueding;
    private RelativeLayout mRlSelectCentent;
    private boolean isonclick=false;
    private String fanan_code="";


    private List<CarBean.DataBean> mBeanList=new ArrayList<>();
//    private CarAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private CheckAdapter checkAdapter;

    private List<String > mStringList=new ArrayList<>();
    private MyHandler mHandler =new MyHandler(SelectCarActivity.this);
    private static class MyHandler extends Handler {
            private WeakReference<SelectCarActivity> activityWeakReference;

            public MyHandler(SelectCarActivity activity) {
                activityWeakReference = new WeakReference<SelectCarActivity>(activity);
            }

            @Override
            public void handleMessage(Message msg) {
                SelectCarActivity activity = activityWeakReference.get();
                if (activity != null) {

                }
            }
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);
        fanan_code=getIntent().getStringExtra("fanan_code");

        initView();
        initPullRefresh();
        getN_findCarByCode("",0);
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
        mEtSelectSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i==0||i==3)&&keyEvent!=null){
                    if (!DataTool.isNullString(mEtSelectSousuo.getText().toString())){
                        getN_findCarByCode(mEtSelectSousuo.getText().toString(),1);
                    }else {
                        BaseToast.success("搜索为空");
                    }

                    return true;
                }

                return false;
            }
        });
//        mEtSelectSousuo.setOnEditorActionListener(new TextView.OnEditorActionListener() {  
//  
//            @Override  
//            public boolean onEditorAction(TextView v, int actionId,  
//                                          KeyEvent event) {  
//                if ((actionId == 0 || actionId == 3) && event != null) {  
//                    //写点击搜索键后的操作  
//                    return true;  
//                }  
//                return false;  
//  
//            }  
//  
//        });  


        mCbSelectQuanxuan = (CheckBox) findViewById(R.id.cb_select_quanxuan);
        mCbSelectQuanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    for (int i = 0; i < mBeanList.size(); i++) {
                        checkAdapter.setSelectItem(i);

                    }


                }else {
                    for (int i = 0; i < mBeanList.size(); i++) {
                        checkAdapter.setSelectItem(i);

                    }
                }

            }
        });


        mIvItemSelectCar = (ImageView) findViewById(R.id.iv_item_select_car);
        mIvItemSelectCar.setOnClickListener(this);
        mTvItemSelectCarCode = (TextView) findViewById(R.id.tv_item_select_car_code);
        mTvItemSelectCarCode.setOnClickListener(this);
        mTvItemSelectCarName = (TextView) findViewById(R.id.tv_item_select_car_name);
        mTvItemSelectCarName.setOnClickListener(this);




        mRvSelectCar = (RecyclerView) findViewById(R.id.rv_select_car);
        mRvSelectCar.setOnClickListener(this);
        mSlSelectCar = (SwipeRefreshLayout) findViewById(R.id.sl_select_car);
        mSlSelectCar.setOnClickListener(this);
        mTvSelectQueding = (TextView) findViewById(R.id.tv_select_queding);
        mTvSelectQueding.setOnClickListener(this);
        mRlSelectCentent = (RelativeLayout) findViewById(R.id.rl_select_centent);
        mRlSelectCentent.setOnClickListener(this);
        if (!DataTool.isNullString(fanan_code)){
            mTvSelectQueding.setVisibility(View.VISIBLE);
        }else {
            mTvSelectQueding.setVisibility(View.GONE);
        }
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

            case R.id.iv_item_select_car:
                break;
            case R.id.tv_item_select_car_code:
                break;
            case R.id.tv_item_select_car_name:
                break;


            case R.id.tv_select_queding:

                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = new JSONObject();
                    JSONObject tmpObj = null;

                    String content ="";
                    mStringList.clear();
                    Map<Integer, Boolean> map = checkAdapter.getMap();
                    Log.d("SelectCarActivity", "map.size>>>>>"+map.size());
                    for(int i =0;i<mBeanList.size();i++){
                        if(map.get(i)){
                            isonclick=true;
                            mStringList.add(mBeanList.get(i).getCarNum());
                        }
                    }

                    //这里是为了测试我们的结果 ，可忽略！
                    for (int j=0;j<mStringList.size();j++){
                        Log.e("TAG",mStringList.get(j));
                        content+=mStringList.get(j)+",";
                    }
                    Gson g = new Gson();
                    String jsonString = g.toJson(mStringList);
                    Log.d("SelectCarActivity", content);
                    Log.d("SelectCarActivity", ">>>>>>>>>>>>"+jsonString);

                    if (isonclick){

                        if (!DataTool.isNullString(jsonString)&!DataTool.isNullString(fanan_code)){

                            getN_sureCars(fanan_code,jsonString);

                        }else {
                            BaseToast.error("失败");
                        }
                    }else {
                        BaseToast.error("请选择车辆");
                    }



//
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

    private void getN_findCarByCode(String code, final int a) {
        String s_token = SPTool.getString(SelectCarActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>get(NetData.N_findCarByCode+"?code="+code)
                .tag(this)
                .headers("token",s_token)

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        final String data = response.body();//这个就是返回来的结果
                        Log.d("RegisteredActivity", data);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(data);
                            final String msg = jsonObject.getString("msg");
                            final int code=jsonObject.getInt("code");
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (code==0){
                                            //BaseToast.success(msg);
                                            mBeanList.clear();
                                            CarBean carBean= new Gson().fromJson(data, CarBean.class);
                                            mBeanList=carBean.getData();
                                            initRecylerView();
                                            mSlSelectCar.setRefreshing(false);
                                            if (a==0){
                                                if (mBeanList.size()==0){
                                                    mLlSelectNull.setVisibility(View.VISIBLE);
                                                    mRlSelectCentent.setVisibility(View.GONE);
                                                    mTitlebarSelect.getRightTextView().setVisibility(View.GONE);

                                                }else {
                                                    mLlSelectNull.setVisibility(View.GONE);
                                                    mRlSelectCentent.setVisibility(View.VISIBLE);
                                                    mTitlebarSelect.getRightTextView().setVisibility(View.VISIBLE);
                                                }

                                            }else if (a==1){
                                                BaseToast.error("没有找到");
                                            }



                                        } else if (code==1004){
                                            ActivityTool.finishAllActivity();
                                            SPTool.remove(SelectCarActivity.this,"token");
                                            startActivity(new Intent(SelectCarActivity.this,LoginActivity.class));
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
    private void initPullRefresh() {
        mSlSelectCar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getN_findCarByCode("",0);

            }
        });
    }
    private void initRecylerView() {

//        mAdapter = new CarAdapter(R.layout.item_selectcar,mBeanList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);



        //添加动画
        mRvSelectCar.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
//        mRecyclerView.addItemDecoration(new RefreshItemDecoration(this,RefreshItemDecoration.VERTICAL_LIST));

        mRvSelectCar.setLayoutManager(mLayoutManager);
        checkAdapter = new CheckAdapter(this, mBeanList);
        mRvSelectCar.setAdapter(checkAdapter);
        //子条目监听
        checkAdapter.setItemClickListener(new RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
//                Toast.makeText(MainActivity.this,"If you are happy - "+ position,Toast.LENGTH_SHORT).show();
                //设置选中的项
                checkAdapter.setSelectItem(position);
            }
        });


    }

    //Adapter数据填充
    class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckViewHolder>{
        private Context mContext;
        private List<CarBean.DataBean> lists;
        private HashMap<Integer,Boolean> Maps=new HashMap<Integer,Boolean>();
        private HashMap<Integer,Boolean>AllMaps=new HashMap<Integer,Boolean>();
        public RecyclerViewOnItemClickListener onItemClickListener;

        //成员方法，初始化checkBox的状态，默认全部不选中
        public CheckAdapter(Context context, List<CarBean.DataBean> lists){
            this.mContext=context;
            this.lists=lists;
            initMap();
        }

        //初始化map内的数据状态，全部重置为false，即为选取状态
        private void initMap() {
            for (int i = 0; i < lists.size(); i++) {
                Maps.put(i, false);
            }
        }


        //获取最终的map存储数据
        public Map<Integer,Boolean> getMap(){
            return Maps;
        }

        //后续扩展 - 获取最终的map存储数据
        public Map<Integer,Boolean>getAllMap(){
            return AllMaps;
        }

        //点击item选中CheckBox
        public void setSelectItem(int position) {
            //对当前状态取反
            if (Maps.get(position)) {
                Maps.put(position, false);
            } else {
                Maps.put(position, true);
            }
            notifyItemChanged(position);
        }
        @Override
        public CheckAdapter.CheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CheckViewHolder checkViewHolder = new CheckViewHolder(LayoutInflater.from(SelectCarActivity.this).inflate(R.layout.item_selectcar, parent,false),onItemClickListener);
            return checkViewHolder;
        }

        @Override
        public void onBindViewHolder(CheckAdapter.CheckViewHolder holder, final int position) {


            holder.tv_item_select_car_name.setText(lists.get(position).getCarBrand());
            holder.tv_item_select_car_code.setText(lists.get(position).getCarNum());
            Glide.with(mContext).load(lists.get(position).getLicensePicture()).apply(bitmapTransform(new CropCircleTransformation())).into((ImageView) holder.iv_item_select_car);

            holder.cb_item_select_car.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Maps.put(position,isChecked);
                }
            });

            if(Maps.get(position)==null){
                Maps.put(position,false);
            }
            //没有设置tag之前会有item重复选框出现，设置tag之后，此问题解决
            holder.cb_item_select_car.setChecked(Maps.get(position));


            //之后扩展使用
            AllMaps.put(position,true);
        }


        @Override
        public int getItemCount() {
            return lists ==null?0:lists.size();
        }

        public void setItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        class CheckViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private RecyclerViewOnItemClickListener mListener;
            private  TextView tv_item_select_car_name,tv_item_select_car_code;
            private ImageView iv_item_select_car;
            private  CheckBox cb_item_select_car;
            public CheckViewHolder(View itemView,RecyclerViewOnItemClickListener onItemClickListener) {
                super(itemView);
                this.mListener = onItemClickListener;
                itemView.setOnClickListener(this);
                tv_item_select_car_name = (TextView) itemView.findViewById(R.id.tv_item_select_car_name);
                tv_item_select_car_code = (TextView) itemView.findViewById(R.id.tv_item_select_car_code);
                cb_item_select_car = (CheckBox) itemView.findViewById(R.id.cb_item_select_car);
                iv_item_select_car = (ImageView) itemView.findViewById(R.id.iv_item_select_car);
            }

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClickListener(v, getPosition());
                }
            }
        }

    }

    //接口回调设置点击事件
    public interface RecyclerViewOnItemClickListener {
        //点击事件
        void onItemClickListener(View view, int position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getN_findCarByCode("",0);
    }
    private void getN_sureCars(String type, final String list) {
        String s_token = SPTool.getString(mContext, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(NetData.N_sureCars )
                .tag(this)
                .headers("token",s_token)
                .params("programCode",""+type)
                .params("carList",""+list)


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
//                                BaseToast.success(msg);


                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        SPTool.putString(SelectCarActivity.this,"car_jsonString",list);
                                        SPTool.putInt(SelectCarActivity.this,"car_size",mStringList.size());

                                       Intent  intent =new Intent(SelectCarActivity.this,SelectReviewActivity.class);
                                       startActivity(intent);
                                       finish();


                                    }
                                });


                            } else if (code == 1004) {
                                ActivityTool.finishAllActivity();
                                SPTool.remove(mContext, "token");
                                startActivity(new Intent(mContext, LoginActivity.class));
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


}
