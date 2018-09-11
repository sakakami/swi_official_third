package com.switube.www.swiofficialthird.app;

import android.content.Context;

import com.switube.www.swiofficialthird.util.INetwork;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    @Provides
    @MyApplicationScope
    public INetwork providesINetwork(Retrofit retrofit) {
        return retrofit.create(INetwork.class);
    }

    @Provides
    @MyApplicationScope
    public Cache providesCache(Context context) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @MyApplicationScope
    public OkHttpClient providesOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder().cache(cache).build();
    }

    @Provides
    @MyApplicationScope
    public Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://www.switube.com/mobile_switube/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

    }
}
