package com.switube.www.swiofficialthird.util;

import com.switube.www.swiofficialthird.create.ResultEntity;
import com.switube.www.swiofficialthird.create.gson.AttractionDetailEntity;
import com.switube.www.swiofficialthird.home.gson.MediumMenuEntity;
import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;
import com.switube.www.swiofficialthird.map.gson.MenuDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceBaseDataEntity;
import com.switube.www.swiofficialthird.map.gson.PlaceDataEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface INetwork {
    @POST("{url}")
    Call<String> getData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<MediumMenuEntity> getDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<PlaceBaseDataEntity> getPlaceBaseDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<PlaceDataEntity> getPlaceDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<AttractionDetailEntity> getAttractionDetailWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<MenuDataEntity> getMenuDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @Multipart
    @POST("{url}")
    Observable<String> getValue(@Path("url") String url, @QueryMap Map<String, String> map, @PartMap Map<String, RequestBody> image);

    @POST("{url}")
    Observable<String> getValue(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Call<String> getData(@Path("url") String url);
}
