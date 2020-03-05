package com.switube.www.landmark2018test.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ACarbonDetail;
import com.switube.www.landmark2018test.entity.ECarbon;
import com.switube.www.landmark2018test.entity.ECarbonDetail;
import com.switube.www.landmark2018test.presenter.PCarbon;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVCarbon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VCarbon extends Fragment implements IVCarbon, OnMapReadyCallback {
    private PCarbon pCarbon;
    public VCarbon() { pCarbon = new PCarbon(this); }

    @BindViews({R.id.textBackInCarbon, R.id.textOkInCarbon})
    List<TextView> textViews;
    @BindView(R.id.recyclerInCarbon)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private ACarbonDetail adapter;
    private SupportMapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_carbon, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new ACarbonDetail(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 8, 0, 8, 8));
        recyclerView.setAdapter(adapter);
        RxView.clicks(textViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) { getFragmentManager().popBackStack(); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) { getFragmentManager().popBackStack(); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //if (mapFragment == null) {
        //    mapFragment = SupportMapFragment.newInstance();
        //    mapFragment.getMapAsync(this);
        //}
        //getChildFragmentManager().beginTransaction().replace(R.id.mapInItemCarbon, mapFragment).commit();
        pCarbon.init();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        PolylineOptions options = new PolylineOptions().color(Color.RED);
        for (ECarbon eCarbon : MyApplication.getAppData().geteCarBonList().getList()) {
            double lat = eCarbon.getLatitude();
            double lon = eCarbon.getLongitude();
            LatLng latLng = new LatLng(lat, lon);
            options.add(latLng);
        }
        googleMap.addPolyline(options);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(options.getPoints().get(0), 15));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void init(ArrayList<ECarbonDetail> list) {
        //adapter.setMap(getChildFragmentManager(), mapFragment);
        adapter.refresh(list);
    }
}
