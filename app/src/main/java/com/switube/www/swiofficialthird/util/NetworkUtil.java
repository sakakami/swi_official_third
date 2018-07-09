package com.switube.www.swiofficialthird.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtil {
    private static final String BASE_URL = "https://www.switube.com/mobile_switube/";
    private static final int DEFAULT_TIMEOUT = 20;
    private Map<String, String> map = new HashMap<>();
    private final INetwork mINetwork;

    private NetworkUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
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
    public static NetworkUtil getInstance() {
        return NetworkUtilHolder.INSTANCE;
    }

    public INetwork getNetworkService() {
        return mINetwork;
    }

    public Map<String, String> getMap(final Context context) {
        map.clear();
        map.put("appname", "ebikekjlab");
        map.put("maid", SharePreferencesUtil.getInstance().getSharedPreferences(context).getString("userMaid", ""));
        map.put("wsid", SharePreferencesUtil.getInstance().getSharedPreferences(context).getString("userWsid", ""));
        //Log.e("androidID", Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        map.put("serialid", Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        map.put("pattern", Build.BOARD + "_" + Build.MODEL);
        return map;
    }

    private static class NetworkUtilHolder {
        private static final NetworkUtil INSTANCE = new NetworkUtil();
    }
}
