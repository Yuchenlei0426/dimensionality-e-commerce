package com.bawei.shop2019101511.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.base.BaseFragment;
import com.bawei.shop2019101511.bean.OrderResult;
import com.bawei.shop2019101511.fragment.adaper.XRecyclerViewadaper;
import com.bawei.shop2019101511.presenter.OrderPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class Order1Fragment extends BaseFragment implements BaseCall<ArrayList<OrderResult>> {

    @BindView(R.id.xv_order)
    XRecyclerView xvOrder;
    Unbinder unbinder;

    private int status;
    @SuppressLint("ValidFragment")
    public Order1Fragment(int status) {
        this.status = status;
    }
    private XRecyclerViewadaper xRecyclerViewadaper;

    @Override
    protected int onLayout() {
        return R.layout.fragment_order1;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }



    @Override
    protected void onData() {

        Intent intent = getActivity().getIntent();
        int userId = intent.getIntExtra("userId", 9526);
        String sessionId = intent.getStringExtra("sessionId");
        OrderPresenter orderPresenter = new OrderPresenter(this);
        orderPresenter.CommRequestData(userId,sessionId,status);
        xRecyclerViewadaper = new XRecyclerViewadaper();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xvOrder.setLayoutManager(linearLayoutManager);

    }
    @Override
    public void onSuccess(ArrayList<OrderResult> loginBean) {
        xRecyclerViewadaper.addAll(loginBean);
        xvOrder.setAdapter(xRecyclerViewadaper);

    }

    @Override
    public void onFail(Object o) {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
