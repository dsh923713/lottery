package com.lottery.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lottery.R;
import com.lottery.adapter.MsgChatAdapter;
import com.lottery.base.BaseActivity;
import com.lottery.bean.MsgBean;
import com.lottery.utils.DateUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgChatActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DSH -> MsgChatActivity";
    @BindView(R.id.rv_msg)
    RecyclerView rv_msg; //消息列表
    @BindView(R.id.et_msg)
    EditText et_msg; //消息输入框
    @BindView(R.id.tv_bottom_pour)
    TextView tv_bottom_pour; //下注
    @BindView(R.id.tv_lead_up)
    TextView tv_lead_up; //抢庄
    @BindView(R.id.v_background)
    View v_background;//半透明蒙版
    @BindView(R.id.tv_num_time)
    TextView tv_num_time; //期数
    @BindView(R.id.tv_lead_time)
    TextView tv_lead_time; //抢庄时间
    @BindView(R.id.rv_record)
    RecyclerView rv_record; //下注玩家列表
    @BindView(R.id.tv_bottom_pour_record)
    TextView tv_bottom_pour_record;//下注记录


    @BindView(R.id.ll_bottom_pour)
    LinearLayout ll_bottom_pour; //下注弹窗
    @BindView(R.id.tv_hundred_money)
    TextView tv_hundred_money; //100元
    @BindView(R.id.tv_two_hundred_money)
    TextView tv_two_hundred_money;//200元
    @BindView(R.id.tv_three_hundred_money)
    TextView tv_three_hundred_money;//300元
    @BindView(R.id.tv_five_hundred_money)
    TextView tv_five_hundred_money;//500元
    @BindView(R.id.et_bottom_pour_money)
    EditText et_bottom_pour_money; //自定义金额
    @BindView(R.id.tv_sure_bottom_pour)
    TextView tv_sure_bottom_pour; //确认下注

    List<MsgBean> data = new ArrayList<>(); //模拟即时消息数据
    private MsgChatAdapter adapter;

    List<String> recordList = new ArrayList<>();//模拟下注玩家数据
    private CommonAdapter<String> recordAdapter;

    private boolean isShow;//是否显示投注记录
    long time = 5 * 60; //抢庄时间5分钟

    private boolean isClick1; //是否选择下注100
    private boolean isClick2; //是否选择下注200
    private boolean isClick3; //是否选择下注300
    private boolean isClick5; //是否选择下注500

    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    time--;
                    tv_lead_time.setText(DateUtil.getCutDown(time));
                    if (time == 0) { //抢庄时间截止 不能继续点击抢庄
                        timer.cancel();
                        tv_lead_up.setClickable(false);
                    }
                }
            });
        }
    };
    private int size;

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
        timer.schedule(task, 1000, 1000);//启动定时器
        addData();
        LinearLayoutManager msgLayout = new LinearLayoutManager(this);
        rv_msg.setLayoutManager(msgLayout);//显示样式
        adapter = new MsgChatAdapter(data); //声明适配器
        rv_msg.setAdapter(adapter);

        addRecordList();
        LinearLayoutManager recordLayout = new LinearLayoutManager(this);
        recordAdapter = new CommonAdapter<String>(this, R.layout.item_rv_record, recordList) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_play_name, s);
            }
        };
        rv_record.setLayoutManager(recordLayout);
        rv_record.setAdapter(recordAdapter);
        if (recordList.size() > 0) {
            isShow = true;
            setDrawableRight(R.mipmap.ic_up);
        }
        tv_bottom_pour.setOnClickListener(this);
        tv_lead_up.setOnClickListener(this);
        tv_bottom_pour_record.setOnClickListener(this);

        tv_hundred_money.setOnClickListener(this);
        tv_two_hundred_money.setOnClickListener(this);
        tv_three_hundred_money.setOnClickListener(this);
        tv_five_hundred_money.setOnClickListener(this);
        tv_sure_bottom_pour.setOnClickListener(this);
        v_background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ll_bottom_pour.setVisibility(View.GONE);
                v_background.setVisibility(View.GONE);
                return false;
            }
        });
    }

    /**
     * 消息列表添加模拟数据
     */
    private void addData() {
        MsgBean msgBean1 = new MsgBean(false, "测试数据试试看怎么样？", MsgBean.TYPE_RECEIVED, R.drawable.renma);
        data.add(msgBean1);
        MsgBean msgBean2 = new MsgBean(true, "200元", MsgBean.TYPE_RECEIVED, R.drawable.renma);
        data.add(msgBean2);
        MsgBean msgBean3 = new MsgBean(false, "发表的数据试试看怎么样？", MsgBean.TYPE_SENT, R.drawable.xiaohei);
        data.add(msgBean3);
        MsgBean msgBean4 = new MsgBean(true, "500元", MsgBean.TYPE_SENT, R.drawable.xiaohei);
        data.add(msgBean4);
        MsgBean msgBean5 = new MsgBean(false, "测试数据试试看怎么样？", MsgBean.TYPE_RECEIVED, R.drawable.renma);
        data.add(msgBean5);
    }

    //投注记录添加模拟数据
    private void addRecordList() {
        for (int i = 1; i < 5; i++) {
            recordList.add("玩家" + i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bottom_pour: //下注
                ll_bottom_pour.setVisibility(View.VISIBLE);
                v_background.setVisibility(View.VISIBLE);
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
            case R.id.tv_hundred_money: //100元
                isClick1 = true;
                isClick2 = false;
                isClick3 = false;
                isClick5 = false;
                break;
            case R.id.tv_two_hundred_money: // 200元
                isClick1 = false;
                isClick2 = true;
                isClick3 = false;
                isClick5 = false;
                break;
            case R.id.tv_three_hundred_money: //300元
                isClick1 = false;
                isClick2 = false;
                isClick3 = true;
                isClick5 = false;
                break;
            case R.id.tv_five_hundred_money://500元
                isClick1 = false;
                isClick2 = false;
                isClick3 = false;
                isClick5 = true;
                break;
            case R.id.tv_sure_bottom_pour://确认下注
                MsgBean msgBean = new MsgBean(true, et_bottom_pour_money.getText().toString(),
                        MsgBean.TYPE_SENT, R.drawable.xiaohei);
                data.add(msgBean);
                adapter.notifyItemInserted(data.size() - 1);//当有新消息，刷新recyclerview显示
                rv_msg.scrollToPosition(data.size() - 1);//将recyclerview定位在最后一行
                v_background.setVisibility(View.GONE);
                ll_bottom_pour.setVisibility(View.GONE);
                isClick1 = false;
                isClick2 = false;
                isClick3 = false;
                isClick5 = false;
                checkedHowMoney();

                size = recordList.size() + 1;
                recordList.add("玩家" + size);
                recordAdapter.notifyItemInserted(recordList.size() - 1);
                break;
            case R.id.tv_bottom_pour_record://投注记录
                if (recordList.size() > 0) {
                    if (isShow) {
                        rv_record.setVisibility(View.GONE);
                        isShow = false;
                        setDrawableRight(R.mipmap.ic_down);
                    } else {
                        rv_record.setVisibility(View.VISIBLE);
                        isShow = true;
                        setDrawableRight(R.mipmap.ic_up);
                    }
                }
                break;
        }
        checkedHowMoney();
    }

    //改变textview右侧图标
    private void setDrawableRight(int resId) {
        Drawable drawable = ContextCompat.getDrawable(this, resId);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_bottom_pour_record.setCompoundDrawables(null, null, drawable, null);//右侧显示
    }

    /**
     * 判断选中的是哪个下注金额并显示在下方
     */
    private void checkedHowMoney() {
        if (isClick1) {
            tv_hundred_money.setBackgroundResource(R.drawable.pink_shape);
            tv_two_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_three_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_five_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            et_bottom_pour_money.setText(tv_hundred_money.getText());
        } else if (isClick2) {
            tv_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_two_hundred_money.setBackgroundResource(R.drawable.pink_shape);
            tv_three_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_five_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            et_bottom_pour_money.setText(tv_two_hundred_money.getText());
        } else if (isClick3) {
            tv_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_two_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_three_hundred_money.setBackgroundResource(R.drawable.pink_shape);
            tv_five_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            et_bottom_pour_money.setText(tv_three_hundred_money.getText());
        } else if (isClick5) {
            tv_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_two_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_three_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_five_hundred_money.setBackgroundResource(R.drawable.pink_shape);
            et_bottom_pour_money.setText(tv_five_hundred_money.getText());
        } else {
            tv_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_two_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_three_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            tv_five_hundred_money.setBackgroundResource(R.drawable.grey_shape);
            et_bottom_pour_money.setText("");
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) { //界面销毁 取消定时器
            timer.cancel();
            task.cancel();
        }
    }
}
