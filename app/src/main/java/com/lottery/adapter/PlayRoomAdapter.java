package com.lottery.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lottery.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class PlayRoomAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PlayRoomAdapter(List<String> data) {
        super(R.layout.item_rv_act_redpacket, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, String item) {
        viewHolder.setText(R.id.tv_room_name, item);
    }
}
