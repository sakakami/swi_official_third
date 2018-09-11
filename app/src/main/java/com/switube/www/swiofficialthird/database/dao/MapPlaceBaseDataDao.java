package com.switube.www.swiofficialthird.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.swiofficialthird.database.entity.MapPlaceBaseDataEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface MapPlaceBaseDataDao {
    @Query("SELECT * FROM placeBaseData")
    Maybe<List<MapPlaceBaseDataEntity>> getAllData();

    @Query("DELETE FROM placeBaseData")
    void cleanTable();

    @Insert
    void insertAllData(List<MapPlaceBaseDataEntity> baseDataEntityList);
}
