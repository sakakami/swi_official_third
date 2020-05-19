package com.switube.www.landmark2018test.view;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.APhotoView;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

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
public class VPhotoView extends Fragment {
    private IMainActivity iMainActivity;

    @BindView(R.id.textBackInPhotoView)
    TextView mTextBack;
    @BindView(R.id.imageCameraInPhotoView)
    ImageView mImageView;
    @BindView(R.id.viewPagerInPhotoView)
    ViewPager mViewPager;
    private Unbinder mUnbinder;

    public VPhotoView() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_photo_view, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        if (!MyApplication.getAppData().isFromClickInfoHeadPhoto()) {
            mImageView.setVisibility(View.GONE);
        } else {
            MyApplication.getAppData().setFromClickInfoHeadPhoto(false);
        }
        mViewPager.setAdapter(new APhotoView(getAdapterData()));
        mViewPager.setCurrentItem(0);
        RxView.clicks(mTextBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setFromPersonalSteaming(false);
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        VPhotoList.isFromInfo = true;
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            new RxPermissions(getActivity())
                                    .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    .subscribe(new Observer<Permission>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {}

                                        @Override
                                        public void onNext(Permission permission) {
                                            if (permission.granted) {
                                                if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                                    new SignInUtil(getContext(), iMainActivity);
                                                } else {
                                                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("PhotoView").commit();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {}

                                        @Override
                                        public void onComplete() {}
                                    });
                        } else {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                new SignInUtil(getContext(), iMainActivity);
                            } else {
                                getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("PhotoView").commit();
                            }
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
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private List<View> getAdapterData() {
        List<View> photoView = new ArrayList<>();
        int size = MyApplication.getAppData().getPhotoList().size();
        for (int i = 0; i < size; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.pager_item_photo_view, null);
            ImageView imagePhoto = view.findViewById(R.id.imagePhotoInItemPhotoView);
            Glide.with(getContext())
                    .load(Uri.parse(AppConstant.BASE_URL + MyApplication.getAppData().getPhotoList().get(i)))
                    .into(imagePhoto);
            photoView.add(view);
        }
        return photoView;
    }
}
