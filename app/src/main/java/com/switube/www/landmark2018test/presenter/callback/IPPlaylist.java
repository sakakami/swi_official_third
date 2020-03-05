package com.switube.www.landmark2018test.presenter.callback;

import com.switube.www.landmark2018test.database.entity.MusicEntity;

import java.util.List;

public interface IPPlaylist {
    void handleSearchData(List<MusicEntity> musicEntities);
    void handleSearchData(List<MusicEntity> musicEntities, List<MusicEntity> allData);
}
