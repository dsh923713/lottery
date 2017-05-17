package com.lottery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lottery.R;
import com.lottery.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_register_phone)
    EditText et_register_phone;
    @BindView(R.id.et_register_pw)
    EditText et_register_pw;
    @BindView(R.id.et_sure_register_pw)
    EditText et_sure_register_pw;
    @BindView(R.id.et_register_captcha_code)
    EditText et_register_captcha_code;
    @BindView(R.id.et_register_request_code)
    EditText et_register_request_code;
    @BindView(R.id.tv_register)
    TextView tv_register;

    public RegisterActivity() {
        super(R.layout.activity_register);
    }

    @Override
    protected void initContentView(Bundle bundle) {

    }

    @Override
    protected void initView() {
        setLeftIcon(R.mipmap.ic_back, "", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ButterKnife.bind(this);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
