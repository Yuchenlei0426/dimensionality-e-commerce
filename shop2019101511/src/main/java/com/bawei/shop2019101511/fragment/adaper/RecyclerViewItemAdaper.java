package com.bawei.shop2019101511.fragment.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.bean.OrderDetailList;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

class RecyclerViewItemAdaper extends RecyclerView.Adapter<RecyclerViewItemAdaper.ViewHolderItem> {
    ArrayList<OrderDetailList> detailList;

    public void addAll(ArrayList<OrderDetailList> detailList) {
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item,null,false);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem viewHolderItem, int i) {
        String commodityPic = detailList.get(i).getCommodityPic();
        String[] split = commodityPic.split(",");
        Glide.with(viewHolderItem.itemView.getContext())
                .load(split[0])
                .into(viewHolderItem.commodityPic);
        viewHolderItem.commodityName.setText(detailList.get(i).getCommodityName());
        viewHolderItem.commodityPrice.setText(detailList.get(i).getCommodityPrice()+"");
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public void clear() {
        detailList.clear();
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageView commodityPic;
        TextView commodityName,commodityPrice;
        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);
            commodityPic = itemView.findViewById(R.id.commodityPic);
            commodityName= itemView.findViewById(R.id.commodityName);
            commodityPrice = itemView.findViewById(R.id.commodityPrice);
        }
    }
}
