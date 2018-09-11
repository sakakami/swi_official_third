package com.switube.www.swiofficialthird.map.view;


import android.Manifest;
import android.content.Context;
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
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.map.adapter.PhotoAdapter;
import com.switube.www.swiofficialthird.map.presenter.PhotoPresenter;
import com.switube.www.swiofficialthird.util.ItemDecorationUtil;
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
public class PhotoFragment extends Fragment implements IPhotoFragment {
    public static boolean isTakePhoto = false;
    public static String imagePath;
    private PhotoPresenter mPhotoFragmentPresenter;
    public PhotoFragment() {
        mPhotoFragmentPresenter = new PhotoPresenter();
    }

    @BindViews({R.id.textBackInPhoto, R.id.textConfirmInPhoto})
    List<TextView> mTextViews;
    @BindView(R.id.imageTakePhotoInPhoto)
    ImageView mImageTakePhoto;
    @BindView(R.id.recyclerInPhoto)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;
    private PhotoAdapter mPhotoAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mPhotoAdapter = new PhotoAdapter(container.getContext(), this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 3));
        mRecyclerView.addItemDecoration(new ItemDecorationUtil(container.getContext(), 2, 2, 2, 2));
        mRecyclerView.setAdapter(mPhotoAdapter);
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
                            mPhotoFragmentPresenter.handleOpenCamera(getActivity());
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
                        mIMainActivity.setSelectedPhoto(mPhotoAdapter.getmSelectedPhoto());
                        getFragmentManager().popBackStack();
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

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    public void handleRefreshAdapter() {
        mPhotoAdapter.handleReloadAllPhoto();
    }
}
