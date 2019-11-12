package com.bawei.shop2019101511.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.presenter.Presenter;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        initPresenter();
        onView();
        onData();
    }

    protected abstract int onLayout();
    protected abstract Presenter initPresenter();

    protected abstract void onView();

    protected abstract void onData();
   /* @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }*/
}
