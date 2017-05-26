package com.lottery.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.lottery.utils.ToastUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义的JPush广播接收器
 * Created by Administrator on 2017/5/23.
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "DSH -> JPushReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive: 执行");
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) { //接收自定义通知
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE); //通知内容
//            ToastUtils.showLongToast(context,message);
            Intent receiver = new Intent("com.lottery.JPUSH_RECEIVER");
            receiver.putExtra("msg",message);
            context.sendBroadcast(receiver);//发送广播

        }
    }
}
