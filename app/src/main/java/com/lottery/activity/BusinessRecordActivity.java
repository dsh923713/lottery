package com.lottery.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lottery.R;
import com.lottery.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessRecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tv_all)
    TextView tv_all;  //全部
    @BindView(R.id.tv_bottom_pour)
    TextView tv_bottom_pour;  //下注
    @BindView(R.id.tv_recharge)
    TextView tv_recharge;  //充值
    @BindView(R.id.srl_refurbish)
    SwipeRefreshLayout srl_refurbish;//下拉刷新
    @BindView(R.id.lv_business)
    ListView lv_business;//显示记录

    public BusinessRecordActivity() {
        super(R.layout.activity_business_record);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initContentView(Bundle bundle) {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this); //绑定注解
        setTitle("交易记录"); //标题
        //返回按钮
        setLeftIcon(R.mipmap.ic_back, "返回", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        srl_refurbish.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));//下拉刷新时控件的颜色
        srl_refurbish.setOnRefreshListener(this);//注册下拉刷新

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        showShortToast("刷新中...");
        srl_refurbish.setRefreshing(false);//刷新结束 下拉框消失
    }
}
