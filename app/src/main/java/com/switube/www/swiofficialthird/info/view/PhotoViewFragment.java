package com.switube.www.swiofficialthird.info.view;


import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.info.adapter.PhotoViewAdapter;
import com.switube.www.swiofficialthird.info.presenter.PhotoViewFragmentPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
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
public class PhotoViewFragment extends Fragment implements IPhotoViewFragment {
    public static boolean isShowCamera = true;
    private PhotoViewFragmentPresenter mPhotoViewFragmentPresenter;
    public PhotoViewFragment() {
        mPhotoViewFragmentPresenter = new PhotoViewFragmentPresenter(this);
    }

    @BindView(R.id.textBackInPhotoView)
    TextView mTextBack;
    @BindView(R.id.imageCameraInPhotoView)
    ImageView mImageView;
    @BindView(R.id.viewPagerInPhotoView)
    ViewPager mViewPager;
    private Unbinder mUnbinder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_view, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mViewPager.setAdapter(new PhotoViewAdapter(getAdapterData()));
        mViewPager.setCurrentItem(0);
        RxView.clicks(mTextBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        if (isShowCamera) {
            mImageView.setVisibility(View.VISIBLE);
        } else {
            mImageView.setVisibility(View.GONE);
        }
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        RxView.clicks(mImageView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(rxPermissions.ensure(Manifest.permission.CAMERA))
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            mPhotoViewFragmentPresenter.handleOpenCamera(getActivity());
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

    private List<View> getAdapterData() {
        List<View> photoView = new ArrayList<>();
        for (int i = 0 ; i < 3; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.pager_item_photo_view, null);
            ImageView imagePhoto = view.findViewById(R.id.imagePhotoInItemPhotoView);
            imagePhoto.setImageResource(R.drawable.none_4);
            photoView.add(view);
        }
        return photoView;
    }
}
