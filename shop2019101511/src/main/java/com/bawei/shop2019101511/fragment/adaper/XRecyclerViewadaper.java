package com.bawei.shop2019101511.fragment.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.bean.OrderDetailList;
import com.bawei.shop2019101511.bean.OrderResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class XRecyclerViewadaper extends RecyclerView.Adapter<XRecyclerViewadaper.OrderView>{
    private ArrayList<OrderResult> order;
    public  void addAll(ArrayList<OrderResult> order){
        this.order=order;
    }

    @NonNull
    @Override
    public OrderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order, null, false);
        return new OrderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderView orderView, int i) {

        Date date = new Date(order.get(i).getOrderTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        orderView.tv_data.setText(dateFormat.format(date));

        String orderId = order.get(i).getOrderId();
        orderView.tv_order_number.setText(orderId);
        ArrayList<OrderDetailList> detailList = order.get(i).getDetailList();
        orderView.recyclerViewItemAdaper.addAll(detailList);
        orderView.recycler_order.setAdapter(orderView.recyclerViewItemAdaper);
//        orderView.recyclerViewItemAdaper.clear();
//        orderView.recyclerViewItemAdaper.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return order.size();
    }
    class  OrderView extends RecyclerView.ViewHolder{
        TextView tv_data,tv_order_number;
        RecyclerView recycler_order;
        RecyclerViewItemAdaper recyclerViewItemAdaper;
        public OrderView(@NonNull View itemView) {
            super(itemView);
            tv_data= itemView.findViewById(R.id.tv_data);
            tv_order_number = itemView.findViewById(R.id.tv_order_number);
            recycler_order = itemView.findViewById(R.id.recycler_order);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycler_order.setLayoutManager(linearLayoutManager);
            recyclerViewItemAdaper = new RecyclerViewItemAdaper();
        }
    }
}
