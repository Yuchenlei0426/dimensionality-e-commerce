package com.bawei.shop2019101511.bean;

import java.util.List;

public class BannerShow {



    private String message;
    private String status;
    private List<BannerResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BannerResultBean> getResult() {
        return result;
    }

    public void setResult(List<BannerResultBean> result) {
        this.result = result;
    }
}
