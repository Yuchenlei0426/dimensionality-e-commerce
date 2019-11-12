package com.bawei.shop2019101511.utils;

import com.bawei.shop2019101511.bean.BannerResultBean;
import com.bawei.shop2019101511.bean.BannerShow;
import com.bawei.shop2019101511.bean.CasualBean;
import com.bawei.shop2019101511.bean.CoBean;
import com.bawei.shop2019101511.bean.CommodityList;
import com.bawei.shop2019101511.bean.LoginBean;
import com.bawei.shop2019101511.bean.OrderBean;
import com.bawei.shop2019101511.bean.OrderResult;
import com.bawei.shop2019101511.bean.ResultBean;
import com.bawei.shop2019101511.bean.ShopBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequest {

    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean<ResultBean>>login(@Field("phone")String phone,@Field("pwd")String pwd);


    @GET("commodity/v1/bannerShow")
    Observable<BannerShow> bannerShow();

    @GET("commodity/v1/commodityList")
    Observable<LoginBean<CasualBean>> commodityList();

    @GET("order/verify/v1/findShoppingCart")
    Observable<LoginBean<List<ShopBean>>> findShoppingCart(@Header("userId") Integer userId, @Header("sessionId") String sessionId);
 @GET("order/verify/v1/findOrderListByStatus")
    Observable<OrderBean> findOrderListByStatus(@Header("userId") Integer userId, @Header("sessionId") String sessionId,
                                                                        @Query("status")Integer status,
                                                                        @Query("page") Integer page,
                                                                        @Query("count") Integer count);

}
