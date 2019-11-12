package com.bawei.shop2019101511.presenter;
import android.util.Log;

import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.base.BasePresenter;
import com.bawei.shop2019101511.bean.BannerShow;
import com.bawei.shop2019101511.bean.LoginBean;
import com.bawei.shop2019101511.utils.IRequest;
import com.bawei.shop2019101511.utils.NetWork;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BanderPresenter   {
    public BaseCall baseCall;
    private static final String TAG = "BanderPresenter";

    public BanderPresenter(BaseCall baseCall) {
        this.baseCall = baseCall;
    }

    public void RequestData(Object...args){
        IRequest iRequest = NetWork.getInstance().create(IRequest.class);
        iRequest.bannerShow().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerShow>() {
                    @Override
                    public void accept(BannerShow loginBean) throws Exception {
                        String status = loginBean.getStatus();
                        Log.i(TAG, "accept: "+loginBean.getMessage());

                        if (status.equals("0000")) {
                            baseCall.onSuccess(loginBean);
                        } else {
                            baseCall.onFail(loginBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        LoginBean LoginBean = new LoginBean(throwable.getMessage());
                        baseCall.onFail(LoginBean);
                    }
                });
    }

}
