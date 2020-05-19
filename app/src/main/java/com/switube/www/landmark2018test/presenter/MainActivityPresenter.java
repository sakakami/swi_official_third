package com.switube.www.landmark2018test.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;

import androidx.annotation.Nullable;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.switube.www.landmark2018test.MainActivity;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.gson.GSignInData;
import com.switube.www.landmark2018test.model.MainActivityModel;
import com.switube.www.landmark2018test.presenter.callback.IMainActivityPresenter;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.VMap;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainActivity mIMainActivity;
    private MainActivityModel mMainActivityModel;
    private CompositeDisposable mCompositeDisposable;
    private Bitmap bitmap;
    public MainActivityPresenter(IMainActivity iMainActivity) {
        mIMainActivity = iMainActivity;
        mMainActivityModel = new MainActivityModel(this);
        mCompositeDisposable = new CompositeDisposable();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        bitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.locate_v1_1, options);
    }

    public void handleCheckPermission(final Activity activity) {
        new RxPermissions(activity)
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Observer<Permission>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Permission permission) {
                        if (permission.granted) {
                            handleLocation();
                        } else {
                            activity.finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    private boolean isFirst = true;
    private LatLng mLatLng;
    private List<LatLng> mLatLngList = new ArrayList<>();
    private MarkerOptions markerOptions;
    private PolylineOptions polylineRed;
    private Polyline polylineMap;
    @SuppressLint("MissingPermission")
    private void handleLocation() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(MyApplication.getInstance());
        LocationCallback callback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mLatLng = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                if (VMap.isCent) {
                    mLatLngList.add(mLatLng);
                }
                if (mIMainActivity.getGoogleMap() != null) {
                    if (markerOptions == null) {
                        markerOptions = new MarkerOptions()
                                .flat(true)
                                .icon(BitmapDescriptorFactory
                                        .fromBitmap(bitmap))
                                .anchor(0.5f, 0.5f)
                                .position(mLatLng);
                    } else {
                        markerOptions.position(mLatLng);
                    }
                    if (marker == null) {
                        marker = mIMainActivity.getGoogleMap().addMarker(markerOptions);
                    } else {
                        marker.remove();
                        marker = mIMainActivity.getGoogleMap().addMarker(markerOptions);
                    }
                    if (isFirst) {
                        isFirst = false;
                        mIMainActivity.getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng,  MyApplication.getAppData().getZoomSize()));
                    }
                    if (VMap.isCent) {
                        if (polylineRed == null) {
                            polylineRed = new PolylineOptions().color(Color.RED);
                        }
                        polylineRed.add(mLatLng);
                        polylineMap =  mIMainActivity.getGoogleMap().addPolyline(polylineRed);
                    }
                }
            }
        };
        LocationRequest request = new LocationRequest();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(2000);
        request.setFastestInterval(1000);
        client.requestLocationUpdates(request, callback, Looper.myLooper());
    }

    public void handleClearPolyline() {
        polylineMap.remove();
        polylineRed = null;
        mIMainActivity.getGoogleMap().clear();
    }

    public LatLng getmLatLng() {
        return mLatLng;
    }

    private Marker marker = null;
    public void refreshMyLocation() {
        if (mIMainActivity.getGoogleMap() != null && mLatLng != null) {
            if (markerOptions == null) {
                markerOptions = new MarkerOptions()
                        .flat(true)
                        .icon(BitmapDescriptorFactory
                                .fromBitmap(bitmap))
                        .anchor(0.5f, 0.5f)
                        .position(mLatLng);
            } else {
                markerOptions.position(mLatLng);
            }
            if (marker == null) {
                marker = mIMainActivity.getGoogleMap().addMarker(markerOptions);
            } else {
                marker.remove();
                marker = mIMainActivity.getGoogleMap().addMarker(markerOptions);
            }
            if (MyApplication.getAppData().isFocus()) {
                MyApplication.getAppData().setFocus(false);
                if (MyApplication.getAppData().getZoomSize() < 19) {
                    MyApplication.getAppData().setZoomSize(MyApplication.getAppData().getZoomSize() + 2);
                } else if (MyApplication.getAppData().getZoomSize() == 19) {
                    MyApplication.getAppData().setZoomSize(15);
                }
            }
            mIMainActivity.getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, MyApplication.getAppData().getZoomSize()), 400, null);
        }
    }

    public void clearLatLngList() {
        mLatLngList.clear();
        if (polyline != null) {
            polyline.remove();
        }
    }

    public List<LatLng> getmLatLngList() {
        return mLatLngList;
    }

    private Polyline polyline;
    public void drawLine() {
        if (mIMainActivity.getGoogleMap() != null) {
            PolylineOptions polylineOptions = new PolylineOptions();
            int size = mLatLngList.size();
            for (int i = 0; i < size; i++) {
                polylineOptions.add(mLatLngList.get(i));
            }
            polylineOptions.color(Color.RED);
            polyline = mIMainActivity.getGoogleMap().addPolyline(polylineOptions);
        }
    }

    private int mCount = 0;
    private int mMin = 0;
    private int mSec = 0;
    private int mHour = 0;
    public void startTimer(final boolean isTimer) {
        DisposableObserver<Long> disposableObserver = new DisposableObserver<Long>() {
            @Override
            public void onNext(Long aLong) {
                mCount++;
                mSec++;
                if (mSec == 60) {
                    mSec = 0;
                    mMin++;
                }
                if (mMin == 60) {
                    mMin = 0;
                    mHour++;
                }
                if (isTimer) {
                    handleTimeAndCarbon();
                    //handleTime();
                    //calculateDistance();
                    //calculateAvg();
                    //handleDrawLine();
                } else {
                    handleTimeAndCost();
                }
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
        mCompositeDisposable.add(disposableObserver);
    }

    public void stopTimer() {
        mCompositeDisposable.clear();
    }

    public void clearTimer() {
        mCount = 0;
        mMin = 0;
        mSec = 0;
        mHour = 0;
    }

    private double mCostPerSec = 0;
    public void setCostPerSec(double costPerSec) {
        mCostPerSec = costPerSec;
    }

    public double getmMoney() {
        return mMoney;
    }

    public StringBuilder getmStringBuilder() {
        return mStringBuilder;
    }

    public void handleSignIn(String type, Context context) {
        switch (type) {
            case "facebook":
                List<String> permissions = new ArrayList<>();
                permissions.add("public_profile");
                permissions.add("email");
                LoginManager.getInstance().logOut();
                LoginManager.getInstance().logInWithReadPermissions((MainActivity)context, permissions);
                break;
            case "google":
                Auth.GoogleSignInApi.signOut(getGoogleApiClient());
                Auth.GoogleSignInApi.revokeAccess(getGoogleApiClient());
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(getGoogleApiClient());
                mIMainActivity.initForResult(intent);
                break;
            default:
                break;
        }
    }

    public void handleSignOut() {
        switch (SharePreferencesUtil.getInstance().getSharedPreferences().getString("signInType", "null")) {
            case "google":
                Auth.GoogleSignInApi.signOut(getGoogleApiClient());
                Auth.GoogleSignInApi.revokeAccess(getGoogleApiClient());
                break;
            case "facebook":
                LoginManager.getInstance().logOut();
                break;
            default:
                break;
        }
        SharePreferencesUtil.getInstance().getEditor().putString("userMaid", "null").apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userEmail", "null").apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userWsid", "null").apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userImg", "null").apply();
        SharePreferencesUtil.getInstance().getEditor().putString("signInType", "null").apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userName", "null").apply();
        mIMainActivity.handleRefreshSlideMenu("null", "null");
    }

    private GoogleApiClient googleApiClient;
    public GoogleApiClient getGoogleApiClient() {
        if (googleApiClient == null) {
            GoogleSignInOptions googleSignInOptions =
                    new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail().requestId().requestProfile().build();
            googleApiClient = new GoogleApiClient.Builder(MyApplication.getInstance())
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(@Nullable Bundle bundle) {}

                        @Override
                        public void onConnectionSuspended(int i) {}
                    })
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                    .build();
            googleApiClient.connect();
            return googleApiClient;
        } else {
            if (!googleApiClient.isConnected()) {
                googleApiClient.connect();
            }
            return googleApiClient;
        }
    }

    public void handleGoogleResult(Intent data) {
        GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (googleSignInResult != null && googleSignInResult.isSuccess()) {
            GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
            if (googleSignInAccount != null) {
                Map<String, String> map = new HashMap<>();
                map.put("appname", "landmark2018test");
                map.put("serialid", Settings.System.getString(MyApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID));
                map.put("pattern", Build.BOARD + "_" + Build.MODEL);
                map.put("matype", "google");
                map.put("id", googleSignInAccount.getId());
                map.put("accid", googleSignInAccount.getEmail());
                map.put("accname", googleSignInAccount.getDisplayName());
                map.put("maimg", "");
                String photoUrl;
                if (googleSignInAccount.getPhotoUrl() != null) {
                    photoUrl = googleSignInAccount.getPhotoUrl().toString();
                } else {
                    photoUrl = "";
                }
                mMainActivityModel.sendSignInData(map, "google", photoUrl);
            }
        }
    }

    private CallbackManager callbackManager;
    public CallbackManager getCallbackManager() {
        if (callbackManager == null) {
            callbackManager = CallbackManager.Factory.create();
            return callbackManager;
        } else {
            return callbackManager;
        }
    }

    private int index = -1;
    public void setMessageIndex(int index) {
        this.index = index;
    }
    public Integer getMessageIndex() {
        return index;
    }

    @Override
    public void handleAccountGson(GSignInData gSignInData, String signType, String photoUrl) {
        SharePreferencesUtil.getInstance().getEditor().putString("userMaid", gSignInData.getInfo_data().get(0).getMaid()).apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userImg", gSignInData.getInfo_data().get(0).getImg()).apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userName", gSignInData.getInfo_data().get(0).getAccname()).apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userEmail", gSignInData.getInfo_data().get(0).getAccid()).apply();
        SharePreferencesUtil.getInstance().getEditor().putString("userWsid", gSignInData.getInfo_data().get(0).getWsid()).apply();
        SharePreferencesUtil.getInstance().getEditor().putString("signInType", signType).apply();
        SharePreferencesUtil.getInstance().getEditor().putString("authority", gSignInData.getAuthority());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gSignInData.getInfo_data().get(0).getImg());
        stringBuilder.delete(0, 3);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("swiid", gSignInData.getInfo_data().get(0).getSwiid());
        map.put("sticker", photoUrl);
        mMainActivityModel.sendHeadPhoto(map);
        mIMainActivity.handleRefreshSlideMenu(gSignInData.getInfo_data().get(0).getAccname(), AppConstant.BASE_URL2 + stringBuilder.toString());
    }

    public void initFacebookClient() {
        LoginManager.getInstance().registerCallback(getCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (loginResult != null) {
                    final Profile profile = Profile.getCurrentProfile();
                    if (profile != null) {
                        final Map<String, String> map = new HashMap<>();
                        map.put("appname", "landmark2018test");
                        map.put("serialid", Settings.System.getString(MyApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID));
                        map.put("pattern", Build.BOARD + "_" + Build.MODEL);
                        map.put("matype", "facebook");
                        if (profile.getName() != null) {
                            map.put("accname", profile.getName());
                        } else {
                            map.put("accname", "");
                        }
                        map.put("maimg", "");
                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                            if (object != null) {
                                try {
                                    map.put("accid", object.getString("email"));
                                    map.put("id", object.getString("id"));
                                    mMainActivityModel.sendSignInData(map, "facebook", profile.getProfilePictureUri(96, 96).toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Bundle bundle = new Bundle();
                        bundle.putString("fields", "email");
                        graphRequest.setParameters(bundle);
                        graphRequest.executeAsync();
                    } else {
                        mIMainActivity.handleSignIn("facebook");
                    }
                }
            }

            @Override
            public void onCancel() { }

            @Override
            public void onError(FacebookException error) { }
        });
    }

    private ECreateAttraction.Time time;
    public void setTime(ECreateAttraction.Time time) {
        this.time = time;
    }
    public ECreateAttraction.Time getTime() {
        return time;
    }

    private String placeName;
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public String getPlaceName() {
        return placeName;
    }

    private StringBuilder mStringBuilder = new StringBuilder();

    private double mMoney = 0;
    private void handleTimeAndCost() {
        mMoney = Math.round(mCount * mCostPerSec);
        mStringBuilder.delete(0, mStringBuilder.length());
        if (mHour > 0) {
            if (mHour < 10) {
                mStringBuilder.append(0);
            }
            mStringBuilder.append(mHour);
            mStringBuilder.append(":");
        }
        if (mMin < 10) {
            mStringBuilder.append(0);
        }
        mStringBuilder.append(mMin);
        mStringBuilder.append(":");
        if (mSec < 10) {
            mStringBuilder.append(0);
        }
        mStringBuilder.append(mSec);
        String titleA = MyApplication.getInstance().getString(R.string.map_ebike_calculate_down);
        String titleB = MyApplication.getInstance().getString(R.string.map_ebike_calculate_up) + MyApplication.getInstance().getString(R.string.map_ebike_calculate_unit);
        mIMainActivity.handleChangeTimeAndCost(titleA + mStringBuilder.toString(), titleB + (int)mMoney);
    }

    private void handleTimeAndCarbon() {
        mStringBuilder.delete(0, mStringBuilder.length());
        if (mHour > 0) {
            if (mHour < 10) {
                mStringBuilder.append(0);
            }
            mStringBuilder.append(mHour);
            mStringBuilder.append(":");
        }
        if (mMin < 10) {
            mStringBuilder.append(0);
        }
        mStringBuilder.append(mMin);
        mStringBuilder.append(":");
        if (mSec < 10) {
            mStringBuilder.append(0);
        }
        mStringBuilder.append(mSec);
        String titleA = "交通時間：";
        String titleB = "預計碳足跡：";
        mIMainActivity.handleChangeTimeAndCost(titleA + mStringBuilder.toString(), titleB + 0);
    }
}
