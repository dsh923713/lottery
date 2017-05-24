package com.lottery.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lottery.R;
import com.lottery.base.BaseActivity;
import com.lottery.utils.ClickUtil;
import com.lottery.utils.ExampleUtil;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DSH -> LoginActivity";
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.tv_register)
    TextView tv_register;

    public LoginActivity() {
        super(R.layout.activity_login);
    }

    @Override
    protected void initContentView(Bundle bundle) {

    }

    @Override
    protected void initView() {
        setTitle("登陆");
        ButterKnife.bind(this);
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
//                setAlias();
                startActivityAndFinish(HomeActivity.class);
                break;
            case R.id.tv_register:
                startActivity(RegisterActivity.class);
                break;
        }
    }

    /**
     * 判断用户名与密码是否正常并将用户名设置为JPush别名
     */
    private void setAlias() {
        String alias = et_name.getText().toString().trim(); //用户名（设置JPush别名）
        String pwd = et_password.getText().toString().trim();//密码
        if (TextUtils.isEmpty(alias)) {
            showShortToast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showShortToast("密码不能为空");
            return;
        }
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            showShortToast("别名不可用");
            return;
        }
        if (!alias.equals("admin") || !pwd.equals("123456")){
            showShortToast("用户名或密码不正确！");
            return;
        }
        if (alias.equals("admin") && pwd.equals("123456")){
            startActivityAndFinish(HomeActivity.class);
        }

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
            ExampleUtil.showToast(logs, getApplicationContext());
        }
    };
    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    Log.d(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
}
