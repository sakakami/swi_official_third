package com.switube.www.swiofficialthird.map.view;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.info.view.InfoFragment;
import com.switube.www.swiofficialthird.map.presenter.MapPresenter;
import com.switube.www.swiofficialthird.util.ClusterItemUtil;
import com.switube.www.swiofficialthird.util.ClusterRendererUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements IMapFragment, OnMapReadyCallback,
        ClusterManager.OnClusterClickListener<ClusterItemUtil>,
        ClusterManager.OnClusterItemInfoWindowClickListener<ClusterItemUtil>,
        ClusterManager.OnClusterItemClickListener<ClusterItemUtil>,
        ClusterManager.OnClusterInfoWindowClickListener<ClusterItemUtil> {
    private MapPresenter mMapPresenter;
    public MapFragment() {
        mMapPresenter = new MapPresenter(this);
    }

    private SupportMapFragment supportMapFragment;
    private Context mContext;
    private Unbinder mUnbinder;
    @BindView(R.id.imageMenuInMap) ImageView mImageMenu;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        RxView.clicks(mImageMenu)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new InfoFragment()).addToBackStack("map").commit();
                        mIMainActivity.handleCleanGoogleMap();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        mContext = container.getContext();
        mMapPresenter.getAttractionData(container.getContext().getApplicationContext());
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            supportMapFragment.getMapAsync(this);
        }
        getChildFragmentManager().beginTransaction().replace(R.id.layoutMap, supportMapFragment).commit();
        return view;
    }

    private ClusterManager<ClusterItemUtil> mClustermanager;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mIMainActivity.setGoogleMap(googleMap);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        mClustermanager = new ClusterManager<>(mContext, googleMap);
        mClustermanager.setRenderer(new ClusterRendererUtil(mContext, googleMap, mClustermanager));
        googleMap.setOnCameraIdleListener(mClustermanager);
        googleMap.setOnMarkerClickListener(mClustermanager);
        googleMap.setOnInfoWindowClickListener(mClustermanager);
        mClustermanager.setOnClusterClickListener(this);
        mClustermanager.setOnClusterItemClickListener(this);
        mClustermanager.setOnClusterInfoWindowClickListener(this);
        mClustermanager.setOnClusterItemInfoWindowClickListener(this);
        mClustermanager.clearItems();
        mClustermanager.addItems(mMapPresenter.getmAttraction());
        mClustermanager.cluster();
    }

    @Override
    public boolean onClusterClick(Cluster<ClusterItemUtil> cluster) {
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<ClusterItemUtil> cluster) {}

    @Override
    public boolean onClusterItemClick(ClusterItemUtil clusterItemUtil) {
        return true;
    }

    @Override
    public void onClusterItemInfoWindowClick(ClusterItemUtil clusterItemUtil) {}

    @Override
    public void handleUpdateAttractionData(List<ClusterItemUtil> list) {
        //mClustermanager.clearItems();
        //mClustermanager.addItems(list);
        //mClustermanager.cluster();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        Log.e("OnDestroyView", "Start");
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }
}
