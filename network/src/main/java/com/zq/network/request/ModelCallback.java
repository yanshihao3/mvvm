package com.zq.network.request;

import com.lzy.okgo.model.Progress;

/**
 * @program: mvvm
 * @description:
 * @author: 闫世豪
 * @create: 2021-03-05 13:37
 **/
public abstract class ModelCallback<T> implements Callback<T> {
    @Override
    public void onStart() {

    }

    @Override
    public void onError(Exception exception) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }
}
