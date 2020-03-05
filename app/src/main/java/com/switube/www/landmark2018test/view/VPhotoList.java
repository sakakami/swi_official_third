package com.switube.www.landmark2018test.view;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.APhotoList;
import com.switube.www.landmark2018test.adapter.callback.IAPhotoList;
import com.switube.www.landmark2018test.presenter.PPhotoList;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVPhotoList;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

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
public class VPhotoList extends Fragment implements IVPhotoList, IAPhotoList {
    public static boolean isTakePhoto = false;
    public static boolean isFromInfo = false;
    public static String imagePath;
    private PPhotoList pPhotoList;
    private APhotoList aPhotoList;

    @BindViews({R.id.textBackInPhoto, R.id.textConfirmInPhoto})
    List<TextView> mTextViews;
    @BindView(R.id.imageTakePhotoInPhoto)
    ImageView mImageTakePhoto;
    @BindView(R.id.recyclerInPhoto)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;

    public VPhotoList() {
        pPhotoList = new PPhotoList(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_photo_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        aPhotoList = new APhotoList(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 3));
        mRecyclerView.addItemDecoration(new ItemDecorationUtil(container.getContext(), 2, 2, 2, 2));
        mRecyclerView.setAdapter(aPhotoList);
        handleSwitchImage(false);
        RxView.clicks(mTextViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        isTakePhoto = false;
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

        RxPermissions rxPermissions = new RxPermissions(getActivity());
        RxView.clicks(mImageTakePhoto)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA))
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            pPhotoList.handleOpenCamera(getContext());
                        } else {
                            new RxPermissions(getActivity())
                                    .requestEach(Manifest.permission.CAMERA)
                                    .subscribe(new Observer<Permission>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {}

                                        @Override
                                        public void onNext(Permission permission) {
                                            if (permission.granted) {
                                                pPhotoList.handleOpenCamera(getContext());
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {}

                                        @Override
                                        public void onComplete() {}
                                    });
                        }
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
                        if (isFromInfo) {
                            if (aPhotoList.getmSelectedPhoto().size() > 0) {
                                pPhotoList.handlePhotoDataToSend(mIMainActivity.getAttractionId(), aPhotoList.getmSelectedPhoto());
                            }
                        } else {
                            MyApplication.getAppData().setSelectedPhotos(aPhotoList.getmSelectedPhoto());
                            getFragmentManager().popBackStack();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void handleSwitchImage(boolean b) {
        if (b) {
            mImageTakePhoto.setVisibility(View.GONE);
            mTextViews.get(1).setVisibility(View.VISIBLE);
        } else {
            mImageTakePhoto.setVisibility(View.VISIBLE);
            mTextViews.get(1).setVisibility(View.GONE);
        }
    }

    @Override
    public void handleStartIntent(Intent intent) {
        startActivityForResult(intent, 1000);
    }

    @Override
    public void handleFinishSend() {
        isFromInfo = false;
        getFragmentManager().popBackStack();
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            pPhotoList.handleSavePhotoAndRefresh(getContext());
        }
    }

    @Override
    public void handleRefreshAdapter() {
        aPhotoList.handleReloadAllPhoto();
    }
}
