package com.gomo.learndemo.http;

import com.gomo.learndemo.bean.MessageBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class HttpApiManager implements IMessageHttp{

    private IMessageHttp mHttpService;

    private HttpApiManager() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // HttpLoggingInterceptor.Level.NONE
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mHttpService = retrofit.create(IMessageHttp.class);
    }

    public static HttpApiManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final HttpApiManager INSTANCE = new HttpApiManager();
    }

    @Override
    public Observable<MessageBean> getProjectList(@Path("page") String page, @Query("cid") int cid) {
        return mHttpService.getProjectList(page, cid);
    }

    @Override
    public Observable<MessageBean> login() {
        return mHttpService.login();
    }

    @Override
    public Observable<MessageBean> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword) {
        return mHttpService.register(username, password, repassword);
    }

    @Override
    public Observable<MessageBean> addTool(@Url String url, @Field("timestrap") String timestrap) {
        return mHttpService.addTool(url, timestrap);
    }
}
