package com.switube.www.swiofficialthird.util;

import android.util.Log;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkCUtil {
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/details/";
    private static final int DEFAULT_TIMEOUT = 20;
    private Map<String, String> map = new HashMap<>();
    private INetwork mINetwork;

    private NetworkCUtil() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("http", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mINetwork = retrofit.create(INetwork.class);
    }

    @Contract(pure = true)
    public static NetworkCUtil getInstance() {
        return NetworkCUtil.NetworkUtilHolder.INSTANCE;
    }

    public INetwork getNetworkService() {
        return mINetwork;
    }

    public Map<String, String> getMap(String id, String key, String language) {
        map.clear();
        map.put("placeid", id);
        map.put("key", key);
        map.put("language", language);
        return map;
    }

    private static class NetworkUtilHolder {
        private static final NetworkCUtil INSTANCE = new NetworkCUtil();
    }
}
