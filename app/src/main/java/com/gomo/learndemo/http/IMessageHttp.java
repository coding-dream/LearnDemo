package com.gomo.learndemo.http;

import com.gomo.learndemo.bean.MessageBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface IMessageHttp {

    @GET("/api/xx?is_xx=true")
    Observable<MessageBean> getMessageList(@Query("sign") int sign,
                                                @Query("date") String date,
                                                @Query("client") String client);
}
