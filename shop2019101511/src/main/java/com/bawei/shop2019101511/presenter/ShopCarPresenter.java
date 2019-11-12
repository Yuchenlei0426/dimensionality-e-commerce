package com.bawei.shop2019101511.presenter;

import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.bean.CasualBean;
import com.bawei.shop2019101511.bean.LoginBean;
import com.bawei.shop2019101511.bean.ShopBean;
import com.bawei.shop2019101511.utils.IRequest;
import com.bawei.shop2019101511.utils.NetWork;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShopCarPresenter {
    private static final String TAG = "CommPresenter";
    private BaseCall baseCall;

    public ShopCarPresenter(BaseCall baseCall) {
        this.baseCall = baseCall;
    }
    public void CommRequestData(Object...args) {
        IRequest iRequest = NetWork.getInstance().create(IRequest.class);
        iRequest.findShoppingCart((Integer)args[0],(String)args[1]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean<List<ShopBean>>>() {
                    @Override
                    public void accept(LoginBean<List<ShopBean>> casualBeanLoginBean) throws Exception {
                        String status = casualBeanLoginBean.getStatus();
                        if (status.equals("0000")) {
                            baseCall.onSuccess(casualBeanLoginBean.getResult());
                        }else {
                            baseCall.onFail(casualBeanLoginBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        baseCall.onFail(new LoginBean<>(throwable.getMessage()));
                    }
                });
    }
}
