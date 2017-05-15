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


//    private List<HomeModel> mHomeModelList;
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView im_home;
//        TextView tv_title;
//
//        public ViewHolder(View view) {
//            super(view);
//            im_home = (ImageView) view.findViewById(R.id.im_rlv_head);
//            tv_title = (TextView) view.findViewById(R.id.tv_rlv_title);
//        }
//    }
//
//    public HomeAdapter(List<HomeModel> homeModelList) {
//        this.mHomeModelList = homeModelList;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rlv_frag_home, parent, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        HomeModel homeModel = mHomeModelList.get(position);
//        holder.im_home.setImageResource(homeModel.getImageId());
//        holder.tv_title.setText(homeModel.getTitle());
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return mHomeModelList.size();
//    }


}
