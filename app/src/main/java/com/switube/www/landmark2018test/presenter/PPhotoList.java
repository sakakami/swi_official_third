package com.switube.www.landmark2018test.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.gson.CheckInDataGson;
import com.switube.www.landmark2018test.model.MPhotoList;
import com.switube.www.landmark2018test.presenter.callback.IPPhotoList;
import com.switube.www.landmark2018test.util.NetworkUtil;
import com.switube.www.landmark2018test.view.VPhotoList;
import com.switube.www.landmark2018test.view.callback.IVPhotoList;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PPhotoList implements IPPhotoList {
    private MPhotoList mPhotoList;
    private IVPhotoList iVPhotoList;
    public PPhotoList(IVPhotoList IVPhotoList) {
        mPhotoList = new MPhotoList(this);
        this.iVPhotoList = IVPhotoList;
    }

    public void handleOpenCamera(Context context) {
        File photoFile;
        photoFile = createImageFile();
        if (photoFile != null) {
            Uri photoUri = FileProvider.getUriForFile(context, "com.switube.www.landmark2018test.provider", photoFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            iVPhotoList.handleStartIntent(intent);
        }
    }

    public void handleSavePhotoAndRefresh(final Context context) {
        Observable
                .create((ObservableOnSubscribe<String>) emitter -> {
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    File file = new File(VPhotoList.imagePath);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    context.sendBroadcast(intent);
                    emitter.onNext("OK");
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .delay(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {
                        if (s.equals("OK")) {
                            com.switube.www.landmark2018test.view.VPhotoList.isTakePhoto = true;
                            iVPhotoList.handleRefreshAdapter();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "/DCIM/SwiPictures/" + "SWI_" + timeStamp + "_";
        File image = new File(Environment.getExternalStorageDirectory(), imageFileName + ".jpg");
        if (!image.getParentFile().exists()) {
            image.getParentFile().mkdirs();
        }
        com.switube.www.landmark2018test.view.VPhotoList.imagePath = image.getAbsolutePath();
        return image;
    }

    private Map<String, RequestBody> image = new HashMap<>();
    public void handlePhotoDataToSend(String spid, List<String> photo) {
        final int size = photo.size();
        List<CheckInDataGson.Tag> tags = new ArrayList<>();
        final CheckInDataGson checkInDataGson = new CheckInDataGson(spid, "1", String.valueOf(photo.size()), "", tags);
        Luban.with(MyApplication.getInstance())
                .load(photo)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {}

                    @Override
                    public void onSuccess(File file) {
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        switch (image.size()) {
                            case 0:
                                image.put("img1" + "\"; filename=\"" + file.getName(), requestBody);
                                if (image.size() == size) {
                                    sendCheckInData(checkInDataGson);
                                }
                                break;
                            case 1:
                                image.put("img2" + "\"; filename=\"" + file.getName(), requestBody);
                                if (image.size() == size) {
                                    sendCheckInData(checkInDataGson);
                                }
                                break;
                            case 2:
                                image.put("img3" + "\"; filename=\"" + file.getName(), requestBody);
                                if (image.size() == size) {
                                    sendCheckInData(checkInDataGson);
                                }
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}
                })
                .launch();
    }

    private void sendCheckInData(CheckInDataGson checkInDataGson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String value = gson.toJson(checkInDataGson);
        Map<String, String> map = NetworkUtil.getInstance().getMap();
        map.put("value", value);
        mPhotoList.sendCheckInData(map, image);
    }

    @Override
    public void handleFinishSend() {
        iVPhotoList.handleFinishSend();
    }
}
