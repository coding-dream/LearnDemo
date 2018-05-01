package com.gomo.learndemo.http;

import com.gomo.learndemo.bean.MessageBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

interface IMessageHttp {

    @GET("project/list/{page}/json")
    Observable<MessageBean> getProjectList(@Path("page") String page, @Query("cid") int cid);

    @GET("user/login")
    Observable<MessageBean> login();
    @FormUrlEncoded

    @POST("user/register")
    Observable<MessageBean> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    @FormUrlEncoded
    @POST
    Observable<MessageBean> addTool(@Url String url, @Field("timestrap") String timestrap);
}
