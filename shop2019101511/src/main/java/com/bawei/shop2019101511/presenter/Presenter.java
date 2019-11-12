package com.bawei.shop2019101511.presenter;
import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.base.BasePresenter;
import com.bawei.shop2019101511.utils.IRequest;

import io.reactivex.Observable;

public class Presenter extends BasePresenter {
    public Presenter(BaseCall baseCall) {
        super(baseCall);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.login((String)args[0],(String)args[1]);
    }
}
