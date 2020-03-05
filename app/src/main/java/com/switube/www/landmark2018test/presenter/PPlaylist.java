package com.switube.www.landmark2018test.presenter;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.database.entity.MusicEntity;
import com.switube.www.landmark2018test.model.MPlaylist;
import com.switube.www.landmark2018test.presenter.callback.IPPlaylist;
import com.switube.www.landmark2018test.view.callback.IVPlaylist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PPlaylist implements IPPlaylist {
    private IVPlaylist ivPlaylist;
    private MPlaylist mPlaylist;
    public PPlaylist(IVPlaylist ivPlaylist) {
        this.ivPlaylist = ivPlaylist;
        mPlaylist = new MPlaylist(this);
    }

    public void getSearchData(String keyword) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        if (keyword.length() > 3) {
            keyword = "%" + keyword + "%";
            mPlaylist.getSearchData(keyword);
        } else {
            StringBuilder stringBuilder = new StringBuilder(keyword);
            if (keyword.length() != 1) {
                stringBuilder.delete(1, stringBuilder.length());
            }
            Matcher matcher = pattern.matcher(stringBuilder.toString());
            if (matcher.matches()) {
                keyword = "%" + keyword + "%";
                mPlaylist.getSearchData(keyword);
            } else {
                ivPlaylist.showSearchError();
            }
        }
    }

    @Override
    public void handleSearchData(List<MusicEntity> musicEntities) {
        MyApplication.getAppData().setMusicEntities(musicEntities);
        mPlaylist.getAllData(musicEntities);
    }

    @Override
    public void handleSearchData(List<MusicEntity> musicEntities, List<MusicEntity> allData) {
        List<String> stid = new ArrayList<>();
        int size = musicEntities.size();
        for (int i = 0; i < size; i++) {
            stid.add(musicEntities.get(i).getStid());
        }
        size = allData.size();
        for (int i = 0; i < size; i++) {
            if (!stid.contains(allData.get(i).getStid())) {
                musicEntities.add(allData.get(i));
            }
        }
        ivPlaylist.init();
    }
}
