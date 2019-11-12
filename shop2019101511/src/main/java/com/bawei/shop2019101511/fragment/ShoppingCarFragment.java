package com.bawei.shop2019101511.fragment;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.base.BaseFragment;
import com.bawei.shop2019101511.bean.ShopBean;
import com.bawei.shop2019101511.fragment.adaper.ExpandableListViewAdatper;
import com.bawei.shop2019101511.presenter.ShopCarPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ShoppingCarFragment extends BaseFragment implements BaseCall<List<ShopBean>> {
    private static final String TAG = "ShoppingCarFragment";
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    Unbinder unbinder;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.quna)
    CheckBox quna;
    Unbinder unbinder1;
    @BindView(R.id.but_sum)
    Button butSum;
    private ExpandableListViewAdatper expandableListViewAdatper;


    @Override
    protected int onLayout() {
        return R.layout.fragment_shopingcar;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    protected void onData() {
        ShopCarPresenter shopCarPresenter = new ShopCarPresenter(this);
        Intent intent = getActivity().getIntent();
        int userId = intent.getIntExtra("userId", 9526);
        String sessionId = intent.getStringExtra("sessionId");

        shopCarPresenter.CommRequestData(userId, sessionId);


    }



    @Override
    public void onSuccess(List<ShopBean> loginBean) {
        expandableListViewAdatper = new ExpandableListViewAdatper();
        expandableListView.setAdapter(expandableListViewAdatper);
        expandableListViewAdatper.addAll(loginBean);
        for (int i = 0; i < loginBean.size(); i++) {
            expandableListView.expandGroup(i);

        }

        expandableListViewAdatper.notifyDataSetChanged();
    }

    @Override
    public void onFail(Object o) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @OnClick({R.id.quna, R.id.but_sum})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quna:
                expandableListViewAdatper.childAllChange(quna.isChecked());
                expandableListViewAdatper.setOnClicked(new ExpandableListViewAdatper.onClicked() {
                    @Override
                    public void onClickChange(double price, boolean ac) {
                        quna.setChecked(ac);
                        tvSum.setText(String.valueOf(price));
                    }
                });
                break;
            case R.id.but_sum:

                break;
        }
    }
}
