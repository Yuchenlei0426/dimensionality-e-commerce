package com.bawei.shop2019101511.fragment.adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.bean.CommodityList;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewadaper extends RecyclerView.Adapter<RecyclerViewadaper.ViewHolderOne> {
    List<CommodityList> commodityList ;

    public void addAll(List<CommodityList> commodityLists){
        if (commodityLists != null) {
            this.commodityList=commodityLists;
        }
    }


    @NonNull
    @Override
    public ViewHolderOne onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, null, false);
        return new ViewHolderOne(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderOne viewHolderOne, int i) {
        Glide.with(viewHolderOne.itemView.getContext()).load(commodityList.get(i).getMasterPic()).into(viewHolderOne.im_masterPic);
        viewHolderOne.tv_commodityName.setText(commodityList.get(i).getCommodityName());

        viewHolderOne.tv_price.setText("￥"+commodityList.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    class ViewHolderOne extends RecyclerView.ViewHolder{
        ImageView im_masterPic;
        TextView tv_commodityName,tv_price;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            im_masterPic = itemView.findViewById(R.id.im_masterPic);
            tv_commodityName = itemView.findViewById(R.id.tv_commodityName);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
