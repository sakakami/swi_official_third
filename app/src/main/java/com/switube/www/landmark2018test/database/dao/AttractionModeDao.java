package com.switube.www.landmark2018test.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface AttractionModeDao {
    @Query("SELECT * FROM attractionMode")
    Maybe<List<AttractionModeEntity>> getAttractionModeDataWithRx();

    @Query("DELETE FROM attractionMode")
    void cleanTable();

    @Insert
    void insertAllData(List<AttractionModeEntity> attractionModeEntities);
}
