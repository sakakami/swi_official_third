package com.switube.www.landmark2018test.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.landmark2018test.database.entity.MapPlaceBaseSubDataEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface MapPlaceBaseSubDataDao {
    @Query("SELECT * FROM placeBaseSubData WHERE miid IN (:miid)")
    Maybe<List<MapPlaceBaseSubDataEntity>> getData(List<String> miid);

    @Query("DELETE FROM placeBaseSubData")
    void cleanTable();

    @Insert
    void insertAllData(List<MapPlaceBaseSubDataEntity> baseDataEntityList);
}
