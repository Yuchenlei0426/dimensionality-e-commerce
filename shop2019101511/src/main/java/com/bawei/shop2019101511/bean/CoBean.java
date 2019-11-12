package com.bawei.shop2019101511.bean;

import org.greenrobot.greendao.annotation.Id;

import java.util.List;

public class CoBean {
    /*id": 1002,
            "name": "热销新品"*/
    private List<CommodityList> commodityList;
    private int id;
    private String name;

    public List<CommodityList> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityList> commodityList) {
        this.commodityList = commodityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
