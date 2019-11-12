package com.bawei.shop2019101511.bean;

public class CommodityList {
    /*"commodityId": 23,
                    "commodityName": "小白鞋 女款 时尚百搭休闲板鞋",
                    "masterPic": "http://172.17.8.100/images/small/commodity/nx/bx/6/1.jpg",
                    "price": 139,
                    "saleNum": 25*/
    private String commodityId;
    private String commodityName;
    private String masterPic;
    private String price;
    private int saleNum;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getMasterPic() {
        return masterPic;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
}
