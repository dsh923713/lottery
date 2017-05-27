package com.lottery.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.lottery.utils.LocalBroadManager;
import com.lottery.utils.ToastUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义的JPush广播接收器
 * Created by Administrator on 2017/5/23.
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "DSH -> JPushReceiver";
    private String message;
    private Bundle bundle;

    @Override
    public void onReceive(Context context, Intent intent) {
        bundle = intent.getExtras();

        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) { //接收自定义消息
            message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        }
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) { //接收普通消息
            message = (String) bundle.get(JPushInterface.EXTRA_ALERT);
        }else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {
            // notification点击打开，主要针对是普通的推送消息
            ToastUtils.showShortToast(context,"用户点击打开了通知");
            // 打开自定义的Activity
//            Intent i = new Intent(context, LoginActivity.class);
//            i.putExtras(bundle);
//            // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(i);
        }
        if ( !TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))){
            message = bundle.getString(JPushInterface.EXTRA_EXTRA);
        }
        if (!TextUtils.isEmpty(message)){
            ToastUtils.showLongToast(context, message);
            Intent receiver = new Intent("com.zmq.lottery.JPUSH_RECEIVER");
            receiver.putExtra("msg",message);
            LocalBroadManager.getInstance().sendBroadcast(receiver);//发送广播
        }
    }
}
