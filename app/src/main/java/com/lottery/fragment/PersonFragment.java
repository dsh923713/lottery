package com.lottery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lottery.R;
import com.lottery.activity.BusinessRecordActivity;
import com.lottery.activity.LoginActivity;
import com.lottery.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/15.
 */

public class PersonFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.civ_head)
    ImageView civ_head; //头像
    @BindView(R.id.tv_name)
    TextView tv_name;  //昵称
    @BindView(R.id.tv_login_style)
    TextView tv_login_style;  //登陆方式
    @BindView(R.id.tv_business_record)
    TextView tv_business_record; //交易记录
    @BindView(R.id.tv_my_news)
    TextView tv_my_news; //我的消息
    @BindView(R.id.tv_contact_custom_service)
    TextView tv_contact_custom_service; //联系客服
    @BindView(R.id.tv_my_extend)
    TextView tv_my_extend;  //我的推广
    @BindView(R.id.tv_back_login)
    TextView tv_back_login; //退出登陆

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        setStatusBar(ContextCompat.getColor(activity, R.color.colorAccent));
        //注册点击事件
        tv_business_record.setOnClickListener(this);
        tv_my_news.setOnClickListener(this);
        tv_contact_custom_service.setOnClickListener(this);
        tv_my_extend.setOnClickListener(this);
        tv_back_login.setOnClickListener(this);
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_business_record: //交易记录
                startActivity(BusinessRecordActivity.class);
                break;
            case R.id.tv_my_news:  //我的消息
                break;
            case R.id.tv_contact_custom_service:  //联系客服
                break;
            case R.id.tv_my_extend: //我的推广
                break;
            case R.id.tv_back_login:  //退出登陆
                startActivityAndFinish(LoginActivity.class);
                break;
        }
    }
}
