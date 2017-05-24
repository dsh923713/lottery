package com.lottery.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lottery.R;
import com.lottery.adapter.PlayRoomAdapter;
import com.lottery.base.BaseActivity;
import com.lottery.bean.RoomBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/16.
 */

public class PlayRoomActivity extends BaseActivity {
    private static final String TAG = "DSH -> PlayRoomActivity";
    @BindView(R.id.cb_redpacket_top)
    ConvenientBanner cb_redpacket_top; //头部轮播图
    @BindView(R.id.rlv_redpacket)
    RecyclerView rlv_redpacket; //房间数量的动态加载recyclerview

    private List<Integer> imageList;//头部轮播图片集合
    private List<String> data = new ArrayList<>(); //数据集合
    private String name; //标题名称
    private List<RoomBean> roomBeanList;//房间数据

    public PlayRoomActivity() {
        super(R.layout.activity_redpacketox);
    }


    @Override
    protected void getBundleExtras(Bundle extras) {
        if (extras != null) {
            name = extras.getString("id_name");
            if (extras.getSerializable("roomBean") == null) {
                return;
            }
            roomBeanList = (List<RoomBean>) extras.getSerializable("roomBean");
        }
    }

    @Override
    protected void initContentView(Bundle bundle) {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this); //绑定注解
        setTitle(name);//设置标题
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));//改变toolbar颜色
        setStatusBar(ContextCompat.getColor(this, R.color.colorAccent));//改变状态栏颜色
        setLeftIcon(R.mipmap.ic_back, "", new View.OnClickListener() { //添加返回按钮
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getImageList();
        cb_redpacket_top.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                                      @Override
                                      public LocalImageHolderView createHolder() {
                                          return new LocalImageHolderView();
                                      }
                                  },//设置两个点图片作为翻页指示器，
                imageList).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器显示的位置区域--居中
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

//        addData();
        PlayRoomAdapter adapter = new PlayRoomAdapter(roomBeanList);
        GridLayoutManager layout = new GridLayoutManager(this, 2);
        rlv_redpacket.setLayoutManager(layout);
        rlv_redpacket.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showShortToast("onItemClick" + name + position);
                startActivity(MsgChatActivity.class);
            }
        });
    }


    /**
     * 往集合添加数据，用于recyclerview
     */
    private void addData() {
        data.add("普通房");
        data.add("高级房");
        data.add("豪华房");
        data.add("总统房");
    }

    /**
     * 添加头部轮播图
     */
    private void getImageList() {
        imageList = new ArrayList<>();
        imageList.add(R.mipmap.im1);
        imageList.add(R.mipmap.im2);
        imageList.add(R.mipmap.im3);
        imageList.add(R.mipmap.im4);
    }

    /**
     * 加载轮播图片
     */
    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}
