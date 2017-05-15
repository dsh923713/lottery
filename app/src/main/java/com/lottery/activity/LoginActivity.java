package com.lottery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lottery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener{
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
//    @OnClick(R.id.tv_login) void login(){
//        startActivityAndFinish(HomeActivity.class);
//    }
//
//    @OnClick(R.id.tv_register) void register(){
//        startActivity(RegisterActivity.class);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:
                startActivityAndFinish(HomeActivity.class);
                break;
            case R.id.tv_register:
                startActivity(RegisterActivity.class);
                break;
        }
    }
}
