package com.zyjd.tijia.api;


import com.google.gson.Gson;
import com.zyjd.tijia.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiService apiService;
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder();
    // 要传一个gson对象，不然可能会 java.lang.IllegalStateException: Fatal Exception thrown on Scheduler.
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create(new Gson());
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    public static ApiService getApiService() {
        if (apiService == null) {
            if (Constant.DEBUG) {
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }
            builder.addInterceptor(new ResponseInterceptor());
            builder.addInterceptor(new RequestInterceptor(""));
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(false);

            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(Constant.API_BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
