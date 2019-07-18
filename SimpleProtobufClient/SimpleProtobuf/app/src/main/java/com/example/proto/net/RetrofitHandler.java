package com.example.proto.net;

import com.google.protobuf.ExtensionRegistry;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author: Junjian Jia
 * @Date: 2019/7/17 23:42
 * @Email: cnrivkaer@outlook.com
 * @Description:
 */
public class RetrofitHandler {

    private Service mService;
    private static String mServiceUrl;

    private static class Holder {
        private static final RetrofitHandler INSTANCE = new RetrofitHandler();
    }

    private RetrofitHandler() {
        init();
    }

    public static RetrofitHandler getInstance(String serviceUrl) {
        mServiceUrl = serviceUrl;
        return Holder.INSTANCE;
    }

    public Service getService() {
        return mService;
    }

    private void init() {
        assert mServiceUrl != null;
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mServiceUrl)
                .client(createClient())
                .addConverterFactory(ProtoConverterFactory.createWithRegistry(registry))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mService = retrofit.create(Service.class);
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.MINUTES))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

    }
}
