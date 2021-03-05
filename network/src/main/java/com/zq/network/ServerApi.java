package com.zq.network;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.zq.network.interceptor.HttpLogInterceptor;
import com.zq.network.request.Callback;
import com.zq.network.request.RequestUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @program: mvvm
 * @description: 网络请求处理类
 * @author: 闫世豪
 * @create: 2021-03-05 10:27
 **/
public class ServerApi {

    private ServerApi() {

    }

    public static ServerApi getInstance() {
        return ApiBaseHolder.holder;
    }

    public static class ApiBaseHolder {
        private static ServerApi holder = new ServerApi();
    }

    public void init(Application context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(HttpLogInterceptor.INSTANCE.getInstance());//添加OkGo默认debug日志

        builder.readTimeout(5000, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(5000, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(5000, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(); //https 证书问题
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        okHttpClient = builder.build();
        HttpHeaders httpHeaders = new HttpHeaders();
        OkGo.getInstance().init(context)//必须调用初始化
                .setOkHttpClient(okHttpClient)
                .addCommonHeaders(httpHeaders)
                .setCacheMode(CacheMode.NO_CACHE)//全局统一缓存模式，默认不使用缓存，可以不传
                .setRetryCount(3); //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }


    private Object tag = this;

    private String url;

    private HttpParams params;

    private HttpMethod method;

    private HttpHeaders headers;

    private OkHttpClient okHttpClient;      //ok请求的客户端


    public ServerApi setHeaders(HttpHeaders headers) {
        this.headers = headers;
        return this;
    }

    public ServerApi setParams(HttpParams params) {
        this.params = params;
        return this;
    }

    public ServerApi setUrl(String url) {
        this.url = url;
        return this;
    }

    public ServerApi setTag(Object tag) {
        this.tag = tag;
        return this;
    }

    public ServerApi doPost() {
        method = HttpMethod.POST;
        return this;
    }

    public ServerApi doGet() {
        method = HttpMethod.GET;
        return this;
    }

    public void cancel(Object tag) {
        RequestUtils.cancel(tag);
    }

    public <T> void execute(Callback<T> callback) {
        if (method != null) {
            switch (method) {
                case GET:
                    RequestUtils.doGet(url, params, headers, tag, callback);
                    break;
                case POST:
                    RequestUtils.doPost(url, params, headers, tag, callback);
                    break;
            }
        }
    }

}
