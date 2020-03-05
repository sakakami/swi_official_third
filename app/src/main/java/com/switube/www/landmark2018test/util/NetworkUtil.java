package com.switube.www.landmark2018test.util;

import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.switube.www.landmark2018test.MyApplication;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtil {
    private Map<String, String> map = new HashMap<>();
    private INetwork mINetwork;

    private NetworkUtil() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("OKHttp:", message);
                //LogToFile.e("OKHttp:", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.switube.com/mobile_swimap/")
                //.baseUrl("http://192.168.1.23/mobile_swimap/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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

    public Map<String, String> getMap() {
        map.clear();
        map.put("appname", "landmark2018test");
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            map.put("maid", "");
        } else {
            map.put("maid", SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"));
        }
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userWsid", "null").equals("null")) {
            map.put("wsid", "");
        } else {
            map.put("wsid", SharePreferencesUtil.getInstance().getSharedPreferences().getString("userWsid", "null"));
        }
        map.put("serialid", Settings.System.getString(MyApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID));
        map.put("pattern", Build.BOARD + "_" + Build.MODEL);
        switch (Locale.getDefault().getLanguage()) {
            case "zh":
                switch (Locale.getDefault().getCountry()) {
                    case "TW":
                        map.put("lang", "tw");
                        break;
                    case "CN":
                        map.put("lang", "ch");
                        break;
                    default:
                        map.put("lang", "en");
                        break;
                }
                break;
            case "jp":
                map.put("lang", "jp");
                break;
            default:
                map.put("lang", "en");
                break;
        }
        return map;
    }

    private static class NetworkUtilHolder {
        private static final NetworkUtil INSTANCE = new NetworkUtil();
    }
}
