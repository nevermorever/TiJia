package com.zyjd.tijia.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class RequestInterceptor implements Interceptor {
    private String token;
    public RequestInterceptor(String token){
        this.token = token;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Authorization","token "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IjMtMTE3NSIsInVzZXJfaWQiOjEsImVtYWlsIjoiNzk4OTE0NjgyQHFxLmNvbSIsImV4cCI6MTUxODY4MDA2NH0.4WVqy5nlhzQ5Rz2rYLkgsiW5Ab0KbX_emiZXvoSrySY")
                .header("Accept", "application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
