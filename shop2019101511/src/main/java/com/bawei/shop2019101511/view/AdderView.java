package com.bawei.shop2019101511.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.shop2019101511.R;

public class AdderView extends LinearLayout {

    private TextView tv_number;

    public AdderView(Context context) {
        super(context);
        initView(context);
    }

    public AdderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttr(context,attrs);
    }

    public AdderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttr(context,attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AdderView);
        String string = typedArray.getString(R.styleable.AdderView_text);
        tv_number.setText(string);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.adder_subtractor, this, true);
        TextView but_minus = view.findViewById(R.id.but_minus);
        tv_number = view.findViewById(R.id.tv_number);
        TextView but_add = view.findViewById(R.id.but_add);
        but_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = tv_number.getText().toString();
                if (!TextUtils.isEmpty(number)) {
                    int num = Integer.parseInt(number);
                    num++;
                    tv_number.setText(num+"");
                    if (onClicked!=null) {
                        onClicked.onClicked(AdderView.this,num);
                    }
                }

            }
        });
        but_minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = tv_number.getText().toString();
                if (!TextUtils.isEmpty(number)) {
                    int num = Integer.parseInt(number);
                    if (num>1) {
                        num--;
                        tv_number.setText(num+"");
                        if (onClicked!=null) {
                            onClicked.onClicked(AdderView.this,num);
                        }
                    }else {
                        Toast.makeText(context, "不能再减了", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void setCont(int cont){
        tv_number.setText(String.valueOf(cont));
    }
    onClicked onClicked;

    public void setOnClicked(AdderView.onClicked onClicked) {
        this.onClicked = onClicked;
    }

    public interface onClicked{
        void onClicked(AdderView adderView,int now);
    }
}
