package com.lottery.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lottery.R;
import com.lottery.base.BaseActivity;
import com.lottery.bean.BusinessBean;
import com.lottery.utils.DateUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessRecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener
        , View.OnClickListener {
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
    private List<BusinessBean> data = new ArrayList<>();//测试数据
    private boolean isChecked1 = true;//全部按钮是否选中
    private boolean isChecked2;//下注按钮是否选中
    private boolean isChecked3;//充值按钮是否选中
    private CommonAdapter<BusinessBean> adapter;

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
        //注册点击监听事件
        tv_all.setOnClickListener(this);
        tv_bottom_pour.setOnClickListener(this);
        tv_recharge.setOnClickListener(this);

        srl_refurbish.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));//下拉刷新时控件的颜色
        srl_refurbish.setOnRefreshListener(this);//注册下拉刷新
        addData("张三");
        adapter = new CommonAdapter<BusinessBean>(this, R.layout.item_lv_act_business, data) {
            @Override
            protected void convert(ViewHolder viewHolder, BusinessBean item, int position) {
                viewHolder.setText(R.id.tv_name, item.getName())
                        .setText(R.id.tv_style, item.getStyle())
                        .setText(R.id.tv_time, item.getDateTime())
                        .setText(R.id.tv_money, "+" + item.getMoneyNum());
            }
        };
        lv_business.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        addData("刘六");
        srl_refurbish.setRefreshing(false);//刷新结束 下拉框消失
        adapter.notifyDataSetChanged();
    }

    BusinessBean bean = null;

    private void addData(String name) {
        data.clear();
        String time = DateUtil.toTime8(System.currentTimeMillis());
        for (int i = 0; i < 20; i++) {
            bean = new BusinessBean(name, time, "充值", "200");
            data.add(bean);
        }
        srl_refurbish.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_all://全部
                isChecked1 = true;
                isChecked2 = false;
                isChecked3 = false;
                srl_refurbish.setRefreshing(true);
                addData("张三");
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_bottom_pour://下注
                isChecked2 = true;
                isChecked1 = false;
                isChecked3 = false;
                srl_refurbish.setRefreshing(true);
                addData("李四");
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_recharge://充值
                isChecked3 = true;
                isChecked1 = false;
                isChecked2 = false;
                srl_refurbish.setRefreshing(true);
                addData("王五");
                adapter.notifyDataSetChanged();
                break;
        }
        if (isChecked1){
            tv_all.setTextColor(ContextCompat.getColor(BusinessRecordActivity.this,R.color.red));
        }else {
            tv_all.setTextColor(ContextCompat.getColor(BusinessRecordActivity.this,R.color.black));
        }
        if (isChecked2){
            tv_bottom_pour.setTextColor(ContextCompat.getColor(BusinessRecordActivity.this,R.color.red));
        }else {
            tv_bottom_pour.setTextColor(ContextCompat.getColor(BusinessRecordActivity.this,R.color.black));
        }
        if (isChecked3){
            tv_recharge.setTextColor(ContextCompat.getColor(BusinessRecordActivity.this,R.color.red));
        }else {
            tv_recharge.setTextColor(ContextCompat.getColor(BusinessRecordActivity.this,R.color.black));
        }
    }
}
