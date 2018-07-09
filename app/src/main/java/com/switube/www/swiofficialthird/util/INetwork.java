package com.switube.www.swiofficialthird.util;

import com.switube.www.swiofficialthird.home.gson.MediumMenuEntity;
import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface INetwork {
    @POST("{url}")
    Call<String> getData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<MediumMenuEntity> getDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<AttractionDataEntity> getAttractionDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Call<String> getData(@Path("url") String url);
}
