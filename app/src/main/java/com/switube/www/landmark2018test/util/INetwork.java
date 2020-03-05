package com.switube.www.landmark2018test.util;

import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.gson.GCashFlow;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.gson.GLikeUnlike;
import com.switube.www.landmark2018test.gson.GMyCollection;
import com.switube.www.landmark2018test.gson.GPlayer;
import com.switube.www.landmark2018test.gson.GPushMusic;
import com.switube.www.landmark2018test.gson.GPushNewVideo;
import com.switube.www.landmark2018test.gson.GPushStroke;
import com.switube.www.landmark2018test.gson.GReport;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.gson.GSlideMenuData;
import com.switube.www.landmark2018test.gson.GLeaveComments;
import com.switube.www.landmark2018test.gson.GCreateAttraction;
import com.switube.www.landmark2018test.gson.GSignInData;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GPlaceIdData;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.gson.GPersonalSteaming;
import com.switube.www.landmark2018test.gson.GTagData;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface INetwork {
    @POST()
    Observable<GSignInData> sendSignInData(@Url String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GAttractionListData> getPlaceBaseDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GSlideMenuData> getMenuDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST()
    Observable<GPlaceIdData> getPlaceDataWithRx(@Url String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GSearchAttractionDetail> getSearchPlaceDetailDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST()
    Observable<GAttractionDataGoogle> getAttractionDetailWithRx(@Url String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GInfoData> getPlaceDetailDataWithRx(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Call<String> getData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GLikeUnlike> sendLikeOrUnlike(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GCommentsData> sendMessage(@Path("url") String url, @QueryMap Map<String, String> map);

    @Multipart
    @POST("{url}")
    Observable<GLeaveComments> sendCheckInData(@Path("url") String url, @QueryMap Map<String, String> map, @PartMap Map<String, RequestBody> image);

    @POST("{url}")
    Observable<GLeaveComments> sendCheckInData(@Path("url") String url, @QueryMap Map<String, String> map);

    @Multipart
    @POST("{url}")
    Observable<GCreateAttraction> getValue(@Path("url") String url, @QueryMap Map<String, String> map, @PartMap Map<String, RequestBody> image);

    @POST("{url}")
    Observable<GCreateAttraction> getValue(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GTagData> getTagData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GPersonalSteaming> getPersonalData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GMusicRadio> getMusicRadioData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GPushMusic> getPushMusicData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GPlayer> getMusicData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST()
    Observable<GPushNewVideo> sendNewVideo(@Url String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GSendLove> sendLove(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST()
    Observable<GReport> sendReport(@Url String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GSaveList> getSaveList(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GStrokeList> getStrokeList(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GPushStroke> getPushStrokeData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GMyCollection> getCollectData(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GSendLove> sendEditData(@Path("url") String url, @QueryMap Map<String, String> map);

    @Multipart
    @POST("{url}")
    Observable<GSendLove> sendEditDataWithPhoto(@Path("url") String url, @QueryMap Map<String, String> map, @PartMap Map<String, RequestBody> image);

    @POST("{url}")
    Observable<GSendLove> sendHeadPhoto(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GSendLove> sendDelPlace(@Path("url") String url, @QueryMap Map<String, String> map);

    @POST("{url}")
    Observable<GCashFlow> getCashFlowData(@Path("url") String url, @QueryMap Map<String, String> map);
}
