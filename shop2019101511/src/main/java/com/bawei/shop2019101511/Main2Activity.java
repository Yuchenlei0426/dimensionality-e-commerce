package com.bawei.shop2019101511;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.shop2019101511.fragment.NewsFragment;
import com.bawei.shop2019101511.fragment.HomeFragment;
import com.bawei.shop2019101511.fragment.MyFragment;
import com.bawei.shop2019101511.fragment.OrderFragment;
import com.bawei.shop2019101511.fragment.ShoppingCarFragment;
import com.bawei.shop2019101511.fragment.adaper.FragmentAdper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_form)
    RadioButton rbForm;
    @BindView(R.id.rb_shopCar)
    RadioButton rbShopCar;
    @BindView(R.id.rb_news)
    RadioButton rbNews;
    @BindView(R.id.rb_my)
    RadioButton rbMy;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment());
        fragments.add(new ShoppingCarFragment());
        fragments.add(new OrderFragment());
        fragments.add(new MyFragment());
        FragmentAdper fragmentAdper = new FragmentAdper(getSupportFragmentManager(),fragments);
        vp.setAdapter(fragmentAdper);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                 radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        vp.setCurrentItem(0);
                        ;break;
                    case R.id.rb_form:
                        vp.setCurrentItem(1);
                        ;break;
                    case R.id.rb_shopCar:
                        vp.setCurrentItem(2);
                        ;break;
                    case R.id.rb_news:
                        vp.setCurrentItem(3);
                        ;break;
                    case R.id.rb_my:
                        vp.setCurrentItem(4);
                        ;break;
                }
            }
        });
    }

}
