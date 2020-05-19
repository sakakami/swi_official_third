package com.switube.www.landmark2018test.view;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ACreateAttraction;
import com.switube.www.landmark2018test.adapter.callback.IACreateAttraction;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.gson.GAttractionDataGoogle;
import com.switube.www.landmark2018test.presenter.PCreateAttraction;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVCreateAttraction;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class VCreateAttraction extends Fragment implements IVCreateAttraction, IACreateAttraction {
    @BindViews({R.id.textBackInCreateAttraction, R.id.textNextInCreateAttraction, R.id.textTitleInCreateAttraction})
    List<TextView> mTextViews;
    @BindView(R.id.recyclerInCreateAttraction)
    RecyclerView mRecyclerView;
    @BindView(R.id.layoutInCreateAttraction)
    RelativeLayout relativeLayout;
    @BindView(R.id.viewBarLockInCreateAttraction)
    View mView;
    private PCreateAttraction pCreateAttraction;
    private Unbinder mUnbinder;
    private IMainActivity mIMainActivity;
    private ACreateAttraction aCreateAttraction;

    public VCreateAttraction() {
        pCreateAttraction = new PCreateAttraction(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_create_attraction, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        if (mIMainActivity.getCreateData() == null) {
            pCreateAttraction.getAttractionDetail(mIMainActivity.getPlaceId());
        } else {
            init(mIMainActivity.getCreateData());
        }
        RxView.clicks(mTextViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.setCreateData(null);
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        RxView.clicks(mTextViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        saveCreateData();
                        int nameEN = mIMainActivity.getCreateData().getPlace().getPlace_en().length();
                        int nameTW = mIMainActivity.getCreateData().getPlace().getPlace_tw().length();
                        int nameCN = mIMainActivity.getCreateData().getPlace().getPlace_ch().length();
                        int nameJP = mIMainActivity.getCreateData().getPlace().getPlace_jp().length();
                        if (nameCN > 0 && nameEN > 0 && nameTW > 0 && nameJP > 0 && mIMainActivity.getSelectedAttractionType() != null) {
                            getParentFragmentManager().beginTransaction()
                                    .replace(R.id.layoutContainer, new VFeatures()).addToBackStack("CreateAttraction")
                                    .commit();
                        } else {
                            AlertDialogUtil.getInstance().initDialogBuilder(getContext(),
                                    R.string.create_action_message,
                                    R.string.global_ok,
                                    (dialogInterface, i) -> {});
                            AlertDialogUtil.getInstance().showAlertDialog();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return view;
    }

    @Override
    public AttractionStyleEntity getStyleEntity() {
        return mIMainActivity.getSelectedAttractionType();
    }

    @Override
    public void switchPage(int type) {
        saveCreateData();
        switch (type) {
            case 0:
                VAttractionType.isClickHotKey = false;
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new VAttractionType()).addToBackStack("CreateAttraction")
                        .commit();
                break;
            case 1:
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new VEditTime()).addToBackStack("CreateAttraction")
                        .commit();
                break;
            case 2:
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    new RxPermissions(getActivity())
                            .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Observer<Permission>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(Permission permission) {
                                    if (permission.granted) {
                                        getParentFragmentManager().beginTransaction()
                                                .replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("CreateAttraction")
                                                .commit();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
                } else {
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("CreateAttraction")
                            .commit();
                }
                break;
            case 3:
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(intentBuilder.build(getActivity()), 2000);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void init(List<GAttractionDataGoogle> detailEntities) {
        pCreateAttraction.init(detailEntities, mIMainActivity);
    }

    @Override
    public void init(ECreateAttraction entity) {
        relativeLayout.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
        aCreateAttraction = new ACreateAttraction(entity, this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(aCreateAttraction);
    }

    @Override
    public boolean checkPhotoSize() {
        return MyApplication.getAppData().getSelectedPhotos().size() > 0;
    }

    @Override
    public String getPhoto() {
        return MyApplication.getAppData().getSelectedPhotos().get(0);
    }

    @Override
    public int getPhotoSize() {
        return MyApplication.getAppData().getSelectedPhotos().size();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == RESULT_OK) {
                relativeLayout.setVisibility(View.VISIBLE);
                mView.setVisibility(View.VISIBLE);
                mIMainActivity.setCreateData(null);
                MyApplication.getAppData().getSelectedPhotos().clear();
                mIMainActivity.setSelectedAttractionType(null);
                Place place = PlacePicker.getPlace(getContext(), data);
                if (place.getName().toString().contains("°")) {
                    pCreateAttraction.getPlaceData(place.getLatLng());
                } else {
                    mIMainActivity.setPlaceId(place.getId());
                    pCreateAttraction.getAttractionDetail(place.getId());
                }
            }
        }
    }

    @Override
    public void savePlaceId(String id) {
        mIMainActivity.setPlaceId(id);
        pCreateAttraction.getAttractionDetail(id);
    }

    private void saveCreateData() {
        mIMainActivity.setCreateData(aCreateAttraction.getmCreateData());
    }
}
