package com.switube.www.swiofficialthird.home.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.database.entity.LanguageEntity;
import com.switube.www.swiofficialthird.home.gson.MediumMenuEntity;

import java.util.List;

public interface ILogoPresenter {
    void handleParseLanguage(Context context, MediumMenuEntity mediumMenuEntity);
    void handleUpgradeMessage(List<LanguageEntity> words);
    void handleFinishUpdate();
}
