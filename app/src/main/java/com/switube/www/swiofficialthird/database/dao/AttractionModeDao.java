package com.switube.www.swiofficialthird.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionModeDao {
    @Query("SELECT * FROM attractionMode")
    List<AttractionModeEntity> getAttractionModeData();

    @Query("SELECT * FROM attractionMode")
    Maybe<List<AttractionModeEntity>> getAttractionModeDataWithRx();

    @Query("DELETE FROM attractionMode")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionModeEntity> attractionModeEntities);
}
