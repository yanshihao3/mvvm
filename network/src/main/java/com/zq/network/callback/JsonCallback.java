package com.zq.network.callback;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConverter<T> convert = new JsonConverter<>(clazz);
                return convert.convertResponse(response);
            }
        }

        JsonConverter<T> convert = new JsonConverter<>(type);
        return convert.convertResponse(response);
    }
}
