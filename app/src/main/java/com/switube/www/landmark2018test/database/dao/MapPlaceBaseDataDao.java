package com.switube.www.landmark2018test.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.switube.www.landmark2018test.database.entity.MapPlaceBaseDataEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface MapPlaceBaseDataDao {
    @Query("SELECT * FROM placeBaseData WHERE spid IN (:spid)")
    Maybe<List<MapPlaceBaseDataEntity>> getDataFromSpid(List<String> spid);

    @Query("DELETE FROM placeBaseData")
    void cleanTable();

    @Insert
    void insertAllData(List<MapPlaceBaseDataEntity> baseDataEntityList);
}
