package com.bawei.shop2019101511;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.shop2019101511.base.BaseActivity;
import com.bawei.shop2019101511.base.BaseCall;
import com.bawei.shop2019101511.bean.LoginBean;
import com.bawei.shop2019101511.bean.ResultBean;
import com.bawei.shop2019101511.presenter.Presenter;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BaseCall<LoginBean<ResultBean>>{

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.but_login)
    Button butLogin;
    private Presenter presenter;



    @Override
    protected int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Presenter initPresenter() {
        return new Presenter(this);
    }


    @Override
    protected void onView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onData() {
        presenter = (Presenter) initPresenter();

    }

    @OnClick({R.id.tv, R.id.but_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:

                break;
            case R.id.but_login:
                String phone = etPhone.getText().toString();
                String pwd = etPwd.getText().toString();
                presenter.RequestData(phone,pwd);
                break;
        }
    }





    @Override
    public void onSuccess(LoginBean<ResultBean> loginBean) {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        ResultBean result = loginBean.getResult();
        int userId = result.getUserId();
        String sessionId = result.getSessionId();
        intent.putExtra("userId",userId);
        intent.putExtra("sessionId",sessionId);
        startActivity(intent);


    }

    @Override
    public void onFail(Object o) {

    }


}
