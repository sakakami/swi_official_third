package com.switube.www.swiofficialthird.home.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.map.view.PhotoFragment;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements IMainActivityPresenter {
    private IMainActivity mIMainActivity;
    @Inject
    public MainActivityPresenter(IMainActivity iMainActivity) {
        Log.e("presenter", "start");
        mIMainActivity = iMainActivity;
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
    @SuppressLint("MissingPermission")
    private void handleLocation(Context context) {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
        LocationCallback callback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (mIMainActivity.getGoogleMap() != null && isFirst) {
                    isFirst = false;
                    LatLng latLng = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                    mIMainActivity.getGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            }
        };
        LocationRequest request = new LocationRequest();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(2000);
        request.setFastestInterval(1000);
        client.requestLocationUpdates(request, callback, Looper.myLooper());
    }
}
