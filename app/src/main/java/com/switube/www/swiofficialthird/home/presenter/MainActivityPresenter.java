package com.switube.www.swiofficialthird.home.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.home.model.MainActivityModel;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.map.view.MapFragment;
import com.switube.www.swiofficialthird.map.view.PhotoFragment;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainActivity mIMainActivity;
    private MainActivityModel mMainActivityModel;
    private CompositeDisposable mCompositeDisposable;
    public MainActivityPresenter(IMainActivity iMainActivity) {
        Log.e("presenter", "start");
        mIMainActivity = iMainActivity;
        mMainActivityModel = new MainActivityModel(this);
        mCompositeDisposable = new CompositeDisposable();
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
                            handleLocation(activity);
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

    public void handleSavePhotoAndRefresh(final AppCompatActivity activity) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        File file = new File(PhotoFragment.imagePath);
                        Uri uri = Uri.fromFile(file);
                        intent.setData(uri);
                        activity.sendBroadcast(intent);
                        emitter.onNext("OK");
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {
                        if (s.equals("OK")) {
                            PhotoFragment.isTakePhoto = true;
                            PhotoFragment photoFragment = (PhotoFragment) activity.getSupportFragmentManager().findFragmentByTag("photoFragment");
                            photoFragment.handleRefreshAdapter();
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
    private LocationResult mLocationResult;
    private List<LatLng> mLatLngList = new ArrayList<>();
    @SuppressLint("MissingPermission")
    private void handleLocation(Context context) {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
        LocationCallback callback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mLocationResult = locationResult;
                //mIMainActivity.getMainService().setLocation(locationResult);
                mLatLng = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                if (MapFragment.isCent) {
                    mLatLngList.add(mLatLng);
                }
                if (mIMainActivity.getGoogleMap() != null) {
                    if (isFirst) {
                        isFirst = false;
                        mIMainActivity.getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));
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

    public LatLng getmLatLng() {
        return mLatLng;
    }

    public void refreshMyLocation() {
        if (mIMainActivity.getGoogleMap() != null && mLatLng != null) {
            mIMainActivity.getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15));
        }
    }

    public void clearLatLngList() {
        mLatLngList.clear();
    }

    public List<LatLng> getmLatLngList() {
        return mLatLngList;
    }

    public void drawLine() {
        if (mIMainActivity.getGoogleMap() != null) {
            PolylineOptions polylineOptions = new PolylineOptions();
            int size = mLatLngList.size();
            for (int i = 0; i < size; i++) {
                polylineOptions.add(mLatLngList.get(i));
            }
            polylineOptions.color(Color.RED);
            mIMainActivity.getGoogleMap().addPolyline(polylineOptions);
        }
    }

    private int mCount = 0;
    private int mMin = 0;
    private int mSec = 0;
    private int mHour = 0;
    public void startTimer(final boolean isTimer, final Context context) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    private Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        mCompositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (!disposable.isDisposed()) {
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
                                handleTime();
                                calculateDistance();
                                calculateAvg();
                                handleDrawLine();
                            } else {
                                handleTimeAndCost(context);
                            }
                            //Log.e("time", String.valueOf(mCount));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void stopTimer() {
        mCompositeDisposable.clear();
    }

    public void clearTimer() {
        mCount = 0;
        mMin = 0;
        mSec = 0;
        mHour = 0;
        mDistance = 0;
    }

    private AttractionStyleEntity mAttractionStyleEntity;
    public void setSelectAttractionTypeData(AttractionStyleEntity attractionStyleEntity) {
        mAttractionStyleEntity = attractionStyleEntity;
    }

    public AttractionStyleEntity getmAttractionStyleEntity() {
        return mAttractionStyleEntity;
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

    private LocationResult mLocationOld;
    private float mDistance = 0;
    private void calculateDistance() {
        if (mLocationOld == null) {
            mLocationOld = mLocationResult;
        } else {
            float[] results = new float[1];
            Location.distanceBetween(mLocationOld.getLastLocation().getLatitude(),
                    mLocationOld.getLastLocation().getLongitude(),
                    mLocationResult.getLastLocation().getLatitude(),
                    mLocationResult.getLastLocation().getLongitude(), results);
            mLocationOld = mLocationResult;
            if (results[0] <= 11) {
                mDistance += results[0];
            }
            float result = mDistance / 1000;
            if (result < 0.01) {
                mIMainActivity.handleChangeTextLeft("0.00");
            } else {
                mIMainActivity.handleChangeTextLeft(String.format(Locale.TAIWAN, "%.2f", result));
            }
        }
    }

    private void calculateAvg() {
        if (mDistance != 0 && mCount != 0) {
            double mAvg = mCount / (mDistance / 1000);
            double sec = 0;
            double min = 0;
            if (mAvg > 59) {
                sec = mAvg % 60;
                min = mAvg / 60;
            } else {
                sec = mAvg;
            }
            //Log.e("sec", String.valueOf(sec));
            //Log.e("min", String.valueOf(min));
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append((int)min);
            stringBuffer.append(":");
            if (sec < 10) {
                stringBuffer.append("0");
            }
            stringBuffer.append((int)sec);
            mIMainActivity.handleChangeTextRight(stringBuffer.toString());
        } else {
            mIMainActivity.handleChangeTextRight("99+");
        }
    }

    private StringBuilder mStringBuilder = new StringBuilder();
    private void handleTime() {
        mStringBuilder.delete(0, mStringBuilder.length());
        int size = 0;
        if (mHour > 0) {
            if (mHour < 10) {
                mStringBuilder.append(0);
            }
            mStringBuilder.append(mHour);
            mStringBuilder.append(":");
            size = 30;
        } else {
            size = 50;
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
        mIMainActivity.handleChangeTextCenter(mStringBuilder.toString(), size);
    }

    private PolylineOptions polylineOptions = new PolylineOptions().color(R.color.colorBlue);
    private void handleDrawLine() {
        polylineOptions.add(new LatLng(mLocationResult.getLastLocation().getLatitude(), mLocationResult.getLastLocation().getLongitude()));
        mIMainActivity.getGoogleMap().clear();
        mIMainActivity.getGoogleMap().addPolyline(polylineOptions);
    }

    private double mMoney = 0;
    private void handleTimeAndCost(Context context) {
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
        String titleA = context.getString(R.string.ebike_calculate_title_down);
        String titleB = context.getString(R.string.ebike_calculate_title_up);
        mIMainActivity.handleChangeTimeAndCost(titleA + mStringBuilder.toString(), titleB + String.valueOf((int)mMoney) + " 元");
    }
}
