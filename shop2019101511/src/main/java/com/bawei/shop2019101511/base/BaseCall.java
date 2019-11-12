package com.bawei.shop2019101511.base;

import android.widget.LinearLayout;

import com.bawei.shop2019101511.bean.LoginBean;

public interface BaseCall<T> {
    void onSuccess(T loginBean);
    void onFail(Object o);
}
