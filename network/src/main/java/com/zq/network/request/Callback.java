package com.zq.network.request;

import com.lzy.okgo.model.Progress;

/**
 * @program: mvvm
 * @description:
 * @author: 闫世豪
 * @create: 2021-03-05 13:13
 **/
public interface Callback<T> {
    /**
     * 开始请求
     */
    void onStart();

    /**
     * 对返回数据进行操作的回调， UI线程
     */
    void onSuccess(T t);

    /**
     * 请求失败，响应错误，数据解析错误等，都会回调该方法， UI线程
     */
    void onError(Exception exception);

    void onFinish();

    void uploadProgress(Progress progress);

    void downloadProgress(Progress progress);

}
