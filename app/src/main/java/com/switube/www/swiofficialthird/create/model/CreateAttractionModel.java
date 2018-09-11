package com.switube.www.swiofficialthird.create.model;

import com.switube.www.swiofficialthird.create.gson.AttractionDetailEntity;
import com.switube.www.swiofficialthird.create.presenter.ICreateAttractionPresenter;
import com.switube.www.swiofficialthird.util.NetworkBUtil;
import com.switube.www.swiofficialthird.util.NetworkCUtil;
import com.switube.www.swiofficialthird.util.NetworkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateAttractionModel {
    private ICreateAttractionPresenter mICreateAttractionPresenter;
    public CreateAttractionModel(ICreateAttractionPresenter iCreateAttractionPresenter) {
        mICreateAttractionPresenter = iCreateAttractionPresenter;
    }

    public void getAttractionDetail(final String id, String key, final String language) {
        NetworkCUtil.getInstance()
                .getNetworkService()
                .getAttractionDetailWithRx("json", NetworkCUtil.getInstance().getMap(id, key, language))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AttractionDetailEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(AttractionDetailEntity attractionDetailEntity) {
                        switch (language) {
                            case "jp":
                                mICreateAttractionPresenter.handleAttractionDetail(attractionDetailEntity);
                                break;
                            case "zh-TW":
                                mICreateAttractionPresenter.handleAttractionDetailTwo(id, attractionDetailEntity);
                                break;
                            case "zh-CN":
                                mICreateAttractionPresenter.handleAttractionDetailThree(id, attractionDetailEntity);
                                break;
                            default:
                                mICreateAttractionPresenter.handleAttractionDetailOne(id, attractionDetailEntity);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
