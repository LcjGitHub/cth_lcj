package com.chetuhui.lcj.chezhubao_x.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;


import com.chetuhui.lcj.chezhubao_x.MainActivity;
import com.chetuhui.lcj.chezhubao_x.activity.HomeActivity;
import com.chetuhui.lcj.chezhubao_x.activity.MessageActivity;
import com.chetuhui.lcj.chezhubao_x.fragment.HomeFragment;
import com.chetuhui.lcj.chezhubao_x.fragment.MessageFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

public class jPushReceiver extends BroadcastReceiver {

    private static final String TAG = "JIGUANG";
    public static String regId;
    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            Bundle bundle = intent.getExtras();
            if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 用户点击打开了通知");

                // 在这里根据 JPushInterface.EXTRA_EXTRA(附加字段) 的内容处理代码，
                // 比如打开新的Activity， 打开一个网页等..
                ActivityManager activityManager =
                        (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
                String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);

                Log.d("extra", "onReceive: "+extra);
                try {
                    JSONObject extraj = new JSONObject(extra);
                    String type = extraj.getString("type");
                    //这里实现的是如果从桌面启动就先打开MainActivity，然后到推送的界面，点击返回然后回到主页，
                    //如果是从应用内部启动，就直接跳转com.xxx.xxx是你的activity文件所在的包名
                    if (activityManager.getRunningTasks(1).get(0).topActivity.getClassName().contains("com.chetuhui.lcj.chezhubao.activity")) { //这里是判断当前启动你App的activity是从桌面上启动的，还是从app内部启动的
                        Intent intent1 = new Intent();
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        switch (type) {
                            case "mg":
                                intent1.setClass(context, MessageActivity.class);
                                context.startActivity(intent1);
                                break;
//                        case "studio":
//                            intent1.setClass(context, HsDetailActivity.class);
//                            intent1.putExtra("id", txt);
//                            context.startActivity(intent1);
//                            break;
//                        case "studio_home":
//                            intent1.setClass(context, HuaShiActivity.class);
//                            context.startActivity(intent1);
//                            break;
                        }
                    } else {
                        Intent intentMain = new Intent(context, HomeActivity.class);
                        intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Intent intent1 = new Intent();
                        Intent[] intents = new Intent[]{intentMain, intent1};
                        switch (type) {
                            case "mg":
                                intent1.setClass(context, MessageActivity.class);
                                context.startActivities(intents);
                                break;

//                        case "studio":
//                            intent1.setClass(context, HsDetailActivity.class);
//                            intent1.putExtra("id", txt);
//                            context.startActivities(intents);
//                            break;
//                        case "studio_home":
//                            intent1.setClass(context, HuaShiActivity.class);
//                            context.startActivities(intents);
//                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...

            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                String EXTRA_MESSAGE = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息(内容为): " +EXTRA_MESSAGE );
                Message msg = Message.obtain(); // 实例化消息对象
                msg.what = 1; // 消息标识
                msg.obj = ""+EXTRA_MESSAGE; // 消息内容存放
              HomeFragment.mhandler.sendMessage(msg);

                // 自定义消息不是通知，默认不会被SDK展示到通知栏上，极光推送仅负责透传给SDK。其内容和展示形式完全由开发者自己定义。
                // 自定义消息主要用于应用的内部业务逻辑和特殊展示需求
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");

                String extra_json = bundle.getString(JPushInterface.EXTRA_EXTRA);
                String EXTRA_MESSAGE = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                if (!TextUtils.isEmpty(extra_json))
                    Log.d(TAG, "[MyReceiver] 接收到推送下来的通知附加字段" + extra_json);
                    Log.d(TAG, "[MyReceiver] 接收到推送下来的通知内容" + EXTRA_MESSAGE);

                // 可以利用附加字段来区别Notication,指定不同的动作,extra_json是个json字符串
                // 通知（Notification），指在手机的通知栏（状态栏）上会显示的一条通知信息
            }  else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    }

