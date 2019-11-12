package com.bawei.shop2019101511.base;

import android.util.Log;

import com.bawei.shop2019101511.bean.BannerResultBean;
import com.bawei.shop2019101511.bean.LoginBean;
import com.bawei.shop2019101511.bean.ResultBean;
import com.bawei.shop2019101511.utils.IRequest;
import com.bawei.shop2019101511.utils.NetWork;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {
    private static final String TAG = "BasePresenter";
   public BaseCall baseCall;

    public BasePresenter(BaseCall baseCall) {
        this.baseCall = baseCall;
    }
    public void RequestData(Object...args){
        IRequest iRequest = NetWork.getInstance().create(IRequest.class);
        getModel(iRequest,args).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
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


    protected abstract Observable getModel(IRequest iRequest,Object...args);
}
