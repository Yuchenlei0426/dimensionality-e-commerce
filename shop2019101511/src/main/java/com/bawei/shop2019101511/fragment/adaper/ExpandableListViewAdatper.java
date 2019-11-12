package com.bawei.shop2019101511.fragment.adaper;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.shop2019101511.R;
import com.bawei.shop2019101511.bean.ShopBean;
import com.bawei.shop2019101511.bean.ShoppingCartListBean;
import com.bawei.shop2019101511.view.AdderView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewAdatper extends BaseExpandableListAdapter implements View.OnClickListener {
    List<ShopBean> loginBean=new ArrayList<>();

    public void addAll(List<ShopBean> loginBean){
        this.loginBean=loginBean;
    }

    @Override
    public int getGroupCount() {
        return loginBean.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return loginBean.get(groupPosition).getShoppingCartList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return loginBean.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return loginBean.get(groupPosition).getShoppingCartList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return loginBean.size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return loginBean.size();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
//    父
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup;
        if (convertView == null) {
            viewHolderGroup = new ViewHolderGroup();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_view, null, false);
            viewHolderGroup.box = convertView.findViewById(R.id.box);
            convertView.setTag(viewHolderGroup);
        }else {
            viewHolderGroup = (ViewHolderGroup) convertView.getTag();
        }
        viewHolderGroup.box.setText(loginBean.get(groupPosition).getCategoryName());
        viewHolderGroup.box.setChecked(loginBean.get(groupPosition).checked);
        viewHolderGroup.box.setTag(groupPosition);
        ShopBean shopBean = loginBean.get(groupPosition);
//        List<ShoppingCartListBean> shoppingCartList = shopBean.getShoppingCartList();
        viewHolderGroup.box.setTag(groupPosition);
//        父CheckBox点击事件
        viewHolderGroup.box.setOnClickListener(this);
        return convertView;
    }
//子
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild viewHolderChild;
        if (convertView == null) {
            viewHolderChild = new ViewHolderChild();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopcar_child, null, false);
            viewHolderChild.child_box = convertView.findViewById(R.id.child_box);
            viewHolderChild.im_pic = convertView.findViewById(R.id.im_pic);
            viewHolderChild.tv_commodityName = convertView.findViewById(R.id.tv_commodityName);
            viewHolderChild.tv_price = convertView.findViewById(R.id.tv_price);
            viewHolderChild.adder_view= convertView.findViewById(R.id.adder_view);
            convertView.setTag(viewHolderChild);
        }else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }


        ShoppingCartListBean shoppingCartListBean = loginBean.get(groupPosition).getShoppingCartList().get(childPosition);
        Glide.with(convertView.getContext()).load(shoppingCartListBean.getPic()).into(viewHolderChild.im_pic);
        viewHolderChild.tv_commodityName.setText(shoppingCartListBean.getCommodityName());
        viewHolderChild.child_box.setChecked(shoppingCartListBean.check);
        viewHolderChild.tv_price.setText(shoppingCartListBean.getPrice()+"");
        viewHolderChild.adder_view.setCont(shoppingCartListBean.getCount());
        viewHolderChild.child_box.setTag(shoppingCartListBean);
//        viewHolderChild.adder_view.setTag(shoppingCartListBean);
        viewHolderChild.adder_view.setTag(shoppingCartListBean);
//        子CheckBox点击事件
        viewHolderChild.child_box.setOnClickListener(this);
        viewHolderChild.adder_view.setOnClicked(new AdderView.onClicked() {
            @Override
            public void onClicked(AdderView adderView, int now) {
                ShoppingCartListBean goodsBean = (ShoppingCartListBean) adderView.getTag();
                int count = goodsBean.getCount();
                count=now;
                contPrice();
            }
        });

        return convertView;
    }



    //4·实现点击事件方法
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 父CheckBox
            case R.id.box:
                int  groupPosition = (int) v.getTag();
                ShopBean shopBean = loginBean.get(groupPosition);
                CheckBox checkBox1 = (CheckBox) v;
                shopBean.checked= checkBox1.isChecked();
                for (int i = 0; i < shopBean.getShoppingCartList().size(); i++) {
                    ShoppingCartListBean shoppingCartListBean = shopBean.getShoppingCartList().get(i);
                    shoppingCartListBean.check = checkBox1.isChecked();
                }
                notifyDataSetChanged();
                contPrice();
                break;
            //子CheckBox
            case R.id.child_box:
                ShoppingCartListBean shoppingCartListBean = (ShoppingCartListBean) v.getTag();
                CheckBox checkBox = (CheckBox) v;
                shoppingCartListBean.check=checkBox.isChecked();
                goodsToShop();
                contPrice();
                break;
        }
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private class ViewHolderGroup {
        CheckBox box;

    }
    //   3· 全选 括号里定义一个布尔类型的变量
    public void childAllChange(boolean isChecked){
        for (int i = 0; i < loginBean.size(); i++) {
            ShopBean shopBean = loginBean.get(i);
            shopBean.checked= isChecked;
            List<ShoppingCartListBean> shoppingCartList = shopBean.getShoppingCartList();
            for (int j = 0; j < shoppingCartList.size(); j++) {
                shoppingCartList.get(j).check=isChecked;
            }
        }
        notifyDataSetChanged();
        contPrice();

    }

    //2·商铺&&商品
    public  void goodsToShop(){
        for (int i = 0; i < loginBean.size(); i++) {
            ShopBean shop = loginBean.get(i);
            boolean isCheck=true;
            for (int j = 0; j < shop.getShoppingCartList().size(); j++) {
                ShoppingCartListBean shoppingCartListBean = shop.getShoppingCartList().get(j);
                isCheck=isCheck&&shoppingCartListBean.check;
            }
            shop.checked=isCheck;
        }
        notifyDataSetChanged();
    }
    //1·计算价格
    public void contPrice() {
        double sum =0.0;
        boolean ac =true;
        for (int i = 0; i < loginBean.size(); i++) {
            ShopBean shopBean = loginBean.get(i);
            ac=ac&&shopBean.checked;
            for (int j = 0; j < shopBean.getShoppingCartList().size(); j++) {
                ShoppingCartListBean shoppingCartListBean = shopBean.getShoppingCartList().get(j);
                if (shoppingCartListBean.check) {
                    double price = shoppingCartListBean.getPrice();
                    int count = shoppingCartListBean.getCount();
                    sum+=price*count;
                }
            }
        }

        if (onClicked != null) {
            onClicked.onClickChange(sum,ac);
        }

    }
    onClicked onClicked;

    public void setOnClicked(ExpandableListViewAdatper.onClicked onClicked) {
        this.onClicked = onClicked;
    }
    public interface onClicked{
        void onClickChange(double price,boolean ac);
    }

    private class ViewHolderChild {
        CheckBox child_box;
        ImageView im_pic;
        TextView tv_commodityName,tv_price;
        AdderView  adder_view;

    }
}
