package com.lottery.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.lottery.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.rlv_home)
    RecyclerView rlv_home;
    @BindView(R.id.cb_home_top)
    ConvenientBanner cb_home_top;

    private List<Integer> imageList;
    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        getImageList();
        cb_home_top.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },imageList);
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    public void getImageList(){
        imageList = new ArrayList<>();
        imageList.add(R.mipmap.im1);
        imageList.add(R.mipmap.im2);
        imageList.add(R.mipmap.im3);
        imageList.add(R.mipmap.im4);
    }

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
