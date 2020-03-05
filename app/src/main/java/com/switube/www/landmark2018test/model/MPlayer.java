package com.switube.www.landmark2018test.model;

import com.switube.www.landmark2018test.database.AppDatabase;
import com.switube.www.landmark2018test.database.entity.MusicEntity;
import com.switube.www.landmark2018test.gson.GPlayer;
import com.switube.www.landmark2018test.gson.GPushNewVideo;
import com.switube.www.landmark2018test.gson.GReport;
import com.switube.www.landmark2018test.gson.GSendLove;
import com.switube.www.landmark2018test.presenter.callback.IPPlayer;
import com.switube.www.landmark2018test.util.NetworkUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MPlayer {
    private IPPlayer ipPlayer;
    public MPlayer(IPPlayer ipPlayer) {
        this.ipPlayer = ipPlayer;
    }

    public void getMusicData(Map<String, String> map) {
        NetworkUtil.getInstance().getNetworkService()
                .getMusicData("GetLargeAppMapTubeList_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GPlayer>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GPlayer gPlayer) {
                        ipPlayer.handleMusicData(gPlayer);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void insertMusicData(final List<MusicEntity> musicEntities, final GPlayer gPlayer) {
        Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        AppDatabase.getInstance().musicDao().clearTable();
                        AppDatabase.getInstance().musicDao().handleInsertAllData(musicEntities);
                        emitter.onNext("finish");
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(String s) {
                        ipPlayer.handleFinishInsert(gPlayer);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });

    }

    public void getMusicDataByName(final boolean needRandom, final int randomMode) {
        AppDatabase.getInstance()
                .musicDao()
                .getAllDataByNameA()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.init(musicEntities, needRandom, randomMode, false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMusicDataByRandom(final boolean needRandom, final int randomMode, final boolean isAllShow) {
        AppDatabase.getInstance()
                .musicDao()
                .getAllDataByRandom()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.init(musicEntities, needRandom, randomMode, isAllShow);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMusicDataByView(final boolean needRandom, final int randomMode) {
        AppDatabase.getInstance()
                .musicDao()
                .getAllDataByViewD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.init(musicEntities, needRandom, randomMode, false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMusicDataByDateD(final boolean needRandom, final int randomMode) {
        AppDatabase.getInstance()
                .musicDao()
                .getAllDataByDateD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.init(musicEntities, needRandom, randomMode, false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMusicDataByDateA(final boolean needRandom, final int randomMode) {
        AppDatabase.getInstance()
                .musicDao()
                .getAllDataByDateA()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.init(musicEntities, needRandom, randomMode, false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMusicDataByLengthA(final boolean needRandom, final int randomMode) {
        AppDatabase.getInstance()
                .musicDao()
                .getAllDataByLengthA()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.init(musicEntities, needRandom, randomMode, false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMusicDataByLengthD(final boolean needRandom, final int randomMode) {
        AppDatabase.getInstance()
                .musicDao()
                .getAllDataByLengthD()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.init(musicEntities, needRandom, randomMode, false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getSmallRange(int x, int y) {
        AppDatabase.getInstance()
                .musicDao().getAllDataOrderByRandom(String.valueOf(x), String.valueOf(y))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.handleSmallRange(musicEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getMiddleRange(int x, int xx, int xxx, int y, int yy, int yyy) {
        AppDatabase.getInstance()
                .musicDao().getAllDataOrderByRandom(String.valueOf(x), String.valueOf(xx), String.valueOf(xxx), String.valueOf(y), String .valueOf(yy), String.valueOf(yyy))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.handleMiddleRange(musicEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void getLargeRange(int x, int xx, int xxx, int xxxx, int xxxxx, int y, int yy, int yyy, int yyyy, int yyyyy) {
        AppDatabase.getInstance()
                .musicDao().getAllDataOrderByRandom(String.valueOf(x), String.valueOf(xx),
                String.valueOf(xxx), String.valueOf(xxxx), String.valueOf(xxxxx), String.valueOf(y),
                String .valueOf(yy), String.valueOf(yyy), String.valueOf(yyyy), String.valueOf(yyyyy))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<MusicEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<MusicEntity> musicEntities) {
                        ipPlayer.handleLargeRange(musicEntities);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void handelSendAddVideo(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendNewVideo("https://www.switube.com/mobile_switube/SendPushYoutubeURLToChannel_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GPushNewVideo>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GPushNewVideo gPushNewVideo) {
                        ipPlayer.handlePushNewVideoSuccess(gPushNewVideo);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendLove(Map<String, String> map, final boolean isPush, final int pushIndex) {
        NetworkUtil.getInstance().getNetworkService()
                .sendLove("SendMapTubeCollect_v1.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GSendLove>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GSendLove gSendLove) {
                        ipPlayer.handleLoveData(gSendLove, isPush, pushIndex);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void sendReport(Map<String, String> map) {
        NetworkUtil.getInstance()
                .getNetworkService()
                .sendReport("https://www.switube.com/mobile_switube/SendTubeIDToReportNotification_v3.php", map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GReport>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(GReport gReport) {
                        ipPlayer.handleReportSuccess(gReport);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
