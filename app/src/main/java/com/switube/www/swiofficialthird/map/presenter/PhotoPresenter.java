package com.switube.www.swiofficialthird.map.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.switube.www.swiofficialthird.map.view.PhotoFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PhotoPresenter {
    public PhotoPresenter() {}

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
        File photoFile = null;
        photoFile = createImageFile();
        if (photoFile != null) {
            Uri photoUri = FileProvider.getUriForFile(activity, "com.switube.www.swiofficialthird.provider", photoFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            activity.startActivityForResult(intent, 1000);
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "/DCIM/SwiPictures/" + "SWI_" + timeStamp + "_";
        File image = new File(Environment.getExternalStorageDirectory(), imageFileName + ".jpg");
        if (!image.getParentFile().exists()) {
            image.getParentFile().mkdirs();
        }
        PhotoFragment.imagePath = image.getAbsolutePath();
        return image;
    }
}
