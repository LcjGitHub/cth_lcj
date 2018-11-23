package com.chetuhui.lcj.chezhubao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.tool.DataTool;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.chetuhui.lcj.chezhubao.view.supertextview.SuperTextView;
import com.chetuhui.lcj.chezhubao.view.titlebar.CommonTitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class BindingActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarChangepw;
    /**
     * 解绑
     */
    private SuperTextView mTvBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        initView();
    }

    private void initView() {
        mTitlebarChangepw = (CommonTitleBar) findViewById(R.id.titlebar_changepw);
        mTitlebarChangepw.setListener(new CommonTitleBar.OnTitleBarListener() {
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

        mTvBinding = (SuperTextView) findViewById(R.id.tv_binding_);
        mTvBinding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_binding_:
                N_removeOpenid();

                break;
        }
    }
    private void N_removeOpenid() {
        String s_token = SPTool.getString(BindingActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(NetData.N_removeOpenid)
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
                            BaseToast.success(msg);
                            int code=jsonObject.getInt("code");
                            if (code==0){
                                finish();
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
