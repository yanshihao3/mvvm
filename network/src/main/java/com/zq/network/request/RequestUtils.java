package com.zq.network.request;


import android.graphics.Bitmap;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.zq.network.callback.JsonCallback;
import com.zq.network.errorhandler.ExceptionHandle;
import com.zq.network.model.LzyResponse;

import java.io.File;


/**
 *
 */
public class RequestUtils {

    /**
     * get请求
     *
     * @param url
     * @param params
     * @param headers
     * @param callback
     * @param <T>
     */
    public static <T> void doGet(String url, HttpParams params, HttpHeaders headers, Object tag, Callback<T> callback) {
        RequestUtils.<T>requestJson(HttpMethod.GET, url, params, headers, tag, callback);
    }


    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @param callback
     * @param <T>
     */
    public static <T> void doPost(String url, HttpParams params, HttpHeaders headers, Object tag, Callback<T> callback) {
        RequestUtils.<T>requestJson(HttpMethod.POST, url, params, headers, tag, callback);
    }


    private static <T> void requestJson(HttpMethod method, String url, HttpParams params, HttpHeaders headers, Object tag, Callback<T> callback) {
        Request<LzyResponse<T>, ? extends Request> request;
        if (method == HttpMethod.GET) request = OkGo.<LzyResponse<T>>get(url);
        else if (method == HttpMethod.POST) request = OkGo.<LzyResponse<T>>post(url);
        else if (method == HttpMethod.PUT) request = OkGo.<LzyResponse<T>>put(url);
        else if (method == HttpMethod.DELETE) request = OkGo.<LzyResponse<T>>delete(url);
        else if (method == HttpMethod.HEAD) request = OkGo.<LzyResponse<T>>head(url);
        else if (method == HttpMethod.PATCH) request = OkGo.<LzyResponse<T>>patch(url);
        else if (method == HttpMethod.OPTIONS) request = OkGo.<LzyResponse<T>>options(url);
        else if (method == HttpMethod.TRACE) request = OkGo.<LzyResponse<T>>trace(url);
        else request = OkGo.<LzyResponse<T>>get(url);

        if (headers != null) {
            request.headers(headers);
        }
        if (params != null) {
            request.params(params);
        }
        if (tag != null) {
            request.tag(tag);
        }
        request.execute(new JsonCallback<LzyResponse<T>>() {
            @Override
            public void onStart(Request<LzyResponse<T>, ? extends Request> request) {
                super.onStart(request);
                if (callback != null) {
                    callback.onStart();
                }
            }

            @Override
            public void onSuccess(Response<LzyResponse<T>> response) {
                if (callback != null) {
                    callback.onSuccess(response.body().data);
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                if (callback != null) {
                    callback.onError(ExceptionHandle.handleException(response.getException()));
                }

            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (callback != null) {
                    callback.onFinish();
                }
            }
        });

    }

    public static void cancel(Object tag) {
        OkGo.getInstance().cancelTag(tag);
        tag = null;
    }

    /**
     * 请求图片
     *
     * @param url
     * @param params
     * @param tag
     * @param callback
     */
    private static void requestBitmap(String url, HttpParams params, Object tag, Callback<Bitmap> callback) {
        OkGo.<Bitmap>get(url)
                .params(params)
                .tag(tag)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        if (callback != null) {
                            callback.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<Bitmap> response) {
                        super.onError(response);
                        if (callback != null) {
                            callback.onError(ExceptionHandle.handleException(response.getException()));
                        }
                    }
                });
    }

    /**
     * 文件下载功能
     */
    private static void downLoad() {
        OkGo.<File>get("url")
                .tag("tag")
                .execute(new FileCallback() {
                    @Override
                    public void onSuccess(Response<File> response) {

                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);

                    }
                });
    }


}
