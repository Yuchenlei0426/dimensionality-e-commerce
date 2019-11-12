package com.bawei.shop2019101511.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.base.BaseFragment;
import com.bawei.shop2019101511.fragment.adaper.OrderAdaper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrderFragment extends BaseFragment {


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private Unbinder bind;
     ArrayList<Fragment> fragments =new ArrayList<>();
    @Override
    protected int onLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected void onView(View view) {
        bind = ButterKnife.bind(this, view);
    }

    @Override
    protected void onData() {
        fragments.add(new Order1Fragment(0));
        fragments.add(new Order1Fragment(1));
        fragments.add(new Order1Fragment(2));
        fragments.add(new Order1Fragment(3));
        fragments.add(new Order1Fragment(9));
        OrderAdaper orderAdaper = new OrderAdaper(getChildFragmentManager());
        orderAdaper.addAll(fragments);
        vp.setAdapter(orderAdaper);
        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setIcon(R.drawable.alllistnhdpi).setText("全部订单");
        tab.getTabAt(1).setIcon(R.drawable.paynhdpi).setText("待付款");
        tab.getTabAt(2).setIcon(R.drawable.receivenhdpi).setText("待收货");
        tab.getTabAt(3).setIcon(R.drawable.mmentnhdpi).setText("待评价");
        tab.getTabAt(4).setIcon(R.drawable.finishnhdpi).setText("已完成");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        fragments.clear();

    }
}
