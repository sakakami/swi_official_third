package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.MusicEntity;
import com.switube.www.landmark2018test.presenter.callback.IPPlaylist;

import java.util.List;

import io.reactivex.MaybeObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MPlaylist {
    private IPPlaylist ipPlaylist;
    public MPlaylist(IPPlaylist ipPlaylist) {
        this.ipPlaylist = ipPlaylist;
    }

    public void getSearchData(String keyword) {
        AppDatabase
                .getInstance()
                .musicDao()
                .getSearchDataByRandom(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlaylist.handleSearchData(musicEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getAllData(final List<MusicEntity> oldData) {
        AppDatabase
                .getInstance()
                .musicDao()
                .getAllDataByRandom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlaylist.handleSearchData(oldData, musicEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() { }
                });
    }
}
