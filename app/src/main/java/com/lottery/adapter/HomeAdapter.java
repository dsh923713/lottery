package com.lottery.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lottery.R;
import com.lottery.model.HomeModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeModel, BaseViewHolder> {
    public HomeAdapter(List<HomeModel> list) {
        super(R.layout.item_rlv_frag_home, list);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, HomeModel item) {
        viewHolder.setImageResource(R.id.im_rlv_head, item.getImageId())
                .setText(R.id.tv_rlv_title, item.getTitle());
    }
}
