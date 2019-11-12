package com.bawei.shop2019101511.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.base.BaseFragment;
import com.bawei.shop2019101511.bean.BannerResultBean;
import com.bawei.shop2019101511.bean.BannerShow;
import com.bawei.shop2019101511.bean.CasualBean;
import com.bawei.shop2019101511.bean.CommodityList;
import com.bawei.shop2019101511.bean.LoginBean;
import com.bawei.shop2019101511.fragment.adaper.RecyclerViewadaper;
import com.bawei.shop2019101511.presenter.BanderPresenter;
import com.bawei.shop2019101511.presenter.CommPresenter;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.banner)
    XBanner banner;
    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.tv_style)
    TextView tvStyle;
    @BindView(R.id.tv_life)
    TextView tvLife;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;

    private BanderPresenter banderPresenter;
    private List<BannerResultBean> result;
    private RecyclerViewadaper recyclerViewadaper;

    @Override
    protected int onLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void onData() {
        banderPresenter = new BanderPresenter(new HomeBanner());
        banderPresenter.RequestData();
        CommPresenter commPresenter = new CommPresenter(new HomeRecyclerView());
        commPresenter.CommRequestData();
        CommPresenter comm1Presenter = new CommPresenter(new Home1RecyclerView());
        comm1Presenter.CommRequestData();
        CommPresenter comm2Presenter = new CommPresenter(new Home2RecyclerView());
        comm2Presenter.CommRequestData();
        CommPresenter comm3Presenter = new CommPresenter(new Home2RecyclerView());
        comm3Presenter.CommRequestData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//banner的BaseCall
    class HomeBanner implements BaseCall<BannerShow> {
        @Override
        public void onSuccess(final BannerShow bannerShow) {
            result = bannerShow.getResult();
            banner.setData(result, null);

            banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(getActivity()).load(result.get(position).getImageUrl()).into((ImageView) view);
                    banner.setmAutoPalyTime(3000);
                    banner.startAutoPlay();
                }
            });
        }

        @Override
        public void onFail(Object o) {

        }


    }
//热销新品
    class HomeRecyclerView implements BaseCall<CasualBean> {
        @Override
        public void onSuccess(CasualBean loginBean) {
            List<CommodityList> commodityList = loginBean.getRxxp().getCommodityList();
            recyclerViewadaper = new RecyclerViewadaper();
            recyclerViewadaper.addAll(commodityList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv.setLayoutManager(linearLayoutManager);
            rv.setAdapter(recyclerViewadaper);
        }

        @Override
        public void onFail(Object o) {

        }
    }
//    魔丽时尚
    class Home1RecyclerView implements BaseCall<CasualBean> {
        @Override
        public void onSuccess(CasualBean loginBean) {
            List<CommodityList> commodityList = loginBean.getMlss().getCommodityList();
            recyclerViewadaper = new RecyclerViewadaper();
            recyclerViewadaper.addAll(commodityList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rv1.setLayoutManager(linearLayoutManager);
            rv1.setAdapter(recyclerViewadaper);
        }

        @Override
        public void onFail(Object o) {

        }
    }
//    品质生活
    class Home2RecyclerView implements BaseCall<CasualBean> {
        @Override
        public void onSuccess(CasualBean loginBean) {
            List<CommodityList> commodityList = loginBean.getPzsh().getCommodityList();
            recyclerViewadaper = new RecyclerViewadaper();
            recyclerViewadaper.addAll(commodityList);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            rv2.setLayoutManager(gridLayoutManager);
            rv2.setAdapter(recyclerViewadaper);
        }

        @Override
        public void onFail(Object o) {

        }
    }
    class HomeText implements BaseCall<CasualBean> {
        @Override
        public void onSuccess(CasualBean loginBean) {
            tvNew.setText(loginBean.getRxxp().getName());
            tvStyle.setText(loginBean.getMlss().getName());
            tvLife.setText(loginBean.getPzsh().getName());
        }

        @Override
        public void onFail(Object o) {

        }
    }


}
