package com.lottery.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lottery.R;
import com.lottery.adapter.MsgChatAdapter;
import com.lottery.base.BaseActivity;
import com.lottery.bean.MsgBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shaohui.bottomdialog.BottomDialog;

public class MsgChatActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rv_msg)
    RecyclerView rv_msg; //消息列表
    @BindView(R.id.et_msg)
    EditText et_msg; //消息输入框
    @BindView(R.id.tv_bottom_pour)
    TextView tv_bottom_pour; //下注
    @BindView(R.id.tv_lead_up)
    TextView tv_lead_up; //抢庄

    List<MsgBean> data = new ArrayList<>(); //模拟数据
    private MsgChatAdapter adapter;

    public MsgChatActivity() {
        super(R.layout.activity_msg_chat);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void initContentView(Bundle bundle) {

    }

    @Override
    protected void initView() {
        setTitle("");//标题为空
        setLeftIcon(R.mipmap.ic_back, "", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setRightIcon(R.mipmap.ic_upload, "中奖明细", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShortToast("开发中...");
            }
        });
        setStatusBar(ContextCompat.getColor(this, R.color.colorAccent));//改变状态栏颜色
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent)); //改变toolbar颜色
        ButterKnife.bind(this); //绑定注入
        addData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_msg.setLayoutManager(layoutManager);//显示样式
        adapter = new MsgChatAdapter(data); //声明适配器
        rv_msg.setAdapter(adapter);
        tv_bottom_pour.setOnClickListener(this);
        tv_lead_up.setOnClickListener(this);

    }

    private void addData() {
        MsgBean msgBean1 = new MsgBean(false, "测试数据试试看怎么样？", MsgBean.TYPE_RECEIVED, R.drawable.renma);
        data.add(msgBean1);
        MsgBean msgBean2 = new MsgBean(true, "200", MsgBean.TYPE_RECEIVED, R.drawable.renma);
        data.add(msgBean2);
        MsgBean msgBean3 = new MsgBean(false, "发表的数据试试看怎么样？", MsgBean.TYPE_SENT, R.drawable.xiaohei);
        data.add(msgBean3);
        MsgBean msgBean4 = new MsgBean(true, "500", MsgBean.TYPE_SENT, R.drawable.xiaohei);
        data.add(msgBean4);
        MsgBean msgBean5 = new MsgBean(false, "测试数据试试看怎么样？", MsgBean.TYPE_RECEIVED, R.drawable.renma);
        data.add(msgBean5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bottom_pour: //下注
                setPopupWindow();
                break;
            case R.id.tv_lead_up:  //抢庄
                String content = et_msg.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    MsgBean msgBean = new MsgBean(false, content, MsgBean.TYPE_SENT, R.drawable.xiaohei);
                    data.add(msgBean);
                    adapter.notifyItemInserted(data.size() - 1);//当有新消息，刷新recyclerview显示
                    rv_msg.scrollToPosition(data.size() - 1);//将recyclerview定位在最后一行
                    et_msg.setText("");//清空输入框数据
                }
                break;
        }
    }

    private void setPopupWindow() {
        BottomDialog.create(getSupportFragmentManager()).setViewListener(new BottomDialog.ViewListener() {
            @Override
            public void bindView(View v) {
                switch (v.getId()){
                    case R.id.tv_hundred_money:
                        showShortToast("100");
                        break;
                }
            }
        }).setLayoutRes(R.layout.dia_msg_chat).show();
    }
}
