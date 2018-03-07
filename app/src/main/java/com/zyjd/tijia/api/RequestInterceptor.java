package com.zyjd.tijia.api;

import android.content.Context;

import com.zyjd.tijia.util.SPUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class RequestInterceptor implements Interceptor {
    private Context context;

    public RequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Authorization", "token " + SPUtil.getString(context, "token", null))
                .header("Accept", "application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
