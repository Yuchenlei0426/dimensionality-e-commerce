package com.bawei.shop2019101511.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.shop2019101511.R;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(onLayout(), null, false);
        onView(view);
        onData();

        return view;
    }

    protected abstract int onLayout();

    protected abstract void onView(View view);

    protected abstract void onData();

}
