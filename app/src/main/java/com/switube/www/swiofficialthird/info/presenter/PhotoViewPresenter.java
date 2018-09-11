package com.switube.www.swiofficialthird.info.presenter;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

import com.switube.www.swiofficialthird.info.view.IPhotoViewFragment;

public class PhotoViewPresenter {
    private IPhotoViewFragment mIPhotoViewFragment;
    public PhotoViewPresenter(IPhotoViewFragment iPhotoViewFragment) {
        mIPhotoViewFragment = iPhotoViewFragment;
    }

    /*public void handlePermission(final Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEachCombined(Manifest.permission.CAMERA)
                .subscribe(new Observer<Permission>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Permission permission) {
                        if (permission.granted) {
                            handleOpenCamera(activity);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }*/

    public void handleOpenCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, 2000);
    }
}
