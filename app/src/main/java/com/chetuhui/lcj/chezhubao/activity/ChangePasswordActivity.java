package com.chetuhui.lcj.chezhubao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chetuhui.lcj.chezhubao.R;
import com.chetuhui.lcj.chezhubao.model.LoginBean;
import com.chetuhui.lcj.chezhubao.tool.ActivityTool;
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

public class ChangePasswordActivity extends ActivityBase implements View.OnClickListener {

    private CommonTitleBar mTitlebarChangepw;
    /**  */
    private EditText mEtChangepwPassword1;
    private ImageView mIvChangepwCleanPassword1;
    private ImageView mIvChangepwShowPwd1;
    /**  */
    private EditText mEtLchangepwPassword2;
    private ImageView mIvChangepwCleanPassword2;
    private ImageView mIvChangepwShowPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        mTitlebarChangepw = (CommonTitleBar) findViewById(R.id.titlebar_changepw);
        mTitlebarChangepw.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_RIGHT_TEXT) {
                    if (DataTool.isNullString(mEtChangepwPassword1.getText().toString())&&DataTool.isNullString(mEtLchangepwPassword2.getText().toString())){
                        BaseToast.error("请检查密码不能为空");
                    }else {
                        getN_updatePassword(mEtChangepwPassword1.getText().toString(),mEtLchangepwPassword2.getText().toString());
                    }



                }
                if (action==CommonTitleBar.ACTION_LEFT_BUTTON){
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


        mEtChangepwPassword1 = (EditText) findViewById(R.id.et_changepw_password1);
        mEtChangepwPassword1.setOnClickListener(this);
        mIvChangepwCleanPassword1 = (ImageView) findViewById(R.id.iv_changepw_clean_password1);
        mIvChangepwCleanPassword1.setOnClickListener(this);
        mIvChangepwShowPwd1 = (ImageView) findViewById(R.id.iv_changepw_show_pwd1);
        mIvChangepwShowPwd1.setOnClickListener(this);
        mEtLchangepwPassword2 = (EditText) findViewById(R.id.et_lchangepw_password2);
        mEtLchangepwPassword2.setOnClickListener(this);
        mIvChangepwCleanPassword2 = (ImageView) findViewById(R.id.iv_changepw_clean_password2);
        mIvChangepwCleanPassword2.setOnClickListener(this);
        mIvChangepwShowPwd2 = (ImageView) findViewById(R.id.iv_changepw_show_pwd2);
        mIvChangepwShowPwd2.setOnClickListener(this);

        mEtChangepwPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvChangepwCleanPassword1.getVisibility() == View.GONE) {
                    mIvChangepwCleanPassword1.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvChangepwCleanPassword1.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty()) {
                    return;
                }
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(mContext, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtChangepwPassword1.setSelection(s.length());
                }
            }
        });
        mEtLchangepwPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvChangepwCleanPassword2.getVisibility() == View.GONE) {
                    mIvChangepwCleanPassword2.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvChangepwCleanPassword2.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty()) {
                    return;
                }
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(mContext, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtLchangepwPassword2.setSelection(s.length());
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.et_changepw_password1:
                break;
            case R.id.iv_changepw_clean_password1:
                mEtChangepwPassword1.setText("");
                break;
            case R.id.iv_changepw_show_pwd1:
                if (mEtChangepwPassword1.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtChangepwPassword1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvChangepwCleanPassword1.setImageResource(R.drawable.openeye);
                } else {
                    mEtChangepwPassword1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvChangepwCleanPassword1.setImageResource(R.drawable.closeeye);
                }
                String pwd = mEtChangepwPassword1.getText().toString();
                if (!TextUtils.isEmpty(pwd)) {
                    mEtChangepwPassword1.setSelection(pwd.length());

                }

                break;
            case R.id.et_lchangepw_password2:
                break;
            case R.id.iv_changepw_clean_password2:
                mEtLchangepwPassword2.setText("");
                break;
            case R.id.iv_changepw_show_pwd2:
                if (mEtLchangepwPassword2.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtLchangepwPassword2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvChangepwCleanPassword1.setImageResource(R.drawable.openeye);
                } else {
                    mEtLchangepwPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvChangepwCleanPassword2.setImageResource(R.drawable.closeeye);
                }
                String pwd2 = mEtLchangepwPassword2.getText().toString();
                if (!TextUtils.isEmpty(pwd2)) {
                    mEtLchangepwPassword2.setSelection(pwd2.length());

                }
                break;
        }
    }
    private void getN_updatePassword(String pw1 ,String pw2) {
        String s_token = SPTool.getString(ChangePasswordActivity.this, "token");
        Log.d("CityActivity", s_token);

        if (DataTool.isNullString(s_token)) {
            Toast.makeText(this, "获取token失败", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(NetData.N_updatePassword)
                .tag(this)
                .headers("token",s_token)
                .params("newPassword",pw1)
                .params("passwordAgain",pw2)

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

                            }else if (code==1004){
                                ActivityTool.finishAllActivity();
                                startActivity(new Intent(ChangePasswordActivity.this,LoginActivity.class));
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
