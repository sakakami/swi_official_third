package com.switube.www.swiofficialthird.home.presenter;

import android.content.Context;
import android.net.ConnectivityManager;

import com.switube.www.swiofficialthird.database.entity.LanguageEntity;
import com.switube.www.swiofficialthird.home.gson.MediumMenuEntity;
import com.switube.www.swiofficialthird.home.model.LogoModel;
import com.switube.www.swiofficialthird.home.view.ILogoFragment;
import com.switube.www.swiofficialthird.util.AppConstant;
import com.switube.www.swiofficialthird.util.NetworkUtil;
import com.switube.www.swiofficialthird.util.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class LogoPresenter implements ILogoPresenter {
    private ILogoFragment mILogoFragment;
    private LogoModel mLogoModel;
    public LogoPresenter(ILogoFragment iLogoFragment) {
        mILogoFragment = iLogoFragment;
        mLogoModel = new LogoModel(this);
    }

    private String getLanguageArea() {
        return Locale.getDefault().getLanguage();
    }

    private String getCountryArea() {
        return Locale.getDefault().getCountry();
    }

    public void getData(Context context) {
        mLogoModel.handleGetData(context, new HashMap<>(NetworkUtil.getInstance().getMap(context)));
    }

    public boolean checkNetworkState(Context context) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager != null && manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isAvailable();
    }

    @Override
    public void handleParseLanguage(Context context, MediumMenuEntity mediumMenuEntity) {
        if (mediumMenuEntity.getAdkey4().isEmpty()) {
            SharePreferencesUtil
                    .getInstance()
                    .getEditor(context)
                    .putString("adKey", mediumMenuEntity.getAdkey3())
                    .apply();
        } else {
            SharePreferencesUtil
                    .getInstance()
                    .getEditor(context)
                    .putString("adKey", mediumMenuEntity.getAdkey4())
                    .apply();
        }
        int size = mediumMenuEntity.getWdata().size();
        List<LanguageEntity> languageEntities = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            languageEntities.add(
                    new LanguageEntity(
                            mediumMenuEntity.getWdata().get(i).getWordid(),
                            mediumMenuEntity.getWdata().get(i).getWord_en(),
                            mediumMenuEntity.getWdata().get(i).getWord_tw(),
                            mediumMenuEntity.getWdata().get(i).getWord_ch(),
                            mediumMenuEntity.getWdata().get(i).getWord_jp(),
                            mediumMenuEntity.getWdata().get(i).getStatus()
                    )
            );
        }

        if (Integer.parseInt(mediumMenuEntity.getA_version()) > AppConstant.VERSION) {
            mLogoModel.handleInsertData(context, languageEntities, true);
        } else {
            mLogoModel.handleInsertData(context, languageEntities, false);
        }
    }

    @Override
    public void handleUpgradeMessage(List<LanguageEntity> words) {
        String message1;
        String message2;
        String message3;
        switch (getLanguageArea()) {
            case "zh":
                switch (getCountryArea()) {
                    case "TW":
                        message1 = words.get(0).getWordTw();
                        message2 = words.get(1).getWordTw();
                        message3 = words.get(2).getWordTw();
                        break;
                    case "CN":
                        message1 = words.get(0).getWordCn();
                        message2 = words.get(1).getWordCn();
                        message3 = words.get(2).getWordCn();
                        break;
                    default:
                        message1 = words.get(0).getWordEn();
                        message2 = words.get(1).getWordEn();
                        message3 = words.get(2).getWordEn();
                        break;
                }
                break;
            case "jp":
                message1 = words.get(0).getWordJp();
                message2 = words.get(1).getWordJp();
                message3 = words.get(2).getWordJp();
                break;
            default:
                message1 = words.get(0).getWordEn();
                message2 = words.get(1).getWordEn();
                message3 = words.get(2).getWordEn();
                break;
        }
        String[] word = new String[3];
        if (message1.contains("<")) {
            StringBuilder stringBuilder = new StringBuilder(message1);
            int index = stringBuilder.indexOf("<");
            stringBuilder.replace(index, index + 2, "\n");
            word[0] = stringBuilder.toString();
        } else {
            word[0] = message1;
        }
        if (message2.contains("<")) {
            StringBuilder stringBuilder = new StringBuilder(message2);
            int index = stringBuilder.indexOf("<");
            stringBuilder.replace(index, index + 2, "\n");
            word[1] = stringBuilder.toString();
        } else {
            word[1] = message2;
        }
        if (message3.contains("<")) {
            StringBuilder stringBuilder = new StringBuilder(message3);
            int index = stringBuilder.indexOf("<");
            stringBuilder.replace(index, index + 2, "\n");
            word[2] = stringBuilder.toString();
        } else {
            word[2] = message3;
        }
        mILogoFragment.showUpgradeMessage(word);
    }

    @Override
    public void handleFinishUpdate() {
        mILogoFragment.handleFinishUpdate();
    }
}
