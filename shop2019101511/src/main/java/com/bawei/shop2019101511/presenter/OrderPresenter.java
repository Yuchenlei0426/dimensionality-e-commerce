package com.bawei.shop2019101511.presenter;

import android.util.Log;

import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.base.BasePresenter;
import com.bawei.shop2019101511.bean.CasualBean;
import com.bawei.shop2019101511.bean.LoginBean;
import com.bawei.shop2019101511.bean.OrderBean;
import com.bawei.shop2019101511.bean.OrderResult;
import com.bawei.shop2019101511.utils.IRequest;
import com.bawei.shop2019101511.utils.NetWork;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OrderPresenter {

    //iRequest.findOrderListByStatus((Integer)args[0],(String) args[1],(Integer)args[2],20,(Integer)args[3])
    private static final String TAG = "order";
    private BaseCall baseCall;

    public OrderPresenter(BaseCall baseCall) {
        this.baseCall = baseCall;
    }

    public void CommRequestData(Object... args) {

        IRequest iRequest = NetWork.getInstance().create(IRequest.class);
        iRequest.findOrderListByStatus((Integer) args[0], (String) args[1], (Integer) args[2], 1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderBean>() {

                    @Override
                    public void accept(OrderBean orderBean) throws Exception {
                        String status = orderBean.getStatus();
                        Log.i(TAG, "accept: "+orderBean);
                        if (status.equals("0000")) {
                            baseCall.onSuccess(orderBean.getOrderList());
                        } else {
                            baseCall.onSuccess(orderBean);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        OrderBean orderBean = new OrderBean(throwable.getMessage());
                        baseCall.onFail(orderBean);
                    }
                });
    }
}
