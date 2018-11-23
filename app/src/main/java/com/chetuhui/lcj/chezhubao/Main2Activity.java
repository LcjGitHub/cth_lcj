package com.chetuhui.lcj.chezhubao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chetuhui.lcj.chezhubao.activity.HomeActivity;
import com.chetuhui.lcj.chezhubao.activity.LoginActivity;
import com.chetuhui.lcj.chezhubao.tool.SPTool;
import com.chetuhui.lcj.chezhubao.utils.NetData;
import com.chetuhui.lcj.chezhubao.view.BaseToast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String token= SPTool.getString(Main2Activity.this,"token");
                Log.d("Main2Activity", token);
                    if (token.equals("")){
                        startActivity(new Intent(Main2Activity.this, LoginActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(Main2Activity.this, HomeActivity.class));
                        finish();
                    }



            }
        }, 1000);


    }

    private void getN_findAdvertisement() {

        OkGo.<String>get(NetData.N_findAdvertisement)
                .tag(this)

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
