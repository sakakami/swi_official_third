package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.PolylineOptions;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.view.callback.IMainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
public class VFinishTour extends Fragment implements OnMapReadyCallback {

    private String mDefault;

    @BindViews({R.id.textBackInDetail, R.id.textOkInDetail, R.id.textCentMessageInDetail, R.id.textReturnMessageInDetail})
    List<TextView> mTextViews;
    @BindView(R.id.editContentInDetail)
    EditText mEditText;
    @BindViews({R.id.textLeftUpInDetail, R.id.textCenterUpInDetail, R.id.textRightUpInDetail})
    List<TextView> mTextViewInUp;
    @BindViews({R.id.textLeftDownInDetail, R.id.textCenterDownInDetail, R.id.textRightDownInDetail})
    List<TextView> mTextViewInDown;
    private SupportMapFragment mSupportMapFragment;
    private Unbinder mUnbinder;

    public VFinishTour() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        mDefault = simpleDateFormat.format(Calendar.getInstance().getTime());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_finish_tour, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mEditText.setText(mDefault);
        mTextViewInUp.get(0).setText(mIMainActivity.getBikeMoney());
        mTextViewInUp.get(1).setText(mIMainActivity.getBikeDate());
        mTextViewInUp.get(2).setText(mIMainActivity.getSelectBikeNumber());
        mTextViews.get(2).setText(mIMainActivity.getCentPlace());
        mTextViews.get(3).setText(mIMainActivity.getReturnPlace());
        RxView.clicks(mTextViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handleStopTimer();
                        mIMainActivity.handleClearLatLngList();
                        mIMainActivity.handleClearPolyline();
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handleStopTimer();
                        mIMainActivity.handleClearLatLngList();
                        mIMainActivity.handleClearPolyline();
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        if (mSupportMapFragment == null) {
            mSupportMapFragment = SupportMapFragment.newInstance();
            mSupportMapFragment.getMapAsync(this);
        }
        getChildFragmentManager().beginTransaction().replace(R.id.mapInDetail, mSupportMapFragment).commit();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        PolylineOptions polylineOptions = new PolylineOptions().color(Color.RED);
        int size = mIMainActivity.getLatLngList().size();
        for (int i = 0; i < size; i++) {
            polylineOptions.add(mIMainActivity.getLatLngList().get(i));
        }
        googleMap.addPolyline(polylineOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mIMainActivity.getLatLngList().get(0), 15));
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
